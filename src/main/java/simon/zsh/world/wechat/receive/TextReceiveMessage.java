package simon.zsh.world.wechat.receive;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.google.common.base.Function;

import simon.zsh.world.wechat.basis.ReceiveMessageBase;
import simon.zsh.world.wechat.basis.SendMessageBase;
import simon.zsh.world.wechat.send.TextSendMessage;
import simon.zsh.world.wechat.tools.WeatherTool;

/**
 * 接收的文本消息
 */
public abstract class TextReceiveMessage extends ReceiveMessageBase {

	static {

		ADAPTERS.put("@天气", new Function<ReceiveMessageBase, SendMessageBase>() {

			@Override
			public SendMessageBase apply(final ReceiveMessageBase obj) {

				final TextSendMessage tsm = obj.makeSendMessage(TextSendMessage.class);

				try {

					tsm.setContent(new WeatherTool().fromHtml());
				} catch (final Exception e) {

					tsm.setContent("暂时无法获取天气情况，请稍后再试。");
				}

				return tsm;
			}
		});
	}

	public TextReceiveMessage(final Map<String, String> vals)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

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
		this.content = content.trim();
	}

	public static boolean verify(final String type) {

		return ReceiveMessageTypes.TEXT.equalsIgnoreCase(type);
	}

	@Override
	public final String aloha() {

		return this.find(content).toXml().asXML();
	}

}