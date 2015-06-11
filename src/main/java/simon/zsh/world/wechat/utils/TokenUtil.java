package simon.zsh.world.wechat.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import simon.zsh.world.wechat.Constants;

import com.google.gson.Gson;

public abstract class TokenUtil {

	private static String ACCESS_TOKEN = null;
	private static Date ACCESS_TOKEN_EXPIRED = null;

	public synchronized static String getACCESS_TOKEN() {

		if (ACCESS_TOKEN == null || ACCESS_TOKEN_EXPIRED.before(new Date())) {

			getAccessToken();
		}

		return ACCESS_TOKEN;
	}

	/**
	 * 获取接口访问凭证
	 */
	private static void getAccessToken() {

		final String ret = new HttpsUtil().httpsRequest(String.format(
				Constants.ACCESS_TOKEN_URL, Constants.APP_ID,
				Constants.APP_SECRET), "GET", null);
		if (ret != null) {

			try {

				@SuppressWarnings("unchecked")
				final Map<String, Object> vals = new Gson().fromJson(ret,
						Map.class);
				ACCESS_TOKEN = (String) vals.get("access_token");
				final Calendar c = Calendar.getInstance();
				c.add(Calendar.SECOND,
						(int) ((double) vals.get("expires_in") * 0.9));
				ACCESS_TOKEN_EXPIRED = c.getTime();
			} catch (final Exception e) {
				
				e.printStackTrace();
			}
		}
	}
}