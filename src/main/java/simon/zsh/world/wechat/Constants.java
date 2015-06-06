package simon.zsh.world.wechat;

public abstract class Constants {

	public static String APP_ID = "wxc5682f9042753a9f";
	public static String APP_SECRET = "90c2e089dc0200dfd08f0b95968382dd";

	public static String TOKEN = "YHSRzibY1YUvQTvriwhe6IQ";
	public static String AES_KEY = "q79jD4DVP0FtqqAXI7R2RY7el6FdAm7pfjaBo4Ccq2U";

	public static String ACCESS_TOKEN = null;
	public static Double UEXPIRES_IN = null;

	public static final String CGI_BIN = "https://api.weixin.qq.com/cgi-bin/";
	public static final String MENU_URL = CGI_BIN + "menu/%s?access_token=%s";
	public static final String TOKEN_URL = Constants.CGI_BIN
			+ "token?grant_type=client_credential&appid=%s&secret=%s";
}