package RedBlack;


public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {

	RedBlackNode<T,E> root;
	public RBTree(){
		root = null;
	}
	
	
    @Override
    public void insert(T key, E value) {
    	//System.out.println(value+" :Inserted");
    	RedBlackNode<T,E> curr = root;
    	RedBlackNode<T,E> ins = new RedBlackNode<T,E>("RED",value,key);
    	if(root == null) {
    		root = ins;
    		root.setColor("BLACK");
    		root.setLeft(new RedBlackNode<T,E>("EXT",null,null));
    		root.setRight(new RedBlackNode<T,E>("EXT",null,null));
    	}
    	else {
    		//System.out.println("The root is: "+curr.getValue());
    		//System.out.println("The left child is: "+curr.getLeft().getValue());
    		//System.out.println("The right child is :"+curr.getRight().getValue());
    		while(!(curr.getLeft().getColor().equals("EXT")&&(curr.getRight().getColor().equals("EXT")))&&(curr.compareTo(ins)!=0)) {

    			/**if(curr.getLeft().getColor().equals("EXT")) {
    				curr = curr.getRight();
    			}
    			else if(curr.getRight().getColor().equals("EXT")) {
    				curr = curr.getLeft();
    			}**/
    			if(curr.compareTo(ins)<0) {
    				if(!curr.getRight().getColor().equals("EXT")) {
    				curr = curr.getRight();
    				}
    				else {
    					break;
    				}
    			}
    			else {
    				if(!curr.getLeft().getColor().equals("EXT")) {
    					curr = curr.getLeft();
    				}
    				else {
    					break;
    				}
    			}
    		}
    		
    		if(curr.compareTo(ins)==0) {
    			//System.out.println("HERE");
    			curr.addValue(ins.getValue());
    		}
    		
    		else {
    			if(curr.compareTo(ins)<0) {
    				curr.setRight(ins);
    				ins.setLeft(new RedBlackNode<T,E>("EXT",null,null));
    				ins.setRight(new RedBlackNode<T,E>("EXT",null,null));
    				ins.setParent(curr);
    				}
    			
    			else 
    				{
    				curr.setLeft(ins);
    				ins.setLeft(new RedBlackNode<T,E>("EXT",null,null));
    				ins.setRight(new RedBlackNode<T,E>("EXT",null,null));
    				ins.setParent(curr);
    		}
    			
    		boolean restructure = false;
    		
    		while(!(restructure)&&((curr!=root)&&(ins!=root))&&(ins.getColor().equals("RED")&&ins.getParent().getColor().equals("RED"))){
    			curr = ins.getParent();
    			RedBlackNode<T,E> par = curr.getParent();
    			if(par.getLeft() == curr) {
    				if(par.getRight().getColor().equals("BLACK")||(par.getRight().getColor().equals("EXT"))) {
    					//System.out.println("1: "+curr.getKey().toString()+" "+ins.getKey().toString());
    					Rotation(par,curr,ins);
    					if(curr.getParent()==ins) {
    						ins.setColor("BLACK");
    					}
    					if(ins.getParent() == curr) {
    						curr.setColor("BLACK");
    					}
    					par.setColor("RED");
    					restructure = true;
    					}
    					else {
        					//System.out.println("2: "+curr.getKey().toString()+" "+ins.getKey().toString());
    						par.setColor("RED");
    						curr.setColor("BLACK");
    						par.getRight().setColor("BLACK");
    						ins = par;
    						curr = par.getParent();
    					}
    				}
    			else if(par.getRight() == curr){
    				if((par.getLeft().getColor().equals("BLACK"))||(par.getLeft().getColor().equals("EXT"))) {
    					//System.out.println("3: "+curr.getKey().toString()+" "+ins.getKey().toString());
    					Rotation(par,curr,ins);
    					if(curr.getParent()==ins) {
    						ins.setColor("BLACK");
    					}
    					if(ins.getParent() == curr) {
    						curr.setColor("BLACK");
    					}
    					par.setColor("RED");
    					restructure = true;
    				}
    				else {
    					//System.out.println("4: "+curr.getKey().toString()+" "+ins.getKey().toString());
    					par.setColor("RED");
    					curr.setColor("BLACK");
    					par.getLeft().setColor("BLACK");
    					ins = par;
    					curr = par.getParent();
    				}
    			}
    		}
    		}
    		if(root.getParent()!=null) {
    			root = root.getParent();
    		}
    		root.setColor("BLACK");
    		}
    	}
    

	public void Rotation(RedBlackNode<T,E> x,RedBlackNode<T,E> y,RedBlackNode<T,E> z){
		RedBlackNode<T,E> temp;
		if(x.getLeft() == y) {
			if(y.getLeft() == z) {
				temp = y.getRight();
				y.setRight(x);
				y.setParent(x.getParent());
				if((x.getParent()!=null)&&(x.getParent().getLeft()==x)) {x.getParent().setLeft(y);}
				else if(x.getParent()!=null) {x.getParent().setRight(y);}
				x.setParent(y);
				x.setLeft(temp);
				temp.setParent(x);
			}
			else {
				temp = z.getLeft();
				z.setParent(x);
				y.setParent(z);
				y.setRight(temp);
				z.setLeft(y);
				x.setLeft(z);
				temp.setParent(y);
				temp = z.getRight();
				z.setParent(x.getParent());
				if((x.getParent()!=null)&&(x.getParent().getLeft()==x)) {x.getParent().setLeft(z);}
				else if(x.getParent()!=null) {x.getParent().setRight(z);}
				z.setRight(x);
				x.setParent(z);
				x.setLeft(temp);
				temp.setParent(x);
			}
		}
		else {
			if(y.getRight() == z) {
				temp = y.getLeft();
				y.setParent(x.getParent());
				y.setLeft(x);
				if((x.getParent()!=null)&&(x.getParent().getLeft()==x)) {x.getParent().setLeft(y);}
				else if(x.getParent()!=null) {x.getParent().setRight(y);}
				x.setParent(y);
				x.setRight(temp);
				temp.setParent(x);
			}
			else {
				temp = z.getRight();
				z.setParent(x);
				y.setParent(z);
				y.setLeft(temp);
				z.setRight(y);
				temp.setParent(y);
				x.setRight(z);
				temp = z.getLeft();
				z.setParent(x.getParent());
				if((x.getParent()!=null)&&(x.getParent().getLeft()==x)) {x.getParent().setLeft(z);}
				else if(x.getParent()!=null) {x.getParent().setRight(z);}
				z.setLeft(x);
				x.setParent(z);
				x.setRight(temp);
				temp.setParent(x);
			}
		}
	}
	
    @Override
    public RedBlackNode<T, E> search(T key) {
    	if(root!=null) {
        RedBlackNode<T,E> curr = root;
        while((!curr.getColor().equals("EXT"))&&(curr.getKey().toString().compareTo(key.toString())!=0)) {
        	if(curr.getKey().toString().compareTo(key.toString())<0) {
        		curr = curr.getRight();
        	}
        	else {
        		curr = curr.getLeft();
        	}
        }
        if(curr.getColor().equals("EXT")) {
        	//System.out.println("Not Found");
        	return curr;
        }
        else {
        	return curr;
        }
    	}
    	else {
    		return new RedBlackNode<T,E>("EXT",null,null);
    	}
    }
    
    /**public void InOrder(RedBlackNode<T,E> curr) {
    	RedBlackNode<T,E> curr = root;
    	
    }**/
}

