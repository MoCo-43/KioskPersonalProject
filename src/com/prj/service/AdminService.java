package com.prj.service;

//import java.util.Map;

import com.prj.vo.Menu;
import com.prj.vo.Order;

public interface AdminService {
	
  // 관리자 메뉴
  public boolean cancelOrders(Order order);  // 주문취소
  public boolean editMenu(Menu menu); // 메뉴수정
  
}

//public Map<String, Integer> getOrderSummary();  // 주문내역집계