package com.ss.InventoryService.service;

import java.util.List;

import com.ss.InventoryService.entity.Inventory;
import com.ss.InventoryService.exceptions.InventoryNotFoundException;

public interface InventoryRouteService {

	
    // Save operation
	Inventory saveBusInventory(Inventory Inventory);
 
    // Read operation
    List<Inventory> fetchBusInventoryList() throws InventoryNotFoundException;
    
    // Read operation
    Inventory fetchBusInventory(Long InventoryId) throws InventoryNotFoundException;
 
    // Update operation
    Inventory updateBusInventory(Inventory Inventory,
                                Long InventoryId) throws InventoryNotFoundException;
 
    // Delete operation
    void deleteBusInventoryById(Long InventoryId) throws InventoryNotFoundException;
}
