package com.prj.service;

import java.util.List;

import com.prj.vo.Menu;

public interface OrderService {
  public boolean makeOrder(List<Menu>cart);  // 고객 장바구니 결제(처리)
}
