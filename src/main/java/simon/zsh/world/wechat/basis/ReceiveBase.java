package simon.zsh.world.wechat.basis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.sax.SAXSource;

/**
 * 接收的基类
 */
public abstract class ReceiveBase extends MessageBase {

	public ReceiveBase(final Map<String, String> vals)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		final Method[] ms = this.getClass().getMethods();
		for (final Method m : ms) {

			final String name = m.getName();
			if (name.startsWith("set")) {

				m.invoke(this, vals.get(name.replaceFirst("set", "")));
			}
		}
	}

	public <T extends SendMessageBase> T makeSendMessage(final Class<T> clazz) {

		T t = null;
		try {

			t = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
		}

		if (t != null) {

			final SendMessageBase mrb = (SendMessageBase) t;
			mrb.setToUserName(this.getFromUserName());
			mrb.setFromUserName(this.getToUserName());
		}

		return t;
	}

	public abstract SAXSource aloha(final HttpServletRequest req);

}