package com.prj.service;

import java.util.List;
import com.prj.vo.Menu;

public interface MenuService {
  public boolean addMenu(Menu menu);  // 메뉴등록
  public boolean modifyMenu(Menu menu);  // 메뉴 변경
  public boolean removeMenu(int menuNo);  // 메뉴 삭제
  public List<Menu> menuList();  // 메뉴 출력
  
  // 장바구니 인터페이스
  public boolean addCart(Menu menu);  // 장바구니 추가
  public boolean removeCart(int menuNo);  // 장바구니 삭제
  public List<Menu> getCart();  // 장바구니 목록 조회
  public void clearCart();  // 장바구니 비우기
}
