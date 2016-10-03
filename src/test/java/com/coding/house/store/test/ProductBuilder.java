package com.coding.house.store.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.coding.house.store.model.Price;
import com.coding.house.store.model.Product;
import com.coding.house.store.model.PriceType;

public class ProductBuilder {

    private List<Product> products = new ArrayList<>();

    private ProductBuilder(Product product) {
        products.add(product);
    }

    public static ProductBuilder newProduct(PriceType priceType, BigDecimal valor) {
    	Product book = create("book 1", priceType, valor);
        return new ProductBuilder(book);
    }

    public static ProductBuilder newProduct() {
        Product book = create("book 1", PriceType.COMBO, BigDecimal.TEN);
        return new ProductBuilder(book);
    }

    private static Product create(String bookName, PriceType priceType, BigDecimal valor) {
    	Product book = new Product();
        book.setTitle(bookName);
        book.setReleaseDate(Calendar.getInstance());
        book.setPages(150);
        book.setDescription("Best book about testing");
        
        Price price = new Price();
        price.setType(priceType);
        price.setAmount(valor);
        book.getPrices().add(price);
        return book;
    }

    public ProductBuilder more(int number) {
    	Product base = products.get(0);
    	Price price = base.getPrices().get(0);
        for (int i = 0; i < number; i++) {
            products.add(create("Book " + i, price.getType(), price.getAmount()));
        }
        return this;
    }

    public Product buildOne() {
        return products.get(0);
    }

    public List<Product> buildAll() {
        return products;
    }
}
