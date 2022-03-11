package ru.stqa.pft.sandbox.Distance;

public class DistanceXY {
  public static void main(String[] args) {
    Point p1 = new Point();
    Point p2 = new Point();
    p1.x = 1;
    p1.y = 2;
    p2.x = 5;
    p2.y = 7;

    System.out.println("Расстояние между точками с координатами");
    System.out.println("х = ( " + p1.x + ";" + p1.y + " )");
    System.out.println("y = ( " + p2.x + ";" + p2.y + " )");
    System.out.println("равно " + distance(p1, p2));
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
  }
}
