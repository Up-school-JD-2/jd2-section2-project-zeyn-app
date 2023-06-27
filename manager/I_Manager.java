package manager;

import java.util.function.Consumer;

public interface I_Manager<T> {
    T add(T t);
    T remove(T t);
    //T remove(int index);
    void update(String item, Consumer<T> t);
}

