import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Peaklass {

    //võtame seente info failist
    public static List <String[]> seenteInfo (String failiNimi) throws Exception {
        List<String[]> info = new ArrayList<>();
        File fail = new File(failiNimi);
        try (Scanner failiScan = new Scanner(fail, StandardCharsets.UTF_8)) {
            while (failiScan.hasNextLine()) {
                String rida = failiScan.nextLine();
                String[] osadeks = rida.split(";");
                info.add(osadeks);
            }
        }
        return info;
    }

    //Meetod, kus genereeritakse 3 uut seent
    public static Seen[] looUuedSeened(List<String[]> kõikMürgiseened, List<String[]> kõikSöögiseened){

        //Loome 3 uut seent
        Seen[] out = new Seen[3];
        for(int i = 0; i < 3; i++){

            //Algul genereeritakse suvaline väärtus, mille põhjal otsustatakse, kas tegu on söögi- või mürgiseenega.
            //Seejärel genereeritakse uued seened, failist loetud informatsiooni põhjal.
            //Samuti otsustatakse, kas see seen on ussitanud, kui valiti söögiseen.
            //Seened luuakse genereeritud parameetritega, ning lisatakse seente massiivi.

            if(Math.random() < 0.35){
                Collections.shuffle(kõikSöögiseened);
                String[] info = kõikSöögiseened.get(0);

                Söögiseen temp = new Söögiseen(info[0], Double.parseDouble(info[2]), info[1].equals("peab"));

                //Varieerib igal väljakutsel ussitamise kohta uue tõeväärtuse ning kui on ussitanud kaob seene väärtus
                temp.setOnUssitanud(Math.random() < 0.3);
                if (temp.kasOnUssitanud()){
                    temp.setVäärtus(0);
                }

                out[i] = temp;
            }else{
                Collections.shuffle(kõikMürgiseened);
                String[] info = kõikMürgiseened.get(0);
                Mürgiseen temp = new Mürgiseen(info[0], Double.parseDouble(info[1]));
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
        List<String[]> söögiseenteInfo = seenteInfo("soogiseened.txt");
        List<String[]> mürgiseenteInfo = seenteInfo("murgiseened.txt");
     

        //Samuti moodustan tühja sõne, mis saab olema seenelise nimi.
        // Algul on ta tühi, et saaks alustada nime küsimise tsükkel,
        // sest nimed, mille pikkus on null ei sobi.
        String seeneliseNimi = "";

        // väike sissejuhatus mängijale, mis toimuma hakkab
        System.out.println(
                """
                        \t*** Seenele ***
                         ----------------------------------------\s
                        Ühel päeval ärkasid ja avastasid, et sinu metsas kasvab\s
                        väga palju seeni. Otsustasid, et proovid õnne nendega\s
                        rikkaks saada ja lähedki metsa seenele.\s
                        """);

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
        Seen[] seened;

        while (otsus != 0) {
            seened = looUuedSeened(seenteInfo);
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

        System.out.println("""
                 Nüüd kus seened on korjatud, on sul 2 valikut,\s
                 kas oma korv tervelt maha müüa või müüa kõige väärtuslikum\s
                seen sealt, mille valid?\s
                 - müü terve korv\s
                 - müü kalleim seen\s
                \s""");

        String valik = "";

        while (valik.length() < 1) {

            valik = scan.nextLine();

            if (valik.equals("müü terve korv")) {
                System.out.println("Said tuluks " + korjaja.korviVäärtus(korjaja.getSeenekorv()) + " münti.");
            } else if (valik.equals(("müü kalleim seen"))) {
                System.out.println("Sinu kalleim seen oli " + korjaja.getSeenekorv().get(0).getNimi() + " ja see oli väärt " +
                        korjaja.getSeenekorv().get(0).getVäärtus() + " münti.");
            } else {
                System.out.println("tee üks valik");
            }
        }
    }
}

