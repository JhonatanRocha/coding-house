package com.coding.house.store.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.coding.house.store.conf.DataSourceConfigurationTest;
import com.coding.house.store.conf.JPAConfiguration;
import com.coding.house.store.dao.ProductDAO;
import com.coding.house.store.model.PriceType;
import com.coding.house.store.model.Product;
import com.coding.house.store.test.ProductBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class,ProductDAO.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProductDAOTest {

	@Autowired
	private ProductDAO productDAO;  
	 
	@Test
	@Transactional
	public void mustSumAllPricesByBookType() {
		
		List<Product> physicalBooks = ProductBuilder.newProduct(PriceType.PHYSICAL, BigDecimal.TEN).more(3).buildAll();
		List<Product> eBooks = ProductBuilder.newProduct(PriceType.EBOOK, BigDecimal.TEN).more(3).buildAll();
		
		physicalBooks.stream().forEach(productDAO::save);
		eBooks.stream().forEach(productDAO::save);
		
		BigDecimal valor = productDAO.productSumByBookType(PriceType.EBOOK);
	    Assert.assertEquals(new BigDecimal(40).setScale(2), valor);
	}
}
