public class RGBController //klasa
{
    private RGB color1;
    private RGB color2;

    public RGBController(RGB color1, RGB color2) //konstruktor
    {
        this.color1 = color1;
        this.color2 = color2;
    }
    //Metoda inicjująca zestaw składowych barw dla danego obiektu
    public void ZestawBarw(RGB color, int R_value, int G_value, int B_value)
    {
        color.setR_value(R_value);
        color.setG_value(G_value);
        color.setB_value(B_value);
    }
    //Metoda wyświetlająca składowe barw w formacie [R_value, G_value, B_value]
    public void wypiszBarwy(RGB color)
    {
        System.out.println("[" + color.getR_value() + ", " + color.getG_value() + ", " + color.getB_value() + "]");
    }
    //Metoda mieszającą kolory zgodnie z zasadami RGB przyjmującą jako argumenty dwa obiekty klasy RGB.
    public RGB mieszajKolory()
    {
        int R_value = (color1.getR_value() + color2.getR_value()) / 2;
        int G_value = (color1.getG_value() + color2.getG_value()) / 2;
        int B_value = (color1.getB_value() + color2.getB_value()) / 2;
        RGB mixBarw = new RGB(R_value, G_value, B_value);
        return mixBarw;
    }
}
