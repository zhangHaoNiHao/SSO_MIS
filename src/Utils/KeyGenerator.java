package Utils;

import java.util.UUID;

public class KeyGenerator {

	private KeyGenerator(){};
	//����32λ16���Ƶ��ַ�����ĸСд
	public static String generator()
	{
		return UUID.randomUUID().toString().replace("-","").toLowerCase();
	}
}
