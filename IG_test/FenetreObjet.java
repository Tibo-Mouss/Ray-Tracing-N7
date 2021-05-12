package IG_test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import rayTracing.LumierePonctuelle;
import rayTracing.RayTracing;
import utilitaire.Point;

public class FenetreObjet extends JFrame {

	private JPanel contentPane;
	private RayTracing raytracing;
	private ListeObjets listeO;
	private JList<Objet> grilleO;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreObjet frame = new FenetreObjet(null,null,null);
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
	public FenetreObjet(RayTracing rt, ListeObjets o, JList<Objet> g) {		
		this.raytracing = rt; 
		this.listeO = o;
		this.grilleO = g;	
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1120, 733);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public class ActionAjouterLumiere implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
		    //Object[] options = {"Oui", "Non"};
				//try {
				//raytracing.getScene().addObjet(objet);
			//} catch (Objet3DHorsSceneException e) {
		    	//int n = JOptionPane.showOptionDialog(new JFrame(),
		    		//"Attention ! L'objet que vous voulez ajouter est hors scène.\n Voulez-vous toujours l'ajouter ? ",
		    	    //"Lumière hors scène  ",
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
