package ru.evgkom.main;

import ru.evgkom.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array1 = {1, 3, 4, -2, 0, 4};
        double[] array2 = {1, -2, 1, -4, -1, 0};
        double[] array3 = {5, 5};

        Vector vector1 = new Vector(6);
        Vector vector2 = new Vector(array1);
        Vector vector3 = new Vector(vector2);
        Vector vector4 = new Vector(array3);
        Vector vector5 = new Vector(8, array2);

        System.out.println("Вектор 1 : " + vector1.toString());
        System.out.println("Вектор 2 : " + vector2.toString());
        System.out.println("Вектор 3 : " + vector3.toString());
        System.out.println("Вектор 4 : " + vector4.toString());
        System.out.println("Вектор 5 : " + vector5.toString());

        System.out.println("Размерность вектора 5 - " + vector5.getSize());
        vector2.addition(vector4);
        System.out.println("Если ко второму вектору прибавить четвертый, то получится " + vector2.toString());
        vector3.subtraction(vector1);
        System.out.println("Если из третьего вектора вычесть первый, то получится " + vector3.toString());
        vector5.scalarMultiplication(3);
        System.out.println("Если пятый вектор умножить на 3, то получится " + vector5.toString());
        vector4.reverse();
        System.out.println("Развернутый вектор 4 будет " + vector4.toString());
        System.out.println("Длина вектора 1 равна " + vector1.getLength());
        vector1.setComponent(8,3);
        System.out.println("Вектор 1 : " + vector1.toString());
        System.out.println("Компонент 2 вектора 1 равен " + vector1.getComponent(1));
        System.out.println("Равны ли векторы 1 и 5 - " + vector1.equals(vector5));
        System.out.println("Сложение векторов 2 и 3 = " + Vector.getaAdition(vector2,vector3));
        System.out.println("Разность векторов 5 и 4 = " + Vector.getSubtraction(vector5,vector4));
        System.out.println("Сколярное произведение векторов 2 и 4 = " + Vector.getScalarProduct(vector2,vector4));
    }
}