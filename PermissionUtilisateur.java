/**
 * Created by Alexandre on 2015-12-11.
 */
public interface PermissionUtilisateur {

    /**
     * Effectue une réservation à un utilisateur.
     * @param article l'article à réservé.
     * @param utilisateur l'utilisateur qui fait la réservation.
     */
    void reservation_article(Article article, Utilisateur utilisateur);

    /**
     * Donne la réservation qui est présentement sélectionné.
     *
     * @return article la réservation sélectionné.
     */
    Article reservationSelectionne();

    /**
     * Annule une réservation pour un utilisateur.
     * @param article l'article à annuler.
     * @param utilisateur l'utilisateur qui fait l'annulation.
     */
    void annulation_reservation(Article article, Utilisateur utilisateur);

}
