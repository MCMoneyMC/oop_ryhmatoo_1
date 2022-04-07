abstract class Seen implements Comparable<Seen> {
    private String nimi;
    private int pikkus;
    private int kübaraLäbimõõt;
    private double väärtus;

    //Konstruktor
    public Seen(String nimi, double väärtus) {
        this.nimi = nimi;
        this.pikkus = (int) Math.round(Math.random()*29.0+1.0);
        this.kübaraLäbimõõt = (int) Math.round(Math.random()*14.0+1.0);
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
    public void setKübaraLäbimõõt(int kübaraLäbimõõt) {
        this.kübaraLäbimõõt = kübaraLäbimõõt;
    }
    public void setPikkus(int pikkus) {
        this.pikkus = pikkus;
    }
    public void setVäärtus(double väärtus) {
        this.väärtus = väärtus;
    }

    //Võrdlemine
    //Järjestab seened korvis vastavalt väärtusele
    @Override
    public int compareTo(Seen o) {

        return Double.compare(o.väärtus, this.väärtus);
    }

    //toString meetod
    //Kirjeldatakse seent
    @Override
    public String toString() {
        return "See " + this.nimi + " on " + this.pikkus + "cm pikk " +
                "ning " + this.kübaraLäbimõõt + "cm suuruse kübaraga. " +
                "Selle seene väärtus on " + this.väärtus + " münti.";
    }
}

