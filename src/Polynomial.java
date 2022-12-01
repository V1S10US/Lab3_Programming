public class Polynomial {

    int MAX_NUM_MONOMS = 20;
    private int num_monoms;
    public Monom[] monomes;

    public Polynomial() {
        num_monoms = MAX_NUM_MONOMS;
        monomes = new Monom[num_monoms];
    }

    public Polynomial(int num_monoms) {
        monomes = new Monom[num_monoms];
    }

    public Polynomial(Polynomial p) {
        num_monoms = p.num_monoms;
        monomes = new Monom[num_monoms];
        for (int i = 0; i < num_monoms; ++i)
            monomes[i] = new Monom(p.monomes[i]);
    }

    public boolean IsNull() {
        for (Monom monom : monomes)
            if (monom.a == 0)
                return true;  // if at least one is null

        return false;  // no zero coefficents
    }

    public void Append(double coef, int x_degree, int sin_degree, int cos_degree) {
        monomes[num_monoms] = new Monom(coef, x_degree, sin_degree, cos_degree);
        num_monoms++;
    }

    public void Delete(int pos) {
        /*  */
        if (pos > 0 | pos < num_monoms) {

            Monom[] new_list = new Monom[num_monoms - 1];

            for (int i = 0; i < num_monoms - 1; ++i) {  // copy initial list but one in pos
                if (i != pos)
                    new_list[i] = monomes[i];
            }
            monomes = new_list;
        } else
            System.out.println("Incorrect pos for deleting");
    }

    public static Polynomial div(Polynomial M, Polynomial N, char symbol) {
        boolean flag = true;

        if (N.IsNull()) {
            System.out.println("Invalid polynome");

        }

        Polynomial div_polynomial = new Polynomial();
        int i = 1;
        int k = 1;
        int max = 0;
        double coef_a = 0;
        int x_degree_a = 0, sin_degree_a = 0, cos_degree_a = 0;
        double coef_b = 0;
        int x_degree_b = 0, sin_degree_b = 0, cos_degree_b = 0;

        while (flag) {
            for (Monom monom : M.monomes) {
                if ((monom.b >= max) | (monom.c > max) | (monom.d > max)) {
                    if (monom.b > max) max = monom.b;
                    if (monom.c > max) max = monom.c;
                    if (monom.d > max) max = monom.d;
                    coef_a = monom.a;
                    x_degree_a = monom.b;
                    sin_degree_a = monom.c;
                    cos_degree_a = monom.d;
                    k = i;
                }
            }

            i = 1;
            k = 1;
            max = 0;

            for (Monom monom : N.monomes) {
                if ((monom.b >= max) | (monom.c > max) | (monom.d > max)) {
                    if (monom.b > max) max = monom.b;
                    if (monom.c > max) max = monom.c;
                    if (monom.d > max) max = monom.d;
                    coef_b = monom.a;
                    x_degree_b = monom.b;
                    sin_degree_b = monom.c;
                    cos_degree_b = monom.d;
                    k = i;
                }
            }

            if ((x_degree_a - x_degree_b < 0) | (sin_degree_a - sin_degree_b < 0) |
                    (cos_degree_a - cos_degree_b < 0))

                flag = false;

            else {

                double coef_c = coef_a / coef_b;
                int x_degree_c = x_degree_a - x_degree_b;
                int sin_degree_c = sin_degree_a - sin_degree_b;
                int cos_degree_c = cos_degree_a - cos_degree_b;
                div_polynomial.Append(coef_c, x_degree_c, sin_degree_c, cos_degree_c);

                Polynomial buf = new Polynomial(N);

                Polynomial d = new Polynomial();

                int iter = 0;
                while (iter < buf.num_monoms) {

                    d.Append(-coef_b * coef_c, x_degree_c + x_degree_b, sin_degree_c + sin_degree_b, cos_degree_c + cos_degree_b);
                    iter++;
                    buf.Delete(k);

                    Monom temp_buf = new Monom();
                    Monom temp_b = new Monom();
                    i = 1;
                    k = 1;
                    max = 0;
                    for (Monom monom : buf.monomes) {
                        if ((monom.b >= max) | (monom.c > max) | (monom.d > max)) {
                            if (monom.b > max) max = monom.b;
                            if (monom.c > max) max = monom.c;
                            if (monom.d > max) max = monom.d;
                            coef_b = monom.a;
                            x_degree_b = monom.b;
                            sin_degree_b = monom.c;
                            cos_degree_b = monom.d;
                            k = i;
                        }
                    }
                }

                for (Monom monom : d.monomes) {
                    M.Append(monom.a, monom.b, monom.c, monom.d);
                }

                if (M.num_monoms == 0) flag = false;


            }
            if (div_polynomial.num_monoms == 0)
                System.out.println("Unable to divide ");
        }
        if (symbol == '/')
            return div_polynomial;
        else if (symbol == '%')
            return M;
        return div_polynomial;
    }

/*
    public static Polynomial mod(Polynomial a, Polynomial b) {
        Polynomial mod_polynomial = new Polynomial();
    }
}
*/