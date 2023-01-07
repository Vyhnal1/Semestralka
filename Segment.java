
/**
 * Write a description of class Segment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Segment {
    /**
     * Constructor for objects of class Segment
     */
    private ObdlznikA obdlznik;
    public Segment(int x, int y, int dlzka, int vyska) {
        // initialise instance variables
        this.obdlznik = new ObdlznikA();
        this.obdlznik.zmenStrany(dlzka, vyska);
        this.obdlznik.zmenPolohu(x, y);
    }
    
    public void zapni() {
        this.obdlznik.zobraz();
    }
    
    public void vypni() {
        this.obdlznik.skry();
    }
}
