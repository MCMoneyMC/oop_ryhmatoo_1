import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Peaklass {

    //võtame seente info failist
    public static List[] seenteInfo (String failiNimi) throws Exception {
        List<Seen> kõikSöögiseened = new ArrayList<>();
        List<Seen> kõikMürgiseened = new ArrayList<>();
        File fail = new File(failiNimi);
        try (Scanner failiScan = new Scanner(fail, "UTF-8")) {
            while (failiScan.hasNextLine()) {
                String rida = failiScan.nextLine();
                String[] osadeks = rida.split(";");
                if (osadeks[1].equals("mürgine")) {
                    kõikMürgiseened.add(new Mürgiseen(osadeks[0], 0, 0, Double.parseDouble(osadeks[2])));
                } else {
                    kõikSöögiseened.add(new Söögiseen(osadeks[0], 0, 0, Double.parseDouble(osadeks[2]), osadeks[1], false));
                }
            }
        }
        // kuna ma tegin 2 erinevat listi, sest neid on vaja hiljem eraldi kasutada,
        // ja funktioon lubab väljastada ainult ühe listi, siis me tegime ühe suurema listi
        // kus sees on 2 väiksemat listi
        List[] inception = {kõikMürgiseened, kõikSöögiseened};
        return inception;
    }

    //Meetod, kus genereeritakse 3 uut seent
    public static Seen[] looUuedSeened(List[] inception){

        // pakime meie koondlisti lasti kasutatavateks listideks
        List<Seen> kõikMürgiseened = inception[0];
        List<Seen> kõikSöögiseened = inception[1];

        // loome 3 uut seent
        Seen[] out = new Seen[3];
        for(int i = 0; i < 3; i++){

            //!!
            //Luua funktsioon mis loeb teksti failist seene nime,
            //ning söögiseene korral ka kas teda on tarvis kupatada.
            //!!
            //DONEZO

            //Algul genereeritakse suvaline väärtus, mille põhjal otsustatakse, kas tegu on söögi- või mürgiseenega.
            //Seejärel genereeritakse seenele suvaline pikkus ning läbimõõt poollõikudes [1, 30) ja [1, 15) vastavalt.
            //Samuti otsustatakse, kas see seen oleks ussitanud, kui valiti söögiseen.
            //Seened luuakse eelnevalt genereeritud parameetritega, ning lisatakse seente massiivi
            double suvalineVäärtus = Math.random();

            if(suvalineVäärtus < 0.35){
                Collections.shuffle(kõikSöögiseened);
                Söögiseen temp = (Söögiseen) kõikSöögiseened.get(0);
                out[i] = temp;
            }else{
                Collections.shuffle(kõikMürgiseened);
                Mürgiseen temp = (Mürgiseen) kõikMürgiseened.get(0);
                out[i] = temp;
            }
        }
        return out;
    }

    //Peameetod
    public static void main(String[] args) throws Exception {
        //Otsus, hakkab näitama, mis väärtusega otsuseid tehakse,
        //algul on ta mitte null, et peamine töö tsükkel alustada saaks
        int otsus = 1;

        // loeme info failist sisse
        List[] inception = seenteInfo("seened.txt");

        //Samuti moodustan tühja sõne, mis saab olema seenelise nimi.
        // Algul on ta tühi, et saaks alustada nime küsimise tsükkel,
        // sest nimed, mille pikkus on null ei sobi.
        String seeneliseNimi = "";

        // väike sissejuhatus mängijale, mis toimuma hakkab
        System.out.println(
                "\t*** Seenele ***\n" +
                        " ---------------------------------------- \n" +
                        "Ühel päeval ärkasid ja avastasid, et sinu metsas kasvab \n" +
                        "väga palju seeni. Otsustasid, et proovid õnne nendega \n" +
                        "rikkaks saada ja lähedki metsa seenele. \n");

        //Scanner objekt scan hakkab sisendeid võtma.
        Scanner scan = new Scanner(System.in);
        //Nime küsimise tsükkel
        while (seeneliseNimi.length() < 1) {
            System.out.println("Sisestage seenelise nimi: ");
            seeneliseNimi = scan.nextLine();
        }
        //Laiskuse sisestamine
        System.out.println("Sisestage seenelise laiskus lõigus [0, 1]: ");
        double seeneliseLaiskus = scan.nextDouble();

        //Moodustame Seenelise klassi isendi korjaja, keda hakkame kontrollima
        Seeneline korjaja = new Seeneline(seeneliseNimi, seeneliseLaiskus);

        //Moodustan ka tühja seente massiivi seened, kuhu tekivad genereeritud seened.
        Seen[] seened = {};

        while (otsus != 0) {
            seened = looUuedSeened(inception);
            //System.out.println(Arrays.toString(seened));
            System.out.println("Seenelisel on ees kolm seent, vali neist üks, " +
                    "kirjutades 1, 2 või 3, ja liikuge edasi. \n" +
                    "Või kui soovite hoopis metsast lahkuda, kirjutage 0.");
            //Võtan otsuse
            otsus = scan.nextInt();
            //Kui otsus on 1, 2 või 3, leia, kas korjaja lisaks valitud seene oma korvi
            if (otsus > 0 && otsus < 4) {
                if (korjaja.lisaSeen(seened[otsus - 1])) {
                    System.out.println("Seen sai korvi lisatud");
                } else {
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

        System.out.println("\n Nüüd kus seened on korjatud, on sul 2 valikut, \n" +
                " kas oma korv tervelt maha müüa või müüa kõige väärtuslikum \n" +
                "seen sealt, mille valid? \n" +
                " - müü terve korv \n" +
                " - müü kalleim seen \n ");

        String valik = "";

        while (valik.length() < 1) {

            valik = scan.nextLine();

            if (valik.equals("müü terve korv")) {
                System.out.println("Said tuluks " + korjaja.korviVäärtus(seened));
            } else if (valik.equals(("müü kalleim seen"))) {
                System.out.println("Said tuluks " + seened[0].getVäärtus());
            } else {
                System.out.println("tee üks valik");
            }
        }
    }
}

