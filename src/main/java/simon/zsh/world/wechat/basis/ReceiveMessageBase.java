package simon.zsh.world.wechat.basis;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.google.common.base.Function;

/**
 * 接收消息的基类
 */
public abstract class ReceiveMessageBase extends ReceiveBase {

	protected static final Map<String, Function<ReceiveMessageBase, SendMessageBase>> ADAPTERS = new HashMap<>(12);

	public ReceiveMessageBase(final Map<String, String> vals)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		super(vals);
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

	@Override
	protected final SendMessageBase find(final String key) {

		SendMessageBase msg = null;

		final Set<Map.Entry<String, Function<ReceiveMessageBase, SendMessageBase>>> es = ADAPTERS
				.entrySet();
		for (final Map.Entry<String, Function<ReceiveMessageBase, SendMessageBase>> e : es) {

			if (Pattern
					.compile("^" + e.getKey() + "$", Pattern.CASE_INSENSITIVE)
					.matcher(key).matches()) {

				msg = e.getValue().apply(this);

				break;
			}
		}

		return msg == null ? this.makeNothingMessage() : msg;
	}

}