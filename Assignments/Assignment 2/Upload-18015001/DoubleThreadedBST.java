/*
Complete your details...
Name and Surname: 
Student/staff Number:
*/


public class DoubleThreadedBST<T extends Comparable<? super T>> 
{
	private DTNode<T> root; // the root node of the tree
        public int max;
	/*
	TODO: You must complete each of the methods in this class to create
	your own double threaded BST. You may add any additional methods
	or data fields which you might need to accomplish your task. You
	must NOT change the signatures of any methods given to you with this
	class.
	*/
	
	public DoubleThreadedBST()
	{
		/*
		The default constructor
		*/
            root=null;
            max=0;
	}
	
	public DoubleThreadedBST(DoubleThreadedBST<T> other)
	{
            DTNode<T> hold = other.root;
            this.insert(other.root.data);
            
            if(hold.right==null) {
                hold = hold.left;
            }
            
            while(hold!=null) {
                this.insert(hold.data);
                if(hold.left == null && hold.hasRightThread==true) {
                    if(hold.right.hasRightThread==false) {
                        hold = hold.right.right;
                    }
                    else {
                        while(hold.hasRightThread==true)
                            hold = hold.right;
                        hold = hold.right;
                        //if(hold==root)
                        //    hold = hold.right;
                    }
                }
                else if(hold.hasLeftThread==true) {
                    if(hold.hasRightThread==false)
                        hold = hold.right;
                    else {
                        while(hold.hasRightThread==true)
                            hold = hold.right;
                        hold = hold.right;
                        //if(hold==root)
                        //    hold = hold.right;
                    }
                }
                else if(hold.left==null && hold.hasRightThread==false && hold.right!=null) {
                    hold = hold.right;
                }
                else
                    hold = hold.left; 
            }
		/*
		The copy constructor
		*/
	}
	
	public DoubleThreadedBST<T> clone()
	{
		/*
		The clone method should return a copy/clone
		of this tree.
		*/
            
            DoubleThreadedBST<T> dup = new DoubleThreadedBST<>(this);
            return dup;
	}
	
	public DTNode<T> getRoot()
	{
		/*
		Return the root of the tree.
		*/
		
		return root;
	}
        
        public void setRoot(DTNode<T> node) {
            root = node;
        }
	
	public boolean insert(T element)
	{
		/*
		The element passed in as a parameter should be
		inserted into this tree. Duplicates are not allowed.
		Left and right threads in the corresponding branch 
		must be updated accordingly, as necessary. 
		If the insert was successfull, the method should 
		return true. If the operation was unsuccessfull, 
		the method should return false.
		
		NB: Do not throw an exception.
		*/
		
            
            if (root==null) {
                root = new DTNode<>(element);
                thleft(root);
                thright(root);
                max=1;
                return true;
            }
            DTNode<T> hold = root;
            int icount = 1;
            while(true) { 
                icount++;
                if(hold.data.equals(element))
                    return false;
                //System.out.println(hold.data +"-" +element);
                if(hold.data.compareTo(element)>0) {
                    if(hold.hasLeftThread==false && hold.left!=null) 
                        hold = hold.left;
                    else {
                        hold.left = new DTNode<>(element);
                        hold.left.up = hold;
                        hold.hasLeftThread=false;
                        thleft(hold.left);
                        thright(hold.left);
                        if(icount>max)
                            max = icount;
                        //hold.left.WhereIs();
                        return true;
                    }
                }
                else
                    if(hold.hasRightThread==false && hold.right!=null) 
                        hold = hold.right;
                    else {
                        hold.right = new DTNode<>(element);
                        hold.right.up = hold;
                        hold.hasRightThread=false;
                        thleft(hold.right);
                        thright(hold.right);
                        if(icount>max)
                            max = icount;
                        //hold.right.WhereIs();
                        return true;
                    }
            }
	}
	
