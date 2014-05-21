package simon.zsh.world.wechat.receive.events;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.sax.SAXSource;

import simon.zsh.world.wechat.basis.EventMessageBase;
import simon.zsh.world.wechat.basis.SendMessageBase;

import com.google.common.base.Function;

/**
 * 订阅事件
 */
public abstract class SubscribeEventMessage extends EventMessageBase {

	public SubscribeEventMessage(final Map<String, String> vals)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		super(vals);
	}

	public static boolean verify(final String event) {

		return EventTypes.SUBSCRIBE.equalsIgnoreCase(event);
	}

	@Override
	public final SAXSource aloha(final HttpServletRequest req) {

		final Function<EventMessageBase, SendMessageBase> func = ADAPTERS
				.get(getEvent().toLowerCase());

		return func == null ? null : func.apply(this).toSource();
	}

}