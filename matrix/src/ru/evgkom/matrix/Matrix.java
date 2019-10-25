package ru.evgkom.matrix;

import ru.evgkom.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] vectors;

    public Matrix(int n, int m) {
        vectors = new Vector[n];

        for (int i = 0; i < n; i++) {
            vectors[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        vectors = Arrays.copyOf(matrix.vectors, matrix.vectors.length);
    }

    public Matrix(double[][] array) {
        int maxLength = 0;

        for (double[] d : array) {
            if (maxLength < d.length) {
                maxLength = d.length;
            }
        }

        Vector[] tempVectors = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            tempVectors[i] = new Vector(maxLength, array[i]);
        }

        vectors = tempVectors;
    }

    public Matrix(Vector[] vectors) {
        int maxLength = 0;

        for (Vector v : vectors) {
            if (maxLength < v.getSize()) {
                maxLength = v.getSize();
            }
        }

        Vector[] tempVectors = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            tempVectors[i] = new Vector(maxLength);
            tempVectors[i].add(vectors[i]);
        }

        this.vectors = tempVectors;
    }


    public String getSize() {
        return vectors.length + "x" + vectors[0].getSize();
    }

    public Vector getVectorRow(int i) {
        if (i < 0 || i >= vectors.length) {
            throw new IllegalArgumentException("Index must be >= 0 and < number of matrix rows.");
        }

        return vectors[i];
    }

    public void setVectorRow(int i, Vector vector) {
        if (i < 0 || i >= vectors.length) {
            throw new IllegalArgumentException("Index must be >= 0 and < number of matrix rows.");
        }

        int tempLength = vectors[i].getSize();
        vectors[i] = new Vector(tempLength);
        vectors[i].add(vector);
    }

    public Vector getVectorColumn(int i) {
        if (i < 0 || i >= vectors.length) {
            throw new IllegalArgumentException("Index must be >= 0 and < number of matrix columns.");
        }

        Vector tempVector = new Vector(vectors.length);

        for (int j = 0; j < vectors.length; j++) {
            tempVector.setComponent(j, vectors[j].getComponent(i));
        }

        return tempVector;
    }

    public void transpose() {
        Vector[] tempVectors = new Vector[vectors[0].getSize()];

        for (int i = 0; i < vectors[0].getSize(); i++) {
            tempVectors[i] = this.getVectorColumn(i);
        }

        vectors = tempVectors;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector v : vectors) {
            v.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (vectors.length == 1) {
            return vectors[0].getComponent(0);
        }

        if (vectors.length == 2) {
            return vectors[0].getComponent(0) * vectors[1].getComponent(1) - vectors[1].getComponent(0) * vectors[0].getComponent(1);
        }

        double totalSum = 0;

        for (int i = 0, j = 0; i < vectors.length; i++) {
            double minorSign = Math.pow(-1, i + 1 + j + 1);
            Matrix tempMatrix = new Matrix(getMinor(i, j));
            totalSum += minorSign * vectors[i].getComponent(j) * tempMatrix.getDeterminant();
        }

        return totalSum;
    }

    private Matrix getMinor(int row, int column) {
        Matrix tempMatrix = new Matrix(vectors.length - 1, vectors[0].getSize() - 1);

        int m = 0;

        for (int i = 0; i < vectors.length; i++) {
            int n = 0;
            if (i == row) {
                continue;
            }

            for (int j = 0; j < vectors[0].getSize(); j++) {
                if (j == column) {
                    continue;
                }

                tempMatrix.vectors[m].setComponent(n, vectors[i].getComponent(j));
                n++;
            }

            m++;
        }

        return tempMatrix;
    }

    public void multiplyByVector(Vector vector) {
        if (vectors[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException("Number of matrix columns must be = number of vector rows.");
        }

        Vector resultVector = new Vector(vectors.length);

        for (int i = 0; i < vectors.length; i++) {
            double sum = 0;

            for (int j = 0; j < vectors[i].getSize(); j++) {
                sum += vectors[i].getComponent(j) * vector.getComponent(j);
            }

            resultVector.setComponent(i, sum);
        }

        Vector[] resultVectors = new Vector[1];
        resultVectors[0] = resultVector;
        vectors = resultVectors;
    }

    public void add(Matrix matrix) {
        if (matrix.vectors.length != vectors.length || matrix.vectors[0].getSize() != vectors[0].getSize()) {
            throw new IllegalArgumentException("Matrices must be the same size.");
        }

        for (int i = 0; i < matrix.vectors.length; i++) {
            this.vectors[i].add(matrix.vectors[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix.vectors.length != vectors.length || matrix.vectors[0].getSize() != vectors[0].getSize()) {
            throw new IllegalArgumentException("Matrices must be the same size.");
        }

        for (int i = 0; i < matrix.vectors.length; i++) {
            this.vectors[i].subtract(matrix.vectors[i]);
        }
    }

    public static Matrix getAddition(Matrix matrix1, Matrix matrix2) {
        Matrix tempMatrix = new Matrix(matrix1);
        tempMatrix.add(matrix2);

        return tempMatrix;
    }

    public static Matrix getSubtraction(Matrix matrix1, Matrix matrix2) {
        Matrix tempMatrix = new Matrix(matrix1);
        tempMatrix.subtract(matrix2);

        return tempMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.vectors.length != matrix2.vectors.length || matrix1.vectors[0].getSize() != matrix2.vectors[0].getSize()) {
            throw new IllegalArgumentException("Matrices must be the same size.");
        }

        Matrix tempMatrix = new Matrix(matrix1.vectors.length, matrix1.vectors[0].getSize());

        for (int i = 0; i < matrix1.vectors.length; i++) {
            for (int j = 0; j < matrix1.vectors[0].getSize(); j++) {
                tempMatrix.vectors[i].setComponent(j, Vector.getScalarProduct(matrix1.vectors[i], matrix2.getVectorColumn(j)));
            }
        }

        return tempMatrix;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{ ");
        string.append(vectors[0].toString());

        for (int i = 1; i <= vectors.length; i++) {
            if (i == vectors.length) {
                string.append(" }");

                break;
            }
            string.append(", ").append(vectors[i].toString());
        }
        return string.toString();
    }
}