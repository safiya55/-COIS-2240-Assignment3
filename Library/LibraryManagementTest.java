import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class LibraryManagementTest {
	private Transaction transaction;
	private Book book;
	private Member member;

	@Test
	public void testBookId() throws Exception{
		//testing for valid ID
		Book book1 = new Book(100, "Valid Book 1");
		assertEquals(100, book1.getId());
		Book book2 = new Book(999, "Valid Book 2");
		assertEquals(999, book2.getId());
		
		//testing for invalid book id and exception methods
		try {
			new Book(99, "Invalid book 1");
			fail("Exception exxception for ID < 100");
		}catch(Exception e) {
			assertEquals("Invalid book ID: 99.", "Must be between 100 and 999");
		}
		try {
			new Book(1000, "Invalid book 2");
			fail("Exception exxception for ID > 1000");
		}catch (Exception e) {
			assertEquals("Invalid book ID: 1000.", "Must be between 100 and 999");
		}
	}
	
	@Before
	public void setUp() throws Exception {
		transaction = Transaction.getTransaction();
		book = new Book(101, "Book test");
		member = new Member(1, "Member test");
	}
	
	@Test
	public void testBorrowReturn() throws Exception{
		
		//is book 
		assertTrue("Book should initially be available", book.isAvailable());
		
		//borrow book
		boolean borrowSuccess = transaction.borrowBook(book, member);
		assertTrue("Borrowing should succeed for available books", borrowSuccess);
		assertFalse("Borrowing should fail for unavailable books", book.isAvailable());
		
		//borrow the same book again
		boolean borrowedAgain = transaction.borrowBook(book, member);
		assertFalse("Borrowing should fail for unavailable books", borrowedAgain);
		
		//return book
		boolean returnSuccess = transaction.returnBook(book, member);
		assertTrue("Returning books should succeed", returnSuccess);
		assertTrue("Book should now be available", book.isAvailable());
		
		//try returning the same book
		boolean returnAgain = transaction.returnBook(book, member);
		assertFalse("Returning should fail if book has already been returned", returnAgain);
		

	}
}

