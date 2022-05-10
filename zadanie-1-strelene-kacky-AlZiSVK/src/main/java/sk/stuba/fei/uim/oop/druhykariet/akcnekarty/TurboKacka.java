package sk.stuba.fei.uim.oop.druhykariet.akcnekarty;

import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.NeakcnaKarta;
import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.Zameriavac;
import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.ArrayList;
import java.util.List;

public class TurboKacka extends AkcnaKarta{
    @Override
    public String getNazovKarty() {
        return "Turbo Kacka";
    }

    @Override
    public void zahrajKartu(List<Hrac> hrac, List<NeakcnaKarta> balikNeakcnychKariet, List<Zameriavac> zameriavace) {
        List<NeakcnaKarta> helper = new ArrayList<>();

        boolean testPosunu = false;
        int vyberKacku = 0;
        while (!testPosunu){
            vyberKacku = KeyboardInput.readInt("Vyber kacku ktoru chces posunut na 1. miesto");
            if (vyberKacku > 0 && vyberKacku < 7 && balikNeakcnychKariet.get(vyberKacku-1).getMeno().contains("Kacka")) testPosunu = true;
            else if (vyberKacku < 1 || !balikNeakcnychKariet.get(vyberKacku-1).getMeno().contains("Kacka")) System.out.println("Musis posunut nejaku kacku");
            else System.out.println("Musis posunut nejaku kacku");
        }

        if (balikNeakcnychKariet.get(vyberKacku-1).getMeno().contains("Kacka")){
            helper.add(balikNeakcnychKariet.get(vyberKacku-1));
            for (int i = vyberKacku-1;i > 0;i--){
                balikNeakcnychKariet.set(i, balikNeakcnychKariet.get(i-1));
            }
            balikNeakcnychKariet.set(0, helper.get(0));
        }
    }
}
