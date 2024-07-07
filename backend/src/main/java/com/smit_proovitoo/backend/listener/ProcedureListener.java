package com.smit_proovitoo.backend.listener;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.smit_proovitoo.backend.model.Procedure;
import com.smit_proovitoo.backend.repository.ProcedureRepository;
import com.smit_proovitoo.backend.config.RabbitMQQueue;

@Component
public class ProcedureListener {

    private final ProcedureRepository procedureRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProcedureListener.class);

    @Autowired
    public ProcedureListener(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    /**
     * Handles the receipt of a procedure email.
     *
     * @param id the ID of the procedure as a string.
    */
    @RabbitListener(queues = RabbitMQQueue.PROCEDURE_RECEIVE_QUEUE)
    public void receiveEmailResponse(String id) {
        Long parsedId = Long.parseLong(id);
        Optional<Procedure> optionalProcedure = procedureRepository.findById(parsedId);

        if (optionalProcedure.isPresent()) {
            Procedure procedure = optionalProcedure.get();
            procedure.setEmailSent(true);
            procedureRepository.save(procedure);
            logger.info(String.format("Email with procedure id %s has been sent and received", id));
        } else {
            logger.error(String.format("Encountered an error while receiving a message from procedure with id %s", id));
        }
    }

}
