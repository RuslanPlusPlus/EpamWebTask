package by.ruslan.web.service.impl;

import by.ruslan.web.comparator.UserAgeComparator;
import by.ruslan.web.entity.User;
import by.ruslan.web.repository.impl.AgeRangeSpecification;
import by.ruslan.web.repository.impl.UserRepository;
import by.ruslan.web.service.UserService;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository repository = UserRepository.getInstance();

    @Override
    public List<User> sortUsersByAge() {
        UserAgeComparator comparator = new UserAgeComparator();
        return repository.sort(comparator);
    }

    @Override
    public List<User> findUsersInAgeRange(int lowerValue, int higherValue) {
        AgeRangeSpecification specification = new AgeRangeSpecification(lowerValue, higherValue);
        return repository.query(specification);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
