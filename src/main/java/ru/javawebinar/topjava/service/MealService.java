package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;

@Service
public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal save(int userId, Meal meal){
        return repository.save(userId, meal);
    }

    public boolean delete(int userId, int id) {
        return repository.delete(userId, id);
    }

    public Meal get(int userId, int id) {
        return repository.get(userId, id);
    }

    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }
}