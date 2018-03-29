 package service;

import Bean.User;
import Dao.UserAccountDao;

public class UserAccountService {

	UserAccountDao userAccountDao = new UserAccountDao();
	public User findUserByAccount(String account) throws Exception {
		if(account == null || account.equals(""))
		{
			return null;
		}
		return userAccountDao.findUserByAccount(account);
	}

}
