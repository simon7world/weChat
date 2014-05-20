package simon.zsh.world.wechat.basis;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Function;

/**
 * 接收消息的基类
 */
public abstract class ReceiveMessageBase extends ReceiveBase {

	protected static final Map<String, Function<ReceiveMessageBase, SendMessageBase>> TEXT_ADAPTERS = new HashMap<>();

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

}