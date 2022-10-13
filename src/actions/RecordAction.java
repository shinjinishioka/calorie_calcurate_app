package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import models.DailyRecord;
import models.DailyTotal;
import models.Food;
import models.RecordDetail;
import models.User;
import services.FoodService;
import services.RecordService;
import varidators.RecordValidator;

public class RecordAction extends ActionBase {
    private RecordService service;
    private User user;

    @Override
    public void process() throws ServletException, IOException {
        service = new RecordService();
        user = (User) request.getSession().getAttribute("login_user");
        invoke();
        service.close();
    }

    //食事記録の追加
    public void create() throws ServletException, IOException {
        //登録内容のバリデーションを行う

        List<String> errors = RecordValidator.validate(request.getParameter("mealTime"),
                request.getParameter("food"), request.getParameter("amount"));
        FoodService fs = new FoodService();
        Food food = fs.findOne(request.getParameter("food"));

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            forward("topPage/index");
        } else {
            DailyRecord dailyRecord;
            LocalDate date = LocalDate.now();
            //デイリーレコード検索
            dailyRecord = service.findDailyRecord(user, date);
            //デイリーレコードがなければ作成
            if (dailyRecord == null) {
                //id,user,date
                dailyRecord = new DailyRecord(null, user, date);
                service.createDailyRecord(dailyRecord);
            }
            //レコード詳細作成
            //id,daily_record,meal_time,food,amount,created_at,updated_at
            RecordDetail recordDetail = new RecordDetail(null, dailyRecord,
                    Integer.parseInt(request.getParameter("mealTime")), food,
                    Double.parseDouble(request.getParameter("amount")), null, null);
            service.createRecordDetail(recordDetail);

            String flush = "登録しました";
            request.setAttribute("flush", flush);
            fs.close();
            forward("topPage/index");
        }
    }

    //一覧表示
    public void show() throws ServletException, IOException {
        List<DailyRecord> dailyRecords = service.getDailyRecordsByUser(user);

        List<RecordDetail> recordDetails = new ArrayList<RecordDetail>();
        //デイリーレコードのリストのすべてのidに紐づいたレコード詳細を取得
        for (DailyRecord dailyRecord : dailyRecords) {
            List<RecordDetail> getDetails = service.getRecordDetailsByDailyRecordId(dailyRecord);
            recordDetails.addAll(getDetails);
        }
        request.setAttribute("recordDetails", recordDetails);

        forward("records/show");
    }

    //日別記録一覧表示
    public void showDailyRecord() throws ServletException, IOException {
        List<DailyRecord> dailyRecords = service.getDailyRecordsByUser(user);
        List<DailyTotal> dailyTotals = new ArrayList<DailyTotal>();
        FoodService fs = new FoodService();

        //ユーザーのデイリーレコードすべて取得し
        for (DailyRecord dailyRecord : dailyRecords) {
            List<RecordDetail> recordDetails = service.getRecordDetailsByDailyRecordId(dailyRecord);
            DailyTotal dailyTotal = new DailyTotal();
            dailyTotal = service.getDailyTotal(dailyRecord, recordDetails, fs);

            dailyTotals.add(dailyTotal);

        }

        //一日推奨カロリーPFCを取得

        double recommendCalorie = user.calculateRecommendCalorie();
        double recommendProtein = user.recommendProtein();
        double recommendFat = user.recommendFat();
        double recommendCarbo = user.recommendCarbo();

        request.setAttribute("recommendCalorie", recommendCalorie);
        request.setAttribute("recommendProtein", recommendProtein);
        request.setAttribute("recommendCarbo", recommendCarbo);
        request.setAttribute("recommendFat", recommendFat);
        request.setAttribute("dailyTotals", dailyTotals);
        fs.close();
        forward("records/showDailyRecord");
    }

    //編集画面表示
    public void edit() throws ServletException, IOException {
        String id = request.getParameter("id");
        RecordDetail recordDetail = service.findOne(id);
        FoodService foodService = new FoodService();
        List<Food> foods = foodService.getAllFoodsByUser(user);
        request.setAttribute("foods", foods);
        request.setAttribute("recordDetail", recordDetail);
        foodService.close();
        forward("records/edit");
    }

    //記録変更
    public void update() throws ServletException, IOException {
        //登録内容のバリデーションを行う
        List<String> errors = RecordValidator.validate(request.getParameter("mealTime"),
                request.getParameter("food"), request.getParameter("amount"), request.getParameter("date"));

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            forward("records/edit");
        } else {
            FoodService fs = new FoodService();
            Food food = fs.findOne(request.getParameter("food"));

            //日付からデイリーレコード検索
            DailyRecord dailyRecord;
            LocalDate date = LocalDate.parse(request.getParameter("date"));

            //デイリーレコード検索
            dailyRecord = service.findDailyRecord(user, date);
            //デイリーレコードがなければ作成
            if (dailyRecord == null) {
                //id,user,date
                dailyRecord = new DailyRecord(null, user, date);
                service.createDailyRecord(dailyRecord);
            }

            RecordDetail recordDetail = new RecordDetail(Integer.parseInt(request.getParameter("id")), dailyRecord,
                    Integer.parseInt(request.getParameter("mealTime")), food,
                    Double.parseDouble(request.getParameter("amount")), null, null);

            service.update(recordDetail);

            String flush = "変更しました";
            request.setAttribute("flush", flush);

            //passwordにペッパー文字追加
            show();
        }
    }

    //削除機能
    public void delete() throws ServletException, IOException {
        service.delete(request.getParameter("id"));
        String flush = "削除しました";
        request.setAttribute("flush", flush);
        //一覧画面に遷移
        show();
    }

}
