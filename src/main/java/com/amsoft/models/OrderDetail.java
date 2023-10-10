package com.amsoft.models;

public class OrderDetail {
	private Long idOrderDetail;
	private Double price;
	private Integer quantity;
	private Double total;
	private String size;

	private Long orderId;
	private Product product;

	public OrderDetail() {
	}

	public void setIdOrderDetail(Long idOrderDetail) {
		this.idOrderDetail = idOrderDetail;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getIdOrderDetail() {
		return idOrderDetail;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getTotal() {
		return total;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Product getProduct() {
		return product;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSize() {
		return size;
	}

}
