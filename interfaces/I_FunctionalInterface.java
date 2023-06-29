package interfaces;

import enums.ConnectionCategory;
import enums.Gender;
import person.Connection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface I_FunctionalInterface {
    List<Connection> filterConnection(Predicate<Connection> filteredCondition);
    void getConnect(String phoneNumber, Consumer<String> connectType);
    Map<ConnectionCategory, Long> groupByCategory();
    Map<Gender, Long> groupByGender();
}
