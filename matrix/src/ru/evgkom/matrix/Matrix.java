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
        for (int i = 0; i < n; i++) {
            array[i] = new Vector(m);
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

    public Vector getVectorString(int i) {
        return array[i];
    }

    public void setVectorString(Vector vector, int i) {
        array[i] = new Vector(vector);
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