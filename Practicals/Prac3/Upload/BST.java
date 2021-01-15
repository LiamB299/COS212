@SuppressWarnings("unchecked")
public class BST<T extends Comparable<? super T>> {
    
	protected BSTNode<T> root = null;
	protected static int count = 0;

	public BST() 
	{
    	}
    
	public void clear() 
	{
		root = null;
	}

	public String inorder(BSTNode<T> node) 
	{
		Boolean verbose = true;
		if (node != null) {
			String result = "";
			result += inorder(node.left);
			if (verbose) {
				result += node.toString()+"\n";
			} else {
				result += node.element.toString() + " ";
			}
			result += inorder(node.right);
			return result;
		}
		return "";
	}

	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	public boolean isEmpty() 
	{
            return root==null;
            //Your code goes here
	}

	public T minValue() 
	{
            if(root==null) {
                return null;
            }
            
            BSTNode<T> hold = root;
            while(hold.left!=null) {
                hold = hold.left;
            }
            return hold.element;
		//Your code goes here
	}

	public T maxValue() 
	{
            if(root==null) {
                return null;
            }
            
            BSTNode<T> hold = root;
            while(hold.right!=null) {
                hold = hold.right;
            }
            return hold.element;
		//Your code goes here
	}

	public void printPostorder() 
	{
            postorder(root);
            System.out.print('\n');
	}

	public void insert(T element) 
	{
            if(root==null) {
                root = new BSTNode<>(element);
            }
            if(search(element)==element) {
                return;
            }
            
            BSTNode<T> hold = root;
            boolean bend = false;
            
            while(bend!=true) {
                if(hold.element.compareTo(element)<0) {
                    if(hold.right!=null){
                        hold=hold.right;
                    }
                    else bend = true;
                }
                if(hold.element.compareTo(element)>0) {
                    if(hold.left!=null){
                        hold=hold.left;
                    }
                    else bend=true;
                }
            }
            
            if(hold.element.compareTo(element)>0) {
                hold.left = new BSTNode<>(element);
            }
            else hold.right = new BSTNode<>(element);
            
		//Your code goes here
	}

	public boolean deleteByCopy(T element) 
	{
           /* BSTNode<T> hold = root;
            BSTNode<T> prev = null;
            boolean bend = false, bfound =false;
            
            while(bend!=true && bfound!=true) {
                if(hold.element.compareTo(element)<0) {
                    if(hold.right!=null){
                        prev = hold;
                        hold=hold.right;
                    }
                    else bend = true;
                }
                if(hold.element.compareTo(element)>0) {
                    if(hold.left!=null){
                        prev = hold;
                        hold=hold.left;
                    }
                    else bend=true;
                }
                if(element==hold.element) {
                    bfound=true;
                }
            }
            if(bend==true &&bfound==false) {
                return false;
            }
            else {
                BSTNode<T> replace = hold;
                if(hold.left!=null){
                        hold = hold.left;
                    }
                if(prev==null) {
                    while(hold.right!=null && hold.right.right!=null) {
                       hold.element = hold.right.element;
                       hold = hold.right;
                    }//while
                    T val = hold.right.element;
                    hold.right = null;
                    replace.element = val;  
                } //if  
                else if(hold.right==null) {
                    if(hold.left!=null)
                }//else
            }
            return true;   
		//Your code goes here */
            boolean found=false;
            BSTNode<T> node, p=root, prev=null;
            while(p!=null && !p.element.equals(element)) {
                prev = p;
                if(element.compareTo(p.element)<0) 
                    p = p.left;
                else p = p.right;
            }
            node = p;
            if(p.element==element) 
                found=true;
            if (p!=null && p.element ==element) {
                if(node.right==null)
                    node = node.left;
                else if(node.left ==null)
                    node = node.right;
                else {
                    BSTNode<T> tmp = node.left;
                    BSTNode<T> previous = node;
                    while(tmp.right!=null) {
                        previous = tmp;
                        tmp = tmp.right;
                    }
                    node.element = tmp.element;
                    
                    if(previous ==node) 
                        previous.left = tmp.left;
                    else previous.right = tmp.left;
                }
                if(p==root) 
                    root=node;
                else if(prev.left==p)
                    prev.left = node;
                else prev.right = node;
            }
                        return found;
                            
}
    

	public T search(T element) 
	{
            if(root==null) {
                return null;
            }
            
            BSTNode<T> hold = root;
            while(true) {
                if(hold.element.compareTo(element)<0) {
                    if(hold.right!=null){
                        hold=hold.right;
                    }
                    else return null;
                }
                if(hold.element.compareTo(element)>0) {
                    if(hold.left!=null){
                        hold=hold.left;
                    }
                    else return null;
                }
                if(hold.element==element) {
                    return hold.element;
                }
            }
		//Your code goes here
	}
        
        public void postorder(BSTNode<T> yeet) {
            if(yeet!=null) {
                postorder(yeet.left);
                postorder(yeet.right);
                System.out.print(yeet.element+ " ");
                /*if(yeet.right==null) {
                    System.out.print(" ");
                    return;
                }
                if(yeet.left==null) {
                    System.out.print(" ");
                    return;
                }*/
            }
            
        
        
        }
}