package simon.zsh.world.wechat.basis;

public abstract class MessageBase {

	/**
	 * Receive：微信公共平台原始ID Send：发送者OpenID
	 */
	private String toUserName;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(final String toUserName) {
		this.toUserName = toUserName;
	}

	/**
	 * Receive：发送者OpenID Send：微信公共平台原始ID
	 */
	private String fromUserName;

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(final String fromUserName) {
		this.fromUserName = fromUserName;
	}

	/**
	 * 消息创建时间
	 */
	private Long createTime = System.currentTimeMillis();

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(final String createTime) {
		this.createTime = Long.parseLong(createTime);
	}

	/**
	 * 消息类型
	 */
	private String msgType;

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(final String msgType) {
		this.msgType = msgType;
	}

}