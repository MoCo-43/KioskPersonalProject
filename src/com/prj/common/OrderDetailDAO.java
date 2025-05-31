package com.prj.common;

import java.sql.SQLException;

import com.prj.vo.OrderDetail;

public class OrderDetailDAO extends DAO {

	// 삽입 insert();
	public int insert(int orderNo, OrderDetail detail) {
	  String sql = "INSERT INTO tbl_order_detail (order_no, menu_no, menu_price, quantity, customer_option) "
			     + "VALUES (?, ?, ?, ?, ?)";

	    getConnect();
	  try{
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, orderNo); // 정수 값 그대로 사용
		psmt.setInt(2, detail.getMenuNo());
		psmt.setInt(3, detail.getMenuPrice());
		psmt.setInt(4, detail.getQuantity());
		psmt.setString(5, detail.getCustomerOption());

		int r = psmt.executeUpdate();
		return r;
	} catch (SQLException e) {
		e.printStackTrace();
	  } finally {
		  disconnect();
	  }return 0;

    } // end of insert

	public boolean deleteByOrderNo(int orderNo) {
	    String sql = "DELETE FROM tbl_order_detail WHERE order_no = ?";
	    getConnect();
	    try {
	        psmt = conn.prepareStatement(sql);
	        psmt.setInt(1, orderNo);
	        int rows = psmt.executeUpdate();
	        return rows >= 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        disconnect();
	    }
	    return false;
	}
	
}
