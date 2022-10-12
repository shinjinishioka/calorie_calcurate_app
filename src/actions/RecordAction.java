package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import models.DailyRecord;
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

        List<String> errors = RecordValidator.createValidate(request.getParameter("mealTime"),
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
}
