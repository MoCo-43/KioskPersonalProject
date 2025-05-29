package com.prj.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prj.vo.Menu;

public class OrderDAO extends DAO {
	
	// 삽입 insert();
	public int insert(Menu menu) {
      String sql = "insert into tbl_menu (menu_no, menu_name, menu_price, menu_info)"
      		+ "  values(?,?,?,?,?)";
      		//접속
      		getConnect();
      		try {
      			psmt = conn.prepareStatement(sql);
      			psmt.setInt(1, menu.getMenuNo());
      			psmt.setString(2, menu.getMenuName());
      			psmt.setInt(3, menu.getMenuPrice());
      			psmt.setString(4, menu.getMenuInfo());
      			int r = psmt.executeUpdate();
      			return r;  // 건수 반환
      		} catch (SQLException e) {
      			e.printStackTrace();
      		} finally {
      		  disconnect();
      		}
      		return 0;  // 반영 X
	} // end of insert
	
	// 수정 update();
	public int update(Menu menu) {
	      String sql = "update tbl_menu"
	    		    + " set    menu_name = ?"
	        		+ "      , menu_price = ?"
	    		    + "      , menu_info = ?"
	        		+ " where menu_no = ?";
	        		//접속
	        		getConnect();
	        		try {
	        			psmt = conn.prepareStatement(sql);
	          			psmt.setString(1, menu.getMenuName());
	          			psmt.setInt(2, menu.getMenuPrice());
	          			psmt.setString(3, menu.getMenuInfo());
	          			psmt.setInt(4, menu.getMenuNo());
	        			int r = psmt.executeUpdate();
	        			return r;  // 건수 반환
	        		} catch (SQLException e) {
	        			e.printStackTrace();
	        		} finally {
	        		  disconnect();
	        		}
	        		return 0;  // 반영 X
	}  // end of update
	
	// 삭제 delete();
	public int delete(int menuNo) {
	      String sql = "delete from tbl_menu"
	      		+ "  where menu_no = ?";
	        		//접속
	        		getConnect();
	        		try {
	        			psmt = conn.prepareStatement(sql);
	        			psmt.setInt(1, menuNo);
	        			int r = psmt.executeUpdate();
	        			return r;  // 건수 반환
	        		} catch (SQLException e) {
	        			e.printStackTrace();
	        		} finally {
	        		  disconnect();
	        		}
	        		return 0;  // 반영 X	
	}  // end of delete
	
	// 목록 select();
	public List<Menu> select() {
	  String sql = "select * from tbl_menu";
	  getConnect();
	  List<Menu> menuList = new ArrayList<>();  // 컬렉션에 저장
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();  // 조회
			while(rs.next()) {
			  Menu selMenu = new Menu();
			  selMenu.setMenuNo(rs.getInt("menu_no"));
			  selMenu.setMenuName(rs.getString("menu_name"));
			  selMenu.setMenuPrice(rs.getInt("menu_price"));
			  selMenu.setMenuInfo(rs.getString("menu_info"));
			  
			  // 추가
			  menuList.add(selMenu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	} // end of select
	
	
}