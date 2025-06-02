package com.prj.service;

//import java.util.Map;

import com.prj.vo.Menu;
import com.prj.vo.Order;

public interface AdminService {
	
  // 관리자 메뉴
  public boolean cancelOrders();  // 주문취소
//  public Map<String, Integer> getOrderSummary();  // 주문내역집계
  public boolean editMenu(Menu menu); // 메뉴수정
  
}
