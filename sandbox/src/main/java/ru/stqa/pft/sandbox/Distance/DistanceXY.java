package ru.stqa.pft.sandbox.Distance;

public class DistanceXY {
  public static void main(String[] args) {
    Point p1 = new Point(1,2);
    Point p2 = new Point(5,7);

    System.out.println("Расстояние между точками с координатами");
    System.out.println("х = ( " + p1.x + ";" + p1.y + " )");
    System.out.println("y = ( " + p2.x + ";" + p2.y + " )");
    System.out.println("равно " + p1.distance(p2));
  }
}
