import java.io.IOException;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the search term.");
        String searchTerm = scanner.nextLine();
        System.out.println("Please enter the number of results. Example: 5 10 20");
        int num = scanner.nextInt();
        scanner.close();

        Set<String> set= UrlCollector.collectUrlWithGivenName(searchTerm,num);

       List<List<String>> list= LibraryFinder.manageUrlSet(set);

       System.out.println(list.toString());


    }
}
