public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Range{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }

    public double getRangeLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (from >= range.to || range.from >= to) {
            return null;
        }

        if (from <= range.from && to >= range.to) {
            return new Range(range.from, range.to);
        }

        if (from > range.from && to < range.to) {
            return new Range(from, to);
        }

        if (from > range.from && to > range.to) {
            return new Range(from, range.to);
        }

        return new Range(range.from, to);
    }

    public Range[] getUnion(Range range) {
        if (from > range.to || range.from > to) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        if (from <= range.from && to >= range.to) {
            return new Range[]{new Range(from, to)};
        }

        if (from >= range.from && to <= range.to) {
            return new Range[]{new Range(range.from, range.to)};
        }

        if (from >= range.from && to >= range.to) {
            return new Range[]{new Range(range.from, to)};
        }

        return new Range[]{new Range(from, range.to)};
    }

    public Range[] getDifference(Range range) {
        if (from > range.to || range.from > to) {
            return new Range[]{new Range(from, to)};
        }

        if (from < range.from && to > range.to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        if (from >= range.from && to <= range.to) {
            return new Range[0];
        }

        if (from >= range.from && to > range.to) {
            return new Range[]{new Range(range.to, to)};
        }

        return new Range[]{new Range(from, range.from)};
    }


}