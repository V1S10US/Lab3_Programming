package com.company;

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Polynomial P = new Polynomial();
        Polynomial P1 = new Polynomial();
        Polynomial P2 = new Polynomial();
        boolean flag = true;
        int number;
        while (flag){
            System.out.println("1. Ввод и вывод многочлена P");
            System.out.println("2. Целая часть от деления");
            System.out.println("3. Остаток от деления");
            System.out.println("4. Дифференцирование многочлена P");
            System.out.println("5. Выход из программы");
            System.out.println("Введите номер");
            number = in.nextInt();
            switch (number){
                case (1):
                    P.Input();
                    System.out.print("Многочлен:" );
                    P.Output();
                    P.Clear();

                    break;
                case(2):
                    System.out.println("Ввведите делимое");
                    P.Input();
                    System.out.println("Ввведите делитель");
                    P1.Input();
                    System.out.print("Делимое:" );
                    P.Output();
                    System.out.print("Делитель:" );
                    P1.Output();
                    P1.begin();
                    if (P1.value_c() == 0){
                        System.out.println("Деление на ноль");
                    }
                    else {
                        P2 = P.wholeDivision(P1);
                        System.out.println(" Целая часть");
                        P2.Output();
                    }
                    P.Clear();
                    P1.Clear();
                    P2.Clear();
                    break;
                case(3):
                    System.out.println("Введите делимое");
                    P.Input();
                    System.out.println("Ввведите делитель");
                    P1.Input();
                    System.out.print("Делимое:" );
                    P.Output();
                    System.out.print("Делитель:" );
                    P1.Output();
                    P1.begin();
                    if (P1.value_c() == 0){
                        System.out.println("Деление на ноль");
                    }
                    else {
                        P2 = P.restDivision(P1);
                        System.out.println(" Остаток ");
                        P2.Output();
                    }
                    P.Clear();
                    P1.Clear();
                    P2.Clear();
                    break;
                case(4):
                    System.out.println("Введите многочлен: ");
                    P.Input();
                    System.out.println("Многочлен: " );
                    P.Output();
                    System.out.println("Полученная производная" );
                    P1= P.differentiation();
                    P1.Output();
                    P.Clear();
                    P1.Clear();
                    break;
                case (5):
                    flag = false;
                    break;
                default:
                    System.out.println("то-то пошло не так. Введите номер от 1 до 5");
                    break;
            }
        }
    }

}
