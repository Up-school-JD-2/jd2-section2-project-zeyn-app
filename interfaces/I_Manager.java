package interfaces;

import exceptions.LoadingExistException;
import exceptions.NoEnoughEmptySpaceException;

import java.util.function.Consumer;

public interface I_Manager<T> {
    T add(T t) throws NoEnoughEmptySpaceException, LoadingExistException;
    T remove(T t);
    //T remove(int index);
    void update(String item, Consumer<T> t);
}

