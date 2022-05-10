package sk.stuba.fei.uim.oop.druhykariet.akcnekarty;

import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.NeakcnaKarta;
import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.Zameriavac;
import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;

public class Vystrelit extends AkcnaKarta{
    @Override
    public String getNazovKarty() {
        return "Vystrelit";
    }

    @Override
    public void zahrajKartu(List<Hrac> hrac, List<NeakcnaKarta> balikNeakcnychKariet, List<Zameriavac> zameriavace) {
        boolean vystrelene = false;
        int counterPokusov = 0;
        while (!vystrelene){
            boolean testVystrelu = false;
            int indexVystrelu = 0;
            for (int i = 0;i < 6;i++){
                if (!zameriavace.get(i).getZameriavac().equals("Zamierene")) {
                    counterPokusov += 1;
                }
            }
            if (counterPokusov == 6){
                System.out.println("Nemozes nikde vystrelit a nemas inu kartu. Stratil si jedno kolo.");
                break;
            }
            else {
                while (!testVystrelu) {
                    indexVystrelu = KeyboardInput.readInt("Na ktore policko chces vystrelit? ");
                    if (indexVystrelu > 0 && indexVystrelu < 7) testVystrelu = true;
                    else if (indexVystrelu < 1) System.out.println("Musis vystrelit na policko 1-6.");
                    else System.out.println("Plocha ma len 6 policok.");

                }

                if (zameriavace.get(indexVystrelu - 1).getZameriavac().equals("Zamierene")) {
                    if (balikNeakcnychKariet.get(indexVystrelu - 1).getMeno().equals("Voda")) {
                        System.out.println("Strelil si do vody");
                    } else {
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
                else System.out.println("Na tomto policku nie je zamierene.");
            }
        }
    }
}
