package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    //ID
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //名前
    @Column(name = "name", nullable = false)
    private String name;

    //年齢
    @Column(name = "age", nullable = false)
    private Integer age;

    //性別
    @Column(name = "sex", nullable = false)
    private Integer sex;

    //身長
    @Column(name = "height", nullable = false)
    private Double height;

    //体重
    @Column(name = "weight", nullable = false)
    private Double weight;

    //体脂肪
    @Column(name = "body_fat")
    private Double bodyFat;

    //目標体重
    @Column(name = "target_weight")
    private Double targetWeight;

    //活動レベル
    @Column(name = "activity_level")
    private Integer activityLevel;

    //目標期日
    @Column(name = "period")
    private LocalDate period;

    //タンパク質割合目安
    @Column(name = "target_protein")
    private Double targetProtein;

    //脂質割合目安
    @Column(name = "target_fat")
    private Double targetFat;

    //糖質割合目安
    @Column(name = "target_carbo")
    private Double targetCarbo;

    //登録日時
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    //更新日時
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    //パスワード
    @Column(name = "password", length = 64, nullable = false)
    private String password;

    //論理削除
    @Column(name = "delete_flag", nullable = false)
    private Integer deleteFlag;

}
