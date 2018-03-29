package Utils;

import java.util.HashMap;
import java.util.Map;

import Bean.User;

public class TokenUserData {

	private TokenUserData(){}
	private static Map<String,User> dataMap = new HashMap<String,User>();
	/**
	 * �洢���� �� user
	 * @param token
	 * @param user
	 */
	public static void addToken(String token,User user)
	{
		dataMap.put(token, user);
	}
	/**
	 * �������ƻ��User����
	 * @param token
	 * @return
	 */
	public static User validateToken(String token)
	{
		return dataMap.get(token);
	}
	/**
	 * �Ƴ�token
	 * @param token
	 */
	public static void removeToken(String token)
	{
		dataMap.remove(token);
	}
}
