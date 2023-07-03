package interfaces;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface I_GroupAndFilter<T> {
    List<T> filter(Predicate<T> filteredCondition);
    Map<Enum, List<T>> groupByCategory();
}
