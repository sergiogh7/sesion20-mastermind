package Clases;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NivelGUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JRadioButton rdbtnPrincipiante;
	private JRadioButton rdbtnMedio;
	private JRadioButton rdbtnAvanzado;
	private String nivel;


	/**
	 * Create the dialog.
	 */
	public NivelGUI() {
		setTitle("Seleccionar nivel");
		setBounds(200, 100, 250, 290);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(3, 3, 0, 0));
			{
				rdbtnPrincipiante = new JRadioButton("Principiante");
				rdbtnPrincipiante.setSelected(true);
				rdbtnPrincipiante.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(rdbtnPrincipiante);
			}
			{
				rdbtnMedio = new JRadioButton("Medio");
				rdbtnMedio.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(rdbtnMedio);
			}
			{
				rdbtnAvanzado = new JRadioButton("Avanzado");
				rdbtnAvanzado.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(rdbtnAvanzado);
			}
			
			ButtonGroup bgroup = new ButtonGroup();
			bgroup.add(rdbtnPrincipiante);
			bgroup.add(rdbtnMedio);
			bgroup.add(rdbtnAvanzado);
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						
						if (rdbtnMedio.isSelected()) {
							nivel = rdbtnMedio.getText();

						}
						else if(rdbtnAvanzado.isSelected()) {
							nivel = rdbtnAvanzado.getText();

						}else {
							nivel = rdbtnPrincipiante.getText();

						}	
						
						//para cerrar la ventana de nivel,
						dispose();
						setVisible(false);
					}
				});
	
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						nivel = rdbtnPrincipiante.getText();
					
						//para cerrar la ventana de nivel,
						dispose();
						setVisible(false);
					}
				});
	
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	//getter de nivel para traspasarlo a la página principal,
	public String getNivel() {
		return nivel;
	}

}
