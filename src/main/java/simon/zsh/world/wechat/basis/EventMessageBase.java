package simon.zsh.world.wechat.basis;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Function;

/**
 * 事件的基类
 */
public abstract class EventMessageBase extends ReceiveBase {

	protected static final Map<String, Function<EventMessageBase, SendMessageBase>> ADAPTERS = new HashMap<>(
			15);

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

	@Override
	protected final SendMessageBase find(final String key) {

		SendMessageBase msg = null;

		final Function<EventMessageBase, SendMessageBase> func = ADAPTERS
				.get(key);
		if (func != null) {

			msg = func.apply(this);
			if (msg == null) {

				msg = this.makeNothingMessage();
			}
		}

		return msg == null ? this.makeMismatchMessage() : msg;
	}

}