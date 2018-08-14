package parseXml.DomParse;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * 简易版的DOM实例，用来解析dom_demo_02.xml。得到name 的值
 */
public class DOMDemoFor02 {
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
            doc = documentBuilder.parse("parseXml/DomParse/dom_demo_02.xml");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4、查找name的节点
        NodeList nl = doc.getElementsByTagName("name");
        //5、输出NodeList中第一个子节点中文本节点的内容
        //nl.item(0).getFirstChild().getNodeValue():取得name节点下的第一个子节点的第一个文本节点
        System.out.println("姓名：" + nl.item(0).getFirstChild().getNodeValue());
    }
}
