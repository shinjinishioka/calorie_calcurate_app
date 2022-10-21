package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "foods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Food {
    //ID
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //名前
    @Column(name = "name", nullable = false)
    private String name;

    //ユーザー
    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    //単位
    @Column(name = "unit", nullable = false)
    private String unit;

    //単位あたりのカロリー
    @Column(name = "calorie_per_unit", nullable = false)
    private Double caloriePerUnit;

    //タンパク質
    @Column(name = "protein", nullable = false)
    private Double protein;

    //脂質
    @Column(name = "fat", nullable = false)
    private Double fat;

    //糖質
    @Column(name = "carbo", nullable = false)
    private Double carbo;

    //論理削除
    @Column(name = "delete_flag", nullable = false)
    private Integer deleteFlag;

}
