package com.prj.service;

import java.util.List;

import com.prj.common.MenuDAO;
import com.prj.vo.Menu;

// DAO 상속
public class MenuServiceDAO implements MenuService {
	MenuDAO mdao = new MenuDAO();

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

	@Override
	public boolean menuCart(Menu menu) {
		return false;
	}

}
