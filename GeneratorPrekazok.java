import java.util.ArrayList;
import java.util.Random;

public class GeneratorPrekazok {
    private ArrayList<Prekazka> prekazky;
    private int velkostHracejPlochy;
    private boolean zobrazeniePrekazky;
    private int zmazaniePrekazky;
    private int medzera;
    private static final int DOBA_PREKAZKY = 150;
    
    public GeneratorPrekazok(int sirka, int vyska) {
        // initialise instance variables
        this.medzera = new Random().nextInt(70) + 130;
        this.prekazky = new ArrayList<>();
        this.vytvorPrekazku();
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);
    }
    
    public void vytvorPrekazku() {
        this.prekazky.add(new Prekazka(800, 75, 25, 150));
        this.prekazky.add(new Prekazka(800, 100 + this.medzera, 25, 150));
        
        // Random random = new Random();
        // if (this.prekazky.isEmpty()) {
            // int y = random.nextInt(250) + 50; 
            // this.prekazky.add(new Prekazka(400, y, 50, 300 - y));  
        // }
    }
    
    public void tik() {
        int i = 0;
        for (Prekazka prekazka : this.prekazky) {
                if(prekazka.getX() + prekazka.getSirka() < 0) {
                this.prekazky.remove(i);
                vytvorPrekazku();
            }
                prekazka.tik();
                i++;
            }
        }
        
        
    }