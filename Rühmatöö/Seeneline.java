import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Seeneline {

    private String nimi;
    private double laiskus;
    private List<Seen> seenekorv;

    //Konstruktor
    public Seeneline(String nimi, double laiskus){
        this.nimi = nimi;
        //Laiskus peab jääma lõiku [0, 1], kui ta sinna ei jää omistatakse seenelisele laiskus tase 0,
        //ning kasutajat sellest ka teavitatakse
        if(0 <= laiskus && laiskus <= 1) {
            this.laiskus = laiskus;
        }else{
            this.laiskus = 0;
            System.out.println("NB! Seenelise laiskus peab jääma lõiku [0, 1], " +
                    "kuna sisestatud arv seda tingimust ei täitnud on tema laiskus 0.");
        }
        this.seenekorv = new ArrayList<>();
    }

    //Seene lisamise meetod
    public boolean lisaSeen(Seen seen){
        //Kui seen otsustatakse üles korjata, lisatakse ta seente listi ning otsuse tõeväärtus tagastatakse
        boolean saiKorjatud = seen.onMõtetKorjata(this);
        if(saiKorjatud){
            this.seenekorv.add(seen);
        }
        return saiKorjatud;
    }

    //Seente väljastamise meetod
    //Igale reale väljastatakse mitmenda seenega on tegu, ning selle seene kirjeldus.
    public void väljastaKorjatudSeened(){
        List<Seen> seened = this.getSeenekorv();
        for(int i = 0; i < seened.size(); i++){
            System.out.println(i+1 + ". " + seened.get(i));
        }
    }

    public double korviVäärtus(Seen[] seenekorv) {
        double summa = 0;
        for (Seen seen : seenekorv) {
            summa= summa + seen.getVäärtus();
        }
        return summa;
    }

    //Get-id
    public String getNimi() {
        return this.nimi.substring(0,1).toUpperCase() + this.nimi.substring(1).toLowerCase();
    }
    public double getLaiskus() {
        return laiskus;
    }
    public List<Seen> getSeenekorv() {
        Collections.sort(this.seenekorv);
        return seenekorv;
    }

    //Set-id
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    public void setLaiskus(double laiskus) {
        this.laiskus = laiskus;
    }
    public void setSeenekorv(List<Seen> seenekorv) {
        this.seenekorv = seenekorv;
    }

    //toString meetod
    //Kirjeldatakse seenelist, tema korvi ning laiskust
    @Override
    public String toString() {
        return this.getNimi() + " on seeneline, kellel on korvis koguni " + this.seenekorv.size() + " seent. " +
                "Tema laiskust hindaksin tasemel ~" + (int) Math.round(this.laiskus*10) + "/10.";
    }
}
