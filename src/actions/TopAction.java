package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.ServletException;

import models.Food;
import models.User;
import services.FoodService;

public class TopAction extends ActionBase {
    // private UserService userService;
    private FoodService foodService;
    private User user;

    @Override
    public void process() throws ServletException, IOException {
        //   userService = new UserService(); //追記
        foodService = new FoodService();
        user = (User) request.getSession().getAttribute("login_user");
        //メソッドを実行
        invoke();
        foodService.close();
        // userService.close();

    }

    public void index() throws ServletException, IOException {
        //top画面表示
       // User user = (User) request.getSession().getAttribute("login_user");

        //目標期日までの残り日数の計算
        LocalDate period = user.getPeriod();
        LocalDate today = LocalDate.now();
        int deadLine = (int) ChronoUnit.DAYS.between(today, period);
        //基礎代謝の計算
        //男性：66.47＋体重×13.75＋身長×5.0－年齢×6.76＝基礎代謝量とする。
        //女性：655.1＋体重×9.56＋身長×1.85－年齢×4.68＝基礎代謝量とする。
        //基礎代謝量×活動レベル＝総消費カロリー
        //活動レベル低＝1.3　中1.5　高1.8
        double metabolism;
        double activityLevel = 1.3;
        if (user.getActivityLevel() == 1) {
            activityLevel = 1.3;
        } else if (user.getActivityLevel() == 2) {
            activityLevel = 1.5;
        } else if (user.getActivityLevel() == 3) {
            activityLevel = 1.8;
        }

        if (user.getSex() == 1) {
            metabolism = (66.47 + (user.getWeight() * 13.75) + (user.getHeight() * 5) - (user.getAge() * 6.78))
                    * activityLevel;
        } else {
            metabolism = (655.1 + (user.getWeight() * 9.56) + (user.getHeight() * 1.85) - (user.getAge() * 4.68))
                    * activityLevel;
        }

        //目標までの体重
        double targetWeight = user.getTargetWeight() - user.getWeight();

        List<Food> foods = foodService.getAllFoodsByUser(user);
        request.setAttribute("foods", foods);
        request.setAttribute("targetWeight", targetWeight);
        request.setAttribute("metabolism", metabolism);
        request.setAttribute("deadLine", deadLine);
        // request.setAttribute("user", user);
        // request.getSession().setAttribute("user", user);
        forward("topPage/index");
    }

}
