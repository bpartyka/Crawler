import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UrlCollector {
    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";


    public static Set<String> collectUrlWithGivenName(String searchTerm, int num) throws IOException {

        String searchURL = GOOGLE_SEARCH_URL + "?q=" + searchTerm + "&num=" + num;
        Document doc = Jsoup.connect(searchURL).userAgent("Chrome").get();

        Elements results = doc.select("a[href]");

        return UrlCollector.parseUrl(results);
    }


    public static Set<String> parseUrl(Elements results) {
        Set<String> urlSet = new HashSet<String>();
        for (Element result : results) {
            String linkHref = result.attr("href");
            if (linkHref.contains("url?q=https") && !linkHref.contains("google")) {
                urlSet.add(linkHref.substring(7, linkHref.indexOf("&")));
            }
        }

        return urlSet;
    }
}
