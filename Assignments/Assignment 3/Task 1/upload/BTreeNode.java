@SuppressWarnings("unchecked")
class BTreeNode<T extends Comparable<T>> {
	boolean leaf;
	int keyTally;
	Comparable<T> keys[];
	BTreeNode<T> references[];
	int m; //minimum no. of children required to be in a leaf
	static int level = 0;
        BTreeNode<T> root=null;
	// Constructor for BTreeNode class
	public BTreeNode(int order, boolean leaf1)
	{
    		// Copy the given order and leaf property
		m = order;
    		leaf = leaf1;
 
    		// Allocate memory for maximum number of possible keys
    		// and child pointers
    		keys =  new Comparable[2*m-1];
    		references = new BTreeNode[2*m];
 
    		// Initialize the number of keys as 0
    		keyTally = 0;
	}

	// Function to print all nodes in a subtree rooted with this node
	public void print(BTreeNode<T> node)
	{
		level++;
		if (node != null) {
			System.out.print("Level " + level + " ");
			node.printKeys();
			System.out.println();

			// If this node is not leaf, then 
        		// print all the subtrees rooted with this node.
        		if (!node.leaf)
			{	
				for (int j = 0; j < 2*m; j++)
    				{
        				this.print(node.references[j]);
    				}
			}
		}
		level--;
	}

	// A utility function to print all the keys in this node
	private void printKeys()
	{
		System.out.print("[");
    		for (int i = 0; i < this.keyTally; i++)
    		{
        		System.out.print(" " + this.keys[i]);
    		}
 		System.out.print("]");
	}

	// A utility function to give a string representation of this node
        @Override
	public String toString() {
		String out = "[";
		for (int i = 1; i < (this.keyTally); i++)
			out += keys[i-1] + ",";
		out += keys[keyTally-1] + "] ";
		return out;
	}

	// Function to insert the given key in tree rooted with this node
	public BTreeNode<T> insert(T key)
	{
        	// If root is full, then tree grows in height
        	if (this.keyTally == 2*m-1)
        	{
            		// Create a new root
            		BTreeNode<T> s = new BTreeNode<T>(m, false);
 
			// Make the current root a child of the new root
            		s.references[0] = this;
 
            		// Split the current root and move 1 key to the new root
            		s.split(0, this);
 
            		// Decide which of the two children is going to have new key
            		int i = 0;
            		if (s.keys[0].compareTo(key) < 0)
                		i++;
            		s.references[i].insertNotFull(key);
 
            		// Change root
			return s;
        	}
        	else  // If root is not full, call insertNotFull for root
		{
            		this.insertNotFull(key);
			return this;
		}
	}

	// A utility function to insert a new key in this node
	// The node must be non-full when this function is called
	private void insertNotFull(T key)
	{
    		// Initialize index as index of rightmost element
    		int i = this.keyTally-1;
 
    		// If this is a leaf node
    		if (this.leaf)
    		{
        		// The following loop does two things
        		// a) Finds the location of new key to be inserted
        		// b) Moves all greater keys to one place ahead
        		while (i >= 0 && this.keys[i].compareTo(key) > 0)
        		{
            			this.keys[i+1] = this.keys[i];
            			i--;
        		}
 
        		// Insert the new key at found location
        		this.keys[i+1] = key;
        		this.keyTally = this.keyTally+1;
    		}
    		else // If this node is not leaf
    		{
        		// Find the child which is going to have the new key
        		while (i >= 0 && this.keys[i].compareTo(key) > 0)
            		i--;
 
        		// See if the found child is full
        		if (references[i+1].keyTally == 2*m-1)
        		{
            			// If the child is full, then split it
            			this.split(i+1, this.references[i+1]);
 
            			// After split, the middle key of references[i] goes up and
            			// references[i] is split into two. Check which of the two
            			// is going to have the new key.
            			if (this.keys[i+1].compareTo(key) < 0)
                			i++;
        		}
        		this.references[i+1].insertNotFull(key);
    		}
	}

