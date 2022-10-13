package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;

import models.DailyRecord;
import models.DailyTotal;
import models.Food;
import models.RecordDetail;
import models.User;

public class RecordService extends ServiceBase {
    //デイリーレコード作成
    public void createDailyRecord(DailyRecord dailyRecord) {
        em.getTransaction().begin();
        em.persist(dailyRecord);
        em.getTransaction().commit();
    }

    //レコード詳細作成
    public void createRecordDetail(RecordDetail recordDetail) {
        //登録日時、Update日時を設定
        LocalDateTime now = LocalDateTime.now();
        recordDetail.setCreatedAt(now);
        recordDetail.setUpdatedAt(now);

        em.getTransaction().begin();
        em.persist(recordDetail);
        em.getTransaction().commit();
    }

    //日付からデイリーレコード取得
    public DailyRecord findDailyRecord(User user, LocalDate date) {
        DailyRecord dailyRecord = null;
        try {
            //ログインユーザーと今日の日付で検索
            dailyRecord = em
                    .createQuery(
                            "SELECT d FROM DailyRecord d WHERE d.user =" + user.getId() + " AND d.date ='" + date + "'",
                            DailyRecord.class)
                    .getSingleResult();

        } catch (NoResultException ex) {
        }

        return dailyRecord;
    }

    //ユーザーからデイリーレコード全件取得
    public List<DailyRecord> getDailyRecordsByUser(User user) {
        List<DailyRecord> dailyRecords = em
                .createQuery("SELECT d FROM DailyRecord d WHERE d.user =" + user.getId(), DailyRecord.class)
                .getResultList();
        return dailyRecords;
    }

    //デイリーレコードに紐づく詳細全件を取得
    public List<RecordDetail> getRecordDetailsByDailyRecordId(DailyRecord dailyRecord) {
        List<RecordDetail> recordDetails = em
                .createQuery("SELECT r FROM RecordDetail r WHERE r.dailyRecord =" + dailyRecord.getId(),
                        RecordDetail.class)
                .getResultList();
        return recordDetails;
    }

    //レコード詳細をIDから取得
    public RecordDetail findOne(String id) {
        RecordDetail recordDetail = em.createQuery("SELECT r FROM RecordDetail r WHERE r.id =" + id, RecordDetail.class)
                .getSingleResult();
        return recordDetail;
    }

    //レコード詳細リストから合計を取得
    public DailyTotal getDailyTotal(DailyRecord dailyRecord, List<RecordDetail> recordDetails,FoodService fs) {
        DailyTotal dailyTotal = new DailyTotal();
        double totalCalorie = 0;
        double totalProtein = 0;
        double totalFat = 0;
        double totalCarbo = 0;

        dailyTotal.setDate(dailyRecord.getDate());
        //レコード詳細からトータルのPFCとカロリーを算出し日ごとに合計
        for (RecordDetail recordDetail : recordDetails) {
            Food food = fs.findOne(recordDetail.getFood().getId().toString());
            totalCalorie = totalCalorie + food.getCaloriePerUnit() * recordDetail.getAmount();
            totalProtein = totalProtein + food.getProtein() * recordDetail.getAmount();
            totalFat = totalFat + food.getFat() * recordDetail.getAmount();
            totalCarbo = totalCarbo + food.getCarbo() * recordDetail.getAmount();
        }
        dailyTotal.setTotalCalorie(totalCalorie);
        dailyTotal.setTotalProtein(totalProtein);
        dailyTotal.setTotalFat(totalFat);
        dailyTotal.setTotalCarbo(totalCarbo);
        return dailyTotal;
    }

    //アップデート処理
    public void update(RecordDetail recordDetail) {
        //アップデート時間更新の為
        LocalDateTime now = LocalDateTime.now();
        RecordDetail savedRecordDetail = findOne(recordDetail.getId().toString());
        savedRecordDetail.setDailyRecord(recordDetail.getDailyRecord());
        savedRecordDetail.setMealTime(recordDetail.getMealTime());
        savedRecordDetail.setFood(recordDetail.getFood());
        savedRecordDetail.setAmount(recordDetail.getAmount());
        savedRecordDetail.setUpdatedAt(now);

        em.getTransaction().begin();
        em.getTransaction().commit();
    }

    //削除処理
    public void delete(String id) {
        RecordDetail recordDetail = findOne(id);
        em.getTransaction().begin();
        em.remove(recordDetail);
        em.getTransaction().commit();
    }

    /*
    public DailyRecord findOne(String id) {
        DailyRecord dailyRecord = em.createQuery("SELECT d FROM DailyRecord d WHERE d.id =" + id, DailyRecord.class)
                .getSingleResult();
        return dailyRecord;
    }


    public void delete(String id) {
        DailyRecord dailyRecord = findOne(id);
        em.getTransaction().begin();
        em.remove(dailyRecord);
        em.getTransaction().commit();
    }
        */

}
