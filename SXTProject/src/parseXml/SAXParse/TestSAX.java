package parseXml.SAXParse;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class TestSAX {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //1、建立SAX解析工厂
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        //2、构造解析器
        SAXParser saxParser = saxParserFactory.newSAXParser();

        //3、解析XML中使用HANDLER

        saxParser.parse("parseXml/DomParse/dom_demo_03.xml", new MySAX());


    }
}
