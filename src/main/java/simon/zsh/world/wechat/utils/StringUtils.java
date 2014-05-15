package simon.zsh.world.wechat.utils;

import java.util.Collection;
import java.util.Iterator;

public abstract class StringUtils {

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

		if (CollectionUtils.isEmpty(coll)) {

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

}