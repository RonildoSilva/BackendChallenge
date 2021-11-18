package com.example.producer;

import com.example.domain.model.UserMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.example.domain.model.mq.MQConstants.EXCHANGE;
import static com.example.domain.model.mq.MQConstants.ROUTING_KEY;

@RestController
@RequestMapping(value = "/api/v1/message")
public class ProducerController {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{email}")
    public ResponseEntity<String> envioMensagem(@RequestBody String message) {
        UserMessage userMessage = new UserMessage("Mensagem de : " + message, new Date());
        template.convertAndSend(EXCHANGE, ROUTING_KEY, userMessage);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
