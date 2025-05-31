package com.prj.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.vo.Order;

public class OrderDAO extends DAO {
	
	// 삽입 insert();
	public int insert(Order order) {
	  String sql = "INSERT INTO tbl_order (order_no, order_name, order_price, order_info) VALUES (?, ?, ?, ?)";
      		//접속
      		getConnect();
      		try {
      			psmt = conn.prepareStatement(sql);
      			psmt.setInt(1, order.getOrderNo());
      			psmt.setString(2, order.getOrderName());
      			psmt.setInt(3, order.getOrderPrice());
      			psmt.setString(4, order.getOrderInfo());
      			
      			int r = psmt.executeUpdate();
      			return r;
      		} catch (SQLException e) {
      			e.printStackTrace();
      		} finally {
      		  disconnect();
      		}
      		return 0;  // 반영 X
	} // end of insert
	
	// 수정 update();
	public int update(Order order) {
	      String sql = "update tbl_order"
	    		    + " set    order_name = ?"
	        		+ "      , order_price = ?"
	    		    + "      , order_info = ?"
	        		+ " where order_no = ?";
	        		//접속
	        		getConnect();
	        		try {
	        			psmt = conn.prepareStatement(sql);
	          			psmt.setString(1, order.getOrderName());
	          			psmt.setInt(2, order.getOrderPrice());
	          			psmt.setString(3, order.getOrderInfo());
	          			psmt.setInt(4, order.getOrderNo());
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
	public int delete(int orderNo) {
	      String sql = "delete from tbl_order"
	      		+ "  where order_no = ?";
	        		//접속
	        		getConnect();
	        		try {
	        			psmt = conn.prepareStatement(sql);
	        			psmt.setInt(1, orderNo);
	        			int r = psmt.executeUpdate();
	        			return r;  // 건수 반환
	        		} catch (SQLException e) {
	        			e.printStackTrace();
	        		} finally {
	        		  disconnect();
	        		}
	        		return 0;  // 반영 X	
	}  // end of delete
	
	// 목록조회 select();
	public List<Order> select() {
	  String sql = "select * from tbl_order";
	  getConnect();
	  List<Order> orderList = new ArrayList<>();  // 컬렉션에 저장
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();  // 조회
			while(rs.next()) {
			  Order selOrder = new Order();
			  selOrder.setOrderNo(rs.getInt("order_no"));
			  selOrder.setOrderName(rs.getString("order_name"));
			  selOrder.setOrderPrice(rs.getInt("order_price"));
			  selOrder.setOrderInfo(rs.getString("order_info"));
			  
			  // 추가
			  orderList.add(selOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	} // end of select
	
    
	
	// 주문조회 orderListSelect()
	public List<Order> orderListSelect() {
		 String sql = "select * from tbl_order";
		  getConnect();
		  List<Order> orderList = new ArrayList<>();  // 컬렉션에 저장
			try {
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();  // 조회
				while(rs.next()) {
				  Order selOrder = new Order();
				  selOrder.setOrderNo(rs.getInt("order_no"));
				  selOrder.setOrderName(rs.getString("order_name"));
				  selOrder.setOrderPrice(rs.getInt("order_price"));
				  selOrder.setOrderInfo(rs.getString("order_info"));
//				  selOrder.setOrderNo(rs.getInt("order_no"));
				  // 추가
				  orderList.add(selOrder);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return orderList;
	}
	
	// 주문내역집계
	public Map<String, Integer> getOrderSummary() {
	    Map<String, Integer> result = new HashMap<>();
	    String sql = "select order_name, count(*) as total from tbl_order group by order_name";
	    getConnect();
	    try {
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(sql);
	        while (rs.next()) {
	            result.put(rs.getString("order_name"), rs.getInt("total"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
}