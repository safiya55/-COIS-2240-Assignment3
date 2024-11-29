import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryManagementTest {

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

}
