package ru.evgkom.main;

import ru.evgkom.matrix.Matrix;
import ru.evgkom.vector.Vector;

public class Main {
    public static void main(String[] args) {

        double[][] array1 = {{2, 4, 5}, {-6, 0, 2}, {1, -2, 0}};
        double[][] array2 = {{2, 2, 5}, {-2, 1, 2}, {1, -7, 1}};
        Vector[] vectors = {new Vector(2), new Vector(3), new Vector(2)};
        double[] array3 = {5, 5};

        Vector vector = new Vector(array3);

        Matrix matrix1 = new Matrix(5, 6);
        Matrix matrix2 = new Matrix(array2);
        Matrix matrix3 = new Matrix(array1);
        Matrix matrix4 = new Matrix(vectors);
        Matrix matrix5 = new Matrix(matrix2);

        System.out.println("Матрица 1 : " + matrix1.toString());
        System.out.println("Матрица 2 : " + matrix2.toString());
        System.out.println("Матрица 3 : " + matrix3.toString());
        System.out.println("Матрица 4 : " + matrix4.toString());
        System.out.println("Матрица 5 : " + matrix5.toString());

        System.out.println("Размер матрицы 1 : " + matrix1.getSize());

        System.out.println("Строка по индексу 2 в матрице 3 : " + matrix3.getVectorRow(2).toString());
        matrix3.setVectorRow(1, vector);
        System.out.println("Матрица 3 после изменения строки : " + matrix3.toString());

        System.out.println("Столбец по индексу 1 в матрице 3 : " + matrix3.getVectorColumn(1).toString());

        matrix3.transpose();
        System.out.println("Транспонированная матрица 3 : " + matrix3.toString());

        matrix3.multiplyByScalar(-2);
        System.out.println("Матрица 3, умноженная на скаляр '-2' : " + matrix3.toString());

        System.out.println("Определитель матрицы 5: " + matrix5.getDeterminant());

        matrix3.add(matrix5);
        System.out.println("К матрице 3 прибавить матрицу 5" + matrix3.toString());

        matrix5.subtract(matrix3);
        System.out.println("От матрицы 5 отнять матрицу 3" + matrix5.toString());

        matrix5.multiplyByVector(matrix5.getVectorRow(2));
        System.out.println("Матрица 3, умноженная на вектор с индексом '-2' в матрице 5 : " + matrix5.toString());

        System.out.println("Сумма матриц 3 и 4" + Matrix.getAddition(matrix3, matrix4).toString());

        System.out.println("Разность матриц 2 и 3" + Matrix.getSubtraction(matrix2, matrix3).toString());

        System.out.println("Произведение матриц 2 и 3" + Matrix.getProduct(matrix2, matrix3).toString());
    }
}
