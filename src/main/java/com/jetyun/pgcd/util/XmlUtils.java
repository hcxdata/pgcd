/**
 * 
 */
package com.jetyun.pgcd.util;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 类说明:<br>
 * 创建时间: 2008-9-18 下午03:52:05<br>
 * 
 * @author 刘岩松<br>
 * @email: seraph115@gmail.com<br>
 */
public class XmlUtils {

	public static Document getDocument(String path) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new File(path));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}

	public static Element getRootElement(Document document) {
		return document.getRootElement();
	}

	/**
	 * 功能说明: 通过给定的XPath选取属性值等于aim的节点<br>
	 * 创建者: 刘岩松<br>
	 * 创建时间: 2008-9-18 下午03:53:04<br>
	 * 
	 * @param document
	 * @param xpath
	 *            "bean/@id"
	 * @param aim
	 *            属性值
	 * @return
	 */
	public static Element getElementByAttributeValue(Document document,
			String xpath, String aim) {

		Element root = getRootElement(document);

		Iterator<?> iterator = root.selectNodes(xpath).iterator();
		while (iterator.hasNext()) {

			Attribute attribute = (Attribute) iterator.next();
			String value = attribute.getValue();
			if (StringUtils.equals(aim, value))
				return attribute.getParent();
		}
		return null;
		// throw new NoSuchElementException("未找到[" + xpath + "=" + aim +
		// "]的节点");
	}

}
