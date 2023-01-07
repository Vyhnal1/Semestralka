import java.awt.geom.Ellipse2D;

/**
 * Kruh, s ktorým možno pohybovať a nakreslí sa na plátno.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  Michal Mrena
 * @version 2.0  (7 November 2022)
 */

public class Kruh {

    private static final int INIT_X = 20;
    private static final int INIT_Y = 60;
    private static final int INIT_PRIEMER = 30;
    private static final String INIT_FARBA = "blue";

    private boolean jeViditelny;
    private Platno.PopisTvaru popis;
    
    /**
     * Vytvor nový kruh preddefinovanej farby na preddefinovanej pozícii. 
     */
    public Kruh() {
        this(INIT_X, INIT_Y);
    }
    
    /**
     * Vytvor nový kruh preddefinovanej farby na danej pozícii. 
     */
    public Kruh(int x, int y) {
        this.jeViditelny = false;
        final Ellipse2D.Double tvar = new Ellipse2D.Double(x, y, INIT_PRIEMER, INIT_PRIEMER);
        this.popis = new Platno.PopisTvaru(tvar, INIT_FARBA);
    }

    /**
     * (Kruh) Zobraz sa.
     */
    public void zobraz() {
        if (!this.jeViditelny) {
            this.zaregistrujSaDoPlatna();
            this.jeViditelny = true;
        }
    }
    
    /**
     * (Kruh) Skry sa.
     */
    public void skry() {
        if (this.jeViditelny) {
            this.odregistrujSaZPlatna();
            this.jeViditelny = false;
        }
    }
    
    /**
     * (Kruh) Vráti x súradnicu ľavého horného rohu ohraničujúceho kruhu.
     */
    public int getLavyHornyX() {
        return (int)this.getTvar().getBounds2D().getX();
    }
    
    /**
     * (Kruh) Vráti y súradnicu ľavého horného rohu ohraničujúceho kruhu.
     */
    public int getLavyHornyY() {
        return (int)this.getTvar().getBounds2D().getY();
    }
    
    /**
     * (Kruh) Vráti priemer kruhu.
     */
    public int getPriemer() {
        return (int)this.getTvar().getWidth();
    }
    
    /**
     * (Kruh) Posuň sa vpravo o pevnú dĺžku. 
     */
    public void posunVpravo() {
        this.posunVodorovne(20);
    }

    /**
     * (Kruh) Posuň sa vľavo o pevnú dĺžku. 
     */
    public void posunVlavo() {
        this.posunVodorovne(-20);
    }

    /**
     * (Kruh) Posuň sa hore o pevnú dĺžku. 
     */
    public void posunHore() {
        this.posunZvisle(-20);
    }

    /**
     * (Kruh) Posuň sa dole o pevnú dĺžku. 
     */
    public void posunDole() {
        this.posunZvisle(20);
    }

    /**
     * (Kruh) Posuň sa vodorovne o dĺžku danú parametrom. 
     */
    public void posunVodorovne(int vzdialenost) {
        this.getTvar().setFrame(
            this.getLavyHornyX() + vzdialenost,
            this.getLavyHornyY(),
            this.getPriemer(),
            this.getPriemer()
        );
    }

    /**
     * (Kruh) Posuň sa zvisle o dĺžku danú parametrom. 
     */
    public void posunZvisle(int vzdialenost) {
        this.getTvar().setFrame(
            this.getLavyHornyX(),
            this.getLavyHornyY() + vzdialenost,
            this.getPriemer(),
            this.getPriemer()
        );
    }

    /**
     * (Kruh) Zmeň priemer na hodnotu danú parametrom.
     * Priemer musí byť nezáporné celé číslo. 
     */
    public void zmenPriemer(int priemer) {
        this.getTvar().setFrame(
            this.getLavyHornyX(),
            this.getLavyHornyY(),
            priemer,
            priemer
        );
    }

    /**
     * (Kruh) Zmeň farbu na hodnotu danú parametrom.
     * Nazov farby musí byť po anglicky.
     * Možné farby sú tieto:
     * červená - "red"
     * žltá    - "yellow"
     * modrá   - "blue"
     * zelená  - "green"
     * fialová - "magenta"
     * čierna  - "black"
     */
    public void zmenFarbu(String farba) {
        this.popis.setFarba(farba);
    }

    private void zaregistrujSaDoPlatna() {
        Platno.dajPlatno().zaregistrujTvar(
            this,
            this.popis
        );
    }
    
    private void odregistrujSaZPlatna() {
        Platno.dajPlatno().odregistrujTvar(this);
    }
    
    private Ellipse2D.Double getTvar() {
        return (Ellipse2D.Double)this.popis.getTvar();
    }
}
