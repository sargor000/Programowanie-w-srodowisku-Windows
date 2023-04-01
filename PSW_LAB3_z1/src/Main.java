public class Main
{
    public static void main(String[] args)
    {
        RGB kolor1 = new RGB(0, 0, 0); //kolor1
        RGB kolor2 = new RGB(255, 255, 255);//kolor2
        RGBController uzyjControleraBarw = new RGBController(kolor1, kolor2);

        uzyjControleraBarw.wypiszBarwy(kolor1);
        uzyjControleraBarw.wypiszBarwy(kolor2);

        RGB mix1 = uzyjControleraBarw.mieszajKolory();
        uzyjControleraBarw.wypiszBarwy(mix1);
    }
}