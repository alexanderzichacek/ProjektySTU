package sk.stuba.fei.uim.oop.druhykariet.neakcnekarty;

public class Voda extends NeakcnaKarta {
    protected String voda;

    public Voda() {
        this.voda = "Voda";
    }

    @Override
    public String getMeno(){
        return voda;
    }
}
