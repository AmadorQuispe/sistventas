package com.amsoft.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//id_order,customer_id,payment_id,required_date,is_paid,status
public class Order {
	private Long idOrder;
	private LocalDate requiredDate;
	private Boolean isPaid;
	private String status;
	private Double total;

	private Long customerId;
	private Long paymentId;

	private List<OrderDetail> orderDetails;

	public Order() {
		this.orderDetails = new ArrayList<OrderDetail>();
	}

	public Order(Long idOrder, LocalDate requiredDate, Boolean isPaid, String status, Double total, Long customerId,
			Long paymentId, List<OrderDetail> orderDetails) {
		this.idOrder = idOrder;
		this.requiredDate = requiredDate;
		this.isPaid = isPaid;
		this.status = status;
		this.total = total;
		this.customerId = customerId;
		this.paymentId = paymentId;
		this.orderDetails = orderDetails;
	}

	public void addOrderDetail(OrderDetail orderDetail) {
		int pos = 0;
		if (orderDetails.size() > 0) {
			Long idProductToAdd = orderDetail.getProduct().getIdProduct();
			String sizeToAdd = orderDetail.getSize();
			for (int i = 0; i < orderDetails.size(); i++) {
				Long idProduct = orderDetails.get(i).getProduct().getIdProduct();
				String size = orderDetails.get(i).getSize();
				if (idProductToAdd == idProduct && sizeToAdd.equalsIgnoreCase(size))
					pos = i;
			}
			if (idProductToAdd == orderDetails.get(pos).getProduct().getIdProduct()
					&& sizeToAdd.equalsIgnoreCase(orderDetails.get(pos).getSize())) {
				Integer updateQuantity = orderDetails.get(pos).getQuantity() + orderDetail.getQuantity();
				Double updateTotal = orderDetails.get(pos).getTotal() + orderDetail.getTotal();
				orderDetails.get(pos).setQuantity(updateQuantity);
				orderDetails.get(pos).setTotal(updateTotal);
			} else {
				orderDetails.add(orderDetail);
			}
		} else {
			orderDetails.add(orderDetail);
		}

	}

	public void removeOrderDetail(String slug, String size) {
		for (OrderDetail orderDetail : this.orderDetails) {
			String slugPres = orderDetail.getProduct().getSlug();
			if (slug.equalsIgnoreCase(slugPres) && size.equals(orderDetail.getSize())) {
				orderDetails.remove(orderDetail);
				break;
			}
		}
	}

	public void updateQuantity(String slug, String size, int numberIn) {
		for (OrderDetail orderDetail : this.orderDetails) {
			String slugPres = orderDetail.getProduct().getSlug();
			if (numberIn <= 0) {
				if (slug.equalsIgnoreCase(slugPres) && size.equals(orderDetail.getSize()) && orderDetail.getQuantity() > 1) {
					orderDetail.setQuantity(orderDetail.getQuantity() + numberIn);
					orderDetail.setTotal(orderDetail.getPrice() * orderDetail.getQuantity());
					break;
				}
			}else{
				if (slug.equalsIgnoreCase(slugPres) && size.equals(orderDetail.getSize()) && orderDetail.getQuantity() >= 1) {
					orderDetail.setQuantity(orderDetail.getQuantity() + numberIn);
					orderDetail.setTotal(orderDetail.getPrice() * orderDetail.getQuantity());
					break;
				}
			}

		}
	}

	public Integer getTotalQuantity() {
		Integer total = 0;
		for (OrderDetail orderDetail : orderDetails) {
			total += orderDetail.getQuantity();
		}
		return total;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public LocalDate getRequiredDate() {
		return requiredDate;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public String getStatus() {
		return status;
	}

	public Double getTotal() {
		Double total = 0D;
		for (OrderDetail orderDetail : orderDetails) {
			total += orderDetail.getTotal();
		}
		return total;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public void setRequiredDate(LocalDate requiredDate) {
		this.requiredDate = requiredDate;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
