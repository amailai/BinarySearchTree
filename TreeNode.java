public class TreeNode<E>
{
  private E value;
  private TreeNode<E> left;
  private TreeNode<E> right;
  
  public TreeNode(E value)
  {
    this.value = value;
    left = null;
    right = null;
  }
  
  public E getValue()
  {
    return value;
  }
  
  public TreeNode<E> getLeft()
  {
    return left;
  }
  
  public TreeNode<E> getRight()
  {
    return right;
  }
  
  public void setValue(E value)
  {
    this.value = value;
  }
  
  public void setLeft(TreeNode<E> left)
  {
    this.left = left;
  }
  
  public void setRight(TreeNode<E> right)
  {
    this.right = right;
  }
}