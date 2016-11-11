package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.EditBookController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateDataDialog extends JDialog {
	private int bookId;
	private String sBookType;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtBookName;
	private JTextField txtBookAuthor;
	private JComboBox<String> cboBookAvailableYN;
	private JTextField txtISBN;
	private JTextField txtPublisher;
	private JTextField txtFileSizeNoofPages;
	private JTextField txtEdition;
	private JTextField txtPrintLengthWeight;
	private JTextField txtMultipledeviceusageDimensions;
	private JLabel lblPrintLengthWeight;
	private JLabel lblMultipledeviceusageDimensions;
	private JLabel lblFileSizePages;
	EditBookController ebcController = new EditBookController();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateDataDialog dialog = new UpdateDataDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param frame 
	 */
	public UpdateDataDialog() {
		
	}

	public UpdateDataDialog(LIS_GUI frame) {
		setBounds(100, 100, 450, 543);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setBounds(15, 16, 105, 20);
		contentPanel.add(lblBookName);
		
		JLabel lblBookAuthor = new JLabel("Author");
		lblBookAuthor.setBounds(15, 56, 105, 20);
		contentPanel.add(lblBookAuthor);
		
		JLabel lblBookAvailableYN = new JLabel("Available");
		lblBookAvailableYN.setBounds(15, 177, 105, 20);
		contentPanel.add(lblBookAvailableYN);
		
		txtBookName = new JTextField();
		txtBookName.setBounds(182, 13, 231, 26);
		contentPanel.add(txtBookName);
		txtBookName.setColumns(10);
		
		txtBookAuthor = new JTextField();
		txtBookAuthor.setBounds(182, 53, 231, 26);
		contentPanel.add(txtBookAuthor);
		txtBookAuthor.setColumns(10);
		
		cboBookAvailableYN = new JComboBox<String>();
		cboBookAvailableYN.setModel(new DefaultComboBoxModel<String>(new String[] {"Yes", "No"}));
		cboBookAvailableYN.setBounds(182, 174, 70, 26);
		contentPanel.add(cboBookAvailableYN);
		
		txtISBN = new JTextField();
		txtISBN.setColumns(10);
		txtISBN.setBounds(182, 132, 231, 26);
		contentPanel.add(txtISBN);
		
		txtPublisher = new JTextField();
		txtPublisher.setColumns(10);
		txtPublisher.setBounds(182, 92, 231, 26);
		contentPanel.add(txtPublisher);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setBounds(15, 95, 105, 20);
		contentPanel.add(lblPublisher);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(15, 135, 105, 20);
		contentPanel.add(lblIsbn);
		
		txtFileSizeNoofPages = new JTextField();
		txtFileSizeNoofPages.setColumns(10);
		txtFileSizeNoofPages.setBounds(182, 256, 231, 26);
		contentPanel.add(txtFileSizeNoofPages);
		
		lblFileSizePages = new JLabel("File Size");
		
		lblFileSizePages.setBounds(15, 259, 105, 20);
		contentPanel.add(lblFileSizePages);
		
		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setBounds(15, 219, 105, 20);
		contentPanel.add(lblEdition);
		
		txtEdition = new JTextField();
		txtEdition.setColumns(10);
		txtEdition.setBounds(182, 216, 231, 26);
		contentPanel.add(txtEdition);
		
		lblPrintLengthWeight = new JLabel("Print Length");
		
		lblPrintLengthWeight.setBounds(15, 301, 105, 20);
		contentPanel.add(lblPrintLengthWeight);
		
		txtPrintLengthWeight = new JTextField();
		txtPrintLengthWeight.setColumns(10);
		txtPrintLengthWeight.setBounds(182, 298, 231, 26);
		contentPanel.add(txtPrintLengthWeight);
		
		txtMultipledeviceusageDimensions = new JTextField();
		txtMultipledeviceusageDimensions.setColumns(10);
		txtMultipledeviceusageDimensions.setBounds(182, 338, 231, 26);
		contentPanel.add(txtMultipledeviceusageDimensions);
		
		lblMultipledeviceusageDimensions = new JLabel("Multiple Device Usage");
	
		lblMultipledeviceusageDimensions.setBounds(15, 341, 165, 20);
		contentPanel.add(lblMultipledeviceusageDimensions);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(sBookType == "Ebook"){
								ebcController.UpdateEBook(bookId, txtBookName.getText(), txtBookAuthor.getText(), txtPublisher.getText(),
										txtISBN.getText(), cboBookAvailableYN.getSelectedItem().toString(), txtEdition.getText(), 
										txtPrintLengthWeight.getText(), txtMultipledeviceusageDimensions.getText(), txtFileSizeNoofPages.getText());
							}
							else{
								ebcController.UpdateHardCoverBook(bookId, txtBookName.getText(), txtBookAuthor.getText(), txtPublisher.getText(),
										txtISBN.getText(), cboBookAvailableYN.getSelectedItem().toString(), txtEdition.getText(), 
										txtPrintLengthWeight.getText(), txtFileSizeNoofPages.getText(), txtMultipledeviceusageDimensions.getText());
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				dispose();
			}
		}
	}
	
	public void setUpdateDataDialogFields(int BookId, String BookName, String BookAuthor, String Publisher,
			String ISBN, String BookAvailableYN, String Edition, String PrintLengthNoOfPages ,
			String FileSizeWeight, String MultipledeviceusageDimensions , String BookType)
	{
		bookId = BookId;
		sBookType= BookType;
		txtBookName.setText(BookName); 
		txtBookAuthor.setText(BookAuthor); 
		txtPublisher.setText(Publisher);
		txtISBN.setText(ISBN);
		cboBookAvailableYN.setSelectedItem(BookAvailableYN);
		txtEdition.setText(Edition);
		txtFileSizeNoofPages.setText(FileSizeWeight);
		txtMultipledeviceusageDimensions.setText(MultipledeviceusageDimensions);
		txtPrintLengthWeight.setText(PrintLengthNoOfPages);
		if (BookType == "Ebook") {
			lblPrintLengthWeight.setText("File Size");	
		}else {
			lblPrintLengthWeight.setText("No. of Pages");
		}
		if (BookType == "Ebook") {
			lblFileSizePages.setText("Print Length");	
		}else {
			lblFileSizePages.setText("Book Weight");
		}
		if (BookType == "Ebook") {
			lblMultipledeviceusageDimensions.setText("Multiple Device Usage");	
		}else {
			lblMultipledeviceusageDimensions.setText("Dimensions");
		}
		
	}
}
