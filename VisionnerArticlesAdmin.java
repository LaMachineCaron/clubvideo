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
/**
 * Created by Alexandre on 2015-12-02.
 */
public class VisionnerArticlesAdmin extends JPanel implements ActionListener, PermissionInvite, PermissionUtilisateur, PermissionAdmin{

    private Connexion parent;
    private JScrollPane scroll_choix_article, scroll_articles_reserves, scroll_choix_utilisateur, scroll_articles_empruntes;
    private JList choix_articles, articles_reserves, choix_utilisateur, articles_empruntes;
    private JPanel feature_pnl, boutons_choix_article, panel_utilisateur, panel_articles, panel_reservations, panel_emprunts,
    boutons_utilisateurs, principal_pnl;
    private JComboBox choix_categorie;
    private JButton info_btn, reserver_btn, annuler_reservation_btn, annuler_emprunt_btn, emprunt_btn, ajouter_btn,
    supprimer_btn, modifier_btn, deconnection_btn, ajouter_article_btn, modifier_article_btn, supprimer_article_btn;
    private Article article_selectionne, reservation_selectionne, emprunt_selectionne;
    private Utilisateur utilisateur_selectionne;
    private DefaultListModel articles, utilisateurs;
    public Dimension dimension = new Dimension(630,450);

    /**
     * Contructeur de VisionnerArticlesAdmin.
     * @param parent La fênetre qui lance celle ci.
     */
    public VisionnerArticlesAdmin(Connexion parent) {

        this.parent = parent;
        initPanel();
        this.add(principal_pnl);
    }

