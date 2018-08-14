package parseXml.Demo01;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import parseXml.Demo01.Person;

import java.util.ArrayList;
import java.util.List;


/**
 * 处理类 PerosnHandler 用于解读xml文件中的属性，并将其以List的方式返回
 */

public class PersonHandler extends DefaultHandler {

    private List<Person> persons;
    private Person person;
    private String tag;//标签  用于记录处理的属性名

    @Override
    public void startDocument() throws SAXException {
        System.out.println("处理文档开始");
        persons = new ArrayList<Person>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("开始一个元素：" + qName);
        if (qName != null) {
            tag = qName;
        }
        if (qName != null && qName.equals("name")) {
            person = new Person();

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length);
        if (null != tag && tag.equals("name")) {
            person.setName(str);

        } else if (null != tag && tag.equals("age")) {
            Integer age = Integer.valueOf(str);
            person.setAge(age);

        }
        System.out.println(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("person")) {
            this.persons.add(person);
        }
        tag = null;
        System.out.println("结束一个元素：" + qName);
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("文档处理结束");
    }

    public List<Person> getPersons() {
        return persons;
    }
}
