package IG;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;

import elements3D.Objet3D;
import elements3D.Sphere;
import rayTracing.Camera;
import rayTracing.RayTracing;
import rayTracing.Scene;
import utilitaire.Point;

public class FenetrePrincipale extends JFrame {
	
	private RayTracing rayTracing;
	private ListeObjets listeO;
	private ListeLumieres listeL;

	final private JButton addObjects = new JButton("Ajouter Objet",new ImageIcon("IG/objet.png"));
	final private JButton addLight = new JButton("Ajouter Lumière", new ImageIcon("IG/lumiere.png"));
	
	
	final private JButton lancerRT = new JButton("Lancer Calcul");
	final private JButton enregistrerI = new JButton("Capture Scène");
	final private JButton enregistrerS = new JButton("Enregistrer Scène");

	final private JLabel image = new JLabel();
	
	final private JLabel objets = new JLabel("Objets");
	final private JButton plusObjets = new JButton("+");
	final private JLabel  lumieres = new JLabel("Lumières");
	final private JButton plusLumieres = new JButton("+");
	
	final private JLabel rebonds = new JLabel("Nombre de rebonds ");
	final private JSpinner nbRebonds = new JSpinner(new SpinnerNumberModel(1,0,10,1));
	
	final private JRadioButton ombre = new JRadioButton("Activer Ombres");
	final private JRadioButton shadding = new JRadioButton("Activer Méthode Shadding");
	
	final private JList<Objet> grilleO;
	final private JList<Lumieres> grilleL;
	final private JPanel grilleObjet;
	final private JPanel grilleLumiere;
	
	final private JScrollPane grilleOScroller;
	
