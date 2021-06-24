package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final int MEAL_ID = 100003;
    public static final int USER_ID = 100000;
    public static final int DIFF_USER_MEAL = 100004;
    public static final int NOT_FOUND = 7;

    public static final Meal MEAL = new Meal(MEAL_ID, LocalDateTime.of(2011, 6, 22, 19, 10, 25, 7), "Ланч", 1200);

    public static final List<Meal> meals = Arrays.asList(MEAL,
            new Meal(100002, LocalDateTime.of(2011, 6, 22, 19, 10, 25, 7), "Завтрак", 1000));


    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("dateTime").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dateTime").isEqualTo(expected);
    }
}
