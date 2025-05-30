package com.prj.service;

import java.util.ArrayList;
import java.util.List;

import com.prj.common.MenuDAO;
import com.prj.vo.Menu;

// DAO 상속
public class MenuServiceDAO implements MenuService {
	MenuDAO mdao = new MenuDAO();
	List<Menu> menu = new ArrayList<>();
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
	
}


