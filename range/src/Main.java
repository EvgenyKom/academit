public class Main {
    public static void main(String[] args) {
        Range range = new Range(-5, 4);
        Range range1 = new Range(2, 3);
        Range range2 = new Range(-8, 0);
        Range range3 = new Range(-5, 2);
        Range range4 = new Range(-8, 4);

        System.out.println(range.getRangeLength());
        System.out.println(range.isInside(1));
        System.out.println(range.isInside(4.13));
        System.out.println(range.isInside(-7.24));

        System.out.println();
        System.out.println(range.getIntersection(range2).toString());
        System.out.println(range1.getIntersection(range4).toString());
        System.out.println(range3.getIntersection(range3).toString());
        System.out.println(range.getIntersection(range3).toString());

        System.out.println();
        for (Range r: range1.getUnion(range3)) {
            System.out.println(r.toString());
        }

        for (Range r: range2.getUnion(range1)) {
            System.out.println(r.toString());
        }

        System.out.println();
        for (Range r: range.getDifference(range2)) {
            System.out.println(r.toString());
        }

        for (Range r: range4.getDifference(range2)) {
            System.out.println(r.toString());
        }

        for (Range r: range.getDifference(range4)) {
            System.out.println(r.toString());
        }
    }
}