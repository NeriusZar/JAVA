package Paskolos;
public class Paskolos {
    protected double suma;
    protected int metai;
    protected int menesiai;
    protected double procentas;
    protected int men1;
    protected int men2;
    protected double grazinti;

    public void setLiko(double liko) {
        this.liko = liko;
    }

    public double getLiko() {
        return liko;
    }
    protected double liko;



    public Paskolos(double suma, int metai, int menesiai, double procentas, int men1, int men2)
    {
        this.suma = suma;
        this.procentas = procentas;
        this.menesiai = menesiai;
        this.metai = metai;
        this.men1 = men1;
        this.men2 = men2;
    }
    public Paskolos(double suma, int metai, int menesiai, double procentas)
    {
        this(suma, metai, menesiai, procentas, 0, 0);
    }
    public Paskolos()
    {

    }
    public double Periodusk()
    {
        return metai*12+menesiai;
    }
    public int Periodusk2()
    {
        return metai*12+menesiai;
    }

    public double ProcentuVertimas()
    {
        return procentas/100;
    }

}
