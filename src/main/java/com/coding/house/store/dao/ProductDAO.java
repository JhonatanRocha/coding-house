package com.coding.house.store.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coding.house.store.model.PriceType;
import com.coding.house.store.model.Product;

@Repository
@Transactional
public class ProductDAO {

	@PersistenceContext
    private EntityManager manager;

    public void save(Product product) {
        manager.persist(product);
    }
    
    public List<Product> list(){
    	return manager.createQuery("select p from Product p").getResultList();
    }
    
    public Product find(int id) {
    	return manager.createQuery("select distinct (p) from Product p "
    			+ "join fetch p.prices where p.id = :id", Product.class).setParameter("id", id).getSingleResult();
    }
    
    public BigDecimal productSumByBookType(PriceType priceType) {
    	
    	//"select sum(preco.valor) from Produto p join p.precos preco where preco.tipo = :tipoPreco"
    	
    	TypedQuery<BigDecimal> query = manager.createQuery("select sum(price.amount) from Product p " + 
    							"join p.prices price where price.type = :priceType", BigDecimal.class);
    
    	query.setParameter("priceType", priceType);
    	
    	return query.getResultList().get(0);
    }
}
