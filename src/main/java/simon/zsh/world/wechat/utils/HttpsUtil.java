package simon.zsh.world.wechat.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

final class HttpsUtil {

	/**
	 * 发送https请求
	 */
	String httpsRequest(final String url, final String method,
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

			final SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(null, tm, new SecureRandom());

			final URL rurl = new URL(url);
			conn = (HttpsURLConnection) rurl.openConnection();
			conn.setConnectTimeout(12000);
			conn.setReadTimeout(12000);
			conn.setSSLSocketFactory(sslContext.getSocketFactory());
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(method);

			// 当outputStr不为null时向输出流写数据
			if (null != source) {

				try (final OutputStream os = conn.getOutputStream()) {

					os.write(source.getBytes(StandardCharsets.UTF_8));
				}
			}

			// 从输入流读取返回内容
			try (final BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream(),
							StandardCharsets.UTF_8))) {

				String str = null;
				final StringBuffer buffer = new StringBuffer();
				while ((str = br.readLine()) != null) {

					buffer.append(str);
				}

				ret = buffer.toString();
			}
		} catch (final Exception e) {

			e.printStackTrace();
		} finally {

			if (conn != null) {

				conn.disconnect();
			}
		}

		return ret;
	}
}