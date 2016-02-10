/**
 * Created by Alexandre on 2015-12-11.
 */
public interface OptionsArticles {

    /**
     * Ajoute un article à la liste d'article du programme.
     */
    void ajouter_article();

    /**
     * Enlève un article de la liste d'article du programme.
     */
    void supprimer_article();

    /**
     * Modifie un article de la liste d'article du programme.
     */
    void modfier_article(Article artcile);
}
