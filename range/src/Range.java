public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getRangeLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }
}