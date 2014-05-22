package simon.zsh.world.wechat.basis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import simon.zsh.world.wechat.send.TextSendMessage;

/**
 * 接收的基类
 */
public abstract class ReceiveBase extends MessageBase implements IMakeMessage,
		IHawaii {

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

	@Override
	public final <T extends SendMessageBase> T makeSendMessage(
			final Class<T> clazz) {

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

	@Override
	public final TextSendMessage makeNothingMessage() {

		final TextSendMessage tsm = makeSendMessage(TextSendMessage.class);
		tsm.setContent("您的指令太高深了~\n我无法理解！");

		return tsm;
	}

	protected abstract SendMessageBase find(final String key);

}