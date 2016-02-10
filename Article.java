import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Contient les informations d'un article
 *
 * @author Alexandre Caron
 * @version 1.0
 */
public class Article
{
    private int id;
    private String nom;
    private Categorie categorie;
    private boolean emprunte;
    private Utilisateur empruntePar;
    private boolean reserve;
    private Utilisateur reservePar;
    private Calendar dateRetour;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


    /**
     * Constructeur de Article
     */
    public Article(String nom, Categorie categorie, boolean emprunte,
                   Utilisateur empruntePar, boolean reserve,
                   Utilisateur reservePar)
    {
        this.nom = nom;
        this.categorie = categorie;
        this.emprunte = emprunte;
        this.empruntePar = empruntePar;
        this.reserve = reserve;
        this.reservePar = reservePar;
        this.dateRetour = null;
    }

    /**
     * Crée une String contenant les informations d'un article
     * @return info_article Plusieurs lignes comprenant les attributs d'un article.
     */
    public String toString() {
        String info_article = "";


        info_article += "Nom du Film: " + this.nom + "\n";
        info_article += "Catégorie: " + this.categorie + "\n";
        if (emprunte) {
            info_article += "Emprunté par: " + empruntePar.getNom() + "\n";
        } else {
            info_article += "Disponible\n";
        }
        if (reserve) {
            info_article += "Réservé par: " + reservePar.getNom() + "\n";
        } else {
            info_article += "Réservable\n";
        }
        if (emprunte) {
            info_article += "Retour le: " + dateFormat.format(dateRetour.getTime()) + "\n";
        }

        return info_article;
    }

    /**
     * @return nom Le nom de l'article.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return categorie La catégorie d'un article.
     */
    public Categorie getCategorie() {
        return categorie;
    }

    /**
     * @return emprunte true si emprunté, sinon false.
     */
    public boolean isEmprunte() {
        return emprunte;
    }

    /**
     * @return empruntePar L'utilisateur qui a emprunté l'article.
     */
    public Utilisateur getEmpruntePar() {
        return empruntePar;
    }

    /**
     * @return reserve true si réservé, sinon false.
     */
    public boolean isReserve() {
        return reserve;
    }

    /**
     * @return reservePar L'utilisateur qui a réservé l'article.
     */
    public Utilisateur getReservePar() {
        return reservePar;
    }

    /**
     * @return dateRetour La date de retour de l'emprunt de l'article.
     */
    public Calendar getDateRetour() {
        return dateRetour;
    }

    /**
     * Met un utilisateur à reservePar et reserve à true.
     * @param reservePar L'utilisateur qui fait la réservation ou
     *                   null si on annule la réservation.
     */
    public void setReservePar(Utilisateur reservePar) {

        this.reservePar = reservePar;
        if (reservePar != null) {
            this.reserve = true;
        } else {
            this.reserve = false;
        }

    }

    /**
     * Met un utilisateur à empruntePar et emprunte à true.
     * @param empruntePar L'utilisateur qui fait l'emprunt ou
     *                   null si on annule l'emprunt.
     */
    public void setEmpruntePar(Utilisateur empruntePar) {
        this.empruntePar = empruntePar;
        if (empruntePar != null) {
            this.emprunte = true;
        } else {
            this.emprunte = false;
        }
    }

    /**
     * Met la date actuelle à dateRetour.
     * @param jour nombre de jour avant le retour
     */
    public void setDateRetour(int jour) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, jour);
        this.dateRetour = cal;
    }

    /**
     * Change le nom d'un article.
     * @param nom Le nouveau nom.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Change la catégorie d'un article.
     * @param categorie La nouvelle catégorie.
     */
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

}
