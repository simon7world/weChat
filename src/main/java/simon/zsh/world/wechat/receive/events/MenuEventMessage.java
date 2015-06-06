package simon.zsh.world.wechat.receive.events;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import simon.zsh.world.wechat.basis.EventMessageBase;
import simon.zsh.world.wechat.basis.SendMessageBase;

/**
 * 菜单点击事件
 */
public abstract class MenuEventMessage extends EventMessageBase {

	public MenuEventMessage(final Map<String, String> vals)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		super(vals);
	}

	/**
	 * 菜单点击的key值
	 */
	private String eventKey;

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey.trim();
	}

	public static boolean verify(final String event) {

		return EventTypes.CLICK.equalsIgnoreCase(event);
	}

	@Override
	public final String aloha() {

		final SendMessageBase smb = this.find(eventKey);

		return smb == null ? null : smb.toXml().asXML();
	}
}