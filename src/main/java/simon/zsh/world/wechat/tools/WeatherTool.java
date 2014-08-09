package simon.zsh.world.wechat.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import simon.zsh.world.wechat.utils.StringUtils;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

public final class WeatherTool {

	public String fromHtml() {

		int times = 3;
		while (times-- > 0) {

			final String temp = htmlSource("http://www.weather.com.cn/weather/101210501.shtml?t="
					+ System.currentTimeMillis());
			final Matcher m = Pattern.compile(
					"<div\\sclass=\"weatherYubaoBox\">.*?</div>",
					Pattern.CASE_INSENSITIVE).matcher(temp);

			String html = "";
			if (m.find()) {

				html = m.group()
						.replaceAll(
								"&nbsp;|<!--.*?-->|<(no)?script.*?>.*?</(no)?script>|<strong>|</strong>|<b>|</b>|<span>|</span>",
								" ");
			} else {

				continue;
			}

			try {

				return readXML((Document) DocumentHelper.parseText(html));
			} catch (DocumentException e) {
			}
		}

		return "接口当前正忙，请稍候再试！";
	}

	private String htmlSource(final String url) {

		final StringBuilder htmlSource = new StringBuilder(7800);

		try (final BufferedReader in = new BufferedReader(
				new InputStreamReader(new URL(url).openStream(), "UTF-8"))) {

			String htmlLine;
			while ((htmlLine = in.readLine()) != null) {

				htmlSource.append(htmlLine.trim().toLowerCase());
			}
		} catch (IOException e) {
		}

		return htmlSource.toString();
	}

	private String readXML(final Document document) {

		final StringBuilder content = new StringBuilder(150);
		final Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);

		final Iterator<?> it = document.getRootElement()
				.selectNodes("table[@class='yubaotable'][position()<5]")
				.iterator();
		while (it.hasNext()) {

			c.add(Calendar.DAY_OF_MONTH, 1);

			@SuppressWarnings("unchecked")
			final List<Node> nodes = ((Element) it.next())
					.selectNodes("tr/td/text()|tr/td/a/text()");
			final List<String> vals = new ArrayList<>(Collections2.transform(
					nodes, new Function<Node, String>() {

						@Override
						public String apply(Node node) {

							return node.getText().trim()
									.replaceAll("\\s+", " ");
						}
					}));

			content.append(c.get(Calendar.MONTH) + 1 + "月"
					+ vals.get(0).replace(" ", "（") + "）\n");
			List<String> dAn = vals.subList(1, 6);
			content.append("  " + dAn.remove(0) + "：");
			dAn.remove(2);
			content.append(StringUtils.collectionToDelimitedString(dAn, "  "));
			if (vals.size() > 4) {

				dAn = vals.subList(4, vals.size());
				content.append("\n  " + dAn.remove(0) + "：");
				dAn.remove(2);
				content.append(StringUtils.collectionToDelimitedString(dAn,
						"  "));
			}

			if (it.hasNext()) {

				content.append("\n\n");
			}
		}

		return content.toString();
	}

}