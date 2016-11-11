package FactoryMethod;

import java.sql.SQLException;

import BusinessObjects.Ebook;
import Commands.FetchBooks;

public  class FetchEBook extends FetchBooks {

	public FetchEBook(String bookName) {
		super(bookName);
	}

	@Override
	public void queryDB() {
		String strQuery;
		try
		{
			stmt = con.createStatement();
			strQuery = "SELECT * FROM ebook_master ";
			if(bookName != ""){
				strQuery = strQuery + "WHERE EBook_Name like '%" + bookName + "%' ";
			}
			rs = stmt.executeQuery(strQuery);
			while (rs.next()) 
			{  			
				bookList.add(
					new Ebook(rs.getInt("idEbook_Master"), 
							rs.getString("EBook_Name"), 
							rs.getString("EBook_Author"), 
							rs.getString("EBook_Publisher"),
							rs.getString("EBook_ISBN"),
							rs.getString("EBook_AvailableYN"), 
							rs.getString("EBook_Edition"), 
							rs.getString("EBook_FileSize"),
							rs.getString("EBook_PrintLength"),
							rs.getString("EBook_MultipleDeviceUsage"))
					);
			} 
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void undoQueryDB() {
		// TODO Auto-generated method stub
		// Do Nothing
	}
}
