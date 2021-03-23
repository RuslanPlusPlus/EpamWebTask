package by.ruslan.web.repository.impl;

import by.ruslan.web.entity.User;
import by.ruslan.web.repository.Specification;

public class AgeRangeSpecification implements Specification<User> {

    private int minValue;
    private int maxValue;

    public AgeRangeSpecification(int minValue, int maxValue){
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public boolean specify(User object) {
        boolean result;
        int age = object.getAge();
        result = age >= minValue && age <= maxValue;
        return result;
    }
}
