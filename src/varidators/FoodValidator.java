package varidators;

import java.util.ArrayList;
import java.util.List;

public class FoodValidator {
    public static List<String> validate(String name, String unit, String caloriePerUnit, String protein, String fat,
            String carbo) {
        List<String> errors = new ArrayList<String>();

        //氏名のチェック
        String nameError = "食品名を入力してください";
        if (name.equals("") || name == null) {
            errors.add(nameError);
        }
        //単位のチェック
        String unitError = "単位を入力してください";
        if (unit.equals("") || unit == null) {
            errors.add(unitError);
        }
        //カロリーのチェック
        String caloriePerUnitError = "カロリーを入力してください";
        if (caloriePerUnit.equals("") || caloriePerUnit == null) {
            errors.add(caloriePerUnitError);
        }

        //タンパク質割合のチェック
        String proteinError = "タンパク質を入力してください";
        if (protein.equals("") || protein == null) {
            errors.add(proteinError);
        }
        //脂質割合のチェック
        String fatError = "脂質を入力してください";
        if (fat.equals("") || fat == null) {
            errors.add(fatError);
        }
        //糖質割合のチェック
        String carboError = "糖質を入力してください";
        if (carbo.equals("") || carbo == null) {
            errors.add(carboError);
        }

        //数値かどうかのチェック
        String numberError = "半角数字数字で入力してください";
        try {
            Double.parseDouble(caloriePerUnit);
            Double.parseDouble(protein);
            Double.parseDouble(fat);
            Double.parseDouble(carbo);

        } catch (NumberFormatException e) {
            errors.add(numberError);
        }

        return errors;
    }

}
