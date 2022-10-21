package actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import models.Food;
import models.User;
import services.FoodService;
import services.UserService;
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
                    Double.parseDouble(request.getParameter("fat")), Double.parseDouble(request.getParameter("carbo")),
                    0);
            service.create(food);
            String flush = "登録しました";
            request.setAttribute("flush", flush);
            //  request.setAttribute("food", food);
            show();
        }
    }

    //一覧表示
    public void show() throws ServletException, IOException {
        List<Food> foodsCount = service.getAllFoodsByUser(user);
        //ページネーション用
        int count = foodsCount.size();
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Food> foods = service.getAllFoodsByUserPerPage(user, page);

        request.setAttribute("count", count);
        request.setAttribute("foods", foods);
        forward("foods/show");
    }

    //編集画面表示
    public void edit() throws ServletException, IOException {
        String id = request.getParameter("id");
        Food food = service.findOne(id);
        if (food.getUser().getId() != user.getId()) {
            show();
        }
        request.setAttribute("food", food); //取得したユーザー情報
        forward("foods/edit");
    }

    //食品情報変更
    public void update() throws ServletException, IOException {

        //登録内容のバリデーションを行う
        List<String> errors = FoodValidator.validate(request.getParameter("name"), request.getParameter("unit"),
                request.getParameter("caloriePerUnit"),
                request.getParameter("protein"), request.getParameter("fat"), request.getParameter("carbo"));
        //ユーザーが登録した食品か判断
        Food f = service.findOne(request.getParameter("id"));
        if (f.getUser().getId() != user.getId()) {
            errors.add("変更できませんでした");
        }

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            forward("foods/edit");
        } else {
            Food food = new Food(Integer.parseInt(request.getParameter("id")), request.getParameter("name"), user,
                    request.getParameter("unit"),
                    Double.parseDouble(request.getParameter("caloriePerUnit")),
                    Double.parseDouble(request.getParameter("protein")),
                    Double.parseDouble(request.getParameter("fat")), Double.parseDouble(request.getParameter("carbo")),
                    null);

            service.update(food);
            String flush = "変更しました";
            request.setAttribute("flush", flush);

            //passwordにペッパー文字追加
            show();
        }

    }

    //削除機能
    public void delete() throws ServletException, IOException {
        //ユーザーが登録した食品のみ削除する
        Food food = service.findOne(request.getParameter("id"));
        String flush = "削除しました";
        if (food.getUser().getId() == user.getId()) {
            service.delete(request.getParameter("id"));
            request.setAttribute("flush", flush);
        } else {
            List<String> errors = new ArrayList<String>();
            errors.add("削除できませんでした");
            request.setAttribute("errors", errors);
        }

        //一覧画面に遷移
        show();
    }

    //食品の初期登録用 ?action=Food&command=createDefaultFoods
    public void createDefaultFoods() throws ServletException, IOException {
        UserService userService = new UserService();
        User master = userService.findOne("1");

        //name,user,unit,caloriePerUnit,protein,fat,carbo,deleteFlag
        List<Food> foods = new ArrayList<Food>();
        Food rice = new Food(null, "米", master, "合", 534.0, 9.15, 1.35, 115.65,
                0);
        foods.add(rice);
        Food bread = new Food(null, "食パン", master, "枚(6枚切)", 158.0, 5.58, 2.64, 28.02,
                0);
        foods.add(bread);
        Food meat = new Food(null, "牛肉", master, "100g", 371.0, 14.4, 32.9, 0.2,
                0);
        foods.add(meat);
        Food chiken = new Food(null, "鶏むね肉", master, "100g", 191.0, 19.5, 11.6, 0.0,
                0);
        foods.add(chiken);
        Food pork = new Food(null, "豚バラ肉", master, "100g", 386.0, 14.2, 34.6, 0.1,
                0);
        foods.add(pork);
        Food egg = new Food(null, "卵", master, "個", 91.0, 7.38, 6.18, 0.18,
                0);
        foods.add(egg);
        Food tofu = new Food(null, "絹ごし豆腐", master, "丁", 168.0, 14.7, 9.0, 6.0,
                0);
        foods.add(tofu);
        Food nattou = new Food(null, "納豆", master, "パック", 100.0, 8.25, 5.0, 6.05,
                0);
        foods.add(nattou);

        for (Food food : foods) {
            service.create(food);
        }
    }
}
