import javax.swing.*;
import java.awt.event.*;

/**
 * @author kallu
 * Définie les listenners pour jouer la partie*/
public class Jeu extends Frame{

    protected byte etatVainceur;
    protected int xVictoire = 0;
    protected  int oVictoire = 0;

    public Jeu() {
        super();

        relancerPartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetJeu();
                setBoutonEnabled(true);
            }
        });

        relancerManche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetScore();
                resetJeu();
                setBoutonEnabled(true);
            }
        });

        setBoutonListenner();
    }

    public byte getEtatVainceur() {
        return etatVainceur;
    }

    public void setEtatVainceur(byte etatVainceur) {
        this.etatVainceur = etatVainceur;
    }

    public int getxVictoire() {
        return xVictoire;
    }

    public void setXVictoire(boolean incrementer) {
        if (incrementer) xVictoire++;
        else xVictoire = 0;
    }

    public int getoVictoire() {
        return oVictoire;
    }

    public void setOVictoire(boolean incrementer) {
        if (incrementer) oVictoire++;
        else oVictoire = 0;
    }

    /** définie le listenner de toutes les Cases*/
    protected void setBoutonListenner(){
        setBoutonListenner(btn11);
        setBoutonListenner(btn12);
        setBoutonListenner(btn13);
        setBoutonListenner(btn21);
        setBoutonListenner(btn22);
        setBoutonListenner(btn23);
        setBoutonListenner(btn31);
        setBoutonListenner(btn32);
        setBoutonListenner(btn33);
    }

    /** définie le listenners qui vérifie la fin de partie sur une Case*/
    protected void setBoutonListenner(Case boutton){

        //clique a la souris
        boutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (!boutton.isEnabled());
                else if (isPartieFini()) setBoutonEnabled(false);
            }
        });

        //tab entrée (bug : une touche doit être appuyé en plus pour vérifier avec la nouvelle disposition)
        boutton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!boutton.isEnabled());
                else if (isPartieFini()) setBoutonEnabled(false);
            }
        });
    }

    /** set les boutons activable ou non*/
    protected void setBoutonEnabled(boolean etat){
        btn11.setEnabled(etat);
        btn12.setEnabled(etat);
        btn13.setEnabled(etat);
        btn21.setEnabled(etat);
        btn22.setEnabled(etat);
        btn23.setEnabled(etat);
        btn31.setEnabled(etat);
        btn32.setEnabled(etat);
        btn33.setEnabled(etat);
    }

    /**Reset une partie entièrement */
    protected void resetJeu(){
        Case.resetNombreTour();
        setAllBtnVide();
    }

    /** remet tout les bouttons a l'état initial*/
    protected void setAllBtnVide(){
        btn11.resetCase();
        btn12.resetCase();
        btn13.resetCase();
        btn21.resetCase();
        btn22.resetCase();
        btn23.resetCase();
        btn31.resetCase();
        btn32.resetCase();
        btn33.resetCase();

    }

    /** verifie si la partie est finie */
    public boolean isPartieFini(){
        if (isPartiFiniLigne()) return true;
        if (isPartiFiniColonne()) return true;
        if (isPartiFiniDiago()) return true;
        if (isPartieEgalite()) return true;
    return false;
    }

    /** vérifie si la partie est fini en égalité*/
    protected boolean isPartieEgalite(){
        if (!btn11.isCaseVide() && !btn12.isCaseVide()&& !btn13.isCaseVide() && !btn21.isCaseVide() && !btn22.isCaseVide()
                && !btn23.isCaseVide() && !btn31.isCaseVide() && !btn32.isCaseVide() && !btn33.isCaseVide()) {
            JOptionPane.showMessageDialog(contentPane, "Égalité", "Partie finie", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    }

    /** vérifie si trois boutons ont le même état hors vide*/
    protected boolean isBouttonEgale(Case btn1, Case btn2, Case btn3){
        if (btn1.getEtat() != Case.VIDE) {
            if (btn1.getEtat() == btn2.getEtat() && btn2.getEtat() == btn3.getEtat()) {
                setEtatVainceur(btn1.getEtat());
                setNouveauScore();
                JOptionPane.showMessageDialog(contentPane,"Partie gagné par : "+ btn1.getEtatToChar(), "Partie finie", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }
        return false;
    }

    /** vérifie si la partie est finie dans les lignes*/
    protected  boolean isPartiFiniLigne(){

        if (isBouttonEgale(btn11, btn12, btn13)) return true;
        if (isBouttonEgale(btn21, btn22, btn23)) return true;
        if (isBouttonEgale(btn31, btn32, btn33)) return true;
        return false;
    }

    /** vérifie si la partie est finie en colonne */
    protected  boolean isPartiFiniColonne(){

        if (isBouttonEgale(btn11, btn21, btn31)) return true;
        if (isBouttonEgale(btn12, btn22, btn32)) return true;
        if (isBouttonEgale(btn13, btn23, btn33)) return true;
        return false;
    }

    /** vérifie si la partie est finie en diagonal */
    protected boolean isPartiFiniDiago(){
        if (isBouttonEgale(btn11, btn22, btn33)) return true;
        if (isBouttonEgale(btn31, btn22, btn13)) return true;
        return false;
    }

    /** gère l'affichage du score*/
    protected void setAffichage(){
        setNouveauScore();
        setScoreJLabel();
    }

    /**s'occupe de l'incrémentation du score en fonction du gagnant */
    protected void setNouveauScore(){
        if (getEtatVainceur() == Case.VIDE) return;
        if (getEtatVainceur() == Case.CROIX) setXVictoire(true);
        else setOVictoire(true);
        setScoreJLabel();
        setEtatVainceur(Case.VIDE);
    }


    /** gère l'affichage du score par le JLabel */
    protected void setScoreJLabel(){
        AfficheScoreDessous.setText("       X = "+getxVictoire()+"  |  O = "+getoVictoire()+"                  ");
    }

    /** reset le score a 0*/
    protected void resetScore(){
        setXVictoire(false);
        setOVictoire(false);
        setScoreJLabel();
    }
}
