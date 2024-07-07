package com.smit_proovitoo.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.smit_proovitoo.backend.repository.ProcedureRepository;
import com.smit_proovitoo.backend.config.RabbitMQQueue;
import com.smit_proovitoo.backend.dto.ProcedureDto;
import com.smit_proovitoo.backend.model.Person;
import com.smit_proovitoo.backend.model.Procedure;

@Service
public class ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final RabbitTemplate rabbitTemplate;
    private final PersonService personService;
    private static final Logger logger = LoggerFactory.getLogger(ProcedureService.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing-key.suffix}")
    private String routingKeySuffix;

    @Autowired
    public ProcedureService(ProcedureRepository procedureRepository, RabbitTemplate rabbitTemplate, PersonService personService) {
        this.procedureRepository = procedureRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.personService = personService;
    }

    /**
     * Retrieves all procedures.
     *
     * @return a list of all procedures.
     */
    public List<Procedure> getAllProcedures() {
        return procedureRepository.findAll();
    }

    /**
     * Creates a new procedure and sends and email.
     *
     * @param procedureDto the data transfer object containing procedure details.
     * @return the created procedure.
     */
    public Procedure createProcedure(ProcedureDto procedureDto) {
        
        Procedure newProcedure = new Procedure();
        newProcedure.setEmailSent(false);
        newProcedure.setReason(procedureDto.getReason());

        Person person = personService.getExistingPerson(procedureDto.getIdentityCode());
        if (person == null) {
            person = new Person();
            person.setName(procedureDto.getName());
            person.setEmail(procedureDto.getEmail());
            person.setIdentityCode(procedureDto.getIdentityCode());
            person = personService.savePerson(person);
        }

        newProcedure.setPerson(person);
        Procedure createdProcedure = procedureRepository.save(newProcedure);

        sendEmail(createdProcedure.getId());

        return createdProcedure;

    }
    
    /**
     * Sends an email for the given procedure ID.
     *
     * @param procedureId the ID of the procedure.
     */
    public void sendEmail(Long procedureId) {
        String routingKey = RabbitMQQueue.PROCEDURE_RECEIVE_QUEUE + routingKeySuffix;
        logger.info(String.format("Sending email to queue with procedure id %s", procedureId));
        rabbitTemplate.convertAndSend(exchange, routingKey, procedureId);
    }

}
