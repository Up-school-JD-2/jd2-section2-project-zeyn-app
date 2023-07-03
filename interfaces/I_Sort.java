package interfaces;

import java.util.Comparator;
import java.util.Map;

public interface I_Sort<T> {
    void sort(Map<String, T> connections, Comparator<T> comparator);
}
