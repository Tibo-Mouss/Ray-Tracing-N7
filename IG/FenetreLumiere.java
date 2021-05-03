package IG;

import rayTracing.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import utilitaire.*;
import utilitaire.Point;

import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreLumiere extends JFrame {
	
	private RayTracing raytracing;
	private ListeLumieres listeL;
	private JList<Lumieres> grilleL;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FenetreLumiere(String title, RayTracing rt, ListeLumieres l, JList<Lumieres> g) {	
		// fenetre de lumiere
		
		super(title);
		this.setSize(1000, 800);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//RayTracing
		
		this.raytracing = rt; 
		this.listeL = l;
		this.grilleL = g;
		// Boutons fenêtre 
			
		JButton button1 = new JButton("Ajouter Lumiere");
		JButton button2 = new JButton("Renommer");
		button1.addActionListener(new ActionAjouterLumiere());
		Container maincontainer = this.getContentPane();
		maincontainer.setLayout(new BorderLayout(8,6));
		maincontainer.setBackground(Color.WHITE);
		
		// panel qui contient tous les autres panel 
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new LineBorder(Color.BLACK,1));
		topPanel.setBackground(new Color(227,223,223));
		topPanel.setLayout(new GridLayout(1,4,0,0));
		maincontainer.add(topPanel,BorderLayout.NORTH);
		
		
		
		// panel des couleur
		
		JPanel couleur = new JPanel();
		couleur.setBorder(new LineBorder(Color.BLACK,1));
		couleur.setLayout(new GridLayout(2,1,0,0));
		couleur.setBackground(new Color(227,223,223));
		topPanel.add(couleur);
		JPanel valeurcouleur = new JPanel();
		valeurcouleur.setBackground(new Color(227,223,223));
		
		// rgb 
		
		JLabel title00 = new JLabel("Couleur par RGB :");
		title00.setHorizontalAlignment(JLabel.CENTER);
		JLabel redp = new JLabel("Rouge");
		JTextField redt = new JTextField();
		redt.setPreferredSize(new Dimension(30, 30));
		JLabel greenp = new JLabel("Vert");
		JTextField greent = new JTextField();
		greent.setPreferredSize(new Dimension(30, 30));
		JLabel bluep = new JLabel("Bleue");
		JTextField bluet = new JTextField();
		bluet.setPreferredSize(new Dimension(30, 30));
		   

		couleur.add(title00,BorderLayout.CENTER);
		couleur.add(valeurcouleur);
		valeurcouleur.add(redp);
		valeurcouleur.add(redt);
		valeurcouleur.add(greenp);
		valeurcouleur.add(greent);
		valeurcouleur.add(bluep);
		valeurcouleur.add(bluet);
		


		// panel des intensites
		
		JPanel intensite = new JPanel();
		intensite.setBorder(new LineBorder(Color.BLACK,1));
		intensite.setLayout(new GridLayout(2,1,5,5));
		intensite.setBackground(new Color(227,223,223));
		
		topPanel.add(intensite);
		
		// rgb 
		
		JLabel title01 = new JLabel("Intensité :");
		title01.setHorizontalAlignment(JLabel.CENTER);
		JTextField inten = new JTextField();
		inten.setPreferredSize(new Dimension(100, 30));	
		JPanel intensiteval = new JPanel();
		intensiteval.setBackground(new Color(227,223,223));   
		
		intensite.add(title01,BorderLayout.CENTER);
		intensite.add(intensiteval);
		intensiteval.add(inten);

				
		// panel des positions
		
		JPanel position = new JPanel();
		position.setBorder(new LineBorder(Color.BLACK,1));
		position.setBackground(new Color(227,223,223));
		position.setLayout(new GridLayout(2,1,5,5));
		topPanel.add(position);
		
		
		JLabel title02 = new JLabel("Position :");
		title02.setHorizontalAlignment(JLabel.CENTER);
		JTextField posx = new JTextField();
		JTextField posy = new JTextField();
		JTextField posz = new JTextField();
		JLabel xnom = new JLabel("X :");
		JLabel ynom = new JLabel("Y :");
		JLabel znom = new JLabel("Z :");
		posx.setPreferredSize(new Dimension(30, 30));
		posy.setPreferredSize(new Dimension(30, 30));
		posz.setPreferredSize(new Dimension(30, 30));	
		JPanel positionvals = new JPanel();
		positionvals.setBackground(new Color(227,223,223));   
		
		position.add(title02,BorderLayout.CENTER);
		position.add(positionvals);
		positionvals.add(xnom);
		positionvals.add(posx);
		positionvals.add(ynom);
		positionvals.add(posy);
		positionvals.add(znom);
		positionvals.add(posz);
		
		
		// panel des direction
		
		JPanel direction = new JPanel();
		direction.setBorder(new LineBorder(Color.BLACK,1));
		direction.setLayout(new GridLayout(2,1,5,5));
		direction.setBackground(new Color(227,223,223));
		topPanel.add(direction);
		
		
		JLabel title03 = new JLabel("Direction :");
		title03.setHorizontalAlignment(JLabel.CENTER);
		JTextField vposx = new JTextField();
		JTextField vposy = new JTextField();
		JTextField vposz = new JTextField();
		JLabel vxnom = new JLabel("Vx :");
		JLabel vynom = new JLabel("Vy :");
		JLabel vznom = new JLabel("Vz :");
		vposx.setPreferredSize(new Dimension(30, 30));
		vposy.setPreferredSize(new Dimension(30, 30));
		vposz.setPreferredSize(new Dimension(30, 30));	
		JPanel directionvals = new JPanel();
		directionvals.setBackground(new Color(227,223,223));   
		
		direction.add(title03,BorderLayout.CENTER);
		direction.add(directionvals);
		directionvals.add(vxnom);
		directionvals.add(vposx);
		directionvals.add(vynom);
		directionvals.add(vposy);
		directionvals.add(vznom);
		directionvals.add(vposz);
				
		//Boutton
		
		couleur.setPreferredSize(new Dimension(160,105));
		button1.setPreferredSize(new Dimension(175, 50));
		button2.setPreferredSize(new Dimension(175, 50));

		
		// Rightpanel
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new LineBorder(Color.BLACK,2));
		rightPanel.setBackground(new Color(227,223,223));
		maincontainer.add(rightPanel,BorderLayout.EAST);
		
		JPanel bouttons = new JPanel();
		bouttons.setBorder(new LineBorder(Color.BLACK,2));
		bouttons.setBackground(new Color(227,223,223));
		bouttons.setLayout(new GridLayout(3,1,0,0));

		rightPanel.add(bouttons, BorderLayout.CENTER);
		
		bouttons.add(button1);
		bouttons.add(button2);
		
	}
	
	public class ActionAjouterLumiere implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
		    LumierePonctuelle lumiere = new LumierePonctuelle(new Point(0,5,10), Color.BLUE, "LumiereTest");
		    listeL.addElement(new Lumieres(lumiere));
		    grilleL.updateUI();

		}
	}
	public static void main(String[] args) {
		//FenetreLumiere mylumiere = new FenetreLumiere("Lumiere",null,null);
		//mylumiere.setVisible(true);
	}
}
