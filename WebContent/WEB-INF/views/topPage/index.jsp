<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h2>
            <c:out value="${login_user.name }" />
            さん、こんにちは
        </h2>
        <p>
            体重
            <c:out value="${login_user.weight }" />
            kg
        </p>
        <p>
            一日の総消費カロリー
            <c:out value="${metabolism}" />
            kcal
        </p>
        <p>
            目標まで
            <c:out value="${targetWeight}" />
            kg
        </p>
        <p>
            あと
            <c:out value="${deadLine}" />
            日
        </p>

        <form method="POST"
            action="<c:url value='?action=Record&command=create' />">
            <p>食事時間</p>
            <select name="mealTime">
                <option value="1">朝食</option>
                <option value="2">昼食</option>
                <option value="3">夕食</option>
                <option value="4">間食</option>
            </select>
            <p>食事</p>
            <select name="food">
                <c:forEach var="food" items="${foods}">
                    <option value="${food.id}"><c:out value="${food.name}" />　（<c:out value="${food.unit}" />）</option>
                </c:forEach>
            </select> <label>数量<br /> <input type="text" name="amount" value="0" /></label>
            <button type="submit">記録追加</button>
        </form>
        <a href="<c:url value='?action=Record&command=show' />">食事記録一覧を見る</a>
    </c:param>
</c:import>