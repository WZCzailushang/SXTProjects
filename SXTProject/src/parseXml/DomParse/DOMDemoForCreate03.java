package parseXml.DomParse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 *生成xml文件：dom_demo_03.xml
 */

public class DOMDemoForCreate03 {
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
        doc = documentBuilder.newDocument();//创建一个新的文档

        //4、建立各个操作节点
        Element addresslist = doc.createElement("addresslist"); //建立节点
        Element linkman = doc.createElement("linkman");//建立节点
        Element name = doc.createElement("name");//建立节点
        Element email = doc.createElement("email");//建立节点

        //5、设置节点的文本内容，即为每一个节点添加文本节点
        name.appendChild(doc.createTextNode("王中春"));//设置文本
        email.appendChild(doc.createTextNode("775897041@qq.com"));//设置文本

        //6、设置节点关系
        linkman.appendChild(name);//子节点
        linkman.appendChild(email);//子节点
        addresslist.appendChild(linkman);//子节点
        doc.appendChild(addresslist);//文档保存节点

        //7、输出文档到文件中
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer t = null;
        try {
            t = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

        t.setOutputProperty(OutputKeys.ENCODING, "GBK");//设置编码
        DOMSource source = new DOMSource(doc);//输出文档
        StreamResult streamResult = new StreamResult(new File("F:\\ideaProjects\\SXTProjects\\src\\parseXml\\DomParse\\dom_demo_03.xml"));
        try {
            t.transform(source, streamResult);   //输出
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }
}
