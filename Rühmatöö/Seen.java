abstract class Seen implements Comparable<Seen> {
    private String nimi;
    private double pikkus;
    private double kübaraLäbimõõt;
    private double väärtus;

    //Konstruktor
    public Seen(String nimi, double pikkus, double kübaraLäbimõõt, double väärtus) {
        this.nimi = nimi;
        this.pikkus = Math.round(Math.random()*29.0+1.0);
        this.kübaraLäbimõõt = Math.round(Math.random()*14.0+1.0);;
        this.väärtus = väärtus;
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
    public double getVäärtus() {
        return väärtus;
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
    //Järjestab seened korvis vastavalt väärtusele
    @Override
    public int compareTo(Seen o) {

        if(väärtus < o.väärtus){
            return 1 /*+ this.getNimi().compareTo(o.getNimi())*/;
        }else if(väärtus > o.väärtus){
            return -1 /*this.getNimi().compareTo(o.getNimi()) - 1*/;
        }else{
            return 0 /*this.getNimi().compareTo(o.getNimi())*/;
        }
    }

    //toString meetod
    //Kirjeldatakse seent
    @Override
    public String toString() {
        return "See " + this.nimi + " on " + this.pikkus + "cm pikk " +
                "ning " + this.kübaraLäbimõõt + "cm suuruse kübaraga." +
                "Selle seene väärtus on " + this.väärtus + " münti. ";
    }
}

