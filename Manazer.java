import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * Automaticky posiela spravy danym objektom
 * posunDole() - pri stlaceni klavesy DOWN
 * posunHore() - pri stlaceni klavesy UP
 * posunVlavo() - pri stlacen klavesy LEFT
 * posunVpravo() - pri stlaceni klavesy RIGHT
 * aktivuj() - pri stlaceni klavesy ENTER alebo SPACE
 * zrus() - pri stlaceni klavesy ESC
 * tik() - kazdych 0,04 sekundy
 * vyberSuradnice(x, y) - pri kliknuti mysou
 */
public class Manazer {
    private final ArrayList<Object> spravovaneObjekty;
    private final ArrayListSet<KeyEvent> stlaceniaKlaves;
    private MouseEvent kliknutie;

    /**
     * Vytvori novy manazer, ktory nespravuje zatial ziadne objekty.
     */
    public Manazer() {
        this.spravovaneObjekty = new ArrayList<Object>();
        this.stlaceniaKlaves = new ArrayListSet<KeyEvent>();
        this.kliknutie = null;
        Platno.dajPlatno().addKeyListener(new ManazerKlaves());        
        Platno.dajPlatno().addMouseListener(new ManazerMysi());
        Platno.dajPlatno().addTimerListener(new ManazerCasovaca());
    }
    
    /**
     * Manazer bude spravovat dany objekt.
     */
    public void spravujObjekt(Object objekt) {
        this.spravovaneObjekty.add(objekt);
    }

    private void posliSpravu(String selektor) {
        for (Object adresat : this.spravovaneObjekty) {
            try {
                Method sprava = adresat.getClass().getMethod(selektor);
                sprava.invoke(adresat);
            } catch (SecurityException e) {
            } catch (NoSuchMethodException e) {
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
        }
    }
    
    private void posliSpravu(String selektor, int prvyParameter, int druhyParameter) {
        for (Object adresat : this.spravovaneObjekty) {
            try {
                Method sprava = adresat.getClass().getMethod(selektor, Integer.TYPE, Integer.TYPE);
                sprava.invoke(adresat, prvyParameter, druhyParameter);
            } catch (SecurityException e) {
            } catch (NoSuchMethodException e) {
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
        }
    }
    
    private void spracujStlaceniaKlaves() {
        for (KeyEvent event : this.stlaceniaKlaves) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    Manazer.this.posliSpravu("posunDole");
                    break;
                    
                case KeyEvent.VK_UP:
                    Manazer.this.posliSpravu("posunHore");
                    break;
                    
                case KeyEvent.VK_LEFT:
                    Manazer.this.posliSpravu("posunVlavo");
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    Manazer.this.posliSpravu("posunVpravo");
                    break;
                    
                case KeyEvent.VK_SPACE:
                case KeyEvent.VK_ENTER:
                    Manazer.this.posliSpravu("aktivuj");
                    break;
                    
                case KeyEvent.VK_ESCAPE:
                    Manazer.this.posliSpravu("zrus");
                    break;
            }
        }
        this.stlaceniaKlaves.clear();
    }

    private void spracujKliknutie() {
        if (this.kliknutie != null) {
            final int x = this.kliknutie.getX();
            final int y = this.kliknutie.getY();
            this.kliknutie = null;
            Manazer.this.posliSpravu("vyberSuradnice", x, y);
        }
    }

    private class ManazerKlaves extends KeyAdapter {
        public void keyPressed(KeyEvent event) {
            Manazer.this.stlaceniaKlaves.add(event);
        }
    }

    private class ManazerCasovaca implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Manazer.this.posliSpravu("tik");
            Manazer.this.spracujStlaceniaKlaves();
            Manazer.this.spracujKliknutie();
        }
    }

    private class ManazerMysi extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            if (event.getButton() == MouseEvent.BUTTON1) {
                Manazer.this.kliknutie = event;
            }
        }
    }

    private static class ArrayListSet<T> implements Iterable<T> {

        private final ArrayList<T> list;
    
        ArrayListSet() {
            this.list = new ArrayList<T>();
        }
        
        public void add(T t) {
            if (!this.list.contains(t)) {
                this.list.add(t);
            }
        }
        
        public void clear() {
            this.list.clear();
        }
        
        @Override
        public Iterator<T> iterator() {
            return this.list.iterator();
        }
    }
}
