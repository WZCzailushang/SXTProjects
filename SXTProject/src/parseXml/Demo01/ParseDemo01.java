package parseXml.Demo01;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/**
 * SAX解析方式解析xml
 * <p>
 * 用来解析并存储Person.xml中的person的name和age属性
 */

public class ParseDemo01 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        //获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();

        //从解析工厂中获得解析器
        SAXParser saxParser = factory.newSAXParser();


        //编写处理器
        PersonHandler personHandler = new PersonHandler();

        /**
         * 加载文档Documents,注册处理器
         * 前者是获取资源路径，后者是处理器
         */
        saxParser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("parseXml/Demo01/person.xml"), personHandler);

        List<Person> persons = personHandler.getPersons();
        for (Person p : persons) {
            System.out.println("name:" + p.getName() + "--->" + "age" + p.getAge());
        }

    }

}
