import java.util.*;
import java.util.random.RandomGenerator;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("LABORATORIUM 2  ZADANIE 2");
        Scanner liczba = new Scanner(System.in); // nowy obiekt liczba

        System.out.print("Ile elementów ma mieć tablica: ");
        int liczbaElementow = liczba.nextInt(); // pobierz liczbę od użytkowanika

        int[] tablica = new int[liczbaElementow];

        double[] oceny = { 2, 3, 3.5, 4, 4.5, 5 };
        //Random random = new Random();
        //RandomGenerator rand = null;

        for (int i = 0; i < liczbaElementow; i++)
        {
            Random random = new Random();
            Random rand = new Random();
            int wartoscLosowa = rand.nextInt(oceny.length);
            tablica[i] = (int) oceny[wartoscLosowa];
        }
        System.out.println("Wylosowane liczby w tablicy: ");
        for (int i = 0; i < tablica.length; i++)
        {
            System.out.print(tablica[i] + " ");
        }
        // obliczenie średniej wartości liczb występujących w tablicy
        double suma = 0;
        for (int i = 0; i < liczbaElementow; i++)
        {
            suma += tablica[i];
        }

        double sredniaOcen = suma / liczbaElementow;
        System.out.println("\nŚrednia wartość liczb w tablicy: " + sredniaOcen);

        // znalezienie wartości minimalnej i maksymalnej
        Arrays.sort(tablica);
        double min = tablica[0];
        double max = tablica[liczbaElementow-1];
        System.out.println("Wartość minimalna: " + min);
        System.out.println("Wartość maksymalna: " + max);

        // znalezienie wartości wyższych/niższych niż średnia
        //int[] tablicaWartościWiekszychNiżSrednia = new int[liczbaElementow];
        ArrayList<Integer> tablicaWartościWiekszychNiżSrednia = new ArrayList<Integer>();
        ArrayList<Integer> tablicaWartościNizyszychNiżSrednia = new ArrayList<Integer>();
        for (int i = 0; i < liczbaElementow; i++)
        {
            if (tablica[i] > sredniaOcen)
            {
                tablicaWartościWiekszychNiżSrednia.add(tablica[i]);
            }
            else if (tablica[i] < sredniaOcen)
            {
                tablicaWartościNizyszychNiżSrednia.add(tablica[i]);
            }
        }
        System.out.println("Liczba wartości wyższych niż średnia: ");

        for (int element : tablicaWartościWiekszychNiżSrednia)
        {
            System.out.print(element + " ");

        }
        System.out.println("\nLiczba wartości niższych niż średnia: ");
        for (int element : tablicaWartościNizyszychNiżSrednia)
        {
            System.out.print(element + " ");
        }

        // Zlicz częstotliwość występowania poszczególnych liczb
        Map<Double, Integer> mapaFrekwencji = new HashMap<>();
        for (int i = 0; i < liczbaElementow; i++)
        {
            double wartosc = tablica[i];
            if (mapaFrekwencji.containsKey(wartosc))
            {
                mapaFrekwencji.put(wartosc, mapaFrekwencji.get(wartosc) + 1);
            }
            else
            {
                mapaFrekwencji.put(wartosc, 1);
            }
        }
        System.out.println("\nCzęstotliwości występowania poszczególnych liczb:");
        for (Map.Entry<Double, Integer> entry : mapaFrekwencji.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Oblicz średnie odchylenie standardowe wartości występujących w tablicy
        double sumaKwadratow = 0;
        for (int i = 0; i < liczbaElementow; i++)
        {
            sumaKwadratow += Math.pow(tablica[i] - sredniaOcen, 2);
        }
        double varancja = sumaKwadratow / liczbaElementow;
        double odchylenieStand = Math.sqrt(varancja);
        System.out.println("Średnie odchylenie standardowe: " + odchylenieStand);

    }
}