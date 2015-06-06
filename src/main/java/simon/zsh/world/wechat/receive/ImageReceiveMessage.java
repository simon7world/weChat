package simon.zsh.world.wechat.receive;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import simon.zsh.world.wechat.basis.ReceiveMessageBase;

/**
 * 接收的图片消息
 */
public abstract class ImageReceiveMessage extends ReceiveMessageBase {

	public ImageReceiveMessage(final Map<String, String> vals)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		super(vals);
	}

	/**
	 * 图片链接
	 */
	private String picUrl;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(final String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * 消息媒体ID
	 */
	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(final String mediaId) {
		this.mediaId = mediaId;
	}

	public static boolean verify(final String type) {

		return ReceiveMessageTypes.IMAGE.equalsIgnoreCase(type);
	}

	@Override
	public final String aloha() {

		return this.find(picUrl).toXml().asXML();
	}

}