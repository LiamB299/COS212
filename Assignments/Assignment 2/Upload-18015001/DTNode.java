/*
Complete your details...
Name and Surname: 
Student/staff Number:
*/

public class DTNode<T extends Comparable<? super T>>
{
	/*
	TODO: You must implement a node class which would be appropriate to use with your trees.
	Methods and variables can be added.
	Names of the given variables must not be altered. 
	*/
	
	protected T data;
	protected DTNode<T> left=null, right=null, up=null; // left child and right child
	protected boolean hasLeftThread=false, hasRightThread=false; // flags that indicate whether the left and the right pointers are threads

        public DTNode(T ele) {
            data = ele;
        }
}
