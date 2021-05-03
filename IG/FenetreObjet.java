package IG;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import rayTracing.RayTracing;

public class FenetreObjet extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static RayTracing rayTracing;
	final private JButton enregistrer = new JButton("Enregistrer");
	final private JButton annuler = new JButton("Annuler");
	final private JButton ajouter = new JButton("Ajouter");
	
	
	public FenetreObjet (String nom, RayTracing rayTracing) {
		super(nom);
		FenetreObjet.rayTracing = rayTracing;
	    this.setSize(1000, 1000);
	    this.setLocation(100, 100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
	    Container maincontainer = this.getContentPane();
		maincontainer.setLayout(new BorderLayout(0,0));
		maincontainer.setBackground(Color.WHITE);
		
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new LineBorder(Color.BLACK,1));
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setLayout(new GridLayout(10,1,0,0));
		maincontainer.add(leftPanel,BorderLayout.WEST);
		
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new LineBorder(Color.BLACK,1));
		rightPanel.setBackground(Color.WHITE);
		rightPanel.setLayout(new BorderLayout());
		maincontainer.add(rightPanel,BorderLayout.EAST);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new LineBorder(Color.BLACK,1));
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setLayout(new BorderLayout());
		maincontainer.add(bottomPanel,BorderLayout.SOUTH);
	    
		//dimension
		JPanel dimension = new JPanel();
		dimension.setBorder(new LineBorder(Color.BLACK,1));
		dimension.setBackground(Color.WHITE);
		dimension.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel dimention = new JLabel("    Dimension:         ");
		leftPanel.add(dimension);
		dimension.add(dimention);
		dimention.setHorizontalAlignment(JLabel.LEFT);
		JTextField dimen = new JTextField();
		dimen.setPreferredSize(new Dimension(100, 40));	
		dimension.add(dimen);
		
		
		//position
		JPanel position = new JPanel();
		position.setBorder(new LineBorder(Color.BLACK,1));
		position.setBackground(Color.WHITE);
		position.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lposition = new JLabel("    Position:   ");
		JLabel xnom = new JLabel("      X:");
		JLabel ynom = new JLabel("  Y:");
		JLabel znom = new JLabel("  Z:");
		leftPanel.add(position);
		position.add(lposition);
		JTextField x= new JTextField();
		JTextField y= new JTextField();
		JTextField z= new JTextField();
		x.setPreferredSize(new Dimension(30, 30));	
		y.setPreferredSize(new Dimension(30, 30));	
		z.setPreferredSize(new Dimension(30, 30));
		position.add(xnom);
		position.add(x);
		position.add(ynom);
		position.add(y);
		position.add(znom);
		position.add(z);
		
		
		//parametre de objet
		JPanel objet = new JPanel();
		objet.setBorder(new LineBorder(Color.BLACK,1));
		objet.setBackground(Color.WHITE);
		objet.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lobjet = new JLabel("    Parametre de Objet: ",JLabel.CENTER);
		leftPanel.add(objet);
		objet.add(lobjet);
		
		 
		//forme de objet
		JPanel forme = new JPanel();
		forme.setBorder(new LineBorder(Color.BLACK,1));
		forme.setBackground(Color.WHITE);
		forme.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 1));
		JLabel lforme = new JLabel("    Forme: ",JLabel.LEFT);
		JLabel image1 = new JLabel(new ImageIcon("/home/yliu6/1a/TOB/tps/KL-4/src/IG/1.png"));
		JLabel image2 = new JLabel(new ImageIcon("/home/yliu6/1a/TOB/tps/KL-4/src/IG/2.png"));
		JLabel image3 = new JLabel(new ImageIcon("/home/yliu6/1a/TOB/tps/KL-4/src/IG/3.png"));
		JLabel image4 = new JLabel(new ImageIcon("/home/yliu6/1a/TOB/tps/KL-4/src/IG/4.png"));
		JButton addObjet = new JButton ("+");
		//image1.setBounds(20, 40, 20, 20);
		//image2.setBounds(20, 40, 10, 10);
		//image3.setBounds(20, 40, 10, 10);
		//image4.setBounds(20, 40, 10, 10);
		leftPanel.add(forme);
		forme.add(lforme);
		forme.add(image1);
		forme.add(image2);
		forme.add(image3);
		forme.add(image4);
		forme.add(addObjet);
		
		
		//nom de objet
		JPanel name = new JPanel();
		name.setBorder(new LineBorder(Color.BLACK,1));
		name.setBackground(Color.WHITE);
		name.setLayout(new GridLayout(1,4,0,0));
		JLabel lname = new JLabel("      Nom: ",JLabel.LEFT);
		leftPanel.add(name);
		name.add(lname);
		
		//materiau de objet
		JPanel materiau = new JPanel();
		materiau.setBorder(new LineBorder(Color.BLACK,1));
		materiau.setBackground(Color.WHITE);
		materiau.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 1));
		JLabel lmateriau = new JLabel("   Materiaus: ",JLabel.LEFT);
		JLabel mimage1 = new JLabel(new ImageIcon("/home/yliu6/1a/TOB/tps/KL-4/src/IG/5.png"));
		JLabel mimage2 = new JLabel(new ImageIcon("/home/yliu6/1a/TOB/tps/KL-4/src/IG/6.png"));
		JLabel mimage3 = new JLabel(new ImageIcon("/home/yliu6/1a/TOB/tps/KL-4/src/IG/7.png"));
		JButton addMateriau = new JButton ("+");
		//mimage1.setBounds(20, 40, 10, 10);
		//mimage2.setBounds(20, 40, 10, 10);
		//mimage3.setBounds(20, 40, 10, 10);
		leftPanel.add(materiau);
		materiau.add(lmateriau);
		materiau.add(mimage1);
		materiau.add(mimage2);
		materiau.add(mimage3);
		materiau.add(addMateriau);
		
		//parametre de materiaus
		JPanel materiaus = new JPanel();
		materiaus.setBorder(new LineBorder(Color.BLACK,1));
		materiaus.setBackground(Color.WHITE);
		materiaus.setLayout(new GridLayout(1,1,0,0));
		JLabel lmateriaus = new JLabel("     Parametre materiaus: ",JLabel.CENTER);
		leftPanel.add(materiaus);
		materiaus.add(lmateriaus);
		
		//transparence de materiaus
		JPanel transparence = new JPanel();
		transparence.setBorder(new LineBorder(Color.BLACK,1));
		transparence.setBackground(Color.WHITE);
		transparence.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel ltransparence = new JLabel("    Transparence: ",JLabel.LEFT);
		JTextField trans= new JTextField();
		trans.setPreferredSize(new Dimension(100, 40));
		leftPanel.add(transparence);
		transparence.add(ltransparence);
		transparence.add(trans);
		
		//opacite de materiaus
		JPanel opacite = new JPanel();
		opacite.setBorder(new LineBorder(Color.BLACK,1));
		opacite.setBackground(Color.WHITE);
		opacite.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lopacite = new JLabel("    Opacite:           ",JLabel.LEFT);
		JTextField opa= new JTextField();
		opa.setPreferredSize(new Dimension(100, 40));
		leftPanel.add(opacite);
		opacite.add(lopacite);
		opacite.add(opa); 
		
		// couleur
		JPanel couleur = new JPanel();
		couleur.setBorder(new LineBorder(Color.BLACK,1));
		couleur.setBackground(Color.WHITE);
		couleur.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lcouleur = new JLabel("    Couleur:          ",JLabel.LEFT);
		JTextField color= new JTextField();
		color.setPreferredSize(new Dimension(100, 40));
		leftPanel.add(couleur);
		couleur.add(lcouleur);
		couleur.add(color);
		
		// trois buttons
		JPanel buttons = new JPanel();
		buttons.setBackground(Color.WHITE);
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		bottomPanel.add(buttons);
		buttons.add(enregistrer);
		buttons.add(annuler);
		buttons.add(ajouter);
		
		ActionEnregistrer listener = new ActionEnregistrer();
		enregistrer.addActionListener(listener);
		
		ActionAnnuler listener2 = new ActionAnnuler();
		annuler.addActionListener(listener2);
		
		ActionAjouter listener3 = new ActionAjouter();
		ajouter.addActionListener(listener3);
		
		ActionAddobjet listener4 = new ActionAddobjet();
		addObjet.addActionListener(listener4);
		
		ActionAddmateriau listener5 = new ActionAddmateriau();
		addMateriau.addActionListener(listener5);
		
    }	
	private class ActionEnregistrer implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
		}
	}
	
	private class ActionAnnuler implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
		}
	}
	
	private class ActionAjouter implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
		}
	}
	
	private class ActionAddobjet implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
		}
	}
	
	private class ActionAddmateriau implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
		}
	}
		
	public static void main(String[] args) {
		FenetreObjet fenetreobjet = new FenetreObjet("Objet", rayTracing);
		fenetreobjet.setVisible(true);
	}	
}