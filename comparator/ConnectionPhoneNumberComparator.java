package comparator;

import connection.Connection;

import java.util.Comparator;

public class ConnectionPhoneNumberComparator implements Comparator<Connection> {
    @Override
    public int compare(Connection o1, Connection o2) {
        return o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
    }
}
