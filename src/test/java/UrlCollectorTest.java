import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class UrlCollectorTest {

    @Test
    public void shouldReturnNothing() {

        Attributes attributes = null;
        Element element = new Element(Tag.valueOf("dd"), "ff", attributes);

        Elements elements = new Elements();
        elements.add(element);
        Assertions.assertEquals(new HashSet<String>(), UrlCollector.parseUrl(elements));
    }

    @Test
    public void shouldReturnUrl() {

        Attribute attribute = new Attribute("href", "/url?q=https://pl.wikipedia.org/wiki/ABS_(motoryzacja)&amp;sa=U&amp;ved=2ahUKEwj76MyEgq7oAhVtk4sKHXbmCqgQmhMwCHoECAYQDg&amp;usg=AOvVaw1fsv6MleLY9BOwNNrS-kI1\"><span class=\"XLloXe AP7Wnd\">Wikipedia</span>");

        List<Attribute> list = new LinkedList<>();
        list.add(attribute);

        Elements elements = createElements(list);


        Set<String> result = new HashSet<>();
        result.add("https://pl.wikipedia.org/wiki/ABS_(motoryzacja)");
        Assertions.assertEquals(result, UrlCollector.parseUrl(elements));
    }

    @Test
    public void shouldReturnUrl2() {

        Attribute attribute = new Attribute("href", "/url?q=https://pl.wikipedia.org/wiki/ABS_(motoryzacja)&amp;sa=U&amp;ved=2ahUKEwj76MyEgq7oAhVtk4sKHXbmCqgQmhMwCHoECAYQDg&amp;usg=AOvVaw1fsv6MleLY9BOwNNrS-kI1\"><span class=\"XLloXe AP7Wnd\">Wikipedia</span>");
        Attribute attribute2 = new Attribute("href", "/url?q=http://pl.wikipedia.org/wiki/ABS_(motoryzacja)&amp;sa=U&amp;ved=2ahUKEwj76MyEgq7oAhVtk4sKHXbmCqgQmhMwCHoECAYQDg&amp;usg=AOvVaw1fsv6MleLY9BOwNNrS-kI1\"><span class=\"XLloXe AP7Wnd\">Wikipedia</span>");
        Attribute attribute3 = new Attribute("href", "/url?q=https://pl.wikipedia.org/wiki/ABS_(motoryzacja)&amp;sa=U&amp;ved=2ahUKEwj76MyEgq7oAhVtk4sKHXbmCqgQmhMwCHoECAYQDg&amp;usg=AOvVaw1fsv6MleLY9BOwNNrS-kI1\"><span class=\"XLloXe AP7Wnd\">Wikipedia</span>");

        List<Attribute> list = new LinkedList<>();
        list.add(attribute);
        list.add(attribute2);
        list.add(attribute3);

        Elements elements = createElements(list);

        Set<String> result = new HashSet<>();
        result.add("https://pl.wikipedia.org/wiki/ABS_(motoryzacja)");
        Assertions.assertEquals(result, UrlCollector.parseUrl(elements));
    }

    @Test
    public void shouldReturnUrl3() {
        Attribute attribute = new Attribute("href", "/url?q=https://pl.wikipedia.org/wiki/ABSs_(motoryzacja)&amp;sa=U&amp;ved=2ahUKEwj76MyEgq7oAhVtk4sKHXbmCqgQmhMwCHoECAYQDg&amp;usg=AOvVaw1fsv6MleLY9BOwNNrS-kI1\"><span class=\"XLloXe AP7Wnd\">Wikipedia</span>");
        Attribute attribute2 = new Attribute("href", "/url?q=https://pl.wikipedia.org/wiki/ABS_(motoryzacja)&amp;sa=U&amp;ved=2ahUKEwj76MyEgq7oAhVtk4sKHXbmCqgQmhMwCHoECAYQDg&amp;usg=AOvVaw1fsv6MleLY9BOwNNrS-kI1\"><span class=\"XLloXe AP7Wnd\">Wikipedia</span>");

        List<Attribute> list = new LinkedList<>();
        list.add(attribute);
        list.add(attribute2);


        Elements elements = createElements(list);


        Set<String> result = new HashSet<>();
        result.add("https://pl.wikipedia.org/wiki/ABS_(motoryzacja)");
        result.add("https://pl.wikipedia.org/wiki/ABSs_(motoryzacja)");


        Assertions.assertEquals(result, UrlCollector.parseUrl(elements));
    }

    public Elements createElements(List<Attribute> attributeList) {
        Elements elements = new Elements();

        for (Attribute attribute : attributeList) {

            Attributes attributes = new Attributes();

            attributes.put(attribute);

            Element element = new Element(Tag.valueOf(attribute.getKey()), attribute.html(), attributes);

            elements.add(element);

        }

        return elements;
    }

}
