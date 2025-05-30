package com.prj.service;

import java.util.List;
import com.prj.vo.Menu;

public interface MenuService {
	
  // 메뉴 화면 출력 및 메뉴수정
  public boolean addMenu(Menu menu);  // 메뉴등록
  public boolean modifyMenu(Menu menu);  // 메뉴 변경
  public boolean removeMenu(int menuNo);  // 메뉴 삭제
  public List<Menu> menuList();  // 메뉴 출력
 
}
