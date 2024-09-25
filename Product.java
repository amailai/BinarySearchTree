public class Product implements Comparable<Product>
{
  private String model;
  private int version;
  
  public Product(String model, int version)
  {
    this.model = model;
    this.version = version;
  }
  
  public String getModel()
  {
    return model;
  }
  
  public int getVersion()
  {
    return version;
  }
  
  public String toString()
  {
    return model + " " + version;
  }
  
  public boolean equals(Object obj)
  {
    Product other = (Product)obj;
    return model.equals(other.getModel()) &&
      version == other.getVersion();
  }
  
  public int compareTo(Product other)
  {
    if(model.compareTo(other.getModel()) > 0) {
    	return 1;
    }else if(model.compareTo(other.getModel()) < 0) {
    	return -1;
    } else if(version > other.version){
    	return 1;
    }else if(version < other.version){
    	return -1;
    } else {
    	return 0;
    }
  }
}