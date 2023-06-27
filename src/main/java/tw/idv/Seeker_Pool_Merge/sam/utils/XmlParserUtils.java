package tw.idv.Seeker_Pool_Merge.sam.utils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlParserUtils {

    public static <T> List<T> parse(String file, Class<T> targetClass){
        ArrayList<T> list = new ArrayList<T>();
        try{
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(file));
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements("emp");

            for(Element element : elements){
                String name = element.element("name").getText();
                String age = element.element("age").getText();
                String image = element.element("image").getText();
                String gender = element.element("gender").getText();
                String job = element.element("job").getText();

                Constructor<T> constructor = targetClass.getDeclaredConstructor(String.class, Integer.class, String.class, String.class, String.class);
                constructor.setAccessible(true);
                T object = constructor.newInstance(name, Integer.parseInt(age), image,gender,job);

                list.add(object);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
