/**
 * Created by Alexandre on 2015-12-02.
 */
public class Principal {

    /**
     * Constructeur de Principal
     * @param args
     */
    public static void main(String[] args) {

        BD.remplissageBD();
        Connexion connection = new Connexion();
    }
}
