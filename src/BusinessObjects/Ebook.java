package BusinessObjects;

public class Ebook extends Book {
	String FileSize; 
	String MultipleDeviceUsage; 
	String PrintLength;
	
	public Ebook(int BookId, String BookName, String BookAuthor, String BookPublisher,
			String BookISBN, String BookAvailableYN, String BookEdition, String FileSize, 
			String PrintLength, String MultipleDeviceUsage) {
		super(BookId, BookName, BookAuthor, BookPublisher, BookISBN, BookAvailableYN, BookEdition);
		this.FileSize = FileSize;
		this.PrintLength = PrintLength;
		this.MultipleDeviceUsage = MultipleDeviceUsage;
	}

	public String getFileSize() {
		return FileSize;
	}

	public void setFileSize(String fileSize) {
		FileSize = fileSize;
	}

	public String getMultipleDeviceUsage() {
		return MultipleDeviceUsage;
	}

	public void setMultipleDeviceUsage(String multipleDeviceUsage) {
		MultipleDeviceUsage = multipleDeviceUsage;
	}

	public String getPrintLength() {
		return PrintLength;
	}

	public void setPrintLength(String printLength) {
		PrintLength = printLength;
	}

}
