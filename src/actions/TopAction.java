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

        foodService = new FoodService();
        recordService = new RecordService();
        user = (User) request.getSession().getAttribute("login_user");
        //メソッドを実行
        invoke();
        recordService.close();
        foodService.close();

    }

    //top画面表示
    @SuppressWarnings("unchecked")
    public void index() throws ServletException, IOException {

        //期日までの日数取得
        int deadLine = user.deadLine();
        //代謝計算
        double metabolism = user.calculateMetabolism();
        //目標までの体重
        double targetWeight = user.getTargetWeight() - user.getWeight();

        //今日のトータルを取得
        LocalDate date = LocalDate.now();
        DailyTotal dailyTotal = new DailyTotal();
        DailyRecord dailyRecord = recordService.findDailyRecord(user, date);
        if (dailyRecord != null) {
            List<RecordDetail> recordDetails = recordService.getRecordDetailsByDailyRecordId(dailyRecord);
            dailyTotal = recordService.getDailyTotal(dailyRecord, recordDetails, foodService);

        } else {
            dailyTotal.setDate(null);
            dailyTotal.setTotalCalorie(0.0);
            dailyTotal.setTotalProtein(0.0);
            dailyTotal.setTotalFat(0.0);
            dailyTotal.setTotalCarbo(0.0);
        }
        request.setAttribute("dailyTotal", dailyTotal);

        //推奨カロリーと推奨PFCを取得
        double recommendCalorie = user.calculateRecommendCalorie();
        double recommendProtein = user.recommendProtein();
        double recommendFat = user.recommendFat();
        double recommendCarbo = user.recommendCarbo();

        //記録追加用の食品一覧取得
        List<Food> foods = foodService.getAllFoodsByUser(user);

        if (foods.size() == 0) {
            foods = null;
        }

        //直近の記録取得
        List<DailyRecord> dailyRecords = recordService.getDailyRecordsByUser(user);
        if (dailyRecords != null) {
            List<RecordDetail> recentlyRecords = new ArrayList<RecordDetail>();
            //デイリーレコードのリストのすべてのidに紐づいたレコード詳細を取得
            for (DailyRecord dr : dailyRecords) {
                List<RecordDetail> getDetails = recordService.getRecordDetailsByDailyRecordId(dr);
                recentlyRecords.addAll(getDetails);
            }
            //直近の3件のみ表示させる
            request.setAttribute("recordDetails", recentlyRecords.subList(0, 3));
        }

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = (String) request.getSession().getAttribute("flush");
        if (flush != null) {
            request.setAttribute("flush", flush);
            request.getSession().removeAttribute("flush");
        }
        //セッションにエラーメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する

        List<String> errors = new ArrayList<String>();
        errors = (List<String>) request.getSession().getAttribute("errors");
        if (errors != null) {
            request.setAttribute("errors", errors);
            request.getSession().removeAttribute("errors");
        }

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
