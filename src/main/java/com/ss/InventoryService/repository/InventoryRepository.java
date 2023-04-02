package com.ss.InventoryService.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.InventoryService.entity.Inventory;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

}