	public FenetrePrincipale(String nom, RayTracing nrayTracing) {
		super(nom);
		this.rayTracing = nrayTracing;
		this.listeO = new ListeObjets();
		this.listeL = new ListeLumieres();
		
		this.pack();
		this.setLocation(100,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// A supprimer lorsque IG objet sera implentée
		//List<Objet3D> listObjets = rayTracing.getScene().getObjet3D();
		
		//Ajout du menu 
		
		this.setJMenuBar(createMenu());
		
		
		
		// Mise en place du contenu de la fenêtre 
		
		
		
		//Initialisation du container principal
		Container contenu = this.getContentPane();
		contenu.setLayout(new BorderLayout(8,6));
			
		//Mise en place du haut de la fenêtre 
		
		
		//Initialisation de la zone de haut
		JPanel haut = new JPanel();
		haut.setBorder(new LineBorder(Color.BLACK, 3));
		haut.setLayout(new FlowLayout(5));
		contenu.add(haut, BorderLayout.NORTH);
		
		// Modification du graphisme des boutons du haut 
		addObjects.setVerticalTextPosition(SwingConstants.BOTTOM);
		addObjects.setHorizontalTextPosition(SwingConstants.CENTER);  
		addLight.setVerticalTextPosition(SwingConstants.BOTTOM);
		addLight.setHorizontalTextPosition(SwingConstants.CENTER);  
		
		// Modification de la taille des boutons du haut 
		addObjects.setPreferredSize(new Dimension(130,125));
		addLight.setPreferredSize(new Dimension(130,125));
		
		//Ajout des actions 
		addObjects.addActionListener(new ActionAjouterObjet());
		addLight.addActionListener(new ActionAjouterLumiere());
		
		// Ajout des boutons dans la zone du haut 
		haut.add(addObjects);
		haut.add(addLight);
		
		
		//Mise en place de la partie centrale de la fenêtre 
		
		
		//Initialisation de la zone centrale
		
		JPanel centre = new JPanel();
		centre.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK,3), new EmptyBorder(3,3,3,3)));
		//centre.setLayout(new FlowLayout(listObjets.size() + 1, 2, 2));
		centre.setLayout(new BorderLayout());
		
		
		// Ajout de la grille à droite du centre de la fenêtre
		
		//Initialisation de la grille
		JPanel grille = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		grille.setLayout(gridbag);
		//grille.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK,2), new EmptyBorder(3,3,3,3)));
		GridBagConstraints c = new GridBagConstraints();
		
		//Mise en place de la première ligne de la grille
		//Positionnement et modification du Label objets
		c.gridwidth = GridBagConstraints.RELATIVE;
		objets.setHorizontalAlignment(SwingConstants.CENTER);
		objets.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		objets.setPreferredSize(new Dimension(150,50));
        gridbag.setConstraints(objets, c);
        grille.add(objets);
        
		//Positionnement et modification du Label plusObjets
        c.gridwidth = GridBagConstraints.REMAINDER;
        plusObjets.setPreferredSize(new Dimension(50,50));
    	plusObjets.addActionListener(new ActionAjouterObjet());
		gridbag.setConstraints(plusObjets,c);
		grille.add(plusObjets);
		
		//Initialisation et ajout de la grille Objet 
		this.grilleObjet = new JPanel();
		grilleO = new JList<Objet>(this.listeO); 
		grilleO.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		grilleO.setLayoutOrientation(JList.VERTICAL);
		grilleO.setVisibleRowCount(-1);
		grilleO.setPreferredSize(new Dimension(150,1000));
		
		grilleOScroller = new JScrollPane(grilleO,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollBar barO = grilleOScroller.getVerticalScrollBar();
		barO.setBounds(10,10,5,10);
		grilleOScroller.setVerticalScrollBar(barO);
		grilleOScroller.setPreferredSize(new Dimension(200, 80));

		grilleObjet.add(grilleOScroller);
		
		c.weightx = 0.0; 
        gridbag.setConstraints(grilleObjet, c);
        grille.add(grilleObjet);
        
		//Mise en place de la deuxième ligne de la grille
		//Positionnement et modification du Label lumières
		c.gridwidth = GridBagConstraints.RELATIVE;
		lumieres.setHorizontalAlignment(SwingConstants.CENTER);
		lumieres.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		lumieres.setPreferredSize(new Dimension(150,50));
        gridbag.setConstraints(lumieres, c);
        grille.add(lumieres);


		//Positionnement et modification du Label plusLumieres
        c.gridwidth = GridBagConstraints.REMAINDER;
        plusLumieres.addActionListener(new ActionAjouterLumiere());
        plusLumieres.setPreferredSize(new Dimension(50,50));
		gridbag.setConstraints(plusLumieres,c);
		grille.add(plusLumieres);
		
		//Initialisation et ajout de la grilleLumiere
		this.grilleLumiere = new JPanel();
		grilleL = new JList<Lumieres> (this.listeL);
		grilleL.addMouseListener(new ActionOuvrirLumiere());
		grilleL.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		grilleL.setLayoutOrientation(JList.VERTICAL);
		grilleL.setVisibleRowCount(-1);
		grilleL.setPreferredSize(new Dimension(150,1000));
		
		JScrollPane grilleLScroller = new JScrollPane(grilleL,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		grilleLScroller.setPreferredSize(new Dimension(200, 80));
	
		grilleLumiere.add(grilleLScroller);
		
		c.weightx = 0.0; 
        gridbag.setConstraints(grilleLumiere, c);
        grille.add(grilleLumiere);
		
        //Ajout de la grille principale dans la zone centrale
        
		centre.add(grille,BorderLayout.NORTH);
		
        //Ajout RadioButton
		JPanel centreBas = new JPanel();
        GridBagLayout centreB = new GridBagLayout();
        centreBas.setLayout(centreB);
        centreBas.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK,2), new EmptyBorder(3,3,3,3)));
        
		c.gridwidth = GridBagConstraints.RELATIVE;
		rebonds.setHorizontalAlignment(SwingConstants.CENTER);
		rebonds.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        rebonds.setPreferredSize(new Dimension(150,50));
        centreB.setConstraints(rebonds, c);
        centreBas.add(rebonds);
        
        c.gridwidth = GridBagConstraints.REMAINDER;
        nbRebonds.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        nbRebonds.setPreferredSize(new Dimension(50,50));
        centreB.setConstraints(nbRebonds, c);
        centreBas.add(nbRebonds);
        
        c.weightx = 0.0; 
        c.gridx = 0;
        shadding.setAlignmentX(LEFT_ALIGNMENT);
        ombre.setAlignmentX(LEFT_ALIGNMENT);
        shadding.setPreferredSize(new Dimension(200,50));
        ombre.setPreferredSize(new Dimension(200,50));
        centreB.setConstraints(shadding,c); 
        centreBas.add(shadding);
        centreB.setConstraints(ombre,c);
        centreBas.add(ombre);

		centre.add(centreBas, BorderLayout.SOUTH);
		
		
		contenu.add(centre, BorderLayout.EAST);
		
		//Initialisation et ajout de l'aperçu de la scene
		
		JPanel scene = new JPanel();
		scene.setLayout(new BorderLayout());
		scene.setBackground(Color.BLACK);
		
		JPanel sceneRT = new JPanel();
		sceneRT.setLayout(new FlowLayout());
		
		image.setBorder(new LineBorder(Color.BLACK, 3));
		//image.setIcon(new ImageIcon(rayTracing.getCamera().creerImage()));
		sceneRT.add(image);
		
		scene.add(sceneRT, BorderLayout.CENTER);
		
		contenu.add(scene);
		

		//Mise en place du bas de la fenetre
		
		//Initialisation de la zone de bas
		JPanel bas = new JPanel();
		bas.setLayout(new BorderLayout());
		
		JPanel basDroite = new JPanel();
		basDroite.setLayout(new FlowLayout());
		
		//Modification de la taille des boutons du bas
		enregistrerI.setPreferredSize(new Dimension(175,50));
		enregistrerS.setPreferredSize(new Dimension(175,50));
		lancerRT.setPreferredSize(new Dimension(175,50));
		
		//Ajout des actions aux boutons 
		enregistrerI.addActionListener(new ActionEnregistrerI());
		enregistrerS.addActionListener(new ActionEnregistrerS());
		lancerRT.addActionListener(new ActionLancerCalcul());
		
		// Ajout des boutons dans le panel basDroite
		basDroite.add(lancerRT);
		basDroite.add(enregistrerI);
		basDroite.add(enregistrerS);
		
		bas.add(basDroite, BorderLayout.EAST);
		contenu.add(bas, BorderLayout.SOUTH);
	}

	public class ActionOuvrirLumiere extends MouseAdapter {
		public void mouseClicked(MouseEvent evt) {
	        if (evt.getClickCount() == 2) {
				FenetreLumiere lux = new FenetreLumiere("Lumière", rayTracing,listeL,grilleL);
				lux.setVisible(true);
	        } else if (evt.getClickCount() == 3) {
				FenetreLumiere lux = new FenetreLumiere("Lumière", rayTracing,listeL,grilleL);
				lux.setVisible(true);
	        }
		}
	}
	
	public class ActionOuvrirObjet extends MouseAdapter {
		public void mouseClicked(MouseEvent evt) {
	        if (evt.getClickCount() == 2) {
				//FenetreLumiere lux = new FenetreLumiere("Lumière", rayTracing,listeL,grilleL);
				//lux.setVisible(true);
	        } else if (evt.getClickCount() == 3) {
				//FenetreLumiere lux = new FenetreLumiere("Lumière", rayTracing,listeL,grilleL);
				//lux.setVisible(true);
	        }
		}
	}
	
	public class ActionEnregistrerI implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			EnregistrerImage enregistrer = new EnregistrerImage( "Enregistrer Capture Image", rayTracing);
			enregistrer.setVisible(true);
			
		}
	}
	
	public class ActionEnregistrerS implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			EnregistrerScene enregistrer = new EnregistrerScene( "Enregistrer Scène", rayTracing);
			enregistrer.setVisible(true);
			
		}
	}
	
	
	public class ActionLancerCalcul implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			rayTracing.lancerRayTracing();
			System.out.printf("Calcul lancé ...\n");
			image.setIcon(new ImageIcon(rayTracing.getCamera().creerImage()));
			System.out.printf("Calcul Terminé \n");
		}
	}
	
	public class ActionAjouterObjet implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
		    Sphere sphere_test = new Sphere(new Point(0,5,10), 1, "SphereTest");
			//rayTracing.getScene().addObjet3D(sphere_test);
			ajouterObjet(new Objet(sphere_test));
		}
	}
	
	public class ActionAjouterLumiere implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			FenetreLumiere lux = new FenetreLumiere("Lumière", rayTracing,listeL,grilleL);
			lux.setVisible(true);
		}
	}
	private JMenuBar createMenu() {
		
		JMenuBar menu = new JMenuBar();
		
		// Définition du menu déroulant "File" et de son contenu
		
        JMenu menuFichier = new JMenu( "Fichier" );
        menuFichier.setMnemonic( 'F' );
        
        // Ajout de l'item "New File"
        JMenuItem itemNouveauFichier = new JMenuItem( "Nouveau Fichier" );
        itemNouveauFichier.setMnemonic( 'N' );
        menuFichier.add(itemNouveauFichier);
		
        menuFichier.addSeparator();
        
        //Ajout de l'item "Save File"
        JMenu itemEnregistrer = new JMenu( "Sauvegarder" );
        itemEnregistrer.setMnemonic( 'S' );
        JMenuItem itemEnregistrerI = new JMenuItem( "Capture Scène" );
        itemEnregistrerI.addActionListener(new ActionEnregistrerI());
        itemEnregistrer.add(itemEnregistrerI);
        JMenuItem itemEnregistrerS = new JMenuItem( "Sauvegarder Scène" );
        itemEnregistrerS.addActionListener(new ActionEnregistrerS());
        itemEnregistrer.add(itemEnregistrerS);
        menuFichier.add(itemEnregistrer);
	
        menuFichier.addSeparator();
        
        
        //Ajout de l'item "Open File"
        JMenuItem itemOuvrir= new JMenuItem( "Ouvrir Fichier" );
        itemOuvrir.setMnemonic( 'O' );
        menuFichier.add(itemOuvrir);
        
        
        //Ajout du Menu Fichier dans la barre de Menu
        
        menu.add(menuFichier);
        
        
        //Définition du menu déroulant "Edit" et de son contenu
        
        JMenu menuEditer = new JMenu("Editer");
        menuEditer.setMnemonic('E');
        
        //Ajout de l'item "Add Object"
        JMenuItem itemAjoutObjet = new JMenuItem( "Ajouter Objet" );
        itemAjoutObjet.setMnemonic( 'B' );
        itemAjoutObjet.addActionListener(new ActionAjouterObjet());
        menuEditer.add(itemAjoutObjet);
	
        //Ajout de l'item "Add Object"
        JMenuItem itemAjoutLumiere = new JMenuItem( "Ajouter Lumière" );
        itemAjoutLumiere.setMnemonic( 'L' );
        itemAjoutLumiere.addActionListener(new ActionAjouterLumiere());
        menuEditer.add(itemAjoutLumiere);
	
        // Ajout du menu déroulant Edit dans la barre de menu
        
        menu.add(menuEditer);
        
        // Item "Help"
        
        JMenuItem itemAide = new JMenuItem("Aide");
        itemAide.setMnemonic('A');
        
        //Ajout de l'item Help dans la barre de menu
        
        menu.add(itemAide);
        
        return menu;
        
	}
	
	public void ajouterObjet(Objet o) {
		this.listeO.addElement(o);
		this.grilleO.updateUI();	
		
	}
	
	public static void main(String[] args) {
		FenetrePrincipale principale = new FenetrePrincipale("Lumiere",null);
		principale.setVisible(true);
	}
}

