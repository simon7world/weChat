package simon.zsh.world.wechat.tools;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import simon.zsh.world.wechat.Constants;
import simon.zsh.world.wechat.basis.EventMessageBase;
import simon.zsh.world.wechat.basis.ReceiveBase;
import simon.zsh.world.wechat.basis.ReceiveMessageBase;
import simon.zsh.world.wechat.receive.VerificationMessage;
import simon.zsh.world.wechat.utils.StringUtils;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public abstract class MatchTool {

	private static final String EVENT = "Event", VERIFY = "verify";

	protected static final List<Class<? extends ReceiveMessageBase>> MSGS = new ArrayList<>(
			6);
	protected static final List<Class<? extends EventMessageBase>> EVTS = new ArrayList<>(
			6);

	public final String findDocument(final HttpServletRequest req) {

		final ReceiveBase rb = find(req);

		return rb == null ? null : rb.aloha();
	}

	public final String findDocumentWithEncryptedData(
			final HttpServletRequest req, final VerificationMessage vm) {

		final ReceiveBase rb = find(req, vm);

		return rb == null ? null : rb.aloha(vm);
	}

	private ReceiveBase find(final HttpServletRequest req) {

		return findBase(xml2Map(req, null));
	}

	private ReceiveBase find(final HttpServletRequest req,
			final VerificationMessage vm) {

		return findBase(xml2Map(req, vm));
	}

	private ReceiveBase findBase(final Map<String, String> vals) {

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

	private Map<String, String> xml2Map(final HttpServletRequest req,
			final VerificationMessage vm) {

		InputStream inStream = null;
		if (vm == null) {

			try {

				inStream = req.getInputStream();
			} catch (final IOException e) {
			}
		} else {

			String decrypt;
			try (final InputStream is = req.getInputStream()) {

				decrypt = new WXBizMsgCrypt(Constants.TOKEN, Constants.AES_KEY,
						Constants.APP_ID).DecryptMsg(vm.getMsg_signature(),
						vm.getTimestamp(), vm.getNonce(),
						new SAXReader().read(is).asXML());
			} catch (final IOException | DocumentException | AesException e) {

				decrypt = "";
			}

			inStream = new ByteArrayInputStream(decrypt.getBytes());
		}

		final Map<String, String> vals = new HashMap<>();
		try {

			@SuppressWarnings("unchecked")
			final List<Element> es = new SAXReader().read(inStream)
					.getRootElement().elements();
			for (final Element e : es) {

				vals.put(e.getName(), e.getText());
			}
		} catch (final DocumentException e) {
		} finally {

			if (inStream != null) {

				try {

					inStream.close();
				} catch (final IOException e) {
				}
			}
		}

		return vals;
	}

	public static boolean isWeChatBrowser(final HttpServletRequest req) {

		return req.getHeader("user-agent").toLowerCase()
				.contains("micromessenger");
	}
}