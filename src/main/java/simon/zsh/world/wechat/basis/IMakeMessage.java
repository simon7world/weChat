package simon.zsh.world.wechat.basis;

import simon.zsh.world.wechat.send.TextSendMessage;

public interface IMakeMessage {

	<T extends SendMessageBase> T makeSendMessage(final Class<T> clazz);

	TextSendMessage makeNothingMessage();

}