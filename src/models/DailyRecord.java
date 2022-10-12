package models;

import java.time.LocalDate;

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

@Table(name = "daily_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DailyRecord {
    //ID
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //ユーザー
    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    //日付
    @Column(name = "date", nullable = false)
    private LocalDate date;

}
