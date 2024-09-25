import java.util.*;

public class MyTreeSet<E extends Comparable<E>>
{
  private TreeNode<E> root;
  private int size;
  private TreeDisplay<E> display;
  
  public MyTreeSet(boolean useDisplay)
  {
    root = null;
    size = 0;
    if (useDisplay)
      display = new TreeDisplay<E>();
    else
      display = null;
    updateDisplay();
  }
  
  public int size()
  {
    return size;
  }
  
  //precondition: set is not empty
  public E peekMin()
  {
	  TreeNode<E> a = root;
	  while(a.getLeft() != null) {
		  a = a.getLeft();
	  }
	  return a.getValue();
  }
  
  public boolean contains(E obj)
  {
	  return contains(obj, root);
  }
  
  public boolean contains(E obj, TreeNode<E> a) {
	  if(a == null) {
		  return false;
	  }
	  if(a.getValue().equals(obj)) {
		  return true;
	  } else if(obj.compareTo(a.getValue()) > 0) { 
		return contains(obj, a.getRight());
	  }else if(obj.compareTo(a.getValue()) < 0) {

		return contains(obj, a.getLeft());
	  }
	  return false;
  }
  
  public boolean add(E obj)
  {
	  return add(obj, root);
  }
  
  public boolean add(E obj, TreeNode<E> a)
  {
	  TreeNode<E> b = new TreeNode<E>(obj);
	  if(a == null) {
		  root = b;
		  updateDisplay();
		  size++;
		  return true;
	  }
	  if(a.equals(obj)) {
		  return false;
	  } else if(obj.compareTo(a.getValue()) > 0) {
		  if(a.getRight() == null) {
			  a.setRight(b);
			  updateDisplay();
			  size++;
			  return true;
		  }
		  return add(obj, a.getRight());
	  }else if(obj.compareTo(a.getValue()) < 0) {
			  if(a.getLeft() == null) {
				  a.setLeft(b);
				  updateDisplay();
				  size++;
				  return true;
			  }
			  return add(obj, a.getLeft());
		  }
	  return false;	  
  }
  
  public boolean remove(E obj)
  {
	  return false;
  }
  

  
  private void updateDisplay()
  {
    if (display != null)
    {
      display.setTitle("size:  " + size);
      display.setRoot(root);
    }
  }
  
  public String toString()
  {
    return toString(root);
  }
  
  private String toString(TreeNode<E> t)
  {
    if (t == null)
      return ".";
    else
      return t.getValue() + toString(t.getLeft()) + toString(t.getRight());
  }
}