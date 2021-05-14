package IG_test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import IG.ListeLumieres;
import IG.Lumieres;
import exception.LumiereHorsSceneException;
import rayTracing.LumierePonctuelle;
import rayTracing.RayTracing;
import utilitaire.Point;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import java.awt.Cursor;
import javax.swing.DropMode;
import java.awt.Point;
import javax.swing.JSpinner;

public class FenetreLumiere extends JFrame {
	
	private RayTracing raytracing;
	private ListeLumieres listeL;
	private JList<Lumieres> grilleL;
	
	private JPanel contentPane;
	
	private JTextField rouge;
	private JTextField vert;
	private JTextField bleu;
	private JSpinner intensite;
	private JTextField x;
	private JTextField y;
	private JTextField z;
	private JTextField vX;
	private JTextField vY;
	private JTextField vZ;
	private JTextField nom;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreLumiere frame = new FenetreLumiere(null,null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public FenetreLumiere(RayTracing rt, ListeLumieres l, JList<Lumieres> g) {		
		super("Ajouter Lumière");
		this.raytracing = rt; 
		this.listeL = l;
		this.grilleL = g;	
		
		this.contentPane = new JPanel();
		
		this.nom = new JTextField();
		
		this.rouge = new JTextField();
		this.vert = new JTextField();
		this.bleu = new JTextField();
		
		this.intensite = new JSpinner();
		
		this.vX = new JTextField();
		this.vY = new JTextField();
		this.vZ = new JTextField();
		
		this.x = new JTextField();
		this.y = new JTextField();
		this.z = new JTextField();
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 200);

		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSetPropriete = new JPanel();
		panelSetPropriete.setBorder(new CompoundBorder());
		contentPane.add(panelSetPropriete, BorderLayout.NORTH);
		panelSetPropriete.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panelCouleur = new JPanel();
		panelCouleur.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCouleur.setPreferredSize(new Dimension(200, 75));
		panelSetPropriete.add(panelCouleur);
		panelCouleur.setLayout(new GridLayout(2, 2, 0, 0));
		
		JTextField txtCouleurParRgb = new JTextField();
		txtCouleurParRgb.setHorizontalAlignment(SwingConstants.CENTER);
		txtCouleurParRgb.setText("Couleur par RGB :");
		txtCouleurParRgb.setToolTipText("");
		txtCouleurParRgb.setEditable(false);
		panelCouleur.add(txtCouleurParRgb);
		txtCouleurParRgb.setColumns(10);
		
		JPanel panelSetCouleur = new JPanel();
		panelCouleur.add(panelSetCouleur);
		panelSetCouleur.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panelRouge = new JPanel();
		panelSetCouleur.add(panelRouge);
		panelRouge.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextField txtRouge = new JTextField();
		txtRouge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rouge.grabFocus();
			}
		});
		txtRouge.setHorizontalAlignment(SwingConstants.CENTER);
		txtRouge.setText("Rouge");
		txtRouge.setEditable(false);
		panelRouge.add(txtRouge);
		txtRouge.setColumns(10);
		
		
		rouge.setToolTipText("valeur comprise entre 1 et 255");
		panelRouge.add(rouge);
		rouge.setColumns(10);
		
		JPanel panelVert = new JPanel();
		panelSetCouleur.add(panelVert);
		panelVert.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextField txtVert = new JTextField();
		txtVert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vert.grabFocus();
			}
		});
		txtVert.setHorizontalAlignment(SwingConstants.CENTER);
		txtVert.setText("Vert");
		txtVert.setEditable(false);
		txtVert.setColumns(10);
		panelVert.add(txtVert);
		

		vert.setToolTipText("valeur comprise entre 1 et 255");
		vert.setColumns(10);
		panelVert.add(vert);
		
		JPanel panelBleu = new JPanel();
		panelSetCouleur.add(panelBleu);
		panelBleu.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextField txtBleue = new JTextField();
		txtBleue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bleu.grabFocus();
			}
		});
		txtBleue.setHorizontalAlignment(SwingConstants.CENTER);
		txtBleue.setText("Bleue");
		txtBleue.setEditable(false);
		txtBleue.setColumns(10);
		panelBleu.add(txtBleue);
		
		bleu.setToolTipText("valeur comprise entre 1 et 255");
		bleu.setColumns(10);
		panelBleu.add(bleu);
		
		JPanel panelIntensite = new JPanel();
		panelIntensite.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSetPropriete.add(panelIntensite);
		panelIntensite.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel pIntensite = new JPanel();
		pIntensite.setToolTipText("");
		panelIntensite.add(pIntensite);
		pIntensite.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextField txtIntensit = new JTextField();
		txtIntensit.setToolTipText("");
		txtIntensit.setText("Intensit\u00E9:");
		txtIntensit.setHorizontalAlignment(SwingConstants.CENTER);
		txtIntensit.setEditable(false);
		txtIntensit.setColumns(10);
		pIntensite.add(txtIntensit);
		
		JPanel panelSetIntensite = new JPanel();
		panelSetIntensite.setBorder(new CompoundBorder());
		panelIntensite.add(panelSetIntensite);

		intensite.setToolTipText("Intensit\u00E9 que vous vouliez choisir");
		panelSetIntensite.add(intensite);
		
		JPanel panelPosition = new JPanel();
		panelPosition.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSetPropriete.add(panelPosition);
		panelPosition.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pPosition = new JPanel();
		pPosition.setPreferredSize(new Dimension(200, 75));
		pPosition.setBorder(new CompoundBorder());
		panelPosition.add(pPosition);
		pPosition.setLayout(new GridLayout(2, 2, 0, 0));
		
		JTextField txtPosition = new JTextField();
		txtPosition.setToolTipText("");
		txtPosition.setText("Position :");
		txtPosition.setHorizontalAlignment(SwingConstants.CENTER);
		txtPosition.setEditable(false);
		txtPosition.setColumns(10);
		pPosition.add(txtPosition);
		
		JPanel panelSetPosition = new JPanel();
		pPosition.add(panelSetPosition);
		panelSetPosition.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel pX = new JPanel();
		panelSetPosition.add(pX);
		pX.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextField positionX = new JTextField();
		positionX.setText("X :");
		positionX.setHorizontalAlignment(SwingConstants.CENTER);
		positionX.setEditable(false);
		positionX.setColumns(10);
		pX.add(positionX);
		
		x.setColumns(10);
		pX.add(x);
		
		JPanel pY = new JPanel();
		panelSetPosition.add(pY);
		pY.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextField positionY = new JTextField();
		positionY.setText("Y :");
		positionY.setHorizontalAlignment(SwingConstants.CENTER);
		positionY.setEditable(false);
		positionY.setColumns(10);
		pY.add(positionY);
		
		y.setColumns(10);
		pY.add(y);
		
		JPanel pZ = new JPanel();
		panelSetPosition.add(pZ);
		pZ.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextField positionZ = new JTextField();
		positionZ.setText("Z :");
		positionZ.setHorizontalAlignment(SwingConstants.CENTER);
		positionZ.setEditable(false);
		positionZ.setColumns(10);
		pZ.add(positionZ);
		

		z.setColumns(10);
		pZ.add(z);
		
		JPanel panelDirection = new JPanel();
		panelSetPropriete.add(panelDirection);
		panelDirection.setPreferredSize(new Dimension(200, 75));
		panelDirection.setBorder(new CompoundBorder());
		panelDirection.setLayout(new GridLayout(2, 2, 0, 0));
		
		JTextField txtDirection = new JTextField();
		txtDirection.setToolTipText("");
		txtDirection.setText("Direction :");
		txtDirection.setHorizontalAlignment(SwingConstants.CENTER);
		txtDirection.setEditable(false);
		txtDirection.setColumns(10);
		panelDirection.add(txtDirection);
		
		JPanel panelSetDirection = new JPanel();
		panelDirection.add(panelSetDirection);
		panelSetDirection.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel pvX = new JPanel();
		panelSetDirection.add(pvX);
		pvX.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextField directionX = new JTextField();
		directionX.setText("Vx :");
		directionX.setHorizontalAlignment(SwingConstants.CENTER);
		directionX.setEditable(false);
		directionX.setColumns(10);
		pvX.add(directionX);
		

		vX.setColumns(10);
		pvX.add(vX);
		
		JPanel pvY = new JPanel();
		panelSetDirection.add(pvY);
		pvY.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextField directionY = new JTextField();
		directionY.setText("Vy :");
		directionY.setHorizontalAlignment(SwingConstants.CENTER);
		directionY.setEditable(false);
		directionY.setColumns(10);
		pvY.add(directionY);
		
		vY.setColumns(10);
		pvY.add(vY);
		
		JPanel pvZ = new JPanel();
		panelSetDirection.add(pvZ);
		pvZ.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextField directionZ = new JTextField();
		directionZ.setText("Vz :");
		directionZ.setHorizontalAlignment(SwingConstants.CENTER);
		directionZ.setEditable(false);
		directionZ.setColumns(10);
		pvZ.add(directionZ);
		
		vZ.setColumns(10);
		pvZ.add(vZ);
		
		JPanel bas = new JPanel();
		bas.setBorder(new CompoundBorder());
		contentPane.add(bas, BorderLayout.CENTER);
		bas.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel pNom = new JPanel();
		pNom.setBorder(new LineBorder(new Color(0, 0, 0)));
		bas.add(pNom);
		pNom.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel renommer = new JLabel("Renommer :");
		pNom.add(renommer);
		renommer.setHorizontalAlignment(SwingConstants.CENTER);
		

		pNom.add(nom);
		nom.setToolTipText("Nouveau Nom");
		nom.setColumns(10);
		
		JPanel pRemplissage = new JPanel();
		pRemplissage.setBorder(new LineBorder(new Color(0, 0, 0)));
		bas.add(pRemplissage);
		pRemplissage.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		pRemplissage.add(panel);
		
		JButton sauvegarderLumiere = new JButton("Sauvegarder lumiere");
		pRemplissage.add(sauvegarderLumiere);
		
		JButton ajouterLumiere = new JButton("ajouter Lumiere");
		ajouterLumiere.addActionListener(new ActionAjouterLumiere());
		bas.add(ajouterLumiere);
	}
	
	public class ActionAjouterLumiere implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
		    LumierePonctuelle lumiere = new LumierePonctuelle(new Point(0,5,10), Color.BLUE, "LumiereTest");
		    //Object[] options = {"Oui", "Non"};
				//try {
				//raytracing.getScene().addLumiere(lumiere);
			//} catch (LumiereHorsSceneException e) {
		    	//int n = JOptionPane.showOptionDialog(new JFrame(),
		    		//"Attention ! La lumiere est hors scï¿½ne.\n Voulez-vous toujours l'ajouter ? ",
		    	    //"Lumiï¿½re hors scï¿½ne  ",
		    	    //JOptionPane.WARNING_MESSAGE, 
		    	    //JOptionPane.YES_NO_OPTION,
		    	    //null,    
		    	    //options,  
		    	    //options[0]); 
		    	//if (n == options[0]) {
		    		//listeL.addElement(new Lumieres(lumiere));
		    		//grilleL.updateUI();
		    		//dispose();
		    	//} else {
		    		//int p = raytracing.getScene().getLumiere().size() - 1;
		    		//raytracing.getScene().getLumiere().remove(p);
		    		//dispose();
		    	//}
			//}
		    listeL.addElement(new Lumieres(lumiere));
	    	grilleL.updateUI();
		    dispose();

		}
	}
}
