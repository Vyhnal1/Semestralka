
/**
 * Write a description of class DvojSSD here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DvojSSD {
    /**
     * Constructor for objects of class DvojSSD
     */
    private SSD lavySSD;
    private SSD pravySSD;
    private int zobrazovaneCislo;
    private int hornaHranica;
    
    public DvojSSD(int x, int y, int rozostup, int dlzka, int sirka, int hornaHranica) {
        // initialise instance variables
        this.hornaHranica = hornaHranica;
        this.lavySSD = new SSD(x, y, dlzka, sirka);
        this.pravySSD = new SSD(
            x + 2 * sirka + dlzka + rozostup,
            y,
            dlzka,
            sirka);
        this.setZobrazovaneCislo(0);
    }
    
    public int getZobrazovaneCislo() {
        return this.zobrazovaneCislo;
    }
    
    public void zobrazCislo() {
       int cisloPravySSD = this.zobrazovaneCislo % 10;
       int cisloLavySSD = (this.zobrazovaneCislo / 10) % 10;
       this.lavySSD.zobrazCislo(cisloLavySSD);
       this.pravySSD.zobrazCislo(cisloPravySSD);
        
    }
    
    public void setZobrazovaneCislo(int cislo) {
        if (cislo >= 0 && cislo < this.hornaHranica) {
            this.zobrazovaneCislo = cislo;
            this.zobrazCislo();
        }
    }
    
    public void tik() {
        int noveCislo = (this.zobrazovaneCislo + 1 ) % this.hornaHranica;
        this.setZobrazovaneCislo(noveCislo);
    }
}
