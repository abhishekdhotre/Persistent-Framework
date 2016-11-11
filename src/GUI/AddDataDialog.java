package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.SaveBookController;
import Controller.UndoRedoController;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddDataDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBookName;
	private JTextField txtBookAuthor;
	private JTextField textField;
	private JTextField txtISBN;
	private JTextField txtEdition;
	private JTextField txtFileSize;
	private JTextField txtPrintLength;
	private JTextField txtDimensions;
	SaveBookController sbController= new SaveBookController();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddDataDialog dialog = new AddDataDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param i 
	 * @return 
	 */
	public AddDataDialog() {
		
	}
	
	
	public AddDataDialog(int i, LIS_GUI frame) {
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setBounds(15, 16, 90, 20);
		contentPanel.add(lblBookName);
		
		JLabel lblBookAuthor = new JLabel("Author");
		lblBookAuthor.setBounds(15, 63, 90, 20);
		contentPanel.add(lblBookAuthor);
		
		txtBookName = new JTextField();
		txtBookName.setBounds(177, 13, 303, 26);
		contentPanel.add(txtBookName);
		txtBookName.setColumns(10);
		
		txtBookAuthor = new JTextField();
		txtBookAuthor.setBounds(177, 60, 303, 26);
		contentPanel.add(txtBookAuthor);
		txtBookAuthor.setColumns(10);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setBounds(15, 116, 90, 20);
		contentPanel.add(lblPublisher);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(177, 113, 303, 26);
		contentPanel.add(textField);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(15, 165, 90, 20);
		contentPanel.add(lblIsbn);
		
		txtISBN = new JTextField();
		txtISBN.setColumns(10);
		txtISBN.setBounds(177, 162, 303, 26);
		contentPanel.add(txtISBN);
		
		txtEdition = new JTextField();
		txtEdition.setColumns(10);
		txtEdition.setBounds(177, 260, 303, 26);
		contentPanel.add(txtEdition);
		
		JLabel lblAvailable = new JLabel("Edition");
		lblAvailable.setBounds(15, 263, 90, 20);
		contentPanel.add(lblAvailable);
		
		txtFileSize = new JTextField();
		txtFileSize.setColumns(10);
		txtFileSize.setBounds(177, 307, 303, 26);
		contentPanel.add(txtFileSize);
		
		JLabel lblFileSize = new JLabel();
		if(i==0){
			lblFileSize.setText("Pages"); 
		}
		else{
			lblFileSize.setText("File Size");
		}
		lblFileSize.setBounds(15, 310, 90, 20);
		contentPanel.add(lblFileSize);
		
		JLabel lblPrintLength;
		if (i==0){
			 lblPrintLength = new JLabel("Weight");
		}
		else{
			lblPrintLength = new JLabel("Multiple Device Usage");
		}
		lblPrintLength.setBounds(15, 363, 90, 20);
		contentPanel.add(lblPrintLength);
		
		txtPrintLength = new JTextField();
		txtPrintLength.setColumns(10);
		txtPrintLength.setBounds(177, 360, 303, 26);
		contentPanel.add(txtPrintLength);
		
		txtDimensions = new JTextField();
		txtDimensions.setColumns(10);
		txtDimensions.setBounds(177, 407, 303, 26);
		contentPanel.add(txtDimensions);
		
		JComboBox cboMultipleDeviceUse = new JComboBox();
		cboMultipleDeviceUse.setBounds(177, 407, 138, 26);
		contentPanel.add(cboMultipleDeviceUse);
		
		JLabel lblMultipleDeviceUsage = new JLabel();
		lblMultipleDeviceUsage.setBounds(15, 410, 125, 20);
		contentPanel.add(lblMultipleDeviceUsage);
		if (i==0){
			lblMultipleDeviceUsage.setText("Dimensions");
			
		}
		else{
			lblMultipleDeviceUsage.setText("Print Length");
		}
		
		JComboBox<String> cboBookAvailable = new JComboBox<String>();
		cboBookAvailable.setModel(new DefaultComboBoxModel<String>(new String[] {"Yes", "No"}));
		cboBookAvailable.setSelectedIndex(0);
		cboBookAvailable.setBounds(177, 210, 111, 26);
		contentPanel.add(cboBookAvailable);
		
		JLabel lblBookAvailable = new JLabel("Book Available");
		lblBookAvailable.setBounds(15, 213, 125, 20);
		contentPanel.add(lblBookAvailable);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(i==0){
								sbController.insertHardcoverBook(txtBookName.getText(), txtBookAuthor.getText(), textField.getText(), txtISBN.getText(),
										cboBookAvailable.getSelectedItem().toString().substring(0, 1), txtEdition.getText(),
										txtFileSize.getText(), txtPrintLength.getText(), txtDimensions.getText());;
							}
							else{
								sbController.insertEBook(txtBookName.getText(), txtBookAuthor.getText(), textField.getText(), txtISBN.getText(),
										cboBookAvailable.getSelectedItem().toString().substring(0, 1), txtEdition.getText(), 
										txtFileSize.getText(), txtDimensions.getText(), txtPrintLength.getText());
									
							}
							frame.FetchData();
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
