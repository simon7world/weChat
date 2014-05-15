package simon.zsh.world.wechat.basis;

import javax.xml.transform.sax.SAXSource;

import org.dom4j.Document;
import org.dom4j.io.DocumentSource;

import simon.zsh.world.wechat.tools.XmlTool;

public abstract class SendMessageBase extends MessageBase implements ToXml {

	@Override
	public Document toXml() {

		return new XmlTool().make(this, "xml");
	}

	public SAXSource toSource() {

		return new SAXSource(new DocumentSource(toXml()).getInputSource());
	}

}