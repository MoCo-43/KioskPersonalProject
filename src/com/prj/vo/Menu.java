package com.prj.vo;

public class Menu {
	
	// 필드
	private int menuNo;
	private String menuName;
	private int menuPrice;
	private String menuInfo;


	// 생성자
	public Menu() {
	}

	public Menu(int menuNo, String menuName, int menuPrice, String menuInfo) {
		this.menuNo = menuNo;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuInfo = menuInfo;
	}
	
	//// getter
	public int getMenuNo() {
		return menuNo;
	}
	
	public String getMenuName() {
		return menuName;
	}

	public int getMenuPrice() {
		return menuPrice;
	}
	
	public String getMenuInfo() {
		return menuInfo;
	}
	
	
	//// setter
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}


	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	
	public void setMenuInfo(String menuInfo) {
		this.menuInfo = menuInfo;
	}
	
}
