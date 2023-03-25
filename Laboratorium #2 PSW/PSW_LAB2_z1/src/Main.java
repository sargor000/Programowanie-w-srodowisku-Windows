import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner liczba = new Scanner(System.in); // nowy obiekt liczba

        System.out.print("Ile elementów ma mieć tablica: ");
        int liczbaElementow = liczba.nextInt(); // pobierz liczbę od użytkowanika

        int[] tablica = new int[liczbaElementow];

        System.out.println("Podaj liczby, które mają być posortowane: ");
        for (int i = 0; i < liczbaElementow; i++) // dodawnie kolejnych liczb do tablicy, tyle zdeklarował użytkowanik
        {
            tablica[i] = liczba.nextInt();// na miejsce "i" w tablicy wstaw daną liczbę
        }

        // Sortowanie bąbelkowe
        for (int i = 0; i < liczbaElementow - 1; i++)
        { //pętla for iteruje tyle razy ile jest elementów w tablicy - 1
            for (int j = 0; j < liczbaElementow - i - 1; j++)  //pętla for, które iteruje tyle razy ile jest elementów w tablicy - i - 1
            {
                if (tablica[j] > tablica[j+1]) // jesli element w tablicy o indeksie j jest wiekszy od elementu w tablicy o indeksie j+1  to wykonaj...
                {
                    int temp = tablica[j]; // wykonaj przypisanie elementu o indeksie j do zmiennej temp
                    tablica[j] = tablica[j+1]; // do w miejsce w tablicy o indeksie j wstaw element z tablicy o indeksie j+1
                    tablica[j+1] = temp; // do w miejsce w tablicy o indeksie j+1 wstaw element z temp
                }
            }
        }

        // Wyświetlenie posortowanej tablicy
        System.out.println("Posortowana tablica: "); // wypisz posortowną tablicę
        for (int i = 0; i < liczbaElementow; i++)
        {
            System.out.print(tablica[i] + " ");
        }
    }
}