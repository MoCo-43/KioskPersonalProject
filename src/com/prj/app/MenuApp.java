package com.prj.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.prj.service.MenuService;
import com.prj.service.MenuServiceDAO;
import com.prj.service.OrderService;
import com.prj.service.OrderServiceDAO;
import com.prj.vo.Menu;
import com.prj.vo.Order;

public class MenuApp {

	Menu menu = new Menu();
	Order order = new Order();
	List<Menu> menuList = new ArrayList<>();
	List<Order> orderList = new ArrayList<>();

	static MenuService msvc = new MenuServiceDAO();
	static OrderService osvc = new OrderServiceDAO();
	static boolean run = true;

	public void execute() {

		while (run) {
			showMainScreen(); // 메인화면 출력
			int selectNo = 0;
			Scanner scanner = new Scanner(System.in);
			selectNo = scanner.nextInt(); scanner.nextLine();

			try {
				switch (selectNo) {
				case 0:
					showAdminMenu(); // 관리자 메뉴
					break;
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6: // 메뉴 선택
					// 1~6번을 선택시 선택한 메뉴를 장바구니로 넘긴다는 기능을 구현??
					selectMenu(selectNo);
					break;
				case 7: // 장바구니 메뉴
					customerCart(); // 장바구니 메뉴 따로 구현
					break;
				case 8: // 주문조회
					orderList();
					break;
				case 9: // 주문취소
					orderCancel();
					break;
				}
			} catch (IndexOutOfBoundsException | NumberFormatException e) {
				System.out.println("메뉴를 다시 선택해주세요.");
			}
		} // end of while

	} // end of execute();

	// 장바구니 메뉴 출력
	void customerCart() {
		while (run) {
			System.out.println("[ ORDER MENU ]");
			List<Order> cart = osvc.getCart();
			if (cart.isEmpty()) {
				System.out.println("장바구니가 비어 있습니다.");
			} else {
				for (Order order : cart) {
					String orderStr = "%d   %s\t\t\t\t | %d￦ | %s\n";
					System.out.printf(orderStr, order.getOrderNo(), order.getOrderName(), order.getOrderPrice(),
							order.getOrderInfo());
				}
			}
			System.out.println("1. 주문하기");
			System.out.println("2. 뒤로가기");

			int selectCart = 0;
			Scanner scnCart = new Scanner(System.in);
			selectCart = Integer.parseInt(scnCart.nextLine());

			switch (selectCart) {
			case 1: // 장바구니 추가 후 구매
				/*
				 * 장바구니에 없다면 + try catch -> 없는상태에서 추가하면 NoSuchElementException 에러 뜨므로 예외처리
				 */
				if (cart.isEmpty()) {
					System.out.println("장바구니에 아무것도 없습니다. 메뉴를 선택해주세요");
					break;
				} else {
					System.out.println("구매완료");
				}
			case 2: // 메뉴화면으로 돌아가기
				System.out.println("다시시도하세요");
			}
			scnCart.close();
		}
	} // end of customerCart()

	
	
	// 주문조회(검색)
	void orderList() {
		while (run) {
			System.out.println("[ SEARCH ORDER ]\n");
			System.out.println("주문번호를 입력해주세요>> ");
			int orderSearch = 0;
			Scanner orderScn = new Scanner(System.in);
			orderSearch = Integer.parseInt(orderScn.nextLine());

			List<Order> cart = osvc.getCart();
			if (cart.isEmpty()) {
				System.out.println("장바구니가 비어 있습니다.");
			} else if (orderSearch == order.getOrderNo()) {
				for (Order order : cart) {
					String orderStr = "%d   %s\t\t\t\t | %d￦ | %s\n";
					System.out.printf(orderStr, order.getOrderNo(), order.getOrderName(), order.getOrderPrice(),
							order.getOrderInfo());
				}
			}

			System.out.println("\n1. Return Menu\t\t\t\t\t\t  | 메뉴 화면으로 돌아가기");
			Scanner backMenuScn = new Scanner(System.in);
			int backMenuNum = 0;
			backMenuNum = Integer.parseInt(backMenuScn.nextLine());
			switch (backMenuNum) {
			case 1:
				orderScn.close();
				backMenuScn.close();
				break;
			}
		}
	} // end of orderList()

