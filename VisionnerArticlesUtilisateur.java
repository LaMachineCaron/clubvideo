import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

/**
 * Created by Alexandre on 2015-12-02.
 */
public class VisionnerArticlesUtilisateur extends JPanel implements ActionListener, PermissionInvite, PermissionUtilisateur{

    private Connexion parent;
    private JScrollPane scroll_choix_article, scroll_articles_reserves, scroll_articles_empruntes;
    private JList choix_articles, articles_reserves, articles_empruntes;
    private JPanel feature_pnl, boutons_choix_article, panel_articles, panel_reservations, principal_pnl, panel_emprunts;
    private JButton info_btn, reserver_btn, annuler_reservation_btn, deconnection_btn;
    private Article article_selectionne, reservation_selectionne;
    private DefaultListModel articles;
    private Utilisateur utilisateur;
    public Dimension dimension = new Dimension(840,245);

    /**
     * Contructeur de VisionnerArticlesUtilisateurs
     * @param parent La fênetre qui a lancé VisionnerArticlesUtilisateurs
     * @param utilisateur l'utilisateur présentement connecté.
     */
    public VisionnerArticlesUtilisateur(Connexion parent, Utilisateur utilisateur) {

        this.parent = parent;
        this.utilisateur = utilisateur;
        initPanel();
        this.add(principal_pnl);
        if (utilisateur.modelEmprunts.size() > 0) {
            for (Article emprunt:BD.articles) {
                if (emprunt.getEmpruntePar() == utilisateur) {
                    Date aujourdhui = Calendar.getInstance().getTime();
                    if (emprunt.getDateRetour().getTime().before(aujourdhui)) {
                        JOptionPane.showMessageDialog(this,
                                emprunt.getNom() + " est en retard.",
                                "Retard",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }

    /**
     * Création de l'interface.
     */
    private void initPanel() {

        // CREATION DE PANELS
        principal_pnl = new JPanel();
        principal_pnl.setLayout(new BoxLayout(principal_pnl, BoxLayout.PAGE_AXIS));

        feature_pnl = new JPanel();
        feature_pnl.setLayout(new GridLayout(1, 3, 10, 10));

        panel_articles = new JPanel();
        panel_articles.setLayout(new BorderLayout());

        panel_reservations = new JPanel();
        panel_reservations.setLayout(new BorderLayout());

        panel_emprunts = new JPanel();
        panel_emprunts.setLayout(new BorderLayout());

        boutons_choix_article = new JPanel();
        boutons_choix_article.setLayout(new FlowLayout());

        // CREATION DES LISTES
        articles = new DefaultListModel();

        // CREATION DES BOUTONS
        info_btn = new JButton("Info");
        reserver_btn = new JButton("Réserver");
        deconnection_btn = new JButton("Déconnection");
        annuler_reservation_btn = new JButton("Annuler Réservation");

        // POPULATION DES LISTES
        for (Article article:BD.articles) {
            articles.addElement(article.getNom());
        }

        choix_articles = new JList(articles);
        choix_articles.setLayoutOrientation(JList.VERTICAL);
        choix_articles.setVisibleRowCount(5);
        choix_articles.setSelectedIndex(0);
        article_selectionne = articleSelectionne();
        choix_articles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        choix_articles.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                article_selectionne = articleSelectionne();
            }
        });

        articles_reserves = new JList(utilisateur.modelReservations);
        articles_reserves.setLayoutOrientation(JList.VERTICAL);
        articles_reserves.setVisibleRowCount(5);
        articles_reserves.setSelectedIndex(0);
        reservation_selectionne = reservationSelectionne();
        articles_reserves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        articles_reserves.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                reservation_selectionne = reservationSelectionne();
            }
        });


        scroll_articles_reserves = new JScrollPane(articles_reserves);
        scroll_choix_article = new JScrollPane(choix_articles);

        articles_empruntes = new JList(utilisateur.modelEmprunts);
        articles_empruntes.setLayoutOrientation(JList.VERTICAL);
        articles_empruntes.setVisibleRowCount(5);
        articles_empruntes.setSelectedIndex(0);
        articles_empruntes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll_articles_empruntes = new JScrollPane(articles_empruntes);

