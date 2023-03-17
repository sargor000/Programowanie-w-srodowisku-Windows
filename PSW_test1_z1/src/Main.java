
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        int nowaLiczbadoSprawdzania;
        int podajLiczbedoSprawdzenia = 0;

        LosowanieLiczby nowaLiczba = new LosowanieLiczby();
        nowaLiczbadoSprawdzania = nowaLiczba.wylosujLiczbe();

        do {
            LosowanieLiczby podajLiczbe = new LosowanieLiczby();
            podajLiczbedoSprawdzenia = podajLiczbe.pobierzLiczbe();

            if (nowaLiczbadoSprawdzania > podajLiczbedoSprawdzenia) {
                System.out.println("Twoja liczba jest za mała.");
            }
            else if (nowaLiczbadoSprawdzania < podajLiczbedoSprawdzenia){
                System.out.println("Twoja liczba jest za duża.");
            }
            else {
                System.out.println("Zgadłeś liczbę!");
            }
        }
        while (nowaLiczbadoSprawdzania != podajLiczbedoSprawdzenia);

    }
}