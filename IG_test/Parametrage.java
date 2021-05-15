package IG_test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rayTracing.RayTracing;

import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;

public class Parametrage extends JFrame{

	private final JPanel contentPanel = new JPanel();
	private RayTracing rt;
	private JSpinner nbRebond;
	private JCheckBox activeOmbres;
	private JCheckBox activerMthodeShadding;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Parametrage dialog = new Parametrage(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Parametrage(RayTracing nrt) {
		this.rt = nrt;
		setBounds(100, 100, 372, 179);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		nbRebond = new JSpinner();
		nbRebond.setModel(new SpinnerNumberModel(0, 0, null, 1));
		nbRebond.setToolTipText("Nombre de rebonds");
		nbRebond.setValue(nrt.getRebond());
		nbRebond.addChangeListener(new setRebond());
		nbRebond.setBounds(209, 8, 85, 20);
		contentPanel.add(nbRebond);
		
		JLabel nombreRebond = new JLabel("Nombre de rebonds ");
		nombreRebond.setHorizontalAlignment(SwingConstants.LEFT);
		nombreRebond.setBackground(new Color(240, 240, 240));
		nombreRebond.setEnabled(true);
		nombreRebond.setBounds(61, 10, 160, 17);
		contentPanel.add(nombreRebond);
		
		activeOmbres = new JCheckBox("Activer Ombres");
		activeOmbres.setSelected(rt.getOmbre());
		activeOmbres.addActionListener(new ActiverOmbre());
		activeOmbres.setMnemonic('O');
		activeOmbres.setBounds(52, 34, 200, 23);
		contentPanel.add(activeOmbres);
		
		activerMthodeShadding = new JCheckBox("Activer M\u00E9thode Shadding");
		activerMthodeShadding.setSelected(rt.getShadding());
		activerMthodeShadding.addActionListener(new ActiverShadding());
		activerMthodeShadding.setMnemonic('S');
		activerMthodeShadding.setBounds(52, 60, 200, 23);
		contentPanel.add(activerMthodeShadding);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionOk());
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
	}
	
	public class setRebond implements ChangeListener {
		
		public void stateChanged(ChangeEvent event) {
			JSpinner valeur = (JSpinner) event.getSource();
			rt.setRebond((int)valeur.getValue());
		}
	}
	
	
	
	public class ActiverOmbre implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			boolean state = activeOmbres.isSelected();
			rt.setOmbre(state);
		}
	}
	
	public class ActiverShadding implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			boolean state = activerMthodeShadding.isSelected();
			rt.setShadding(state);
		}
	}
	
	public class ActionOk implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			dispose();
		}
	}
}
