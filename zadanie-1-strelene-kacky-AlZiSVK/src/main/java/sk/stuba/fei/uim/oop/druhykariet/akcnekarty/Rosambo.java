package sk.stuba.fei.uim.oop.druhykariet.akcnekarty;

import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.NeakcnaKarta;
import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.Zameriavac;
import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rosambo extends AkcnaKarta{
    @Override
    public String getNazovKarty() {
        return "Rosambo";
    }

    @Override
    public void zahrajKartu(List<Hrac> hrac, List<NeakcnaKarta> balikNeakcnychKariet, List<Zameriavac> zameriavace) {
        List<NeakcnaKarta> helper = new ArrayList<>(6);
        for (int i = 0;i < 6;i++) helper.add(balikNeakcnychKariet.get(i));

        Collections.shuffle(helper);

        for (int i = 0;i < 6;i++) balikNeakcnychKariet.set(i, helper.get(i));
    }
}
