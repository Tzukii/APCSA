// Test code for Rectangle class

public class TestRectangle
{
  public static void main(String args[])
  {
    Rectangle c1 = new Rectangle(1, 2);
    System.out.println(c1.isSquare());
    c1.quadratize();
    System.out.println(c1.toString());

  }
}
