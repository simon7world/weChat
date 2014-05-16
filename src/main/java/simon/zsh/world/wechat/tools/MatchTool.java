package simon.zsh.world.wechat.tools;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.sax.SAXSource;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import simon.zsh.world.wechat.basis.ReceiveMessageBase;
import simon.zsh.world.wechat.utils.StringUtils;

public abstract class MatchTool {

	protected static final List<Class<? extends ReceiveMessageBase>> MSGS = new ArrayList<>();

	public final SAXSource find(final HttpServletRequest req) {

		final String msgType;
		final Map<String, String> vals = xml2Map(req);
		if (vals != null && !StringUtils.isEmpty(msgType = vals.get("MsgType"))) {

			try {

				for (Class<?> clazz : MSGS) {

					if ((boolean) clazz.getDeclaredMethod("verify",
							String.class).invoke(clazz, msgType)) {

						return ((ReceiveMessageBase) clazz.getConstructor(
								Map.class).newInstance(vals)).aloha(req);
					}
				}
			} catch (NoSuchMethodException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| SecurityException | InstantiationException e) {
			}
		}

		return null;
	}

	private Map<String, String> xml2Map(final HttpServletRequest req) {

		final Map<String, String> vals = new HashMap<>();

		try (final InputStream is = req.getInputStream()) {

			@SuppressWarnings("unchecked")
			final List<Element> es = new SAXReader().read(is).getRootElement()
					.elements();
			for (final Element e : es) {

				vals.put(e.getName(), e.getText());
			}
		} catch (IOException | DocumentException ex) {
		}

		return vals;
	}

}