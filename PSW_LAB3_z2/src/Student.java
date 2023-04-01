public class Student // klasa student
{
    private String nr_indeksu;
    private String imie;
    private String nazwisko;
    private int rok_st;
    private int ocena;

    public Student(String nr_indeksu, String imie, String nazwisko, int rok_st) //konstruktor
    {
        this.nr_indeksu = nr_indeksu;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rok_st = rok_st;
    }
    //gettery i settery dla zmiennych w klasie

    //nr_indeksu
    public String getNr_indeksu() {
        return nr_indeksu;
    }

    public void setNr_indeksu(String nr_indeksu) {
        this.nr_indeksu = nr_indeksu;
    }

    //imie
    public String getImie() {return imie;}

    public void setImie(String imie) {
        this.imie = imie;
    }

    //nazwisko
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    //rok studiów
    public int getRok_st() {
        return rok_st;
    }

    public void setRok_st(int rok_st) {
        this.rok_st = rok_st;
    }

    //wypisz dane studenta
    public int wyswietlStudenta() { // wypisz dane studenta
        System.out.println("Dane studnta");
        System.out.println("Student " + imie + " " + nazwisko + " \nNumer indeksu studenta: " + nr_indeksu + "\nRok studiów studenta: " + rok_st );
        return 0;
    }

    //ocena
    public void setOcena(double ocena) {
        this.ocena =  (int) ocena;
    }

    public double getOcena() {
        return 0;
    }
}
