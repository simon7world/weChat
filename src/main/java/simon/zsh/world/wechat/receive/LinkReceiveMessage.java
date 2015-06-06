package simon.zsh.world.wechat.receive;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import simon.zsh.world.wechat.basis.ReceiveMessageBase;

/**
 * 接收的链接消息
 */
public abstract class LinkReceiveMessage extends ReceiveMessageBase {

	public LinkReceiveMessage(final Map<String, String> vals)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		super(vals);
	}

	/**
	 * 标题
	 */
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * 描述
	 */
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * 链接
	 */
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public static boolean verify(final String type) {

		return ReceiveMessageTypes.LINK.equalsIgnoreCase(type);
	}

	@Override
	public final String aloha() {

		return this.find(url).toXml().asXML();
	}

}