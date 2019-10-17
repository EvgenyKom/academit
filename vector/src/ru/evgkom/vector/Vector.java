package ru.evgkom.vector;

import java.util.Arrays;

public class Vector {
    private double[] array;

    private double[] getArray() {
        return array;
    }

    private void setArray(double[] array) {
        this.array = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }
    }

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Vector dimensionality must be > 0");
        }

        this.array = new double[n];
    }

    public Vector(Vector vector) {
        this.setArray(vector.array);
    }

    public Vector(double[] array) {
        this.setArray(array);
    }

    public Vector(int n, double[] array) throws IllegalArgumentException {
        if (n < array.length) {
            throw new IllegalArgumentException("Vector dimensionality must be > than array length");
        }

        this.array = new double[n];

        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }
    }

    public int getSize() {
        return array.length;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{ " + array[0]);

        for (int i = 1; i <= array.length; i++) {
            if (i == array.length) {
                s.append(" }");

                break;
            }

            s.append(", ").append(array[i]);
        }

        return s.toString();
    }

    public void addition(Vector vector) {
        if (array.length < vector.array.length) {
            double[] tempArray = new double[vector.array.length];

            for (int i = 0; i < array.length; i++) {
                tempArray[i] = array[i];
            }

            array = tempArray;
        }

        if (array.length > vector.array.length) {
            double[] tempArray = new double[array.length];

            for (int i = 0; i < vector.array.length; i++) {
                tempArray[i] = vector.array[i];
            }

            vector.array = tempArray;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] + vector.getArray()[i];
        }
    }

    public void subtraction(Vector vector) {
        if (array.length < vector.array.length) {
            double[] tempArray = new double[vector.array.length];

            for (int i = 0; i < array.length; i++) {
                tempArray[i] = array[i];
            }

            array = tempArray;
        }

        if (array.length > vector.array.length) {
            double[] tempArray = new double[array.length];

            for (int i = 0; i < vector.array.length; i++) {
                tempArray[i] = vector.array[i];
            }

            vector.array = tempArray;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] - vector.getArray()[i];
        }
    }

    public void scalarMultiplication(double scalar) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * scalar;
        }
    }

    public void reverse() {
        for (int i = 0; i < array.length; i++) {
            array[i] = -array[i];
        }
    }

    public double getLength() {
        double sum = 0;

        for (double d : array) {
            sum = sum + Math.pow(d, 2);
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int i) {
        return array[i];
    }

    public void setComponent(double number, int i) {
        array[i] = number;
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
        return this.getSize() == vector.getSize() &&
                Arrays.equals(array, vector.array);
    }

    @Override
    public int hashCode() {
        final int prime = 7;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(array);
        return hash;
    }

    public static Vector getaAdition(Vector vector1, Vector vector2) {
        if (vector1.array.length < vector2.array.length) {
            double[] tempArray = new double[vector2.array.length];

            for (int i = 0; i < vector1.array.length; i++) {
                tempArray[i] = vector1.array[i];
            }

            vector1.array = tempArray;
        }

        if (vector1.array.length > vector2.array.length) {
            double[] tempArray = new double[vector1.array.length];

            for (int i = 0; i < vector2.array.length; i++) {
                tempArray[i] = vector2.array[i];
            }

            vector2.array = tempArray;
        }

        Vector tempVector = new Vector(vector1);
        tempVector.addition(vector2);

        return tempVector;
    }

    public static Vector getSubtraction(Vector vector1, Vector vector2) {
        Vector tempVector = new Vector(vector1);
        tempVector.subtraction(vector2);

        return tempVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        if (vector1.array.length < vector2.array.length) {
            double[] tempArray = new double[vector2.array.length];

            for (int i = 0; i < vector1.array.length; i++) {
                tempArray[i] = vector1.array[i];
            }

            vector1.array = tempArray;
        }

        if (vector1.array.length > vector2.array.length) {
            double[] tempArray = new double[vector1.array.length];

            for (int i = 0; i < vector2.array.length; i++) {
                tempArray[i] = vector2.array[i];
            }

            vector2.array = tempArray;
        }

        double sum = 0;

        for (int i = 0; i < vector1.array.length; i++) {
            sum = sum + vector1.array[i] * vector2.array[i];
        }

        return sum;
    }
}