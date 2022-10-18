package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String contextPath = ((HttpServletRequest) request).getContextPath();
        String servletPath = ((HttpServletRequest) request).getServletPath();

        if (servletPath.matches("/css.*")) {
            // CSSフォルダ内は認証処理から除外する
            chain.doFilter(request, response);

        } else {
            HttpSession session = ((HttpServletRequest) request).getSession();

            //クエリパラメータからactionとcommandを取得
            String action = request.getParameter("action");
            String command = request.getParameter("command");

            //セッションからログインしているユーザー情報を取得
            User user = (User) session.getAttribute("login_user");

            if (user == null) {
                //未ログイン

                if (action == null || command == null) {
                    //ログインページの表示またはログイン実行以外はログインページにリダイレクト
                    ((HttpServletResponse) response).sendRedirect(
                            contextPath
                                    + "?action=Auth&command=showLogin");
                    return;
                }

                if (!((action.equals("Auth")
                        && command.equals("showLogin")
                        || command.equals("login"))
                        || (action.equals("User")
                                && command.equals("entryNew")
                                || command.equals("create")))) {

                    //ログインページの表示またはログイン実行以外はログインページにリダイレクト
                    ((HttpServletResponse) response).sendRedirect(
                            contextPath
                                    + "?action=Auth&command=showLogin");
                    return;
                }
            } else {
                //ログイン済

                if (action.equals("Auth")) {
                    //認証系Actionを行おうとしている場合

                    if (command.equals("showLogin")) {
                        //ログインページの表示はトップ画面にリダイレクト
                        ((HttpServletResponse) response).sendRedirect(
                                contextPath
                                        + "?action=Top&command=index");
                        return;

                    } else if (command.equals("logout")) {
                        //ログアウトの実施は許可

                    } else {
                        //上記以外の認証系Actionはエラー画面

                        String forward = String.format("/WEB-INF/views/%s.jsp", "error/unknown");
                        RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
                        dispatcher.forward(request, response);

                        return;

                    }
                }
            }

            //次のフィルタまたはサーブレットを呼び出し
            chain.doFilter(request, response);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