        // PLACEMENT DES WIDGETS
        info_btn.addActionListener(this);
        boutons_choix_article.add(info_btn);
        reserver_btn.addActionListener(this);
        boutons_choix_article.add(reserver_btn);
        annuler_reservation_btn.addActionListener(this);

        panel_articles.add(scroll_choix_article, BorderLayout.CENTER);
        panel_articles.add(boutons_choix_article, BorderLayout.SOUTH);
        panel_articles.setBorder(BorderFactory.createTitledBorder("Liste d'articles"));
        panel_reservations.add(scroll_articles_reserves, BorderLayout.CENTER);
        panel_reservations.add(annuler_reservation_btn, BorderLayout.SOUTH);
        panel_reservations.setBorder(BorderFactory.createTitledBorder("Article(s) réservé(s)"));
        panel_emprunts.add(scroll_articles_empruntes, BorderLayout.CENTER);
        panel_emprunts.setBorder(BorderFactory.createTitledBorder("Article(s) emprunté(s)"));
        deconnection_btn.addActionListener(this);
        deconnection_btn.setAlignmentX(CENTER_ALIGNMENT);

        if (utilisateur.modelReservations.getSize() < 1) {
            annuler_reservation_btn.setEnabled(false);
        }

        // PLACEMENT DES PANELS

        feature_pnl.add(panel_articles);
        feature_pnl.add(panel_reservations);
        feature_pnl.add(panel_emprunts);
        feature_pnl.setBorder(new EmptyBorder(10, 10, 10, 10));
        feature_pnl.setAlignmentX(CENTER_ALIGNMENT);

        principal_pnl.add(feature_pnl);
        principal_pnl.add(deconnection_btn);
    }

    /**
     * Gestion des boutons appuyés.
     * @param e Le bouton appuyé.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Bouton Info
        if (e.getSource() == info_btn) {
            information(article_selectionne);

        // Bouton Réserver
        } else if (e.getSource() == reserver_btn) {
            reservation_article(article_selectionne, utilisateur);

            // Bouton Annuler Réservation
        } else if (e.getSource() == annuler_reservation_btn) {
            annulation_reservation(reservation_selectionne, utilisateur);

            // Bouton Déconnection
        } else if(e.getSource() == deconnection_btn) {
            parent.deconnection();
        }
    }

    @Override
    public void information(Article article_choisi) {
        String info_article = article_choisi.toString();
        JOptionPane.showMessageDialog(this, info_article, "Informations", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Indique l'article sélectionné dans la liste.
     * @return article_selectionne l'article présentement sélectionné.
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
     * Effectue la réservation d'un article pour un utilisateur.
     * @param article l'article à réservé.
     * @param utilisateur l'utilisateur qui fait la réservation.
     */
    @Override
    public void reservation_article(Article article, Utilisateur utilisateur) {
        if (article.isReserve()) {
            JOptionPane.showMessageDialog(this, "Ce film est déjà réservé.", "", JOptionPane.WARNING_MESSAGE);
        } else {
            article.setReservePar(utilisateur);
            utilisateur.modelReservations.addElement(article.getNom());
            articles_reserves.setModel(utilisateur.modelReservations);
            articles_reserves.setSelectedIndex(0);
            annuler_reservation_btn.setEnabled(true);
        }
    }

    /**
     * Donne la réservation qui est présentement sélectionné.
     *
     * @return article la réservation sélectionné.
     */
    @Override
    public Article reservationSelectionne() {
        Article article = null;
        int index = articles_reserves.getSelectedIndex();
        if (index >= 0) {
            String nom_article = utilisateur.modelReservations.get(index).toString();
            for (Article a:BD.articles) {
                if (a.getNom().equals(nom_article)) {
                    article = a;
                }
            }
        }
        return article;
    }

    /**
     * Annulation de la réservation d'un article pour un utilisateur.
     * @param article l'article à annuler.
     * @param utilisateur l'utilisateur qui fait l'annulation.
     */
    @Override
    public void annulation_reservation(Article article, Utilisateur utilisateur) {
        article.setReservePar(null);
        utilisateur.modelReservations.removeElement(article.getNom());
        if (utilisateur.modelReservations.getSize() > 0) {
            articles_reserves.setSelectedIndex(0);
            reservation_selectionne = reservationSelectionne();
        } else {
            annuler_reservation_btn.setEnabled(false);
        }
    }
}
