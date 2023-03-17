import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj liczbę w systemie binarnym: ");
        String binarna = scanner.nextLine();

        int liczbaDziur = 0;
        boolean wewnatrzDziury = false;

        for (int i = 0; i < binarna.length(); i++)
        {
            char znak = binarna.charAt(i);//

            if (znak == '1')
            {
                if (wewnatrzDziury) //jeśli wewnatrzDziury jest true to zwiększ liczbaDziury
                {
                    liczbaDziur++;
                }
                wewnatrzDziury = true;
            }
            else if (znak == '0')
            {
                if (wewnatrzDziury)
                {
                    continue;
                }
            }
            else
            {
                System.out.println("Podana wartość nie jest liczbą binarną!");
                return;
            }
        }

        System.out.println("Liczba dziur binarnych: " + liczbaDziur);
    }
}
