package by.ruslan.web.comparator;

import by.ruslan.web.entity.User;

import java.util.Comparator;

public class UserAgeComparator implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        return user1.getAge() - user2.getAge();
    }
}
