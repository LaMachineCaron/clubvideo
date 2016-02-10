/**
 * Created by Alexandre on 2015-12-11.
 */
public interface PermissionAdmin {

    /**
     * Emprunt un article pour un utilisateur.
     * @param article l'article à emprunter.
     * @param utilisateur l'utilisateur qui fait l'emprunt.
     */
    void emprunt_article(Article article, Utilisateur utilisateur);

    /**
     *
     * @return L'article emprunté sélectionné.
     */
    Article empruntSelectionne();

    /**
     * Annule un emprunt d'un article pour un utilisateur.
     * @param article l'article à annuler l'emprunt.
     * @param utilisateur l'utilisateur qui fait l'annulation.
     */
    void annuler_emprunt(Article article, Utilisateur utilisateur);

    /**
     * Ajoute un utilisateur.
     */
    void ajouter_utilisateur();

    /**
     * Modifie un utilisateur.
     * @param utilisateur L'utilisateur à modifier.
     */
    void modifier_utilisateur(Utilisateur utilisateur);

    /**
     * Supprime un utilisateur.
     * @param utilisateur L'utilisateur à supprimer.
     */
    void supprimer_utilisateur(Utilisateur utilisateur);

    /**
     * Ajoute un nouvel article.
     */
    void ajouter_article();

    /**
     * Modifie le nom d'un article.
     * @param article L'article à changer.
     */
    void modifier_article(Article article);

    /**
     * Supprime un article.
     * @param article L'article à supprimer.
     */
    void supprimer_article(Article article);
}
