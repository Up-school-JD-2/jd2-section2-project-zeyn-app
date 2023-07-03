package interfaces;

import connection.Gender;
import connection.Connection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface I_FunctionalInterface {
    void getConnect(String phoneNumber, Consumer<String> connectType);
    Map<Gender, List<Connection>> groupByGender();
}
