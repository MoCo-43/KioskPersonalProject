package com.prj.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.prj.service.AdminService;
import com.prj.service.AdminServiceDAO;
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
	static AdminService asvc = new AdminServiceDAO();

	static boolean run = true; // 실행하기 위한 값
	static Scanner scanner = new Scanner(System.in);

	public void execute() {
		while (run) {
			showMainMenu(); // 메인화면 출력
			int selectNo = 0;
			try {
				selectNo = scanner.nextInt();
				scanner.nextLine();

				switch (selectNo) {
				case 0: // 관리자 메뉴, hidden menu로 숨겨 둠
					showAdminMenu();
					break;

				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6: // 메뉴 선택(1 ~ 6번)
					selectMenu(selectNo);
					break;

				case 8: // 장바구니 메뉴
					customerCart();
					break;

				case 9: // 주문조회
					orderList();
					break;

				default: // 지정된 번호 이외를 입력하면 표시하는 문구
					System.out.println("번호를 다시 입력해 주세요.");
					continue;
				}
			} catch (InputMismatchException e) { // 숫자이외에 입력되면 표시하는 문구
				System.out.println("메뉴를 다시 선택해주세요.");
				scanner.nextLine(); // 잘못된 입력을 소비하여 버퍼를 비움
				System.out.println("숫자만 입력해 주세요.");
				continue;
			} catch (NumberFormatException e) {
				System.out.println("메뉴를 다시 선택해주세요.");
				continue;
			}
		} // end of while

		scanner.close();
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
					String orderStr = "     %s\t\t\t\t | %d￦ | %s\n";
					System.out.printf(orderStr, order.getOrderName(), order.getOrderPrice(), order.getOrderInfo());
				}
			}
			System.out.println("1. 주문하기");
			System.out.println("2. 뒤로가기");

			int selectCart = 0;
			selectCart = Integer.parseInt(scanner.nextLine());

			switch (selectCart) {
			case 1: // 장바구니 추가 후 구매
				try {
					if (cart.isEmpty()) {
						System.out.println("장바구니에 아무것도 없습니다. 메뉴를 선택해주세요");
						break;
					} else {
						System.out.println("구매완료");
						break;
					}
				} catch (NoSuchElementException e) {
					System.out.println("번호를 다시 입력해주세요");
				}
			case 2: // 메뉴화면으로 돌아가기
				break;
			}
			break;
		}
	} // end of customerCart()

	// 주문조회(검색)
	void orderList() {
		while (run) {
			List<Order> cart = osvc.getCart();
			if (cart.isEmpty()) {
				System.out.println("장바구니가 비어 있습니다.");
			} else {
				for (Order order : cart) {
					String orderStr = "%d   %s\t\t\t\t | %d￦ |\n";
					System.out.printf(orderStr, order.getOrderNo(), order.getOrderName(), order.getOrderPrice());
				}
			}
			try {
				System.out.println("\n1. Return Menu\t\t\t\t\t\t  | 메뉴 화면으로 돌아가기");
				int backMenuNum = 0;
				backMenuNum = Integer.parseInt(scanner.nextLine());
				if (backMenuNum == 1) {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("숫자를 다시 입력해주세요");
			}
		}
	} // end of orderList()

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
		}
	} // end of selectMenu()

	// 메인화면
	void showMainMenu() {
		System.out.println("Java Caffe에 오신것을 환영합니다.\n메뉴를 선택해주세요");
		System.out.println("\n[ MENU ]\n");
		menuList = msvc.menuList();
		for (Menu menu : menuList) {
			String menuStr = "%d   %s\t\t\t\t | %d" + " ￦" + " | %s\n";
			System.out.printf(menuStr, menu.getMenuNo(), menu.getMenuName(), menu.getMenuPrice(), menu.getMenuInfo());
		}
		System.out.println("\n[ ORDER MENU ]");
		System.out.println("8. Cart   \t\t\t\t\t\t | 장바구니 확인 및 주문취소");
		System.out.println("9. OrderList   \t\t\t\t\t\t | 주문조회");
	} // end of showMainScreen()

	// 관리자 메뉴
	void showAdminMenu() {
		Map<String, String> login;
		login = new HashMap<String, String>();
		login.put("admin", "kioskADMIN");

		boolean loginCheck = false;

		while (run) {
			System.out.println("[ LOGIN ]\n");
			System.out.println("아이디를 입력해주세요>> ");
			String id = scanner.nextLine();
			System.out.println("비밀번호를 입력해주세요>> ");
			String pw = scanner.nextLine();

			if (login.containsKey(id)) {
				// 키가 존재 => 비밀번호 체크
				if (login.get(id).equals(pw)) {
					loginCheck = true;
					break;
				}
				System.out.println("입력하신 비밀번호가 일치하지 않습니다.");
			} else {
				System.out.println("로그인 실패했습니다.");
			}
			break;
		}

		while (loginCheck) {
			System.out.println("[ Admin Menu ]\n");
			System.out.println("1. Order List\t\t\t\t\t\t   | 주문 내역 조회");
			System.out.println("2. Order Cancel\t\t\t\t\t\t   | 주문 내역 취소");
			System.out.println("3. Edit Menu\t\t\t\t\t\t   | 메뉴 수정\n\n");
			System.out.println("0. Return Menu\t\t\t\t\t\t  | 메뉴 화면으로 돌아가기");
//			System.out.println("3. Order Summary\t\t\t\t\t\t   | 주문 집계");

			int adminMenu = Integer.parseInt(scanner.nextLine());
			switch (adminMenu) {
			case 0:
				break;
			case 1:
				// admin 주문내역 조회
				while (loginCheck) {
					List<Order> cart = osvc.getCart();
					if (cart.isEmpty()) {
						System.out.println("장바구니가 비어 있습니다.");
					} else {
						for (Order order : cart) {
							String orderStr = "%d   %s\t\t\t\t | %d￦ |\n";
							System.out.printf(orderStr, order.getOrderNo(), order.getOrderName(),
									order.getOrderPrice());
						}
					}
					System.out.println("\n1. Return Menu\t\t\t\t\t\t  | Admin Menu로 돌아가기");
					int backMenuNum = 0;
					try {
						backMenuNum = Integer.parseInt(scanner.nextLine());
						if (backMenuNum == 1) {
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("번호를 다시 입력해주세요");
					}
				}
				break;
			case 2:
				// 사용자 주문 취소
				while (run) {
					System.out.println("[ !! ORDER CANCEL !! ]");
					List<Order> cart = osvc.getCart();
					if (cart.isEmpty()) {
						System.out.println("\n주문목록이 없습니다.");
					} else {
						for (Order order : cart) {
							String orderStr = "%d   %s\t\t\t\t | %d￦ |\n";
							System.out.printf(orderStr, order.getOrderNo(), order.getOrderName(),
									order.getOrderPrice());
						}
					}
					System.out.println("위 메뉴를 주문취소 하시겠습니까?");
					System.out.println("1. 취소하기");
					System.out.println("2. 메인메뉴로 돌아가기");

					try {
						int ocNum = Integer.parseInt(scanner.nextLine());

						switch (ocNum) {
						case 1:
							Order rmOrder = new Order();
							rmOrder.setOrderNo(rmOrder.getOrderNo());
							rmOrder.setOrderName(rmOrder.getOrderName());
							rmOrder.setOrderPrice(rmOrder.getOrderPrice());

							osvc.removeCart(rmOrder);
							System.out.println("\n주문 취소 완료");
							break;
						case 2:
							break;
						default:
							System.out.println("올바른 숫자를 입력해주세요");
						}
					} catch (NumberFormatException e) {
						System.out.println("올바른 숫자를 입력해주세요.");
						continue;
					}
					break;

				}

				break;
			case 3:
				// 메뉴 수정
				while (loginCheck) {
					System.out.println("[ CURRENT SHOWING MENU ]");
					menuList = msvc.menuList();
					for (Menu menu : menuList) {
						String menuStr = "%d   %s\t\t\t\t | %d" + " ￦" + " | %s\n";
						System.out.printf(menuStr, menu.getMenuNo(), menu.getMenuName(), menu.getMenuPrice(),
								menu.getMenuInfo());
					}
					System.out.println("\n[ EDIT MENU ]");
					System.out.println("1. 메뉴 추가");
					System.out.println("2. 메뉴 수정");
					System.out.println("3. 메뉴 삭제");
					System.out.println("3. 돌아가기");

					try {
						int edit = Integer.parseInt(scanner.nextLine());
						switch (edit) {
						case 1:
							System.out.print("추가할 메뉴 번호>> ");
							int addMenuNo = scanner.nextInt();
							scanner.nextLine();

							System.out.print("추가할 메뉴 이름>> ");
							String addMenuName = scanner.nextLine();

							System.out.print("추가할 메뉴 가격>> ");
							int addMenuPrice = scanner.nextInt();
							scanner.nextLine();

							System.out.print("추가할 메뉴 설명>> ");
							String addMenuInfo = scanner.nextLine();

							Menu addMenu = new Menu();
							addMenu.setMenuNo(addMenuNo);
							addMenu.setMenuName(addMenuName);
							addMenu.setMenuPrice(addMenuPrice);
							addMenu.setMenuInfo(addMenuInfo);

							boolean addSuccess = msvc.addMenu(addMenu);
							if (addSuccess) {
								System.out.println("메뉴 수정 완료");
							} else {
								System.out.println("메뉴 수정에 실패했습니다. 번호를 다시 확인해주세요");
							}
							break;
						case 2:
							System.out.print("수정할 메뉴 번호>> ");
							int updateMenuNo = scanner.nextInt();
							scanner.nextLine();

							System.out.print("새 메뉴 이름>> ");
							String updateMenuName = scanner.nextLine();

							System.out.print("새 메뉴 가격>> ");
							int updateMenuPrice = scanner.nextInt();
							scanner.nextLine();

							System.out.print("새 메뉴 설명>> ");
							String updateMenuInfo = scanner.nextLine();

							Menu updateMenu = new Menu();
							updateMenu.setMenuNo(updateMenuNo);
							updateMenu.setMenuName(updateMenuName);
							updateMenu.setMenuPrice(updateMenuPrice);
							updateMenu.setMenuInfo(updateMenuInfo);

							boolean updateSuccess = msvc.modifyMenu(updateMenu);
							if (updateSuccess) {
								System.out.println("메뉴 수정 완료");
							} else {
								System.out.println("메뉴 수정에 실패했습니다. 번호를 다시 확인해주세요");
							}
							break;
						case 3:
							System.out.print("삭제할 메뉴 번호>> ");
							int menuNo = scanner.nextInt();
							scanner.nextLine();
							boolean success = msvc.removeMenu(menuNo);
							if (success) {
								System.out.println("메뉴 수정 완료");
							} else {
								System.out.println("메뉴 수정에 실패했습니다. 번호를 다시 확인해주세요");
							}
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("올바르게 입력해주세요");
					}
				}

				break;

//				// admin 주문 집계
//				Map<String, Integer> stats = asvc.getOrderSummary();
//				for (Map.Entry<String, Integer> entry : stats.entrySet()) {
//					String name = entry.getKey();
//					int count = entry.getValue();
//					System.out.println(name + ": " + count + "개");
//				}
//				break;
			}
			break;
		}
	} // end of showAdminMenu()
}
