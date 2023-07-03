package comparator;

import connection.Connection;

import java.util.Comparator;

public class ConnectionSurnameComparator implements Comparator<Connection> {
    @Override
    public int compare(Connection o1, Connection o2) {
        return o1.getSurname().compareTo(o2.getSurname());
    }

    /*public void sort(ConnectionEmailComparator comparator){
        ConnectionManager connectionManager = new ConnectionManager();
        Arrays.sort(connectionManager.getConnections().values(), comparator);
    }*/
}
