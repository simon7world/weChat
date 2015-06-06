package simon.zsh.world.wechat.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import simon.zsh.world.wechat.basis.IToXml;

public final class XmlTool {

	public Document make(final Object obj, final String rootName) {

		final Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		final Element root = doc.addElement(rootName);

		final Method[] ms = obj.getClass().getMethods();
		for (final Method m : ms) {

			final String name = m.getName();
			if (name.startsWith("get") && !name.equals("getClass")) {

				try {

					final Element sub = root.addElement(name.replaceFirst(
							"get", ""));
					if (List.class.equals(m.getReturnType())) {

						@SuppressWarnings("unchecked")
						final List<? extends IToXml> list = (List<? extends IToXml>) m
								.invoke(obj);
						for (final IToXml l : list) {

							sub.add(l.toXml().getRootElement());
						}
					} else {

						sub.addCDATA(m.invoke(obj).toString());
					}
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
				}
			}
		}

		return doc;
	}
}