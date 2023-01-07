import java.awt.Rectangle;

/**
 * Obdĺžnik, s ktorým možno pohybovať a nakreslí sa na plátno.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  Michal Mrena
 * @version 2.0  (7 November 2022)
 */

public class Obdlznik {

    private static final int INIT_STRANA_A = 30;
    private static final int INIT_STRANA_B = 60;
    private static final int INIT_X = 60;
    private static final int INIT_Y = 50;
    private static final String INIT_FARBA = "red";
    
    private boolean jeViditelny;
    private Platno.PopisTvaru popis;

    /**
     * Vytvor nový obdĺžnik preddefinovanej farby na preddefinovanej pozícii.
     */
    public Obdlznik() {
        this(INIT_X, INIT_Y);
    }
    
    /**
     * Vytvor nový obdĺžnik preddefinovanej farby na danej pozícii.
     */
    public Obdlznik(int x, int y) {
        final Rectangle tvar = new Rectangle(x, y, INIT_STRANA_A, INIT_STRANA_B);
        this.popis = new Platno.PopisTvaru(tvar, INIT_FARBA);
        this.jeViditelny = false;
    }

    /**
     * (Obdĺžnik) Zobraz sa.
     */
    public void zobraz() {
        if (!this.jeViditelny) {
            this.zaregistrujSaDoPlatna();
            this.jeViditelny = true;
        }
    }
    
    /**
     * (Obdĺžnik) Skry sa.
     */
    public void skry() {
        if (this.jeViditelny) {
            this.odregistrujSaZPlatna();
            this.jeViditelny = false;
        }
    }
    
    /**
     * (Obdĺžnik) Vráti x súradnicu ľavého horného rohu obdĺžnika
     */
    public int getLavyHornyX() {
        return (int)this.getTvar().getX();
    }
    
    /**
     * (Obdĺžnik) Vráti y súradnicu ľavého horného rohu obdĺžnika
     */
    public int getLavyHornyY() {
        return (int)this.getTvar().getY();
    }
    
    /**
     * (Obdĺžnik) Vráti veľkosť strany A obdĺžnika
     */
    public int getStranaA() {
        return (int)this.getTvar().getWidth();
    }
    
    /**
     * (Obdĺžnik) Vráti veľkosť strany B obdĺžnika
     */
    public int getStranaB() {
        return (int)this.getTvar().getHeight();
    }
    
    /**
     * (Obdĺžnik) Posuň sa vpravo o pevnú dĺžku.
     */
    public void posunVpravo() {
        this.posunVodorovne(20);
    }

    /**
     * (Obdĺžnik) Posuň sa vľavo o pevnú dĺžku.
     */
    public void posunVlavo() {
        this.posunVodorovne(-20);
    }

    /**
     * (Obdĺžnik) Posuň sa hore o pevnú dĺžku.
     */
    public void posunHore() {
        this.posunZvisle(-20);
    }

    /**
     * (Obdĺžnik) Posuň sa dole o pevnú dĺžku.
     */
    public void posunDole() {
        this.posunZvisle(20);
    }

    /**
     * (Obdĺžnik) Posuň sa vodorovne o dĺžku danú parametrom.
     */
    public void posunVodorovne(int vzdialenost) {
        this.getTvar().setBounds(
            this.getLavyHornyX() + vzdialenost,
            this.getLavyHornyY(),
            this.getStranaA(),
            this.getStranaB()
        );
    }

    /**
     * (Obdĺžnik) Posuň sa zvisle o dĺžku danú parametrom.
     */
    public void posunZvisle(int vzdialenost) {
        this.getTvar().setBounds(
            this.getLavyHornyX(),
            this.getLavyHornyY() + vzdialenost,
            this.getStranaA(),
            this.getStranaB()
        );
    }

    /**
     * (Obdĺžnik) Zmeň dĺžky strán na hodnoty dané parametrami.
     * Dĺžka strany musí byť nezáporné celé číslo. 
     */
    public void zmenStrany(int stranaA, int stranaB) {
        this.getTvar().setBounds(
            this.getLavyHornyX(),
            this.getLavyHornyY(),
            stranaA,
            stranaB
        );
    }
    

    /**
     * (Obdĺžnik) Zmeň farbu na hodnotu danú parametrom.
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
    
    private Rectangle getTvar() {
        return (Rectangle)this.popis.getTvar();
    }
}
