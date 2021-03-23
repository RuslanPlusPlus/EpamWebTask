package by.ruslan.web.service.impl;

import by.ruslan.web.entity.User;
import by.ruslan.web.repository.Repository;
import by.ruslan.web.repository.Specification;
import by.ruslan.web.repository.impl.UserRepository;
import by.ruslan.web.service.UserService;

import java.util.Comparator;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(){
        repository = UserRepository.getInstance();
    }

    @Override
    public List<User> sortUsers(Comparator<User> comparator) {
        return repository.sort(comparator);
    }

    @Override
    public List<User> query(Specification<User> specification) {
        return repository.query(specification);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void addAll(List<User> users) {
        repository.addAll(users);
    }
}
