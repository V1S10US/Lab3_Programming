package com.company;
import java.util.Scanner;
public class Polynomial {
    private Scanner in = new Scanner(System.in);
    private int size;//кол-во элентов массива
    private int i;//текущее слагаемое
    private Struct[] mas;//массив для слагаемых
    public Polynomial(){
        mas = new Struct[100];
        size = 0;
        mas[0] = new Struct(0,0,0,0);

    }
    public void Add(double c, int x, int y, int z) {
        Struct e = new Struct(c, x, y, z);
        mas[size]=e;
        size ++;
    }
    public void Clear() {
        size = 0;
        mas[0] = new Struct(0,0,0,0);
    }
    public void Input (){
        Polynomial l = new Polynomial();
        System.out.println(" Введите коэффициенты и стпени перед x, cos(x), sin(x)");
        System.out.println(" Ввод будет продолжаться, пока коэффициент не равен 0");
        double c;
        int x,y,z;
        c = in.nextInt();
        while (c !=0) {
            x = in.nextInt();
            y = in.nextInt();
            z = in.nextInt();
            this.Add(c, x, y, z);
            c = in.nextInt();
        }
    }
    public int quantity()  {
        return size;
    }
    public double value_c () {
        return mas[i].getCoefficient();
    }
    public int value_x() {
        return mas[i].getXdegree();
    }
    public int value_y(){
        return mas[i].getYdegree();
    }
    public int value_z() {
        return mas[i].getZdegree();
    }
    public void begin()  {
        i = 0;
    }
    public void next() {
        i++;
    }
    public boolean flag(){
        if (i < size)
            return true;
        else
            return false;
    }

