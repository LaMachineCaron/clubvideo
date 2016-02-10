/**
 * Catégories des articles
 *
 * @author Alexandre Caron
 * @version 1.0
 */
public class Categorie
{
    private String genre;
    private String description;
    private String visa;

    /**
     *Constructeur de la class Categorie
     */
    public Categorie(String genre, String description, String visa)
    {
        this.genre = genre;
        this.visa = visa;
        this.description = description;
    }

    /**
     * Met le genre et le visa d'une catégorie dans une String
     * @return categorie String contenant le genre et le visa de la catégorie.
     */
    public String toString() {
        String categorie = "";

        categorie += genre + " " + visa;

        return categorie;
    }
}