import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LiczenieUnikatowychLiczb {
     {
         Scanner scanner = new Scanner(System.in);
         Set<Integer> liczby = new HashSet<>();

         System.out.println("Podaj liczby całkowite (wprowadź 0, aby zakończyć):");

         int liczba = scanner.nextInt();
         while (liczba != 0) {
             liczby.add(liczba);
             liczba = scanner.nextInt();
         }

         System.out.println("Liczba unikatowych wartości: " + liczby.size());
     }
}