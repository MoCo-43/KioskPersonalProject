package com.prj.service;

import java.util.ArrayList;
import java.util.List;

import com.prj.common.OrderDAO;
import com.prj.vo.Order;

public class OrderServiceDAO implements OrderService {
	OrderDAO odao = new OrderDAO();
	List<Order> cart = new ArrayList<>();

	//// 장바구니 메뉴
	// 장바구니 추가
	@Override
	public boolean addCart(Order order) {
		return odao.insert(order) == 1;
//		return cart.add(order);
	}



	// 장바구니 삭제
	@Override
	public boolean removeCart(Order order) {
	  return odao.delete(order) == 1;
	}
	


	
	// 장바구니 목록 조회 (DB직접접근 및 리스트)
	@Override
	public List<Order> getCart() {
		return odao.select();
	}



	
// 장바구니 추가
//	@Override
//	public boolean addCart(Order order) {
//		return odao.insert(order) == 1;
//		return cart.add(order);
//	}	
	

// 장바구니 삭제
//	@Override
//	public boolean removeCart(Order order) {
//	  for (int i = 0; i < cart.size(); i++) {
//	     if (cart.get(i).getOrderNo() == orderNo) {
//	          cart.remove(i);
//	            return true;
//	     }
//	   }
//	 return false;
//	}
	

//	// 장바구니 비우기
//	@Override
//	public void clearCart() {
////		cart.clear();  // list형태로 만들었을때 
//		odao.clearCart();
//	}


// 장바구니 목록 조회 (원본)
//	@Override
//	public List<Order> getCart() {
////		return new ArrayList<>(cart);
//		return odao.orderListSelect();
//	}	
	
	
//	// 장바구니 삭제(원본)
//	@Override
//	public boolean removeCart(int orderNo) {
//	  return odao.delete(orderNo) == 1;
//  // 리스트 삭제
////	  for (int i = 0; i < cart.size(); i++) {
////	     if (cart.get(i).getOrderNo() == orderNo) {
////	          cart.remove(i);
////	            return true;
////	     }
////	   }
////	 return false;
//	}
	
}