	// 주문 취소
	void orderCancel() {
		System.out.println("[ !! ORDER CANCEL !! ]");
		List<Order> cart = osvc.getCart();
		if (cart.isEmpty()) {
			System.out.println("\n주문목록이 없습니다.");
		} else {
			for (Order order : cart) {
				String orderStr = "%d   %s\t\t\t\t | %d￦ | %s\n";
				System.out.printf(orderStr, order.getOrderNo(), order.getOrderName(), order.getOrderPrice(),
						order.getOrderInfo());
			}
		}
		System.out.println("위 메뉴를 주문취소 하시겠습니까?");
		System.out.println("1. 취소하기");
		System.out.println("2. 메인메뉴로 돌아가기");

		int ocNum = 0;
		Scanner ocScn = new Scanner(System.in);
		ocNum = Integer.parseInt(ocScn.nextLine());
		switch (ocNum) {
		case 1:
			osvc.clearCart();
			System.out.println("\n주문 취소 완료");
			break;
		case 2:
			break;
		}
		ocScn.close();
		execute();
	} // end of orderCancel()

	// 메뉴선택
	void selectMenu(int selectNo) {
		while (run) {
			Menu selMenu = menuList.get(selectNo - 1); // DB에서 가져온 기존 메뉴
			// Menu → Order 변환
			Order newOrder = new Order();
			newOrder.setOrderNo(selMenu.getMenuNo()); // 메뉴 번호를 주문 번호로 활용 (필요 시 분리)
			newOrder.setOrderName(selMenu.getMenuName());
			newOrder.setOrderPrice(selMenu.getMenuPrice());

			osvc.addCart(newOrder); // 장바구니에 추가

			// 안내 메시지
			System.out.printf("[ ORDER CONFIRM ]\n장바구니에 추가 완료: %s\n", newOrder.getOrderName());

			break;
			// 메뉴가 강제종료 되니까, 다시 메인메뉴로 돌아가게 하려고
//	    if (selectNo > 1 && selectNo < 7) {
//	      execute();	
//	    } else {
//	    	
//	    }
		}
	} // end of selectMenu()

	// 메인화면
	void showMainScreen() {
		System.out.println("Java Caffe에 오신것을 환영합니다.\n메뉴를 선택해주세요");
		System.out.println("\n[ MENU ]\n");
		menuList = msvc.menuList();
		for (Menu menu : menuList) {
			String menuStr = "%d   %s\t\t\t\t | %d" + " ￦" + " | %s\n";
			System.out.printf(menuStr, menu.getMenuNo(), menu.getMenuName(), menu.getMenuPrice(), menu.getMenuInfo());
		}
		System.out.println("\n[ ORDER MENU ]");
		System.out.println("7. Cart   \t\t\t\t\t\t | 장바구니 확인 및 주문취소");
		System.out.println("8. OrderList   \t\t\t\t\t\t | 주문조회");
		System.out.println("9. Cancel  \t\t\t\t\t\t | 주문 모두 취소");
	} // end of showMainScreen()

	// 관리자 메뉴
	void showAdminMenu() {
		System.out.println("[ Admin Menu ]\n");
		System.out.println("1. Order List\t\t\t\t\t\t   | 주문 내역 조회");
		System.out.println("2. Order Cancel\t\t\t\t\t\t   | 주문 내역 취소");
		System.out.println("3. Order Cancel\t\t\t\t\t\t   | 주문 집계");
		System.out.println("4. Edit Menu\t\t\t\t\t\t   | 메뉴 수정\n\n");
		System.out.println("0. Return Menu\t\t\t\t\t\t  | 메뉴 화면으로 돌아가기");
	} // end of showAdminMenu()
}
