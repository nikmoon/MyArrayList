package nikpack;


public class Main {

    public static void main(String[] args) {

    }


}

class MyArrayList<T> {
    private int capacity;
    private int length;
    private Object[] storage;

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        this.length = 0;
        storage = new Object[capacity];
    }

    private Object[] getBiggerStorage() {
        Object[] newStorage = new Object[this.capacity * 3 / 2 + 1];
        return newStorage;
    }

    public void add(int index, T value) throws IndexOutOfBoundsException{
        if (index > this.length)
            throw new IndexOutOfBoundsException();

        Object[] newStorage = storage;
        if (this.length + 1 > capacity) {
            newStorage = getBiggerStorage();
            this.capacity = newStorage.length;

            // копируем первую половину в новый массив
            System.arraycopy(this.storage, 0, newStorage, 0, index);
        }

        // вставляем новое значение
        newStorage[index] = value;

        // копируем оставшиеся элементы
        System.arraycopy(this.storage, index, newStorage, index + 1, this.length - index);

        this.length += 1;
    }


    public boolean add(T value) {
        this.add(this.length, value);
        return true;
    }


    public T remove(int index) {
        return null;
    }

}

//1 вариант
//реализация ArrayList
// add(index), add(value), remove(index), remove(value)
// contains
// System.arraycopy