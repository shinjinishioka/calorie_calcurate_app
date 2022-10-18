package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import models.User;
import services.UserService;
import varidators.AuthValidate;

public class AuthAction extends ActionBase {

    private UserService service;

    @Override
    public void process() throws ServletException, IOException {
        service = new UserService();
        invoke();
        service.close();
    }

    public void showLogin() throws ServletException, IOException {
        forward("login/login");
    }

    /**
     * ログイン処理を行う
     * @throws ServletException
     * @throws IOException
     */
    public void login() throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        //バリデーション
        List<String> errors = AuthValidate.validate(name, password);

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            forward("login/login");
        } else {
            User user = service.findLoginUser(name, password);
            if (user != null) {
                //認証成功の場合
                //セッションにログインした従業員を設定
                request.getSession().setAttribute("login_user", user);
                //ログイン完了のフラッシュメッセージを設定
                String flush = "ログインしました";
                request.getSession().setAttribute("flush", flush);
                //トップページへ
                redirect("Top","index");
            } else {
                //失敗の場合
                errors.add("ログインに失敗しました");
                request.setAttribute("errors", errors);
                forward("login/login");
            }

        }

    }

    public void logout() throws ServletException, IOException{
        //セッションからログイン従業員のパラメータを削除
        request.getSession().removeAttribute("login_user");

        //セッションにログアウト時のフラッシュメッセージを追加
        String flush = "ログアウトしました";
        request.setAttribute("flush", flush);

        //ログイン画面にリダイレクト
        forward("login/login");
    }

}
