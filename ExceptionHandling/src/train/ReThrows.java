package train;

public class ReThrows {
	
	public static void main(String[] args) {
		try {
			performTransaction();
		} catch (Exception e) {
			System.out.println("Transaction was not made: " + e.getMessage());
			//e.printStackTrace();
		}
		
	}
	
	public static void performTransaction() throws Exception {
		try {
			callBank();
		} catch (Exception e) {
			throw new Exception("There is no funds to transfer",e);
		}
	}
	public static void callBank() throws Exception {
		checkAccountFunds();
	}
	public static void checkAccountFunds() throws Exception {
		throw new Exception("There is no funds");
	}

}
