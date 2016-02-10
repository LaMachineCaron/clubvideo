/**
 * Created by Alexandre on 2015-12-10.
 */
public class Admin{

    private static String nom = "admin";
    private static char[] mdp = {'a', 'd', 'm', 'i', 'n'};

    /**
     * @return nom Le nom du compte Admin.
     */
    public static String getNom() {
        return nom;
    }

    /**
     * @return mdp Le mot de passe du compte Admin.
     */
    public static char[] getMdp() {
        return mdp;
    }
}
