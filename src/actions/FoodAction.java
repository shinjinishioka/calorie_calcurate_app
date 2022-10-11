package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import models.Food;
import models.User;
import services.FoodService;
import varidators.FoodValidator;

public class FoodAction extends ActionBase {
    private FoodService service;
    private User user;

    @Override
    public void process() throws ServletException, IOException {
        service = new FoodService();
        user = (User) request.getSession().getAttribute("login_user");
        invoke();
        service.close();
    }

    public void entryNew() throws ServletException, IOException {
        //新規登録
        forward("foods/new");
    }

    public void create() throws ServletException, IOException {
        //登録内容のバリデーションを行う

        List<String> errors = FoodValidator.validate(request.getParameter("name"), request.getParameter("unit"),
                request.getParameter("caloriePerUnit"),
                request.getParameter("protein"), request.getParameter("fat"), request.getParameter("carbo"));

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            forward("foods/new");
        } else {
            //name,user,unit,caloriePerUnit,protein,fat,carbo
            Food food = new Food(null, request.getParameter("name"), user, request.getParameter("unit"),
                    Double.parseDouble(request.getParameter("caloriePerUnit")),
                    Double.parseDouble(request.getParameter("protein")),
                    Double.parseDouble(request.getParameter("fat")), Double.parseDouble(request.getParameter("carbo")));
            service.create(food);

            request.setAttribute("food", food);
            show();
        }
    }

    public void show() throws ServletException, IOException {
        List<Food> foods = service.getAllFoodsByUser(user);
        request.setAttribute("foods", foods);
        forward("foods/show");
    }

}
