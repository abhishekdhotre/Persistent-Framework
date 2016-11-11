package BusinessObjects;

public class Book 
{
	int BookId;
	String BookName;
	String BookAuthor;
	String BookPublisher;
	String BookISBN; 
	String BookAvailableYN;
	String BookEdition;
	
	public Book(int BookId, String BookName, String BookAuthor, String BookPublisher, String BookISBN, String BookAvailableYN, String BookEdition)
	{
		this.BookId = BookId;
		this.BookName = BookName;
		this.BookAuthor= BookAuthor;
		this.BookPublisher = BookPublisher;
		this.BookISBN = BookISBN;
		this.BookAvailableYN = BookAvailableYN;
		this.BookEdition = BookEdition;
	}

	public int getBookId() {
		return BookId;
	}

	public void setBookId(int bookId) {
		BookId = bookId;
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public String getBookAuthor() {
		return BookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		BookAuthor = bookAuthor;
	}

	public String getBookPublisher() {
		return BookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		BookPublisher = bookPublisher;
	}

	public String getBookISBN() {
		return BookISBN;
	}

	public void setBookISBN(String bookISBN) {
		BookISBN = bookISBN;
	}

	public String getBookAvailableYN() {
		return BookAvailableYN;
	}

	public void setBookAvailableYN(String bookAvailableYN) {
		BookAvailableYN = bookAvailableYN;
	}

	public String getBookEdition() {
		return BookEdition;
	}

	public void setBookEdition(String bookEdition) {
		BookEdition = bookEdition;
	}
}
