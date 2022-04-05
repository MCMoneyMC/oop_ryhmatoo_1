abstract public class Seen implements Comparable<Seen>{
    private String nimi;
    private double pikkus;
    private double kübaraLäbimõõt;

    //Konstruktor
    public Seen(String nimi, double pikkus_cm, double kübaraLäbimõõt_cm){
        this.nimi = nimi;
        this.pikkus = pikkus_cm;
        this.kübaraLäbimõõt = kübaraLäbimõõt_cm;
    }

    //Korjamise otsuse tegemise meetod
    public abstract boolean onMõtetKorjata(Seeneline korjaja);

    //Get-id
    public String getNimi() {
        return nimi;
    }
    public double getKübaraLäbimõõt() {
        return kübaraLäbimõõt;
    }
    public double getPikkus() {
        return pikkus;
    }

    //Set-id
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    public void setKübaraLäbimõõt(double kübaraLäbimõõt) {
        this.kübaraLäbimõõt = kübaraLäbimõõt;
    }
    public void setPikkus(double pikkus) {
        this.pikkus = pikkus;
    }

    //Võrdlemine
    //Järjestab seened korvis vastavalt nime ning kaalutud suuruse baasil baasil
    @Override
    public int compareTo(Seen o) {
        double kaalutudSuurus1 = this.getPikkus() * 0.6 + this.getKübaraLäbimõõt() * 0.4;
        double kaalutudSuurus2 = o.getPikkus() * 0.6 + o.getKübaraLäbimõõt() * 0.4;

        if(kaalutudSuurus1 < kaalutudSuurus2){
            return 1 + this.getNimi().compareTo(o.getNimi());
        }else if(kaalutudSuurus1 > kaalutudSuurus2){
            return this.getNimi().compareTo(o.getNimi()) - 1;
        }else{
            return this.getNimi().compareTo(o.getNimi());
        }
    }

    //toString meetod
    //Kirjeldatakse seent
    @Override
    public String toString() {
        return "See " + this.nimi + " on " + this.pikkus + "cm pikk " +
                "ning " + this.kübaraLäbimõõt + "cm suuruse kübaraga.";
    }
}
