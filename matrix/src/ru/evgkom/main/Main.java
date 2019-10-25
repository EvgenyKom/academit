package ru.evgkom.main;

import ru.evgkom.matrix.Matrix;
import ru.evgkom.vector.Vector;

public class Main {
    public static void main(String[] args) {

        double[][] array1 = {{2, 4, 5}, {-6, 0, 2}, {1, -2, 0}};
        double[][] array2 = {{2, 2, 5}, {-2, 1, 2}, {1, -7, 1}};
        Vector[] vectors = {new Vector(5), new Vector(6), new Vector(2)};
        double[] array3 = {5, 5};

        Vector vector = new Vector(array3);

        Matrix matrix1 = new Matrix(5, 6);
        Matrix matrix2 = new Matrix(matrix1);
        Matrix matrix3 = new Matrix(array1);
        Matrix matrix4 = new Matrix(vectors);
        Matrix matrix5 = new Matrix(array2);

        System.out.println(Matrix.getProduct(matrix3, matrix5).toString());

        System.out.println(matrix1.toString());
        System.out.println(matrix2.toString());
        System.out.println(matrix3.toString());
        System.out.println(matrix4.toString());

        System.out.println(matrix1.getSize());

        System.out.println(matrix3.getVectorRow(2).toString());
        matrix3.setVectorRow(1, vector);
        System.out.println(matrix3.toString());

        System.out.println(matrix3.getVectorColumn(1).toString());

        matrix3.transpose();
        System.out.println(matrix3.toString());

        matrix3.multiplyByScalar(-2);
        System.out.println(matrix3.toString());

        System.out.println(matrix5.getDeterminant());

        matrix5.multiplyByVector(matrix5.getVectorRow(2));
        System.out.println(matrix5.toString());

    }
}
