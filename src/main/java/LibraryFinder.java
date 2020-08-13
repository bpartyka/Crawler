import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LibraryFinder {

    public static List<List<String>> manageUrlSet(Set<String> set) throws IOException {
        List<List<String>> libaryList = new LinkedList<>();

        for (String element :
                set) {
            libaryList.add(findLibrary(element));

        }
        return libaryList;
    }

    public static List<String> findLibrary(String url) throws IOException {

        Document doc = Jsoup.connect(url).userAgent("Chrome").get();


        Elements results = doc.select("script");


        return urlParser(results);
    }

    public static List<String> urlParser(Elements elements) {
        List<String> urlSet = new LinkedList<>();
        for (Element result : elements) {
            String linkHref = result.attr("src");

            if (linkHref.endsWith(".js")) {
                urlSet.add(linkHref);
            }
        }
        return urlSet;

    }

}
