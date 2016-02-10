import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by Alexandre on 2015-12-02.
 */
public class Connexion extends JFrame implements ActionListener {

    private JPanel panel_principal;
    private JLabel user_lbl, mdp_lbl;
    private JButton connexion_btn, invite_btn;
    private JTextField user_txf;
    private JPasswordField mdp_txf;
    public Dimension dimension = new Dimension(250,115);
    private VisionnerArticlesAdmin fenetre_admin;
    private VisionnerArticlesUtilisateur fenetre_utilisateur;
    private VisionnerArticlesInvite fenetre_invite;

    /**
     * Constructeur de la classe.
     */
    public Connexion() {

        initPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel_principal);
        this.setTitle("Projet Final");
        this.setSize(this.dimension);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Initialise les widgets pour la page de connexion.
     */
    private void initPanel() {

        panel_principal = new JPanel(new GridLayout(3, 2, 5, 5));

        // Ligne Utilisateur
        user_lbl = new JLabel("Utilisateur: ", JLabel.TRAILING);
        user_txf = new JTextField(10);
        user_txf.requestFocus();
        user_lbl.setLabelFor(user_txf);

        // Ligne Mot de passe
        mdp_lbl = new JLabel("Mot de passe: ", JLabel.TRAILING);
        mdp_txf = new JPasswordField(10);
        mdp_lbl.setLabelFor(mdp_txf);

        //Boutons
        connexion_btn = new JButton("Connexion");
        connexion_btn.addActionListener(this);
        invite_btn = new JButton("Invité");
        invite_btn.addActionListener(this);

        //Ajout des widgets au panel principal.
        panel_principal.add(user_lbl);
        panel_principal.add(user_txf);
        panel_principal.add(mdp_lbl);
        panel_principal.add(mdp_txf);
        panel_principal.add(invite_btn);
        panel_principal.add(connexion_btn);
    }

    /**
     * Gère les boutons qui ont été appuyés.
     * @param e Le bouton appuyé.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Connexion en invité.
        if (e.getSource() == invite_btn) {
            fenetre_invite = new VisionnerArticlesInvite(this);
            this.setContentPane(this.fenetre_invite);
            this.panel_principal.updateUI();
            this.setSize(fenetre_invite.dimension);

        // Connexion en tant qu'utilisateur ou admin.
        } else if (e.getSource() == connexion_btn) {
            String username = user_txf.getText();
            char[] mdp = mdp_txf.getPassword();
            boolean erreur = true;
            if (username.equals(Admin.getNom())) {
                if (Arrays.equals(mdp, Admin.getMdp())) {
                    erreur = false;
                    fenetre_admin = new VisionnerArticlesAdmin(this);
                    this.setContentPane(this.fenetre_admin);
                    this.panel_principal.updateUI();
                    this.setSize(fenetre_admin.dimension);
                }
            } else {
                for (Utilisateur utilisateur:BD.utilisateurs) {
                    if (utilisateur.getNom().equals(username)) {
                        if (isMdpCorrect(utilisateur, mdp)) {
                            erreur = false;
                            fenetre_utilisateur = new VisionnerArticlesUtilisateur(this, utilisateur);
                            this.setContentPane(this.fenetre_utilisateur);
                            this.panel_principal.updateUI();
                            this.setSize(fenetre_utilisateur.dimension);
                        }
                    }
                }
                if (erreur) {
                    JOptionPane.showMessageDialog(this, "Information incorrecte", "", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    /**
     * Valide si le mot de passe d'un utilisateur est valide.
     * @param utilisateur l'utilisateur à vérifier.
     * @param mdp Le mot de passe à vérifier.
     * @return valide true si c'est correct, sinon false.
     */
    private boolean isMdpCorrect(Utilisateur utilisateur, char[] mdp) {
        boolean valide = true;
        if (utilisateur.getMdp().length != mdp.length) {
            valide = false;
        } else {
            valide = Arrays.equals(mdp, utilisateur.getMdp());
        }
        return valide;
    }

    /**
     * Remet le panel_principal comme panel visible.
     */
    public void deconnection() {
        this.setContentPane(panel_principal);
        this.panel_principal.updateUI();
        this.setSize(dimension);
        this.user_txf.setText("");
        this.mdp_txf.setText("");
        this.user_txf.requestFocus();
    }
}
