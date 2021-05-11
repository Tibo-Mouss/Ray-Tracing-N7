package IG ;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import java.awt.Cursor;
import javax.swing.DropMode;
import java.awt.Point;

public class IG_test_lumiere extends JFrame {

	private JPanel contentPane;
	private JTextField txtCouleurParRgb;
	private JTextField textField;
	private JTextField txtRouge;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField txtBleue;
	private JTextField txtIntensit;
	private JTextField textField_1;
	private JTextField txtPosition;
	private JTextField txtX;
	private JTextField textField_6;
	private JTextField txtY;
	private JTextField textField_8;
	private JTextField txtZ;
	private JTextField textField_10;
	private JTextField txtDirection;
	private JTextField txtVx;
	private JTextField textField_7;
	private JTextField txtVy;
	private JTextField textField_11;
	private JTextField txtVz;
	private JTextField textField_13;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private JPanel panel_16;
	private JLabel lblNewLabel;
	private JPanel panel_17;
	private JPanel panel_18;
	private JTextField textField_3;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IG_test_lumiere frame = new IG_test_lumiere();
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
	public IG_test_lumiere() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder());
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setPreferredSize(new Dimension(200, 75));
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 2, 0, 0));
		
		txtCouleurParRgb = new JTextField();
		txtCouleurParRgb.setHorizontalAlignment(SwingConstants.CENTER);
		txtCouleurParRgb.setText("Couleur par RGB :");
		txtCouleurParRgb.setToolTipText("");
		txtCouleurParRgb.setEditable(false);
		panel_4.add(txtCouleurParRgb);
		txtCouleurParRgb.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtRouge = new JTextField();
		txtRouge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.grabFocus();
			}
		});
		txtRouge.setHorizontalAlignment(SwingConstants.CENTER);
		txtRouge.setText("Rouge");
		txtRouge.setEditable(false);
		panel_6.add(txtRouge);
		txtRouge.setColumns(10);
		
		textField = new JTextField();
		textField.setToolTipText("valeur comprise entre 1 et 255");
		panel_6.add(textField);
		textField.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextField txtVert = new JTextField();
		txtVert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_2.grabFocus();
			}
		});
		txtVert.setHorizontalAlignment(SwingConstants.CENTER);
		txtVert.setText("Vert");
		txtVert.setEditable(false);
		txtVert.setColumns(10);
		panel_7.add(txtVert);
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("valeur comprise entre 1 et 255");
		textField_2.setColumns(10);
		panel_7.add(textField_2);
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtBleue = new JTextField();
		txtBleue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_4.grabFocus();
			}
		});
		txtBleue.setHorizontalAlignment(SwingConstants.CENTER);
		txtBleue.setText("Bleue");
		txtBleue.setEditable(false);
		txtBleue.setColumns(10);
		panel_8.add(txtBleue);
		
		textField_4 = new JTextField();
		textField_4.setToolTipText("valeur comprise entre 1 et 255");
		textField_4.setColumns(10);
		panel_8.add(textField_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_10.setToolTipText("");
		panel_3.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtIntensit = new JTextField();
		txtIntensit.setToolTipText("");
		txtIntensit.setText("Intensit\u00E9:");
		txtIntensit.setHorizontalAlignment(SwingConstants.CENTER);
		txtIntensit.setEditable(false);
		txtIntensit.setColumns(10);
		panel_10.add(txtIntensit);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new CompoundBorder());
		panel_3.add(panel_9);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Intensit\u00E9 que vous vouliez choisir");
		panel_9.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setPreferredSize(new Dimension(200, 75));
		panel_4_1.setBorder(new CompoundBorder());
		panel_2.add(panel_4_1);
		panel_4_1.setLayout(new GridLayout(2, 2, 0, 0));
		
		txtPosition = new JTextField();
		txtPosition.setToolTipText("");
		txtPosition.setText("Position :");
		txtPosition.setHorizontalAlignment(SwingConstants.CENTER);
		txtPosition.setEditable(false);
		txtPosition.setColumns(10);
		panel_4_1.add(txtPosition);
		
		JPanel panel_5_1 = new JPanel();
		panel_4_1.add(panel_5_1);
		panel_5_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_6_1 = new JPanel();
		panel_5_1.add(panel_6_1);
		panel_6_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtX = new JTextField();
		txtX.setText("X :");
		txtX.setHorizontalAlignment(SwingConstants.CENTER);
		txtX.setEditable(false);
		txtX.setColumns(10);
		panel_6_1.add(txtX);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		panel_6_1.add(textField_6);
		
		JPanel panel_7_1 = new JPanel();
		panel_5_1.add(panel_7_1);
		panel_7_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtY = new JTextField();
		txtY.setText("Y :");
		txtY.setHorizontalAlignment(SwingConstants.CENTER);
		txtY.setEditable(false);
		txtY.setColumns(10);
		panel_7_1.add(txtY);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		panel_7_1.add(textField_8);
		
		JPanel panel_8_1 = new JPanel();
		panel_5_1.add(panel_8_1);
		panel_8_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtZ = new JTextField();
		txtZ.setText("Z :");
		txtZ.setHorizontalAlignment(SwingConstants.CENTER);
		txtZ.setEditable(false);
		txtZ.setColumns(10);
		panel_8_1.add(txtZ);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		panel_8_1.add(textField_10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2_1 = new JPanel();
		panel_1.add(panel_2_1);
		panel_2_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setPreferredSize(new Dimension(200, 75));
		panel_4_1_1.setBorder(new CompoundBorder());
		panel_2_1.add(panel_4_1_1);
		panel_4_1_1.setLayout(new GridLayout(2, 2, 0, 0));
		
		txtDirection = new JTextField();
		txtDirection.setToolTipText("");
		txtDirection.setText("direction :");
		txtDirection.setHorizontalAlignment(SwingConstants.CENTER);
		txtDirection.setEditable(false);
		txtDirection.setColumns(10);
		panel_4_1_1.add(txtDirection);
		
		JPanel panel_5_1_1 = new JPanel();
		panel_4_1_1.add(panel_5_1_1);
		panel_5_1_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_6_1_1 = new JPanel();
		panel_5_1_1.add(panel_6_1_1);
		panel_6_1_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtVx = new JTextField();
		txtVx.setText("Vx :");
		txtVx.setHorizontalAlignment(SwingConstants.CENTER);
		txtVx.setEditable(false);
		txtVx.setColumns(10);
		panel_6_1_1.add(txtVx);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		panel_6_1_1.add(textField_7);
		
		JPanel panel_7_1_1 = new JPanel();
		panel_5_1_1.add(panel_7_1_1);
		panel_7_1_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtVy = new JTextField();
		txtVy.setText("Vy :");
		txtVy.setHorizontalAlignment(SwingConstants.CENTER);
		txtVy.setEditable(false);
		txtVy.setColumns(10);
		panel_7_1_1.add(txtVy);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		panel_7_1_1.add(textField_11);
		
		JPanel panel_8_1_1 = new JPanel();
		panel_5_1_1.add(panel_8_1_1);
		panel_8_1_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtVz = new JTextField();
		txtVz.setText("Vz :");
		txtVz.setHorizontalAlignment(SwingConstants.CENTER);
		txtVz.setEditable(false);
		txtVz.setColumns(10);
		panel_8_1_1.add(txtVz);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		panel_8_1_1.add(textField_13);
		
		panel_11 = new JPanel();
		panel_11.setBorder(new CompoundBorder());
		contentPane.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new GridLayout(0, 3, 0, 0));
		
		panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_15 = new JPanel();
		panel_12.add(panel_15);
		panel_15.setLayout(new GridLayout(3, 1, 0, 0));
		
		panel_16 = new JPanel();
		panel_15.add(panel_16);
		
		lblNewLabel = new JLabel("Renommer :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_15.add(lblNewLabel);
		
		panel_17 = new JPanel();
		panel_12.add(panel_17);
		panel_17.setLayout(new GridLayout(3, 3, 20, 0));
		
		panel_18 = new JPanel();
		panel_17.add(panel_18);
		
		textField_3 = new JTextField();
		textField_3.setToolTipText("Nouveau Nom");
		panel_17.add(textField_3);
		textField_3.setColumns(10);
		
		panel_13 = new JPanel();
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.add(panel_13);
		
		panel_14 = new JPanel();
		panel_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.add(panel_14);
		panel_14.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnNewButton = new JButton("ajouter Lumiere");
		panel_14.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Sauvegarder lumiere");
		panel_14.add(btnNewButton_1);
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}
}
