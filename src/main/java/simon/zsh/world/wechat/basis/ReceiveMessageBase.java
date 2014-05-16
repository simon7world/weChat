package simon.zsh.world.wechat.basis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.sax.SAXSource;

import com.google.common.base.Function;

public abstract class ReceiveMessageBase extends MessageBase {

	protected static final Map<String, Function<ReceiveMessageBase, SendMessageBase>> TEXT_ADAPTERS = new HashMap<>();

	public ReceiveMessageBase(final Map<String, String> vals)
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

	/**
	 * 消息ID
	 */
	private Long msgId;

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(final String msgId) {
		this.msgId = Long.parseLong(msgId);
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