	public boolean delete(T element)
	{
		/*
		The element passed in as a parameter should be
		deleted from this tree. If the delete was successfull,
		the method should return true. If the operation was
		unsuccessfull, the method should return false. Eg, if
		the requested element is not found, return false.
		
		You have to implement the mirror case of delete by merging 
		as discussed in the textbook. That is, for a deleted node,
		its right child should replace it in the tree and not its
		left child as in the textbook examples. Relevant left and
		right threads must be updated accordingly.
		
		NB: Do not throw an exception.
		*/
            
            
            
//==============================================================================          
            if(root==null)
                return false;
            
            DTNode<T> hold = root, thr;
            while(hold!=null) { 
                
//==============================================================================       

                if(hold.data.equals(element)) {
                //hold.WhereIs();
//============================================================================== root cases
                    if(hold==root) {
                        if(hold.left == null && hold.right==null) { //only root
                            root=null;
                            return true;
                        }
                        else if(root.left==null) { //no left
                            root = root.right;
                            root.up=null;
                            thr = Succ(hold.right);
                            thr.left=null;
                            thr.hasLeftThread=false;
                            thleft(thr);
                            return true;    
                        } 
                        else if(root.right==null) {
                            root = root.left;
                            root.up=null;
                            thr = Pred(hold.left);
                            thr.right=null;
                            thr.hasRightThread=false;
                            thright(thr);
                            return true;
                        }
                    }
//============================================================================== general leaf cases  
                    if(hold.left==null) { //leftmost leaf
                        if(hold.hasRightThread==false) { //leaf has right child
                            hold.up.left = hold.right;
                            hold.right.up = hold.up;
                            DTNode<T> leftmost = Succ(hold.right);
                            leftmost.left = null;
                            leftmost.hasLeftThread=false;
                        }
                        else
                            hold.up.left = null;
                        return true;
                    }
                    else if(hold.right==null) { //rightmost leaf
                        if(hold.hasLeftThread==false) { //leaf has left child
                            hold.up.right = hold.left;
                            hold.left.up = hold.up;
                            DTNode<T> rightmost = hold.left;
                            while(rightmost.hasRightThread==false)
                                rightmost= rightmost.right;
                            rightmost.right = null;
                            rightmost.hasRightThread=false;
                            
                        }
                        else
                            hold.up.right = null;
                        //hold.up.WhereIs();
                        return true;
                    }
                    else if(hold.hasLeftThread==true && hold.hasRightThread==true) { //general leaf with no children                   
                        if(hold.up.right == hold) {
                            hold.up.right = hold.right;
                            hold.up.hasRightThread=true;
                        }
                        else if(hold.up.left == hold) {
                            hold.up.left = hold.left;
                            hold.up.hasLeftThread=true;
                        }
                    return true;
                    }                       
                    
//==============================================================================//no right child to inherit                             
                    else if(hold.hasRightThread==true) {
                        if(hold.hasLeftThread==false) {
                            if(hold.up.right==hold) {
                                hold.up.right=hold.left;
                                hold.left.up = hold.up;
                            }
                            else {
                                hold.up.left = hold.left;
                                hold.left.up = hold.up;        
                            }
                            hold = hold.left;
                            while(hold.hasRightThread==false)
                                hold = hold.right;
                            hold.right=null;
                            thright(hold);
                            //hold.WhereIs();
                            return true;
                        }
                    }
                    
//==============================================================================//general case
//==============================================================================//change parent                  
//==============================================================================//fix thread- the leftmost and rightmost from it's subtrees would point to it if not null.
                    else {
                        DTNode<T> leftmost=null, rightmost=null;
                        if(hold.hasLeftThread==false) {
                            rightmost = hold.left;
                            while(rightmost.hasRightThread==false)
                                rightmost = rightmost.right;   
                        } 
                        
                        leftmost = hold.right;
                        while(leftmost.hasLeftThread==false)
                            leftmost = leftmost.left;
                        
                        if(hold.hasLeftThread==true) {
                            leftmost.left = hold.left;
                            leftmost.hasLeftThread=true;
                        }
                        else {
                            leftmost.hasLeftThread=false;
                            leftmost.left = hold.left;
                            hold.left.up = leftmost; 
                        }
                        
//                        System.out.println("LeftThread: "+rightmost.data+'\n'+"RightThread: "+leftmost.data);
                        
                        if(hold.up==null) {
                            root = root.right;
                            root.up=null;
                        }
                        else {
                            if(hold.up.right==hold) {
                                hold.up.right = hold.right;
                            }
                            else {
                                hold.up.left = hold.right;
                            }
                            hold.right.up = hold.up;
                        }
                        if(rightmost!=null)
                            if(rightmost.hasRightThread==true)
                                rightmost.right = null;
                        thright(rightmost);
                        
                       // hold.left.WhereIs();
                        //if(rightmost!=null) rightmost.WhereIs();
                        //leftmost.WhereIs();
                        //root.WhereIs();
                        return true;
                    }
                }
            
//==============================================================================//find  

                if(hold.data.compareTo(element)>0)
                    if(hold.hasLeftThread==false) 
                        hold = hold.left;
                    else
                        return false;
                else
                    if(hold.hasRightThread==false) 
                        hold = hold.right;
                    else 
                        return false;
            }
            return false;
        }
		
