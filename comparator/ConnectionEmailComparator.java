package comparator;

import connection.Connection;

import java.util.Comparator;

public class ConnectionEmailComparator implements Comparator<Connection> {
    @Override
    public int compare(Connection o1, Connection o2) {
        return o1.getEmailAddress().compareTo(o2.getEmailAddress());
    }
}
