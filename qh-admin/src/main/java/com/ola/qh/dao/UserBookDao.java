package com.ola.qh.dao;

import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserIntomoneyHistory;

public interface UserBookDao {

	public UserBook single(String userId);
	
	public int saveUserBook(UserBook ub);
	
	public int updateUserBook(UserBook userbook);
	
	public int saveUserIntomoneyHistory(UserIntomoneyHistory uis);
}
