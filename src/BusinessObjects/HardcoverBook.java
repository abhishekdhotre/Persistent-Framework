package BusinessObjects;

public class HardcoverBook extends Book {
	String BookWeight; 
	String BookNoOfPages; 
	String BookDimensions;
	
	public HardcoverBook(int BookId, String BookName, String BookAuthor, String BookPublisher, 
			String BookISBN, String BookAvailableYN, String BookEdition, String BookNoOfPages, 
			String BookWeight, String BookDimensions) {
		super(BookId, BookName, BookAuthor, BookPublisher, BookISBN, BookAvailableYN, BookEdition);
		this.BookWeight = BookWeight;
		this.BookNoOfPages = BookNoOfPages;
		this.BookDimensions = BookDimensions;
	}

	public String getBookWeight() {
		return BookWeight;
	}

	public void setBookWeight(String bookWeight) {
		BookWeight = bookWeight;
	}

	public String getBookNoOfPages() {
		return BookNoOfPages;
	}

	public void setBookNoOfPages(String bookNoOfPages) {
		BookNoOfPages = bookNoOfPages;
	}

	public String getBookDimensions() {
		return BookDimensions;
	}

	public void setBookDimensions(String bookDimensions) {
		BookDimensions = bookDimensions;
	}

}
