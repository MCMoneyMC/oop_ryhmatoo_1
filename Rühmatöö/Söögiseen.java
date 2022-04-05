public class Söögiseen extends Seen {

    private boolean vajabKupatamist;
    private boolean onUssitanud;

    //Konstruktor
    public Söögiseen(String nimi, double pikkus_cm, double kübaraLäbimõõt_cm, boolean vajabKupatamist, boolean onUssitanud) {
        super(nimi, pikkus_cm, kübaraLäbimõõt_cm);
        this.vajabKupatamist = vajabKupatamist;
        this.onUssitanud = onUssitanud;
    }

    //Get-id
    public boolean kasVajabKupatamist() {
        return vajabKupatamist;
    }
    public boolean kasOnUssitanud(){
        return this.onUssitanud;
    }

    //Set-id
    public void setVajabKupatamist(boolean vajabKupatamist) {
        this.vajabKupatamist = vajabKupatamist;
    }
    public void setOnUssitanud(boolean onUssitanud) {
        this.onUssitanud = onUssitanud;
    }

    //Korjamise otsuse tegemise meetod
    @Override
    public boolean onMõtetKorjata(Seeneline korjaja) {
        //Genereeritakse suvaline arv poollõigus [0,1), mida inimese laiskusega võrreldakse.
        //Kui inimene on laisk (laiskus läheneb ühele), on suurem tõenäosus,
        //et ta ei ole nii põhjalik ussitamise kontrollil, ning kipub küike korjama.
        //Kui aga inimene pole laisk (laiskus läheneb nullile), on suurem tõenäosus,
        //et ta korjab vaid seeni, mis pole ussitanud.
        double piir = Math.random();
        if(korjaja.getLaiskus() <= piir){
            return !this.kasOnUssitanud();
        }
        return true;
    }

    //toString meetod
    @Override
    public String toString() {
        String algneTekst = super.toString();

        //Uurin, kas on ussitanud või mitte
        String ussitamine = "ussitamata ";
        if(this.onUssitanud){
            ussitamine = "ussitanud ";
        }
        //Uurin, kas on tarvis kupatada, või mitte
        String kupatamine  = "ei ole";
        if(this.vajabKupatamist){
            kupatamine = "on";
        }
        //Lisan vahele, kas on ussitanud, täpsustan lõpus, et tegu on söögiseenega ning kas vajab kupatamist.
        return algneTekst.substring(0, 4) + ussitamine + algneTekst.substring(4) + " Tegu on söögiseenega, " +
                "mida " + kupatamine + " tarvis kupatada.";
    }
}
