package com.smit_proovitoo.email_service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smit_proovitoo.email_service.config.RabbitMQQueue;
import com.smit_proovitoo.email_service.service.EmailService;

@Component
public class EmailListener {

    private static final Logger logger = LoggerFactory.getLogger(EmailListener.class);

    @Autowired
    EmailService procedureMessagingService;

    /**
     * Handles messages from the procedure email send queue.
     *
     * @param id, the ID of the procedure.
     */
    @RabbitListener(queues = RabbitMQQueue.PROCEDURE_EMAIL_SEND_QUEUE)
    public void receiveProcedureStartMessage(Long procedureId) {
        logger.info(String.format("Received email with procedure id %s.", procedureId.toString()));
        procedureMessagingService.sendResponse(procedureId);
    }
}
