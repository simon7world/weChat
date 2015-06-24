package simon.zsh.world.wechat.utils;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public abstract class CommonUtil {

	public static boolean isEmpty(Collection<?> collection) {

		return (collection == null || collection.isEmpty());
	}

	public static boolean isEmpty(Map<?, ?> map) {

		return (map == null || map.isEmpty());
	}

	public static boolean isEmpty(Object str) {

		return str == null || "".equals(str);
	}

	public static String padLeft(final String str, int length,
			final String padding) {

		length = Math.abs(length);
		final StringBuilder sb = new StringBuilder(str);
		final int diff = length - str.length();
		if (length != 0 && diff > 0) {

			for (int i = 0; i < diff; i++) {

				sb.insert(0, padding);
			}
		}

		return sb.toString();
	}

	public static String collectionToDelimitedString(Collection<?> coll,
			String delim) {

		if (isEmpty(coll)) {

			return "";
		}

		final StringBuilder sb = new StringBuilder();
		final Iterator<?> it = coll.iterator();
		while (it.hasNext()) {

			sb.append(it.next());
			if (it.hasNext()) {

				sb.append(delim);
			}
		}

		return sb.toString();
	}

	public static Date expiresDate(final double expiresIn) {

		final Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, (int) expiresIn);

		return c.getTime();
	}
}