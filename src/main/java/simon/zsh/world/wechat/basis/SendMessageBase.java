package simon.zsh.world.wechat.basis;

import org.dom4j.Document;

import simon.zsh.world.wechat.tools.XmlTool;

/**
 * 发送消息的基类
 */
public abstract class SendMessageBase extends MessageBase implements IToXml {

	@Override
	public final Document toXml() {

		return new XmlTool().make(this, "xml");
	}
}