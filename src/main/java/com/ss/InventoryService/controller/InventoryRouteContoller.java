package com.ss.InventoryService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ss.InventoryService.broker.JMSProducer;
import com.ss.InventoryService.entity.Inventory;
import com.ss.InventoryService.exceptions.InventoryNotFoundException;
import com.ss.InventoryService.service.InventoryRouteService;

@RestController
public class InventoryRouteContoller {
	
	
	@Autowired  
	InventoryRouteService inventoryRouteService;
	
	
	@Autowired
    JMSProducer jmsProducer;

    @PostMapping(value="/jms/inventory")
    public Inventory sendMessage(@PathVariable("inventoryid") long busroutesid){
        Inventory availableSeats = inventoryRouteService.fetchBusInventory(busroutesid);
    	jmsProducer.sendMessage(availableSeats);
        return availableSeats;
    }
	
	@GetMapping("/inventory")  
	private ResponseEntity<List<Inventory>> getAllBusRoutes() throws InventoryNotFoundException 
	{  
	return new ResponseEntity<>(inventoryRouteService.fetchBusInventoryList(), 
			   HttpStatus.OK);
	}  
	
	@GetMapping("/inventory/{inventoryid}")  
	private ResponseEntity<Inventory> getBusroutesId(@PathVariable("inventoryid") long busroutesid) throws InventoryNotFoundException  
	{  
	return new ResponseEntity<>(inventoryRouteService.fetchBusInventory(busroutesid), 
			   HttpStatus.OK);
	}  
	
	@DeleteMapping("/inventory/{inventoryid}")  
	private ResponseEntity<String> deleteBook(@PathVariable("inventoryid") long inventoryid)  throws InventoryNotFoundException 
	{  
		try{
			inventoryRouteService.deleteBusInventoryById(inventoryid);  
			return ResponseEntity.ok("Entity deleted");      
		   }
		   catch (EmptyResultDataAccessException e){
		      return ResponseEntity.notFound().build();
		  } 
		
		
	}
	
	@PostMapping("/inventory")  
	@ResponseStatus(HttpStatus.OK)
	private ResponseEntity<String> saveBook(@RequestBody Inventory busroute)   
	{  
		inventoryRouteService.saveBusInventory(busroute) ;
		return new ResponseEntity<>("Bus Route updated success", 
				   HttpStatus.OK);  
	
	}  
	
	@PutMapping("/inventory/{inventoryid}")  
	@ResponseStatus(HttpStatus.OK)
	private ResponseEntity<String> update(@RequestBody Inventory busroute,@PathVariable("inventoryid") long inventoryid) throws InventoryNotFoundException  
	{  
		inventoryRouteService.updateBusInventory(busroute, inventoryid);  
		return new ResponseEntity<>("Bus Route updated success", 
				   HttpStatus.OK);   
	}  

}
