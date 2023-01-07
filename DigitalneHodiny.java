
/**
 * Write a description of class DigitalneHodiny here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DigitalneHodiny {
    private DvojSSD hodiny;
    private DvojSSD minuty;
    private DvojSSD sekundy;
    public DigitalneHodiny(int x, int y) {
        // initialise instance variables
        int rozostupDvojSSD = 50;
        int dlzkaSegmentu = 57;
        int sirkaSegmentu = 17;
        int rozostupSSD = 10;

        int dlzkaDvojSSD = 2 * dlzkaSegmentu + 4 
            * sirkaSegmentu + rozostupSSD + rozostupDvojSSD; 

        this.hodiny = new DvojSSD(
            x, 
            y, 
            rozostupSSD, 
            dlzkaSegmentu, 
            sirkaSegmentu,
            24);
        this.minuty = new DvojSSD(
            x + dlzkaDvojSSD, 
            y, 
            rozostupSSD, 
            dlzkaSegmentu, 
            sirkaSegmentu,
            60);
            
        this.sekundy = new DvojSSD(
            x + 2 * dlzkaDvojSSD, 
            y, 
            rozostupSSD, 
            dlzkaSegmentu, 
            sirkaSegmentu,
            60);
    }
    
    public void tik() {
        this.sekundy.tik();
        if(this.sekundy.getZobrazovaneCislo() == 0) {
            this.minuty.tik();
            if(this.minuty.getZobrazovaneCislo() == 0) {
                this.hodiny.tik();
            }
        }
    }
}