    public void Output (){
        Polynomial l =this;
        l.begin();
        if ((l.quantity() == 1) && (l.value_c() == 0))
            System.out.print("0");
        else {
            while (l.flag()) {
                if (l.value_c() != 0) {
                    if ((l.value_x() == 0) && (l.value_y() == 0) && (l.value_z() == 0)) {
                        System.out.print( l.value_c());
                    }
                    else {
                        if (l.value_c() != 1) {
                            System.out.print( l.value_c());
                            if (l.value_x() != 0)
                                System.out.print("*");
                        }
                        if (l.value_x() != 0) {
                            if (l.value_x() == 1) {
                                System.out.print("x");
                            }
                            else
                                System.out.print("x^" + l.value_x());
                            if (l.value_y() != 0)
                                System.out.print("*");
                        }
                        if (l.value_y() != 0) {
                            if (l.value_y() == 1) {
                                System.out.print( "sinx");
                            }
                            else  System.out.print( "sinx^" + l.value_y());
                            if (l.value_z() != 0)
                                System.out.print( "*");
                        }
                        if (l.value_z() != 0) {
                            if (l.value_z() == 1) {
                                System.out.print("cosx");
                            }
                            else
                                System.out.print("cosx^" + l.value_z());
                        }
                    }
                    l.next();
                    if (l.mas[i]!= null && l.value_c() > 0) {
                        System.out.print( "+");
                    }
                }
            }
        }
        System.out.println();
    }
    public Polynomial differentiation() {
        Polynomial P1 = new Polynomial();
        double c;
        int x, y, z;
        for (int i = 0; i < size; i++) {
            if (( mas[i].getXdegree() == 0) && (mas[i].getYdegree() == 0) && (mas[i].getZdegree() == 0))
            {

                P1.Add(0, 0, 0, 0);
            }
            else {
                c = mas[i].getCoefficient();
                x = mas[i].getXdegree();
                y = mas[i].getYdegree();
                z = mas[i].getZdegree();
                if (mas[i].getXdegree() != 0 || mas[i].getYdegree() != 0 || mas[i].getZdegree() != 0) {
                    if (mas[i].getXdegree() != 0) {
                        c *= mas[i].getXdegree();
                        x--;
                        P1.Add(c, x, y, z);
                    }
                    if (mas[i].getYdegree() != 0) {
                        c = mas[i].getCoefficient();
                        x = mas[i].getXdegree();
                        c *= mas[i].getYdegree();
                        z++;
                        y--;
                        P1.Add(c, x, y, z);
                    }
                    if (mas[i].getZdegree() != 0) {
                        c = mas[i].getCoefficient();
                        x = mas[i].getXdegree();
                        y = mas[i].getYdegree();
                        z = mas[i].getZdegree();
                        c *= mas[i].getZdegree();
                        y++;
                        c = -c;
                        z--;
                        P1.Add(c, x, y, z);
                    }
                }
            }
        }
        return P1;
    }
    Polynomial multi (Polynomial n) {
        double k = mas[0].getCoefficient();
        int x = mas[0].getXdegree();
        int y = mas[0].getYdegree();
        int z = mas[0].getZdegree();
        size = n.size;
        for (int i = 0; i < size; i++) {
            mas[i] = new Struct(0, 0 ,0 , 0);
            mas[i].changeCoefficient(k * n.mas[i].getCoefficient());
            mas[i].changeXdegree(x + n.mas[i].getXdegree());
            mas[i].changeYdegree(y + n.mas[i].getYdegree());
            mas[i].changeZdegree(z + n.mas[i].getZdegree());
        }
        return this;
    }
    Polynomial difference (Polynomial n) {
        boolean flag = false;
        for (int i = 0; i < n.size; i++) {
            for (int j = 0; j < size; j++){
                if ((mas[j].getXdegree() ==n.mas[i].getXdegree())&&(mas[j].getYdegree() == n.mas[i].getYdegree())&&(mas[j].getZdegree() == n.mas[i].getZdegree()))
                {
                    mas[j].changeCoefficient(mas[j].getCoefficient() - n.mas[i].getCoefficient());
                    flag = true;
                    break;
                }}
            if (!flag) {
                size = size + 1;
                mas[size - 1] = n.mas[i];
                mas[size - 1].changeCoefficient((-1) * mas[size - 1].getCoefficient());
            }
            flag = false;
        }
        int j = 0;

        for (int i = 0; i < size; i++)
        {
            if (mas[i].getCoefficient() != 0) {
                mas[j].changeCoefficient(mas[i].getCoefficient());
                mas[j].changeXdegree(mas[i].getXdegree());
                mas[j].changeYdegree(mas[i].getYdegree());
                mas[j].changeZdegree(mas[i].getZdegree());
                j++;
            }
        }
        size = j;
        return this;
    }
    void sort () {
        int imin;
        Struct min = new Struct();

        for (int i = 0; i < size -1 ; i++){
            min.changeCoefficient(mas[i].getCoefficient());
            min.changeXdegree(mas[i].getXdegree());
            min.changeYdegree(mas[i].getYdegree());
            min.changeZdegree(mas[i].getZdegree());
            imin = i;
            for (int j = i + 1; j < size; j++)
                if (kluch1(mas[j], min)){
                    min.changeCoefficient(mas[j].getCoefficient()) ;
                    min.changeXdegree(mas[j].getXdegree());
                    min.changeYdegree(mas[j].getYdegree());
                    min.changeZdegree(mas[j].getZdegree());
                    imin = j;
                }
            mas[imin].changeCoefficient(mas[i].getCoefficient());
            mas[imin].changeXdegree(mas[i].getXdegree());
            mas[imin].changeYdegree(mas[i].getYdegree());
            mas[imin].changeZdegree(mas[i].getZdegree());
            mas[i].changeCoefficient(min.getCoefficient());
            mas[i].changeXdegree(min.getXdegree());
            mas[i].changeYdegree(min.getYdegree());
            mas[i].changeZdegree(min.getZdegree());
        }
        for (int i = 0; i < size -1 ; i++){
            min.changeCoefficient(mas[i].getCoefficient());
            min.changeXdegree(mas[i].getXdegree());
            min.changeYdegree(mas[i].getYdegree());
            min.changeZdegree(mas[i].getZdegree());
            imin = i;
            for (int j = i + 1; j < size; j++)
                if (kluch2(mas[j], min)){
                    min.changeCoefficient(mas[j].getCoefficient()) ;
                    min.changeXdegree(mas[j].getXdegree());
                    min.changeYdegree(mas[j].getYdegree());
                    min.changeZdegree(mas[j].getZdegree());
                    imin = j;
                }
            mas[imin].changeCoefficient(mas[i].getCoefficient());
            mas[imin].changeXdegree(mas[i].getXdegree());
            mas[imin].changeYdegree(mas[i].getYdegree());
            mas[imin].changeZdegree(mas[i].getZdegree());
            mas[i].changeCoefficient(min.getCoefficient());
            mas[i].changeXdegree(min.getXdegree());
            mas[i].changeYdegree(min.getYdegree());
            mas[i].changeZdegree(min.getZdegree());
        }
        for (int i = 0; i < size -1 ; i++){
            min.changeCoefficient(mas[i].getCoefficient());
            min.changeXdegree(mas[i].getXdegree());
            min.changeYdegree(mas[i].getYdegree());
            min.changeZdegree(mas[i].getZdegree());
            imin = i;
            for (int j = i + 1; j < size; j++)
                if (kluch3(mas[j], min)){
                    min.changeCoefficient(mas[j].getCoefficient()) ;
                    min.changeXdegree(mas[j].getXdegree());
                    min.changeYdegree(mas[j].getYdegree());
                    min.changeZdegree(mas[j].getZdegree());
                    imin = j;
                }
            mas[imin].changeCoefficient(mas[i].getCoefficient());
            mas[imin].changeXdegree(mas[i].getXdegree());
            mas[imin].changeYdegree(mas[i].getYdegree());
            mas[imin].changeZdegree(mas[i].getZdegree());
            mas[i].changeCoefficient(min.getCoefficient());
            mas[i].changeXdegree(min.getXdegree());
            mas[i].changeYdegree(min.getYdegree());
            mas[i].changeZdegree(min.getZdegree());
        }

    }
    private boolean kluch1(Struct a, Struct b){
        if ((a.getXdegree() >= b.getXdegree()))
            return true;
        else
            return false;
    }

