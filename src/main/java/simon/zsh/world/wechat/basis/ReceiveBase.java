package simon.zsh.world.wechat.basis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import simon.zsh.world.wechat.Constants;
import simon.zsh.world.wechat.receive.VerificationMessage;
import simon.zsh.world.wechat.send.TextSendMessage;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * 接收的基类
 */
public abstract class ReceiveBase extends MessageBase implements IMakeMessage,
		IHawaii {

	public ReceiveBase(final Map<String, String> vals)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		final Method[] ms = this.getClass().getMethods();
		for (final Method m : ms) {

			final String name = m.getName();
			if (name.startsWith("set")) {

				m.invoke(this, vals.get(name.replaceFirst("set", "")));
			}
		}
	}

	@Override
	public final <T extends SendMessageBase> T makeSendMessage(
			final Class<T> clazz) {

		T t = null;
		try {

			t = clazz.newInstance();
		} catch (final Exception e) {

			e.printStackTrace();
		}

		if (t != null) {

			final SendMessageBase mrb = (SendMessageBase) t;
			mrb.setToUserName(this.getFromUserName());
			mrb.setFromUserName(this.getToUserName());
			mrb.setAgentID(this.getAgentID());
		}

		return t;
	}

	@Override
	public final TextSendMessage makeNothingMessage() {

		final TextSendMessage tsm = makeSendMessage(TextSendMessage.class);
		tsm.setContent("您的指令执行无结果~\n请检查或稍候再试！");

		return tsm;
	}

	@Override
	public final TextSendMessage makeMismatchMessage() {

		final TextSendMessage tsm = makeSendMessage(TextSendMessage.class);
		tsm.setContent("您的指令太高深了~\n我无法理解！");

		return tsm;
	}

	@Override
	public final String aloha(final VerificationMessage vm) {

		String ret = null;
		try {

			final String reply = aloha();
			if (reply != null) {

				ret = new WXBizMsgCrypt(Constants.TOKEN, Constants.AES_KEY,
						Constants.APP_ID).EncryptMsg(reply,
						"" + System.currentTimeMillis() / 1000,
						Constants.RANDOM_STRING());
			}
		} catch (final Exception e) {

			e.printStackTrace();
		}

		return ret;
	}

	protected abstract SendMessageBase find(final String key);

}