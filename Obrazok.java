import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;

/**
 * Trieda Obrazok, reprezentuje bitmapovy obrazok, ktory moze byt vykresleny na platno.
 * 
 * @author Miroslav Kvassay
 * @author Michal Varga
 * @author  Michal Mrena
 * @version 2.0  (7 November 2022)
 */
public class Obrazok {
    
    private static final int INIT_X = 100;
    private static final int INIT_Y = 100;
    private static final int INIT_UHOL = 0;
    
    private int lavyHornyX;
    private int lavyHornyY;
    private int uhol;
    private boolean jeViditelny;
    private Platno.PopisObrazku popis;

    /**
     * Parametricky konstruktor vytvori Obrazok na pozicii paX, paY s natocenim paUhol
     * 
     * @param suborSObrazkom cesta k suboru s obrazkom, ktory sa ma vykreslovat
     */
    public Obrazok(String suborSObrazkom) {
        this.jeViditelny = false;
        this.lavyHornyX = INIT_X;
        this.lavyHornyY = INIT_Y; 
        this.uhol = INIT_UHOL;
        this.popis = new Platno.PopisObrazku(this.nacitajObrazokZoSuboru(suborSObrazkom));
    }
    
    /**
     * (Obrázok) Zobraz sa.
     */
    public void zobraz() {      
        if (!this.jeViditelny) {
            this.zaregistrujSaDoPlatna();
            this.jeViditelny = true;
        }
    }
    
    /**
     * (Obrázok) Zobraz sa.
     */
    public void skry() {       
        if (this.jeViditelny) {
            this.odregistrujSaZPlatna();
            this.jeViditelny = false;
        }
    }
    
    /*
     * (Obrázok) Vráti všírku obrázka.
     */
    public int getSirka() {
        return this.popis.getObrazok().getWidth();
    }
    
    /*
     * (Obrázok) Vráti výšku obrázka.
     */
    public int getVyska() {
        return this.popis.getObrazok().getHeight();
    }
    
    /**
     * (Obrázok) Posuň sa vpravo o pevnú dĺžku.
     */
    public void posunVpravo() {
        this.posunVodorovne(20);
    }

    /**
     * (Obrázok) Posuň sa vľavo o pevnú dĺžku.
     */
    public void posunVlavo() {
        this.posunVodorovne(-20);
    }

    /**
     * (Obrázok) Posuň sa hore o pevnú dĺžku.
     */
    public void posunHore() {
        this.posunZvisle(-20);
    }

    /**
     * (Obrázok) Posuň sa dole o pevnú dĺžku.
     */
    public void posunDole() {
        this.posunZvisle(20);
    }

    /**
     * (Obrázok) Posuň sa vodorovne o dĺžku danú parametrom.
     */
    public void posunVodorovne(int vzdialenost) {
        this.lavyHornyX += vzdialenost;
        this.aktualizujPolohu();
    }

    /**
     * (Obrázok) Posuň sa zvisle o dĺžku danú parametrom.
     */
    public void posunZvisle(int vzdialenost) {
        this.lavyHornyY += vzdialenost;
        this.aktualizujPolohu();
    }
       
    /**
     * (Obrázok) Zmení obrázok.
     * Súbor s obrázkom musí existovať.
     * 
     * @param suborSObrazkom cesta k súboru s obrázkom, ktorý sa má načítať
     */
    public void zmenObrazok(String suborSObrazkom) {
        this.popis.setObrazok(this.nacitajObrazokZoSuboru(suborSObrazkom));
    }    
    
    /**
     * (Obrázok) Zmeň polohu stredu obrázka na hodnoty dané parametrami. 
     */
    public void zmenPolohu(int stredX, int stredY) {       
        this.lavyHornyX = stredX - this.getSirka() / 2;
        this.lavyHornyY = stredY - this.getVyska() / 2;
        this.aktualizujPolohu();
    }
    
    /**
     * (Obrázok) Zmeň uhol natočenia obrázku podľa parametra. Sever = 0.
     */
    public void zmenUhol(int uhol) {
        this.uhol = uhol;
        this.aktualizujPolohu();
    }
    
    private void aktualizujPolohu() {
        AffineTransform at = new AffineTransform();
        at.translate(this.lavyHornyX + this.getSirka() / 2, this.lavyHornyY + this.getVyska() / 2);
        at.rotate(this.uhol / 180.0 * Math.PI);
        at.translate(-this.getSirka() / 2, -this.getVyska() / 2);
        this.popis.setAt(at);
    }
    
    /*
     * Načíta obrázok zo súboru.
     */
    private BufferedImage nacitajObrazokZoSuboru(String subor) {
        BufferedImage nacitanyOBrazok = null;
        
        try {
            nacitanyOBrazok = ImageIO.read(new File(subor));
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Subor " + subor + " sa nenasiel.");
        }        
        
        return nacitanyOBrazok;
    }     
    
    private void zaregistrujSaDoPlatna() {
        Platno.dajPlatno().zaregistrujObrazok(
            this,
            this.popis
        );
    }

    private void odregistrujSaZPlatna() {
        Platno.dajPlatno().odregistrujObrazok(this);
    }
    
}
