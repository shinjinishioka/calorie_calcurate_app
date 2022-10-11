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
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

            </div>
        </c:if>
        <h2>ログイン</h2>
        <!--　-->
        <form method="POST"
            action="<c:url value='?action=Auth&command=login' />">
            <label>ユーザー名<br /> <input type="text" name="name" /></label>
            <br /><label>パスワード<br /> <input type="password" name="password" /></label>
            <br />  <br />
            <button type="submit">ログイン</button>
        </form>
        <a href="<c:url value='?action=User&command=entryNew' />">新規登録</a>
    </c:param>
</c:import>