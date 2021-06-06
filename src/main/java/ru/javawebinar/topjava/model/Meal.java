package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Meal {
    private final AtomicInteger mealId = new AtomicInteger(0);
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public int getMealId() {
        return mealId.get();
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this.mealId.getAndIncrement();
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
