package com.ss.InventoryService.broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.ss.InventoryService.entity.Inventory;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JMSProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${active-mq.topicProducer}")
    private String topic;
    
   

    public void sendMessage(Inventory message){
        try{
            log.info("Attempting Send message to Topic: "+ topic);
            jmsTemplate.convertAndSend(topic, message);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }
}
