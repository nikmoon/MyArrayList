/**
 * Created by sa on 07.06.17.
 */
public class MyArrayList<T> {
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
        if (index < 0 || index > this.length)
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


    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.length)
            throw new IndexOutOfBoundsException();
        T elem = (T)storage[index];

        // копируем вторую часть влево на 1 позицию
        System.arraycopy(this.storage, index + 1, this.storage, index, this.length - index - 1);

        this.length -= 1;
        return elem;
    }

    public boolean remove(T value) {
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