	public boolean contains(T element)
	{
		/*
		This method searches for the element passed in as a
		parameter. If the element is found, true should be 
		returned. If the element is not in the tree, the
		method should return false.
		*/
            DTNode<T> hold = root;
            while(true) {
                if (hold==null)
                    return false;
                if(hold.data.equals(element)) {
                   // hold.WhereIs();
                    return true;
                }
                else {
                    if(hold.data.compareTo(element)>0 && hold.hasLeftThread==false)
                        hold = hold.left;
                    else if(hold.data.compareTo(element)<0 && hold.hasRightThread==false)
                        hold = hold.right;
                    else
                        return false;
                }   
            }
        }
	
	public String inorderReverse()
	{
		/*
		This method must return a string representation
		of the elements in the tree inorder, right to left. 
		This function must not be recursive. Instead, left 
		threads must be utilised to perform a depth-first 
		inorder traversal.
		
		Note that there are no spaces in the string, and
		the elements are comma-separated.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		
		The following string must be returned:
		
		E,D,C,B,A
		*/
                if(root == null)
                    return "";
            
                DTNode<T> hold = root;
                String sline="";
                
                while(hold.right!=null)
                    hold = hold.right;
                
                while(hold!=null) {
                    sline +=hold.data+",";
                    if(hold.hasLeftThread==false && hold.left!=null) {
                        hold = hold.left;
                        while(hold.hasRightThread==false)
                            hold = hold.right;
                    }
                    else
                        hold = hold.left;   
                }   
                while((sline.charAt(sline.length()-1)==',') || (sline.charAt(sline.length()-1)=='|') ) 
                    sline = sline.substring(0, sline.length()-1);
		return sline;
	}
	
	public String inorderReverseDetailed()
	{
		/*
		This method must return a string representation
		of the elements in the tree inorder, right to left. 
		This function must not be recursive. Instead, left 
		threads must be utilised to perform a depth-first 
		inorder traversal.
		
		Note that there are no spaces in the string, and
		the elements are comma-separated. Additionally,
		whenever a thread is followed during the
		traversal, a pipe symbol should be printed
		instead of a comma.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		
		In this tree, there is a thread linking the left
		branch of node E to node D, and a thread linking
		the left branch of node C to node B.
		
		This means the following string must be returned:
		
		E|D,C|B,A
		*/
            
            
            
            if(root == null)
                    return "";
            
                DTNode<T> hold = root;
                String sline="";
                
                while(hold.right!=null)
                    hold = hold.right;
                
                while(hold!=null) {
                   // hold.WhereIs();
                    if(hold.hasLeftThread==true)
                        sline = sline+ hold.data + "|";
                    else
                        sline = sline+ hold.data + ",";
                    if(hold.hasLeftThread==false && hold.left!=null) {
                        hold = hold.left;
                        while(hold.hasRightThread==false)
                            hold = hold.right;
                    }
                    else
                        hold = hold.left;   
                }   
                
                
                while((sline.charAt(sline.length()-1)==',') || (sline.charAt(sline.length()-1)=='|') ) 
                    sline = sline.substring(0, sline.length()-1);
		return sline;
            
                
                    
               
		
	}
	
