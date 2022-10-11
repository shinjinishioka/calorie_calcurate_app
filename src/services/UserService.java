package services;

import java.time.LocalDateTime;

import javax.persistence.NoResultException;

import models.User;

public class UserService extends ServiceBase {
    //新規ユーザー登録

    public void create(User user) {
        //登録日時、Update日時を設定
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public User findOne(String id) {
        User user = em.createQuery("SELECT u FROM User u WHERE u.id =" + id, User.class)
                .getSingleResult();
        return user;
    }

    //ログインユーザー取得
    public User findLoginUser(String name, String password) {
        User user = null;
        try {
            user = em
                    .createQuery(
                            "SELECT u FROM User u WHERE u.name = '" + name + "' AND u.password ='" + password + "'",
                            User.class)
                    .getSingleResult();
            if (user.getDeleteFlag() == 1) {
                user = null;
            }
        } catch (NoResultException ex) {
        }

        return user;
    }

    public void update(User user) {
        User savedUser = findOne(user.getId().toString());
        savedUser.setName(user.getName());
        savedUser.setAge(user.getAge());
        savedUser.setSex(user.getSex());
        savedUser.setHeight(user.getHeight());
        savedUser.setWeight(user.getWeight());
        savedUser.setActivityLevel(user.getActivityLevel());
        savedUser.setBodyFat(user.getBodyFat());
        savedUser.setPeriod(user.getPeriod());
        savedUser.setTargetWeight(user.getTargetWeight());
        savedUser.setTargetProtein(user.getTargetProtein());
        savedUser.setTargetFat(user.getTargetFat());
        savedUser.setUpdatedAt(LocalDateTime.now());

        em.getTransaction().begin();
        em.getTransaction().commit();
    }

    public void changePass(String id, String password) {
        User savedUser = findOne(id);
        savedUser.setPassword(password);
        em.getTransaction().begin();
        em.getTransaction().commit();
    }

    public void delete(String id) {
        User user = findOne(id);
        user.setDeleteFlag(1);
        em.getTransaction().begin();
        em.getTransaction().commit();
    }

}
