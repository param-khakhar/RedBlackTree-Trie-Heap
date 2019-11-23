package Trie;

public class Person {
	
	private String n;
	private String ph;
    public Person(String name, String phone_number) {
    	n = name;
    	ph = phone_number;
    }

    public String getName() {
    	//[Name: Diljeet Singh, Phone=+91987654321]
    	return n;
        //return "[Name: "+n+", Phone="+ph+"]";
    }
    
    public String toString() {
    	return "[Name: "+n+", Phone="+ph+"]";
    }
}