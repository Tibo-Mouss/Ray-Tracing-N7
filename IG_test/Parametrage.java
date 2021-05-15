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
import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class Parametrage extends JFrame{

	private final JPanel contentPanel = new JPanel();
	private JSpinner nbRebond;
	private JCheckBox activeOmbres;
	private JCheckBox activerMthodeShadding;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Parametrage dialog = new Parametrage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Parametrage() {
		setBounds(100, 100, 372, 179);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		nbRebond = new JSpinner();
		nbRebond.setToolTipText("Nombre de rebonds");
		nbRebond.setBounds(222, 8, 30, 20);
		contentPanel.add(nbRebond);
		
		JLabel nombreRebond = new JLabel("Nombre de rebonds ");
		nombreRebond.setHorizontalAlignment(SwingConstants.LEFT);
		nombreRebond.setBackground(new Color(240, 240, 240));
		nombreRebond.setEnabled(true);
		nombreRebond.setBounds(52, 10, 160, 17);
		contentPanel.add(nombreRebond);
		
		activeOmbres = new JCheckBox("Activer Ombres");
		activeOmbres.setSelected(true);
		activeOmbres.setMnemonic('O');
		activeOmbres.setBounds(52, 34, 200, 23);
		contentPanel.add(activeOmbres);
		
		activerMthodeShadding = new JCheckBox("Activer M\u00E9thode Shadding");
		activerMthodeShadding.setMnemonic('O');
		activerMthodeShadding.setBounds(52, 60, 200, 23);
		contentPanel.add(activerMthodeShadding);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionModifier());
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Fermer");
		cancelButton.addActionListener(new ActionQuitter());
		buttonPane.add(cancelButton);
		
	}
	public JSpinner getNbRebond() {
		return nbRebond;
	}
	public JCheckBox getActiveOmbres() {
		return activeOmbres;
	}
	public JCheckBox getActiverMthodeShadding() {
		return activerMthodeShadding;
	}
	
	public class ActionQuitter implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			dispose();
		}
	}
	
	public class ActionModifier implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			dispose();
		}
	}
}
