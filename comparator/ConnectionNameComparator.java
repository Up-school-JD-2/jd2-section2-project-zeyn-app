package comparator;

import connection.Connection;

import java.util.Comparator;

public class ConnectionNameComparator implements Comparator<Connection> {
    @Override
    public int compare(Connection o1, Connection o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
