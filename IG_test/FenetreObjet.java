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
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.Panel;
import java.awt.Font;
import java.awt.TextField;
import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
public class FenetreObjet extends JFrame {

private static final long serialVersionUID = 1L;
	
	private RayTracing rayTracing;
	final private JButton enregistrer = new JButton("Enregistrer");
	final private JButton annuler = new JButton("Annuler");
    private JButton ajouter;
	private ListeObjets listeO;
	private JList<Objet> grilleO;
	private JSpinner dimen;
	private JSpinner x;
	private JSpinner y;
	private JSpinner z;
	private Color mycolor;
	private JColorChooser jcc;
	private JFrame jf;
	private JTextField rouge;
	private JTextField vert;
	private JTextField bleu;
	private JPanel couleurChoisi;
	private TextField nom;
	private String forme[] = {"Spère", "Cube","Cone"};
	private JComboBox formeO = new JComboBox(forme);
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public FenetreObjet (RayTracing rayTracing,ListeObjets oL,JList<Objet> L) {
		super("Ajouter Objet");
		setAutoRequestFocus(false);
		this.rayTracing = rayTracing;
	    this.listeO = oL; 
	    this.grilleO = L;
	    
	    this.x= new JSpinner();
		this.y= new JSpinner();
		this.z= new JSpinner();
		
		this.rouge = new JTextField("0");
		this.vert = new JTextField("0");
		this.bleu = new JTextField("0");
		
		this.couleurChoisi = new JPanel();		
	    initialise();
	}
	private void initialise() {
		jf = new JFrame("objet");
		this.setSize(758, 554);
	    this.setLocation(100, 100);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    JMenuBar menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    JMenu menu = new JMenu("Fichier");
	    menu.setBackground(new Color(30, 144, 255));
	    menuBar.add(menu);
	    
	    JMenuItem mntmNewMenuItem = new JMenuItem("Enregistrer objet");
	    mntmNewMenuItem.setBackground(new Color(30, 144, 255));
	    menu.add(mntmNewMenuItem);
	    
	    JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ouvrir");
	    mntmNewMenuItem_1.setBackground(new Color(30, 144, 255));
	    menu.add(mntmNewMenuItem_1);
	    
	    JMenuItem mntmNewMenuItem_2 = new JMenuItem("Aide");
	    mntmNewMenuItem_2.setBackground(new Color(30, 144, 255));
	    menuBar.add(mntmNewMenuItem_2);
    
	    
	    Container maincontainer = this.getContentPane();
		maincontainer.setBackground(new Color(135, 206, 250));
		getContentPane().setLayout(null);
		
		
		this.ajouter = new JButton("Ajouter");
		ajouter.addActionListener(new ActionAjouterObjet());
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(17, 437, 709, 40);
		bottomPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		bottomPanel.setBackground(new Color(0, 0, 255));
		bottomPanel.setLayout(new BorderLayout());
		maincontainer.add(bottomPanel);
		
		JPanel basGauche = new JPanel();
		basGauche.setBackground(new Color(0, 0, 255));
		bottomPanel.add(basGauche, BorderLayout.WEST);
		annuler.setBackground(new Color(30, 144, 255));
		basGauche.add(annuler);
		
		JPanel basDroit = new JPanel();
		basDroit.setBackground(new Color(0, 0, 255));
		bottomPanel.add(basDroit, BorderLayout.EAST);
		enregistrer.setBackground(new Color(30, 144, 255));
		basDroit.add(enregistrer);
		enregistrer.setHorizontalAlignment(SwingConstants.RIGHT);
		
				ajouter.setBackground(new Color(30, 144, 255));
				basDroit.add(ajouter);
				
						enregistrer.addActionListener(new ActionEnregistrer());
						annuler.addActionListener(new ActionAnnuler());
		
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(17, 11, 355, 430);
		leftPanel.setBorder(new LineBorder(Color.BLACK,1));
		leftPanel.setBackground(new Color(0, 0, 255));
		maincontainer.add(leftPanel);
		leftPanel.setLayout(null);
	    jcc = new JColorChooser();
		
		
		//parametre de objet
		JPanel objet = new JPanel();
		objet.setBounds(10, 11, 332, 216);
		leftPanel.add(objet);
		objet.setBorder(new LineBorder(new Color(0, 255, 255), 1, true));
		objet.setBackground(new Color(0, 191, 255));
		objet.setLayout(null);
		
		// couleur
		JPanel couleur = new JPanel();
		couleur.setBounds(10, 120, 312, 85);
		objet.add(couleur);
		couleur.setBorder(new LineBorder(Color.BLACK,1));
		couleur.setBackground(Color.WHITE);
		couleur.setLayout(null);
		//JTextField color= new JTextField();
		//color.setPreferredSize(new Dimension(100, 40));
		JButton color = new JButton("Choisir couleur");
		color.setBackground(new Color(30, 144, 255));
		color.setBounds(138, 51, 153, 23);
		couleur.add(color);
		JLabel lcouleur = new JLabel("Couleur:",JLabel.LEFT);
		lcouleur.setBounds(10, 20, 67, 14);
		couleur.add(lcouleur);
		
		
		rouge.setBounds(128, 15, 48, 25);
		couleur.add(rouge);
		rouge.setColumns(10);	
	
		vert.setColumns(10);
		vert.setBounds(186, 15, 48, 25);
		couleur.add(vert);
	
		bleu.setColumns(10);
		bleu.setBounds(243, 15, 48, 25);
		couleur.add(bleu);
		
		couleurChoisi.setBorder(new LineBorder(new Color(0, 0, 0)));
		couleurChoisi.addKeyListener(new ColorUpdater(rouge,bleu,vert,couleurChoisi));
		couleurChoisi.setBounds(86, 15, 25, 25);
		couleur.add(couleurChoisi);
		couleurChoisi.setLayout(null);
		
		JLabel labelRouge = new JLabel("Rouge");
		labelRouge.setHorizontalAlignment(SwingConstants.CENTER);
		labelRouge.setBounds(127, 0, 49, 14);
		couleur.add(labelRouge);
		labelRouge.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		JLabel labelVert = new JLabel("Vert");
		labelVert.setHorizontalAlignment(SwingConstants.CENTER);
		labelVert.setFont(new Font("Tahoma", Font.PLAIN, 9));
		labelVert.setBounds(185, 0, 49, 14);
		couleur.add(labelVert);
		
		JLabel labelBleu = new JLabel("Bleu");
		labelBleu.setHorizontalAlignment(SwingConstants.CENTER);
		labelBleu.setFont(new Font("Tahoma", Font.PLAIN, 9));
		labelBleu.setBounds(242, 0, 49, 14);
		couleur.add(labelBleu);
		
		
		formeO.setEditable(true);
		formeO.setToolTipText("Formes");
		formeO.setBounds(10, 70, 312, 39);
		objet.add(formeO);
		this.nom = new TextField();
		nom.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		
		//nom de objet
		JPanel name = new JPanel();
		name.setBounds(10, 23, 314, 36);
		objet.add(name);
		name.setBorder(new LineBorder(Color.BLACK,1));
		name.setBackground(Color.WHITE);
		name.setLayout(null);
		JLabel lname = new JLabel("Nom: ",JLabel.LEFT);
		lname.setBounds(10, 6, 83, 20);
		name.add(lname);
		
		nom.setText("nouveauNom");
		nom.setBounds(119, 6, 100, 25);
		name.add(nom);
		
		//parametre de materiaus
		JPanel materiaus = new JPanel();
		materiaus.setBounds(7, 238, 335, 181);
		leftPanel.add(materiaus);
		materiaus.setBorder(new LineBorder(new Color(0, 255, 255)));
		materiaus.setBackground(new Color(0, 191, 255));
		materiaus.setLayout(null);
		
		
		//Reflectivite de materiaus
		JPanel reflectivite = new JPanel();
		reflectivite.setBounds(10, 43, 315, 51);
		materiaus.add(reflectivite);
		reflectivite.setBorder(new LineBorder(Color.BLACK,1));
		reflectivite.setBackground(Color.WHITE);
		reflectivite.setLayout(null);
		
		JSpinner intensiteRefl = new JSpinner();
		intensiteRefl.setModel(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.01));
		intensiteRefl.setBounds(81, 14, 50, 25);
		reflectivite.add(intensiteRefl);
		
