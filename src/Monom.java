import java.lang.management.MonitorInfo;

public class Monom {
    /* expressions : ax^b*sinx^c*cosx^d */
    double a;
    int b, c, d;

    public Monom() { a = b = c = d = 0; }
    public Monom(double new_a, int new_b, int new_c, int new_d)
    {
        a = new_a;
        b = new_b;
        c = new_c;
        d = new_d;
    }
    public Monom(Monom m)
    {
        a = m.a;
        b = m.b;
        c = m.c;
        d = m.d;
    }
    public Polynomial derivative()
    {
        Polynomial der_polynomial = new Polynomial(3);

        if (a != 0 & b != 0 & c != 0 & d != 0) {

            /*according to differential formula, this produces 3 monomes */

            Monom monom0 = new Monom(a * b, b-1, d, c);
            Monom monom1 = new Monom(a * d, b, c+1, d-1);
            Monom monom2 = new Monom(a * c, b, d+1, c-1);

            der_polynomial.monomes[0] = monom0;
            der_polynomial.monomes[1] = monom1;
            der_polynomial.monomes[2] = monom2;

            return der_polynomial;



        }

        return der_polynomial;
    }

}
