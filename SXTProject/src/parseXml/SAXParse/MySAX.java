package parseXml.SAXParse;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 此处理器类适用于读取完整的xml文件并输出,和IO不同的是，此类是以属性为单位进行读取
 */

public class MySAX extends DefaultHandler {

    /**
     * 开始处理文档
     *
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.print("<");
        System.out.print(qName);  //输出元素名称
        if (attributes != null) {   //取得全部的属性
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.print(" " + attributes.getQName(i) + "=\"" + attributes.getValue(i) + "\" ");
            }
        }
        System.out.print(">");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print("</");
        System.out.print(qName);
        System.out.print(">");
    }

    /**
     * 结束处理文档
     *
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        System.out.println("\n文档处理结束。。");
    }
}
