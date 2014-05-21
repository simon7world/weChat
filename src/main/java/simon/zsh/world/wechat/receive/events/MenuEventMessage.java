package simon.zsh.world.wechat.receive.events;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.sax.SAXSource;

import simon.zsh.world.wechat.basis.EventMessageBase;
import simon.zsh.world.wechat.basis.SendMessageBase;

import com.google.common.base.Function;

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
		this.eventKey = eventKey;
	}

	public static boolean verify(final String event) {

		return EventTypes.CLICK.equalsIgnoreCase(event);
	}

	@Override
	public final SAXSource aloha(final HttpServletRequest req) {

		final Function<EventMessageBase, SendMessageBase> func = ADAPTERS
				.get(eventKey);

		return func == null ? null : func.apply(this).toSource();
	}

}