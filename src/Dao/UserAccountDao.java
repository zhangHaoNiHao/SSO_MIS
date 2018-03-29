package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Bean.User;
import Utils.jdbcUtil;

public class UserAccountDao {

	public User findUserByAccount(String account) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = jdbcUtil.getConnection();
		String sql = "select * from user where account = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		rs = pstmt.executeQuery();
		User user = null;
		if(rs.next())
		{
			int id = rs.getInt("id");
			String name = rs.getString("name");
			account = rs.getString("account");
			String password = rs.getString("passwd");
			user = new User(id,name,password,account);
		}
		if(pstmt!=null)
			pstmt.close();
		if(conn!=null)
			conn.close();
		return user;
	}

}
