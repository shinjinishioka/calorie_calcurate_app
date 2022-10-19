package varidators;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    public static List<String> validate(String name, String age, String height, String weight, String bodyFat,
            String targetWeight, String period, String targetProtein, String targetFat, String targetCarbo,
            String password) {
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
        //目標期日のチェック
        String periodError = "目標期日を入力してください";
        LocalDate today = LocalDate.now();
        if (period.equals("") || period == null) {
            errors.add(periodError);
        } else {
            String formatError = "日付をyyyy-mm-ddの形式で入力してください";
             period = period.replace("/", "-");
            try {
                if (LocalDate.parse(period).isBefore(today)) {
                    periodError = "本日以降の日付を選択してください";
                    errors.add(periodError);
                }
            } catch (Exception e) {
                errors.add(formatError);
            }

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
        //PFCバランスの合計が100かチェック
        String pfcError = "たんぱく質、脂質、糖質の割合の合計が100になるように入れてください";
        try {
            if (Double.parseDouble(targetProtein) + Double.parseDouble(targetFat)
                    + Double.parseDouble(targetCarbo) != 100) {
                errors.add(pfcError);
            }
        } catch (NumberFormatException e) {
        }
        //パスワードのチェック
        String passError = "パスワードを入力してください";
        if (password.equals("") || password == null) {
            errors.add(passError);
        }
        //数値かどうかのチェック
        String numberError = "半角数字で入力してください";
        try {
            Double.parseDouble(age);
            Double.parseDouble(height);
            Double.parseDouble(weight);
            Double.parseDouble(bodyFat);
            Double.parseDouble(targetWeight);
            Double.parseDouble(targetProtein);
            Double.parseDouble(targetFat);
            Double.parseDouble(targetCarbo);

        } catch (NumberFormatException e) {
            errors.add(numberError);
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
