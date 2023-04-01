public class RGB // klasa RGB
{
    // pola ze zmiennymi
    private int R_value; //zmienna, która przechowuje wartość czerwonej barwy
    private int G_value;//zmienna, która przechowuje wartość zielonej barwy
    private int B_value; //zmienna, która przechowuje wartość niebieskie barwy

    public RGB(int R_value, int G_value, int B_value) //konstruktor
    {
        this.R_value = R_value; //przypisuje wartość zmiennej "R_value" do właściwości "this.R_value" w obiekcie
        this.G_value = G_value;
        this.B_value = B_value;
    }

    public int getR_value() //getter R //pozwalają na uzyskanie dostępu do prywatnych zmiennych w kontrolowany sposób
    {
        return R_value;
    }

    public void setR_value(int R_value) // setter R // służy do ustawiania wartości pola R_value na określoną wartość
    {
        this.R_value = R_value;
    }

    public int getG_value() //getter G
    {
        return G_value;
    }

    public void setG_value(int G_value) // setter G
    {
        this.G_value = G_value;
    }

    public int getB_value() //getter B
    {
        return B_value;
    }

    public void setB_value(int B_value) // setter B
    {
        this.B_value = B_value;
    }
}
