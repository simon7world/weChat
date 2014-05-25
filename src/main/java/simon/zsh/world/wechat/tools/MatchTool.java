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

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import simon.zsh.world.wechat.basis.EventMessageBase;
import simon.zsh.world.wechat.basis.ReceiveBase;
import simon.zsh.world.wechat.basis.ReceiveMessageBase;
import simon.zsh.world.wechat.utils.StringUtils;

public abstract class MatchTool {

	private static final String EVENT = "Event", VERIFY = "verify";

	protected static final List<Class<? extends ReceiveMessageBase>> MSGS = new ArrayList<>(6);
	protected static final List<Class<? extends EventMessageBase>> EVTS = new ArrayList<>(6);

	public final SAXSource findSource(final HttpServletRequest req) {

		final ReceiveBase rb = find(req);

		return rb == null ? null : rb.aloha();
	}

	public final Document findDocument(final HttpServletRequest req) {

		final ReceiveBase rb = find(req);

		return rb == null ? null : rb.hula();
	}

	private ReceiveBase find(final HttpServletRequest req) {

		final Map<String, String> vals = xml2Map(req);
		final String msgType = vals.get("MsgType");
		final String event = EVENT.equalsIgnoreCase(msgType) ? vals.get(EVENT)
				: null;
		if (vals != null) {

			try {

				if (StringUtils.isEmpty(event)) {

					for (final Class<?> clazz : MSGS) {

						if ((boolean) clazz.getMethod(VERIFY, String.class)
								.invoke(clazz, msgType)) {

							return (ReceiveBase) clazz
									.getConstructor(Map.class)
									.newInstance(vals);
						}
					}
				} else {

					for (final Class<?> clazz : EVTS) {

						if ((boolean) clazz.getMethod(VERIFY, String.class)
								.invoke(clazz, event)) {

							return (ReceiveBase) clazz
									.getConstructor(Map.class)
									.newInstance(vals);
						}
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