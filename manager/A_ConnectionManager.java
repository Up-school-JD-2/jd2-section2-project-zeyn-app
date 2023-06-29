package manager;

import enums.ConnectionCategory;
import enums.Gender;
import interfaces.I_FunctionalInterface;
import interfaces.I_Manager;
import interfaces.I_SortConnections;
import person.Connection;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class A_ConnectionManager implements I_Manager<Connection>, I_FunctionalInterface, I_SortConnections {
    Map<String, Connection> connections;

    public Map<String, Connection> getConnections(){
        return connections;
    }
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
    public Map<ConnectionCategory, Long> groupByCategory() {
        return connections.values().stream().collect(Collectors.groupingBy(Connection::getCategory, Collectors.counting()));
    }
    @Override
    public Map<Gender, Long> groupByGender() {
        return connections.values().stream().collect(Collectors.groupingBy(Connection::getGender, Collectors.counting()));
    }
    @Override
    public void getConnect(String phoneNumber, Consumer<String> connectType){
        connectType.accept(phoneNumber);
    }

    @Override
    public void sortByName(Map<String, Connection> connections, Comparator<Connection> comparator) {}
    @Override
    public void sortBySurname(Map<String, Connection> connections, Comparator<Connection> comparator) {}
    @Override
    public void sortByPhoneNumber(Map<String, Connection> connections, Comparator<Connection> comparator) {}
    @Override
    public void sortByGenderThenName(Map<String, Connection> connections, Comparator<Connection> comparator) {}
    public void list() {
        connections.values().forEach(System.out::println);
    }
    public abstract void updateOnlyNameAndSurname(Connection connection);
    public abstract void updateOnlyPhoneNumber(Connection connection) ;
    public abstract void updateOnlyEmailAddress(Connection connection);
    public abstract void updateAll(Connection connection);
    public abstract List<Connection> filterByGender(String specifiedGender);
    public abstract List<Connection> filterByCategory(String specifiedCategory);
    public abstract void sendMessage(String phoneNumber, String message);
    public abstract void call(String phoneNumber);
}
