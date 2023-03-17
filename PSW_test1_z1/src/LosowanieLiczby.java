import java.util.Random;
import java.util.Scanner;
public class LosowanieLiczby {
    public int wylosujLiczbe()
        {
            Random generator = new Random();
            int wylosowanaLiczba = generator.nextInt(10) + 1;
            //System.out.println("Wylosowana liczba: " + wylosowanaLiczba);
            return wylosowanaLiczba;
        }
    public int pobierzLiczbe()
        {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Podaj liczbę całkowitą: ");
            int liczba = scanner.nextInt();
            System.out.println("Podana liczba to: " + liczba);
            return liczba;
        }
}
