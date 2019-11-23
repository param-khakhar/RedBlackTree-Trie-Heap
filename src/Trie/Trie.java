package Trie;
import java.util.Queue;
import java.util.Stack;

import PriorityQueue.MaxHeap;

import java.util.LinkedList;
import java.util.ArrayList;
public class Trie<T> implements TrieInterface<T> {
	
	private TrieNode<T> root;
	private int size;
	public Trie(){
		root = new TrieNode<T>(' ',95);
		root.setLevel(0);
		size = 95;
	}

    @Override
    public boolean delete(String word) {
    	
    	boolean deleted = false;
    	TrieNode<T> last = search(word);
    	if((last==null)||(!last.isEndOfword())) {
    		//System.out.println("ERROR DELETING");
    		return deleted;
    	}
    	else {
    		last.setEndOfword(false);
    		char [] charArr = word.toCharArray();
    		TrieNode<T> currNode = last;
    		TrieNode<T> parNode = last.getParent();
    		int s = charArr.length;
    		TrieNode<T> [] childArr = parNode.getChildren();
    		int currAscii;
    		int i = s-1;
    		while(i>=0) {
    			currAscii = ascii(charArr[i]);
    			if((currNode.getCount()==0)&&(!currNode.isEndOfword())) {
    				childArr[currAscii] = null;
    				parNode.setCount(parNode.getCount()-1);
    				currNode = parNode;
    				if(i == 0) {
    					deleted = true;
    				}
    				else {
    					parNode = parNode.getParent();
    					childArr = parNode.getChildren();
    				}
    			}
    			else {
    				//System.out.println("DELETED");
    				deleted = true;
    				break;
    			}
    			//childArr = parNode.getChildren();
    			i = i-1;	
    		}
    		return deleted;
    	}
       }

    @Override
    public TrieNode<T> search(String word) {
    	char [] charArr = word.toCharArray();
    	int s = charArr.length;
    	TrieNode<T> currNode = root;
    	TrieNode<T> [] childArr = currNode.getChildren();
    	boolean found = true;
    	int currAscii;
    	for(int i=0;i<s;i++) {
    		currAscii = ascii(charArr[i]);
    		currNode = childArr[currAscii];
    		if((currNode == null)) {
    			found = false;
    			break;
    		}
    		else {
    			childArr = currNode.getChildren();
    		}
    	}
        if((!found)||(!currNode.isEndOfword())) {
        	return null;
        }
        else {
        	return currNode;
        }
    }

    @Override
    public TrieNode<T> startsWith(String prefix) {
    	
    	char [] charArr = prefix.toCharArray();
    	int s = charArr.length;
    	TrieNode<T> currNode = root;
    	TrieNode<T> [] childArr = currNode.getChildren();
    	boolean found = true;
    	int currAscii;
    	for(int i=0;i<s;i++) {
    		currAscii = ascii(charArr[i]);
    		currNode = childArr[currAscii];
    		if((currNode == null)) {
    			found = false;
    			break;
    		}
    		else {
    			childArr = currNode.getChildren();
    		}
    	}
        if(!found) {
        	return null;
        }
        else {
        	return currNode;
        }
    }

    @Override
    public void printTrie(TrieNode trieNode) {
    	
    	//DFS(trieNode,trieNode.getLevel());
    	Queue<TrieNode<T>> q = new LinkedList<>();
    	ArrayList<String> l = new ArrayList<String>();
    	TrieNode<T> currNode = trieNode;
    	TrieNode<T> [] childArr;
    	q.add(currNode);
		if(currNode.isEndOfword()) {
			l.add(currNode.getValue().toString());
		}
    	while(!q.isEmpty()) {
    		currNode = q.remove();
    		childArr = currNode.getChildren();
    		for(int i=0;i<childArr.length;i++) {
    			if(childArr[i]!=null) {
    				if(childArr[i].isEndOfword()) {
    					l.add(childArr[i].getValue().toString());
    				//System.out.println(childArr[i].getValue());
    					}
    				q.add(childArr[i]);
    			}
    		}
    	}
    	ArrayList<String> l1 = SheapSort(l);
    	for(int i=0;i<l1.size();i++) {
    		System.out.println(l1.get(i));
    	}
    }

    @Override
    public boolean insert(String word, Object value) {
    	
    	char [] charArr = word.toCharArray();
    	int s = charArr.length;
    	TrieNode<T> currNode = root;
    	TrieNode<T> parNode = root;
    	TrieNode<T> [] currChildren = currNode.getChildren();
    	boolean inserted = false;
    	int currAscii;
    	for(int i=0;i<s;i++) {
    		currAscii = ascii(charArr[i]);
    		currNode = currChildren[currAscii];
    		if(currNode == null) {
    			currChildren[currAscii] = new TrieNode<T>(charArr[i],size);
    			parNode.setCount(parNode.getCount()+1);
    			currNode = currChildren[currAscii];
    			currNode.setParent(parNode);
    			currNode.setLevel(parNode.getLevel()+1);
    		}
    		if(i == s-1) {
				if(currNode.isEndOfword()) {
					inserted = false;
				}
				else {
				currNode.setVal((T)value);	
				currNode.setEndOfword(true);
				inserted = true;
				}
			}
        	currChildren = currNode.getChildren();
        	parNode = currNode;
    	}
        return inserted;
    }

