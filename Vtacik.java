

public class Vtacik {
    private Obrazok grafikaVtaka;
    private boolean skok;
    private Poloha poloha;
    private Manazer manazer;
    private int rychlostPadania;
    private double gravitacia = 1.0;
    private double skore;
    
    
    public Vtacik() {
        // initialise instance variables
        this.grafikaVtaka = new Obrazok(".\\img\\vtacik.png");
        this.grafikaVtaka.zmenPolohu(400, 300);
        this.grafikaVtaka.zobraz();
        this.skok = false;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.rychlostPadania = 1;
        this.skore = 0.0;
    }
    
    public void aktualizaciaSkoku() {
        if(skok) {
            this.posunHore();
        }
        return;
    }
    
    public void zobraz() {
        this.grafikaVtaka = new Obrazok(".\\img\\vtacik.png");
    }
    
    public void tik() {
        if (this.rychlostPadania < 0) {
            this.rychlostPadania += this.gravitacia;
        }
    
        this.posunDole();
        this.skore += 0.25;
    }  

    public void nastavGravitaciu(double gravitacia) {
        this.gravitacia = gravitacia;
    }
    
    public Poloha getPoloha() {
        return this.poloha;
    }
    
    public void posunHore() {
        this.grafikaVtaka.posunZvisle(-30);
    }
    
    public void posunDole() {
        this.grafikaVtaka.posunZvisle(3);
    }
    
    public void pridajSkore() {
        this.skore++;
    }
    
    public double getSkore() {
        System.out.println("Vase skore je " + this.skore);
        return this.skore;
    }

}