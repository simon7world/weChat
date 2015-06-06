package simon.zsh.world.wechat.receive;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import simon.zsh.world.wechat.Constants;
import simon.zsh.world.wechat.utils.StringUtils;

public final class VerificationMessage {

	/**
	 * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	 */
	private String signature;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * 时间戳
	 */
	private String timestamp;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * 随机数
	 */
	private String nonce;

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	/**
	 * 随机字符串
	 */
	private String echostr;

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	/**
	 * 加密类型
	 */
	private String encrypt_type;

	public String getEncrypt_type() {
		return encrypt_type;
	}

	public void setEncrypt_type(String encrypt_type) {
		this.encrypt_type = encrypt_type;
	}

	/**
	 * 消息加密签名
	 */
	private String msg_signature;

	public String getMsg_signature() {
		return msg_signature;
	}

	public void setMsg_signature(String msg_signature) {
		this.msg_signature = msg_signature;
	}

	public boolean check() {

		if (!(StringUtils.isEmpty(signature) || StringUtils.isEmpty(timestamp) || StringUtils
				.isEmpty(nonce))) {

			final String[] arr = { Constants.TOKEN, timestamp, nonce };
			Arrays.sort(arr);
			final String content = StringUtils.collectionToDelimitedString(
					Arrays.asList(arr), "");

			byte[] digest = null;
			try {

				digest = MessageDigest.getInstance("SHA-1").digest(
						content.getBytes());
			} catch (final NoSuchAlgorithmException e) {
			}

			if (digest != null) {

				String ciphertext = "";
				for (final byte b : digest) {

					ciphertext += StringUtils.padLeft(
							Integer.toHexString(b & 0xff), 2, "0");
				}

				if (ciphertext.equalsIgnoreCase(signature)) {

					return true;
				}
			}
		}

		return false;
	}

	public String verify() {

		if (check()) {

			return echostr;
		}

		return null;
	}

	public boolean hasEncrypted() {

		return "aes".equals(encrypt_type);
	}
}