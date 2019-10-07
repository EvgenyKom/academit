public class Main {
    public static void main(String[] args) {
        Range range = new Range(-5.25, 2.45);

        System.out.println(range.getRangeLength());
        System.out.println(range.isInside(1));
        System.out.println(range.isInside(4.13));
        System.out.println(range.isInside(-7.24));
    }
}