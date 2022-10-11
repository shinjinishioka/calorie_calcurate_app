<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:import url="../layout/app.jsp">

    <c:param name="content">
        <c:if test="${errors != null}">
            <div id="flush_error">
                入力内容にエラーがあります。<br />
                <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
                    <br />
                </c:forEach>

            </div>
        </c:if>
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