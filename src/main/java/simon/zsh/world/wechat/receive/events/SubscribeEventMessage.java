package simon.zsh.world.wechat.receive.events;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import simon.zsh.world.wechat.basis.EventMessageBase;
import simon.zsh.world.wechat.basis.SendMessageBase;

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
	public final String aloha() {

		final SendMessageBase smb = this.find(getEvent().toLowerCase());

		return smb == null ? null : smb.toXml().asXML();
	}

}