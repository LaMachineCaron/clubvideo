import javax.swing.*;
import java.util.ArrayList;

/**
 * Class pour les Utilisateurs
 *
 * @author Alexandre Caron
 * @version 1.0
 */
public class Utilisateur extends Invite
{
    private String nom;
    private char[] mdp;
    public DefaultListModel modelReservations, modelEmprunts;

    /**
     * Constructeur
     *
     * @param nom Le nom de l'utilisateur
     * @param mdp le mot de passe de l'utilisateur
     */
    public Utilisateur(String nom, char[] mdp)
    {
        this.nom = nom;
        this.mdp = mdp;
        this.modelReservations = new DefaultListModel();
        this.modelEmprunts = new DefaultListModel();
    }

    /**
     * @return le nom de l'utilisateur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return Le mot de passe de l'utilisateur.
     */
    public char[] getMdp() { return  mdp; }

    /**
     * Change le mot de passe de l'utilisateur.
     * @param mdp le nouveau mot de passe.
     */
    public void setMdp(char[] mdp) {
        this.mdp = mdp;
    }

}