    @Override
    public void printLevel(int level) {
    	
    	System.out.print("Level "+level+":"+" ");
    	ArrayList<Character> l = new ArrayList<Character>();
    	Queue<TrieNode<T>> q = new LinkedList<>();
    	q.add(root);
    	TrieNode<T> curr;
    	TrieNode<T> [] arr;
    	while(!q.isEmpty()) {
    		curr = q.remove();
    		arr = curr.getChildren();
    		if(curr.getLevel()==level-1) {
    			for(int i=0;i<arr.length;i++) {
    				if(arr[i]!=null) {
    				//System.out.print(arr[i].getKey()+",");
    				l.add(arr[i].getKey());
    				}
    			}
    		}
    		else {
    			for(int i=0;i<arr.length;i++) {
    				if(arr[i]!=null) {
    				q.add(arr[i]);
    				}
    			}
    		}
    	}
    	
    	ArrayList<Character>l1 = CheapSort(l);
    	for(int i=0;i<l1.size();i++) {
    		if(i == l1.size()-1) {
    			System.out.print(l1.get(i));	
    		}
    		else {
    		System.out.print(l1.get(i)+",");
    		}
    	}
    	System.out.println();
    }

    @Override
   /** public void print() {
    	
    	System.out.println("-------------");
    	System.out.println("Printing Trie");
    	ArrayList<Character> l = new ArrayList<Character>();
    	Queue<TrieNode<T>> q = new LinkedList<>();
    	q.add(root);
    	TrieNode<T> curr;
    	TrieNode<T> [] arr;
    	int level = 1;
    	System.out.print("Level "+level+": ");
    	while(!q.isEmpty()) {
    		curr = q.remove();
    		arr = curr.getChildren();
    		for(int i=0;i<arr.length;i++) {
    			if(arr[i]!=null){
    				if(!arr[i].isEndOfword()) {
    			q.add(arr[i]);
    				}
    			if(arr[i].getLevel() != level) {
    				ArrayList<Character> l2 = CheapSort(l);
    				for(int j=0;j<l2.size();j++) {
    					if(j!=l2.size()-1) {
    					System.out.print(l2.get(j)+",");
    					}
    					else {
    					System.out.print(l2.get(j));
    					}
    				}
    				l = new ArrayList<Character>();
    	    		System.out.println();
    				level = level + 1;
    				System.out.print("Level "+level+": ");
    			}
    			if(arr[i].getKey() != ' ') {
    				l.add(arr[i].getKey());
    			//System.out.print(arr[i].getKey()+" ");
    			}
    			}
    		}
    	}
    	
    	ArrayList<Character> l3 = CheapSort(l);
		for(int j=0;j<l3.size();j++) {
			if(j!=l3.size()-1) {
			System.out.print(l3.get(j)+",");
			}
			else {
			System.out.print(l3.get(j));
			}
		}
		System.out.println();
		level = level + 1;
		System.out.print("Level "+level+": ");
    	System.out.println();
    	System.out.println("-------------");
    }**/
    
    public void print() {

    	System.out.println("-------------");
    	System.out.println("Printing Trie");
    	ArrayList<Character> l = new ArrayList<Character>();
    	Queue<TrieNode<T>> q = new LinkedList<>();
    	q.add(root);
    	TrieNode<T> curr;
    	TrieNode<T> [] arr;
    	int level = 1;
    	
    	while(!q.isEmpty()) {
    		curr = q.remove();
    		arr = curr.getChildren();
    		for(int i=0;i<arr.length;i++) {
    			if(arr[i]!=null) {
    				q.add(arr[i]);
    			}
    		}
    		if(curr!=root) {
    			if(curr.getLevel()==level) {
    				if(curr.getKey() != ' ') {
				l.add(curr.getKey());
    				}
    		}
    			else {
    				System.out.print("Level "+level+": ");
    				level += 1;
    				l = CheapSort(l);
    				for(int j=0;j<l.size();j++) {
    					if(j!=l.size()-1) {
    						System.out.print(l.get(j)+",");
    					}
    					else {
    						System.out.print(l.get(j));
    						}
    					}
    				System.out.println();
    				l = new ArrayList<Character>();
    				if(curr.getKey()!=' ') {
    				l.add(curr.getKey());
    				}
    			}
    		}
    	}
		System.out.print("Level "+level+": ");
		level += 1;
		l = CheapSort(l);
		if(l.size()!=0) {
		for(int j=0;j<l.size();j++) {
			if(j!=l.size()-1) {
				System.out.print(l.get(j)+",");
			}
			else {
				System.out.print(l.get(j));
				}
			}
		System.out.println();
		System.out.print("Level "+level+": ");
		}
		System.out.println();
    	System.out.println("-------------");
    }
    
    
    public int ascii(char c) {
    	
    	int s = (int)c;
    	return s-32;
    }
    
    public ArrayList<String> SheapSort(ArrayList<String> arr) {
    	
    	MaxHeap<String> supp = new MaxHeap<>();
    	Stack<String> supp2 = new Stack<>();
    	ArrayList<String> supp3 = new ArrayList<>();
    	
    	for(int i=0;i<arr.size();i++) {
    		supp.insert(arr.get(i));
    	}
    	
    	while(supp.getSize()>0) {
    		String c = supp.extractMax();
    		supp2.push(c);
    	}
    	
    	while(!supp2.isEmpty()) {
    		String c1 = supp2.pop();
    		supp3.add(c1);
    	}
    	return supp3;
    }
    
    public ArrayList<Character> CheapSort(ArrayList<Character> arr) {
    	
    	MaxHeap<Character> supp = new MaxHeap<>();
    	Stack<Character> supp2 = new Stack<>();
    	ArrayList<Character> supp3 = new ArrayList<>();
    	
    	for(int i=0;i<arr.size();i++) {
    		supp.insert(arr.get(i));
    	}
    	
    	while(supp.getSize()>0) {
    		Character c = supp.extractMax();
    		supp2.push(c);
    	}
    	
    	while(!supp2.isEmpty()) {
    		Character c1 = supp2.pop();
    		supp3.add(c1);
    	}
    	return supp3;
    }
}