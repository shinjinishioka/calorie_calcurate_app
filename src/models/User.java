package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    //代謝の計算
    public double calculateMetabolism() {
        //基礎代謝の計算
        //男性：66.47＋体重×13.75＋身長×5.0－年齢×6.76＝基礎代謝量とする。
        //女性：655.1＋体重×9.56＋身長×1.85－年齢×4.68＝基礎代謝量とする。
        //基礎代謝量×活動レベル＝総消費カロリー
        //活動レベル低＝1.3　中1.5　高1.8
        double metabolism;
        double activityLevel = 1.3;
        if (getActivityLevel() == 1) {
            activityLevel = 1.3;
        } else if (getActivityLevel() == 2) {
            activityLevel = 1.5;
        } else if (getActivityLevel() == 3) {
            activityLevel = 1.8;
        }

        if (getSex() == 1) {
            metabolism = (66.47 + (getWeight() * 13.75) + (getHeight() * 5) - (getAge() * 6.78))
                    * activityLevel;
        } else {
            metabolism = (655.1 + (getWeight() * 9.56) + (getHeight() * 1.85) - (getAge() * 4.68))
                    * activityLevel;
        }
        return metabolism;

    }

    //目標までの一日の推奨カロリー計算
    public double calculateRecommendCalorie() {
        //目標までの体重
        double targetWeight = getTargetWeight() -getWeight();
        //目標までの期日
        int deadLine = deadLine();
        //1kg増減させるのに必要なカロリー
        int calorieChangePerWeight = 7200;
        double metabolism = calculateMetabolism();
        //目標までの日数から求めた基礎代謝から増減させる摂取カロリー
        double recommendCalorie = metabolism - (targetWeight * calorieChangePerWeight) / deadLine;

        return recommendCalorie;
    }

    //推奨摂取タンパク質量計算
    public double recommendProtein() {
        //目標摂取タンパク質割合を取得
        double targetProtein = getTargetProtein();
        double recommendCalorie = calculateRecommendCalorie();
        double recommendProtein = (recommendCalorie * (targetProtein * 0.01)) / 4;
        return recommendProtein;
    }

    //推奨摂取脂質量計算
    public double recommendFat() {
        //目標摂取タンパク質割合を取得
        double targetFat = getTargetFat();
        double recommendCalorie = calculateRecommendCalorie();
        double recommendFat = (recommendCalorie * (targetFat * 0.01)) / 9;
        return recommendFat;
    }

    //推奨摂取糖質量計算
    public double recommendCarbo() {
        //目標摂取タンパク質割合を取得
        double targetCarbo = getTargetCarbo();
        double recommendCalorie = calculateRecommendCalorie();
        double recommendCarbo = (recommendCalorie * (targetCarbo * 0.01)) / 4;
        return recommendCarbo;
    }

    //目標期日の計算
    public int deadLine() {
        //目標期日までの残り日数の計算
        LocalDate period = getPeriod();
        LocalDate today = LocalDate.now();
        int deadLine = (int) ChronoUnit.DAYS.between(today, period);
        return deadLine;
    }
}
