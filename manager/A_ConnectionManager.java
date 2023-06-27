package manager;

import person.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class A_ConnectionManager implements I_Manager<Connection>, FunctionalInterface {
    Map<String, Connection> connections;
    public A_ConnectionManager() {
        connections = new HashMap<>();
    }
    @Override
    public Connection add(Connection connection) {
        return connections.put(connection.getPhoneNumber(), connection);
    }
    @Override
    public Connection remove(Connection connection) {
        return connections.remove(connection.getPhoneNumber());
    }
    @Override
    public void update(String phoneNumber, Consumer<Connection> connectionConsumer) {
        Connection connection = connections.get(phoneNumber);
        connectionConsumer.accept(connection);
    }
    @Override
    public List<Connection> filterConnection(Predicate<Connection> filteredCondition) {
        return connections.values().stream().filter(filteredCondition).toList();
    }
    @Override
    public void getConnect(String phoneNumber, Consumer<String> connectType){
        connectType.accept(phoneNumber);
    }
    public void list() {
        connections.values().forEach(System.out::println);
    }
    public abstract void updateOnlyNameAndSurname(Connection connection);
    public abstract void updateOnlyPhoneNumber(Connection connection) ;
    public abstract void updateOnlyEmailAddress(Connection connection);
    public abstract void updateAll(Connection connection);
    public abstract List<Connection> filterByGender(String specifiedGender);
    public abstract List<Connection> filterByCategory(String specifiedCategory);
    public abstract List<Connection> sortByName(int length);
    public abstract void sendMessage(String phoneNumber, String message);
    public abstract void call(String phoneNumber);
}