	public String preOrder()
	{
		/*
		This method must return a string representation
		of the elements in the tree in preorder, left to right. 
		This function must not be recursive. Instead, right 
		threads must be utilised to perform a depth-first 
		preorder traversal.
		
		Note that there are no spaces in the string, and
		the elements are comma-separated.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		
		The following string must be returned:
		
		B,A,D,C,E
		*/
            
            if(root==null) {
                return"";
            }
            
            String sline="";
            
            DTNode<T> hold = root;
            
            if(hold.right==null) {
                sline+=hold.data+",";
                hold = hold.left;
            }
            
            while(hold!=null) {
                sline += hold.data+",";
                if(hold.left == null && hold.hasRightThread==true) {
                    if(hold.right.hasRightThread==false) {
                        hold = hold.right.right;
                    }
                    else {
                        while(hold.hasRightThread==true)
                            hold = hold.right;
                        hold = hold.right;
                        //if(hold==root)
                        //    hold = hold.right;
                    }
                }
                else if(hold.hasLeftThread==true) {
                    if(hold.hasRightThread==false)  //has a child
                        hold = hold.right;
                    else {
                        while(hold.hasRightThread==true) //go back up
                            hold = hold.right;
                        hold = hold.right;
                        //if(hold==root)
                        //    hold = hold.right;
                    }
                }
                else if(hold.left==null && hold.hasRightThread==false && hold.right!=null) {
                    hold = hold.right;
                }
                else
                    hold = hold.left;  
            }
            //sline += hold.data;
            //if(hold.hasLeftThread==false)
            //    sline += ","+hold.left.data;
            
            while((sline.charAt(sline.length()-1)==',') || (sline.charAt(sline.length()-1)=='|') ) 
                    sline = sline.substring(0, sline.length()-1);
		
            return sline;
	}
	
	public String preorderDetailed()
	{
		/*
		This method must return a string representation
		of the elements in the tree in preorder, right to left. 
		This function must not be recursive. Instead, right 
		threads must be utilised to perform a depth-first 
		preorder traversal (see the last paragraph on page 240
		of the textbook for more detail on this procedure).
		
		Note that there are no spaces in the string, and
		the elements are comma-separated. Additionally,
		whenever a thread is followed during the
		traversal, a pipe symbol should be printed
		instead of a comma.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		
		In this tree, there is a thread linking the right
		branch of node A to node B, and a thread linking
		the right branch of node C to node D.
		
		This means the following string must be returned:
		
		B,A|D,C|E
		*/
            if(root==null) {
                return"";
            }
            
            String sline="";
            
            DTNode<T> hold = root;
            /*
            if(hold.right==null) {
                sline+=hold.data+",";
                hold = hold.left; 
            }
            */
            
            while(hold!=null) {
                sline += hold.data;
                if(hold.left==null && hold.hasRightThread==true) {
                    if(hold.right.hasRightThread==false) {
                        hold = hold.right.right;
                        sline +="|";
                    }
                    else {
                        while(hold.hasRightThread==true) {
                            hold = hold.right;
                            sline +="|";
                        }
                        hold = hold.right;
                        //sline +="|";
                        //if(hold==root)
                        //    hold = hold.right;
                    }
                }
                else if(hold.hasLeftThread==true) {
                    if(hold.hasRightThread==false) {
                        hold = hold.right;
                        sline +=",";
                    }
                    else {
                        while(hold.hasRightThread==true) {
                            hold = hold.right;
                            sline +="|";
                        }
                            
                        hold = hold.right;
                        //sline +="|";
                        //if(hold==root)
                        //    hold = hold.right;
                    }
                }
                else if(hold.left==null && hold.hasRightThread==false) {
                    hold = hold.right;
                    sline+=",";
                }
                else {
                    hold = hold.left;
                    sline +=",";
                }
                    
            }
            //sline += hold.data;
            while((sline.charAt(sline.length()-1)==',')) 
                    sline = sline.substring(0, sline.length()-1);      
		
		return sline;
	}
	
