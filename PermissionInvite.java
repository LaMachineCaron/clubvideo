import javax.swing.*;

/**
 * Created by Alexandre on 2015-12-11.
 */
public interface PermissionInvite {

    /**
     * Affiche les informations d'un article.
     * @param article_choisi l'article à afficher.
     */
    void information(Article article_choisi);

    /**
     * Donne l'article qui est présentement sélectionné.
     * @return article l'article sélectionné.
     */
    Article articleSelectionne();
}
