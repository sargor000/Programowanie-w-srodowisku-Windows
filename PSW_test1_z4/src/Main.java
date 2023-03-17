import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        // Wczytanie zbiorów od użytkownika
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj elementy zbioru A oddzielone spacjami: ");
        String pobierzZbiorA = scanner.nextLine();
        Set<String> zbiorA = new HashSet<>(Arrays.asList(pobierzZbiorA.split(" ")));

        System.out.print("Podaj elementy zbioru B oddzielone spacjami: ");
        String pobierzzbiorB = scanner.nextLine();
        Set<String> zbiorB = new HashSet<>(Arrays.asList(pobierzzbiorB.split(" ")));

        // Wykonanie operacji na zbiorach
        // Suma
        Set<String> suma = new HashSet<>(zbiorA);
        suma.addAll(zbiorB);
        //Różnica
        Set<String> roznica = new HashSet<>(zbiorA);
        roznica.removeAll(zbiorB);
        //Cześć wspólna zbiorów
        Set<String> czesc_wspolna = new HashSet<>(zbiorA);
        czesc_wspolna.retainAll(zbiorB);

        //Różnica symetryczna zbiorów
        Set<String> roznica_symetryczna = new HashSet<>(zbiorA);
        roznica_symetryczna.addAll(zbiorB);

        Set<String> tmp = new HashSet<>(zbiorA);
        tmp.retainAll(zbiorB);
        roznica_symetryczna.removeAll(tmp);

        // Wyświetlenie wyniku
        //Suma
        System.out.println("Suma zbiorów: " + suma);
        //Różnica
        System.out.println("Różnica zbiorów: " + roznica);
        //Cześć wspólna zbiorów
        System.out.println("Cześć wspólna zbiorów: " + czesc_wspolna);
        //Różnica symetryczna zbiorów
        System.out.println("Różnica symetryczna zbiorów: " + roznica_symetryczna);

    }
}

