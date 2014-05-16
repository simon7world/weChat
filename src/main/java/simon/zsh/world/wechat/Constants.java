package simon.zsh.world.wechat;

public abstract class Constants {

	public static String TOKEN = null;
	public static String APP_ID = null;
	public static String APP_SECRET = null;
	public static String ACCESS_TOKEN = null;
	public static Integer UEXPIRES_IN = null;
	public static final String CGI_BIN = "https://api.weixin.qq.com/cgi-bin/";
	public static final String MENU_URL = CGI_BIN + "menu/%s?access_token=%s";
	public static final String TOKEN_URL = Constants.CGI_BIN
			+ "token?grant_type=client_credential&appid=%s&secret=%s";

}