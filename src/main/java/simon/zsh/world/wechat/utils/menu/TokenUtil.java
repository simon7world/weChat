package simon.zsh.world.wechat.utils.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import simon.zsh.world.wechat.Constants;

import com.google.gson.Gson;

public abstract class TokenUtil {

	/**
	 * 发送https请求
	 */
	public static String httpsRequest(final String url, final String method,
			final String source) {

		HttpsURLConnection conn = null;

		String ret = null;
		try {

			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			final TrustManager[] tm = { new X509TrustManager() {

				@Override
				public void checkClientTrusted(final X509Certificate[] chain,
						final String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(final X509Certificate[] chain,
						final String authType) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} };

			final SSLContext sslContext = SSLContext.getInstance("SSL",
					"SunJSSE");
			sslContext.init(null, tm, new SecureRandom());

			final URL rurl = new URL(url);
			conn = (HttpsURLConnection) rurl.openConnection();
			conn.setSSLSocketFactory(sslContext.getSocketFactory());
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(method);

			// 当outputStr不为null时向输出流写数据
			if (null != source) {

				try (final OutputStream os = conn.getOutputStream()) {

					os.write(source.getBytes("UTF-8"));
				}
			}

			// 从输入流读取返回内容
			try (final BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream(), "UTF-8"))) {

				String str = null;
				final StringBuffer buffer = new StringBuffer();
				while ((str = br.readLine()) != null) {

					buffer.append(str);
				}

				ret = buffer.toString();
			}
		} catch (IOException | NoSuchAlgorithmException
				| NoSuchProviderException | KeyManagementException e) {
		} finally {

			if (conn != null) {
				conn.disconnect();
			}
		}

		return ret;
	}

	/**
	 * 获取接口访问凭证
	 */
	public static void getAccessToken() {

		final String ret = httpsRequest(String.format(Constants.TOKEN_URL,
				Constants.APP_ID, Constants.APP_SECRET), "GET", null);
		if (ret != null) {

			try {

				@SuppressWarnings("unchecked")
				final Map<String, Object> vals = new Gson().fromJson(ret,
						Map.class);

				Constants.ACCESS_TOKEN = (String) vals.get("access_token");
				Constants.UEXPIRES_IN = (int) vals.get("uexpires_in");

			} catch (Exception e) {
			}
		}
	}

}