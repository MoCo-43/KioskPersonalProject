package com.prj.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.prj.vo.Menu;
import com.prj.service.MenuServiceDAO;
import com.prj.service.MenuService;

public class MenuApp {
  static Menu menu = new Menu();
  
  public void excute() {
	boolean run = true;
	Scanner scanner = new Scanner(System.in);
	List<Menu> menus = new ArrayList<>();
	MenuService msvc = new MenuServiceDAO();
	

	while(run) {
	  System.out.println("Java Caffe에 오신것을 환영합니다.\n");
	  System.out.println("메뉴를 선택해주세요");
      int selectNo = 0;
	  selectNo = Integer.parseInt(scanner.nextLine());
	  System.out.println("[ MENU ]\n");
	  System.out.println("");
		switch(selectNo) {
		case 1:
			List<Menu> list =  msvc.menuList();
			for(Menu menu : list) {
			  String menuStr = "%d   %s\t\t\t|  %d" + " ￦" + "  | %s";
			  System.out.printf(menuStr,menu.getMenuNo(), menu.getMenuName(), menu.getMenuPrice(), menu.getMenuInfo());
			}
			break;
		}
	}
		
  }

}
