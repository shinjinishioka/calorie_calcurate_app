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
        List<Food> foods = em.createQuery("SELECT f FROM Food f  WHERE f.deleteFlag =0 AND f.user =" + user.getId()+"OR f.deleteFlag =0 AND f.user=1 ORDER BY f.id DESC", Food.class)
                .getResultList();

        return foods;
    }

    public List<Food> getAllFoodsByUserPerPage(User user, int page) {
        List<Food> foods = em
                .createQuery("SELECT f FROM Food f WHERE f.deleteFlag =0 AND f.user = " + user.getId() + "OR f.deleteFlag =0 AND f.user=1 ORDER BY f.id DESC",
                        Food.class)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
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

    /*
    public void delete(String id) {
        Food food = findOne(id);
        em.getTransaction().begin();
        em.remove(food);
        em.getTransaction().commit();
    }
    */
    public void delete(String id) {
        Food food = findOne(id);
        food.setDeleteFlag(1);
        em.getTransaction().begin();
        em.getTransaction().commit();
    }

}
