import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {(new Circle(2)), (new Rectangle(6, 2.5)), (new Square(4.2)),
                (new Triangle(1, 4, 2, 5, -3, 0)), new Circle(2.5), (new Square(0.5))};

        Shape maxAreaShape = getMaxAreaShape(shapes);
        System.out.println(maxAreaShape.getClass().toString());
        System.out.println("Area = " + maxAreaShape.getArea());

        Shape secondLargestPerimeterShape = getSecondLargestPerimeterShape(shapes);
        System.out.println(secondLargestPerimeterShape.getClass().toString());
        System.out.println("Perimeter = " + secondLargestPerimeterShape.getPerimeter());
    }

    private static Shape getMaxAreaShape(Shape[] shapes) {
        Comparator<Shape> areaComparator = new AreaShapeComparator();

        Arrays.sort(shapes, areaComparator);

        return shapes[shapes.length - 1];
    }

    private static Shape getSecondLargestPerimeterShape(Shape[] shapes) {
        Comparator<Shape> perimeterComparator = new PerimeterShapeComparator();

        Arrays.sort(shapes, perimeterComparator);

        return shapes[shapes.length - 2];
    }
}