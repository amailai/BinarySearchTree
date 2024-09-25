public class BSTBeast
{
  public static void main(String[] args)
  {
    testMyTreeSet();
    //testMyTreeSetRemove();
  }
  
  public static void testMyTreeSet()
  {
    System.out.println("Besting your BST ... ");
    
    Product p = new Product("bravo", 2);
    test(new Product("bravo", 2).compareTo(new Product("alfa", 3)) > 0);
    test(new Product("bravo", 2).compareTo(new Product("charlie", 1)) < 0);
    test(new Product("bravo", 2).compareTo(new Product("bravo", 3)) < 0);
    test(new Product("bravo", 2).compareTo(new Product("bravo", 1)) > 0);
    test(new Product("bravo", 2).compareTo(new Product("bravo", 2)) == 0);
    
    MyTreeSet<Integer> set = new MyTreeSet<Integer>(false);
    test(set.size() == 0);
    test(set.toString().equals("."));
    test(!set.contains(4));
    
    test(set.add(4));
    test(set.size() == 1);
    test(set.toString().equals("4.."));
    test(set.peekMin() == 4);
    test(set.toString().equals("4.."));
    test(!set.contains(3));
    test(set.contains(4));
    test(!set.contains(5));
    test(set.toString().equals("4.."));
    test(set.toString().equals("4.."));
    test(!set.add(4));
    test(set.size() == 1);
    test(set.toString().equals("4.."));
    
    test(set.add(2));
    test(set.size() == 2);
    test(set.toString().equals("42..."));
    test(set.peekMin() == 2);
    test(set.toString().equals("42..."));
    test(!set.contains(1));
    test(set.contains(2));
    test(!set.contains(3));
    test(set.contains(4));
    test(!set.contains(5));
    test(set.toString().equals("42..."));
    test(!set.add(2));
    test(!set.add(4));
    test(set.size() == 2);
    test(set.toString().equals("42..."));
    
    test(set.add(6));
    test(set.size() == 3);
    test(set.toString().equals("42..6.."));
    test(set.peekMin() == 2);
    test(!set.contains(1));
    test(set.contains(2));
    test(!set.contains(3));
    test(set.contains(4));
    test(!set.contains(5));
    test(set.contains(6));
    test(!set.contains(7));
    test(!set.add(2));
    test(!set.add(4));
    test(!set.add(6));
    test(set.size() == 3);
    test(set.toString().equals("42..6.."));
    
    test(set.add(3));
    test(set.add(5));
    test(set.size() == 5);
    test(set.toString().equals("42.3..65..."));
    test(set.peekMin() == 2);
    test(!set.contains(1));
    test(set.contains(2));
    test(set.contains(3));
    test(set.contains(4));
    test(set.contains(5));
    test(set.contains(6));
    test(!set.contains(7));
    test(!set.add(2));
    test(!set.add(3));
    test(!set.add(4));
    test(!set.add(5));
    test(!set.add(6));
    test(set.size() == 5);
    test(set.toString().equals("42.3..65..."));
    
    test(set.add(1));
    test(set.add(7));
    test(set.size() == 7);
    test(set.toString().equals("421..3..65..7.."));
    test(set.peekMin() == 1);
    for (int i = 1; i <= 7; i++)
      test(set.contains(i));
    for (int i = 1; i <= 7; i++)
      test(!set.add(i));
    test(set.size() == 7);
    test(set.toString().equals("421..3..65..7.."));
    
    MyTreeSet<String> set2 = new MyTreeSet<String>(false);
    String s = "xqeklhandspcmyvtiowg";
    for (int i = 0; i < s.length(); i++)
      test(set2.add(s.substring(i, i + 1)));
    test(set2.size() == s.length());
    test(set2.toString().equals("xqea.dc...khg..i..l.nm..po...s.vt..w..y.."));
    test(set2.peekMin().equals("a"));
    for (char ch = 'a'; ch <= 'z'; ch++)
      test(set2.contains("" + ch) == s.contains("" + ch));
    for (char ch = 'a'; ch <= 'z'; ch++)
    {
      if (s.contains("" + ch))
        test(!set2.add("" + ch));
    }
    test(set2.size() == s.length());
    test(set2.toString().equals("xqea.dc...khg..i..l.nm..po...s.vt..w..y.."));

    int[] a = new int[1000000];
    for (int i = 0; i < a.length; i++)
      a[i] = i;
    for (int i = 0; i < a.length; i++)
    {
      int r = (int)(Math.random() * (a.length - i) + i);
      int temp = a[i];
      a[i] = a[r];
      a[r] = temp;
    }
    set = new MyTreeSet<Integer>(false);
    System.out.print("add      ... ");
    long start = System.currentTimeMillis();
    for (int i = 0; i < a.length; i++)
      test(set.add(a[i]));
    for (int i = 0; i < a.length; i++)
      test(!set.add(a[i]));
    int elapsed = (int)(System.currentTimeMillis() - start);
    System.out.println(a.length + " in " + elapsed + " ms");
    System.out.print("size     ... ");
    start = System.currentTimeMillis();
    for (int i = 0; i < a.length; i++)
      test(set.size() == a.length);
    elapsed = (int)(System.currentTimeMillis() - start);
    System.out.println(a.length + " in " + elapsed + " ms");
    System.out.print("peekMin  ... ");
    start = System.currentTimeMillis();
    for (int i = 0; i < a.length; i++)
      test(set.contains(i));
    elapsed = (int)(System.currentTimeMillis() - start);
    System.out.println(a.length + " in " + elapsed + " ms");
    System.out.print("contains ... ");
    start = System.currentTimeMillis();
    for (int i = 0; i < a.length; i++)
      test(set.contains(i));
    elapsed = (int)(System.currentTimeMillis() - start);
    System.out.println(a.length + " in " + elapsed + " ms");
    
    System.out.println("... MyTreeSet PASSED!");
  }
  
  public static void test(boolean b)
  {
    if (!b)
      throw new RuntimeException("did not pass");
  }
    
  public static void testMyTreeSetRemove()
  {
    System.out.println("Testing MyTreeSet remove ... ");
    
    MyTreeSet<Integer> set = new MyTreeSet<Integer>(true);
    int[] a = {19, 7, 2, 1, 3, 5, 4, 6, 10, 9, 8, 15, 14, 12, 11, 13, 17, 16, 18,
      23, 22, 20, 21, 29, 25, 24, 26, 28, 27};
    for (int obj : a)
      test(set.add(obj));
    test(set.size() == 29);
    test(set.toString().equals("19721..3.54..6..1098...15141211..13...1716..18..232220.21...292524..26.2827...."));
    
    test(!set.remove(30));
    test(set.size() == 29);
    test(set.toString().equals("19721..3.54..6..1098...15141211..13...1716..18..232220.21...292524..26.2827...."));
    
    test(set.remove(1));
    test(set.size() == 28);
    test(set.toString().equals("1972.3.54..6..1098...15141211..13...1716..18..232220.21...292524..26.2827...."));
    
    test(set.remove(3));
    test(set.size() == 27);
    test(set.toString().equals("1972.54..6..1098...15141211..13...1716..18..232220.21...292524..26.2827...."));

    test(set.remove(9));
    test(set.size() == 26);
    test(set.toString().equals("1972.54..6..108..15141211..13...1716..18..232220.21...292524..26.2827...."));
    
    test(set.remove(15));
    test(set.size() == 25);
    test(set.toString().equals("1972.54..6..108..141211..13..1716..18..232220.21...292524..26.2827...."));
    
    test(set.remove(22));
    test(set.size() == 24);
    test(set.toString().equals("1972.54..6..108..141211..13..1716..18..232120...292524..26.2827...."));
    
    test(set.remove(29));
    test(set.size() == 23);
    test(set.toString().equals("1972.54..6..108..141211..13..1716..18..232120...282524..26.27..."));
    
    test(set.remove(19));
    test(set.size() == 22);
    test(set.toString().equals("1872.54..6..108..141211..13..1716...232120...282524..26.27..."));
    
    test(set.remove(18));
    test(set.size() == 21);
    test(set.toString().equals("1772.54..6..108..141211..13..16..232120...282524..26.27..."));
    
    test(set.remove(17));
    test(set.size() == 20);
    test(set.toString().equals("1672.54..6..108..141211..13...232120...282524..26.27..."));
    
    test(set.remove(16));
    test(set.size() == 19);
    test(set.toString().equals("1472.54..6..108..1211..13..232120...282524..26.27..."));
    
    test(set.remove(14));
    test(set.size() == 18);
    test(set.toString().equals("1372.54..6..108..1211...232120...282524..26.27..."));
    
    test(set.remove(13));
    test(set.size() == 17);
    test(set.toString().equals("1272.54..6..108..11..232120...282524..26.27..."));
    
    test(set.remove(12));
    test(set.size() == 16);
    test(set.toString().equals("1172.54..6..108...232120...282524..26.27..."));
    
    test(set.remove(11));
    test(set.size() == 15);
    test(set.toString().equals("1072.54..6..8..232120...282524..26.27..."));
    
    test(set.remove(10));
    test(set.size() == 14);
    test(set.toString().equals("872.54..6...232120...282524..26.27..."));
    
    test(set.remove(8));
    test(set.size() == 13);
    test(set.toString().equals("72.54..6..232120...282524..26.27..."));
    
    test(set.remove(7));
    test(set.size() == 12);
    test(set.toString().equals("62.54...232120...282524..26.27..."));
    
    test(set.remove(6));
    test(set.size() == 11);
    test(set.toString().equals("52.4..232120...282524..26.27..."));
    
    test(set.remove(5));
    test(set.size() == 10);
    test(set.toString().equals("42..232120...282524..26.27..."));
    
    test(set.remove(4));
    test(set.size() == 9);
    test(set.toString().equals("2.232120...282524..26.27..."));
    
    test(set.remove(2));
    test(set.size() == 8);
    test(set.toString().equals("232120...282524..26.27..."));
    
    test(set.remove(23));
    test(set.size() == 7);
    test(set.toString().equals("2120..282524..26.27..."));
    
    test(set.remove(21));
    test(set.size() == 6);
    test(set.toString().equals("20.282524..26.27..."));
    
    test(set.remove(20));
    test(set.size() == 5);
    test(set.toString().equals("282524..26.27..."));
    
    test(set.remove(28));
    test(set.size() == 4);
    test(set.toString().equals("272524..26..."));
    
    test(set.remove(27));
    test(set.size() == 3);
    test(set.toString().equals("262524...."));
    
    test(set.remove(26));
    test(set.size() == 2);
    test(set.toString().equals("2524..."));
    
    test(set.remove(25));
    test(set.size() == 1);
    test(set.toString().equals("24.."));
    
    test(set.remove(24));
    test(set.size() == 0);
    test(set.toString().equals("."));
    
    test(!set.remove(24));
    test(set.size() == 0);
    test(set.toString().equals("."));

    System.out.println("... MyTreeSet remove PASSED!");
  }
}