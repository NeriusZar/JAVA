package Paskolos;
public class Linijinis extends Paskolos{
    protected double liko = 0;

    public Linijinis(double suma, int metai, int menesiai, double procentas, int men1, int men2) {
        super(suma, metai, menesiai, procentas, men1, men2);

    }

    public void Priskirimas() {
        PaskolosDydis();
        grazinti = (suma / Periodusk2()) + (suma * ProcentuVertimas()) / 12;
        setLiko(suma);

    }

    public void Pakeitimas() {
        setLiko(getLiko() - (suma / Periodusk2()));
        grazinti = (suma / Periodusk2()) + (getLiko() * ProcentuVertimas()) / 12;
        liko -= grazinti;
    }

    public void PaskolosDydis() {
        setLiko(suma);
        for (int i = 0; i < Periodusk2(); i++) {
            liko += (suma / Periodusk2()) + ((getLiko() * ProcentuVertimas()) / 12);
            setLiko(getLiko() - (suma / Periodusk2()));
        }
    }
}
