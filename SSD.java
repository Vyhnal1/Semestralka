
/**
 * Write a description of class SSD here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SSD {
    /**
     * Constructor for objects of class SSD
     */
    private Segment segmentA;
    private Segment segmentB;
    private Segment segmentC;
    private Segment segmentD;
    private Segment segmentE;
    private Segment segmentF;
    private Segment segmentG;
    
    public SSD(int x, int y, int dlzka, int sirka) {
        int vyskaVodorovnehoSegmentu = sirka;
        int dlzkaVodorovnehoSegmentu = dlzka;

        int vyskaZvislehoSegmentu = dlzkaVodorovnehoSegmentu;
        int dlzkaZvislehoSegmentu = vyskaVodorovnehoSegmentu;

        this.segmentA = new Segment(
            x + dlzkaZvislehoSegmentu, 
            y, 
            dlzkaVodorovnehoSegmentu,
            vyskaVodorovnehoSegmentu);

        this.segmentB = new Segment(
            x + dlzkaZvislehoSegmentu + dlzkaVodorovnehoSegmentu, 
            y + vyskaVodorovnehoSegmentu, 
            dlzkaZvislehoSegmentu,
            vyskaZvislehoSegmentu);

        this.segmentC = new Segment(
            x + dlzkaZvislehoSegmentu + dlzkaVodorovnehoSegmentu, 
            y + 2 * vyskaVodorovnehoSegmentu + vyskaZvislehoSegmentu, 
            dlzkaZvislehoSegmentu,
            vyskaZvislehoSegmentu);

        this.segmentD = new Segment(
            x + dlzkaZvislehoSegmentu, 
            y + 2 * vyskaVodorovnehoSegmentu + 2 * vyskaZvislehoSegmentu, 
            dlzkaVodorovnehoSegmentu,
            vyskaVodorovnehoSegmentu);

        this.segmentE = new Segment(
            x , 
            y + 2 * vyskaVodorovnehoSegmentu + vyskaZvislehoSegmentu, 
            dlzkaZvislehoSegmentu,
            vyskaZvislehoSegmentu);

        this.segmentF = new Segment(
            x , 
            y + vyskaVodorovnehoSegmentu, 
            dlzkaZvislehoSegmentu,
            vyskaZvislehoSegmentu);

        this.segmentG= new Segment(
            x + dlzkaZvislehoSegmentu, 
            y + vyskaVodorovnehoSegmentu + vyskaZvislehoSegmentu, 
            dlzkaVodorovnehoSegmentu,
            vyskaVodorovnehoSegmentu);

        this.zasvietVsetky();
    }

    public void zasvietVsetky() {
        this.segmentA.zapni();
        this.segmentB.zapni();
        this.segmentC.zapni();
        this.segmentD.zapni();
        this.segmentE.zapni();
        this.segmentF.zapni();
        this.segmentG.zapni();
    }
    
    public void zobraz0() {
        this.segmentA.zapni();
        this.segmentB.zapni();
        this.segmentC.zapni();
        this.segmentD.zapni();
        this.segmentE.zapni();
        this.segmentF.zapni();
        this.segmentG.vypni();
    }

    public void zobraz1() {
        this.segmentA.vypni();
        this.segmentB.zapni();
        this.segmentC.zapni();
        this.segmentD.vypni();
        this.segmentE.vypni();
        this.segmentF.vypni();
        this.segmentG.vypni();
    }

    public void zobraz2() {
        this.segmentA.zapni();
        this.segmentB.zapni();
        this.segmentC.vypni();
        this.segmentD.zapni();
        this.segmentE.zapni();
        this.segmentF.vypni();
        this.segmentG.zapni();
    }

    public void zobraz3() {
        this.segmentA.zapni();
        this.segmentB.zapni();
        this.segmentC.zapni();
        this.segmentD.zapni();
        this.segmentE.vypni();
        this.segmentF.vypni();
        this.segmentG.zapni();
    }

    public void zobraz4() {
        this.segmentA.vypni();
        this.segmentB.zapni();
        this.segmentC.zapni();
        this.segmentD.vypni();
        this.segmentE.vypni();
        this.segmentF.zapni();
        this.segmentG.zapni();
    }

    public void zobraz5() {
        this.segmentA.zapni();
        this.segmentB.vypni();
        this.segmentC.zapni();
        this.segmentD.zapni();
        this.segmentE.vypni();
        this.segmentF.zapni();
        this.segmentG.zapni();
    }

    public void zobraz6() {
        this.segmentA.zapni();
        this.segmentB.vypni();
        this.segmentC.zapni();
        this.segmentD.zapni();
        this.segmentE.zapni();
        this.segmentF.zapni();
        this.segmentG.zapni();
    }

    public void zobraz7() {
        this.segmentA.zapni();
        this.segmentB.zapni();
        this.segmentC.zapni();
        this.segmentD.vypni();
        this.segmentE.vypni();
        this.segmentF.vypni();
        this.segmentG.vypni();
    }

    public void zobraz8() {
        this.segmentA.zapni();
        this.segmentB.zapni();
        this.segmentC.zapni();
        this.segmentD.zapni();
        this.segmentE.zapni();
        this.segmentF.zapni();
        this.segmentG.zapni();
    }

    public void zobraz9() {
        this.segmentA.zapni();
        this.segmentB.zapni();
        this.segmentC.zapni();
        this.segmentD.zapni();
        this.segmentE.vypni();
        this.segmentF.zapni();
        this.segmentG.zapni();
    }
    
    public void zobrazCislo(int cislo) {
        switch (cislo) {
            case 1:
            this.zobraz1();
            break;
            case 2:
            this.zobraz2();
            break;
            case 3:
            this.zobraz3();
            break;
            case 4:
            this.zobraz4();
            break;
            case 5:
            this.zobraz5();
            break;
            case 6:
            this.zobraz6();
            break;
            case 7:
            this.zobraz7();
            break;
            case 8:
            this.zobraz8();
            break;
            case 9:
            this.zobraz9();
            break;
            default:
            this.zobraz0();
            break;
        }  
    }
}



