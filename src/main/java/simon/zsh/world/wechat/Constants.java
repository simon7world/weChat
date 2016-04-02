package simon.zsh.world.wechat;

import java.math.BigInteger;
import java.security.SecureRandom;

public abstract class Constants {

	public static boolean ENTERPRISE = false;
	public static String APP_ID = "wxc5682f9042753a9f";
	public static String APP_SECRET = "90c2e089dc0200dfd08f0b95968382dd";
	public static String TOKEN = "YHSRzibY1YUvQTvriwhe6IQ";
	public static String AES_KEY = "q79jD4DVP0FtqqAXI7R2RY7el6FdAm7pfjaBo4Ccq2U";

	private static final String CGI_BIN = "https://api.weixin.qq.com/cgi-bin/";
	private static final String ACCESS_TOKEN_URL = CGI_BIN + "token?grant_type=client_credential&appid=%s&secret=%s";
	private static final String JSAPI_TICKET_URL = CGI_BIN + "ticket/getticket?access_token=%s&type=jsapi";

	public static final String MENU_URL = CGI_BIN + "menu/%s?access_token=%s";

	private static final String ENTERPRISE_CGI_BIN = "https://qyapi.weixin.qq.com/cgi-bin/";
	private static final String ENTERPRISE_ACCESS_TOKEN_URL = ENTERPRISE_CGI_BIN + "gettoken?corpid=%s&corpsecret=%s";
	private static final String ENTERPRISE_JSAPI_TICKET_URL = ENTERPRISE_CGI_BIN + "get_jsapi_ticket?access_token=%s";

	public static String ACCESS_TOKEN_URL(final String id, final String secret) {

		return String.format(ENTERPRISE ? ENTERPRISE_ACCESS_TOKEN_URL : ACCESS_TOKEN_URL, id, secret);
	}

	public static String JSAPI_TICKET_URL(final String token) {

		return String.format(ENTERPRISE ? ENTERPRISE_JSAPI_TICKET_URL : JSAPI_TICKET_URL, token);
	}

	public static String RANDOM_STRING() {

		return new BigInteger(70, new SecureRandom()).toString(Character.MAX_RADIX);
	}
}