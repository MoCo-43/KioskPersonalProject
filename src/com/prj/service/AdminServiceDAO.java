package com.prj.service;

//import java.util.Map;

import com.prj.common.MenuDAO;
import com.prj.common.OrderDAO;
import com.prj.vo.Menu;
import com.prj.vo.Order;

public class AdminServiceDAO implements AdminService {
    OrderDAO odao = new OrderDAO();
    MenuDAO mdao = new MenuDAO();
    
    // 주문취소
	@Override
	public boolean cancelOrders(Order order) {
	  return odao.delete(order) == 1;
	}
	
	// 메뉴수정
	@Override
	public boolean editMenu(Menu menu) {
	  return mdao.update(menu) == 1;
	}

}


//// 주문내역집계
//@Override
//public Map<String, Integer> getOrderSummary() {
//  return odao.getOrderSummary();
//}
//