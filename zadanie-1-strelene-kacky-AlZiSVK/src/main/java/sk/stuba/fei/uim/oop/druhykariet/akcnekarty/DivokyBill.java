package sk.stuba.fei.uim.oop.druhykariet.akcnekarty;

import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.NeakcnaKarta;
import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.Zameriavac;
import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;

public class DivokyBill extends AkcnaKarta{
    public DivokyBill() {
    }

    @Override
    public String getNazovKarty() {
        return "Divoky Bill";
    }

    @Override
    public void zahrajKartu(List<Hrac> hrac, List<NeakcnaKarta> balikNeakcnychKariet, List<Zameriavac> zameriavace) {
        boolean vystrelene = false;
        while (!vystrelene){
            boolean testVystrelu = false;
            int indexVystrelu = 0;
            while (!testVystrelu){
                indexVystrelu = KeyboardInput.readInt("Na ktore policko chces vystrelit? ");
                if (indexVystrelu > 0 && indexVystrelu < 7) testVystrelu = true;
                else if (indexVystrelu < 1) System.out.println("Musis vystrelit na policko 1-6");
                else System.out.println("Plocha ma len 6 policok");
            }

            if (balikNeakcnychKariet.get(indexVystrelu - 1).getMeno().contains("Voda")) {
                System.out.println("Strelil si do vody");
            }
            else {
                for (Hrac value : hrac) {
                    if (balikNeakcnychKariet.get(indexVystrelu - 1).getMeno().equals("Kacka hraca " + value.getPoradie())) {
                        System.out.println("Strelil si do kacky hraca " + value.getPoradie());
                        value.strataZivota();

                        balikNeakcnychKariet.set(6, balikNeakcnychKariet.get(6));
                        balikNeakcnychKariet.remove(indexVystrelu - 1);
                        break;
                    }
                }
            }
            vystrelene = true;
            zameriavace.get(indexVystrelu - 1).setZameriavac("Nezamierene");
        }
    }
}