	// A utility function to split the child y of this node
	// Node y must be full when this function is called
	private void split(int i, BTreeNode<T> y)
	{
    		// Create a new node which is going to store m-1 keys
    		// of y
    		BTreeNode<T> z = new BTreeNode(y.m, y.leaf);
    		z.keyTally = m - 1;
 
    		// Copy the last (m-1) keys of y to z
    		for (int j = 0; j < m-1; j++)
        		z.keys[j] = y.keys[j+m];
 
    		// Copy the last m children of y to z
    		if (!y.leaf)
    		{
        		for (int j = 0; j < m; j++)
			{
            			z.references[j] = y.references[j+m];
				y.references[j+m] = null;
			}
    		}
 
    		// Reduce the number of keys in y
    		y.keyTally = m - 1;
 
    		// Since this node is going to have a new child,
    		// create space of new child
    		for (int j = this.keyTally; j >= i+1; j--)
        		this.references[j+1] = this.references[j];
 
    		// Link the new child to this node
    		this.references[i+1] = z;
 
    		// A key of y will move to this node. Find location of
    		// new key and move all greater keys one space ahead
    		for (int j = this.keyTally-1; j >= i; j--)
        		this.keys[j+1] = this.keys[j];
 
    		// Copy the middle key of y to this node
    		this.keys[i] = y.keys[m-1];
 
    		// Increment count of keys in this node
    		this.keyTally = this.keyTally + 1;
	}
//==============================================================================
	////// You should not change any code above this line //////

	////// Implement the functions below this line //////

	// A function to delete the given key from the sub-tree rooted with this node 
	public BTreeNode<T> delete(T key) 
	{ 
            root= this; 
            
            if(this==null)
                return null;
            
            if(this.leaf==true) { //only root
                for(int i=0; i<this.keyTally; i++) {
                    if(this.keys[i].compareTo(key)>0)
                        return this;
                    else if(this.keys[i].equals(key)) {
                        if(i==this.keyTally-1) { 
                            if(this.keyTally==1) {
                                root = null;
                                return root;
                            }
                            this.keyTally--;
                            this.keys[i] =null;
                            return this;
                        }
                        else { //somewhere in the middle
                            for(int j=i; j<this.keyTally-1; j++) {
                                this.keys[j]= this.keys[j+1];
                            }
                            this.keys[this.keyTally-1] = null;
                            this.keyTally--;
                            return this;
                        }
                    }
                }
                return root;
            }
//==============================================================================
            //find key & redist keys on way down
            BTreeNode<T> hold = this;
            boolean bfound=false, bdown=false;
            int i=0;
            while(hold!=null && bfound==false) {
                
                if(hold.keyTally==m-1 && hold!=root) { //minimum
                    BTreeNode<T> par = FindPar(hold, key);
                    int j = WhereIsChild(par, hold);
                    if(j==0) {
                        if(par.references[j+1].keyTally>m-1) {
                            hold = OverflowRedist(par, hold, par.references[j+1]);
                        }
                        else {
                            hold = MergeLeaves(par, hold, par.references[1], key);
                        }
                    }
                    else if(j==par.keyTally) {
                        if(par.references[j-1].keyTally>m-1) 
                            hold =  OverflowRedist(par, par.references[j-1], hold);
                        else
                            hold = MergeLeaves(par, par.references[j-1], hold, key);
                        
                    }
                    else {
                        if(par.references[j-1].keyTally>m-1)
                            hold = OverflowRedist(par, par.references[j-1], hold);
                        else if(par.references[j+1].keyTally>m-1)
                            hold = OverflowRedist(par, hold, par.references[j+1]);
                        else { //merge!
                            hold = MergeLeaves(par,  hold, par.references[j+1], key);
                        }
                    }
                    
                    if(hold.references[0]==null) {
                        if(FindPar(hold, key)==null)
                            return root.delete(key);
                    }
                        
                }
            //==================================================================    
                for(i=0; i<hold.keyTally && bfound==false ; i++) {
                    if(hold.keys[i].equals(key)) {
                        bfound =true;
                        break;
                    }
                    else if(hold.keys[i].compareTo(key)>0) {
                        if(i==0) {
                            hold = hold.references[0];
                            bdown=true;
                            break;
                        }
                        else {
                            hold = hold.references[i];
                            bdown=true;
                            break;
                        }
                    }
                }
                if(hold==null)
                    return root;
                
                if(bfound==false && i==hold.keyTally && bdown==false) {
                    hold = hold.references[hold.keyTally];
                }
                
                bdown=false;
                
            }
//==============================================================================            
            if(hold==null || bfound==false)
                return root;
            else {
                if(hold.leaf==true) {
                    DeleteFromLeaf(hold, key, i);
                    return root;
                }    
//==============================================================================                
                else { //internal node shifts- delete by copy and redist. 
                    DeleteFromInternal(hold, key, i);
                    return root;
                }
            }
	}
//==============================================================================        
        public BTreeNode<T> FindPar(BTreeNode<T> child, T key) {
            BTreeNode<T> hold = root;
            int i;
            if(child==root)
                return null;
            boolean bfound=false;
            while(hold!=null && bfound==false) {
                for(i=0; i<hold.keyTally; i++) {
                    if(hold.keys[i].compareTo(key)>0) {
                        if(i==0) {
                            if(hold.references[0]==child)
                                return hold;
                            else {
                                hold = hold.references[0];
                                i=-1;
                            }
                        }
                        else {
                            if(hold.references[i]==child)
                                return hold;
                            else {
                                hold = hold.references[i];
                                i=-1;
                                //hold.Children();
                            }
                        }
                    }
                }
                if(hold.references[hold.keyTally]==child)
                    return hold;
                else
                    hold = hold.references[hold.keyTally];
            }
            return hold;
        }
//==============================================================================
        
