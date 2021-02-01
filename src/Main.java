public class Main {
    public static void main(String[] args){
        //Инициализируем массив
        int numArray[] = {1,20,51,6,44,0,-1,32,345,-4};
        MayArrayList arLst = new MayArrayList(numArray);
        arLst.outSize();
        arLst.outArray();

        //Перезватим исключения
        try {
            //Добавим в конец списка
            arLst.add(-121);
            arLst.outSize();
            arLst.outArray();

            //Добавим в нужную позицию
            arLst.add(11111,10);
            arLst.outSize();
            arLst.outArray();

            //Удалим нужную позицию
            arLst.del(11);
            arLst.outSize();
            arLst.outArray();

        } catch (MyException e) {
            e.printStackTrace();
        }
        //Вывод отсортированного массива по возрастанию
        arLst.outArray(true);
        //Вывод отсортированного массива по убыванию
        arLst.outArray(false);
        //Вывод максимального и минимального значения
        arLst.outMaxMin();

        //Заполненим массив одинаковыми элементами
        arLst.fill(25);
        arLst.outArray();

    }

}
