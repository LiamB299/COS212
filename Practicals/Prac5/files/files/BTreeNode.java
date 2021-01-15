@SuppressWarnings("unchecked")
class BTreeNode<T extends Comparable<T>> {
	boolean leaf;
	int keyTally; //number of filled keys
	Comparable<T> keys[]; //data
	BTreeNode<T> references[]; //pointers to children
	int m; //number of references in a Node
	static int level = 0; //From the top

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
	public void print()
	{
		level++;
		if (this != null) {
			System.out.print("Level " + level + " ");
			this.printKeys();
			System.out.println();

			// If this node is not a leaf, then 
        		// print all the subtrees rooted with this node.
        		if (!this.leaf)
			{	
				for (int j = 0; j < 2*m; j++)
    				{
        				if (this.references[j] != null)
						this.references[j].print();
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

	////// You may not change any code above this line //////  

	////// Implement the functions below this line //////
        
                BTreeNode<T> up = null;

	// Function to insert the given key in tree rooted with this node
	public BTreeNode<T> insert(T key) //the function always begins at root
        {
            BTreeNode<T> hold = this, child=null;
            boolean bfound =false;
            int icount= 0;
            
            while(hold.leaf==false) {
                if(hold.keys[icount].compareTo(key)>0) {
                    if(icount>hold.keyTally) {  //reached last node
                        if(hold.keyTally==m*2-1)
                            hold = hold.references[m*2];
                        else
                            hold = hold.references[icount+1];
                    }
                    else {
                        icount++;
                    }
                }
                else {
                    hold = hold.references[icount];
                    icount=0;
                }
            }
            
            if(hold.keyTally==2*m-1) {//full
                
            }
            else { //not full
                icount=0;
                while(hold.keys[icount].compareTo(key)>0) //find position
                    icount++;
                if(hold.keys[icount].compareTo(key)>0) {
                    icount++;
                    hold.keys[icount] = key;
                    hold.keyTally++;
                    return hold;
                }
                else {
                    Comparable<T> pass = hold.keys[icount], pass2;
                    hold.keys[icount] = key;
                    while(icount!=hold.keyTally) {
                        icount++;
                        pass2 = pass;
                        pass = hold.keys[icount];
                        hold.keys[icount] = pass2;
                    }
                    hold.keyTally++;
                    hold.keys[icount+1] = pass;
                }
            }
            
        	// Your code goes here
	}

	// Function to traverse all nodes in a subtree rooted with this node
	public void traverse() {
    		// Your code goes here
	}
}