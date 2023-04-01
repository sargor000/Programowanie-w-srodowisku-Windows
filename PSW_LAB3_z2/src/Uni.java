import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Uni {
    private List<Double> listaDopuszczalnychOcen = List.of(2.0, 3.0, 3.5, 4.0, 4.5, 5.0);// lista z ocenami
    private ArrayList<Student> listaStudentow = new ArrayList<>(); //lista studentów

    public void dodajStudenta() { // opcja dodaj studenta
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj numer indeksu:");// dodaj nr indeksu
        String nr_indeksu = scanner.nextLine(); // pobranie wprowdzonych znaków przez użytkownika

        System.out.println("Podaj imię:"); // podaj imię studenta
        String imie = scanner.nextLine(); // pobranie wprowdzonych znaków przez użytkownika

        System.out.println("Podaj nazwisko:"); // podaj nazwisko studenta
        String nazwisko = scanner.nextLine();// pobranie wprowdzonych znaków przez użytkownika

        System.out.println("Podaj rok studiów:"); // podaj rok studiów studenta
        int rok_st = scanner.nextInt();// pobranie wprowdzonych znaków przez użytkownika

        Student student = new Student(nr_indeksu, imie, nazwisko, rok_st); // nowy obiekt student

        System.out.println("Podaj ocenę dla studenta " + imie + " " + nazwisko + " (2, 3, 3.5, 4, 4.5, 5):");
        double ocena = scanner.nextDouble(); // podaj ocenę
        while (!listaDopuszczalnychOcen.contains(ocena)) { // sprawdzenie czy ocena jest z zakresu ocen
            System.out.println("Niepoprawna ocena. Podaj ocenę jeszcze raz:");
            ocena = scanner.nextDouble();
        }

        student.setOcena(ocena); // dodaje ocene do studenta
        listaStudentow.add(student); // dodaj studenta do listy studentów
    }

    public void usunStudenta() { // metoda, która usuwa studenta z listy, usunięcie studenta z przez wybranie numeru indeksu
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj numer indeksu studenta do usunięcia:");
        String nr_indeksu = scanner.nextLine(); // pobranie wprowdzonych znaków przez użytkownika

        for (Student student : listaStudentow) { //wyszukanie studenta z listy studentów
            if (student.getNr_indeksu().equals(nr_indeksu)) { //porównuje numer indeksu studenta (zwrócony przez metodę getNr_indeksu())
                // z wartością zmiennej nr_indeksu i zwraca wartość logiczną true, jeśli obie wartości są równe.
                // Jeśli wartości nie są równe, to zwrócona zostanie wartość false.
                listaStudentow.remove(student); // usunięcie studenta z listy studentów
                System.out.println("Usunięto studenta o numerze indeksu " + nr_indeksu); // wypisnanie informacji o tym że dany student został usunięty
                return;
            }
        }
        System.out.println("Nie ma studenta o numerze indeksu " + nr_indeksu); //jeśli nie znaleziono studenta o danym numerze, wyświetl komunikat
    }
    public void wyswietlDaneStudenta() { // metoda, wyświetla dane studnta
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj numer indeksu studenta, aby wyświetlić dane:");
        String nr_indeksu = scanner.nextLine(); // pobranie wprowdzonych znaków przez użytkownika

        for (Student student : listaStudentow) { //wyszukanie studenta z listy studentów
            if (student.getNr_indeksu().equals(nr_indeksu)) { //porównuje numer indeksu studenta (zwrócony przez metodę getNr_indeksu())
                // z wartością zmiennej nr_indeksu i zwraca wartość logiczną true, jeśli obie wartości są równe.
                // Jeśli wartości nie są równe, to zwrócona zostanie wartość false.
                //listaStudentow.remove(student); // usunięcie studenta z listy studentów
                listaStudentow.get(student.wyswietlStudenta()); // wypisnanie informacji
                return;
            }
        }
        System.out.println("Nie ma studenta o numerze indeksu " + nr_indeksu); //jeśli nie znaleziono studenta o danym numerze, wyświetl komunikat
    }



    public void obliczSrednia() { // metoda do oblicznia średniej z ocen studenta
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj numer indeksu studenta, dla którego chcesz obliczyć średnią ocenę:");
        String nr_indeksu = scanner.nextLine(); // dla którego studenta ma być wyliczona śrdenia

        for (Student student : listaStudentow) { // znajdź studenta na liście studentów
            if (student.getNr_indeksu().equals(nr_indeksu)) {
                double sumaOcen = student.getOcena();
                int iloscOcen = 1; //inicjalizowana na 1, ponieważ bieżącą oceną jest już ocena studenta z listy

                while (true) {
                    System.out.println("Podaj kolejną ocenę (lub wpisz -1, aby zakończyć):");
                    double ocena = scanner.nextDouble(); //pobierz wartość wprowadzoną przez użytkownika jako ocenę i przypisuje ją do zmiennej

                    if (ocena == -1) { // jeśli podano -1 to zakończono podawnie ocen dla studenta
                        break;
                    }

                    while (!listaDopuszczalnychOcen.contains(ocena)) {
                        //Sprawdza, czy wprowadzona ocena znajduje się na liście dozwolonych ocen (zapisanej w zmiennej "listaDopuszczalnychOcen").
                        // Jeśli nie, to wyświetla komunikat o błędzie i prosi użytkownika o ponowne wprowadzenie oceny.
                        System.out.println("Niepoprawna ocena. Podaj ocenę jeszcze raz:");
                        ocena = scanner.nextDouble();
                    }

                    sumaOcen += ocena; // sumowanie ocen
                    iloscOcen++; // zwiększanie ilości ocen po każdym dodaniu oceny
                }

                double srednia = sumaOcen / iloscOcen; // obliczenie średniej z podanych ocen
                System.out.println("Średnia ocen dla studenta " + student.getImie() + " " + student.getNazwisko() + " wynosi: " + srednia);
                //wyświetlenie komunitkatu o średniej z ocen studenta
                return;
            }
        }

        System.out.println("Nie ma studenta o numerze indeksu " + nr_indeksu);
    }

    public void obliczSredniaAll() {//metoda, która oblicza  średnia z ocen dla wszysktich studentów
        double sumaOcen = 0; // zmienna do przechowywania sumy z ocen
        int iloscOcen = 0; // zmienna do przechowywania ilości sumowanych ocen

        for (Student student : listaStudentow) { //pętli "for-each", iteruje przez każdego studenta na liście "listaStudentow"
            sumaOcen += student.getOcena(); //dla każdego studenta, dodzwne są jego ocenę do sumy ocen przechowywanej
            iloscOcen++; //zwiększa licznik ilości ocen "iloscOcen"
        }

        double srednia = sumaOcen / iloscOcen;// oblicznie średniej z ocen
        System.out.println("Średnia ocen dla wszystkich studentów wynosi: " + srednia);// wypisnie wyniku
    }
}