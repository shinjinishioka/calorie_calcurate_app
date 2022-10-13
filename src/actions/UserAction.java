package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import models.User;
import services.UserService;
import varidators.UserValidator;

public class UserAction extends ActionBase {
    private UserService service;

    @Override
    public void process() throws ServletException, IOException {
        service = new UserService();
        invoke();
        service.close();

    }

    public void entryNew() throws ServletException, IOException {
        //新規登録
        forward("users/new");
    }

    public void create() throws ServletException, IOException {
        //登録内容のバリデーションを行う
        List<String> errors = UserValidator.validate(request.getParameter("name"), request.getParameter("age"),
                request.getParameter("height"),
                request.getParameter("weight"), request.getParameter("bodyFat"), request.getParameter("targetWeight"),
                request.getParameter("targetProtein"), request.getParameter("targetFat"),
                request.getParameter("targetCarbo"), request.getParameter("password"));
        //パスワード再入力チェック
        if (!request.getParameter("password").equals(request.getParameter("passwordCheck"))) {
            errors.add("パスワードの再入力に誤りがあります");
        }

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            forward("users/new");
        } else {
            //name,age,sex,height,weight,bodyFat,targetWeight,activityLevel,period,targetProtein,targetFat,targetCarbo,createdAt,UpdatedAt,password,deleteFlag
            User user = new User(null, request.getParameter("name"), Integer.parseInt(request.getParameter("age")),
                    Integer.parseInt(request.getParameter("sex")), Double.parseDouble(request.getParameter("height")),
                    Double.parseDouble(request.getParameter("weight")),
                    Double.parseDouble(request.getParameter("bodyFat")),
                    Double.parseDouble(request.getParameter("targetWeight")),
                    Integer.parseInt(request.getParameter("activityLevel")),
                    LocalDate.parse(request.getParameter("period")),
                    Double.parseDouble(request.getParameter("targetProtein")),
                    Double.parseDouble(request.getParameter("targetFat")),
                    Double.parseDouble(request.getParameter("targetCarbo")), null, null,
                    request.getParameter("password"), 0);
            service.create(user);

            //passwordにペッパー文字追加
            request.getSession().setAttribute("login_user", user);
            request.setAttribute("user", user);
            forward("users/profile");
        }
    }

    public void show() throws ServletException, IOException {

        //idを条件に従業員データを取得する
        User user = (User) request.getSession().getAttribute("login_user");
        String sex = "男";
        if (user.getSex() == 2) {
            sex = "女";
        }

        String activityLevel = "低";
        if (user.getActivityLevel() == 2) {
            activityLevel = "中";
        } else if (user.getActivityLevel() == 3) {
            activityLevel = "高";
        }
        request.setAttribute("sex", sex); //取得した性別
        request.setAttribute("activityLevel", activityLevel); //取得した活動レベル
        // request.setAttribute("user", user); //取得したユーザー情報

        //詳細画面を表示
        forward("users/profile");

    }

    public void delete() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("login_user");
        service.delete(user.getId().toString());

        String flush = "退会しました";
        request.setAttribute("flush", flush);
        //セッションを削除
        request.getSession().removeAttribute("login_user");

        //ログイン画面に遷移
        forward("login/login");
    }

    public void edit() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("login_user");
        // String user = request.getParameter("id");
        request.setAttribute("user", user); //取得したユーザー情報
        forward("users/edit");
    }

    public void changePass() throws ServletException, IOException {
        forward("users/changePass");
    }

    public void updatePass() throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("login_user");
        //バリデーション
        List<String> errors = UserValidator.changePassValidate(request.getParameter("currentPassword"),
                user.getPassword(), request.getParameter("newPassword"), request.getParameter("passwordCheck"));

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            forward("users/changePass");
        } else {
            String newPassword = request.getParameter("newPassword");
            service.changePass(user.getId().toString(), newPassword);
            //詳細画面を表示
            forward("users/profile");
        }
    }

    public void update() throws ServletException, IOException {
        //登録内容のバリデーションを行う
        List<String> errors = UserValidator.validate(request.getParameter("name"), request.getParameter("age"),
                request.getParameter("height"),
                request.getParameter("weight"), request.getParameter("bodyFat"), request.getParameter("targetWeight"),
                request.getParameter("targetProtein"), request.getParameter("targetFat"),
                request.getParameter("targetCarbo"), request.getParameter("password"));

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            forward("users/edit");
        } else {
            //name,age,sex,height,weight,bodyFat,targetWeight,activityLevel,period,targetProtein,targetFat,targetCarbo,createdAt,UpdatedAt,password
            User user = new User(Integer.parseInt(request.getParameter("id")), request.getParameter("name"),
                    Integer.parseInt(request.getParameter("age")),
                    Integer.parseInt(request.getParameter("sex")), Double.parseDouble(request.getParameter("height")),
                    Double.parseDouble(request.getParameter("weight")),
                    Double.parseDouble(request.getParameter("bodyFat")),
                    Double.parseDouble(request.getParameter("targetWeight")),
                    Integer.parseInt(request.getParameter("activityLevel")),
                    LocalDate.parse(request.getParameter("period")),
                    Double.parseDouble(request.getParameter("targetProtein")),
                    Double.parseDouble(request.getParameter("targetFat")),
                    Double.parseDouble(request.getParameter("targetCarbo")), null, null,
                    request.getParameter("password"),
                    null);

            service.update(user);
            user = service.findOne(user.getId().toString());
            request.getSession().setAttribute("login_user", user);

            //passwordにペッパー文字追加
            show();
        }

    }



}
