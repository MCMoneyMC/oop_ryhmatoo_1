public class Mürgiseen extends Seen {

    //Konstruktor
    public Mürgiseen(String nimi, double pikkus_cm, double kübaraLäbimõõt_cm, double väärtus) {
        super(nimi, pikkus_cm, kübaraLäbimõõt_cm,väärtus);

    }

    //Korjamise otsuse tegemise meetod
    @Override
    public boolean onMõtetKorjata(Seeneline korjaja) {
        //Genereeritakse suvaline arv lõigus [0,1), mida inimese laiskusega võrreldakse.
        //Kui inimene on laisk (laiskus läheneb ühele), on suurem tõenäosus,
        //et ta ei vaata, kas tegu on mürgiseenega, või mitte.
        //Kui aga inimene pole laisk (laiskus läheneb nullile), on suurem tõenäosus,
        //et ta mürgiseene ära tunneb ja seda ei korja.
        double piir = Math.random();
        return !(korjaja.getLaiskus() < piir);
    }

    //toString meetod
    //Täpsustan lõpus, et tegu on mürgiseenega.
    @Override
    public String toString() {
        return super.toString() + " Tegu on mürgiseenega.";
    }
}

