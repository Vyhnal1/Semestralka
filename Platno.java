import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.LinkedHashMap;

/**
 * Canvas is a class to allow for simple graphical drawing on a canvas.
 * This is a modification of the general purpose Canvas, specially made for
 * the BlueJ "shapes" example. 
 *
 * @author: Bruce Quig
 * @author: Michael Kolling (mik)
 * @author: Michal Mrena
 *
 * @version 2.0  (7 November 2022)
 */
public class Platno {

    private static final int SIRKA = 800;
    private static final int VYSKA = 600;
    private static final int FPS = 25;
    
    private static Platno platnoSingleton;

    /**
     * Factory method to get the canvas singleton object.
     */
    public static Platno dajPlatno() {
        if (Platno.platnoSingleton == null) {
            Platno.platnoSingleton = new Platno(
                "BlueJ Shapes Demo",
                SIRKA, VYSKA,
                Color.white
            );
        }
        Platno.platnoSingleton.setVisible(true);
        return Platno.platnoSingleton;
    }

    //  ----- instance part -----

    private final JFrame frame;
    private final CanvasPane canvas;
    private Graphics2D graphic;
    private final Color pozadie;
    private Image canvasImage;    
    private final LinkedHashMap<Object, IPopis> objekty;
    private final Timer casovac;
    
    /**
     * Create a Canvas.
     * @param title  title to appear in Canvas Frame
     * @param width  the desired width for the canvas
     * @param height  the desired height for the canvas
     * @param bgClour  the desired background colour of the canvas
     */
    private Platno(String titulok, int sirka, int vyska, Color pozadie) {
        this.frame = new JFrame();
        this.canvas = new CanvasPane();
        this.frame.setContentPane(this.canvas);
        this.frame.setTitle(titulok);
        this.canvas.setPreferredSize(new Dimension(sirka, vyska));
        this.pozadie = pozadie;
        this.frame.pack();
        this.objekty = new LinkedHashMap<Object, IPopis>();
        this.casovac = new Timer(fpsToTimerDelay(FPS), new ManazerVykreslovania());
        this.casovac.start();
    }

    /**
     * Znovu vykreslí všetky spravované tvary.
     */
    public void prekresli() {
        this.vymazPlatno();
        this.vykresliObjekty();
        this.canvas.repaint();
    }
    
    /**
     * Zaregistruje tvar s jeho popisom medzi spravované tvary.
     */
    public void zaregistrujTvar(Object tvarObjekt, PopisTvaru popis) {
        SwingUtilities.invokeLater(() -> this.objekty.put(tvarObjekt, popis));        
    }
    
    /**
     * Odregistruje tvar zo spravovaných tvarov.
     */
    public void odregistrujTvar(Object tvarObjekt) {
        SwingUtilities.invokeLater(() -> this.objekty.remove(tvarObjekt));
    }
    
    /**
     * Zaregistruje obrázok s jeho popisom medzi vykresľované obrázky.
     */
    public void zaregistrujObrazok(Object obrazokObjekt, PopisObrazku popis) {
        SwingUtilities.invokeLater(() -> this.objekty.put(obrazokObjekt, popis));
    }
    
    /**
     * Odregistruje obrázok s jeho popisom.
     */
    public void odregistrujObrazok(Object obrazokObjekt) {
        SwingUtilities.invokeLater(() -> this.objekty.remove(obrazokObjekt));
    }
    
    /**
     * Pridá listener udalostí z klávesnice.
     */
    public void addKeyListener(KeyListener listener) {
        this.frame.addKeyListener(listener);
    }
    
    /**
     * Pridá listener udalostí myši.
     */
    public void addMouseListener(MouseListener listener) {
        this.canvas.addMouseListener(listener);
    }
    
    /**
     * Pridá listener časovača.
     */
    public void addTimerListener(ActionListener listener) {
        this.casovac.addActionListener(listener);
    }
    
    private void vykresliObjekty() {
        for (IPopis popis : this.objekty.values()) {
            popis.vykresli(this.graphic);
        }
    }
       
    private void vymazPlatno() {
        Color original = this.graphic.getColor();
        this.graphic.setColor(this.pozadie);
        Dimension size = this.canvas.getSize();
        this.graphic.fill(new Rectangle(0, 0, size.width, size.height));
        this.graphic.setColor(original);
    }
    
    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    private void setVisible(boolean visible) {
        if (this.graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background colour
            Dimension size = this.canvas.getSize();
            this.canvasImage = this.canvas.createImage(size.width, size.height);
            this.graphic = (Graphics2D)this.canvasImage.getGraphics();
            this.graphic.setColor(this.pozadie);
            this.graphic.fillRect(0, 0, size.width, size.height);
            this.graphic.setColor(Color.black);
        }
        this.frame.setVisible(visible);
    }
    
    /**
     * Set the foreground colour of the Canvas.
     * @param  newColour   the new colour for the foreground of the Canvas 
     */
    private void setForegroundColor(String farba) {
        if (farba.equals("red")) {
            this.graphic.setColor(Color.red);
        } else if (farba.equals("black")) {
            this.graphic.setColor(Color.black);
        } else if (farba.equals("blue")) {
            this.graphic.setColor(Color.blue);
        } else if (farba.equals("yellow")) {
            this.graphic.setColor(Color.yellow);
        } else if (farba.equals("green")) {
            this.graphic.setColor(Color.green);
        } else if (farba.equals("magenta")) {
            this.graphic.setColor(Color.magenta);
        } else if (farba.equals("white")) {
            this.graphic.setColor(Color.white);
        } else {
            this.graphic.setColor(Color.black);
        }
    }
    
    private static int fpsToTimerDelay(int fps) {
        return 1000 / fps;
    }

    private class CanvasPane extends JPanel {
        public void paint(Graphics graphic) {
            graphic.drawImage(Platno.this.canvasImage, 0, 0, null);
        }
    }

    public interface IPopis {
        void vykresli(Graphics2D g);
    }
    
    public static class PopisTvaru implements IPopis {
        private Shape tvar;
        private String farba;

        PopisTvaru(Shape tvar, String farba) {
            this.tvar = tvar;
            this.farba = farba;
        }
        
        public void setFarba(String farba) {
            this.farba = farba;
        }
        
        public Shape getTvar() {
            return this.tvar;
        }
        
        public void setTvar(Shape s) {
            this.tvar = s;
        }
        
        @Override
        public void vykresli(Graphics2D g) {
            Platno.dajPlatno().setForegroundColor(this.farba);
            g.fill(this.getTvar());
        }
    }
    
    public static class PopisObrazku implements IPopis {
    
        private BufferedImage obrazok;
        private AffineTransform at;

        public PopisObrazku(BufferedImage obrazok) {
            this.obrazok = obrazok;
            this.at = null;
        }
        
        public BufferedImage getObrazok() {
            return this.obrazok;
        }
        
        public void setObrazok(BufferedImage obrazok) {
            this.obrazok = obrazok;
        }
        
        public void setAt(AffineTransform at) {
            this.at = at;
        }
        
        @Override
        public void vykresli(Graphics2D g) {
            g.drawImage(
                this.obrazok,
                this.at,
                null
            );
        }
    }
    
    private class ManazerVykreslovania implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Platno.this.prekresli();
        }
    }
}