    /**
     * Création de l'interface pour VisionnerArticlesAdmin.
     */
    private void initPanel() {

        // CREATION DE PANELS
        principal_pnl = new JPanel();
        principal_pnl.setLayout(new BoxLayout(principal_pnl, BoxLayout.PAGE_AXIS));

        feature_pnl = new JPanel();
        feature_pnl.setLayout(new GridLayout(2, 2, 10, 10));

        panel_articles = new JPanel();
        panel_articles.setLayout(new BorderLayout());

        panel_emprunts = new JPanel();
        panel_emprunts.setLayout(new BorderLayout());

        panel_reservations = new JPanel();
        panel_reservations.setLayout(new BorderLayout());

        panel_utilisateur = new JPanel();
        panel_utilisateur.setLayout(new BorderLayout());

        boutons_choix_article = new JPanel();
        boutons_choix_article.setLayout(new GridLayout(2, 3, 5, 5));

        boutons_utilisateurs = new JPanel();
        boutons_utilisateurs.setLayout(new FlowLayout());

        // CREATION DES LISTES
        utilisateurs = new DefaultListModel();
        articles = new DefaultListModel();

        // CREATION DES BOUTONS
        info_btn = new JButton("Info");
        reserver_btn = new JButton("Réserver");
        emprunt_btn = new JButton("Emprunter");
        ajouter_article_btn = new JButton("Ajouter");
        modifier_article_btn = new JButton("Modifier");
        supprimer_article_btn = new JButton("Supprimer");
        ajouter_btn = new JButton("Ajouter");
        modifier_btn = new JButton("Modifier");
        supprimer_btn = new JButton("Supprimer");
        deconnection_btn = new JButton("Déconnection");
        annuler_reservation_btn = new JButton("Annuler Réservation");
        annuler_emprunt_btn = new JButton("Retour Emprunt");

        // POPULATION DES LISTES
        for (Utilisateur usager:BD.utilisateurs) {
            utilisateurs.addElement(usager.getNom());
        }
        choix_utilisateur = new JList(utilisateurs);
        choix_utilisateur.setLayoutOrientation(JList.VERTICAL);
        choix_utilisateur.setVisibleRowCount(5);
        choix_utilisateur.setSelectedIndex(0);
        utilisateur_selectionne = getUtilisateur();
        choix_utilisateur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        choix_utilisateur.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                utilisateur_selectionne = getUtilisateur();
                changementUtilisateur();
            }
        });

        scroll_choix_utilisateur = new JScrollPane(choix_utilisateur);

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

        articles_empruntes = new JList(utilisateur_selectionne.modelEmprunts);
        articles_empruntes.setLayoutOrientation(JList.VERTICAL);
        articles_empruntes.setVisibleRowCount(5);
        articles_empruntes.setSelectedIndex(0);
        emprunt_selectionne = empruntSelectionne();
        articles_empruntes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        articles_empruntes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                emprunt_selectionne = articleSelectionne();
            }
        });


        articles_reserves = new JList(utilisateur_selectionne.modelReservations);
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
        scroll_articles_empruntes = new JScrollPane(articles_empruntes);
        scroll_choix_article = new JScrollPane(choix_articles);

        // PLACEMENT DES WIDGETS
        ajouter_btn.addActionListener(this);
        boutons_utilisateurs.add(ajouter_btn);
        modifier_btn.addActionListener(this);
        boutons_utilisateurs.add(modifier_btn);
        supprimer_btn.addActionListener(this);
        boutons_utilisateurs.add(supprimer_btn);
        emprunt_btn.addActionListener(this);
        boutons_choix_article.add(emprunt_btn);
        info_btn.addActionListener(this);
        boutons_choix_article.add(info_btn);
        reserver_btn.addActionListener(this);
        boutons_choix_article.add(reserver_btn);
        ajouter_article_btn.addActionListener(this);
        boutons_choix_article.add(ajouter_article_btn);
        modifier_article_btn.addActionListener(this);
        boutons_choix_article.add(modifier_article_btn);
        supprimer_article_btn.addActionListener(this);
        boutons_choix_article.add(supprimer_article_btn);
        annuler_reservation_btn.addActionListener(this);
        annuler_emprunt_btn.addActionListener(this);
        panel_emprunts.add(scroll_articles_empruntes, BorderLayout.CENTER);
        panel_emprunts.add(annuler_emprunt_btn, BorderLayout.SOUTH);
        panel_emprunts.setBorder(BorderFactory.createTitledBorder("Article(s) emprunté(s)"));
        panel_utilisateur.add(scroll_choix_utilisateur, BorderLayout.CENTER);
        panel_utilisateur.add(boutons_utilisateurs, BorderLayout.SOUTH);
        panel_utilisateur.setBorder(BorderFactory.createTitledBorder("Liste d'usagers"));
        panel_articles.add(scroll_choix_article, BorderLayout.CENTER);
        panel_articles.add(boutons_choix_article, BorderLayout.SOUTH);
        panel_articles.setBorder(BorderFactory.createTitledBorder("Liste d'articles"));
        panel_reservations.add(scroll_articles_reserves, BorderLayout.CENTER);
        panel_reservations.add(annuler_reservation_btn, BorderLayout.SOUTH);
        panel_reservations.setBorder(BorderFactory.createTitledBorder("Article(s) réservé(s)"));
        deconnection_btn.addActionListener(this);
        deconnection_btn.setAlignmentX(CENTER_ALIGNMENT);

        if (utilisateur_selectionne.modelEmprunts.getSize() < 1) {
            annuler_emprunt_btn.setEnabled(false);
        }
        if (utilisateur_selectionne.modelReservations.getSize() < 1) {
            annuler_reservation_btn.setEnabled(false);
        }


        // PLACEMENT DES PANELS

        feature_pnl.add(panel_utilisateur);
        feature_pnl.add(panel_articles);
        feature_pnl.add(panel_reservations);
        feature_pnl.add(panel_emprunts);
        feature_pnl.setBorder(new EmptyBorder(10, 10, 10, 10));
        feature_pnl.setAlignmentX(CENTER_ALIGNMENT);

        principal_pnl.add(feature_pnl);
        deconnection_btn.setAlignmentX(CENTER_ALIGNMENT);
        principal_pnl.add(deconnection_btn);
    }

    /**
     * Gestion des boutons appuyés.
     * @param e Le bouton appuyé,
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Bouton info
        if (e.getSource() == info_btn) {
            information(article_selectionne);

        // Bouton Réserver.
        } else if (e.getSource() == reserver_btn) {
            if (article_selectionne.isReserve()) {
                JOptionPane.showMessageDialog(this, "Ce film est déjà réservé.", "", JOptionPane.WARNING_MESSAGE);
            } else {
                reservation_article(article_selectionne, utilisateur_selectionne);
            }

        // Bouton Annuler Réservation
        } else if (e.getSource() == annuler_reservation_btn) {
            annulation_reservation(reservation_selectionne, utilisateur_selectionne);

        // Bouton Emprunt
        } else if (e.getSource() == emprunt_btn) {
            if (article_selectionne.isEmprunte()) {
                JOptionPane.showMessageDialog(this, "Ce film est déjà emprunté.", "", JOptionPane.WARNING_MESSAGE);
            } else {
                emprunt_article(article_selectionne, utilisateur_selectionne);
            }
        // Bouton Annuler Emprunt
        } else if (e.getSource() == annuler_emprunt_btn) {
            annuler_emprunt(emprunt_selectionne, utilisateur_selectionne);

        // Bouton Ajouter Utilisateur
        } else if (e.getSource() == ajouter_btn) {
            ajouter_utilisateur();

        // Bouton Modifier Utilisateur
        } else if (e.getSource() == modifier_btn) {
            modifier_utilisateur(utilisateur_selectionne);

        // Bouton Supprimer Utilisateur
        } else if (e.getSource() == supprimer_btn) {
            supprimer_utilisateur(utilisateur_selectionne);

        //Bouton Ajouter Article
        } else if (e.getSource() == ajouter_article_btn) {
            ajouter_article();

        //Bouton Modifier Article
        } else if (e.getSource() == modifier_article_btn) {
            modifier_article(article_selectionne);

        //Bouton Supprimer Article
        }else if (e.getSource() == supprimer_article_btn) {
            supprimer_article(article_selectionne);

        // Bouton déconnection
        } else if(e.getSource() == deconnection_btn) {
            parent.deconnection();
        }
    }

    /**
     * Fait le changement du contenu des listes selon l'utilisateur sélectionné.
     */
    private void changementUtilisateur() {
        articles_reserves.setModel(utilisateur_selectionne.modelReservations);
        articles_empruntes.setModel(utilisateur_selectionne.modelEmprunts);
        if (utilisateur_selectionne.modelReservations.size() > 0) {
            annuler_reservation_btn.setEnabled(true);
            articles_reserves.setSelectedIndex(0);
        } else {
            annuler_reservation_btn.setEnabled(false);
        }
        if (utilisateur_selectionne.modelEmprunts.size() > 0) {
            annuler_emprunt_btn.setEnabled(true);
            articles_empruntes.setSelectedIndex(0);

        } else {
            annuler_emprunt_btn.setEnabled(false);
        }
    }

    /**
     * @return l'utilisateur présentement sélectionné.
     */
    private Utilisateur getUtilisateur() {
        Utilisateur utilisateur = null;
        int index = choix_utilisateur.getSelectedIndex();
        String nom_utilisateur = utilisateurs.get(index).toString();
        for (Utilisateur usager:BD.utilisateurs) {
            if (usager.getNom().equals(nom_utilisateur)) {
                utilisateur = usager;
            }
        }
        return utilisateur;
    }

    /**
     * Donne l'article qui est présentement sélectionné.
     *
     * @return article l'article sélectionné.
     */
    @Override
    public Article articleSelectionne() {
        Article article = null;
        int index = choix_articles.getSelectedIndex();
        String nom_article = articles.get(index).toString();
        for (Article a:BD.articles) {
            if (a.getNom().equals(nom_article)) {
                article = a;
            }
        }
        return article;
    }

    /**
     * Donne l'article qui est présentement sélectionné dans la section réservation.
     *
     * @return article l'article sélectionné.
     */
    @Override
    public Article reservationSelectionne() {
        Article article = null;
        int index = articles_reserves.getSelectedIndex();
        if (index >= 0) {
            String nom_article = utilisateur_selectionne.modelReservations.get(index).toString();
            for (Article a:BD.articles) {
                if (a.getNom().equals(nom_article)) {
                    article = a;
                }
            }
        }
        return article;
    }

    /**
     * @return L'article emprunté sélectionné.
     */
    @Override
    public Article empruntSelectionne() {
        Article article = null;
        int index = articles_empruntes.getSelectedIndex();
        if (index >= 0) {
            String nom_article = utilisateur_selectionne.modelEmprunts.get(index).toString();
            for (Article a:BD.articles) {
                if (a.getNom().equals(nom_article)) {
                    article = a;
                }
            }
        }
        return article;
    }

    /**
     * Effectue une réservation à un utilisateur.
     *
     * @param article     l'article à réservé.
     * @param utilisateur l'utilisateur qui fait la réservation.
     */
    @Override
    public void reservation_article(Article article, Utilisateur utilisateur) {
        article.setReservePar(utilisateur);
        utilisateur.modelReservations.addElement(article.getNom());
        articles_reserves.setModel(utilisateur.modelReservations);
        articles_reserves.setSelectedIndex(0);
        annuler_reservation_btn.setEnabled(true);
    }

    /**
     * Annule une réservation pour un utilisateur.
     *
     * @param article     l'article à annuler.
     * @param utilisateur l'utilisateur qui fait l'annulation.
     */
    @Override
    public void annulation_reservation(Article article, Utilisateur utilisateur) {
        article.setReservePar(null);
        utilisateur_selectionne.modelReservations.removeElement(article.getNom());
        if (utilisateur_selectionne.modelReservations.getSize() > 0) {
            articles_reserves.setSelectedIndex(0);
            reservation_selectionne = reservationSelectionne();
        } else {
            annuler_reservation_btn.setEnabled(false);
        }
    }

    /**
     * Emprunt un article pour un utilisateur.
     *
     * @param article     l'article à emprunter.
     * @param utilisateur l'utilisateur qui fait l'emprunt.
     */
    @Override
    public void emprunt_article(Article article, Utilisateur utilisateur) {
        article.setEmpruntePar(utilisateur);
        utilisateur.modelEmprunts.addElement(article.getNom());
        articles_empruntes.setModel(utilisateur.modelEmprunts);
        articles_empruntes.setSelectedIndex(0);
        annuler_emprunt_btn.setEnabled(true);
        article.setDateRetour(3);
    }

    /**
     * Annule un emprunt d'un article pour un utilisateur.
     *
     * @param article     l'article à annuler l'emprunt.
     * @param utilisateur l'utilisateur qui fait l'annulation.
     */
    @Override
    public void annuler_emprunt(Article article, Utilisateur utilisateur) {
        Date aujourdhui = Calendar.getInstance().getTime();
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        if (article.getDateRetour().getTime().before(aujourdhui)) {
            JOptionPane.showMessageDialog(this,
                    "Le film " + article.getNom() + " est en retard depuis le: "
                            + date.format(article.getDateRetour().getTime()) + ".\n"
                    + "Il y aura un retard à payer.",
                    "Retard",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        emprunt_selectionne.setEmpruntePar(null);
        utilisateur_selectionne.modelEmprunts.removeElement(emprunt_selectionne.getNom());
        if (utilisateur_selectionne.modelEmprunts.getSize() > 0) {
            articles_empruntes.setSelectedIndex(0);
            emprunt_selectionne = empruntSelectionne();
        } else {
            annuler_emprunt_btn.setEnabled(false);
        }
    }

    /**
     * Ajoute un utilisateur.
     */
    @Override
    public void ajouter_utilisateur() {
        String nom = JOptionPane.showInputDialog(this, "Nom d'utilisateur?",
                "Création d'un compte", JOptionPane.QUESTION_MESSAGE);
        if (nom != null && nom.length() > 0) {
            String mdp = JOptionPane.showInputDialog(this, "Mot de passe?",
                    "Création d'un compte", JOptionPane.QUESTION_MESSAGE);
            if (mdp != null && mdp.length() > 0) {
                Utilisateur nouvel_usager = new Utilisateur(nom, mdp.toCharArray());
                utilisateurs.addElement(nouvel_usager.getNom());
                BD.utilisateurs.add(nouvel_usager);
            }
        }
    }

    /**
     * Modifie un utilisateur.
     *
     * @param utilisateur L'utilisateur à modifier.
     */
    @Override
    public void modifier_utilisateur(Utilisateur utilisateur) {
        String mdp = JOptionPane.showInputDialog(this, "Nouveau mot de passe:",
                utilisateur.getNom(), JOptionPane.QUESTION_MESSAGE);
        if (mdp != null && mdp.length() > 0) {
            utilisateur.setMdp(mdp.toCharArray());
        }
    }

    /**
     * Supprime un utilisateur.
     *
     * @param utilisateur L'utilisateur à supprimer.
     */
    @Override
    public void supprimer_utilisateur(Utilisateur utilisateur) {
        if (utilisateurs.size() == 1) {
            JOptionPane.showMessageDialog(this, "Impossible de supprimer le dernier usager.", "", JOptionPane.WARNING_MESSAGE);
        } else {
            Object[] option = {"Oui", "Non"};
            int valide = JOptionPane.showOptionDialog(this,
                    "Voulez-vous vraiment supprimer l'usager: " + utilisateur.getNom() + " ?",
                    "Confirmation de la suppression", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
                    option, option[1]);
            if (valide == 0) {
                for (Article reservation : BD.articles) {
                    if (reservation.getReservePar() != null) {
                        if (reservation.getReservePar() == utilisateur) {
                            annulation_reservation(reservation, utilisateur);
                        }
                    }
                }
                for (Article emprunt : BD.articles) {
                    if (emprunt.getEmpruntePar() != null) {
                        if (emprunt.getEmpruntePar() == utilisateur) {
                            annuler_emprunt(emprunt, utilisateur);
                        }
                    }
                }
                BD.utilisateurs.remove(utilisateur);
                int index = choix_utilisateur.getSelectedIndex();
                if (index == 0) {
                    choix_utilisateur.setSelectedIndex(index + 1);
                } else {
                    choix_utilisateur.setSelectedIndex(index - 1);
                }
                utilisateurs.removeElementAt(index);
                changementUtilisateur();
            }

        }
    }

    /**
     * Ajoute un nouvel article.
     */
    @Override
    public void ajouter_article() {
        String nom = JOptionPane.showInputDialog(this, "Nom de l'article?",
                "Création d'un article", JOptionPane.QUESTION_MESSAGE);
        if (nom != null && nom.length() > 0) {
            String[] choix = new String[BD.categories.size()];

            for (int i = 0; i < BD.categories.size(); i++) {
                choix[i] = BD.categories.get(i).toString();
            }

            String categorie = (String)JOptionPane.showInputDialog(
                    this,
                    "Quel catégorie?",
                    "Création d'un article",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    choix,
                    choix[0]);
            for (Categorie cat:BD.categories) {
                if (cat.toString().equals(categorie)) {
                    Article article = new Article(nom, cat, false, null, false, null);
                    BD.articles.add(article);
                    articles.addElement(article.getNom());
                }
            }
        }
    }

    /**
     * Modifie le nom d'un article.
     *
     * @param article L'article à changer.
     */
    @Override
    public void modifier_article(Article article) {
        if (article.isEmprunte() || article.isReserve()) {
            JOptionPane.showMessageDialog(this,
                    "Impossible de modifier un article réservé et/ou emprunté",
                    "Modification impossible",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            String nom = JOptionPane.showInputDialog(this, "Nouveau Nom:",
                    article.getNom(), JOptionPane.QUESTION_MESSAGE);
            if (nom != null && nom.length() > 0) {

                String[] choix = new String[BD.categories.size()];
                for (int i = 0; i < BD.categories.size(); i++) {
                    choix[i] = BD.categories.get(i).toString();
                }

                String categorie = (String)JOptionPane.showInputDialog(
                        this,
                        "Quel catégorie?",
                        "Modification d'un article",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        choix,
                        choix[0]);
                for (Categorie cat:BD.categories) {
                    if (cat.toString().equals(categorie)) {
                        String ancien_nom = article.getNom();
                        int indexBD = BD.articles.indexOf(article);
                        int index = articles.indexOf(article.getNom());
                        BD.articles.remove(indexBD);
                        article.setNom(nom);
                        article.setCategorie(cat);
                        BD.articles.add(indexBD, article);
                        articles.insertElementAt(article.getNom(), index);
                        choix_articles.setSelectedIndex(index);
                        articles.removeElement(ancien_nom);
                        choix_articles.updateUI();
                    }
                }
            }

        }
    }

    /**
     * Supprime un article.
     *
     * @param article L'article à supprimer.
     */
    @Override
    public void supprimer_article(Article article) {
        if (articles.size() == 1) {
            JOptionPane.showMessageDialog(this, "Impossible de supprimer le dernier article.", "", JOptionPane.WARNING_MESSAGE);
        } else {
            Object[] option = {"Oui", "Non"};
            int valide = JOptionPane.showOptionDialog(this,
                    "Voulez-vous vraiment supprimer l'article: " + article.getNom() + " ?",
                    "Confirmation de la suppression", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
                    option, option[1]);
            if (valide == 0) {
                if (article.isEmprunte()) {
                    annuler_emprunt(article, article.getEmpruntePar());
                }
                if (article.isReserve()) {
                    annulation_reservation(article, article.getReservePar());
                }

                BD.articles.remove(article);
                int index = choix_articles.getSelectedIndex();
                if (index == 0) {
                    choix_articles.setSelectedIndex(index + 1);
                } else {
                    choix_articles.setSelectedIndex(index - 1);
                }
                articles.removeElementAt(index);
            }
        }

    }

    /**
     * Affiche les informations d'un article.
     *
     * @param article_choisi l'article à afficher.
     */
    @Override
    public void information(Article article_choisi) {
        String info_article = article_choisi.toString();
        JOptionPane.showMessageDialog(this, info_article, "Informations", JOptionPane.INFORMATION_MESSAGE);
    }
}
