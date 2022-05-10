package sk.stuba.fei.uim.oop.druhykariet.akcnekarty;

import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.NeakcnaKarta;
import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.Zameriavac;
import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;

public class Zamierit extends AkcnaKarta{
    @Override
    public String getNazovKarty() {
        return "Zamierit";
    }

    @Override
    public void zahrajKartu(List<Hrac> hrac, List<NeakcnaKarta> balikNeakcnychKariet, List<Zameriavac> zameriavace) {
        boolean zamierene = false;
        boolean testZamierenia = false;
        boolean testZameriavacov = false;
        int counterPokusov = 0;
        int indexZamierenia = 0;
        while(!zamierene){
            for (int i = 0;i < 6;i++){
                if (zameriavace.get(i).getZameriavac().equals("Zamierene")) {
                    counterPokusov += 1;
                }
            }
            if (counterPokusov == 6){
                System.out.println("Nemozes nikde zamierit a nemas inu kartu. Stratil si jedno kolo.");
                break;
            }
            else {
                while (!testZameriavacov){
                    indexZamierenia = KeyboardInput.readInt("Na ktore policko chces zamierit? ");
                    if (indexZamierenia > 0 && indexZamierenia < 7) testZamierenia = true;
                    else if (indexZamierenia < 1) System.out.print("");
                    else System.out.print("");

                    if (indexZamierenia > 0 && indexZamierenia < 7 && zameriavace.get(indexZamierenia-1).getZameriavac().equals("Nezamierene")){
                        testZameriavacov = true;
                    }
                    else{
                        System.out.println("Musis zamierit na policko 1-6 take, na ktorom nie je zameriavac");
                    }
                }

                if (zameriavace.get(indexZamierenia-1).getZameriavac().equals("Nezamierene")){
                    zameriavace.get(indexZamierenia-1).setZameriavac("Zamierene");
                    zamierene = true;
                }
            }
        }
    }
}
