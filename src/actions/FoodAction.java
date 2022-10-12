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

    //新規登録画面表示
    public void entryNew() throws ServletException, IOException {
        //新規登録
        forward("foods/new");
    }

    //食品の新規登録
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
            String flush = "登録しました";
            request.setAttribute("flush", flush);
            //  request.setAttribute("food", food);
            show();
        }
    }

    //一覧表示
    public void show() throws ServletException, IOException {
        List<Food> foods = service.getAllFoodsByUser(user);
        // Food food = service.findOne("1");
        request.setAttribute("foods", foods);
        forward("foods/show");
    }

    //編集画面表示
    public void edit() throws ServletException, IOException {
        String id = request.getParameter("id");
        Food food = service.findOne(id);
        request.setAttribute("food", food); //取得したユーザー情報
        forward("foods/edit");
    }

    //食品情報変更
    public void update() throws ServletException, IOException {
        //登録内容のバリデーションを行う
        List<String> errors = FoodValidator.validate(request.getParameter("name"), request.getParameter("unit"),
                request.getParameter("caloriePerUnit"),
                request.getParameter("protein"), request.getParameter("fat"), request.getParameter("carbo"));

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            forward("foods/edit");
        } else {
            Food food = new Food(Integer.parseInt(request.getParameter("id")), request.getParameter("name"), user,
                    request.getParameter("unit"),
                    Double.parseDouble(request.getParameter("caloriePerUnit")),
                    Double.parseDouble(request.getParameter("protein")),
                    Double.parseDouble(request.getParameter("fat")), Double.parseDouble(request.getParameter("carbo")));

            service.update(food);
            String flush = "変更しました";
            request.setAttribute("flush", flush);

            //passwordにペッパー文字追加
            show();
        }

    }

    public void delete() throws ServletException, IOException {
        service.delete(request.getParameter("id"));
        String flush = "削除しました";
        request.setAttribute("flush", flush);
        //一覧画面に遷移
        show();
    }

}
