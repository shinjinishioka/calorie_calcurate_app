package models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyTotal {
    //日付
    private LocalDate date;
    //カロリー合計
    private Double totalCalorie;
    //タンパク質合計
    private Double totalProtein;
    //脂質合計
    private Double totalFat;
    //糖質合計
    private Double totalCarbo;
}
