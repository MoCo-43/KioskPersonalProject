package com.prj.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.prj.vo.Menu;
import com.prj.service.MenuServiceDAO;
import com.prj.service.MenuService;

public class MenuApp {
	static Menu menu = new Menu();

	public void execute() {
		boolean run = true;
		Scanner scanner = new Scanner(System.in);
		List<Menu> menus = new ArrayList<>();
		MenuService msvc = new MenuServiceDAO();

		System.out.println("Java Caffe에 오신것을 환영합니다.\n메뉴를 선택해주세요");
		
		while (run) {
			// 첫화면 부터 메인페이지
			System.out.println("\n[ MENU ]\n");
			menus = msvc.menuList();
			for (Menu menu : menus) {
				String menuStr = "%d   %s\t\t\t\t | %d" + " ￦" + " | %s\n";
				System.out.printf(menuStr, menu.getMenuNo(), menu.getMenuName(), menu.getMenuPrice(),
						menu.getMenuInfo());
			}
			System.out.println("\n[ ORDER MENU ]");
			System.out.println("8. Order   \t\t\t\t\t\t | 장바구니 확인 및 주문취소");
			System.out.println("9. Cancel  \t\t\t\t\t\t | 주문 모두 취소");
			
			int selectNo = 0;
			selectNo = Integer.parseInt(scanner.nextLine());

			try {
				if (selectNo >= 1 && selectNo <= menus.size()) {  // 메뉴선택
					Menu selected = menus.get(selectNo - 1);  // 리스트에서 제일 처음 index는 0이므로 -1 
					msvc.addCart(selected);
					System.out.printf("[ ORDER CONFIRM ]\n장바구니에 추가 완료: %s", selected.getMenuName());
				} else if (selectNo == 8) {  // 장바구니 메뉴
					System.out.println("[ ORDER MENU ]");
					List<Menu> cart = msvc.getCart();
					if (cart.isEmpty()) {
						System.out.println("장바구니가 비어 있습니다.");
					} else {
						for (Menu menu : cart) {
							String menuStr = "%d   %s\t\t\t\t | %d￦ | %s\n";
							System.out.printf(menuStr, menu.getMenuNo(), menu.getMenuName(),
									menu.getMenuPrice(), menu.getMenuInfo());
						}
					}
				} else if (selectNo == 9) {
					System.out.println("Calcel");
				} else if (selectNo == 0) {		
					System.out.println("[ Admin Menu ]");
					System.out.println("\n\n TEST");
				}
			} catch (IndexOutOfBoundsException | NumberFormatException e) {
				System.out.println("메뉴를 다시 선택해주세요.");
			}
		} // end of while

	} // end of execute();

	
}
