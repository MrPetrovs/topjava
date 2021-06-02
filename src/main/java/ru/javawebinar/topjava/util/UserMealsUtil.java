package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<Integer, Integer> map = new HashMap<>();

        for (UserMeal userMeal : meals) {
            int dayOfMonth = userMeal.getDateTime().toLocalDate().getDayOfMonth();
            int calories = map.getOrDefault(dayOfMonth, -1);

            if (calories == -1) {
                map.put(dayOfMonth, userMeal.getCalories());
            }else {
                map.replace(dayOfMonth, calories, calories + userMeal.getCalories());
            }
        }

        List<UserMealWithExcess> resultList = new ArrayList<>();

        for (UserMeal userMeal : meals) {
            int timeToCheck = userMeal.getDateTime().toLocalTime().toSecondOfDay();

            if (timeToCheck >= startTime.toSecondOfDay() && timeToCheck <= endTime.toSecondOfDay()) {
                int dayOfMonth = userMeal.getDateTime().toLocalDate().getDayOfMonth();

                if (map.get(dayOfMonth) > caloriesPerDay) {
                    resultList.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true));
                } else {
                    resultList.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), false));
                }
            }
        }
        return resultList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        return null;
    }
}
