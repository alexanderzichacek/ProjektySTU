package sk.stuba.fei.uim.oop.druhykariet.akcnekarty;

import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.NeakcnaKarta;
import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.Zameriavac;
import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.util.List;

public abstract class AkcnaKarta {
    protected String nazovKarty;

    public abstract void zahrajKartu(List<Hrac> hrac, List<NeakcnaKarta> balikNeakcnychKariet, List<Zameriavac> zameriavace);

    public String getNazovKarty() {
        return nazovKarty;
    }
}
