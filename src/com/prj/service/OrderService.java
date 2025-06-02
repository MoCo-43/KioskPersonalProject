package com.prj.service;

import java.util.List;

import com.prj.vo.Menu;
import com.prj.vo.Order;

public interface OrderService {
	
  // 장바구니 조회 및 관리
  public boolean addCart(Order order);  // 장바구니 추가
//  public boolean removeCart(int orderNo);  // 장바구니 삭제
  public boolean removeCart(Order order);  // 장바구니 삭제
//  public List<Order> getCart();  // 장바구니 목록 조회
  public List<Order> getCart();  // 장바구니 목록 조회
  public void clearCart();  // 장바구니 비우기
  
}
