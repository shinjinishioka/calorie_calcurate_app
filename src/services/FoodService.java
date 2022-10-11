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
        List<Food> foods = em.createQuery("SELECT f FROM Food f WHERE f.user =" + user, Food.class)
                .getResultList();;
        return foods;
    }
}
