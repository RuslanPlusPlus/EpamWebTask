package by.ruslan.web.repository.impl;

import by.ruslan.web.creator.UserCreator;
import by.ruslan.web.entity.User;
import by.ruslan.web.repository.Repository;
import by.ruslan.web.repository.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository implements Repository<User> {

    static final Logger logger = LogManager.getLogger();
    private List<User> users = new ArrayList<>();
    private static final UserRepository repository = new UserRepository();

    public static UserRepository getInstance(){
        return repository;
    }

    private UserRepository(){
        List<User> userList = new UserCreator().createUsers();
        users.addAll(userList);
    }

    @Override
    public List<User> getAll() {
        return List.copyOf(users);
    }

    @Override
    public boolean addAll(Collection<? extends User> collection) {
        return users.addAll(collection);
    }

    @Override
    public List<User> query(Specification<User> specification) {
        List<User> list = users.stream().filter(o -> specification.specify(o)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<User> sort(Comparator<User> comparator) {
        users.sort(comparator);
        return users;
    }
}
