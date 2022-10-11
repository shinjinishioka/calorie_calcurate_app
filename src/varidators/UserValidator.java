package varidators;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    public static List<String> validate(String name, String age, String height, String weight, String bodyFat,
            String targetWeight, String targetProtein,String targetFat,String targetCarbo,String password) {
        List<String> errors = new ArrayList<String>();

        //氏名のチェック
        String nameError = "氏名を入力してください";
        if (name.equals("") || name == null) {
            errors.add(nameError);
        }
        //年齢のチェック
        String ageError = "年齢を入力してください";
        if (age.equals("") || age == null) {
            errors.add(ageError);
        }
        //身長のチェック
        String heightError = "身長を入力してください";
        if (height.equals("") || height == null) {
            errors.add(heightError);
        }
        //体重のチェック
        String weightError = "体重を入力してください";
        if (weight.equals("") || weight == null) {
            errors.add(weightError);
        }
        //体脂肪のチェック
        String bodyFatError = "体脂肪を入力してください";
        if (bodyFat.equals("") || bodyFat == null) {
            errors.add(bodyFatError);
        }
        //目標体重のチェック
        String targetWeightError = "目標体重を入力してください";
        if (targetWeight.equals("") || targetWeight == null) {
            errors.add(targetWeightError);
        }
        //タンパク質割合のチェック
        String targetProteinError = "タンパク質割合を入力してください";
        if (targetProtein.equals("") || targetProtein == null) {
            errors.add(targetProteinError);
        }
        //脂質割合のチェック
        String targetFatError = "タンパク質割合を入力してください";
        if (targetFat.equals("") || targetFat == null) {
            errors.add(targetFatError);
        }
        //糖質割合のチェック
        String targetCarboError = "タンパク質割合を入力してください";
        if (targetCarbo.equals("") || targetCarbo == null) {
            errors.add(targetCarboError);
        }
        //パスワードのチェック
        String passError = "パスワードを入力してください";
        if (password.equals("") || password == null) {
            errors.add(passError);
        }

        return errors;
    }

    public static List<String> changePassValidate(String currentPassword, String oldPassword, String newPassword,
            String passwordCheck) {
        List<String> errors = new ArrayList<String>();
        //現在のパスワードと合っているかチェック
        String wrongPass = "現在のパスワードの入力に誤りがあります";
        if (!currentPassword.equals(oldPassword) || currentPassword == null) {
            errors.add(wrongPass);
        }
        //新しいパスワードのバリデーション
        String checkNewPass = "新しいパスワードの入力に誤りがあります";
        if (newPassword.equals("") || newPassword == null) {
            errors.add(checkNewPass);
        }
        //パスワード再入力チェック
        String checkPass = "パスワードの再入力に誤りがあります";
        if (!newPassword.equals(passwordCheck)) {
            errors.add(checkPass);
        }
        return errors;
    }
}
