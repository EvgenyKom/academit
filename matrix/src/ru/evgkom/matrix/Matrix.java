package ru.evgkom.matrix;

import ru.evgkom.vector.Vector;

public class Matrix {
    private Vector[] rowVectors;

    public Matrix(int rowCount, int columnCount) {
        if (rowCount <= 0 || columnCount <= 0) {
            throw new IllegalArgumentException("RowCount and columnCount must be > 0.");
        }

        rowVectors = new Vector[rowCount];

        for (int i = 0; i < rowCount; i++) {
            rowVectors[i] = new Vector(columnCount);
        }
    }

    public Matrix(Matrix matrix) {
        rowVectors = new Vector[matrix.rowVectors.length];

        for (int i = 0; i < matrix.rowVectors.length; i++) {
            rowVectors[i] = new Vector(matrix.rowVectors[i]);
        }
    }

    public Matrix(double[][] array) {
        int maxLength = 0;

        for (double[] d : array) {
            if (maxLength < d.length) {
                maxLength = d.length;
            }
        }

        if (array.length <= 0 || maxLength <= 0) {
            throw new IllegalArgumentException("Array length must be > 0.");
        }

        Vector[] tempVectors = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            tempVectors[i] = new Vector(maxLength, array[i]);
        }

        rowVectors = tempVectors;
    }

    public Matrix(Vector[] rowVectors) {
        int maxLength = 0;

        for (Vector v : rowVectors) {
            if (maxLength < v.getSize()) {
                maxLength = v.getSize();
            }
        }

        Vector[] tempVectors = new Vector[rowVectors.length];

        for (int i = 0; i < rowVectors.length; i++) {
            tempVectors[i] = new Vector(maxLength);
            tempVectors[i].add(rowVectors[i]);
        }

        this.rowVectors = tempVectors;
    }

    public int getRowCount() {
        return rowVectors[0].getSize();
    }

    public int getColumnCount() {
        return rowVectors.length;
    }

    public Vector getRow(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Index must be >= 0.");
        }

        if (i >= rowVectors.length) {
            throw new ArrayIndexOutOfBoundsException("Index must be < number of matrix rows.");
        }

        return new Vector(rowVectors[i]);
    }

    public void setRow(int i, Vector vector) {
        if (vector.getSize() != rowVectors[0].getSize()) {
            throw new IllegalArgumentException("Vector size should not be changed.");
        }

        if (i < 0) {
            throw new IllegalArgumentException("Index must be >= 0.");
        }

        if (i >= rowVectors.length) {
            throw new ArrayIndexOutOfBoundsException("Index must be < number of matrix rows.");
        }

        rowVectors[i] = new Vector(vector);
    }

    public Vector getColumn(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Index must be >= 0.");
        }

        if (i >= rowVectors[0].getSize()) {
            throw new ArrayIndexOutOfBoundsException("Index must be < number of matrix columns.");
        }

        Vector tempVector = new Vector(rowVectors.length);

        for (int j = 0; j < rowVectors.length; j++) {
            tempVector.setComponent(j, rowVectors[j].getComponent(i));
        }

        return tempVector;
    }

    public void transpose() {
        Vector[] tempVectors = new Vector[rowVectors[0].getSize()];

        for (int i = 0; i < rowVectors[0].getSize(); i++) {
            tempVectors[i] = getColumn(i);
        }

        rowVectors = tempVectors;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector v : rowVectors) {
            v.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (rowVectors.length != rowVectors[0].getSize()) {
            throw new IllegalArgumentException("Matrix must be square.");
        }

        if (rowVectors.length == 1) {
            return rowVectors[0].getComponent(0);
        }

        if (rowVectors.length == 2) {
            return rowVectors[0].getComponent(0) * rowVectors[1].getComponent(1) - rowVectors[1].getComponent(0) * rowVectors[0].getComponent(1);
        }

        double totalSum = 0;

        for (int i = 0, j = 0; i < rowVectors.length; i++) {
            double minorSign = Math.pow(-1, i + 1 + j + 1);
            Matrix tempMatrix = new Matrix(getMinor(i, j));
            totalSum += minorSign * rowVectors[i].getComponent(j) * tempMatrix.getDeterminant();
        }

        return totalSum;
    }

    private Matrix getMinor(int row, int column) {
        Matrix tempMatrix = new Matrix(rowVectors.length - 1, rowVectors[0].getSize() - 1);

        int m = 0;

        for (int i = 0; i < rowVectors.length; i++) {
            int n = 0;
            if (i == row) {
                continue;
            }

            for (int j = 0; j < rowVectors[0].getSize(); j++) {
                if (j == column) {
                    continue;
                }

                tempMatrix.rowVectors[m].setComponent(n, rowVectors[i].getComponent(j));
                n++;
            }

            m++;
        }

        return tempMatrix;
    }

    public Vector multiplyByVector(Vector vector) {
        if (rowVectors[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException("Number of matrix columns must be = vector size.");
        }

        Vector resultVector = new Vector(rowVectors.length);

        for (int i = 0; i < rowVectors.length; i++) {
            resultVector.setComponent(i, Vector.getScalarProduct(rowVectors[i], vector));
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        if (matrix.getColumnCount() != getColumnCount() || matrix.getRowCount() != getRowCount()) {
            throw new IllegalArgumentException("Matrices must be the same size.");
        }

        for (int i = 0; i < matrix.rowVectors.length; i++) {
            this.rowVectors[i].add(matrix.rowVectors[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix.getColumnCount() != getColumnCount() || matrix.getRowCount() != getRowCount()) {
            throw new IllegalArgumentException("Matrices must be the same size.");
        }

        for (int i = 0; i < matrix.rowVectors.length; i++) {
            this.rowVectors[i].subtract(matrix.rowVectors[i]);
        }
    }

    public static Matrix getAddition(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnCount() != matrix2.getColumnCount() || matrix1.getRowCount() != matrix2.getRowCount()) {
            throw new IllegalArgumentException("Matrices must be the same size.");
        }

        Matrix tempMatrix = new Matrix(matrix1);
        tempMatrix.add(matrix2);

        return tempMatrix;
    }

    public static Matrix getSubtraction(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnCount() != matrix2.getColumnCount() || matrix1.getRowCount() != matrix2.getRowCount()) {
            throw new IllegalArgumentException("Matrices must be the same size.");
        }

        Matrix tempMatrix = new Matrix(matrix1);
        tempMatrix.subtract(matrix2);

        return tempMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnCount() != matrix2.getColumnCount() || matrix1.getRowCount() != matrix2.getRowCount()) {
            throw new IllegalArgumentException("Matrices must be the same size.");
        }

        Matrix tempMatrix = new Matrix(matrix1.rowVectors.length, matrix1.rowVectors[0].getSize());

        for (int i = 0; i < matrix1.rowVectors.length; i++) {
            for (int j = 0; j < matrix1.rowVectors[0].getSize(); j++) {
                tempMatrix.rowVectors[i].setComponent(j, Vector.getScalarProduct(matrix1.rowVectors[i], matrix2.getColumn(j)));
            }
        }

        return tempMatrix;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{ ");
        string.append(rowVectors[0].toString());

        for (int i = 1; i <= rowVectors.length; i++) {
            if (i == rowVectors.length) {
                string.append(" }");

                break;
            }
            string.append(", ").append(rowVectors[i].toString());
        }
        return string.toString();
    }
}