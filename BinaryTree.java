public class BinaryTree
{
  TreeNode root;
  
  public BinaryTree ()
  {
    root = null;
  }
  
  public boolean insert(Listing newListing)
  {
    TreeNodeWrapper p = new TreeNodeWrapper();
    TreeNodeWrapper c = new TreeNodeWrapper();
    TreeNode n = new TreeNode();
    
    if (n == null)
    {
      return false;
    }
    else 
    {
      n.node = newListing.deepCopy();
      n.lc = null;
      n.rc = null;
      
      if (root == null)
      {
        root = n;
      } 
      else 
      {
        findNode(newListing.getKey(), p, c);
        if (newListing.getKey().compareTo(p.get().node.getKey()) < 0)
        {
          p.get().lc = n;
        } 
        else 
        {
          p.get().rc = n;
        }
      }
      return true;
    }
  }
  
  public Listing fetch(String key)
  {
    boolean found;
    TreeNodeWrapper p = new TreeNodeWrapper();
    TreeNodeWrapper c = new TreeNodeWrapper();
    
    found = findNode(key, p, c);
    
    if (found == true)
    {
      return c.get().node.deepCopy();
    } 
    else 
    {
      return null;
    }
  }
  
  public boolean delete(String key){
    boolean found;
    TreeNodeWrapper p = new TreeNodeWrapper();
    TreeNodeWrapper c = new TreeNodeWrapper();
    TreeNode largest;
    TreeNode nextLargest;
    
    found = findNode(key, p, c);
    
    if (found == false)
    {
      return false;
    }
    else 
    {
      //case 1 node has no children
      if (c.get().lc == null && c.get().rc == null) 
      {
        if (p.get().node.compareTo(root.node.getKey()) == 0){
          root = null;
        }
        else if (p.get().lc == c.get())
        {
          p.get().lc = null;
        }
        else 
        {
          p.get().rc = null;
        }
      }
      //case 2 node has one child
      else if (c.get().lc == null || c.get().rc == null)
      {
        if (p.get().lc == c.get())
        {
          if (c.get().lc != null)
          {
            p.get().lc = c.get().lc;
          } 
          else 
          {
            p.get().lc = c.get().rc;
          }
        }
        else 
        {
          if (c.get().lc != null)
          {
            p.get().rc = c.get().lc;
          }
          else 
          {
            p.get().rc = c.get().rc;
          }
        }
      }
      //case 3 node has 2 children
      else 
      {
        nextLargest = c.get().lc;
        largest = nextLargest.rc;
        if (largest != null) 
        {
          while(largest.rc != null)
          {
            nextLargest = largest;
            largest = largest.rc;
          }
          c.get().node = largest.node;
          nextLargest.rc = largest.lc;
        }
        else
        {
          nextLargest.rc = c.get().rc;
          if (p.get().lc == c.get())
          {
            p.get().lc = nextLargest;
          }
          else 
          {
            p.get().rc = nextLargest;
          }
        }
      }
      return true;
    }
  }
  
  public boolean update(String key, Listing newListing)
  {
    if (delete(key) == false){
      return false;
    }
    else if (insert(newListing) == false)
    {
      return false;
    }
    return true;
  }
  
  public boolean findNode(String key, TreeNodeWrapper parent, TreeNodeWrapper child)
  {
    parent.set(root);
    child.set(root);
    if (root == null)
    {
      return false;
    }
    while (child.get() != null)
    {
      if (child.get().node.compareTo(key) == 0)
      {
        return true;
      }
      else 
      {
        parent.set(child.get());
        if (key.compareTo(child.get().node.getKey()) < 0)
        {
          child.set(child.get().lc);
        }
        else 
        {
          child.set(child.get().rc);
        }
      }
    }
    return false;
  }
  
  public void RNLoutputTraversal(TreeNode root)
  {
    if (root.rc != null){
      RNLoutputTraversal(root.rc);
    }
    System.out.println(root.node.toString());
    if (root.lc != null){
      RNLoutputTraversal(root.lc);
    }
  }
  
  public void showAll()
  {
    if (root == null)
    {
      System.out.println("Data Structure is empty");
    }
    else
    {
      RNLoutputTraversal(root);
    }
  }
  
  public class TreeNode
  {
    private Listing node;
    private TreeNode rc;
    private TreeNode lc;
    
    public TreeNode ()
    {
      
    }
  }
  public class TreeNodeWrapper
  {
    TreeNode ref = null;
    
    public TreeNodeWrapper()
    {
      
    }
    
    public TreeNode get()
    {
      return ref;
    }
    
    public void set(TreeNode newNode)
    {
      ref = newNode;
    }
  }
}