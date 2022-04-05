import java.util.Scanner;

public class Peaklass {

    //Meetod, kus genereeritakse 3 uut seent
    public static Seen[] looUuedSeened(){
        Seen[] out = new Seen[3];
        for(int i = 0; i < 3; i++){

            //!!
            //Luua funktsioon mis loeb teksti failist seene nime,
            //ning söögiseene korral ka kas teda on tarvis kupatada.
            //!!

            //Algul genereeritakse suvaline väärtus, mille põhjal otsustatakse, kas tegu on söögi- või mürgiseenega.
            //Seejärel genereeritakse seenele suvaline pikkus ning läbimõõt poollõikudes [1, 30) ja [1, 15) vastavalt.
            //Samuti otsustatakse, kas see seen oleks ussitanud, kui valiti söögiseen.
            //Seened luuakse eelnevalt genereeritud parameetritega, ning lisatakse seente massiivi
            double suvalineVäärtus = Math.random();
            double pikkus = Double.parseDouble(String.format("%.2f", Math.random()*29+1));
            double laius = Double.parseDouble(String.format("%.2f", Math.random()*14+1));
            if(suvalineVäärtus < 0.35){
                boolean ussitanud = Math.random() < 0.3;
                Söögiseen temp = new Söögiseen("Söök", pikkus, laius, false, ussitanud);
                out[i] = temp;
            }else{
                Mürgiseen temp = new Mürgiseen("Mürk", pikkus, laius);
                out[i] = temp;
            }
        }
        return out;
    }

    //Peameetod
    public static void main(String[] args) {
        //Otsus, hakkab näitama, mis väärtusega otsuseid tehakse,
        //algul on ta mitte null, et peamine töö tsükkel alustada saaks
        int otsus = 1;
        //Samuti moodustan tühja sõne, mis saab olema seenelise nimi.
        // Algul on ta tühi, et saaks alustada nime küsimise tsükkel,
        // sest nimed, mille pikkus on null ei sobi.
        String seeneliseNimi = "";

        //Scanner objekt scan hakkab sisendeid võtma.
        Scanner scan = new Scanner(System.in);
        //Nime küsimise tsükkel
        while(seeneliseNimi.length() < 1){
            System.out.println("Sisestage seenelise nimi: ");
            seeneliseNimi = scan.nextLine();
        }
        //Laiskuse sisestamine
        System.out.println("Sisestage seenelise laiskus lõigus [0, 1]: ");
        double seeneliseLaiskus = scan.nextDouble();

        //Moodustame Seenelise klassi isendi korjaja, keda hakkame kontrollima
        Seeneline korjaja = new Seeneline(seeneliseNimi, seeneliseLaiskus);

        //Moodustan ka tühja seente massiivi seened, kuhu tekivad genereeritud seened.
        Seen[] seened;

        while(otsus != 0) {
            seened = looUuedSeened();
            //System.out.println(Arrays.toString(seened));
            System.out.println("Seenelisel on ees kolm seent, vali neist üks, " +
                    "kirjutades 1, 2 või 3, ja liikuge edasi. \n" +
                    "Või kui soovite hoopis metsast lahkuda, kirjutage 0.");
            //Võtan otsuse
            otsus = scan.nextInt();
            //Kui otsus on 1, 2 või 3, leia, kas korjaja lisaks valitud seene oma korvi
            if (otsus > 0 && otsus < 4) {
                if(korjaja.lisaSeen(seened[otsus - 1])){
                    System.out.println("Seen sai korvi lisatud");
                }else{
                    System.out.println("Seen polnud kõlbulik");
                }
            //Kui otsus on 0, siis antakse kasutajale teada, et tema soov täidetakse ning tsükkel lõppeb
            } else if (otsus == 0) {
                System.out.println("Nii sündigu!");
            //Või kui otsus pole ükski eelnevatest, annab programm teada,
            //et midagi aru ei saanud, korjaja sattus segadusse ja kõndis lihtsalt edasi
            } else {
                System.out.println("Seeneline ei mõistnud päris mida ta teha tahtis, ning kõndis edasi.");
            }
        }

        //Väljastan peale retke iseloomustuse seenelkäigust ning korjajast. Samuti tema seenekorvi sisu.
        System.out.println("Peale toredat seenelkäiku on " + korjaja.getNimi() + " jõudnud koju.");
        System.out.println(korjaja);
        System.out.println(korjaja.getNimi() + " seenekorv: ");
        korjaja.väljastaKorjatudSeened();
    }

}
