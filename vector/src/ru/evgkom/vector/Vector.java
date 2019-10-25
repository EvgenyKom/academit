package ru.evgkom.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Vector dimensionality must be > 0");
        }

        this.components = new double[n];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("OrderedSet length must be > 0");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int n, double[] components) {
        if (n <= 0) {
            throw new IllegalArgumentException("Vector dimensionality must be > 0");
        }

        this.components = Arrays.copyOf(components, n);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{ ");
        s.append(components[0]);

        for (int i = 1; i < components.length; i++) {
            s.append(", ").append(components[i]);
        }

        s.append(" }");

        return s.toString();
    }

    public void add(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double d : components) {
            sum += Math.pow(d, 2);
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int i) {
        return components[i];
    }

    public void setComponent(int i, double number) {
        components[i] = number;
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
        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 7;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);
        return hash;
    }

    public static Vector getAddition(Vector vector1, Vector vector2) {
        Vector tempVector = new Vector(vector1);
        tempVector.add(vector2);

        return tempVector;
    }

    public static Vector getSubtraction(Vector vector1, Vector vector2) {
        Vector tempVector = new Vector(vector1);
        tempVector.subtract(vector2);

        return tempVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double sum = 0;
        int min = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < min; i++) {
            sum += vector1.components[i] * vector2.components[i];
        }

        return sum;
    }
}