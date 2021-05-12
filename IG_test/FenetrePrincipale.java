package IG_test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;


import elements3D.Sphere;
import rayTracing.RayTracing;
import utilitaire.Point;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;

public class FenetrePrincipale {

	private JFrame frame;
	private ListeObjets listeO;
	private ListeLumieres listeL;
	private JList<Objet> listObjets;
	private JList<Lumieres> listLumieres;
	private RayTracing rayTracing;
	private JLabel imageRT;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePrincipale window = new FenetrePrincipale(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FenetrePrincipale(RayTracing nrt) {
		this.rayTracing = nrt;
		this.listeO = new ListeObjets();
		this.listeL = new ListeLumieres();
		this.imageRT = new JLabel("");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(new Dimension(1087, 674));
		frame.setResizable(false);
		frame.setBackground(new Color(240, 240, 240));
		frame.getContentPane().setForeground(new Color(102, 205, 170));
		frame.getContentPane().setBackground(new Color(32, 178, 170));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 128, 128));
		frame.setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("Fichier");
		menuFile.setMnemonic( 'F' );
		menuFile.setBackground(new Color(0, 128, 128));
		menuBar.add(menuFile);
		
		JMenuItem itemEnregistrerS = new JMenuItem("Enregistrer Sc\u00E8ne");
		 itemEnregistrerS.addActionListener(new ActionEnregistrerS());
		itemEnregistrerS.setMnemonic( 'S' );
		menuFile.add( itemEnregistrerS);
		
		JMenuItem  itemOuvrir = new JMenuItem("Ouvrir ");
		itemOuvrir.setMnemonic( 'O' );
		menuFile.add(itemOuvrir);
		
		JMenuItem  itemEnregistrerI = new JMenuItem("Capture Image");
		itemEnregistrerI.addActionListener(new ActionEnregistrerI());
		itemEnregistrerI.setMnemonic( 'C' );
		menuFile.add( itemEnregistrerI);
		
		JMenu menuEditer = new JMenu("Editer");
		menuEditer.setMnemonic( 'E' );
		menuBar.add(menuEditer);
		
		JMenuItem itemAjouterO = new JMenuItem("Ajouter Objet");
		itemAjouterO.setMnemonic( 'B' );
	    itemAjouterO.addActionListener(new ActionAjouterObjet());
		menuEditer.add(itemAjouterO);
		
		JMenuItem itemAjouterL = new JMenuItem("Ajouter Lumière");
		itemAjouterL.setMnemonic( 'L' );
		itemAjouterL.addActionListener(new ActionAjouterLumiere());
		menuEditer.add(itemAjouterL);
		
		JMenu menuAide = new JMenu("Aide");
		menuAide.setMnemonic( 'A' );
		menuBar.add(menuAide);
		
		frame.getContentPane().setLayout(null);
		
		JButton ajouterObjet = new JButton("",new ImageIcon("IG_test/objet.png"));
		ajouterObjet.setBackground(new Color(0, 139, 139));
		ajouterObjet.setForeground(new Color(0, 0, 0));
		ajouterObjet.addActionListener(new ActionAjouterObjet());
		ajouterObjet.setToolTipText("");
		ajouterObjet.setBounds(10, 11, 70, 73);
		frame.getContentPane().add(ajouterObjet);
		
