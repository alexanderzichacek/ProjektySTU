package sk.stuba.fei.uim.oop.druhykariet.akcnekarty;

import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.NeakcnaKarta;
import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.Zameriavac;
import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.util.Collections;
import java.util.List;

public class KacaciTanec extends AkcnaKarta{
    @Override
    public String getNazovKarty() {
        return "Kacaci Tanec";
    }

    @Override
    public void zahrajKartu(List<Hrac> hrac, List<NeakcnaKarta> balikNeakcnychKariet, List<Zameriavac> zameriavace) {
        Collections.shuffle(balikNeakcnychKariet);
    }
}
