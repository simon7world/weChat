package simon.zsh.world.wechat.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import simon.zsh.world.wechat.Constants;

import com.google.gson.Gson;

public abstract class TicketUtil {

	private static String JSAPI_TICKET = null;
	private static Date JSAPI_TICKET_EXPIRED = null;

	private static String getJSAPI_TICKET() {

		if (expired()) {

			getJsapiTicket();
		}

		return JSAPI_TICKET;
	}

	public synchronized static boolean expired() {

		return JSAPI_TICKET == null || JSAPI_TICKET_EXPIRED.before(new Date());
	}

	public synchronized static String[] getJSAPI_SIGNATURE(final String url) {

		final String timestamp = "" + System.currentTimeMillis() / 1000;
		final String noncestr = Constants.RANDOM_STRING();

		final String[] arr = { "timestamp=" + timestamp,
				"noncestr=" + noncestr, "url=" + url,
				"jsapi_ticket=" + getJSAPI_TICKET() };
		Arrays.sort(arr);

		return new String[] {
				timestamp,
				noncestr,
				DigestUtils.sha1Hex(CommonUtil.collectionToDelimitedString(
						Arrays.asList(arr), "&")) };
	}

	/**
	 * 获取JS接口的临时票据
	 */
	private static void getJsapiTicket() {

		final String ret = new HttpsUtil().httpsRequest(
				Constants.JSAPI_TICKET_URL(TokenUtil.getACCESS_TOKEN()), "GET",
				null);
		if (ret != null) {

			try {

				@SuppressWarnings("unchecked")
				final Map<String, Object> vals = new Gson().fromJson(ret,
						Map.class);
				JSAPI_TICKET = (String) vals.get("ticket");
				JSAPI_TICKET_EXPIRED = CommonUtil.expiresDate((double) vals
						.get("expires_in"));
			} catch (final Exception e) {

				e.printStackTrace();
			}
		}
	}
}