package parseXml.DomParse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * DOM方式解析dom_demo_01.xml，得到name 和value的值
 */

public class DOMDemoFor01 {
    public static void main(String[] args) {
        //1、建立DocumentBuilderFactory，以用于取得DocumentBuilder
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //2、通过DocumentBuilderFactory取得DocumentBuilder
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        //3、定义Document接口对象，通过DocumentBuilder类进行DOM树的转换操作
        Document doc = null;
        try {
            doc = documentBuilder.parse("parseXml/DomParse/dom_demo_01.xml");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //4、查找linkman的节点  此时有多个节点，所以下面要用循环来一一获取
        NodeList linkman = doc.getElementsByTagName("linkman");
        for (int i = 0; i < linkman.getLength(); i++) {
            Element element = (Element) linkman.item(i);
            System.out.println("姓名：" + element.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
            System.out.println("邮箱：" + element.getElementsByTagName("email").item(0).getFirstChild().getNodeValue());
        }
    }
}
