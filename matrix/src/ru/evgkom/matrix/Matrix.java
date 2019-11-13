package ru.evgkom.matrix;

import ru.evgkom.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IndexOutOfBoundsException("RowCount and columnCount must be > 0.");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length <= 0) {
            throw new IndexOutOfBoundsException("Array length must be > 0.");
        }

        int maxLength = 0;

        for (double[] d : array) {
            if (maxLength < d.length) {
                maxLength = d.length;
            }
        }

        if (maxLength <= 0) {
            throw new IndexOutOfBoundsException("Array length must be > 0.");
        }

        Vector[] tempVectors = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            tempVectors[i] = new Vector(maxLength, array[i]);
        }

        rows = tempVectors;
    }

    public Matrix(Vector[] rows) {
        if (rows.length <= 0) {
            throw new IndexOutOfBoundsException("Array length must be > 0.");
        }

        int maxLength = 0;

        for (Vector v : rows) {
            if (maxLength < v.getSize()) {
                maxLength = v.getSize();
            }
        }

        Vector[] tempVectors = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            tempVectors[i] = new Vector(maxLength);
            tempVectors[i].add(rows[i]);
        }

        this.rows = tempVectors;
    }

    public int getRowsCount() {
        return rows[0].getSize();
    }

    public int getColumnsCount() {
        return rows.length;
    }

    public Vector getRow(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Index must be >= 0.");
        }

        if (i >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Index must be < number of matrix rows.");
        }

        return new Vector(rows[i]);
    }

    public void setRow(int i, Vector vector) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Index must be >= 0.");
        }

        if (vector.getSize() != getRowsCount()) {
            throw new IllegalArgumentException("Vector size should not be changed.");
        }

        if (i >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Index must be < number of matrix rows.");
        }

        rows[i] = new Vector(vector);
    }

    public Vector getColumn(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Index must be >= 0.");
        }

        if (i >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Index must be < number of matrix columns.");
        }

        Vector tempVector = new Vector(getColumnsCount());

        for (int j = 0; j < getColumnsCount(); j++) {
            tempVector.setComponent(j, rows[j].getComponent(i));
        }

        return tempVector;
    }

    public void transpose() {
        Vector[] tempVectors = new Vector[getRowsCount()];

        for (int i = 0; i < getRowsCount(); i++) {
            tempVectors[i] = getColumn(i);
        }

        rows = tempVectors;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector v : rows) {
            v.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (getColumnsCount() != getRowsCount()) {
            throw new IllegalArgumentException("Matrix must be square.");
        }

        if (getColumnsCount() == 1) {
            return rows[0].getComponent(0);
        }

        if (getColumnsCount() == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1) - rows[1].getComponent(0) * rows[0].getComponent(1);
        }

        double totalSum = 0;

        for (int i = 0, j = 0; i < getColumnsCount(); i++) {
            double minorSign = Math.pow(-1, i + 1 + j + 1);
            Matrix tempMatrix = new Matrix(getMinor(i, j));
            totalSum += minorSign * rows[i].getComponent(j) * tempMatrix.getDeterminant();
        }

        return totalSum;
    }

    private Matrix getMinor(int row, int column) {
        Matrix tempMatrix = new Matrix(getColumnsCount() - 1, getRowsCount() - 1);

        int m = 0;

        for (int i = 0; i < getColumnsCount(); i++) {
            int n = 0;
            if (i == row) {
                continue;
            }

            for (int j = 0; j < getRowsCount(); j++) {
                if (j == column) {
                    continue;
                }

                tempMatrix.rows[m].setComponent(n, rows[i].getComponent(j));
                n++;
            }

            m++;
        }

        return tempMatrix;
    }

    public Vector multiplyByVector(Vector vector) {
        if (getRowsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Number of matrix columns must be = vector size.");
        }

        Vector resultVector = new Vector(getColumnsCount());

        for (int i = 0; i < getColumnsCount(); i++) {
            resultVector.setComponent(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        if (matrix.getColumnsCount() != getColumnsCount() || matrix.getRowsCount() != getRowsCount()) {
            throw new IllegalArgumentException("Matrices must be the same size.");
        }

        for (int i = 0; i < matrix.getColumnsCount(); i++) {
            this.rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix.getColumnsCount() != getColumnsCount() || matrix.getRowsCount() != getRowsCount()) {
            throw new IllegalArgumentException("Matrices must be the same size.");
        }

        for (int i = 0; i < matrix.getColumnsCount(); i++) {
            this.rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getAddition(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getColumnsCount() || matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Matrices must be the same size.");
        }

        Matrix tempMatrix = new Matrix(matrix1);
        tempMatrix.add(matrix2);

        return tempMatrix;
    }

    public static Matrix getSubtraction(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getColumnsCount() || matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Matrices must be the same size.");
        }

        Matrix tempMatrix = new Matrix(matrix1);
        tempMatrix.subtract(matrix2);

        return tempMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Matrix1 columnsCount must be = matrix2 rowsCount.");
        }

        Matrix tempMatrix = new Matrix(matrix1.getColumnsCount(), matrix1.getRowsCount());

        for (int i = 0; i < matrix1.getColumnsCount(); i++) {
            for (int j = 0; j < matrix1.getRowsCount(); j++) {
                tempMatrix.rows[i].setComponent(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return tempMatrix;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{ ");
        string.append(rows[0].toString());

        for (int i = 1; i <= getColumnsCount(); i++) {
            if (i == getColumnsCount()) {
                string.append(" }");

                break;
            }
            string.append(", ").append(rows[i].toString());
        }
        return string.toString();
    }
}