package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;

import models.DailyRecord;
import models.RecordDetail;
import models.User;

public class RecordService extends ServiceBase {
    public void createDailyRecord(DailyRecord dailyRecord) {
        em.getTransaction().begin();
        em.persist(dailyRecord);
        em.getTransaction().commit();
    }

    public void createRecordDetail(RecordDetail recordDetail) {
        //登録日時、Update日時を設定
        LocalDateTime now = LocalDateTime.now();
        recordDetail.setCreatedAt(now);
        recordDetail.setUpdatedAt(now);

        em.getTransaction().begin();
        em.persist(recordDetail);
        em.getTransaction().commit();
    }

    //ログインユーザー取得
    public DailyRecord findDailyRecord(User user, LocalDate date) {
        DailyRecord dailyRecord = null;
        try {
            //ログインユーザーと今日の日付で検索  + " AND d.date ='" + date + "'"  d.user = " + user.getId()
            dailyRecord = em
                    .createQuery(
                            "SELECT d FROM DailyRecord d WHERE d.user =" + user.getId() + " AND d.date ='" + date + "'",
                            DailyRecord.class)
                    .getSingleResult();

        } catch (NoResultException ex) {
        }

        return dailyRecord;
    }

    public List<DailyRecord> getDailyRecordsByUser(User user) {
        List<DailyRecord> dailyRecords = em
                .createQuery("SELECT d FROM DailyRecord d WHERE d.user =" + user.getId(), DailyRecord.class)
                .getResultList();
        return dailyRecords;
    }

    public List<RecordDetail> getRecordDetailsByDailyRecordId(DailyRecord dailyRecord) {
        List<RecordDetail> recordDetails = em
                .createQuery("SELECT r FROM RecordDetail r WHERE r.dailyRecord =" + dailyRecord.getId(),
                        RecordDetail.class)
                .getResultList();
        return recordDetails;
    }

    public RecordDetail findOne(String id) {
        RecordDetail recordDetail = em.createQuery("SELECT r FROM RecordDetail r WHERE r.id =" + id, RecordDetail.class)
                .getSingleResult();
        return recordDetail;
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
