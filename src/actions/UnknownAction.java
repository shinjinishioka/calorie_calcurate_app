package actions;

import java.io.IOException;

import javax.servlet.ServletException;

public class UnknownAction extends ActionBase {
    @Override
    public void process() throws ServletException, IOException {

        //エラー画面を表示
        forward("error/unknown");

    }

}
