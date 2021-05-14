package IG_test;

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
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import rayTracing.RayTracing;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
public class FenetreObjet extends JFrame {

private static final long serialVersionUID = 1L;
	
	private RayTracing rayTracing;
	final private JButton enregistrer = new JButton("Enregistrer");
	final private JButton annuler = new JButton("Annuler");
    private JButton ajouter;
	private ListeObjets listeO;
	private JList<Objet> grilleO;
	private JTextField dimen;
	private JTextField x;
	private JTextField y;
	private JTextField z;
	private JTextField ref;
	private Color mycolor;
	private JColorChooser jcc;
	private JFrame jf;
	
	
	public FenetreObjet (RayTracing rayTracing,ListeObjets oL,JList<Objet> L) {
		super("Ajouter Objet");
		this.rayTracing = rayTracing;
	    this.listeO = oL;
	    this.grilleO = L;
	    this.dimen = new JTextField();
	    this.x = new JTextField();
	    this.y = new JTextField();
	    this.z = new JTextField();
	    this.ref = new JTextField(); 
	    this.ajouter = new JButton("Ajouter");   
	    ajouter.addActionListener(new ActionAjouterObjet());
	    initialise();
	}
	private void initialise() {
		jf = new JFrame("objet");
		this.setSize(1000, 1000);
	    this.setLocation(100, 100);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    JMenuBar menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    JMenu menu = new JMenu("Fichier");
	    menuBar.add(menu);
	    
	    JMenuItem mntmNewMenuItem = new JMenuItem("Enregistrer objet");
	    menu.add(mntmNewMenuItem);
	    
	    JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ouvrir");
	    menu.add(mntmNewMenuItem_1);
	    
	    JMenuItem mntmNewMenuItem_2 = new JMenuItem("Aide");
	    menuBar.add(mntmNewMenuItem_2);
    
	    
	    Container maincontainer = this.getContentPane();
		maincontainer.setLayout(new BorderLayout(0,0));
		maincontainer.setBackground(Color.WHITE);
		
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new LineBorder(Color.BLACK,1));
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setLayout(new GridLayout(9,1,0,0));
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
		//JTextField dimen = new JTextField();
       
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
		//JTextField x= new JTextField();
		//JTextField y= new JTextField();
		//JTextField z= new JTextField();
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
		objet.setLayout(new GridLayout(1,1,0,0));
		JLabel lobjet = new JLabel("    Parametre de Objet: ",JLabel.CENTER);
		leftPanel.add(objet);
		objet.add(lobjet);
		
		 
		//forme de objet
		JPanel forme = new JPanel();
		forme.setBorder(new LineBorder(Color.BLACK,1));
		forme.setBackground(Color.WHITE);
		forme.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 1));
		JLabel lforme = new JLabel("    Forme: ",JLabel.LEFT);
		JLabel image1 = new JLabel(new ImageIcon("IG_test/1.png"));
		JLabel image2 = new JLabel(new ImageIcon("IG_test/2.png"));
		JLabel image3 = new JLabel(new ImageIcon("IG_test/3.png"));
		JLabel image4 = new JLabel(new ImageIcon("IG_test/4.png"));
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
		//JLabel mimage1 = new JLabel(new ImageIcon("/home/yliu6/1a/TOB/tps/KL-4/src/IG/5.png"));
		//JLabel mimage2 = new JLabel(new ImageIcon("/home/yliu6/1a/TOB/tps/KL-4/src/IG/6.png"));
		//JLabel mimage3 = new JLabel(new ImageIcon("/home/yliu6/1a/TOB/tps/KL-4/src/IG/7.png"));
		JButton addMateriau = new JButton ("+");
		//mimage1.setBounds(20, 40, 10, 10);
		//mimage2.setBounds(20, 40, 10, 10);
		//mimage3.setBounds(20, 40, 10, 10);
		leftPanel.add(materiau);
		materiau.add(lmateriau);
		//materiau.add(mimage1);
		//materiau.add(mimage2);
		//materiau.add(mimage3);
		materiau.add(addMateriau);
		
		//parametre de materiaus
		JPanel materiaus = new JPanel();
		materiaus.setBorder(new LineBorder(Color.BLACK,1));
		materiaus.setBackground(Color.WHITE);
		materiaus.setLayout(new GridLayout(1,1,0,0));
		JLabel lmateriaus = new JLabel("     Parametre materiaus: ",SwingConstants.CENTER);
		leftPanel.add(materiaus);
		materiaus.add(lmateriaus);
		
		
		//Reflectivite de materiaus
		JPanel reflectivite = new JPanel();
		reflectivite.setBorder(new LineBorder(Color.BLACK,1));
		reflectivite.setBackground(Color.WHITE);
		reflectivite.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		JLabel lreflectivite = new JLabel("Reflectivite:",JLabel.LEFT);
		//JTextField ref= new JTextField();
		ref.setPreferredSize(new Dimension(100, 40));
		leftPanel.add(reflectivite);
		reflectivite.add(lreflectivite);
		reflectivite.add(ref); 
		
		// couleur
		JPanel couleur = new JPanel();
		couleur.setBorder(new LineBorder(Color.BLACK,1));
		couleur.setBackground(Color.WHITE);
		couleur.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		JLabel lcouleur = new JLabel("    Couleur:          ",JLabel.LEFT);
		//JTextField color= new JTextField();
		//color.setPreferredSize(new Dimension(100, 40));
		JButton color = new JButton("Choisir couleur");
	    jcc = new JColorChooser();
		
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
				
		ActionAddobjet listener4 = new ActionAddobjet();
		addObjet.addActionListener(listener4);
		
		ActionAddmateriau listener5 = new ActionAddmateriau();
		addMateriau.addActionListener(listener5);
		
		ActionCouleur listener6 = new ActionCouleur();
		color.addActionListener(listener6);
		
    }	
	public FenetreObjet(String nom, RayTracing rayTracing,ListeObjets oL,JList<Objet> L, Objet o) {
		super(nom);
		this.rayTracing = rayTracing;
	    this.listeO=oL;
	    this.grilleO=L;
	    this.dimen = new JTextField("25");
	    this.x = new JTextField("255");
	    this.y = new JTextField("255");
	    this.z = new JTextField("255");
	    this.ref = new JTextField("1");
	    this.ajouter = new JButton("Modifier");
	    ajouter.addActionListener(new ActionModifierObjet());
	    initialise();
	}
	private class ActionEnregistrer implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
		}
	}
	
	private class ActionAnnuler implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
		}
	}

	private class ActionAddobjet implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
		}
	}
	
	private class ActionModifierObjet implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
		}
	}
	
	private class ActionAddmateriau implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
		}
	}
	
	private class ActionCouleur implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mycolor = JColorChooser.showDialog(jf,"Swing color chooser", null);
		}
	}
	public static void main(String[] args) {
		FenetreObjet fenetreobjet = new FenetreObjet(null, null, null);
		fenetreobjet.setVisible(true);
	}	

	
	public class ActionAjouterObjet implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
		    //Object[] options = {"Oui", "Non"};
				//try {
				//raytracing.getScene().addObjet(objet);
			//} catch (Objet3DHorsSceneException e) {
		    	//int n = JOptionPane.showOptionDialog(new JFrame(),
		    		//"Attention ! L'objet que vous voulez ajouter est hors sc�ne.\n Voulez-vous toujours l'ajouter ? ",
		    	    //"Lumi�re hors sc�ne  ",
		    	    //JOptionPane.WARNING_MESSAGE, 
		    	    //JOptionPane.YES_NO_OPTION,
		    	    //null,    
		    	    //options,  
		    	    //options[0]); 
		    	//if (n == options[0]) {
		    		//listeO.addElement(new Objet(objet));
		    		//grilleO.updateUI();
		    		//dispose();
		    	//} else {
		    		//int p = raytracing.getScene().getObjets().size() - 1;
		    		//raytracing.getScene().getObjets().remove(p);
		    		//dispose();
		    	//}
			//}
		    //listeO.addElement(new Objet(objet));
	    	//grilleO.updateUI();
		    dispose();

		}
	}
}
