package varidators;

import java.util.ArrayList;
import java.util.List;

public class AuthValidate {
    public static List<String> validate(String name, String password) {
        List<String> errors = new ArrayList<String>();

        //氏名のチェック
        String nameError = "氏名を入力してください";
        if (name.equals("") || name == null) {
            errors.add(nameError);
        }

        //パスワードのチェック
        String passError = "パスワードを入力してください";
        if (password.equals("") || password == null) {
            errors.add(passError);
        }


        return errors;
    }
}
