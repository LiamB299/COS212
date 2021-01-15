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
		//Your code goes here
	}

	public T minValue() 
	{
		//Your code goes here
	}

	public T maxValue() 
	{
		//Your code goes here
	}

	public void printPostorder() 
	{
		//Your code goes here
	}

	public void insert(T element) 
	{
		//Your code goes here
	}

	public boolean deleteByCopy(T element) 
	{
		//Your code goes here
	}

	public T search(T element) 
	{
		//Your code goes here
	}

}