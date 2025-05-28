package com.prj.service;

import java.util.ArrayList;
import java.util.List;

import com.prj.common.MenuDAO;
import com.prj.vo.Menu;

// DAO 상속
public class MenuServiceDAO implements MenuService {
	MenuDAO mdao = new MenuDAO();
    List<Menu> cart = new ArrayList<>();
    
    
	@Override
	public boolean addCart(Menu menu) {
		return cart.add(menu);
	}

	@Override
	public boolean removeCart(int menuNo) {
		for(int i = 0; i<cart.size(); i++) {
			cart.remove(i);
			return true;
		}
		
		return false;
	}

	@Override
	public List<Menu> getCart() {
		return new ArrayList<>(cart);
	}

	@Override
	public void clearCart() {
		cart.clear();
	}

	@Override
	public boolean addMenu(Menu menu) {
		return mdao.insert(menu) == 1;
	}

	@Override
	public boolean modifyMenu(Menu menu) {
		return mdao.update(menu) == 1;
	}

	@Override
	public boolean removeMenu(int menuNo) {
		return mdao.delete(menuNo) == 1;
	}

	@Override
	public List<Menu> menuList() {
		return null;
	}

}
