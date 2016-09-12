package com.coding.house.store.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coding.house.store.model.Product;

@Repository
@Transactional
public class ProductDAO {

	@PersistenceContext
    private EntityManager manager;

    public void save(Product product){
        manager.persist(product);
    }
}
