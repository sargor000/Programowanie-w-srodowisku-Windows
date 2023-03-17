import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj liczbę: ");
        int liczba = scanner.nextInt();

        boolean czyPierwsza = true;

        if (liczba <= 1)
        {
            czyPierwsza = false;
        }

        for (int i = 2; i < liczba; i++)
        {
            if (liczba % i == 0)
            {
                czyPierwsza = false;
                break;
            }
        }

        if (czyPierwsza)
        {
            System.out.println(liczba + " jest liczbą pierwszą.");
        }
        else
        {
            System.out.println(liczba + " nie jest liczbą pierwszą.");
        }
    }
}