#include "Header.h"

template <class T>
TEMPLATE_LIST<T>::TEMPLATE_LIST()
{
    size = 0;
    head = nullptr;
    tail = nullptr;
}

template <class T>
int TEMPLATE_LIST<T>::IS_EMPTY() const
{

    return (head == nullptr);

}



template <class T>
T TEMPLATE_LIST<T>::GET_INFO_FIRST() const
{



    if (this->head->coef == 0) return 0;
    else {
        cout << this->head->coef;
        if (this->head->x_degree != 0)  cout << "x^" << this->head->x_degree;
        if (this->head->sin_degree != 0)  cout << " * sin^" << this->head->sin_degree;
        if (this->head->cos_degree != 0)  cout << " * cos^" << this->head->cos_degree;

    }

    return 1;

}



template <class T>
TEMPLATE_LIST<T>::TEMPLATE_LIST(const TEMPLATE_LIST& L)
{
    head = nullptr;
    tail = nullptr;
    size = 0;
    node<T>* temp = L.head;
    while (temp != nullptr)
    {
        this->ADD_LAST(temp->coef, temp->x_degree, temp->sin_degree, temp->cos_degree);
        temp = temp->pNext;
    }
}



template <class T>
TEMPLATE_LIST<T>& TEMPLATE_LIST<T>::ADD_LAST(T coef, T x_degree, T sin_degree, T cos_degree)
{
    node<T>* temp = new node<T>;
    if (size == 0)
    {
        temp->pNext = nullptr;
        head = temp;
        tail = temp;

        temp->coef = coef;

        temp->x_degree = x_degree;
        temp->sin_degree = sin_degree;
        temp->cos_degree = cos_degree;

        head = temp;
        tail = temp;
        size++;

    }
    else {


        bool flag = 1;
        int k = 1;
        node<T>* temp = this->head;
        while (temp != nullptr and flag and size != 0)
        {
            if ((x_degree == temp->x_degree) && (sin_degree == temp->sin_degree) && (cos_degree == temp->cos_degree))
            {
                if ((temp->coef = temp->coef + coef) == 0)
                {
                    temp = temp->pNext;
                    this->Del(k);

                }
                flag = 0;

            }
            if (flag) temp = temp->pNext;
            k++;
        }
        if (flag) {

            node<T>* temp = new node<T>;

            // Следующего нет:
            temp->pNext = nullptr;
            // Заполняем данные:

            temp->coef = coef;

            temp->x_degree = x_degree;
            temp->sin_degree = sin_degree;
            temp->cos_degree = cos_degree;



            if (tail != nullptr)
                tail->pNext = temp;
            tail = temp;

            size++;
        }

    }
    return *this;
}





void Show(LIST_FLOAT& list)
{



    LIST_FLOAT buf(list);
    if (list.head == nullptr) cout << "0\n"; else {
        while (!buf.IS_EMPTY())
        {

            buf.GET_INFO_FIRST();
            buf.DELETE_FIRST();
            if (!buf.IS_EMPTY()) cout << " + ";
        }
    }
    cout << "\n";
}


template <class T>
TEMPLATE_LIST<T>& TEMPLATE_LIST<T>::DELETE_FIRST()
{

    if (!(IS_EMPTY())) {
        node<T>* temp = head;
        head = temp->pNext;
        delete temp;
    }

    return *this;
}

template <class T>
void TEMPLATE_LIST<T>::DELETE_First()
{
    if (!(IS_EMPTY())) {
        node<T>* temp = head;
        head = temp->pNext;
        delete temp;
    }
}

template <class T>
int TEMPLATE_LIST<T>::Del(int pos)
{

    if (pos < 1 || pos > size)
    {   // Неверная позиция:
        cout << "Incorrect position !!!\n";
        return 0;
    }

    if (pos == 1) {
        DELETE_First();
    }
    else
    {
        if (pos == size) {
            DELETE_LAST();
        }
        else {
            node<T>* PrevDel = head;
            for (int i = 1; i < pos; i++)
            {   // Доходим до элемента, который удаляется:
                PrevDel = PrevDel->pNext;
            }
            node<T>* Del = PrevDel->pNext;
            PrevDel->pNext = Del->pNext;
            delete Del;

            
            
        }
    }
    size--;
}

template <class T>
TEMPLATE_LIST<T>& TEMPLATE_LIST<T>::COPY_LIST(const TEMPLATE_LIST& L)
{
    head = nullptr;
    tail = nullptr;
    size = 0;
    node<T>* temp = L.head;
    while (temp != nullptr)
    {
        this->ADD_LAST(temp->coef, temp->x_degree, temp->sin_degree, temp->cos_degree);
        temp = temp->pNext;
    }
    return *this;
}



void Input(LIST_FLOAT& list)
{


    int number;
    cin >> number;
    float coef, x_degree, sin_degree, cos_degree;
    for (int i = 1; i <= number; i++) {
        cout << "Введите коээфициент, а после поочередно степени у " << i << "-го элемента многочлена при x, sin и cos" << endl;

        cin >> coef >> x_degree >> sin_degree >> cos_degree;
        list.ADD_LAST(coef, x_degree, sin_degree, cos_degree);

    }



}

