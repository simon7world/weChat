package simon.zsh.world.wechat.receive;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import simon.zsh.world.wechat.basis.ReceiveMessageBase;

/**
 * 接收的地理位置消息
 */
public abstract class LocationReceiveMessage extends ReceiveMessageBase {

	public LocationReceiveMessage(final Map<String, String> vals)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		super(vals);
	}

	/**
	 * 纬度
	 */
	private String location_X;

	public String getLocation_X() {
		return location_X;
	}

	public void setLocation_X(final String location_X) {
		this.location_X = location_X;
	}

	/**
	 * 经度
	 */
	private String location_Y;

	public String getLocation_Y() {
		return location_Y;
	}

	public void setLocation_Y(final String location_Y) {
		this.location_Y = location_Y;
	}

	/**
	 * 缩放大小
	 */
	private String scale;

	public String getScale() {
		return scale;
	}

	public void setScale(final String scale) {
		this.scale = scale;
	}

	/**
	 * 地理位置信息
	 */
	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	public static boolean verify(final String type) {

		return ReceiveMessageTypes.LOCATION.equalsIgnoreCase(type);
	}

	@Override
	public final String aloha() {

		return this.find(location_Y + "," + location_X).toXml().asXML();
	}

}