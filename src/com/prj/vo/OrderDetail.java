package com.prj.vo;

public class OrderDetail {
	
	private int menuNo;
	private int orderNo;
	private int menuPrice;
	private int quantity;
	private String customerOption;

	public OrderDetail() {
	}
	
	

	public OrderDetail(int menuNo, int orderNo, int menuPrice, int quantity, String customerOption) {
		this.menuNo = menuNo;
		this.orderNo = orderNo;
		this.menuPrice = menuPrice;
		this.quantity = quantity;
		this.customerOption = customerOption;
	}



	public int getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCustomerOption() {
		return customerOption;
	}

	public void setCustomerOption(String customerOption) {
		this.customerOption = customerOption;
	}

}
