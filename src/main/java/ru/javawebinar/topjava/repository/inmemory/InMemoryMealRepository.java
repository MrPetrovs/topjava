package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        ConcurrentHashMap<Integer, Meal> map1 = new ConcurrentHashMap<>();
        for (int i = 0; i < MealsUtil.mealsForUser1.size(); i++) {
            MealsUtil.mealsForUser1.get(i).setId(counter.incrementAndGet());
            map1.put(MealsUtil.mealsForUser1.get(i).getId(), MealsUtil.mealsForUser1.get(i));
        }

        ConcurrentHashMap<Integer, Meal> map2 = new ConcurrentHashMap<>();
        for (int i = 0; i < MealsUtil.mealsForUser2.size(); i++) {
            MealsUtil.mealsForUser2.get(i).setId(counter.incrementAndGet());
            map2.put(MealsUtil.mealsForUser2.get(i).getId(), MealsUtil.mealsForUser2.get(i));
        }

        repository.put(UsersUtil.users.get(0).getId(), map1);
        repository.put(UsersUtil.users.get(1).getId(), map2);

    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (meal.isNew()) {
            System.out.println("meal id = " + meal.getId());
            meal.setId(counter.incrementAndGet());
            if (repository.containsKey(userId)) {
                repository.get(userId).put(meal.getId(), meal);
            } else {
                ConcurrentHashMap<Integer, Meal> map = new ConcurrentHashMap<>();
                map.put(meal.getId(), meal);
                repository.put(userId, map);
            }
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);

    }

    @Override
    public boolean delete(int userId, int id) {
        return repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        return repository.get(userId).get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.get(userId).values();
    }
}

