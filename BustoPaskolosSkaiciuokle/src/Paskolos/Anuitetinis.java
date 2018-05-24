package Paskolos;

import Paskolos.Paskolos;

public class Anuitetinis extends Paskolos{
    public Anuitetinis(double suma, int metai, int menesiai, double procentas, int men1, int men2)
    {
        super(suma,metai,menesiai,procentas,men1,men2);
    }
    public double ProcentuVertimas()
    {
        return procentas/1200;
    }
    public double Konstanta()
    {
        return (ProcentuVertimas()*Math.pow((1+ProcentuVertimas()),Periodusk()))/(Math.pow((1+ProcentuVertimas()),Periodusk())-1);
    }
    public void Priskirimas()
    {
        grazinti = suma * Konstanta();
        liko = grazinti*Periodusk();
    }
    public void Sumazinimas()
    {
        liko -= suma * Konstanta();
    }

}
