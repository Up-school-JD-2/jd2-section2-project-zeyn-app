package connection;

import interfaces.I_FunctionalInterface;
import interfaces.I_GroupAndFilter;
import interfaces.I_Manager;
import interfaces.I_Sort;
import person.Connection;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class A_ConnectionManager implements I_Manager<Connection>, I_FunctionalInterface, I_Sort<Connection>, I_GroupAndFilter<Connection> {
    Map<String, Connection> connections;

    public Map<String, Connection> getConnections() {
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
    public List<Connection> filter(Predicate<Connection> filteredCondition) {
        return connections.values().stream().filter(filteredCondition).toList();
    }

    @Override
    public Map<Enum, List<Connection>> groupByCategory() {
        return connections.values().stream().collect(Collectors.groupingBy(Connection::getCategory, Collectors.toList()));
    }

    @Override
    public Map<Gender, List<Connection>> groupByGender() {
        return connections.values().stream().collect(Collectors.groupingBy(Connection::getGender, Collectors.toList()));
    }

    @Override
    public void getConnect(String phoneNumber, Consumer<String> connectType) {
        connectType.accept(phoneNumber);
    }

    @Override
    public void sort(Map<String, Connection> connections, Comparator<Connection> comparator) {
        connections.values().stream().sorted(comparator).toList().forEach(connection -> System.out.println("\t\t" + connection));
    }

    public void list() {
        if (!connections.isEmpty()) System.out.println("\n\t\tKİŞİLERİM");
        connections.values().forEach(connection -> System.out.println("\t\t" + connection));
    }

    public abstract void updateOnlyNameAndSurname(Connection connection);

    public abstract void updateOnlyPhoneNumber(Connection connection);

    public abstract void updateOnlyEmailAddress(Connection connection);

    public abstract void updateAll(Connection connection);

    public abstract List<Connection> filterByGender(String specifiedGender);

    public abstract List<Connection> filterByCategory(String specifiedCategory);

    public abstract void sendMessage(String phoneNumber, String message);

    public abstract void call(String phoneNumber);
}
