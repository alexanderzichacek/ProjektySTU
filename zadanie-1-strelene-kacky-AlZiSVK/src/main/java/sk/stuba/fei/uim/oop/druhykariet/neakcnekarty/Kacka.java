package sk.stuba.fei.uim.oop.druhykariet.neakcnekarty;

public class Kacka extends NeakcnaKarta {
    protected String kacka;

    public Kacka(int poradieHraca) {
        this.kacka = "Kacka hraca "+poradieHraca;
    }

    @Override
    public String getMeno(){
        return kacka;
    }
}
