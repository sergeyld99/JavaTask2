import java.util.Arrays;
import java.util.Collections;

public class MayArrayList {
    //Массив значений
    private int numberArray[];
    //Размерность массива по умолчанию
    private final int defaultCapacity = 10;
    //Размерность массива (первоначально 10)
    private int capacity = defaultCapacity;
    //Размер массива
    private int size = 0;
    //Получить Capacity по размеру
    private int getCapacityBySize(int size) {
        //если размер меньше меньшего, вернем defaultCapacity
        if (size<=0) return defaultCapacity;
        //Дополнительное приращение
        int addValue = 0;
        //System.out.println(size%defaultCapacity * 3/2);
        //Если остаток больше чем 2/3 от defaultCapacity прибавим 1
        if (size%defaultCapacity * 3/2 > defaultCapacity)
            addValue = 1;
        return (size/defaultCapacity + 1 + addValue) * defaultCapacity;
    }

    //Изменим размер массива
    private void resize(int newSize) throws MyException {
        if (newSize<0)
            throw new MyException("Размер не может быть отрицательным");
        //Найдем capacity
        capacity = getCapacityBySize(newSize);
        //Создадим новый массив
        int numberArray0[] = new int[capacity];
        //Новый размер массива
        int sizeToCopy = newSize;
        //Если старый размер меньше нового, то скопируем старый размер
        if (size<newSize)
            newSize = size;
        //Скопируем массив
        System.arraycopy(numberArray, 0, numberArray0, 0, newSize);
        //Зададим новый массив
        numberArray = numberArray0;
        size = newSize;
    }
    //Конструктор
    public MayArrayList(int value[]){
        //Если массив не нулевой изменим capacity
        if (!(value == null || value.length == 0)) {
            capacity = getCapacityBySize(value.length);
            //Инициируем массив
            numberArray = new int[capacity];
            //Копируем данные
            System.arraycopy(value, 0, numberArray, 0, value.length);
            size = value.length;
        }
    }
    //Вывод количества элементов массива
    public void outSize(){
        System.out.println(String.format("Размер = %d",size));
    }
    //Вывод максимального и минимального
    public void outMaxMin(){
        if (size == 0) System.out.println("Массив пустой");
        int maxElem = numberArray[0];
        int minElem = numberArray[0];
        for (int i = 1;i<size;i++) {
            if (maxElem < numberArray[i]) maxElem = numberArray[i];
            if (minElem > numberArray[i]) minElem = numberArray[i];
        }
        System.out.println(String.format("Максимальный элемент = %d",maxElem));
        System.out.println(String.format("Минимальный элемент = %d",minElem));
    }
    //Вывод элементов массива
    public void outArray(){
        System.out.print("Массив: ");
        for(int i = 0; i < size; i++) {
            //Разделим список запятыми
            if (i>0)
                System.out.print(",");
            //Выведем элемент массива
            System.out.print(numberArray[i]);
        }
        System.out.println();
    }
    //Вывод отсортированного массива. sortAsc=true - сортировка по возрастанию, sortAsc=false - сортировка по убыванию
    public void outArray(boolean sortAsc){
        //Массив посредние который будем сортировать
        Integer numberArray0[] = new Integer[size];
        for(int i = 0; i < size; i++)
            numberArray0[i] = numberArray[i];
        //System.arraycopy(numberArray, 0, numberArray0, 0, size);
        if (sortAsc)
            Arrays.sort(numberArray0);
        else
            Arrays.sort(numberArray0, Collections.reverseOrder());

        System.out.print("Отсортированный массив: ");
        for(int i = 0; i < size; i++) {
            //Разделим список запятыми
            if (i>0)
                System.out.print(",");
            //Выведем элемент массива
            System.out.print(numberArray0[i]);
        }
        System.out.println();
    }

    //Добавим элемент в конец списка
    public void add(int value) throws MyException {
        numberArray[size++] = value;
        //Если новый размер увеличился более чем 2/3 от capacity, увеличим capacity
        if (getCapacityBySize(size) > capacity)
            resize(size);
    }
    //Добавим элемент в определенную позицию
    public void add(int value, int pos) throws MyException {
        if (pos<0)
            throw new MyException("Позиция не может быть отрицательной");
        if (pos>size)
            throw new MyException(String.format("Позиция не может быть больше %d", size));
        //Расширим массив
        System.arraycopy(numberArray, pos, numberArray, pos+1, size - pos);
        numberArray[pos] = value;
        //Если новый размер увеличился более чем 2/3 от capacity, увеличим capacity
        if (getCapacityBySize(++size) > capacity)
            resize(size);
    }

    //Удаляем элемент по индексу
    public void del(int pos) throws MyException {
        if (pos<0)
            throw new MyException("Позиция не может быть отрицательной");
        if (pos>=size)
            throw new MyException(String.format("Позиция не может быть больше %d", size - 1));
        //Копируем блок данных
        System.arraycopy(numberArray, pos+1, numberArray, pos, size - pos);
        //Уменьшим массив, если нужно
        if (getCapacityBySize(--size) > capacity)
            resize(size);
    }
    //Изменяем элемент по индексу
    public void change(int value,int pos) throws MyException {
        if (pos<0)
            throw new MyException("Позиция не может быть отрицательной");
        if (pos>=size)
            throw new MyException(String.format("Позиция не может быть больше %d", size - 1));
        numberArray[pos] = value;
    }

    //Функция заполнения массива одинаковыми элементами
    public void fill(int value){
        Arrays.fill(numberArray, value);
    }

}
