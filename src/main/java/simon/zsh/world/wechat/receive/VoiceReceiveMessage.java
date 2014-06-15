package simon.zsh.world.wechat.receive;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.xml.transform.sax.SAXSource;

import org.dom4j.Document;

import simon.zsh.world.wechat.basis.ReceiveMessageBase;

/**
 * 接收的语音消息
 */
public abstract class VoiceReceiveMessage extends ReceiveMessageBase {

	public VoiceReceiveMessage(final Map<String, String> vals)
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
	 * 语音格式
	 */
	private String format;

	public String getFormat() {
		return format;
	}

	public void setFormat(final String format) {
		this.format = format;
	}

	public static boolean verify(final String type) {

		return ReceiveMessageTypes.VOICE.equalsIgnoreCase(type);
	}

	@Override
	public final SAXSource aloha() {

		return this.find(format).toSource();
	}

	@Override
	public final Document hula() {

		return this.find(format).toXml();
	}

}