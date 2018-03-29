package Utils;

import java.util.UUID;

public class KeyGenerator {

	private KeyGenerator(){};
	//生成32位16进制的字符，字母小写
	public static String generator()
	{
		return UUID.randomUUID().toString().replace("-","").toLowerCase();
	}
}
