package simon.zsh.world.wechat.receive;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.sax.SAXSource;

import simon.zsh.world.wechat.WeChatMessages;
import simon.zsh.world.wechat.basis.ReceiveMessageBase;
import simon.zsh.world.wechat.basis.SendMessageBase;
import simon.zsh.world.wechat.send.TextSendMessage;
import simon.zsh.world.wechat.tools.WeatherTool;

public final class TextReceiveMessage extends ReceiveMessageBase {

	public TextReceiveMessage(final Map<String, String> vals)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		super(vals);
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

	public static boolean verify(final String type) {

		return WeChatMessages.TEXT.equalsIgnoreCase(type);
	}

	private SendMessageBase weather() {

		final TextSendMessage tsm = this.makeSendMessage(TextSendMessage.class);
		tsm.setContent(new WeatherTool().fromHtml());

		return tsm;
	}

	@Override
	public SAXSource aloha(final HttpServletRequest req) {

		SendMessageBase source = null;
		switch (content) {

		case "天气":
			source = weather();
			break;
		}

		return source.toSource();
	}

}