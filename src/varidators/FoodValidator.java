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
        String unitError = "年齢を入力してください";
        if (unit.equals("") || unit == null) {
            errors.add(unitError);
        }
        //カロリーのチェック
        String caloriePerUnitError = "身長を入力してください";
        if (caloriePerUnit.equals("") || caloriePerUnit == null) {
            errors.add(caloriePerUnitError);
        }

        //タンパク質割合のチェック
        String proteinError = "タンパク質割合を入力してください";
        if (protein.equals("") || protein == null) {
            errors.add(proteinError);
        }
        //脂質割合のチェック
        String fatError = "タンパク質割合を入力してください";
        if (fat.equals("") || fat == null) {
            errors.add(fatError);
        }
        //糖質割合のチェック
        String carboError = "タンパク質割合を入力してください";
        if (carbo.equals("") || carbo == null) {
            errors.add(carboError);
        }

        return errors;
    }

}
