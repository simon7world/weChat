package simon.zsh.world.wechat.receive.events;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import simon.zsh.world.wechat.basis.EventBase;

/**
 * 订阅事件
 */
public abstract class SubscribeEventMessage extends EventBase {

	public SubscribeEventMessage(final Map<String, String> vals)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		super(vals);
	}

	public static boolean verify(final String event) {

		return EventTypes.SUBSCRIBE.equalsIgnoreCase(event);
	}

}