    boolean kluch2(Struct a, Struct b){
        if ((a.getXdegree() == b.getXdegree()) && (a.getYdegree() >= b.getYdegree()))
            return true;
        else
            return false;
    }

    boolean kluch3(Struct a, Struct b){
        if ((a.getXdegree() == b.getXdegree()) && (a.getYdegree() >= b.getYdegree()) && (a.getZdegree() >= b.getZdegree()))
            return true;
        else
            return false;
    }
    Polynomial equally ( Polynomial n) {
        size = n.size;
        for (int i = 0; i < n.size; i++) {
            mas[i] = new Struct(0,0,0,0);
            mas[i].changeCoefficient(n.mas[i].getCoefficient());
            mas[i].changeXdegree(n.mas[i].getXdegree());
            mas[i].changeYdegree(n.mas[i].getYdegree());
            mas[i].changeZdegree(n.mas[i].getZdegree());
        }
        return this;
    }
    public  Polynomial wholeDivision (Polynomial n) {
        Polynomial divisible = new Polynomial();
        divisible.equally(this);
        Polynomial p = new Polynomial();//частное
        Polynomial u = new Polynomial();
        Polynomial v = new Polynomial();
        Polynomial w = new Polynomial();
        n.sort();
        for (int i = 0; i < size; i++) {
            divisible.sort();
            if ((divisible.mas[0].getCoefficient() != 0) && (divisible.size !=0))
                if ((divisible.mas[0].getXdegree() >= n.mas[0].getXdegree()) && (divisible.mas[0].getYdegree() >= n.mas[0].getYdegree()) && (divisible.mas[0].getZdegree() >= n.mas[0].getZdegree())){
                    if (divisible.mas[0].getCoefficient() / n.mas[0].getCoefficient() != 0) {
                        u.mas[0].changeCoefficient(divisible.mas[0].getCoefficient() / n.mas[0].getCoefficient());
                        u.mas[0].changeXdegree(divisible.mas[0].getXdegree() - n.mas[0].getXdegree());
                        u.mas[0].changeYdegree(divisible.mas[0].getYdegree() - n.mas[0].getYdegree());
                        u.mas[0].changeZdegree(divisible.mas[0].getZdegree() - n.mas[0].getZdegree());
                        p.Add(u.mas[0].getCoefficient(), u.mas[0].getXdegree(), u.mas[0].getYdegree(), u.mas[0].getZdegree());
                        v.equally(u.multi(n));
                        w.equally(divisible);
                        divisible.equally(w.difference(v));

                    }
                } else
                    break;
        }
        return p;
    }
    Polynomial restDivision (Polynomial n) {
        Polynomial divisible = new Polynomial();
        divisible.equally(this);
        Polynomial p = new Polynomial();//частное
        Polynomial u = new Polynomial();
        Polynomial v = new Polynomial();
        Polynomial w = new Polynomial();
        n.sort();

        for (int i = 0; i < size; i++) {
            divisible.sort();
            if ((divisible.mas[0].getCoefficient() != 0)&& (divisible.size !=0)){
                if ((divisible.mas[0].getXdegree() >= n.mas[0].getXdegree()) && (divisible.mas[0].getYdegree() >= n.mas[0].getYdegree()) && (divisible.mas[0].getZdegree() >= n.mas[0].getZdegree()))
                    if (divisible.mas[0].getCoefficient() / n.mas[0].getCoefficient() != 0) {
                        u.mas[0].changeCoefficient(divisible.mas[0].getCoefficient() / n.mas[0].getCoefficient());
                        u.mas[0].changeXdegree(divisible.mas[0].getXdegree() - n.mas[0].getXdegree());
                        u.mas[0].changeYdegree(divisible.mas[0].getYdegree() - n.mas[0].getYdegree());
                        u.mas[0].changeZdegree(divisible.mas[0].getZdegree() - n.mas[0].getZdegree());
                        p.Add(u.mas[0].getCoefficient(), u.mas[0].getXdegree(), u.mas[0].getYdegree(), u.mas[0].getZdegree());
                        v.equally(u.multi(n));
                        w.equally(divisible);
                        divisible.equally(w.difference(v));
                    }
            } else
                break;
        }
        int j = 0;
        for (int i = 0; i < divisible.size; i++)
        {
            if (divisible.mas[i].getCoefficient() != 0) {
                mas[j].changeCoefficient(divisible.mas[i].getCoefficient());
                mas[j].changeXdegree(divisible.mas[i].getXdegree());
                mas[j].changeYdegree(divisible.mas[i].getYdegree());
                mas[j].changeZdegree(divisible.mas[i].getZdegree());
                j++;
            }
        }
        size = j;
        return this;
    }
}