        public BTreeNode<T> OverflowRedist(BTreeNode<T> par, BTreeNode<T> left, BTreeNode<T> right) {
            int imid, itot = left.keyTally + 1 + right.keyTally;
            if(itot%2>0)
                imid=(itot/2);
            else 
                imid = itot/2;
            
            if(right.keyTally==1)
                imid-=1;
                
            
            BTreeNode<T> leftnew= new BTreeNode<>(m, left.leaf), rightnew= new BTreeNode<>(m, right.leaf); 
            BTreeNode<T> links[] = new BTreeNode[4*m];
            int f=WhereIsChild(par, left);
            Comparable<T> array[];
            array = new Comparable[m*2+1];
            
            if(left.keyTally!=0)
                System.arraycopy(left.keys, 0, array, 0, left.keyTally);
            
            array[left.keyTally] = par.keys[f];
            System.arraycopy(right.keys, 0, array, left.keyTally+1, right.keyTally);
            
            par.keys[f] = array[imid];
            par.references[f]= leftnew;
            par.references[f+1] = rightnew;
            //right.Children();
            //left.Children();
            System.arraycopy(array, 0, leftnew.keys, 0, imid);
            leftnew.keyTally = imid;
            rightnew.keyTally = itot-imid-1;
            System.arraycopy(array, imid+1, rightnew.keys, 0, itot-imid-1);
            if(left.leaf==false) {
                System.arraycopy(left.references, 0, links, 0, left.keyTally+1);
                System.arraycopy(right.references, 0, links, left.keyTally+1, right.keyTally+1);
                System.arraycopy(links, 0, leftnew.references, 0, leftnew.keyTally+1);
                System.arraycopy(links, leftnew.keyTally+1, rightnew.references, 0, rightnew.keyTally+1);   
            }
            //leftnew.Children();
            //rightnew.Children();
            return par;
        }
        
//==============================================================================

