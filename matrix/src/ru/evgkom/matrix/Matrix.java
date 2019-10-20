package ru.evgkom.matrix;

import ru.evgkom.vector.Vector;

public class Matrix {
    private Vector[] array;

    public Vector[] getArray() {
        return array;
    }

    public void setArray(Vector[] array) {
        this.array = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }
    }

    public Matrix(int n, int m) {
        Vector[] array = new Vector[n];
        int max = Math.max(n, m);

        for (int i = 0; i < max; i++) {
            array[i] = new Vector(max);
        }

        this.array = array;
    }

    public Matrix(Matrix matrix) {
        this.setArray(matrix.getArray());
    }

    public Matrix(double[][] array) {
        Vector[] tempArray = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            tempArray[i] = new Vector(array[i]);
        }

        this.setArray(tempArray);
    }

    public Matrix(Vector[] vectors) {
        Vector[] tempArray = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            tempArray[i] = vectors[i];
        }

        array = tempArray;
    }

    public String getSize() {
        return this.array.length + "x" + this.array[0].getSize();
    }

    public Vector getVectorRow(int i) {
        return array[i];
    }

    public void setVectorRow(Vector vector, int i) {
        array[i] = new Vector(vector);
    }

    public Vector getVectorColumn(int i) {
        Vector tempVector = new Vector(array.length);

        for (int j = 0; j < array.length; j++) {
            tempVector.setComponent(array[j].getComponent(i), j);
        }

        return tempVector;
    }

    public void transpose() {

    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{ " + array[0].toString());

        for (int i = 1; i <= array.length; i++) {
            if (i == array.length) {
                string.append(" }");

                break;
            }
            string.append(", ").append(array[i].toString());
        }
        return string.toString();
    }
}