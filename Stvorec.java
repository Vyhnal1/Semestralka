import java.awt.Rectangle;

/**
 * Štvorec, s ktorým možno pohybovať a nakreslí sa na plátno.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  Michal Mrena
 * @version 2.0  (7 November 2022)
 */

public class Stvorec {

    private static final int INIT_X = 60;
    private static final int INIT_Y = 50;
    private static final int INIT_STRANA = 30;
    private static final String INIT_FARBA = "red";

    private boolean jeViditelny;
    private Platno.PopisTvaru popis;

    /**
     * Vytvor nový štvorec preddefinovanej farby na preddefinovanej pozícii.
     */
    public Stvorec() {
        this(INIT_X, INIT_Y);
    }
    
    /**
     * Vytvor nový štvorec preddefinovanej farby na danej pozícii.
     */
    public Stvorec(int x, int y) {
        this.jeViditelny = false;
        final Rectangle tvar = new Rectangle(x, y, INIT_STRANA, INIT_STRANA);
        this.popis = new Platno.PopisTvaru(tvar, INIT_FARBA);
    }

    /**
     * (Štvorec) Zobraz sa.
     */
    public void zobraz() {
        if (!this.jeViditelny) {
            this.zaregistrujSaDoPlatna();
            this.jeViditelny = true;
        }
    }
    
    /**
     * (Štvorec) Skry sa.
     */
    public void skry() {
        if (this.jeViditelny) {
            this.odregistrujSaZPlatna();
            this.jeViditelny = false;
        }
    }

    /**
     * (Štvorec) Vráti x súradnicu ľavého horného rohu štvorca
     */
    public int getLavyHornyX() {
        return (int)this.getTvar().getX();
    }
    
    /**
     * (Štvorec) Vráti y súradnicu ľavého horného rohu štvorca
     */
    public int getLavyHornyY() {
        return (int)this.getTvar().getY();
    }
    
    /**
     * (Štvorec) Vráti veľkosť strany štvorca.
     */
    public int getStrana() {
        return (int)this.getTvar().getWidth();
    }
    
    /**
     * (Štvorec) Posuň sa vpravo o pevnú dĺžku.
     */
    public void posunVpravo() {
        this.posunVodorovne(20);
    }

    /**
     * (Štvorec) Posuň sa vľavo o pevnú dĺžku.
     */
    public void posunVlavo() {
        this.posunVodorovne(-20);
    }

    /**
     * (Štvorec) Posuň sa hore o pevnú dĺžku.
     */
    public void posunHore() {
        this.posunZvisle(-20);
    }

    /**
     * (Štvorec) Posuň sa dole o pevnú dĺžku.
     */
    public void posunDole() {
        this.posunZvisle(20);
    }
    
    /**
     * (Štvorec) Posuň sa vodorovne o dĺžku danú parametrom.
     */
    public void posunVodorovne(int vzdialenost) {
        this.getTvar().setLocation(
            this.getLavyHornyX() + vzdialenost,
            this.getLavyHornyY()
        );
    }

    /**
     * (Štvorec) Posuň sa zvisle o dĺžku danú parametrom.
     */
    public void posunZvisle(int vzdialenost) {
        this.getTvar().setLocation(
            this.getLavyHornyX(),
            this.getLavyHornyY() + vzdialenost
        );
    }

    /**
     * (Štvorec) Zmeň dĺžku strany na hodnotu danú parametrom.
     * Dĺžka strany musí byť nezáporné celé číslo. 
     */
    public void zmenStranu(int dlzka) {
        this.getTvar().setSize(dlzka, dlzka);
    }

    /**
     * (Štvorec) Zmeň farbu na hodnotu danú parametrom.
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
    
    private Rectangle getTvar() {
        return (Rectangle)this.popis.getTvar();
    }
}
