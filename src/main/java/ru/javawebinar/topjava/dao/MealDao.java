package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDao {
    private static AtomicInteger counter = new AtomicInteger(0);
    private final List<Meal> meals = new CopyOnWriteArrayList<>(new ArrayList<>());

    {
        meals.add(new Meal(counter.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(counter.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(counter.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(counter.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        meals.add(new Meal(counter.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(counter.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(counter.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    public List<Meal> index() {
        return meals;
    }

    public void deleteMeal(int mealId) {
        Meal mealToDelete = getMealById(mealId);
        meals.remove(mealToDelete);
    }

    public Meal getMealById(int mealId) {
        return meals.stream().filter(meal -> meal.getMealId() == mealId).findAny().orElse(null);
    }

    public void updateMeal(String userid, Meal meal) {
        Meal mealToUpdate = getMealById(Integer.parseInt(userid));

        mealToUpdate.setCalories(meal.getCalories());
        mealToUpdate.setDescription(meal.getDescription());
        mealToUpdate.setDateTime(meal.getDateTime());
    }

    public void addMeal(Meal meal) {

    }
}
