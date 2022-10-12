<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:import url="../layout/app.jsp">

    <c:param name="content">

        <h2>パスワード変更画面</h2>
        <!--　-->
        <form method="POST"
            action="<c:url value='?action=User&command=updatePass' />">
            <label>現在のパスワード<br /> <input type="password" name="currentPassword" /></label>
            <br /><label>新しいパスワード<br /> <input type="password" name="newPassword" /></label>
            <br />  <label>パスワード確認<br /> <input type="password"
                name="passwordCheck" /></label> <br /> <br />
            <button type="submit">登録</button>
        </form>
    </c:param>
</c:import>