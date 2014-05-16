package simon.zsh.world.wechat.receive;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.sax.SAXSource;

import simon.zsh.world.wechat.Types;
import simon.zsh.world.wechat.basis.ReceiveMessageBase;
import simon.zsh.world.wechat.basis.SendMessageBase;
import simon.zsh.world.wechat.send.TextSendMessage;
import simon.zsh.world.wechat.tools.WeatherTool;

import com.google.common.base.Function;

public abstract class TextReceiveMessage extends ReceiveMessageBase {

	static {

		TEXT_ADAPTERS.put("天气",
				new Function<ReceiveMessageBase, SendMessageBase>() {

					@Override
					public SendMessageBase apply(final ReceiveMessageBase obj) {

						final TextSendMessage tsm = obj
								.makeSendMessage(TextSendMessage.class);
						tsm.setContent(new WeatherTool().fromHtml());

						return tsm;
					}
				});
	}

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

		return Types.TEXT.equalsIgnoreCase(type);
	}

	@Override
	public final SAXSource aloha(final HttpServletRequest req) {

		final SendMessageBase msg = TEXT_ADAPTERS.get(content).apply(this);

		return msg.toSource();
	}

}