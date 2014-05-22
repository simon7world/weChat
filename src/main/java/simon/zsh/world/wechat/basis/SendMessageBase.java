package simon.zsh.world.wechat.basis;

import javax.xml.transform.sax.SAXSource;

import org.dom4j.Document;
import org.dom4j.io.DocumentSource;

import simon.zsh.world.wechat.tools.XmlTool;

/**
 * 发送消息的基类
 */
public abstract class SendMessageBase extends MessageBase implements IToXml {

	@Override
	public final Document toXml() {

		return new XmlTool().make(this, "xml");
	}

	public final SAXSource toSource() {

		return new SAXSource(new DocumentSource(toXml()).getInputSource());
	}

}