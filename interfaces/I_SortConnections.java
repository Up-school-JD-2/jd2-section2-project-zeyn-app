package interfaces;

import person.Connection;

import java.util.Comparator;
import java.util.Map;

public interface I_SortConnections {
    void sort(Map<String, Connection> connections, Comparator<Connection> comparator);
}
