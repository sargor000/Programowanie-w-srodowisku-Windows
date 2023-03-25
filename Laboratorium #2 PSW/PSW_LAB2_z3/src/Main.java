import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // pobranie wymiarów macierzy od użytkownika
        System.out.print("Podaj wymiar macierzy kwadratowej: ");
        int n = sc.nextInt();

        // inicjalizacja macierzy A i B oraz macierzy wynikowej C
        int[][] A = new int[n][n];
        int[][] B = new int[n][n];
        int[][] C = new int[n][n];

        // wypełnienie macierzy A i B wartościami losowymi z zakresu od -10 do 10
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                A[i][j] = (int) (Math.random() * 21) - 10;
                B[i][j] = (int) (Math.random() * 21) - 10;
            }
        }

        // wyświetlenie macierzy A i B
        System.out.println("Macierz A:");
        wyswietlMacierz(A);
        System.out.println("Macierz B:");
        wyswietlMacierz(B);

        // dodawanie macierzy A i B
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        System.out.println("Macierz A + B:");
        wyswietlMacierz(C);

        // odejmowanie macierzy A i B
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        System.out.println("Macierz A - B:");
        wyswietlMacierz(C);

        // mnożenie macierzy A i B
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                for (int k = 0; k < n; k++)
                {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        System.out.println("Macierz A * B:");
        wyswietlMacierz(C);
    }

    // metoda pomocnicza do wyświetlania macierzy
    public static void wyswietlMacierz(int[][] macierz)
    {
        for (int i = 0; i < macierz.length; i++)
        {
            for (int j = 0; j < macierz[i].length; j++)
            {
                System.out.print(macierz[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}