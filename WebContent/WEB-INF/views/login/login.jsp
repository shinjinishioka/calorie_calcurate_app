<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:import url="../layout/app.jsp">

    <c:param name="content">
        <h2>ログイン</h2>
        <!--　-->
        <form method="POST"
            action="<c:url value='?action=Auth&command=login' />">
            <label>ユーザー名<br /> <input type="text" name="name" /></label>
            <br /><label>パスワード<br /> <input type="password" name="password" /></label>
            <br />  <br />
            <button type="submit">ログイン</button>
        </form>
        <br/>
        <a href="<c:url value='?action=User&command=entryNew' />">新規登録</a>
    </c:param>
</c:import>