int Division(LIST_FLOAT& list_a, LIST_FLOAT& list_b, LIST_FLOAT& list_c)
{
    bool flag = 1;
    if (list_b.head == nullptr) {
        cout << "Нельзя делить на 0";
        return 0;
    }
    if (list_a.head == nullptr) {
        return 1;
    }


    while (flag) {
        node<double>* temp = list_a.head;
        int i = 1; int k = 1;
        int max = 0;
        double coef_a; int x_degree_a, sin_degree_a, cos_degree_a;
        double coef_b;  int x_degree_b, sin_degree_b, cos_degree_b;
        while (temp != nullptr)
        {
            if ((temp->x_degree >= max) or (temp->sin_degree > max) or (temp->cos_degree > max))
            {
                if (temp->x_degree > max) max = temp->x_degree;
                if (temp->sin_degree > max) max = temp->sin_degree;
                if (temp->cos_degree > max) max = temp->cos_degree;
                coef_a = temp->coef;
                x_degree_a = temp->x_degree;
                sin_degree_a = temp->sin_degree;
                cos_degree_a = temp->cos_degree;
                k = i;
            }
            i += 1;
            temp = temp->pNext;
        }


        temp = list_b.head;
        i = 1;  k = 1;
        max = 0;
        while (temp != nullptr)
        {
            if ((temp->x_degree >= max) or (temp->sin_degree > max) or (temp->cos_degree > max))
            {
                if (temp->x_degree > max) max = temp->x_degree;
                if (temp->sin_degree > max) max = temp->sin_degree;
                if (temp->cos_degree > max) max = temp->cos_degree;
                coef_b = temp->coef;
                x_degree_b = temp->x_degree;
                sin_degree_b = temp->sin_degree;
                cos_degree_b = temp->cos_degree;
                k = i;
            }
            i += 1;
            temp = temp->pNext;
        }




        if ((x_degree_a - x_degree_b < 0) or (sin_degree_a - sin_degree_b < 0) or (cos_degree_a - cos_degree_b < 0)) flag = 0;
        else {
            double coef_c = coef_a / coef_b;
            int x_degree_c = x_degree_a - x_degree_b;
            int sin_degree_c = sin_degree_a - sin_degree_b;
            int cos_degree_c = cos_degree_a - cos_degree_b;
            list_c.ADD_LAST(coef_c, x_degree_c, sin_degree_c, cos_degree_c);
            LIST_FLOAT buf(list_b);
            LIST_FLOAT d;
            node<double>* temp_b = buf.head;
            int num = 1;
            while (temp_b != nullptr and buf.size != 0) {


                d.ADD_LAST(-coef_b * coef_c, x_degree_c + x_degree_b, sin_degree_c + sin_degree_b, cos_degree_c + cos_degree_b);
                temp_b = temp_b->pNext;
                buf.Del(k);




                node<double>* temp_buf = buf.head;
                node<double>* temp_b = buf.head;
                i = 1;  k = 1;
                max = 0;
                while (temp_buf != nullptr)
                {
                    if ((temp_buf->x_degree >= max) or (temp_buf->sin_degree > max) or (temp_buf->cos_degree > max))
                    {
                        if (temp_buf->x_degree > max) max = temp_buf->x_degree;
                        if (temp_buf->sin_degree > max) max = temp_buf->sin_degree;
                        if (temp_buf->cos_degree > max) max = temp_buf->cos_degree;
                        coef_b = temp_buf->coef;
                        x_degree_b = temp_buf->x_degree;
                        sin_degree_b = temp_buf->sin_degree;
                        cos_degree_b = temp_buf->cos_degree;
                        k = i;
                    }
                    i += 1;
                    temp_buf = temp_buf->pNext;
                }

            }


            node<double>* temp_d = d.head;
            while (temp_d != nullptr)
            {

                list_a.ADD_LAST(temp_d->coef, temp_d->x_degree, temp_d->sin_degree, temp_d->cos_degree);
                temp_d = temp_d->pNext;

            }
            if (list_a.size == 0) flag = false;

        }

    }
    if (list_c.head == nullptr) {

        cout << "Многочлены невозможно поделить";
        return 0;
    }
    return 1;
}





template <class T>
void TEMPLATE_LIST<T>::DELETE_LAST()
{
    if (!(IS_EMPTY())) {
        if (head == tail)
        {
            DELETE_First();
            return;
        }
        else {
            node<T>* temp = head;
            while (temp->pNext != tail) temp = temp->pNext;
            temp->pNext = nullptr;
            delete tail;
            tail = temp;
        }
    }
}



template <class T>
T TEMPLATE_LIST<T>::GET_INFO() const
{
    if (this->head->coef == 0) return 0;
    else {
        cout << this->head->coef;
        cout << "*" << this->head->x_degree << "* x^" << this->head->x_degree - 1;
        cout << " * sin^" << this->head->sin_degree;
        cout << " * cos^" << this->head->cos_degree;
        cout << " + " << this->head->coef;
        cout << "*" << this->head->sin_degree;
        cout << "*" << "* x^" << this->head->x_degree;
        cout << " * sin^" << this->head->sin_degree - 1;
        cout << " * cos^" << this->head->cos_degree + 1;
        cout << " - " << this->head->coef;
        cout << "*" << this->head->cos_degree;
        cout << "* x^" << this->head->x_degree;
        cout << " * sin^" << this->head->sin_degree + 1;
        cout << " * cos^" << this->head->cos_degree - 1;


    }

    return 1;

}

void Show_Diff(LIST_FLOAT& list)
{



    LIST_FLOAT buf(list);
    if (list.head == nullptr) cout << "0\n"; else {
        while (!buf.IS_EMPTY())
        {

            buf.GET_INFO();
            buf.DELETE_FIRST();
            if (!buf.IS_EMPTY()) cout << " + ";
        }
    }
    cout << "\n";
}

