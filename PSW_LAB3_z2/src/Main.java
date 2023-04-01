import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Uni uni = new Uni();
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            //wybór opcji w programie
            System.out.println("Witaj! Dziennik studenta. Co chcesz zrobić?");
            System.out.println("1. Dodaj studenta do listy");
            System.out.println("2. Usuń studenta z listy");
            System.out.println("3. Oblicz średnią ocen dla danego studenta (nr_indeksu)");
            System.out.println("4. Oblicz średnią ocen dla wszystkich studentów");
            System.out.println("5. Wyświetl dane studneta");
            System.out.println("6. Opuść z programu");

            try {
                int wybor = scanner.nextInt();
                scanner.nextLine(); // konsumujemy znak nowej linii

                switch (wybor) { //wybór opcji i przeniesienie do odpowiedniego case z metodą.
                    case 1:
                        uni.dodajStudenta();
                        break;
                    case 2:
                        uni.usunStudenta();
                        break;
                    case 3:
                        uni.obliczSrednia();
                        break;
                    case 4:
                        uni.obliczSredniaAll();
                        break;
                    case 5:
                        uni.wyswietlDaneStudenta();
                        return;
                    case 6:
                        System.out.println("Koniec programu.");
                        return;
                    default:
                        System.out.println("Niepoprawny wybór z listy opcji."); // użytkownik wybrał zły numer
                        break;
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Błąd.");
                scanner.nextLine(); // nieprawidłowe dane wejściowe
            }
        }
    }
}
