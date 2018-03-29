package Utils;

import java.util.HashMap;
import java.util.Map;

import Bean.User;

public class TokenUserData {

	private TokenUserData(){}
	private static Map<String,User> dataMap = new HashMap<String,User>();
	/**
	 * 存储令牌 和 user
	 * @param token
	 * @param user
	 */
	public static void addToken(String token,User user)
	{
		dataMap.put(token, user);
	}
	/**
	 * 根据令牌获得User对象
	 * @param token
	 * @return
	 */
	public static User validateToken(String token)
	{
		return dataMap.get(token);
	}
	/**
	 * 移除token
	 * @param token
	 */
	public static void removeToken(String token)
	{
		dataMap.remove(token);
	}
}
