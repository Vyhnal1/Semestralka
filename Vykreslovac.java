
public class Vykreslovac {
    private Vtacik vtacik;
    private GeneratorPrekazok prekazky;
    private Pozadie pozadie;
    private double gravitacia = 0.0;
    private DvojSSD skore;
    
    public Vykreslovac() {
        // initialise instance variables
        this.pozadie = new Pozadie();
        this.vtacik = new Vtacik();
        this.prekazky = new GeneratorPrekazok(75, 200);
        // this.skore = new DvojSSD(
            // 25, 
            // 25, 
            // 10, 
            // 57, 
            // 17,
            // 24);
    }
}
