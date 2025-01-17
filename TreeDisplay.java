import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

//a graphical component for displaying the contents of a binary tree.
public class TreeDisplay<E> extends JComponent
{
  //number of pixels between text and edge
  private static final int ARC_PAD = 2;
  
  //the tree being displayed
  private TreeNode<E> root = null;
  
  private JFrame frame;
  
  //creates a frame with a new TreeDisplay component.
  //(constructor returns the TreeDisplay component--not the frame).
  public TreeDisplay()
  {
    //create surrounding frame
    frame = new JFrame("Tree Display");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //add the TreeDisplay component to the frame
    frame.getContentPane().add(this);
    
    //show frame
    frame.pack();
    frame.setVisible(true);
  }
  
  //tells the frame the default size of the tree
  public Dimension getPreferredSize()
  {
    return new Dimension(400, 300);
  }
  
  //called whenever the TreeDisplay must be drawn on the screen
  public void paint(Graphics g)
  {
    Graphics2D g2 = (Graphics2D)g;
    Dimension d = getSize();
    
    //draw white background
    g2.setPaint(Color.white);
    g2.fill(new Rectangle2D.Double(0, 0, d.width, d.height));
    
    int depth = h();
    
    if (root == null)
      //no tree to draw
      return;
    
    //hack to avoid division by zero, if only one level in tree
    if (depth == 1)
      depth = 2;
    
    //compute the size of the text
    FontMetrics font = g2.getFontMetrics();
    TreeNode<E> leftmost = root;
    while (leftmost.getLeft() != null)
      leftmost = leftmost.getLeft();
    TreeNode<E> rightmost = root;
    while (rightmost.getRight() != null)
      rightmost = rightmost.getRight();
    int leftPad = font.stringWidth(leftmost.getValue() + "") / 2;
    int rightPad = font.stringWidth(rightmost.getValue() + "") / 2;
    int textHeight = font.getHeight();
    
    //draw the actual tree
    drawTree(g2, root, leftPad + ARC_PAD,
             d.width - rightPad - ARC_PAD,
             textHeight / 2 + ARC_PAD,
             (d.height - textHeight - 2 * ARC_PAD) / (depth - 1));
  }
  
  //draws the tree, starting from the given node, in the region with x values ranging
  //from minX to maxX, with y value beginning at y, and next level at y + yIncr.
  private void drawTree(Graphics2D g2, TreeNode t, int minX, int maxX, int y, int yIncr)
  {
    //skip if empty
    if (t == null)
      return;
    
    //compute useful coordinates
    int x = (minX + maxX) / 2;
    int nextY = y + yIncr;
    
    //draw black lines
    g2.setPaint(Color.black);
    if (t.getLeft() != null)
    {
      int nextX = (minX + x) / 2;
      g2.draw(new Line2D.Double(x, y, nextX, nextY));
    }
    if (t.getRight() != null)
    {
      int nextX = (x + maxX) / 2;
      g2.draw(new Line2D.Double(x, y, nextX, nextY));
    }
    
    //measure text
    FontMetrics font = g2.getFontMetrics();
    String text = t.getValue() + "";
    int textHeight = font.getHeight();
    int textWidth = font.stringWidth(text);
    
    //draw the box around the node
    Rectangle2D.Double box = new Rectangle2D.Double(x - textWidth / 2 - ARC_PAD, y - textHeight / 2 - ARC_PAD,
                                                    textWidth + 2 * ARC_PAD, textHeight + 2 * ARC_PAD);
    Color c = new Color(187, 224, 227);
    g2.setPaint(c);
    g2.fill(box);
    //draw black border
    g2.setPaint(Color.black);
    g2.draw(box);
    
    //draw text
    g2.drawString(text, x - textWidth / 2, y + textHeight / 2);
    
    //draw children
    drawTree(g2, t.getLeft(), minX, x, nextY, yIncr);
    drawTree(g2, t.getRight(), x, maxX, nextY, yIncr);
  }
  
  //tells the component to switch to displaying the given tree
  public void setRoot(TreeNode<E> root)
  {
    this.root = root;
    
    //signal that the display needs to be redrawn
    repaint();
  }
  
  private int h()
  {
    Stack<TreeNode<E>> nodeStack = new Stack<TreeNode<E>>();
    Stack<Integer> leftStack = new Stack<Integer>();
    Stack<Integer> rightStack = new Stack<Integer>();
    
    nodeStack.push(root);
    leftStack.push(-1);
    rightStack.push(-1);
    
    while (true)
    {
      TreeNode<E> t = nodeStack.peek();
      int left = leftStack.peek();
      int right = rightStack.peek();
      
      if (t == null)
      {
        nodeStack.pop();
        leftStack.pop();
        rightStack.pop();
        int value = 0;
        if (nodeStack.isEmpty())
          return value;
        else if (leftStack.peek() == -1)
        {
          leftStack.pop();
          leftStack.push(value);
        }
        else
        {
          rightStack.pop();
          rightStack.push(value);
        }
      }
      else if (left == -1)
      {
        nodeStack.push(t.getLeft());
        leftStack.push(-1);
        rightStack.push(-1);
      }
      else if (right == -1)
      {
        nodeStack.push(t.getRight());
        leftStack.push(-1);
        rightStack.push(-1);
      }
      else
      {
        nodeStack.pop();
        leftStack.pop();
        rightStack.pop();
        int value = 1 + Math.max(left, right);
        if (nodeStack.isEmpty())
          return value;
        else if (leftStack.peek() == -1)
        {
          leftStack.pop();
          leftStack.push(value);
        }
        else
        {
          rightStack.pop();
          rightStack.push(value);
        }
      }
    }
  }
  
  public void setTitle(String title)
  {
    frame.setTitle(title);
  }
}