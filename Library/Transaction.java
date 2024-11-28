import java.text.SimpleDateFormat;
import java.util.Date;
//import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Transaction {
	
	//singleton
	private static Transaction instance;
	
	private Transaction() {
		
	}
	public static Transaction getTransaction() {
		if(instance == null) {
			instance = new Transaction();
		}
		return instance;
	}

    // Perform the borrowing of a book
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }
    
    
  //save all borrowing and returning transactions to a file
    public void saveTransaction(String transactionDetails) {
//    	try 
//			(BufferedWriter writer = new BufferedWriter(new FileWriter("transaction.txt", true))){
//			writer.write(transactionDetails);
//			writer.newLine();
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    	FileWriter writer = null;
    	try {
    		String fileName = "transaction.txt";
    		
    		writer = new FileWriter(fileName, true);
    		writer.write(transactionDetails + System.lineSeparator());
    	}
    	catch (IOException e) {
    		System.out.println("Error writting to file: " + e.getMessage());
    	}finally {
    		try {
    			if(writer != null) {
    				writer.close();
    			}
    		}catch(IOException e) {
    			System.out.println("Error closing the file: " + e.getMessage());
    		}
    	}
    }
    
    //display previous transactions saved in transactions.txt
    public void displayTransactionHistory() {
    	BufferedReader reader = null;
    	try {
    		//open file
    		reader = new BufferedReader(new FileReader("transaction.txt"));
    		String line;
    		System.out.println("Transaction History: ");
    		
    		//read and print
    		while((line = reader.readLine()) != null) {
    			System.out.println(line);
    		}
    	}
    	catch(IOException e){
    		System.out.println("Error reading the file: " + e.getMessage());
    	}
    	finally {
    		try {
    			if(reader != null) {
    				reader.close();
    			}
    		}catch(IOException e) {
    				System.out.println("Error closinf file" + e.getMessage());
    			}
    		}
    	}

    // Get the current date and time in a readable format
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
}