package by.ruslan.web.creator;

import by.ruslan.web.entity.User;

import java.util.List;

public class UserCreator {
    public List<User> createUsers(){
        List<User> users = List.of(
                new User("Johnny", 34),
                new User ("Kate", 16),
                new User ("James", 25),
                new User ("Harry", 13),
                new User ("Thomas", 30),
                new User ("Jane", 29),
                new User ("Ann", 24)
        );
        return users;
    }
}
