package by.ruslan.web.repository;

import by.ruslan.web.entity.User;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface Repository<T extends User> {
    List<T> getAll();
    boolean addAll(Collection<? extends T> collection);
    List<T> query(Specification<T> specification);
    List<T> sort(Comparator<T> comparator);
}
