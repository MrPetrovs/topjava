package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal save(int userId, Meal meal) {
        log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        return service.save(userId, meal);
    }

    public boolean delete(int userId, int id) {
        log.info("Delete {}", id);
        return service.delete(userId, id);
    }

    public Meal get(int userId, int id) {
        log.info("Get {}", id);
        return service.get(userId, id);
    }

    public Collection<Meal> getAll(int userId) {
        log.info("getAll");
        return service.getAll(userId);
    }
}