package com.coding.house.store.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	private String title;
    private String description;
    private int pages;
    private String summaryPath;
    
    @DateTimeFormat
    private Calendar releaseDate;
    
    @ElementCollection
    private List<Price> prices;
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public Calendar getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public String getSummaryPath() {
		return summaryPath;
	}

	public void setSummaryPath(String summaryPath) {
		this.summaryPath = summaryPath;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "Produto [titulo=" + title + ", descricao=" + description + ", paginas=" + pages + "]";
    }

	public BigDecimal forPrice(PriceType priceType) {
		return prices.stream().filter(price -> price.getType().equals(priceType))
				.findFirst().get().getAmount();
		
	}

}
