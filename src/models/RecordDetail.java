package models;

import java.time.LocalDateTime;

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

@Table(name = "record_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecordDetail {
    //ID
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //DailyRecord
    @ManyToOne
    @JoinColumn(name = "daily_record", nullable = false)
    private DailyRecord dailyRecord;

    //食事時間
    @Column(name = "meal_time", nullable = false)
    private Integer mealTime;

    //食事
    @ManyToOne
    @JoinColumn(name = "food", nullable = false)
    private Food food;

    //数量
    @Column(name = "amount", nullable = false)
    private Double amount;

    //登録日時
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    //更新日時
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
