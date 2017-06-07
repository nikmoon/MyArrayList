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

    public void add(int index, T value) {
        Object[] newStorage = storage;
        int newLength = this.length + 1;
        if (newLength > capacity) {
            newStorage = getBiggerStorage();
        }
        System.arraycopy(this.storage, 0, newStorage, 0, index);
        newStorage[index] = value;
        System.arraycopy(this.storage, index, newStorage, index + 1, this.length - index);
    }
}

//1 вариант
//реализация ArrayList
// add(index), add(), remove(index), remove(value)
// contains
// System.arraycopy