		JButton ajouterLumiere = new JButton("");
		ajouterLumiere.setBackground(new Color(0, 128, 128));
		ajouterLumiere.addActionListener(new ActionAjouterLumiere());
		ajouterLumiere.setIcon(new ImageIcon("IG_test/lumiere.png"));
		ajouterLumiere.setBounds(89, 11, 70, 73);
		frame.getContentPane().add(ajouterLumiere);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(64, 224, 208));
		panel.setBorder(new LineBorder(new Color(64, 224, 208)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 95, 826, 473);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		imageRT.setBounds(0, 0, 826, 473);
		panel.add(imageRT);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(72, 209, 204));
		panel_1.setBorder(new LineBorder(new Color(64, 224, 208), 1, true));
		panel_1.setBounds(846, 95, 217, 473);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton plusObjet = new JButton("+");
	   	plusObjet.addActionListener(new ActionAjouterObjet());
		plusObjet.setBackground(new Color(0, 139, 139));
		plusObjet.setBounds(166, 10, 41, 42);
		panel_1.add(plusObjet);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 52, 197, 125);
		panel_1.add(scrollPane);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		listObjets = new JList<Objet>(this.listeO);
		scrollPane.setViewportView(listObjets);
		
		listObjets.addMouseListener(new ActionOuvrirObjet());
		listObjets.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(10, 222, 197, 125);
		panel_1.add(scrollPane_1);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollPane_1.setRowHeaderView(scrollBar_1);
		listLumieres = new JList<Lumieres>(this.listeL);
		scrollPane_1.setViewportView(listLumieres);
		
		listLumieres.addMouseListener(new ActionOuvrirLumiere());
		listLumieres.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		JButton plusLumieres = new JButton("+");
	   	plusLumieres.addActionListener(new ActionAjouterLumiere());
		plusLumieres.setBackground(new Color(0, 128, 128));
		plusLumieres.setBounds(166, 180, 41, 42);
		panel_1.add(plusLumieres);
		
		JLabel objets = new JLabel("Objets 3D");
		objets.setHorizontalAlignment(SwingConstants.CENTER);
		objets.setBackground(new Color(95, 158, 160));
		objets.setForeground(Color.BLACK);
		objets.setBounds(10, 11, 158, 42);
		panel_1.add(objets);
		
		JLabel lumiere = new JLabel("Lumi\u00E8res");
		lumiere.setHorizontalAlignment(SwingConstants.CENTER);
		lumiere.setBackground(Color.GRAY);
		lumiere.setBounds(10, 180, 158, 42);
		panel_1.add(lumiere);
		
		JButton btnParametre = new JButton("Param\u00E9trer Ray Tracing ");
		btnParametre.addActionListener(new ActionParametrer());
		btnParametre.setBackground(new Color(0, 128, 128));
		btnParametre.setBounds(10, 360, 197, 23);
		panel_1.add(btnParametre);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionQuitter());
		btnAnnuler.setBackground(new Color(0, 128, 128));
		btnAnnuler.setBounds(974, 584, 89, 23);
		frame.getContentPane().add(btnAnnuler);
		
		JButton btnEnregistrerS = new JButton("Enregistrer Sc\u00E8ne");
		btnEnregistrerS.addActionListener(new ActionEnregistrerS());
		btnEnregistrerS.setBackground(new Color(0, 128, 128));
		btnEnregistrerS.setBounds(764, 584, 200, 23);
		frame.getContentPane().add(btnEnregistrerS);
		
		JButton btnEnregistrerI = new JButton("Capture Image");
		btnEnregistrerI.addActionListener(new ActionEnregistrerI());
		btnEnregistrerI.setBackground(new Color(0, 128, 128));
		btnEnregistrerI.setBounds(554, 584, 200, 23);
		frame.getContentPane().add(btnEnregistrerI);
		
		JButton lancerRT = new JButton("Lancer RayTracing");
		lancerRT.setBackground(new Color(0, 128, 128));
		lancerRT.setBounds(10, 584, 200, 23);
		frame.getContentPane().add(lancerRT);
		
	
	}
	
	public class ActionOuvrirLumiere extends MouseAdapter {
		public void mouseClicked(MouseEvent evt) {
	        if (evt.getClickCount() == 2) {
				FenetreLumiere lux = new FenetreLumiere(rayTracing,listeL,listLumieres);
				lux.setVisible(true);
	        } else if (evt.getClickCount() == 3) {
				FenetreLumiere lux = new FenetreLumiere(rayTracing,listeL,listLumieres);
				lux.setVisible(true);
	        }
		}
	}
	
	public class ActionOuvrirObjet extends MouseAdapter {
		public void mouseClicked(MouseEvent evt) {
	        if (evt.getClickCount() == 2) {
				FenetreObjet objet = new FenetreObjet(rayTracing,listeO,listObjets);
				objet.setVisible(true);
	        } else if (evt.getClickCount() == 3) {
				FenetreObjet objet = new FenetreObjet(rayTracing,listeO,listObjets);
				objet.setVisible(true);
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
			imageRT.setIcon(new ImageIcon(rayTracing.getCamera().creerImage()));
			System.out.printf("Calcul Terminé \n");
		}
	}
	
	public class ActionAjouterObjet implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			FenetreObjet objet = new FenetreObjet(rayTracing,listeO,listObjets);
			objet.setVisible(true);
		}
	}
	
	public class ActionAjouterLumiere implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			FenetreLumiere lux = new FenetreLumiere(rayTracing,listeL,listLumieres);
			lux.setVisible(true);
		}
	}
	
	public class ActionParametrer implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Parametrage parametre = new Parametrage();
			parametre.setVisible(true);
		}
	}
	
	public class ActionQuitter implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			System.exit(0);
		}
	}
}
