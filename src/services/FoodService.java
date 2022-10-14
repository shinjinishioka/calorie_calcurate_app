package services;

import java.util.List;

import models.Food;
import models.User;

public class FoodService extends ServiceBase {

    public void create(Food food) {
        em.getTransaction().begin();
        em.persist(food);
        em.getTransaction().commit();
    }

    public List<Food> getAllFoodsByUser(User user) {
        List<Food> foods = em.createQuery("SELECT f FROM Food f WHERE f.user = " + user.getId(), Food.class)
                .getResultList();

        return foods;
    }

    public Food findOne(String id) {
        Food food = em.createQuery("SELECT f FROM Food f WHERE f.id =" + id, Food.class)
                .getSingleResult();
        return food;
    }

    public void update(Food food) {
        Food savedFood = findOne(food.getId().toString());
        savedFood.setName(food.getName());
        savedFood.setUnit(food.getUnit());
        savedFood.setCaloriePerUnit(food.getCaloriePerUnit());
        savedFood.setProtein(food.getProtein());
        savedFood.setFat(food.getFat());
        savedFood.setCarbo(food.getCarbo());

        em.getTransaction().begin();
        em.getTransaction().commit();
    }

    public void delete(String id) {
        Food food = findOne(id);
        em.getTransaction().begin();
        em.remove(food);
        em.getTransaction().commit();
    }

}
