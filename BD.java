import java.util.ArrayList;

/**
 * Created by Alexandre on 2015-12-02.
 */
public class BD {

    public static ArrayList<Article> articles;
    public static ArrayList<Utilisateur> utilisateurs;
    public static ArrayList<Categorie> categories;

    /**
     * Constructeur de BD
     */
    public BD() {
        this.articles = new ArrayList<Article>();
        this.categories = new ArrayList<Categorie>();
        this.utilisateurs = new ArrayList<Utilisateur>();
    }

    /**
     * Rempli les ArrayList Article, Categorie, Utilisateur avec des données hardcodées.
     */
    public static void remplissageBD() {

        BD bd = new BD();

        char[] mdp = {'1', '2', '3'};
        char[] mdp_invite = {};
        char[] mdp_admin = {'a', 'd', 'm', 'i', 'n'};

        Utilisateur alexandre = new Utilisateur("Alexandre", mdp);
        Utilisateur olivier = new Utilisateur("Olivier", mdp);
        Utilisateur alysson = new Utilisateur("Alysson", mdp);
        Admin admin = new Admin();
        alexandre.modelEmprunts.addElement("Star Wars");

        BD.utilisateurs.add(alexandre);
        BD.utilisateurs.add(olivier);
        BD.utilisateurs.add(alysson);

        Categorie science_fiction = new Categorie("Science-Fiction", "Geek Movies", "G");
        Categorie action = new Categorie("Action", "Boom Boom", "13+");

        BD.categories.add(science_fiction);
        BD.categories.add(action);

        Article article1 = new Article("Star Wars", science_fiction, true, alexandre, false, null);
        Article article2 = new Article("Deadpool", action, false, null, false, null);
        Article article3 = new Article("Harry Potter", action, false, null, false, null);
        Article article4 = new Article("Iron Man", action, false, null, false, null);
        Article article5 = new Article("Frozen", action, false, null, false, null);
        Article article6 = new Article("Avengers", action, false, null, false, null);
        article1.setDateRetour(-3);

        BD.articles.add(article1);
        BD.articles.add(article2);
        BD.articles.add(article3);
        BD.articles.add(article4);
        BD.articles.add(article5);
        BD.articles.add(article6);

    }
}
