public class Triangle implements Shape {
    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;

    private double max(double coordinate1, double coordinate2, double coordinate3) {
        return Math.max(Math.max(coordinate1, coordinate2), coordinate3);
    }

    private double min(double coordinate1, double coordinate2, double coordinate3) {
        return Math.min(Math.min(coordinate1, coordinate2), coordinate3);
    }

    public Triangle(double x1, double x2, double x3, double y1, double y2, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return max(x1, x2, x3) - min(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return max(y1, y2, y3) - min(y1, y2, y3);
    }

    @Override
    public double getArea() {
        double side12 = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        double side23 = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
        double side31 = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));

        return side12 + side23 + side31;
    }

    @Override
    public double getPerimeter() {
        double side12 = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        double side23 = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
        double side31 = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
        double semiPerimeter = (side12 + side23 + side31) / 2;

        return Math.sqrt((semiPerimeter * (semiPerimeter - side12) * (semiPerimeter - side23) * (semiPerimeter - side31)));
    }
}