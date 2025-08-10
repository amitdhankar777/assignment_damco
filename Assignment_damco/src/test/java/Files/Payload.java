package Files;

public class Payload {

	public static String addUser(int id, String name, String email, String gender, String status) {
		String userdetails = "{\r\n"
				+ "	    \"id\": "+id+",\r\n"
				+ "	    \"name\": \""+name+"\",\r\n"
				+ "	    \"email\": \""+email+"\",\r\n"
				+ "	    \"gender\": \""+gender+"\",\r\n"
				+ "	    \"status\": \""+status+"\"\r\n"
				+ "	}";
		return userdetails;
	}
	
	
	
	
	
	
	
	
	
	
}
