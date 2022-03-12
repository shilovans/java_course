package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Distance.Point;

public class PointTests {

  @Test
  public void testPoint() {
    Point p1 = new Point(1,4);
    Point p2 = new Point(3,4);
    Assert.assertEquals(p1.distance(p2), 2.0);
  }

  @Test
  public void testZeroPoint() {
    Point p1 = new Point(0,0);
    Point p2 = new Point(3,4);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }

  @Test
  public void testSamePoints() {
    Point p1 = new Point(5,5);
    Point p2 = new Point(5,5);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test
  public void testNegativePoints() {
    Point p1 = new Point(-3,-4);
    Point p2 = new Point(3,4);
    Assert.assertEquals(p1.distance(p2), 10.0);
  }
}
