import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by Alexandre on 2015-12-02.
 */
public class VisionnerArticlesInvite extends JPanel implements ActionListener, PermissionInvite{

    private Connexion parent;
    private JScrollPane scroll_choix_article, scroll_articles_reserves;
    private JList choix_articles, articles_reserves;
    private JPanel feature_pnl, boutons_choix_article, panel_articles, panel_reservations, principal_pnl;
    private JButton info_btn, reserver_btn, annuler_reservation_btn, deconnection_btn;
    private Article article_selectionne;
    private DefaultListModel articles;
    private Utilisateur utilisateur;
    public Dimension dimension = new Dimension(200,245);

    /**
     * Constructeur de VisionnerArticlesInvite.
     * @param parent La fenêtre qui a lancé VisionnerArticlesInvite.
     */
    public VisionnerArticlesInvite(Connexion parent) {

        this.parent = parent;
        initPanel();
        this.add(principal_pnl);

    }

    /**
     * Création de l'interface.
     */
    private void initPanel() {

        // CREATION DE PANELS
        principal_pnl = new JPanel();
        principal_pnl.setLayout(new BoxLayout(principal_pnl, BoxLayout.PAGE_AXIS));

        feature_pnl = new JPanel();
        feature_pnl.setLayout(new GridLayout(1, 1, 10, 10));

        panel_articles = new JPanel();
        panel_articles.setLayout(new BorderLayout());

        boutons_choix_article = new JPanel();
        boutons_choix_article.setLayout(new FlowLayout());

        // CREATION DES LISTES
        articles = new DefaultListModel();

        // CREATION DES BOUTONS
        info_btn = new JButton("Info");
        deconnection_btn = new JButton("Déconnection");

        // POPULATION DES LISTES
        for (Article article:BD.articles) {
            articles.addElement(article.getNom());
        }

        choix_articles = new JList(articles);
        choix_articles.setLayoutOrientation(JList.VERTICAL);
        choix_articles.setVisibleRowCount(5);
        choix_articles.setSelectedIndex(0);
        choix_articles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll_choix_article = new JScrollPane(choix_articles);

        // PLACEMENT DES WIDGETS
        info_btn.addActionListener(this);

        panel_articles.add(scroll_choix_article, BorderLayout.CENTER);
        panel_articles.add(info_btn, BorderLayout.SOUTH);
        panel_articles.setBorder(BorderFactory.createTitledBorder("Liste d'articles"));
        deconnection_btn.addActionListener(this);
        deconnection_btn.setAlignmentX(CENTER_ALIGNMENT);


        // PLACEMENT DES PANELS

        feature_pnl.add(panel_articles);
        feature_pnl.setBorder(new EmptyBorder(10, 10, 10, 10));
        feature_pnl.setAlignmentX(CENTER_ALIGNMENT);

        principal_pnl.add(feature_pnl);
        principal_pnl.add(deconnection_btn);
    }

    /**
     * Gestion des boutons appuyés.
     * @param e Le bouton.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Bouton Info
        if (e.getSource() == info_btn) {
            Article article = articleSelectionne();
            information(article);

        // Bouton Déconnection
        } else if(e.getSource() == deconnection_btn) {
            parent.deconnection();
        }
    }

    /**
     * Indique quel article est présentement sélectionné dans la liste.
     * @return article_selectionne L'article sélectionné.
     */
    @Override
    public Article articleSelectionne() {
        Article article_selectionne = null;
        int index = choix_articles.getSelectedIndex();
        String film = articles.get(index).toString();
        for (Article article : BD.articles) {
            if (article.getNom().equals(film)) {
                article_selectionne = article;
            }
        }
        return article_selectionne;
    }

    /**
     * Affiche les informations d'un article.
     * @param article_selectionne l'article à afficher.
     */
    @Override
    public void information(Article article_selectionne) {
        String info_article = article_selectionne.toString();
        JOptionPane.showMessageDialog(this, info_article, "Informations", JOptionPane.INFORMATION_MESSAGE);
    }

}
