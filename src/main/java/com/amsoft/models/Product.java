package com.amsoft.models;

public class Product {

	private Long idProduct;
	private String slug;
	private String name;
	private String description;
	private String urlImage;
	private Double price;
	private int stock;

	public Product() {
	}

	public Product(Long idProduct, String slug, String name, String description, String urlImage, Double price,
			int stock) {
		this.idProduct = idProduct;
		this.slug = slug;
		this.name = name;
		this.description = description;
		this.urlImage = urlImage;
		this.price = price;
		this.stock = stock;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public String getSlug() {
		return slug;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public Double getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

}
