package com.chia.multienty.wechat.thirdparty.sdk.tools;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.*;

public class XmlKit {
    @SneakyThrows
    public static String toXml(Object object) {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        ByteArrayOutputStream op = new ByteArrayOutputStream();
        StringWriter writer = new StringWriter();
        Marshaller marshaller = context.createMarshaller();
        // 格式化xml
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // Marshaller.JAXB_ encoding  xml的编码方式
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        // 去掉生成xml的默认报文头
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        //CData处理
        marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
            public void escape(char[] ch, int start,int length, boolean isAttVal, Writer writer) throws IOException {
                writer.write(ch, start, length);
            }
        });
        marshaller.marshal(object, writer);
        return writer.toString()
                .replace("<\\?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"\\?>","");
    }

    @SneakyThrows
    public static <T> T parse(String xml, Class<T> clazz) {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        XMLStreamReader xmlStreamReader = XMLInputFactory.newFactory().createXMLStreamReader(reader);
        JAXBElement<T> unmarshal = unmarshaller.unmarshal(xmlStreamReader, clazz);
        return unmarshal.getValue();
    }

    @SneakyThrows
    public static String getInfoType(String decryptedMsg) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(decryptedMsg);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);

        Element root = document.getDocumentElement();
        NodeList nodeListInfoType = root.getElementsByTagName("InfoType");
        if(nodeListInfoType.getLength() > 0) {
            return nodeListInfoType.item(0).getTextContent();
        }
        NodeList nodeListEvent = root.getElementsByTagName("Event");
        if(nodeListEvent.getLength() > 0) {
            return nodeListEvent.item(0).getTextContent();
        }
        return "NONE";
    }

    @SneakyThrows
    public static String getMsgType(String decryptedMsg) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(decryptedMsg);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);

        Element root = document.getDocumentElement();
        NodeList nodeListInfoType = root.getElementsByTagName("MsgType");
        if(nodeListInfoType.getLength() > 0) {
            return nodeListInfoType.item(0).getTextContent();
        }
        return "NONE";
    }

    @SneakyThrows
    public static String getSpec(String decryptedMsg, String nodeName) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(decryptedMsg);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);
        Element root = document.getDocumentElement();
        NodeList nodeListInfoType = root.getElementsByTagName(nodeName);
        return nodeListInfoType.getLength() > 0 ? nodeListInfoType.item(0).getTextContent() : null;
    }
}