		JSpinner energieRefl = new JSpinner();
		energieRefl.setModel(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.01));
		energieRefl.setBounds(148, 15, 50, 25);
		reflectivite.add(energieRefl);
		
		JLabel labelIntensiteRF = new JLabel("Intensite ");
		labelIntensiteRF.setHorizontalAlignment(SwingConstants.CENTER);
		labelIntensiteRF.setFont(new Font("Tahoma", Font.PLAIN, 9));
		labelIntensiteRF.setBounds(82, 0, 49, 14);
		reflectivite.add(labelIntensiteRF);
		
		JLabel labelEnergieRF = new JLabel("Energie ");
		labelEnergieRF.setHorizontalAlignment(SwingConstants.CENTER);
		labelEnergieRF.setFont(new Font("Tahoma", Font.PLAIN, 9));
		labelEnergieRF.setBounds(149, 0, 49, 14);
		reflectivite.add(labelEnergieRF);
		
		JCheckBox activeRefr = new JCheckBox("On");
		activeRefr.setBounds(6, 14, 46, 25);
		reflectivite.add(activeRefr);
		JLabel lobjet = new JLabel("Propri\u00E9t\u00E9 du Mat\u00E9riaux ",JLabel.CENTER);
		lobjet.setBounds(10, 11, 315, 27);
		materiaus.add(lobjet);
		
		JPanel reflectivite_1 = new JPanel();
		reflectivite_1.setLayout(null);
		reflectivite_1.setBorder(new LineBorder(Color.BLACK,1));
		reflectivite_1.setBackground(Color.WHITE);
		reflectivite_1.setBounds(10, 119, 315, 51);
		materiaus.add(reflectivite_1);
		
		JSpinner intensiteRefr = new JSpinner();
		intensiteRefr.setModel(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.01));
		intensiteRefr.setBounds(82, 14, 50, 25);
		reflectivite_1.add(intensiteRefr);
		
		JLabel labelIntensiteRR = new JLabel("Intensite ");
		labelIntensiteRR.setHorizontalAlignment(SwingConstants.CENTER);
		labelIntensiteRR.setFont(new Font("Tahoma", Font.PLAIN, 9));
		labelIntensiteRR.setBounds(82, 0, 49, 14);
		reflectivite_1.add(labelIntensiteRR);
		
		JSpinner energieRefra = new JSpinner();
		energieRefra.setModel(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.01));
		energieRefra.setBounds(148, 14, 50, 25);
		reflectivite_1.add(energieRefra);
		
		JLabel labelEnergieRR = new JLabel("Energie ");
		labelEnergieRR.setHorizontalAlignment(SwingConstants.CENTER);
		labelEnergieRR.setFont(new Font("Tahoma", Font.PLAIN, 9));
		labelEnergieRR.setBounds(149, 0, 49, 14);
		reflectivite_1.add(labelEnergieRR);
		
		JSpinner indiceRR = new JSpinner();
		indiceRR.setModel(new SpinnerNumberModel(1.0, 1.0, 100, 0.01));
		indiceRR.setBounds(234, 14, 50, 25);
		reflectivite_1.add(indiceRR);
		
		JCheckBox activeRefl = new JCheckBox("On");
		activeRefl.setBounds(6, 14, 46, 25);
		reflectivite_1.add(activeRefl);
		
		JLabel labelIndiceMilieuRR = new JLabel("Indice de r\u00E9fraction");
		labelIndiceMilieuRR.setHorizontalAlignment(SwingConstants.CENTER);
		labelIndiceMilieuRR.setFont(new Font("Tahoma", Font.PLAIN, 9));
		labelIndiceMilieuRR.setBounds(214, 0, 91, 14);
		reflectivite_1.add(labelIndiceMilieuRR);
		JLabel lreflectivite = new JLabel("Reflectivite:",JLabel.LEFT);
		lreflectivite.setBounds(10, 29, 81, 14);
		materiaus.add(lreflectivite);
		
		JLabel lrefraction = new JLabel("R\u00E9fraction:", SwingConstants.LEFT);
		lrefraction.setBounds(10, 105, 81, 14);
		materiaus.add(lrefraction);
		color.addActionListener(new ActionCouleur());
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		panel.setBounds(371, 301, 355, 140);
		getContentPane().add(panel);
		panel.setLayout(null);
				
				this.dimen = new JSpinner();
				
				JPanel parametreBase = new JPanel();
				parametreBase.setBounds(10, 17, 332, 102);
				panel.add(parametreBase);
				parametreBase.setBorder(new LineBorder(new Color(0, 255, 255), 1, true));
				parametreBase.setBackground(new Color(0, 191, 255));
				parametreBase.setLayout(null);
				
				dimen.setBackground(new Color(30, 144, 255));
				dimen.setBounds(120, 6, 100, 25);
				
				//dimension
				JPanel dimension = new JPanel();
				dimension.setBounds(10, 11, 314, 36);
				parametreBase.add(dimension);
				dimension.setBorder(new LineBorder(Color.BLACK,1));
				dimension.setBackground(Color.WHITE);
				JLabel dimention = new JLabel("Dimension:         ");
				dimention.setBounds(11, 13, 99, 14);
				dimension.setLayout(null);
				dimension.add(dimention);
				dimention.setHorizontalAlignment(JLabel.LEFT);
				//JTextField dimen = new JTextField();
       
				dimen.setPreferredSize(new Dimension(100, 40));	
				dimension.add(dimen);
				this.x = new JSpinner();
				x.setModel(new SpinnerNumberModel(0.0, null, null, 1.0));
				x.setBackground(new Color(30, 144, 255));
				x.setBounds(99, 6, 44, 27);
				this.y = new JSpinner();
				y.setModel(new SpinnerNumberModel(0.0, null, null, 1.0));
				y.setBackground(new Color(30, 144, 255));
				y.setBounds(181, 4, 42, 30);
				this.z = new JSpinner();
				z.setModel(new SpinnerNumberModel(0.0, null, null, 1.0));
				z.setBackground(new Color(30, 144, 255));
				z.setBounds(260, 4, 42, 30);
				
				
				//position
				JPanel position = new JPanel();
				position.setBounds(10, 58, 314, 36);
				parametreBase.add(position);
				position.setBorder(new LineBorder(Color.BLACK,1));
				position.setBackground(Color.WHITE);
				JLabel lposition = new JLabel("Position:   ");
				lposition.setBounds(10, 12, 66, 14);
				JLabel xnom = new JLabel("x");
				xnom.setHorizontalAlignment(SwingConstants.RIGHT);
				xnom.setBounds(61, 7, 28, 24);
				JLabel ynom = new JLabel("y");
				ynom.setHorizontalAlignment(SwingConstants.RIGHT);
				ynom.setBounds(153, 12, 17, 14);
				JLabel znom = new JLabel("z");
				znom.setHorizontalAlignment(SwingConstants.RIGHT);
				znom.setBounds(233, 12, 17, 14);
				position.setLayout(null);
				position.add(lposition);
				
						x.setPreferredSize(new Dimension(30, 30));	
						y.setPreferredSize(new Dimension(30, 30));	
						z.setPreferredSize(new Dimension(30, 30));
						position.add(xnom);
						position.add(x);
						position.add(ynom);
						position.add(y);
						position.add(znom);
						position.add(z);
						
						JPanel panel_1 = new JPanel();
						panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
						panel_1.setBackground(Color.WHITE);
						panel_1.setBounds(371, 11, 355, 290);
						getContentPane().add(panel_1);
		
		
    }	
	public FenetreObjet(String nom, RayTracing rayTracing,ListeObjets oL,JList<Objet> L, Objet o) {
		super(nom);
		this.rayTracing = rayTracing;
	    this.listeO=oL;
	    this.grilleO=L;
	    this.dimen = new JSpinner();
	    
	    this.x = new JSpinner();
	    this.y = new JSpinner();
	    this.z = new JSpinner();
	    
		this.rouge = new JTextField();
		this.vert = new JTextField();
		this.bleu = new JTextField();
		
		this.couleurChoisi = new JPanel();

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
			dispose();
		}
	}
	
	private class ActionModifierObjet implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
		}
	}
	
	private class ColorUpdater extends KeyAdapter {
		JTextField red;
		JTextField blue;
		JTextField green;
		JPanel couleur;
		
		public ColorUpdater(JTextField r,JTextField b,JTextField g, JPanel c) {
			super();
			red = r;
			blue = b;
			green = g;
			couleur = c;
		}
		public void keyReleased(KeyEvent e) {
			String r = red.getText();
			String g = blue.getText();
			String b = green.getText();
		    mycolor = new Color(Integer.parseInt(r), Integer.parseInt(g),Integer.parseInt(b));
		    couleur.setBackground(mycolor);
			
		}
	}
	
	private class ActionCouleur implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mycolor = JColorChooser.showDialog(jf,"Swing color chooser", null);
			if (mycolor != null) {
				int r = mycolor.getRed();
				int g = mycolor.getGreen();
				int b = mycolor.getBlue();
				rouge.setText(String.valueOf(r));
				vert.setText(String.valueOf(g));
				bleu.setText(String.valueOf(b));
				couleurChoisi.setBackground(mycolor);
			}
			
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
		    		//"Attention ! L'objet que vous voulez ajouter est hors scï¿½ne.\n Voulez-vous toujours l'ajouter ? ",
		    	    //"Lumiï¿½re hors scï¿½ne  ",
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