	public int getNumberOfNodes()
	{
		/*
		This method should count and return the number of nodes 
		currently in the tree.
		*/
            
                if(root == null)
                    return 0;
            
                DTNode<T> hold = root;
                int icount = 0;
                
                while(hold.right!=null)
                    hold = hold.right;
                
                while(hold!=null) {
                    icount++;
                    //System.out.println(hold.data);
                    //hold.WhereIs();
                    if(hold.hasLeftThread==false && hold.left!=null) {
                        hold = hold.left;
                        while(hold.hasRightThread==false)
                            hold = hold.right;
                    }
                    else
                        hold = hold.left;   
                }   
                
		return icount;
	}
	
	public int getHeight()
	{
            int newmax = 0;
            if(root==null)
                return newmax;
            else {
            DoubleThreadedBST<T> copy = new DoubleThreadedBST<>(); //duplicate
            DoubleThreadedBST<T> orig = this; //original
            DTNode<T> hold = orig.root;
            copy.insert(orig.root.data);
            newmax = copy.max;
            
            if(hold.right==null) {
                copy.insert(hold.data);
                hold = hold.left;
            }
            
            while(hold!=null) {
                copy.insert(hold.data);
                if(hold.left == null && hold.hasRightThread==true) {
                    if(hold.right.hasRightThread==false) {
                        hold = hold.right.right;
                    }
                    else {
                        while(hold.hasRightThread==true)
                            hold = hold.right;
                        hold = hold.right;
                        //if(hold==root)
                        //    hold = hold.right;
                    }
                }
                else if(hold.hasLeftThread==true) {
                    if(hold.hasRightThread==false)
                        hold = hold.right;
                    else {
                        while(hold.hasRightThread==true)
                            hold = hold.right;
                        hold = hold.right;
                        //if(hold==root)
                        //    hold = hold.right;
                    }
                }
                else if(hold.left==null && hold.hasRightThread==false && hold.right!=null) {
                    hold = hold.right;
                }
                else
                    hold = hold.left;  
            }
            newmax = copy.max;
            }
		/*
		This method should return the height of the tree. The height 
		of an empty tree is 0; the height of a tree with nothing but
		the root is 1.
		*/
            
            return newmax;
	}
        
        public void thleft(DTNode<T> node) { //in order predecessor- creates threads for node passed in
            if(node==null) {
                //System.out.println("Null pointer on left thread pass");
                return;
            }
            if(node.left!=null)
                return;
            
            if(node.up == null)
                return;
            
            DTNode<T> prev = node, hold = node.up;
            if(hold.right==prev) {
                node.left = hold;
               //System.out.println("Node "+node.data+" points left to " +hold.data);
                node.hasLeftThread = true;
                return;
            }
            while(hold.up!=null && hold.left==prev) {
                prev = hold;
                hold = hold.up;
            }
            if(hold.up==null && hold.left==prev) {
                return;
            }
            else {
                node.left = hold;
              //  System.out.println("Node "+node.data+" points left to " +hold.data);
                node.hasLeftThread = true;
            }
        }
        
        public void thright(DTNode<T> node) {
            if(node==null) {
                //System.out.println("Null pointer on right thread pass");
                return;
            }
            
            if(node.right!=null)
                return;
            
            if(node.up==null)
                return;
            
            DTNode<T> prev = node, hold = node.up;
            if(hold.left==prev) {
                node.right = hold;
              ///  System.out.println("Node "+node.data+" points right to " +hold.data);
                node.hasRightThread = true;
                return;
            }
            while(hold.up!=null && hold.right==prev) {
                prev = hold;
                hold = hold.up;
            }
            if(hold.up==null && hold.right==prev) {
                return;
            }
            else {
                node.right = hold;
              //  System.out.println("Node "+node.data+" points right to " +hold.data);
                node.hasRightThread = true;
            }
        }
        
        public DTNode<T> Pred(DTNode<T> hold) {
            if(hold==null)
                return null;
            while(hold.hasRightThread==false)
                hold=hold.right;
            return hold;
        }
        
        public DTNode<T> Succ(DTNode<T> hold) {
            if(hold==null)
                return null;
            while(hold.hasLeftThread==false)
                hold=hold.left;
            return hold;
        }
        
     
}
