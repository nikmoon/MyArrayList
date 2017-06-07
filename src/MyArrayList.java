import java.util.Arrays;

/**
 * Created by sa on 07.06.17.
 */


//1 вариант
//реализация ArrayList
// add(index), add(value), remove(index), remove(value)
// contains
// System.arraycopy


public class MyArrayList<T> {

    private int capacity;
    private int length;
    private Object[] storage;

    public static void main(String[] args) {
        MyArrayList<Integer> l = new MyArrayList<>();
        l.dump();
        for(int i = 0; i < 9; i++)
            l.add(i);
        l.dump();
        l.add(0,20);
        l.dump();
        l.remove(5);
        l.dump();
    }

    public void dump() {
        System.out.println(Arrays.toString(storage));
        System.out.println("Length: " + length);
    }


    public MyArrayList(int initialCapacity) {
        capacity = initialCapacity;
        length = 0;
        storage = new Object[capacity];
    }

    public MyArrayList() {
        this(10);
    }

    /*
        Получаем элемент по индексу
     */
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException();
        return (T)storage[index];
    }

    /*
        Заменяем элемент по индексу
     */
    public T set(int index, T value) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException();
        Object prevValue = storage[index];
        storage[index] = value;
        return (T)prevValue;
    }

    /*
        Создание нового хранилища, размер которого определяется по формуле n * 3/2 + 1
     */
    private Object[] getBiggerStorage() {
        Object[] newStorage = new Object[this.capacity * 3 / 2 + 1];
        return newStorage;
    }

    /*
        Добавление элемента по индексу
     */
    public void add(int index, T value) throws IndexOutOfBoundsException{
        if (index < 0 || index > this.length)
            throw new IndexOutOfBoundsException();

        Object[] newStorage = storage;

        if (this.length + 1 > capacity) {
            newStorage = getBiggerStorage();
            this.capacity = newStorage.length;

            // копируем в новое хранилище все элементы до вставляемого элемента
            System.arraycopy(this.storage, 0, newStorage, 0, index);
        }

        // копируем все элементы, расположенные, начиная с заданного
        System.arraycopy(this.storage, index, newStorage, index + 1, this.length - index);

        // вставляем новое значение
        newStorage[index] = value;

        this.storage = newStorage;
        this.length += 1;
    }

    /*
        Добавление элемента в конец списка
     */
    public boolean add(T value) {
        this.add(this.length, value);
        return true;
    }

    /*
        Удаление элемента по индексу
     */
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.length)
            throw new IndexOutOfBoundsException();
        T elem = (T)storage[index];

        // копируем вторую часть влево на 1 позицию
        System.arraycopy(this.storage, index + 1, this.storage, index, this.length - index - 1);

        this.length -= 1;
        return elem;
    }

    /*
        Удаление элемента по значению
     */
    public boolean remove(Object value) {
        int i;
        if (value == null) {
            for(i = 0; i < this.length; i++) {
                if (storage[i] == null) {
                    remove(i);
                    return true;
                }
            }
        } else
            for(i = 0; i < this.length; i++) {
                if (value.equals(storage[i])) {
                    remove(i);
                    return true;
                }
            }
        return false;
    }

    /*
        Проверка вхождения элемента в список
     */
    public boolean contains(T value) {
        int i;
        if (value == null) {
            for(i = 0; i < this.length; i++) {
                if (storage[i] == null) {
                    return true;
                }
            }
        } else {
            for(i = 0; i < this.length; i++) {
                if (value.equals(storage[i])) {
                    return true;
                }
            }
        }
        return false;
    }

}
