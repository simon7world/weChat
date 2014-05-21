package simon.zsh.world.wechat.basis;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Function;

/**
 * 事件的基类
 */
public abstract class EventMessageBase extends ReceiveBase {

	protected static final Map<String, Function<EventMessageBase, SendMessageBase>> ADAPTERS = new HashMap<>();

	public EventMessageBase(final Map<String, String> vals)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		super(vals);
	}

	/**
	 * 事件类型
	 */
	private String event;

	public String getEvent() {
		return event;
	}

	public void setEvent(final String event) {
		this.event = event;
	}

}