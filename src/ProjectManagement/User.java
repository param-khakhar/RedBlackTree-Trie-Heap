package ProjectManagement;

public class User implements Comparable<User> {
	
	private String name;
	
	User(String s){
		name = s;
	}
	
	public String getName() {
		return name;
	}
	
    @Override
    public int compareTo(User user) {
        return name.compareTo(user.getName());
    }
}
