package train;

public class Throws {
	
	public static void main(String[] args) {
		
		try {
			handleUserRegistration("Cleiton", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}
	public static void handleUserRegistration(String username, String password) throws Exception {
		try {
			validatePassword(password);
			System.out.println("User " + username + " registrated successfully");
			
		}catch(IllegalArgumentException e) {
			System.out.println("Error in handleUserRegistration: " + e.getMessage());
			throw new Exception("Registration failed for username: " + username,e);
		}
	}
	
	public static void validatePassword(String passWord) {
		if(passWord == null || passWord.isEmpty()) {
			throw new IllegalArgumentException("Password cannot be empty");
		}
	}

}
