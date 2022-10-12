package varidators;

import java.util.ArrayList;
import java.util.List;

public class RecordValidator {
    public static List<String> createValidate(String mealTime, String food, String amount) {
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

        return errors;
    }

}
