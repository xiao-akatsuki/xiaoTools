package com.xiaoTools.util.xmlUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.escape.escapeUtil.EscapeUtil;
import com.xiaoTools.core.exception.iORuntimeException.IORuntimeException;
import com.xiaoTools.core.exception.utilException.UtilException;
import com.xiaoTools.core.map.biMap.BiMap;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.beanUtil.BeanUtil;
import com.xiaoTools.util.charsetUtil.CharsetUtil;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.fileUtil.fileUtil.FileUtil;
import com.xiaoTools.util.ioUtil.IoUtil;
import com.xiaoTools.util.mapUtil.MapUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [XML工具类](XML tool class)
 * @description zh - XML工具类
 * @description en - XML tool class
 * copy in [hutools](https://github.com/dromara/hutool)
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-28 19:08:17
 */
public class XmlUtil {

	/**
	 * 字符串常量：XML 空格转义 {@code "&nbsp;" -> " "}
	 */
	public static final String NBSP = "&nbsp;";

	/**
	 * 字符串常量：XML And 符转义 {@code "&amp;" -> "&"}
	 */
	public static final String AMP = "&amp;";

	/**
	 * 字符串常量：XML 双引号转义 {@code "&quot;" -> "\""}
	 */
	public static final String QUOTE = "&quot;";

	/**
	 * 字符串常量：XML 单引号转义 {@code "&apos" -> "'"}
	 */
	public static final String APOS = "&apos;";

	/**
	 * 字符串常量：XML 小于号转义 {@code "&lt;" -> "<"}
	 */
	public static final String LT = "&lt;";

	/**
	 * 字符串常量：XML 大于号转义 {@code "&gt;" -> ">"}
	 */
	public static final String GT = "&gt;";

	/**
	 * 在XML中无效的字符 正则
	 */
	public static final String INVALID_REGEX = "[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]";
	/**
	 * 在XML中注释的内容 正则
	 */
	public static final String COMMENT_REGEX = "(?s)<!--.+?-->";
	/**
	 * XML格式化输出默认缩进量
	 */
	public static final int INDENT_DEFAULT = 2;

	/**
	 * 默认的DocumentBuilderFactory实现
	 */
	private static String defaultDocumentBuilderFactory = "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl";

	/**
	 * 是否打开命名空间支持
	 */
	private static boolean namespaceAware = true;
	/**
	 * Sax读取器工厂缓存
	 */
	private static SAXParserFactory factory;

	/**
	 * [禁用默认的DocumentBuilderFactory](Disable the default documentbuilderfactory)
	 * @description zh - 禁用默认的DocumentBuilderFactory
	 * @description en - Disable the default documentbuilderfactory
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:20:53
	 */
	synchronized public static void disableDefaultDocumentBuilderFactory() {
		defaultDocumentBuilderFactory = null;
	}

	/**
	 * [设置是否打开命名空间支持](Sets whether namespace support is turned on)
	 * @description zh - 设置是否打开命名空间支持
	 * @description en - Sets whether namespace support is turned on
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:21:24
	 * @param isNamespaceAware 是否命名空间支持
	 */
	synchronized public static void setNamespaceAware(boolean isNamespaceAware) {
		namespaceAware = isNamespaceAware;
	}

	// -------------------------------------------------------------------------------------- Read

	/**
	 * [读取解析XML文件](Read parse XML file)
	 * @description zh - 读取解析XML文件
	 * @description en - Read parse XML file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:24:18
	 * @param file XML文件
	 * @return org.w3c.dom.Document
	 */
	public static Document readXML(File file) {
		Assertion.notNull(file, "Xml file is null !");
		if (Constant.FALSE == file.exists()) {
			throw new UtilException("File [{}] not a exist!", file.getAbsolutePath());
		}
		if (Constant.FALSE == file.isFile()) {
			throw new UtilException("[{}] not a file!", file.getAbsolutePath());
		}

		try {
			file = file.getCanonicalFile();
		} catch (IOException e) {
		}

		BufferedInputStream in = null;
		try {
			in = FileUtil.getInputStream(file);
			return readXML(in);
		} finally {
			IoUtil.close(in);
		}
	}

	/**
	 * [读取解析XML文件](Read parse XML file)
	 * @description zh - 读取解析XML文件
	 * @description en - Read parse XML file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:26:08
	 * @param pathOrContent 内容或路径
	 * @return org.w3c.dom.Document
	 */
	public static Document readXML(String pathOrContent) {
		return StrUtil.startWith(pathOrContent, '<') ? parseXml(pathOrContent) : readXML(FileUtil.file(pathOrContent));
	}

	/**
	 * [读取解析XML文件](Read parse XML file)
	 * @description zh - 读取解析XML文件
	 * @description en - Read parse XML file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:26:44
	 * @param inputStream XML流
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return org.w3c.dom.Document
	 */
	public static Document readXML(InputStream inputStream) throws UtilException {
		return readXML(new InputSource(inputStream));
	}

	/**
	 * [读取解析XML文件](Read parse XML file)
	 * @description zh - 读取解析XML文件
	 * @description en - Read parse XML file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:27:16
	 * @param reader XML流
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return org.w3c.dom.Document
	 */
	public static Document readXML(Reader reader) throws UtilException {
		return readXML(new InputSource(reader));
	}

	/**
	 * [读取解析XML文件](Read parse XML file)
	 * @description zh - 读取解析XML文件
	 * @description en - Read parse XML file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:29:45
	 * @param source InputSource
	 * @return org.w3c.dom.Document
	 */
	public static Document readXML(InputSource source) {
		final DocumentBuilder builder = createDocumentBuilder();
		try {
			return builder.parse(source);
		} catch (Exception e) {
			throw new UtilException(e, "Parse XML from stream error!");
		}
	}

	/**
	 * [使用Sax方式读取指定的XML](Reads the specified XML in Sax mode)
	 * @description zh - 使用Sax方式读取指定的XML
	 * @description en - Reads the specified XML in Sax mode
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:30:18
	 * @param file XML源文件,使用后自动关闭
	 * @param contentHandler XML流处理器，用于按照Element处理xml
	 */
	public static void readBySax(File file, ContentHandler contentHandler) {
		InputStream in = null;
		try{
			in = FileUtil.getInputStream(file);
			readBySax(new InputSource(in), contentHandler);
		} finally {
			IoUtil.close(in);
		}
	}

	/**
	 * [使用Sax方式读取指定的XML](Reads the specified XML in Sax mode)
	 * @description zh - 使用Sax方式读取指定的XML
	 * @description en - Reads the specified XML in Sax mode
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:32:50
	 * @param reader XML源Reader,使用后自动关闭
	 * @param contentHandler XML流处理器，用于按照Element处理xml
	 */
	public static void readBySax(Reader reader, ContentHandler contentHandler) {
		try{
			readBySax(new InputSource(reader), contentHandler);
		} finally {
			IoUtil.close(reader);
		}
	}

	/**
	 * [使用Sax方式读取指定的XML](Reads the specified XML in Sax mode)
	 * @description zh - 使用Sax方式读取指定的XML
	 * @description en - Reads the specified XML in Sax mode
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:33:22
	 * @param source XML源流,使用后自动关闭
	 * @param contentHandler XML流处理器，用于按照Element处理xml
	 */
	public static void readBySax(InputStream source, ContentHandler contentHandler) {
		try{
			readBySax(new InputSource(source), contentHandler);
		} finally {
			IoUtil.close(source);
		}
	}

	/**
	 * [使用Sax方式读取指定的XML](Reads the specified XML in Sax mode)
	 * @description zh - 使用Sax方式读取指定的XML
	 * @description en - Reads the specified XML in Sax mode
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:33:56
	 * @param source XML源，可以是文件、流、路径等
	 * @param contentHandler XML流处理器，用于按照Element处理xml
	 */
	public static void readBySax(InputSource source, ContentHandler contentHandler) {
		if (null == factory) {
			factory = SAXParserFactory.newInstance();
			factory.setValidating(Constant.FALSE);
			factory.setNamespaceAware(namespaceAware);
		}
		final SAXParser parse;
		XMLReader reader;
		try {
			parse = factory.newSAXParser();
			if (contentHandler instanceof DefaultHandler) {
				parse.parse(source, (DefaultHandler) contentHandler);
				return;
			}
			reader = parse.getXMLReader();
			reader.setContentHandler(contentHandler);
			reader.parse(source);
		} catch (ParserConfigurationException | SAXException e) {
			throw new UtilException(e);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	/**
	 * [将String类型的XML转换为XML文档](Converts XML of type string to an XML document)
	 * @description zh - 将String类型的XML转换为XML文档
	 * @description en - Converts XML of type string to an XML document
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:34:19
	 * @param xmlStr XML字符串
	 * @return org.w3c.dom.Document
	 */
	public static Document parseXml(String xmlStr) {
		if (StrUtil.isBlank(xmlStr)) {
			throw new IllegalArgumentException("XML content string is empty !");
		}
		xmlStr = cleanInvalid(xmlStr);
		return readXML(StrUtil.getReader(xmlStr));
	}

	/**
	 * [从XML中读取对象](Reads serialized object from the XML file.)
	 * @description zh - 从XML中读取对象
	 * @description en - Reads serialized object from the XML file.
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:34:55
	 * @param source XML文件
	 * @return T
	 */
	public static <T> T readObjectFromXml(File source) {
		return readObjectFromXml(new InputSource(FileUtil.getInputStream(source)));
	}

	/**
	 * [从XML中读取对象](Reads serialized object from the XML file.)
	 * @description zh - 从XML中读取对象
	 * @description en - Reads serialized object from the XML file.
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:35:30
	 * @param xmlStr XML内容
	 * @return T
	 */
	public static <T> T readObjectFromXml(String xmlStr) {
		return readObjectFromXml(new InputSource(StrUtil.getReader(xmlStr)));
	}

	/**
	 * [从XML中读取对象](Reads serialized object from the XML file.)
	 * @description zh - 从XML中读取对象
	 * @description en - Reads serialized object from the XML file.
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:37:07
	 * @param source InputSource
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T readObjectFromXml(InputSource source) {
		Object result;
		XMLDecoder xmldec = null;
		try {
			xmldec = new XMLDecoder(source);
			result = xmldec.readObject();
		} finally {
			IoUtil.close(xmldec);
		}
		return (T) result;
	}

	// -------------------------------------------------------------------------------------- Write

	/**
	 * [将XML文档转换为String](Convert XML document to string)
	 * @description zh - 将XML文档转换为String
	 * @description en - Convert XML document to string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:37:42
	 * @param doc XML文档
	 * @return java.lang.String
	 */
	public static String toStr(Node doc) {
		return toStr(doc, Constant.FALSE);
	}

	/**
	 * [将XML文档转换为String](Convert XML document to string)
	 * @description zh - 将XML文档转换为String
	 * @description en - Convert XML document to string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:38:36
	 * @param doc XML文档
	 * @return java.lang.String
	 */
	public static String toStr(Document doc) {
		return toStr((Node)doc);
	}

	/**
	 * [将XML文档转换为String](Convert XML document to string)
	 * @description zh - 将XML文档转换为String
	 * @description en - Convert XML document to string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:39:01
	 * @param doc XML文档
	 * @param isPretty 是否格式化输出
	 * @return java.lang.String
	 */
	public static String toStr(Node doc, boolean isPretty) {
		return toStr(doc, CharsetUtil.UTF_8, isPretty);
	}

	/**
	 * [将XML文档转换为String](Convert XML document to string)
	 * @description zh - 将XML文档转换为String
	 * @description en - Convert XML document to string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:39:37
	 * @param doc XML文档
	 * @param isPretty 是否格式化输出
	 * @return java.lang.String
	 */
	public static String toStr(Document doc, boolean isPretty) {
		return toStr((Node)doc, isPretty);
	}

	/**
	 * [将XML文档转换为String](Convert XML document to string)
	 * @description zh - 将XML文档转换为String
	 * @description en - Convert XML document to string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:41:11
	 * @param doc XML文档
	 * @param charset 编码
	 * @param isPretty 是否格式化输出
	 * @return java.lang.String
	 */
	public static String toStr(Node doc, String charset, boolean isPretty) {
		return toStr(doc, charset, isPretty, false);
	}

	/**
	 * [将XML文档转换为String](Convert XML document to string)
	 * @description zh - 将XML文档转换为String
	 * @description en - Convert XML document to string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:41:45
	 * @param doc XML文档
	 * @param charset 编码
	 * @param isPretty 是否格式化输出
	 * @return java.lang.String
	 */
	public static String toStr(Document doc, String charset, boolean isPretty) {
		return toStr((Node)doc, charset, isPretty);
	}

	/**
	 * [将XML文档转换为String](Convert XML document to string)
	 * @description zh - 将XML文档转换为String
	 * @description en - Convert XML document to string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:42:19
	 * @param doc XML文档
	 * @param charset 编码
	 * @param isPretty 是否格式化输出
	 * @param omitXmlDeclaration 是否输出 xml Declaration
	 * @return java.lang.String
	 */
	public static String toStr(Node doc, String charset, boolean isPretty, boolean omitXmlDeclaration) {
		final StringWriter writer = StrUtil.getWriter();
		try {
			write(doc, writer, charset, isPretty ? INDENT_DEFAULT : 0, omitXmlDeclaration);
		} catch (Exception e) {
			throw new UtilException(e, "Trans xml document to string error!");
		}
		return writer.toString();
	}

	/**
	 * [格式化XML输出](Format XML output)
	 * @description zh - 格式化XML输出
	 * @description en - Format XML output
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:43:07
	 * @param doc XML文档
	 * @return java.lang.String
	 */
	public static String format(Document doc) {
		return toStr(doc, Constant.TRUE);
	}

	/**
	 * [格式化XML输出](Format XML output)
	 * @description zh - 格式化XML输出
	 * @description en - Format XML output
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:43:56
	 * @param xmlStr XML字符串
	 * @return java.lang.String
	 */
	public static String format(String xmlStr) {
		return format(parseXml(xmlStr));
	}

	/**
	 * [将XML文档写入到文件](Writing an XML document to a file)
	 * @description zh - 将XML文档写入到文件
	 * @description en - Writing an XML document to a file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:44:22
	 * @param doc XML文档
	 * @param absolutePath 文件绝对路径，不存在会自动创建
	 */
	public static void toFile(Document doc, String absolutePath) {
		toFile(doc, absolutePath, null);
	}

	/**
	 * [将XML文档写入到文件](Writing an XML document to a file)
	 * @description zh - 将XML文档写入到文件
	 * @description en - Writing an XML document to a file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:45:08
	 * @param doc XML文档
	 * @param path 文件路径绝对路径或相对ClassPath路径，不存在会自动创建
	 * @param charset 自定义XML文件的编码，如果为{@code null} 读取XML文档中的编码，否则默认UTF-8
	 */
	public static void toFile(Document doc, String path, String charset) {
		if (StrUtil.isBlank(charset)) {
			charset = doc.getXmlEncoding();
		}
		if (StrUtil.isBlank(charset)) {
			charset = CharsetUtil.UTF_8;
		}

		BufferedWriter writer = null;
		try {
			writer = FileUtil.getWriter(path, charset, false);
			write(doc, writer, charset, INDENT_DEFAULT);
		} finally {
			IoUtil.close(writer);
		}
	}

	/**
	 * [将XML文档写出](Write XML document out)
	 * @description zh - 将XML文档写出
	 * @description en - Write XML document out
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:46:06
	 * @param node XML文档节点或文档本身
	 * @param writer 写出的Writer，Writer决定了输出XML的编码
	 * @param charset 编码
	 * @param indent 格式化输出中缩进量，小于1表示不格式化输出
	 */
	public static void write(Node node, Writer writer, String charset, int indent) {
		transform(new DOMSource(node), new StreamResult(writer), charset, indent);
	}

	/**
	 * [将XML文档写出](Write XML document out)
	 * @description zh - 将XML文档写出
	 * @description en - Write XML document out
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:49:08
	 * @param node XML文档节点或文档本身
	 * @param writer 写出的Writer，Writer决定了输出XML的编码
	 * @param charset 编码
	 * @param indent 格式化输出中缩进量，小于1表示不格式化输出
	 * @param omitXmlDeclaration 是否输出 xml Declaration
	 */
	public static void write(Node node, Writer writer, String charset, int indent, boolean omitXmlDeclaration) {
		transform(new DOMSource(node), new StreamResult(writer), charset, indent, omitXmlDeclaration);
	}

	/**
	 * [将XML文档写出](Write XML document out)
	 * @description zh - 将XML文档写出
	 * @description en - Write XML document out
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:50:09
	 * @param node XML文档节点或文档本身
	 * @param out 写出的Writer，Writer决定了输出XML的编码
	 * @param charset 编码
	 * @param indent 格式化输出中缩进量，小于1表示不格式化输出
	 */
	public static void write(Node node, OutputStream out, String charset, int indent) {
		transform(new DOMSource(node), new StreamResult(out), charset, indent);
	}

	/**
	 * [将XML文档写出](Write XML document out)
	 * @description zh - 将XML文档写出
	 * @description en - Write XML document out
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:50:51
	 * @param node XML文档节点或文档本身
	 * @param out 写出的Writer，Writer决定了输出XML的编码
	 * @param charset 编码
	 * @param indent 格式化输出中缩进量，小于1表示不格式化输出
	 * @param omitXmlDeclaration 是否输出 xml Declaration
	 */
	public static void write(Node node, OutputStream out, String charset, int indent, boolean omitXmlDeclaration) {
		transform(new DOMSource(node), new StreamResult(out), charset, indent, omitXmlDeclaration);
	}

	/**
	 * [将XML文档写出](Write XML document out)
	 * @description zh - 将XML文档写出
	 * @description en - Write XML document out
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:51:29
	 * @param source 源
	 * @param result 目标
	 * @param charset 编码
	 * @param indent 格式化输出中缩进量，小于1表示不格式化输出
	 */
	public static void transform(Source source, Result result, String charset, int indent) {
		transform(source, result, charset, indent, Constant.FALS);
	}

	/**
	 * [将XML文档写出](Write XML document out)
	 * @description zh - 将XML文档写出
	 * @description en - Write XML document out
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:53:27
	 * @param source 源
	 * @param result 目标
	 * @param charset 编码
	 * @param indent 格式化输出中缩进量，小于1表示不格式化输出
	 * @param omitXmlDeclaration 是否输出 xml Declaration
	 */
	public static void transform(Source source, Result result, String charset, int indent, boolean omitXmlDeclaration) {
		final TransformerFactory factory = TransformerFactory.newInstance();
		try {
			final Transformer xformer = factory.newTransformer();
			if (indent > Constant.ZERO) {
				xformer.setOutputProperty(OutputKeys.INDENT, "yes");
				xformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
				xformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(indent));
			}
			if (StrUtil.isNotBlank(charset)) {
				xformer.setOutputProperty(OutputKeys.ENCODING, charset);
			}
			if (omitXmlDeclaration) {
				xformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			}
			xformer.transform(source, result);
		} catch (Exception e) {
			throw new UtilException(e, "Trans xml document to string error!");
		}
	}

	// -------------------------------------------------------------------------------------- Create

	/**
	 * [创建XML文档](Create XML document)
	 * @description zh - 创建XML文档
	 * @description en - Create XML document
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:54:13
	 * @param org.w3c.dom.Document
	 */
	public static Document createXml() {
		return createDocumentBuilder().newDocument();
	}

	/**
	 * [创建 DocumentBuilder](Create documentbuilder)
	 * @description zh - 创建 DocumentBuilder
	 * @description en - Create documentbuilder
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:54:50
	 * @return javax.xml.parsers.DocumentBuilder
	 */
	public static DocumentBuilder createDocumentBuilder() {
		DocumentBuilder builder;
		try {
			builder = createDocumentBuilderFactory().newDocumentBuilder();
		} catch (Exception e) {
			throw new UtilException(e, "Create xml document error!");
		}
		return builder;
	}

	/**
	 * [创建 DocumentBuilderFactory](Create documentbuilderfactory)
	 * @description zh - 创建 DocumentBuilderFactory
	 * @description en - Create documentbuilderfactory
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:55:51
	 * @return javax.xml.parsers.DocumentBuilderFactory
	 */
	public static DocumentBuilderFactory createDocumentBuilderFactory() {
		final DocumentBuilderFactory factory;
		if (StrUtil.isNotEmpty(defaultDocumentBuilderFactory)) {
			factory = DocumentBuilderFactory.newInstance(defaultDocumentBuilderFactory, null);
		} else {
			factory = DocumentBuilderFactory.newInstance();
		}
		factory.setNamespaceAware(namespaceAware);
		return disableXXE(factory);
	}

	/**
	 * [创建XML文档](Create XML document)
	 * @description zh - 创建XML文档
	 * @description en - Create XML document
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:56:30
	 * @param rootElementName 根节点名称
	 * @return org.w3c.dom.Document
	 */
	public static Document createXml(String rootElementName) {
		return createXml(rootElementName, null);
	}

	/**
	 * [创建XML文档](Create XML document)
	 * @description zh - 创建XML文档
	 * @description en - Create XML document
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:57:09
	 * @param rootElementName 根节点名称
	 * @param namespace 命名空间，无则传null
	 * @return org.w3c.dom.Document
	 */
	public static Document createXml(String rootElementName, String namespace) {
		final Document doc = createXml();
		doc.appendChild(null == namespace ? doc.createElement(rootElementName) : doc.createElementNS(namespace, rootElementName));
		return doc;
	}

	// -------------------------------------------------------------------------------------- Function

	/**
	 * [获得XML文档根节点](Get XML document root node)
	 * @description zh - 获得XML文档根节点
	 * @description en - Get XML document root node
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:57:48
	 * @param doc Document
	 * @return org.w3c.dom.Element
	 */
	public static Element getRootElement(Document doc) {
		return (null == doc) ? null : doc.getDocumentElement();
	}

	/**
	 * [获取节点所在的Document](Get the document where the node is located)
	 * @description zh - 获取节点所在的Document
	 * @description en - Get the document where the node is located
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:59:30
	 * @param node 节点
	 * @return org.w3c.dom.Document
	 */
	public static Document getOwnerDocument(Node node) {
		return (node instanceof Document) ? (Document) node : node.getOwnerDocument();
	}

	/**
	 * [去除XML文本中的无效字符](Remove invalid characters from XML text)
	 * @description zh - 去除XML文本中的无效字符
	 * @description en - Remove invalid characters from XML text
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:00:09
	 * @param xmlContent XML文本
	 * @return java.lang.String
	 */
	public static String cleanInvalid(String xmlContent) {
		return xmlContent == null ?
			null :
			xmlContent.replaceAll(INVALID_REGEX, "");
	}

	/**
	 * [去除XML文本中的注释内容](Remove comments from XML text)
	 * @description zh - 去除XML文本中的注释内容
	 * @description en - Remove comments from XML text
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:00:50
	 * @param xmlContent XML文本
	 * @return java.lang.String
	 */
	public static String cleanComment(String xmlContent) {
		return xmlContent == null ?
			null :
			xmlContent.replaceAll(COMMENT_REGEX, Constant.EMPTY);
	}

	/**
	 * [根据节点名获得子节点列表](Obtain the list of child nodes according to the node name)
	 * @description zh - 根据节点名获得子节点列表
	 * @description en - Obtain the list of child nodes according to the node name
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:04:47
	 * @param element 节点
	 * @param tagName 节点名，如果节点名为空（null或blank），返回所有子节点
	 * @return java.util.List<org.w3c.dom.Element>
	 */
	public static List<Element> getElements(Element element, String tagName) {
		final NodeList nodeList = StrUtil.isBlank(tagName) ? element.getChildNodes() : element.getElementsByTagName(tagName);
		return transElements(element, nodeList);
	}

	/**
	 * [根据节点名获得第一个子节点](Get the first child node according to the node name)
	 * @description zh - 根据节点名获得第一个子节点
	 * @description en - Get the first child node according to the node name
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:06:29
	 * @param element 节点
	 * @param tagName 节点名
	 * @return org.w3c.dom.Element
	 */
	public static Element getElement(Element element, String tagName) {
		final NodeList nodeList = element.getElementsByTagName(tagName);
		if (nodeList == null || nodeList.getLength() < Constant.ONE) {
			return null;
		}
		int length = nodeList.getLength();
		for (int i = Constant.ZERO; i < length; i++) {
			Element childEle = (Element) nodeList.item(i);
			if (childEle == null || childEle.getParentNode() == element) {
				return childEle;
			}
		}
		return null;
	}

	/**
	 * [根据节点名获得第一个子节点](Get the first child node according to the node name)
	 * @description zh - 根据节点名获得第一个子节点
	 * @description en - Get the first child node according to the node name
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:07:12
	 * @param element 节点
	 * @param tagName 节点名
	 * @return java.lang.String
	 */
	public static String elementText(Element element, String tagName) {
		Element child = getElement(element, tagName);
		return child == null ? null : child.getTextContent();
	}

	/**
	 * [根据节点名获得第一个子节点](Get the first child node according to the node name)
	 * @description zh - 根据节点名获得第一个子节点
	 * @description en - Get the first child node according to the node name
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:07:49
	 * @param element 节点
	 * @param tagName 节点名
	 * @param defaultValue 默认值
	 * @return java.lang.String
	 */
	public static String elementText(Element element, String tagName, String defaultValue) {
		Element child = getElement(element, tagName);
		return child == null ? defaultValue : child.getTextContent();
	}

	/**
	 * [将NodeList转换为Element列表](Convert NodeList to element list)
	 * @description zh - 将NodeList转换为Element列表
	 * @description en - Convert NodeList to element list
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:08:35
	 * @param nodeList NodeList
	 * @return java.util.List<org.w3c.dom.Element>
	 */
	public static List<Element> transElements(NodeList nodeList) {
		return transElements(null, nodeList);
	}

	/**
	 * [将NodeList转换为Element列表](Convert NodeList to element list)
	 * @description zh - 将NodeList转换为Element列表
	 * @description en - Convert NodeList to element list
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:09:12
	 * @param parentEle 父节点，如果指定将返回此节点的所有直接子节点，null返回所有就节点
	 * @param nodeList  NodeList
	 * @return java.util.List<org.w3c.dom.Element>
	 */
	public static List<Element> transElements(Element parentEle, NodeList nodeList) {
		int length = nodeList.getLength();
		final ArrayList<Element> elements = new ArrayList<>(length);
		Node node;
		Element element;
		for (int i = Constant.ZERO; i < length; i++) {
			node = nodeList.item(i);
			if (Node.ELEMENT_NODE == node.getNodeType()) {
				element = (Element) nodeList.item(i);
				if (parentEle == null || element.getParentNode() == parentEle) {
					elements.add(element);
				}
			}
		}

		return elements;
	}

	/**
	 * [将可序列化的对象转换为XML写入文件](Converts serializable objects to XML and writes them to a file)
	 * @description zh - 将可序列化的对象转换为XML写入文件
	 * @description en - Converts serializable objects to XML and writes them to a file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:09:45
	 * @param dest 目标文件
	 * @param bean 对象
	 */
	public static void writeObjectAsXml(File dest, Object bean) {
		XMLEncoder xmlenc = null;
		try {
			xmlenc = new XMLEncoder(FileUtil.getOutputStream(dest));
			xmlenc.writeObject(bean);
		} finally {
			IoUtil.close(xmlenc);
		}
	}

	/**
	 * [创建XPath](Create XPath)
	 * @description zh - 创建XPath
	 * @description en - Create XPath
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:15:56
	 * @return javax.xml.xpath.XPath
	 */
	public static XPath createXPath() {
		return XPathFactory.newInstance().newXPath();
	}

	/**
	 * [通过XPath方式读取XML节点等信息](Read XML nodes and other information through XPath)
	 * @description zh - 通过XPath方式读取XML节点等信息
	 * @description en - Read XML nodes and other information through XPath
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:16:32
	 * @param expression XPath表达式
	 * @param source 资源，可以是Docunent、Node节点等
	 * @return org.w3c.dom.Element
	 */
	public static Element getElementByXPath(String expression, Object source) {
		return (Element) getNodeByXPath(expression, source);
	}

	/**
	 * [通过XPath方式读取XML的NodeList](Read the NodeList of XML through XPath)
	 * @description zh - 通过XPath方式读取XML的NodeList
	 * @description en - Read the NodeList of XML through XPath
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:17:07
	 * @param expression XPath表达式
	 * @param source 资源，可以是Docunent、Node节点等
	 * @return org.w3c.dom.NodeList
	 */
	public static NodeList getNodeListByXPath(String expression, Object source) {
		return (NodeList) getByXPath(expression, source, XPathConstants.NODESET);
	}

	/**
	 * [通过XPath方式读取XML的NodeList](Read the NodeList of XML through XPath)
	 * @description zh - 通过XPath方式读取XML的NodeList
	 * @description en - Read the NodeList of XML through XPath
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:17:42
	 * @param expression XPath表达式
	 * @param source 资源，可以是Docunent、Node节点等
	 * @return 匹配返回类型的值
	 */
	public static Node getNodeByXPath(String expression, Object source) {
		return (Node) getByXPath(expression, source, XPathConstants.NODE);
	}

	/**
	 * [通过XPath方式读取XML节点等信息](Read XML nodes and other information through XPath)
	 * @description zh - 通过XPath方式读取XML节点等信息
	 * @description en - Read XML nodes and other information through XPath
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:18:52
	 * @param expression XPath表达式
	 * @param source 资源，可以是Docunent、Node节点等
	 * @param returnType 返回类型
	 * @return java.lang.Object
	 */
	public static Object getByXPath(String expression, Object source, QName returnType) {
		NamespaceContext nsContext = null;
		if (source instanceof Node) {
			nsContext = new UniversalNamespaceCache((Node) source, Constant.FALSE);
		}
		return getByXPath(expression, source, returnType, nsContext);
	}

	/**
	 * [通过XPath方式读取XML节点等信息](Read XML nodes and other information through XPath)
	 * @description zh - 通过XPath方式读取XML节点等信息
	 * @description en - Read XML nodes and other information through XPath
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:24:04
	 * @param expression XPath表达式
	 * @param source 资源，可以是Docunent、Node节点等
	 * @param returnType 返回类型
	 * @param nsContext NamespaceContext
	 */
	public static Object getByXPath(String expression, Object source, QName returnType, NamespaceContext nsContext) {
		final XPath xPath = createXPath();
		if (null != nsContext) {
			xPath.setNamespaceContext(nsContext);
		}
		try {
			if (source instanceof InputSource) {
				return xPath.evaluate(expression, (InputSource) source, returnType);
			} else {
				return xPath.evaluate(expression, source, returnType);
			}
		} catch (XPathExpressionException e) {
			throw new UtilException(e);
		}
	}

	/**
	 * [转义XML特殊字符](Escape XML special characters)
	 * @description zh - 转义XML特殊字符
	 * @description en - Escape XML special characters
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:24:48
	 * @param value 被替换的字符串
	 * @return java.lang.String
	 */
	public static String escape(String value) {
		return EscapeUtil.escape(value);
	}

	/**
	 * [反转义XML特殊字符](Anti escape XML special characters)
	 * @description zh - 反转义XML特殊字符
	 * @description en - Anti escape XML special characters
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:25:30
	 * @param value 被替换的字符串
	 * @return java.lang.String
	 */
	public static String unescape(String value) {
		return EscapeUtil.unescape(value);
	}

	/**
	 * [XML格式字符串转换为Map](Convert XML format string to map)
	 * @description zh - XML格式字符串转换为Map
	 * @description en - Convert XML format string to map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:26:10
	 * @param xmlStr XML字符串
	 * @return java.util.Map<java.lang.String, java.lang.String>
	 */
	public static Map<String, Object> xmlToMap(String xmlStr) {
		return xmlToMap(xmlStr, new HashMap<>());
	}

	/**
	 * [XML转Java Bean](XML to Java Bean)
	 * @description zh - XML转Java Bean
	 * @description en - XML to Java Bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:27:19
	 * @param node XML节点
	 * @param bean bean类
	 * @return T
	 */
	public static <T> T xmlToBean(Node node, Class<T> bean) {
		final Map<String, Object> map = xmlToMap(node);
		return null != map && map.size() == Constant.ONE ?
			BeanUtil.toBean(map.get(bean.getSimpleName()), bean) :
				BeanUtil.toBean(map, bean);
	}

	/**
	 * [XML格式字符串转换为Map](Convert XML format string to map)
	 * @description zh - XML格式字符串转换为Map
	 * @description en - Convert XML format string to map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:27:53
	 * @param node XML节点
	 * @return java.util.Map<java.lang.String, java.lang.Object>
	 */
	public static Map<String, Object> xmlToMap(Node node) {
		return xmlToMap(node, new HashMap<>());
	}

	/**
	 * [XML格式字符串转换为Map](Convert XML format string to map)
	 * @description zh - XML格式字符串转换为Map
	 * @description en - Convert XML format string to map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:32:19
	 * @param xmlStr XML字符串
	 * @param result 结果Map类型
	 * @return java.util.Map<java.lang.String, java.lang.Object>
	 */
	public static Map<String, Object> xmlToMap(String xmlStr, Map<String, Object> result) {
		final Document doc = parseXml(xmlStr);
		final Element root = getRootElement(doc);
		root.normalize();

		return xmlToMap(root, result);
	}

	/**
	 * [XML节点转换为Map](Convert XML node to map)
	 * @description zh - XML节点转换为Map
	 * @description en - Convert XML node to map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:33:02
	 * @param node XML节点
	 * @param result 结果Map类型
	 * @return XML数据转换后的Map
	 * @return java.util.Map<java.lang.String, java.lang.Object>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> xmlToMap(Node node, Map<String, Object> result) {
		if (null == result) {
			result = new HashMap<>();
		}
		final NodeList nodeList = node.getChildNodes();
		final int length = nodeList.getLength();
		Node childNode;
		Element childEle;
		for (int i = Constant.ZERO; i < length; ++i) {
			childNode = nodeList.item(i);
			if (Constant.FALSE == isElement(childNode)) {
				continue;
			}

			childEle = (Element) childNode;
			final Object value = result.get(childEle.getNodeName());
			Object newValue;
			if (childEle.hasChildNodes()) {
				final Map<String, Object> map = xmlToMap(childEle);
				if (MapUtil.isNotEmpty(map)) {
					newValue = map;
				} else {
					newValue = childEle.getTextContent();
				}
			} else {
				newValue = childEle.getTextContent();
			}


			if (null != newValue) {
				if (null != value) {
					if (value instanceof List) {
						((List<Object>) value).add(newValue);
					} else {
						result.put(childEle.getNodeName(), CollUtil.newArrayList(value, newValue));
					}
				} else {
					result.put(childEle.getNodeName(), newValue);
				}
			}
		}
		return result;
	}

	/**
	 * [将Map转换为XML格式的字符串](Converts a map to a string in XML format)
	 * @description zh - 将Map转换为XML格式的字符串
	 * @description en - Converts a map to a string in XML format
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:33:41
	 * @param data Map类型数据
	 * @return java.lang.String
	 */
	public static String mapToXmlStr(Map<?, ?> data) {
		return toStr(mapToXml(data, "xml"));
	}

	/**
	 * [将Map转换为XML格式的字符串](Converts a map to a string in XML format)
	 * @description zh - 将Map转换为XML格式的字符串
	 * @description en - Converts a map to a string in XML format
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:34:19
	 * @param data Map类型数据
	 * @param omitXmlDeclaration 是否输出 xml Declaration
	 * @return java.lang.String
	 */
	public static String mapToXmlStr(Map<?, ?> data, boolean omitXmlDeclaration) {
		return toStr(mapToXml(data, "xml"), CharsetUtil.UTF_8, false, omitXmlDeclaration);
	}

	/**
	 * [将Map转换为XML格式的字符串](Converts a map to a string in XML format)
	 * @description zh - 将Map转换为XML格式的字符串
	 * @description en - Converts a map to a string in XML format
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:34:50
	 * @param data Map类型数据
	 * @param rootName 根节点名
	 * @return java.lang.String
	 */
	public static String mapToXmlStr(Map<?, ?> data, String rootName) {
		return toStr(mapToXml(data, rootName));
	}

	/**
	 * [将Map转换为XML格式的字符串](Converts a map to a string in XML format)
	 * @description zh - 将Map转换为XML格式的字符串
	 * @description en - Converts a map to a string in XML format
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:38:13
	 * @param data Map类型数据
	 * @param rootName 根节点名
	 * @param namespace 命名空间，可以为null
	 * @return java.lang.String
	 */
	public static String mapToXmlStr(Map<?, ?> data, String rootName, String namespace) {
		return toStr(mapToXml(data, rootName, namespace));
	}

	/**
	 * [将Map转换为XML格式的字符串](Converts a map to a string in XML format)
	 * @description zh - 将Map转换为XML格式的字符串
	 * @description en - Converts a map to a string in XML format
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:38:51
	 * @param data Map类型数据
	 * @param rootName 根节点名
	 * @param namespace 命名空间，可以为null
	 * @param omitXmlDeclaration 是否输出 xml Declaration
	 * @return java.lang.String
	 */
	public static String mapToXmlStr(Map<?, ?> data, String rootName, String namespace, boolean omitXmlDeclaration) {
		return toStr(mapToXml(data, rootName, namespace), CharsetUtil.UTF_8, false, omitXmlDeclaration);
	}

	/**
	 * [将Map转换为XML格式的字符串](Converts a map to a string in XML format)
	 * @description zh - 将Map转换为XML格式的字符串
	 * @description en - Converts a map to a string in XML format
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:39:33
	 * @param data Map类型数据
	 * @param rootName 根节点名
	 * @param namespace 命名空间，可以为null
	 * @param isPretty 是否格式化输出
	 * @param omitXmlDeclaration 是否输出 xml Declaration
	 * @return java.lang.String
	 */
	public static String mapToXmlStr(Map<?, ?> data, String rootName, String namespace, boolean isPretty, boolean omitXmlDeclaration) {
		return toStr(mapToXml(data, rootName, namespace), CharsetUtil.UTF_8, isPretty);
	}

	/**
	 * [将Map转换为XML格式的字符串](Converts a map to a string in XML format)
	 * @description zh - 将Map转换为XML格式的字符串
	 * @description en - Converts a map to a string in XML format
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:37:19
	 * @param data Map类型数据
	 * @param rootName 根节点名
	 * @param namespace 命名空间，可以为null
	 * @param charset 编码
	 * @param isPretty 是否格式化输出
	 * @param omitXmlDeclaration 是否输出 xml Declaration
	 * @return java.lang.String
	 */
	public static String mapToXmlStr(Map<?, ?> data, String rootName, String namespace, String charset, boolean isPretty, boolean omitXmlDeclaration) {
		return toStr(mapToXml(data, rootName, namespace), charset, isPretty, omitXmlDeclaration);
	}

	/**
	 * [将Map转换为XML](Convert map to XML)
	 * @description zh - 将Map转换为XML
	 * @description en - Convert map to XML
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:36:48
	 * @param data Map类型数据
	 * @param rootName 根节点名
	 * @return org.w3c.dom.Document
	 */
	public static Document mapToXml(Map<?, ?> data, String rootName) {
		return mapToXml(data, rootName, null);
	}

	/**
	 * [将Map转换为XML](Convert map to XML)
	 * @description zh - 将Map转换为XML
	 * @description en - Convert map to XML
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:36:08
	 * @param data Map类型数据
	 * @param rootName 根节点名
	 * @param namespace 命名空间，可以为null
	 * @return org.w3c.dom.Document
	 */
	public static Document mapToXml(Map<?, ?> data, String rootName, String namespace) {
		final Document doc = createXml();
		final Element root = appendChild(doc, rootName, namespace);

		appendMap(doc, root, data);
		return doc;
	}

	/**
	 * [将Bean转换为XML](Convert bean to XML)
	 * @description zh - 将Bean转换为XML
	 * @description en - Convert bean to XML
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:35:37
	 * @param bean Bean对象
	 * @return org.w3c.dom.Document
	 */
	public static Document beanToXml(Object bean) {
		return beanToXml(bean, null);
	}

	/**
	 * [将Bean转换为XML](Convert bean to XML)
	 * @description zh - 将Bean转换为XML
	 * @description en - Convert bean to XML
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:31:25
	 * @param bean Bean对象
	 * @param namespace 命名空间
	 * @return org.w3c.dom.Document
	 */
	public static Document beanToXml(Object bean, String namespace) {
		return null == bean ? null : mapToXml(BeanUtil.beanToMap(bean), bean.getClass().getSimpleName(), namespace);
	}

	/**
	 * [给定节点是否为 Element 类型节点](Whether the given node is an element type node)
	 * @description zh - 给定节点是否为 Element 类型节点
	 * @description en - Whether the given node is an element type node
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:30:27
	 * @param node 节点
	 * @return boolean
	 */
	public static boolean isElement(Node node) {
		return (null != node) && Node.ELEMENT_NODE == node.getNodeType();
	}

	/**
	 * [在已有节点上创建子节点](Create child nodes on existing nodes)
	 * @description zh - 在已有节点上创建子节点
	 * @description en - Create child nodes on existing nodes
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:29:52
	 * @param node 节点
	 * @param tagName 标签名
	 * @return org.w3c.dom.Element
	 */
	public static Element appendChild(Node node, String tagName) {
		return appendChild(node, tagName, null);
	}

	/**
	 * [在已有节点上创建子节点](Create child nodes on existing nodes)
	 * @description zh - 在已有节点上创建子节点
	 * @description en - Create child nodes on existing nodes
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:29:14
	 * @param node 节点
	 * @param tagName 标签名
	 * @param namespace 命名空间
	 * @return org.w3c.dom.Element
	 */
	public static Element appendChild(Node node, String tagName, String namespace) {
		final Document doc = getOwnerDocument(node);
		final Element child = (null == namespace) ? doc.createElement(tagName) : doc.createElementNS(namespace, tagName);
		node.appendChild(child);
		return child;
	}

	/**
	 * [创建文本子节点](Create text child node)
	 * @description zh - 创建文本子节点
	 * @description en - Create text child node
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:28:40
	 * @param node 节点
	 * @param text 文本
	 * @return org.w3c.dom.Node
	 */
	public static Node appendText(Node node, CharSequence text) {
		return appendText(getOwnerDocument(node), node, text);
	}

	// ---------------------------------------------------------------------------------------- Private method start

	/**
	 * [追加数据子节点](Append data child node)
	 * @description zh - 追加数据子节点
	 * @description en - Append data child node
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:23:01
	 * @param doc Document
	 * @param node 节点
	 * @param data 数据
	 */
	@SuppressWarnings("rawtypes")
	private static void append(Document doc, Node node, Object data) {
		if (data instanceof Map) {
			appendMap(doc, node, (Map) data);
		} else if (data instanceof Iterator) {
			appendIterator(doc, node, (Iterator) data);
		} else if (data instanceof Iterable) {
			appendIterator(doc, node, ((Iterable) data).iterator());
		} else {
			appendText(doc, node, data.toString());
		}
	}

	/**
	 * [追加Map数据子节点](Append map data sub node)
	 * @description zh - 追加Map数据子节点
	 * @description en - Append map data sub node
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:22:25
	 * @param doc Document
	 * @param node 当前节点
	 * @param data Map类型数据
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	private static void appendMap(Document doc, Node node, Map data) {
		data.forEach((key, value) -> {
			if (null != key) {
				final Element child = appendChild(node, key.toString());
				if (null != value) {
					append(doc, child, value);
				}
			}
		});
	}

	/**
	 * [追加集合节点](Append collection node)
	 * @description zh - 追加集合节点
	 * @description en - Append collection node
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:21:48
	 * @param doc Document
	 * @param node 节点
	 * @param data 数据
	 */
	@SuppressWarnings("rawtypes")
	private static void appendIterator(Document doc, Node node, Iterator data) {
		final Node parentNode = node.getParentNode();
		boolean isFirst = Constant.TRUE;
		Object eleData;
		while (data.hasNext()) {
			eleData = data.next();
			if (isFirst) {
				append(doc, node, eleData);
				isFirst = Constant.FALSE;
			} else {
				final Node cloneNode = node.cloneNode(Constant.FALSE);
				parentNode.appendChild(cloneNode);
				append(doc, cloneNode, eleData);
			}
		}
	}

	/**
	 * [追加文本节点](Append text node)
	 * @description zh - 追加文本节点
	 * @description en - Append text node
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:20:58
	 * @param doc Document
	 * @param node 节点
	 * @param text 文本内容
	 * @return org.w3c.dom.Node
	 */
	private static Node appendText(Document doc, Node node, CharSequence text) {
		return node.appendChild(doc.createTextNode(StrUtil.str(text)));
	}

	/**
	 * [关闭XXE](Close xxE)
	 * @description zh - 关闭XXE
	 * @description en - Close xxE
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:20:19
	 * @param dbf DocumentBuilderFactory
	 * @return javax.xml.parsers.DocumentBuilderFactory
	 */
	private static DocumentBuilderFactory disableXXE(DocumentBuilderFactory dbf) {
		String feature;
		try {
			feature = "http://apache.org/xml/features/disallow-doctype-decl";
			dbf.setFeature(feature, true);
			feature = "http://xml.org/sax/features/external-general-entities";
			dbf.setFeature(feature, false);
			feature = "http://xml.org/sax/features/external-parameter-entities";
			dbf.setFeature(feature, false);
			feature = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
			dbf.setFeature(feature, false);
			dbf.setXIncludeAware(false);
			dbf.setExpandEntityReferences(false);
		} catch (ParserConfigurationException e) {}
		return dbf;
	}

	/**
	 * [全局命名空间上下文](Global namespace context)
	 * @description zh - 全局命名空间上下文
	 * @description en - Global namespace context
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:19:52
	 */
	public static class UniversalNamespaceCache implements NamespaceContext {
		private static final String DEFAULT_NS = "DEFAULT";
		private final BiMap<String, String> prefixUri = new BiMap<>(new HashMap<>());

		public UniversalNamespaceCache(Node node, boolean toplevelOnly) {
			examineNode(node.getFirstChild(), toplevelOnly);
		}

		private void examineNode(Node node, boolean attributesOnly) {
			final NamedNodeMap attributes = node.getAttributes();
			if (null != attributes) {
				for (int i = Constant.ZERO; i < attributes.getLength(); i++) {
					Node attribute = attributes.item(i);
					storeAttribute(attribute);
				}
			}

			if (Constant.FALSE == attributesOnly) {
				final NodeList childNodes = node.getChildNodes();
				if (null != childNodes) {
					Node item;
					for (int i = Constant.ZERO; i < childNodes.getLength(); i++) {
						item = childNodes.item(i);
						if (item.getNodeType() == Node.ELEMENT_NODE)
							examineNode(item, Constant.FALSE);
					}
				}
			}
		}

		private void storeAttribute(Node attribute) {
			if (null == attribute) {
				return;
			}
			if (XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(attribute.getNamespaceURI())) {
				if (XMLConstants.XMLNS_ATTRIBUTE.equals(attribute.getNodeName())) {
					prefixUri.put(DEFAULT_NS, attribute.getNodeValue());
				} else {
					prefixUri.put(attribute.getLocalName(), attribute.getNodeValue());
				}
			}

		}

		@Override
		public String getNamespaceURI(String prefix) {
			if (prefix == null || prefix.equals(XMLConstants.DEFAULT_NS_PREFIX)) {
				return prefixUri.get(DEFAULT_NS);
			} else {
				return prefixUri.get(prefix);
			}
		}

		@Override
		public String getPrefix(String namespaceURI) {
			return prefixUri.getInverse().get(namespaceURI);
		}

		@Override
		public Iterator<String> getPrefixes(String namespaceURI) {
			return null;
		}

	}

}
