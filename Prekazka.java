
public class Prekazka {
    private int x;
    private int y;
    private int sirkaTrubky;
    private int vyskaTrubky;
    private Obdlznik obdlznik;
    
    public Prekazka(int x, int y, int sirkaTrubky, int vyskaTrubky) {
        // initialise instance variables
        this.x = x;
        this.y = y;
        this.sirkaTrubky = sirkaTrubky;
        this.vyskaTrubky = vyskaTrubky;
        this.obdlznik = new Obdlznik(x, y);
        this.obdlznik.zmenStrany(sirkaTrubky, vyskaTrubky);
        this.obdlznik.zmenFarbu("green");
        this.obdlznik.zobraz();
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getVyska() {
        return this.vyskaTrubky;
    }
    
    public int getSirka() {
        return this.sirkaTrubky;
    }
    
    public void tik() {
        this.x = x - 5;
        this.obdlznik.posunVodorovne(-5);
    }
}
