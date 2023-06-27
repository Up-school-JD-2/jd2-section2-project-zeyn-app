package manager;

import java.util.function.Consumer;

public interface Manager<T> {
    T add(T t);
    boolean remove(T t);
    //T remove(int index);
    void update(String str, Consumer<T> t);
}

