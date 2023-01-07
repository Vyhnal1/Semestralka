import java.awt.Polygon;

/**
 * Rovnoramenný trojuholník, s ktorým možno pohybovať a nakreslí sa na plátno.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  Michal Mrena
 * @version 2.0  (7 November 2022)
 */

public class Trojuholnik {
    
    private static final int INIT_X = 50;
    private static final int INIT_Y = 15;
    private static final int INIT_VYSKA = 30;
    private static final int INIT_ZAKLADNA = 30;
    private static final String INIT_FARBA = "green";
    
    private int vyska;
    private int zakladna;
    private int hornyVrcholX;
    private int hornyVrcholY;
    private boolean jeViditelny;
    private Platno.PopisTvaru popis;

    /**
     * Vytvor nový rovnoramenný trojuholník preddefinovanej farby na preddefinovanej pozícii.
     */
    public Trojuholnik() {
        this(INIT_X, INIT_Y);
    }
    
    /**
     * Vytvor nový rovnoramenný trojuholník preddefinovanej farby na danej pozícii.
     */
    public Trojuholnik(int x, int y) {
        this.vyska = INIT_VYSKA;
        this.zakladna = INIT_ZAKLADNA;
        this.hornyVrcholX = x;
        this.hornyVrcholY = y;
        this.jeViditelny = false;
        this.popis = new Platno.PopisTvaru(this.makePolygon(), INIT_FARBA);
    }

    /**
     * (Trujuholník) Zobraz sa.
     */
    public void zobraz() {
        if (!this.jeViditelny) {
            this.zaregistrujSaDoPlatna();
            this.jeViditelny = true;
        }
    }
    
    /**
     * (Trujuholník) Skry sa.
     */
    public void skry() {
        if (this.jeViditelny) {
            this.odregistrujSaZPlatna();
            this.jeViditelny = false;
        }
    }
    
    /**
     * (Trujuholník) Vráti x súradnicu horného vrcholu trojuholníka.
     */
    public int getHornyVrcholX() {
        return this.hornyVrcholX;
    }
    
    /**
     * (Trujuholník) Vráti y súradnicu horného vrcholu trojuholníka.
     */
    public int getHornyVrcholY() {
        return this.hornyVrcholY;
    }
    
    /**
     * (Trujuholník) Posuň sa vpravo o pevnú dĺžku.
     */
    public void posunVpravo() {
        this.posunVodorovne(20);
    }

    /**
     * (Trujuholník) Posuň sa vľavo o pevnú dĺžku.
     */
    public void posunVlavo() {
        this.posunVodorovne(-20);
    }

    /**
     * (Trujuholník) Posuň sa hore o pevnú dĺžku.
     */
    public void posunHore() {
        this.posunZvisle(-20);
    }

    /**
     * (Trujuholník) Posuň sa dole o pevnú dĺžku.
     */
    public void posunDole() {
        this.posunZvisle(20);
    }

    /**
     * (Trujuholník) Posuň sa vodorovne o dĺžku danú parametrom.
     */
    public void posunVodorovne(int vzdialenost) {
        this.hornyVrcholX += vzdialenost;
        this.getTvar().translate(vzdialenost, 0);
    }

    /**
     * (Trujuholník) Posuň sa zvisle o dĺžku danú parametrom.
     */
    public void posunZvisle(int vzdialenost) {
        this.hornyVrcholY += vzdialenost;
        this.getTvar().translate(0, vzdialenost);
    }

    /**
     * (Trujuholník) Zmeň rozmery výšky a základne na hodnoty dané parametrami.
     * Obe hodnoty musia byť nezáporné celé čísla. 
     */
    public void zmenRozmery(int vyska, int zakladna) {
        this.vyska = vyska;
        this.zakladna = zakladna;
        this.aktualizujPolygon();
    }

    /**
     * (Trujuholník) Zmeň farbu na hodnotu danú parametrom.
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
    
    private void aktualizujPolygon() {
        this.popis.setTvar(this.makePolygon());
    }

    private Polygon makePolygon() {
        int[] xpoints = { this.hornyVrcholX, this.hornyVrcholX + (this.zakladna / 2), this.hornyVrcholX - (this.zakladna / 2) };
        int[] ypoints = { this.hornyVrcholY, this.hornyVrcholY + this.vyska, this.hornyVrcholY + this.vyska };
        return new Polygon(xpoints, ypoints, 3);
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
    
    private Polygon getTvar() {
        return (Polygon)this.popis.getTvar();
    }
}
