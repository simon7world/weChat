package simon.zsh.world.wechat.utils.menu;

import java.util.Map;

import simon.zsh.world.wechat.Constants;
import simon.zsh.world.wechat.menu.Menu;

import com.google.gson.Gson;

/**
 * 自定义菜单工具类
 */
public abstract class MenuUtil {

	private final static String CREATE_URL;
	private final static String GET_URL;
	private final static String DELETE_URL;

	private final static Gson GSON = new Gson();

	static {

		if (Constants.ACCESS_TOKEN == null) {

			TokenUtil.getAccessToken();
		}

		CREATE_URL = String.format(Constants.MENU_URL, "create",
				Constants.ACCESS_TOKEN);
		GET_URL = String.format(Constants.MENU_URL, "get",
				Constants.ACCESS_TOKEN);
		DELETE_URL = String.format(Constants.MENU_URL, "delete",
				Constants.ACCESS_TOKEN);
	}

	/**
	 * 创建菜单
	 */
	public static boolean createMenu(final Menu menu) {

		boolean result = false;

		final Map<String, Object> map = getResult(CREATE_URL, "POST",
				GSON.toJson(menu));
		if (map != null) {

			result = (double) map.get("errcode") == 0;
		}

		return result;
	}

	/**
	 * 查询菜单
	 */
	public static Map<String, Object> getMenu() {

		return getResult(GET_URL, "GET", null);
	}

	/**
	 * 删除菜单
	 */
	public static boolean deleteMenu() {

		boolean result = false;

		final Map<String, Object> map = getResult(DELETE_URL, "GET", null);
		if (map != null) {

			result = (double) map.get("errcode") == 0;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> getResult(final String url,
			final String method, final String source) {

		final String ret = TokenUtil.httpsRequest(url, method, source);
		if (null != ret) {

			return GSON.fromJson(ret, Map.class);
		}

		return null;
	}

}