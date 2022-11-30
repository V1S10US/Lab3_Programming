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

    public void Append(double coef, int x_degree, int sin_degree, int cos_degree) {
        monomes[num_monoms] = new Monom(coef, x_degree, sin_degree, cos_degree);
        num_monoms++;
    }

    public static Polynomial div(Polynomial M, Polynomial N) {
        boolean flag = true;
        Polynomial div_polynomial = new Polynomial();
        int i = 1;
        int k = 1;
        int max = 0;
        double coef_a;
        int x_degree_a, sin_degree_a, cos_degree_a;
        double coef_b;
        int x_degree_b, sin_degree_b, cos_degree_b;

        while (flag) {
        for (Monom m : M.monomes) {
            if ((m.b >= max) | (m.c > max) | (m.d > max)) {
                if (m.b > max) max = m.b;
                if (m.c > max) max = m.c;
                if (m.d > max) max = m.d;
                coef_a = m.a;
                x_degree_a = m.b;
                sin_degree_a = m.c;
                cos_degree_a = m.d;
                k = i;
            }
        }

        i = 1;
        k = 1;
        max = 0;

        for (Monom m : N.monomes) {
            if ((m.b >= max) | (m.c > max) | (m.d > max)) {
                if (m.b > max) max = m.b;
                if (m.c > max) max = m.c;
                if (m.d > max) max = m.d;
                coef_b = m.a;
                x_degree_b = m.b;
                sin_degree_b = m.c;
                cos_degree_b = m.d;
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
            div_polynomial.monomes[0] = new Monom(coef_c, x_degree_c, sin_degree_c, cos_degree_c);

            LIST_FLOAT buf (list_b);
            LIST_FLOAT d;
            node<double>*temp_b = buf.head;
            int num = 1;
            while (temp_b != nullptr and buf.size != 0)
            {


                d.ADD_LAST(-coef_b * coef_c, x_degree_c + x_degree_b, sin_degree_c + sin_degree_b, cos_degree_c + cos_degree_b);
                temp_b = temp_b -> pNext;
                buf.Del(k);


                node<double>*temp_buf = buf.head;
                node<double>*temp_b = buf.head;
                i = 1;
                k = 1;
                max = 0;
                while (temp_buf != nullptr) {
                    if ((temp_buf -> x_degree >= max) or(temp_buf -> sin_degree > max) or(temp_buf -> cos_degree > max))
                    {
                        if (temp_buf -> x_degree > max) max = temp_buf -> x_degree;
                        if (temp_buf -> sin_degree > max) max = temp_buf -> sin_degree;
                        if (temp_buf -> cos_degree > max) max = temp_buf -> cos_degree;
                        coef_b = temp_buf -> coef;
                        x_degree_b = temp_buf -> x_degree;
                        sin_degree_b = temp_buf -> sin_degree;
                        cos_degree_b = temp_buf -> cos_degree;
                        k = i;
                    }
                    i += 1;
                    temp_buf = temp_buf -> pNext;
                }

            }


            node<double>*temp_d = d.head;
            while (temp_d != nullptr) {

                list_a.ADD_LAST(temp_d -> coef, temp_d -> x_degree, temp_d -> sin_degree, temp_d -> cos_degree);
                temp_d = temp_d -> pNext;

            }
            if (list_a.size == 0) flag = false;

        }


    }}

    public static Polynomial mod(Polynomial a, Polynomial b) {
        Polynomial mod_polynomial = new Polynomial();
    }
}
