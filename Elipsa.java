import java.awt.geom.Ellipse2D;

/**
 * Elipsa, s ktorým možno pohybovať a nakreslí sa na plátno.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  Michal Mrena
 * @version 2.0  (7 November 2022)
 */

public class Elipsa {
    
    private static final int INIT_X = 20;
    private static final int INIT_Y = 60;
    private static final int INIT_OS_X = 60;
    private static final int INIT_OS_Y = 30;
    private static final String INIT_FARBA = "blue";
    
    private Platno.PopisTvaru popis;
    private boolean jeViditelny;
    
    /**
     * Vytvor novú elipsu preddefinovanej farby na preddefinovanej pozícii. 
     */
    public Elipsa() {
        this(INIT_X, INIT_Y);
    }
    
    /**
     * Vytvor novú elipsu preddefinovanej farby na daných pozícii. 
     */
    public Elipsa(int x, int y) {
        final Ellipse2D.Double tvar = new Ellipse2D.Double(x, y, INIT_OS_X, INIT_OS_Y);
        this.popis = new Platno.PopisTvaru(tvar, INIT_FARBA);
        this.jeViditelny = false;
    }

    /**
     * (Elipsa) Zobraz sa.
     */
    public void zobraz() {
        if (!this.jeViditelny) {
            this.zaregistrujSaDoPlatna();
            this.jeViditelny = true;
        }
    }
    
    /**
     * (Elipsa) Skry sa.
     */
    public void skry() {
        if (this.jeViditelny) {
            this.odregistrujSaZPlatna();
            this.jeViditelny = false;
        }
    }
    
    /**
     * (Elipsa) Vráti x súradnicu ľavého horného rohu ohraničujúceho elipsy.
     */
    public int getLavyHornyX() {
        return (int)this.getTvar().getBounds2D().getX();
    }
    
    /**
     * (Elipsa) Vráti y súradnicu ľavého horného rohu ohraničujúceho elipsy.
     */
    public int getLavyHornyY() {
        return (int)this.getTvar().getBounds2D().getY();
    }
    
    /**
     * (Elipsa) Vráti šírku ohraničujúceho obdĺžnika.
     */
    public int getSirka() {
        return (int)this.getTvar().getBounds2D().getWidth();
    }
    
    /**
     * (Elipsa) Vráti výšku ohraničujúceho obdĺžnika.
     */
    public int getVyska() {
        return (int)this.getTvar().getBounds2D().getHeight();
    }
    
    /**
     * (Elipsa) Posuň sa vpravo o pevnú dĺžku. 
     */
    public void posunVpravo() {
        this.posunVodorovne(20);
    }

    /**
     * (Elipsa) Posuň sa vľavo o pevnú dĺžku. 
     */
    public void posunVlavo() {
        this.posunVodorovne(-20);
    }

    /**
     * (Elipsa) Posuň sa hore o pevnú dĺžku. 
     */
    public void posunHore() {
        this.posunZvisle(-20);
    }

    /**
     * (Elipsa) Posuň sa dole o pevnú dĺžku. 
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
            this.getSirka(),
            this.getVyska()
        );
    }

    /**
     * (Elipsa) Posuň sa zvisle o dĺžku danú parametrom. 
     */
    public void posunZvisle(int vzdialenost) {
        this.getTvar().setFrame(
            this.getLavyHornyX(),
            this.getLavyHornyY() + vzdialenost,
            this.getSirka(),
            this.getVyska()
        );
    }

    /**
     * (Elipsa) Zmeň veľkosti osí na hodnoty dané parametrami.
     * Veľkosť musí byť nezáporné celé číslo. 
     */
    public void zmenOsi(int osX, int osY) {
        this.getTvar().setFrame(
            this.getLavyHornyX(),
            this.getLavyHornyY(),
            osX,
            osY
        );
    }

    /**
     * (Elipsa) Zmeň farbu na hodnotu danú parametrom.
     * Nazov farby musí byť po anglicky.
     * Možné farby sú tieto:
     * červená - "red"
     * žltá    - "yellow"
     * modrá   - "blue"
     * zelená  - "green"
     * fialová - "magenta"
     * čierna  - "black"
     * biela   - "white"
     * hnedá   - "brown"
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
