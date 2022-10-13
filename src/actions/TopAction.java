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

public class TopAction extends ActionBase {
    // private UserService userService;
    private FoodService foodService;
    private RecordService recordService;
    private User user;

    @Override
    public void process() throws ServletException, IOException {
        //   userService = new UserService(); //追記
        foodService = new FoodService();
        recordService = new RecordService();
        user = (User) request.getSession().getAttribute("login_user");
        //メソッドを実行
        invoke();
        recordService.close();
        foodService.close();
        // userService.close();

    }

    public void index() throws ServletException, IOException {
        //top画面表示
        // User user = (User) request.getSession().getAttribute("login_user");
        //期日までの日数取得
        int deadLine = user.deadLine();
        //代謝計算
        double metabolism = user.calculateMetabolism();
        //目標までの体重
        double targetWeight = user.getTargetWeight() - user.getWeight();
        //今日の記録を取得
        LocalDate date = LocalDate.now();
        DailyRecord dailyRecord = recordService.findDailyRecord(user, date);
        List<RecordDetail> recordDetails = recordService.getRecordDetailsByDailyRecordId(dailyRecord);
        //今日のトータルを取得
        DailyTotal dailyTotal = recordService.getDailyTotal(dailyRecord, recordDetails, foodService);
        //推奨カロリーと推奨PFCを取得
        double recommendCalorie = user.calculateRecommendCalorie();
        double recommendProtein = user.recommendProtein();
        double recommendFat = user.recommendFat();
        double recommendCarbo = user.recommendCarbo();

        //記録追加用の食品一覧取得
        List<Food> foods = foodService.getAllFoodsByUser(user);
        //直近の記録取得
        List<DailyRecord> dailyRecords = recordService.getDailyRecordsByUser(user);
        List<RecordDetail> details = new ArrayList<RecordDetail>();
        //デイリーレコードのリストのすべてのidに紐づいたレコード詳細を取得
        for (DailyRecord dr : dailyRecords) {
            List<RecordDetail> getDetails = recordService.getRecordDetailsByDailyRecordId(dr);
            details.addAll(getDetails);
        }

        request.setAttribute("recordDetails", details);
        request.setAttribute("dailyTotal", dailyTotal);
        request.setAttribute("foods", foods);
        request.setAttribute("targetWeight", targetWeight);
        request.setAttribute("metabolism", metabolism);
        request.setAttribute("deadLine", deadLine);
        request.setAttribute("recommendCalorie", recommendCalorie);
        request.setAttribute("recommendProtein", recommendProtein);
        request.setAttribute("recommendCarbo", recommendCarbo);
        request.setAttribute("recommendFat", recommendFat);
        forward("topPage/index");
    }

}
