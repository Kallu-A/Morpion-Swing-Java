import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author kallu
 * Case un bouton qui peut avoir trois état vide croix rond */
public class Case extends JButton {

    protected static byte nombreTour = 0;
    public static final byte CROIX = -1;
    public static final byte ROND = -2;
    public static final byte VIDE = -3;

    /** etat de la Case CROIX ROND VIDE*/
    private byte etat;

    public ImageIcon O = new ImageIcon(this.getClass().getResource("image/O.png"));
    public ImageIcon X = new ImageIcon(this.getClass().getResource("image/X.png"));
    public ImageIcon vide = new ImageIcon(this.getClass().getResource("image/vide.png"));

    public Case() {
        super();
        setPreferredSize(new Dimension(70, 70));
        this.etat = VIDE;
        setIcon(vide);
        addActionListennerButton();
    }

    /** ActionListenner set l'etat et Icon en fonction du tour */
    public void addActionListennerButton () {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getIcon() != vide) return;
                if (getNombreTour() % 2 == 0) {
                    setIcon(X);
                    setEtat(CROIX);
                } else {
                    setIcon(O);
                    setEtat(ROND);
                }

            }
        });
    }

    public byte getEtat() {
        return etat;
    }

    public void setEtat(byte etat) {
        this.etat = etat;
    }


    /** récupère le nombre de tour*/
    public static byte getNombreTour() {
        setNombreTourIncrementer();
        return nombreTour;
    }

    /** incrémente le tour */
    public static void setNombreTourIncrementer() {
        nombreTour++;
    }

    /**remet le tour a 0 */
    public static void resetNombreTour() {
        nombreTour = 0;
    }

    /** reset la case son Icon et son etat*/
    protected void resetCase(){
        this.setIcon(this.vide);
        this.setEtat(VIDE);
    }

    /** récupère l'état et le renvoie en char*/
    public char getEtatToChar(){
        if (etat == ROND) return 'O';
        else return 'X';
    }

    /** renvoie si la case est vide ou non*/
    public boolean isCaseVide(){
        return (getEtat() == Case.VIDE);
    }
}
