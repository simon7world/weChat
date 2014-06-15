package simon.zsh.world.wechat.receive;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.xml.transform.sax.SAXSource;

import org.dom4j.Document;

import simon.zsh.world.wechat.basis.ReceiveMessageBase;

/**
 * 接收的视频消息
 */
public abstract class VideoReceiveMessage extends ReceiveMessageBase {

	public VideoReceiveMessage(final Map<String, String> vals)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		super(vals);
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

	/**
	 * 缩略图的媒体ID
	 */
	private String thumbMediaId;

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(final String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public static boolean verify(final String type) {

		return ReceiveMessageTypes.VIDEO.equalsIgnoreCase(type);
	}

	@Override
	public final SAXSource aloha() {

		return this.find("").toSource();
	}

	@Override
	public final Document hula() {

		return this.find("").toXml();
	}

}