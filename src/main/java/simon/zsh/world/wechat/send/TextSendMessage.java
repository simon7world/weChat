package simon.zsh.world.wechat.send;

import simon.zsh.world.wechat.WeChatMessages;
import simon.zsh.world.wechat.basis.SendMessageBase;

public final class TextSendMessage extends SendMessageBase {

	public TextSendMessage() {

		setMsgType(WeChatMessages.TEXT);
	}

	/**
	 * 消息内容
	 */
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

}