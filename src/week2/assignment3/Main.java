package week2.assignment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Ödev:
 * Bu listedeki isimleri ekrana şu şekilde yazdır.
 * 1. Murat
 * 2. Ahmet
 * 3. Mehmet
 */

public class Main {
    public static void main(String[] args) {
        List<String> nameList = new ArrayList<String>(List.of("Murat", "Ahmet", "Mehmet"));
        int index = 1;
        for (String name : nameList) {
            System.out.printf("%d. %s\n", index, name);
            index++;
        }
    }
}
