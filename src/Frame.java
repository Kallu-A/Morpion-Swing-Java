import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author kallu
 * Interface graphique avec peu de listenners */
public class Frame extends JFrame {

    // btn(ligne)(colonne)
    protected Case btn11;
    protected Case btn12;
    protected Case btn13;
    protected Case btn21;
    protected Case btn22;
    protected Case btn23;
    protected Case btn31;
    protected Case btn32;
    protected Case btn33;

    protected JToolBar toolBar;
    protected JPanel ticTacToe;
    protected JPanel contentPane;

    protected JButton relancerPartie;
    protected JButton relancerManche;

    protected JPanel afficheScore;
    protected JLabel afficheScoreDessus;
    protected JLabel AfficheScoreDessous;


    public Frame() {
        //initialisation
        super("Kallu morpion");
        this.setSize(450, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.contentPane = (JPanel) getContentPane();
        this.setUI();
        this.ticTacToe = new JPanel(new GridLayout(3,3));
        ticTacToe.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setAllBtn();
        this.createToolBar();

        //ajout
        contentPane.add(ticTacToe, BorderLayout.CENTER);
        contentPane.add(toolBar, BorderLayout.NORTH);

    }

    /** créer la toolBar*/
    private void createToolBar(){
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        relancerPartie = new JButton("Relancer manche");
        relancerManche = new JButton("Relancer partie");
        afficheScore = new JPanel(new GridLayout(2,1));

        //espace a la fin pour gérer l'affichage nimbus
        afficheScoreDessus = new JLabel("  Nombre de victoire                   ");
        AfficheScoreDessous = new JLabel("       X = 0  |  O = 0                ");
        afficheScore.add(afficheScoreDessus);
        afficheScore.add(AfficheScoreDessous);

        toolBar.add(relancerPartie);
        toolBar.addSeparator();
        toolBar.add(relancerManche);
        toolBar.addSeparator(new Dimension( 30, 0));
        toolBar.add(afficheScore);

    }

    /** définie un look and feel en fonction de l'OS  */
    private void setUI() {
        try {
            // indisponible sur window
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (Exception error) {
            try {
                UIManager.setLookAndFeel(new NimbusLookAndFeel());
            } catch (UnsupportedLookAndFeelException error2) {
                JOptionPane.showMessageDialog(contentPane, "Aucun look n'est applicable", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /** créer les boutons et les ajoutes*/
    protected void setAllBtn(){
        btn11 = new Case();
        btn12 = new Case();
        btn13 = new Case();
        btn21 = new Case();
        btn22 = new Case();
        btn23 = new Case();
        btn31 = new Case();
        btn32 = new Case();
        btn33 = new Case();

        ticTacToe.add(btn11);
        ticTacToe.add(btn12);
        ticTacToe.add(btn13);
        ticTacToe.add(btn21);
        ticTacToe.add(btn22);
        ticTacToe.add(btn23);
        ticTacToe.add(btn31);
        ticTacToe.add(btn32);
        ticTacToe.add(btn33);
    }



}
