package com.ss.InventoryService.broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.ss.InventoryService.entity.Inventory;
import com.ss.InventoryService.service.InventoryRouteService;

import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JmsConsumer implements MessageListener {

	@Autowired  
	InventoryRouteService inventoryRouteService;

    @Override
    @JmsListener(destination = "${active-mq.topicConsumer}")
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage)message;
            Inventory employee = (Inventory)objectMessage.getObject();
            inventoryRouteService.saveBusInventory(employee);
            log.info("Received Message from Topic: "+ employee.toString());
        } catch(Exception e) {
            log.error("Received Exception while processing message: "+ e);
        }

    }
}    