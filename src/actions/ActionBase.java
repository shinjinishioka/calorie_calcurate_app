package actions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ActionBase {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public void init(ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
    }

    //フロントコントローラーから呼び出されるメソッド
    public abstract void process() throws ServletException, IOException;

    protected void invoke() throws ServletException, IOException {
        Method commandMethod;
        try {
            //パラメータからコマンドを取得
            String command = request.getParameter("command");
            commandMethod = this.getClass().getDeclaredMethod(command, new Class[0]);
            commandMethod.invoke(this, new Object[0]);

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NullPointerException e) {
            //発生した例外をコンソールに表示
            e.printStackTrace();
            //commandの値が不正で実行できない場合エラー画面を呼び出し
            forward("error/unknown");
        }
    }

    protected void forward(String target) throws ServletException, IOException {
        //jspのパスを設定
        String forward = String.format("/WEB-INF/views/%s.jsp", target);
        RequestDispatcher dispatcher = request.getRequestDispatcher(forward);

        //jspファイルの呼び出し
        dispatcher.forward(request, response);

    }

    protected void redirect(String action, String command) throws ServletException, IOException {
        //URL作成
        String redirectUrl = request.getContextPath() + "/?action=" + action;
        if (command != null) {
            redirectUrl = redirectUrl + "&command=" + command;
        }

        //URLへリダイレクト
        response.sendRedirect(redirectUrl);
    }

}
