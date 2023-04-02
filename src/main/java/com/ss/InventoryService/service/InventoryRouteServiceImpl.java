package com.ss.InventoryService.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.InventoryService.entity.Inventory;
import com.ss.InventoryService.exceptions.InventoryNotFoundException;
import com.ss.InventoryService.repository.InventoryRepository;

import jakarta.transaction.Transactional;

@Service
public class InventoryRouteServiceImpl implements InventoryRouteService {

	@Autowired
	InventoryRepository inventoryRepository;
	
	@Override
	@Transactional
	public Inventory saveBusInventory(Inventory route) {
		// TODO Auto-generated method stub
		return inventoryRepository.save(route);
	}

	@Override
	public List<Inventory> fetchBusInventoryList() throws InventoryNotFoundException{
		return (List<Inventory>)
				inventoryRepository.findAll();
	}

	@Override
	@Transactional
	public Inventory updateBusInventory(Inventory route, Long routeId) throws InventoryNotFoundException{
		Inventory depDB
        = inventoryRepository.findById(routeId)
              .get();

    if (Objects.nonNull(route.getAvailableSeats())
        && !"".equalsIgnoreCase(
        		route.getAvailableSeats())) {
        depDB.setAvailableSeats(
        		route.getAvailableSeats());
    }
    
    if (Objects.nonNull(route.getLastUpdatedDate())) {
            depDB.setLastUpdatedDate(
            		route.getLastUpdatedDate());
     }
    
    return inventoryRepository.save(depDB);

	}

	@Override
	@Transactional
	public void deleteBusInventoryById(Long routeId) throws InventoryNotFoundException{
		inventoryRepository.deleteById(routeId);
		
	}

	@Override
	public Inventory fetchBusInventory(Long routeId) throws InventoryNotFoundException{
		// TODO Auto-generated method stub
		return inventoryRepository.findById(routeId)
	              .get();
	}


}
