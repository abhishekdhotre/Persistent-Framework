package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Controller.EditBookController;
import Controller.RetrieveBookController;
import Controller.UndoRedoController;
import UndoRedo.RedoStack;
import UndoRedo.UndoStack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTable;
import java.sql.*;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class LIS_GUI extends JFrame {
	static LIS_GUI frame;
	private JPanel contentPane;
	private JTable tblHardCoverBooks;
	private JTable tblEBooks;
	JTabbedPane tabbedPane;
	UndoRedoController urController = new UndoRedoController();
	RetrieveBookController rbcController = new RetrieveBookController();
	EditBookController ebcController = new EditBookController();
	private JTextField txtBookSearch;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LIS_GUI();
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
	public LIS_GUI() {
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1665, 817);
		
		RedoStack.initialize();
		UndoStack.initialize();
		
		txtBookSearch = new JTextField();
		txtBookSearch.setFont(new Font("Tahoma", Font.PLAIN, 26));
		txtBookSearch.setBounds(197, 14, 184, 26);
		contentPane.add(txtBookSearch);
		txtBookSearch.setColumns(10);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setAutoscrolls(true);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 45, 1476, 732);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 26));
	    ChangeListener changeListener = new ChangeListener() {
	      public void stateChanged(ChangeEvent changeEvent) {
	    	  txtBookSearch.setText("");
	        FetchData();
	      }
	    };   
	    tabbedPane.addChangeListener(changeListener);
	    
		JScrollPane pnlHardCoverBooks = new JScrollPane();
		pnlHardCoverBooks.setAutoscrolls(true);
		pnlHardCoverBooks.setLayout(null);
		tblHardCoverBooks = new JTable();
		tblHardCoverBooks.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblHardCoverBooks.setFont(new Font("Verdana", Font.PLAIN, 26));
		tblHardCoverBooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JTableHeader header = tblHardCoverBooks.getTableHeader();
	    header.setFont(new Font("Verdana", Font.PLAIN, 26));
	    header.setAutoscrolls(true);
		JScrollPane tableContainer = new JScrollPane(tblHardCoverBooks);
		tableContainer.setAutoscrolls(true);
		tableContainer.setBounds(new Rectangle(0, 0, 1474, 676));
		tableContainer.setBounds(0, 0, 1489, 676);
		pnlHardCoverBooks.add(tableContainer);
		tblHardCoverBooks.setFillsViewportHeight(true);
		tblHardCoverBooks.setRowHeight(30);
		contentPane.setLayout(null);
		
		JPanel pnlEBookBook = new JPanel();
		pnlEBookBook.setAutoscrolls(true);
		pnlEBookBook.setLayout(null);
		tblEBooks = new JTable();
		tblEBooks.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblEBooks.setFont(new Font("Verdana", Font.PLAIN, 26));
		tblEBooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane tableContainer1 = new JScrollPane(tblEBooks);
		tableContainer1.setAutoscrolls(true);
		tableContainer1.setBounds(0, 0, 1474, 676);
		pnlEBookBook.add(tableContainer1);
		tblEBooks.setFillsViewportHeight(true);
		tblEBooks.setRowHeight(30);
		JTableHeader header1 = tblEBooks.getTableHeader();
		header1.setFont(new Font("Verdana", Font.PLAIN, 26));
		contentPane.setLayout(null);

		JPanel pnlButtons = new JPanel();
		pnlButtons.setAutoscrolls(true);
		pnlButtons.setBackground(Color.WHITE);
		pnlButtons.setBounds(1480, 0, 165, 621);
		contentPane.add(pnlButtons);
		
		ImageIcon icon = null;
		JComponent panel1 = pnlHardCoverBooks;
		tabbedPane.addTab("Hard Cover Books", icon, panel1,
		                  "Lsit of Hard Cover Books");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		JComponent panel2 = pnlEBookBook;
		tabbedPane.addTab("E-Books", icon, panel2,
		                  "List of E-Books");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		contentPane.add(tabbedPane);
		FetchData();
		
		/**
		 * Delete Button.
		 */
		JButton btnDeleteData = new JButton("Delete Data");
		btnDeleteData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDeleteData.setBounds(5, 220, 150, 29);
		btnDeleteData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteBook();
			}
		});
		pnlButtons.setLayout(null);
		pnlButtons.add(btnDeleteData);
		
		/**
		 * Add Button.
		 */
		JButton btnAddData = new JButton("Add Data");
		btnAddData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddData.setBounds(5, 103, 150, 29);
		btnAddData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDataDialog addDialog = new AddDataDialog(tabbedPane.getSelectedIndex(), frame);
				addDialog.setLocationRelativeTo(contentPane);
				addDialog.setVisible(true);
				
			}
		});
		pnlButtons.add(btnAddData);
		
		/**
		 * Update Button.
		 */
		JButton btnUpdateData = new JButton("Update Data");
		btnUpdateData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpdateData.setBounds(5, 161, 150, 29);
		btnUpdateData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateDataDialog updDialog = new UpdateDataDialog(frame);
				updDialog.setLocationRelativeTo(contentPane);
				int rowIndex;
				if(tabbedPane.getSelectedIndex() == 0){
					if (tblHardCoverBooks.getSelectedRow() != -1) 
					{           
			    			rowIndex= tblHardCoverBooks.getSelectedRow();    			
			    			updDialog.setUpdateDataDialogFields((int)tblHardCoverBooks.getModel().getValueAt(rowIndex, 0),
			    					(String)tblHardCoverBooks.getModel().getValueAt(rowIndex, 1),
			    					(String)tblHardCoverBooks.getModel().getValueAt(rowIndex, 2),
			    					(String)tblHardCoverBooks.getModel().getValueAt(rowIndex, 3),
					    			(String)tblHardCoverBooks.getModel().getValueAt(rowIndex, 4),
			    					(String)tblHardCoverBooks.getModel().getValueAt(rowIndex, 5),
			    					(String)tblHardCoverBooks.getModel().getValueAt(rowIndex, 6),
									(String)tblHardCoverBooks.getModel().getValueAt(rowIndex, 7), 
									(String)tblHardCoverBooks.getModel().getValueAt(rowIndex, 8), 
									(String)tblHardCoverBooks.getModel().getValueAt(rowIndex, 9),
									"HardcoverBook");
					}
					updDialog.setVisible(true);
				}
				else if(tabbedPane.getSelectedIndex() == 1){
					if (tblEBooks.getSelectedRow() != -1) 
					{           
			    			rowIndex= tblEBooks.getSelectedRow();    			
			    			updDialog.setUpdateDataDialogFields((int)tblEBooks.getModel().getValueAt(rowIndex, 0),
			    					(String)tblEBooks.getModel().getValueAt(rowIndex, 1),
			    					(String)tblEBooks.getModel().getValueAt(rowIndex, 2),
			    					(String)tblEBooks.getModel().getValueAt(rowIndex, 3),
					    			(String)tblEBooks.getModel().getValueAt(rowIndex, 4),
			    					(String)tblEBooks.getModel().getValueAt(rowIndex, 5),
			    					(String)tblEBooks.getModel().getValueAt(rowIndex, 6),
									(String)tblEBooks.getModel().getValueAt(rowIndex, 7),
									(String)tblEBooks.getModel().getValueAt(rowIndex, 8),
									(String)tblEBooks.getModel().getValueAt(rowIndex, 9),
									"Ebook");
					}
					updDialog.setVisible(true);
				}
				
			}
		});
		pnlButtons.add(btnUpdateData);
		
		/**
		 * Refresh Button.
		 */
		JButton btnRefreshData = new JButton("Refresh Data");
		btnRefreshData.setHorizontalTextPosition(SwingConstants.LEFT);
		btnRefreshData.setHorizontalAlignment(SwingConstants.LEFT);
		btnRefreshData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRefreshData.setBounds(5, 41, 150, 35);
		btnRefreshData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBookSearch.setText("");
				FetchData();
			}
		});
		btnRefreshData.setVerticalAlignment(SwingConstants.BOTTOM);
		btnRefreshData.setPreferredSize(new Dimension(125, 29));
		pnlButtons.add(btnRefreshData);
		
		/**
		 * Undo Button.
		 */
		JButton btnNewButton = new JButton("Undo");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				urController.undo();
				FetchData();
			}
		});
		btnNewButton.setBounds(5, 275, 150, 29);
		pnlButtons.add(btnNewButton);
		
		/**
		 * Redo Button.
		 */
		JButton btnNewButton_1 = new JButton("Redo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				urController.redo();
				FetchData();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(5, 330, 150, 29);
		pnlButtons.add(btnNewButton_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Search by Name", "Search by ID"}));
		comboBox.setBounds(15, 14, 174, 26);
		contentPane.add(comboBox);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = new DefaultTableModel();
				try {
					if(tabbedPane.getSelectedIndex() == 0){
						if(comboBox.getSelectedItem().toString().equals("Search by Name")){
							model = rbcController.FetchHardCoverBooks(txtBookSearch.getText());
							if(model!=null){
							tblHardCoverBooks.setModel(model);
								for (int i = 0; i < tblHardCoverBooks.getColumnCount(); i++) {
									setColumnWidth(tblHardCoverBooks, i, 2);
						        }
							}
						}
						else{
							model = rbcController.SearchHardcoverBook(txtBookSearch.getText());
							if(model!=null){
							tblHardCoverBooks.setModel(model);
								for (int i = 0; i < tblHardCoverBooks.getColumnCount(); i++) {
									setColumnWidth(tblHardCoverBooks, i, 2);
						        }
							}
						}
					}
					else{
						if(comboBox.getSelectedItem().toString().equals("Search by Name")){
							model = rbcController.FetchEBooks(txtBookSearch.getText());
							if(model!=null){
							tblEBooks.setModel(model);
								for (int i = 0; i < tblEBooks.getColumnCount(); i++) {
									setColumnWidth(tblEBooks, i, 2);
						        }
							}
						}
						else{
							model = rbcController.SearchEBook(txtBookSearch.getText());
							if(model!=null){
							tblEBooks.setModel(model);
								for (int i = 0; i < tblEBooks.getColumnCount(); i++) {
									setColumnWidth(tblEBooks, i, 2);
						        }
							}
						}
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSearch.setBounds(386, 14, 115, 29);
		contentPane.add(btnSearch);
		
		
	}
	
	/**Display Book Details 
	 * @throws SQLException **/
	public void FetchData() 
	{
		try
		{
			if(tabbedPane.getSelectedIndex() == 0){
				tblHardCoverBooks.setModel(rbcController.FetchHardCoverBooks(""));
				
				for (int i = 0; i < tblHardCoverBooks.getColumnCount(); i++) {
					setColumnWidth(tblHardCoverBooks, i, 2);
		        }
			}
			else{
				tblEBooks.setModel(rbcController.FetchEBooks(""));
				for (int i = 0; i < tblEBooks.getColumnCount(); i++) {
					setColumnWidth(tblEBooks, i, 2);
		        }
			}
			
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	/**Delete Book Data
	 * @throws SQLException **/
	public void DeleteBook() 
	{
		int rowIndex;
		try
		{
			if(tabbedPane.getSelectedIndex() == 0){
				if (tblHardCoverBooks.getSelectedRow() != -1)    
				{
					rowIndex= tblHardCoverBooks.getSelectedRow();
					Object BookId = tblHardCoverBooks.getModel().getValueAt(rowIndex, 0);
					ebcController.DeleteHardcoverBook((int)BookId);
					FetchData();
				} 
			}
			else if (tabbedPane.getSelectedIndex() == 1){
				if (tblEBooks.getSelectedRow() != -1)    
				{
					rowIndex= tblEBooks.getSelectedRow();
					Object BookId = tblEBooks.getModel().getValueAt(rowIndex, 0);
					ebcController.DeleteEBook((int)BookId);
					FetchData();
				} 
			}
			
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
    public void setColumnWidth(JTable table, int colIndex, int edge) {
        DefaultTableColumnModel model = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn column = model.getColumn(colIndex);
        int width;
        TableCellRenderer celRenderer = column.getHeaderRenderer();
        if (celRenderer == null) {
        	celRenderer = table.getTableHeader().getDefaultRenderer();
        }
        Component component = celRenderer.getTableCellRendererComponent(table, column.getHeaderValue(), false, false, 0, 0);
        width = component.getPreferredSize().width;
        for (int i = 0; i < table.getRowCount(); i++) {
        	celRenderer = table.getCellRenderer(i, colIndex);
            component = celRenderer.getTableCellRendererComponent(table, table.getValueAt(i, colIndex), false, false, i, colIndex);
            int currentWidth = component.getPreferredSize().width;
            width = Math.max(width, currentWidth);
        }
        width += 2 * edge;
        column.setPreferredWidth(width);
        column.setWidth(width);
    }
}

