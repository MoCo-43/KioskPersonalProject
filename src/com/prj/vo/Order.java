package com.prj.vo;

public class Order {

	// 필드
	private int orderNo;
	private String orderName;
	private int orderPrice;
	private String orderInfo;

	// 생성자
	public void Order() {
	}

	public void Order(int orderNo, String orderName, int orderPrice, String orderInfo) {
		this.orderNo = orderNo;
		this.orderName = orderName;
		this.orderPrice = orderPrice;
		this.orderInfo = orderInfo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

}
