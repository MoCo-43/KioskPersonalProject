package com.prj.service;

import java.util.ArrayList;
import java.util.List;

import com.prj.common.MenuDAO;
import com.prj.vo.Menu;

// DAO 상속
public class MenuServiceDAO implements MenuService {
	MenuDAO mdao = new MenuDAO();
    List<Menu> cart = new ArrayList<>();
    
	////메뉴항목
	
	// 메뉴등록
	@Override
	public boolean addMenu(Menu menu) {
		return mdao.insert(menu) == 1;
	}

	// 메뉴변경
	@Override
	public boolean modifyMenu(Menu menu) {
		return mdao.update(menu) == 1;
	}

	
	// 메뉴삭제
	@Override
	public boolean removeMenu(int menuNo) {
		return mdao.delete(menuNo) == 1;
	}

	// 메뉴출력
	@Override
	public List<Menu> menuList() {
		return mdao.select();
	}
	
	
    ////장바구니 메뉴
    
    // 장바구니 추가
	@Override
	public boolean addCart(Menu menu) {
		return cart.add(menu);
	}

	// 장바구니 삭제
	@Override
	public boolean removeCart(int menuNo) {
	    for (int i = 0; i < cart.size(); i++) {
	        if (cart.get(i).getMenuNo() == menuNo) {
	            cart.remove(i);
	            return true;
	        }
	    }
	    return false;
	}

	// 장바구니 목록 조회
	@Override
	public List<Menu> getCart() {
		return new ArrayList<>(cart);
	}

	// 장바구니 비우기
	@Override
	public void clearCart() {
		cart.clear();
	}


}


