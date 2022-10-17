package varidators;

import java.util.ArrayList;
import java.util.List;

public class RecordValidator {
    public static List<String> validate(String mealTime, String food, String amount) {
        List<String> errors = new ArrayList<String>();

        //食事時間のチェック
        String mealTimeError = "食事時間が選択されていません";
        if (mealTime.equals("") || mealTime == null) {
            errors.add(mealTimeError);
        }
        //食品のチェック
        String foodError = "食品が選択されていません";
        if (food.equals("") || food == null) {
            errors.add(foodError);
        }

        //数量のチェック
        String amountError = "数量を入力してください";
        if (amount.equals("") || amount == null) {
            errors.add(amountError);
        }
        //数値かどうかのチェック
        String numberError = "半角数字数字で入力してください";
        try {
            Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            errors.add(numberError);
        }

        return errors;
    }

    public static List<String> validate(String mealTime, String food, String amount, String date) {
        List<String> errors = new ArrayList<String>();

        //食事時間のチェック
        String mealTimeError = "食事時間が選択されていません";
        if (mealTime.equals("") || mealTime == null) {
            errors.add(mealTimeError);
        }
        //食品のチェック
        String foodError = "食品が選択されていません";
        if (food.equals("") || food == null) {
            errors.add(foodError);
        }

        //数量のチェック
        String amountError = "数量を入力してください";
        if (amount.equals("") || amount == null) {
            errors.add(amountError);
        }
        //日付のチェック
        String dateError = "日付を選択してください";
        if (date.equals("") || date == null) {
            errors.add(dateError);
        }
        //数値かどうかのチェック
        String numberError = "半角数字数字で入力してください";
        try {
            Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            errors.add(numberError);
        }
        return errors;
    }

}
