package Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    /**
     * ���cookie
     * @param name cookie��key
     * @param value cookie��value
     * @param domain domain
     * ��param  path path 
     * @param maxage  ����ʱ�� ��λΪ��
     * @param response
     */
    public static void addCookie(String name ,String value,String domain,
            int maxage,String path, HttpServletResponse response){
        Cookie cookie = new Cookie(name,value);
        if(domain!=null){
            cookie.setDomain(domain);
        }
        cookie.setMaxAge(maxage);
        cookie.setPath(path);
        response.addCookie(cookie);
    }
     
    /**
     * ���������һ��cookie
     * * @param name cookie��key
     * @param value cookie��value
     * @param domain domain
     * @param maxage  ����ʱ�� ��λΪ��
     * @param response
     */
    public static void addCookie(String name ,String value,String domain,
            int maxage, HttpServletResponse response){
        addCookie(name, value,domain, maxage, "/" , response);
    }
     
    /**
     * ��cookieֵ����cookieֵ�����û�з��� null
     * @param req
     * @param name
     * @return cookie��ֵ
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(name)) {
                return cookies[i].getValue();
            }
        }
        return null;
    }
 
    public static void removeCookie(String name, String domain, HttpServletRequest request, HttpServletResponse response) {
        String cookieVal = getCookie(request,name);
        if(cookieVal!=null){
            CookieUtil.addCookie(name, null, domain, 0, response);
        }
    }
 
    public static void removeCookie(String name, HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.removeCookie(name, ".dhgate.com", request, response);
    }
    public static void removeCookie(HttpServletResponse response, String name, String path, String domain) {
		Cookie cookie = new Cookie(name,null);
		if(path != null)
		{
			cookie.setPath(path);
		}
		if(domain != null)
		{
			cookie.setDomain(domain);
		}
		cookie.setMaxAge(-1000);
		response.addCookie(cookie);
	}
}