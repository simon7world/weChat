package simon.zsh.world.wechat.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import simon.zsh.world.wechat.Constants;

import com.google.gson.Gson;

public abstract class TicketUtil {

	private static String JSAPI_TICKET = null;
	private static Date JSAPI_TICKET_EXPIRED = null;

	public synchronized static String getJSAPI_TICKET() {

		if (JSAPI_TICKET == null || JSAPI_TICKET_EXPIRED.before(new Date())) {

			getJsapiTicket();
		}

		return JSAPI_TICKET;
	}

	/**
	 * 获取JS接口的临时票据
	 */
	private static void getJsapiTicket() {

		final String ret = new HttpsUtil().httpsRequest(
				String.format(Constants.JSAPI_TICKET_URL,
						TokenUtil.getACCESS_TOKEN()), "GET", null);
		if (ret != null) {

			try {

				@SuppressWarnings("unchecked")
				final Map<String, Object> vals = new Gson().fromJson(ret,
						Map.class);
				JSAPI_TICKET = (String) vals.get("ticket");
				final Calendar c = Calendar.getInstance();
				c.add(Calendar.SECOND,
						(int) ((double) vals.get("expires_in") * 0.9));
				JSAPI_TICKET_EXPIRED = c.getTime();
			} catch (final Exception e) {
			}
		}
	}
}