package sk.stuba.fei.uim.oop.druhykariet.akcnekarty;

import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.NeakcnaKarta;
import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.Zameriavac;
import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.util.ArrayList;
import java.util.List;

public class KacaciPochod extends AkcnaKarta{
    @Override
    public String getNazovKarty() {
        return "Kacaci Pochod";
    }

    @Override
    public void zahrajKartu(List<Hrac> hrac, List<NeakcnaKarta> balikNeakcnychKariet, List<Zameriavac> zameriavace) {
        List<NeakcnaKarta> helper = new ArrayList<>();
        for (int i = 0;i < balikNeakcnychKariet.size()-1;i++){
            helper.add(balikNeakcnychKariet.get(i));
            balikNeakcnychKariet.set(i, balikNeakcnychKariet.get(i+1));
        }
        balikNeakcnychKariet.set(balikNeakcnychKariet.size()-1, helper.get(0));
    }
}
