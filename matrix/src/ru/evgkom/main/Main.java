package ru.evgkom.main;

import ru.evgkom.matrix.Matrix;
import ru.evgkom.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[][] array1 = {{2, 4, 5, -4}, {-6, 0, 2, 1}, {1, -2, 0}};
        Vector[] vectors = {new Vector(6), new Vector(2), new Vector(4)};
        double[] array2 = {5, 5};

        Vector vector = new Vector(array2);

        Matrix matrix1 = new Matrix(5, 6);
        Matrix matrix2 = new Matrix(matrix1);
        Matrix matrix3 = new Matrix(array1);
        Matrix matrix4 = new Matrix(vectors);

        System.out.println(matrix1.toString());
        System.out.println(matrix2.toString());
        System.out.println(matrix3.toString());
        System.out.println(matrix4.toString());

        System.out.println(matrix1.getSize());

        System.out.println(matrix3.getVectorString(2).toString());
        matrix3.setVectorString(vector, 1);
        System.out.println(matrix3.toString());

    }
}