        public void DeleteFromLeaf(BTreeNode<T> hold, T key, int iloc) {
            if(hold.keyTally>m-1) { //has enough keys for no cross redist.
                        for(int j=iloc; j<hold.keyTally-1; j++) {
                                    hold.keys[j]= hold.keys[j+1];
                                }
                                hold.keys[hold.keyTally-1] = null;
                                hold.keyTally--;
                    }
//==============================================================================                    
                    else { //redist across leaves or merge
                        //first delete key
                        for(int j=iloc; iloc<hold.keyTally-1; j++) {
                                    hold.keys[j]= hold.keys[j+1];
                                }
                                hold.keys[hold.keyTally-1] = null;
                                hold.keyTally--;
                        //=========================================
                        
                        //boolean bred = false;
                        BTreeNode<T> par = FindPar(hold, key);
                        int j=0;
                        while(par.references[j]!=hold)
                            j++;
                        if(j==0) { //leftmost child leaf 
                            if(par.references[j+1].keyTally>m+1) { //use right sibling for cross redist
                                OverflowRedist(par, hold, par.references[j+1]);
                            }
                            else { //merge with right 
                                MergeLeaves(par, hold, par.references[j+1], key);
                            }
                        }
                        else if(j==par.keyTally) { //right most child
                            if(par.references[j-1].keyTally>m) {
                                OverflowRedist(par, par.references[j-1], hold);
                            }
                            else { //merge with left
                                MergeLeaves(par,par.references[j-1], hold, key);
                            }
                        }
                        else {
                            if(par.references[j-1].keyTally>m) { //redist with left
                                OverflowRedist(par, par.references[j-1], hold);
                            }
                            else if(par.references[j+1].keyTally>m) { //redist with right
                                OverflowRedist(par, hold, par.references[j+1]);
                            }
                            else { //merge with left
                                MergeLeaves(par, par.references[j-1], hold, key);
                            }
                                
                        }
                    }    
        }
//==============================================================================

        
        public BTreeNode<T> MergeLeaves(BTreeNode<T> par, BTreeNode<T> left, BTreeNode<T> right, T key) { //interate up after deleting to check underflow
            Comparable<T> ele[] = new Comparable[m*2-1];
            System.arraycopy(left.keys, 0, ele, 0, left.keyTally);
            ele[left.keyTally] = par.keys[WhereIsChild(par, left)];
            System.arraycopy(right.keys, 0, ele, left.keyTally+1, right.keyTally);
            
            BTreeNode<T> cont = new BTreeNode<>(m, left.leaf);
            cont.keyTally = left.keyTally+1+right.keyTally;
            if(left.leaf==false) {
                BTreeNode<T> links[] = new BTreeNode[2*m];
                System.arraycopy(left.references, 0, links, 0, left.keyTally+1);
                System.arraycopy(right.references, 0, links, left.keyTally+1, right.keyTally+1);
                System.arraycopy(links, 0, cont.references, 0, left.keyTally+right.keyTally+2);
                System.arraycopy(ele, 0, cont.keys, 0, m*2-1);
            }
            System.arraycopy(ele, 0, cont.keys, 0, m*2-1);
            
//==============================================================================
            
            int loc = WhereIsChild(par, left);
            par.references[loc] = cont;
            if(loc!=par.keyTally-1) { //shift other keys left
                for(int i=loc; i<par.keyTally-1; i++)
                    par.keys[i] = par.keys[i+1];
                par.keys[par.keyTally-1] = null;
                for(int i=loc+1; i<par.keyTally; i++)
                    par.references[i] = par.references[i+1];
                par.references[par.keyTally]=null;
                //par.keyTally--;
                //par.Children();
            //cont.Children();
            }
            else {
                par.references[loc+1] = null;
            }
            par.keyTally--;
            //cont.Children();
            for(int l=cont.keyTally+1; l<2*m; l++)
                cont.references[l]=null;
            
            if(par==root && par.keyTally==0 && cont.references[0]!=null) {
                root = cont;
                root.leaf=false;
                return root;
            }
            else if(par==root && par.keyTally==0 && cont.references[0]==null) {
                root = cont;
                root.leaf=true;
                return root;
            }
            
//==============================================================================

            if(par.keyTally<m-1 && par!=root) { //check whether redist or another merge and recursively call until root or no rule broken 
                BTreeNode<T> gran = FindPar(par, key);
                int j = WhereIsChild(gran, par);
                if(j==0) { //leftmost child leaf 
                            if(gran.references[j+1].keyTally>m+1) { //use right sibling for cross redist
                                OverflowRedist(gran, par, gran.references[j+1]);
                            }
                            else { //merge with right 
                                MergeLeaves(gran, par, gran.references[j+1], key);
                            }
                        }
                        else if(j==gran.keyTally) { //right most child
                            if(gran.references[j-1].keyTally>m) {
                                OverflowRedist(gran, gran.references[j-1], par);
                            }
                            else { //merge with left
                                MergeLeaves(gran, gran.references[j-1], par, key);
                            }
                        }
                        else {
                            if(gran.references[j-1].keyTally>m) { //redist with left
                                OverflowRedist(gran, gran.references[j-1], par);
                            }
                            else if(gran.references[j+1].keyTally>m) { //redist with right
                                OverflowRedist(gran, par, gran.references[j+1]);
                            }
                            else { //merge with right
                                MergeLeaves(par, par.references[j-1], par, key);
                            }     
                        }  
            }
            return cont;
        }
//==============================================================================        
        public void DeleteFromInternal(BTreeNode<T> hold, T key, int iloc) {
            //find predeccesor
            BTreeNode<T> find = hold;
            int ifind;
            while(find.leaf==false || find.keyTally==m-1) {
                if(find.keyTally==m-1 && find!=root) { //minimum
                    BTreeNode<T> par = FindPar(find, (T)(find.keys[0]));
                    int j = WhereIsChild(par, find);
                    if(j==0) {
                        if(par.references[j+1].keyTally>m-1) {
                            find = OverflowRedist(par, find, par.references[j+1]);
                        }
                        else {
                            find = MergeLeaves(par, find, par.references[1], key);
                        }
                    }
                    else if(j==par.keyTally) {
                        if(par.references[j-1].keyTally>m-1) 
                            OverflowRedist(par, par.references[j-1], find);
                        else
                            find = MergeLeaves(par, par.references[j-1], find, key);
                        
                    }
                    else {
                        if(par.references[j-1].keyTally>m-1)
                            find = OverflowRedist(par, par.references[j-1], find);
                        else if(par.references[j+1].keyTally>m-1)
                            find = OverflowRedist(par, find, par.references[j+1]);
                        else { //merge!
                                find = MergeLeaves(par, find, par.references[j+1], key);
                        }
                    }
                    
                    if(find.references[0]==null) {
                        if(FindPar(find, key)==null)
                            return;
                        else
                            break;
                    }
                }
                
            //==================================================================
                ifind=0;
                if(find.NodeCont(key)==true)
                    hold = find;
                while(find.keys[ifind].compareTo(key)<0 && ifind<find.keyTally-1)
                    ifind++;
                if(find.keys[ifind].compareTo(key)<0)
                    find = find.references[ifind+1];
                else 
                    find = find.references[ifind]; 
            }
            
            //==================================================================
            ifind= find.keyTally-1;
            BTreeNode<T> par = FindPar(find, (T)(find.keys[ifind]));  
            if(find.NodeCont(key)==true && find.leaf==true) { //moves into leaf node
                int j=0;
                while(find.keys[j].equals(key)==false)
                    j++;
                DeleteFromLeaf(find, key, j);
            }
            else {
                
                  
            hold.keys[hold.Locate(key)] = find.keys[ifind];   
            
            find.keys[ifind]=null;        
            find.keyTally--;
            
            }
            
            //==================================================================
                int j = WhereIsChild(par, find);
                if(find.keyTally<m-1) {
                    if(j==0) { //leftmost child leaf 
                                if(par.references[j+1].keyTally>m-1) { //use right sibling for cross redist
                                    OverflowRedist(par, find, par.references[j+1]);
                                }
                                else { //merge with right 
                                    MergeLeaves(par, find, par.references[j+1], key);
                                }
                            }
                            else if(j==par.keyTally) { //right most child
                                if(par.references[j-1].keyTally>m-1) {
                                    OverflowRedist(par, par.references[j-1], find);
                                }
                                else { //merge with left
                                    MergeLeaves(par,par.references[j-1], find, key);
                                }
                            }
                            else {
                                if(par.references[j-1].keyTally>m-1) { //redist with left
                                    OverflowRedist(par, par.references[j-1], find);
                                }
                                else if(par.references[j+1].keyTally>m-1) { //redist with right
                                    OverflowRedist(par, find, par.references[j+1]);
                                }
                                else { //merge with left
                                    MergeLeaves(par, par.references[j-1], find, key);
                                }
                            }
                }
        }
//==============================================================================    
        
        
        
        @SuppressWarnings("empty-statement")
        public int WhereIsChild(BTreeNode<T> par, BTreeNode<T> child) {
            int ifind;
            for(ifind=0; par.references[ifind]!=child; ifind++);
            return ifind;
        }
        
        public void Children() {
            if(this==null)
                return;
            System.out.println("Children of Node: "+this.toString());
            for(int i=0; i<this.keyTally; i++)
                if(this.references[i]==null)
                    System.out.println("Child: "+i+" is null");
                else
                    System.out.println(this.references[i].toString());
            System.out.println(this.references[this.keyTally].toString());
        }
        
        public boolean NodeCont(T key) {
            for(int i=0; i<this.keyTally; i++)
                if(this.keys[i].equals(key))
                    return true;
            return false;
        }
        
        public int Locate(T key) {
            for(int i=0; i<this.keyTally; i++) {
                if(this.keys[i].equals(key))
                    return i;
            } 
            return 0;
        }
                

}