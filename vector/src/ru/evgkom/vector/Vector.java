package ru.evgkom.vector;

import java.util.Arrays;

public class Vector {
    private double[] orderedSet;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Vector dimensionality must be > 0");
        }

        this.orderedSet = new double[n];
    }

    public Vector(Vector vector) {
        orderedSet = Arrays.copyOf(vector.orderedSet, vector.orderedSet.length);
    }

    public Vector(double[] orderedSet) {
        if (orderedSet.length == 0) {
            throw new IllegalArgumentException("OrderedSet length must be > 0");
        }

        this.orderedSet = Arrays.copyOf(orderedSet, orderedSet.length);
    }

    public Vector(int n, double[] orderedSet) {
        if (n <= 0) {
            throw new IllegalArgumentException("Vector dimensionality must be > 0");
        }

        this.orderedSet = Arrays.copyOf(orderedSet, n);
    }

    public int getSize() {
        return orderedSet.length;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{ ");
        s.append(orderedSet[0]);

        for (int i = 1; i <= orderedSet.length; i++) {
            if (i == orderedSet.length) {
                s.append(" }");

                break;
            }

            s.append(", ").append(orderedSet[i]);
        }

        return s.toString();
    }

    public void addVector(Vector vector) {
        double[] tempOrderedSet = Arrays.copyOf(vector.orderedSet, vector.orderedSet.length);

        if (orderedSet.length < vector.orderedSet.length) {
            orderedSet = Arrays.copyOf(orderedSet, vector.orderedSet.length);
        }

        if (orderedSet.length > vector.orderedSet.length) {
            tempOrderedSet = Arrays.copyOf(vector.orderedSet, orderedSet.length);

        }

        for (int i = 0; i < orderedSet.length; i++) {
            orderedSet[i] += tempOrderedSet[i];
        }
    }

    public void subtractVector(Vector vector) {
        double[] tempOrderedSet = Arrays.copyOf(vector.orderedSet, vector.orderedSet.length);

        if (orderedSet.length < vector.orderedSet.length) {
            orderedSet = Arrays.copyOf(orderedSet, vector.orderedSet.length);
        }

        if (orderedSet.length > vector.orderedSet.length) {
            tempOrderedSet = Arrays.copyOf(vector.orderedSet, orderedSet.length);
        }

        for (int i = 0; i < orderedSet.length; i++) {
            orderedSet[i] -= tempOrderedSet[i];
        }
    }

    public void multiplyVectorByScalar(double scalar) {
        for (int i = 0; i < orderedSet.length; i++) {
            orderedSet[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyVectorByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double d : orderedSet) {
            sum += Math.pow(d, 2);
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int i) {
        return orderedSet[i];
    }

    public void setComponent(int i, double number) {
        orderedSet[i] = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        return Arrays.equals(orderedSet, vector.orderedSet);
    }

    @Override
    public int hashCode() {
        final int prime = 7;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(orderedSet);
        return hash;
    }

    public static Vector getAddition(Vector vector1, Vector vector2) {
        Vector tempVector = new Vector(vector1);
        tempVector.addVector(vector2);

        return tempVector;
    }

    public static Vector getSubtraction(Vector vector1, Vector vector2) {
        Vector tempVector = new Vector(vector1);
        tempVector.subtractVector(vector2);

        return tempVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double sum = 0;

        for (int i = 0; i < Math.min(vector1.orderedSet.length, vector2.orderedSet.length); i++) {
            sum += vector1.orderedSet[i] * vector2.orderedSet[i];
        }

        return sum;
    }
}