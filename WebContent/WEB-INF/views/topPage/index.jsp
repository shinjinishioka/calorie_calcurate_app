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

        <h3>今日の合計摂取 目標差</h3>
        <p>
            <c:out value="${dailyTotal.totalCalorie }" />
            kcal 【
            <c:out value="${dailyTotal.totalCalorie - recommendCalorie}" />
            】
        </p>
        <p>
            タンパク質
            <c:out value="${dailyTotal.totalProtein }" />
            【
            <c:out value="${dailyTotal.totalProtein - recommendProtein }" />
            】
        </p>
        <p>
            脂質
            <c:out value="${dailyTotal.totalFat }" />
            【
            <c:out value="${dailyTotal.totalFat - recommendFat}" />
            】
        </p>
        <p>
            糖質
            <c:out value="${dailyTotal.totalCarbo }" />
            【
            <c:out value="${dailyTotal.totalCarbo - recommendCarbo }" />
            】
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
                    <option value="${food.id}"><c:out value="${food.name}" />
                        （
                        <c:out value="${food.unit}" />）
                    </option>
                </c:forEach>
            </select> <label>数量<br /> <input type="text" name="amount" value="0" /></label>
            <button type="submit">記録追加</button>
        </form>
        <a href="<c:url value='?action=Record&command=show' />">食事記録一覧を見る</a>
        <h3>直近の記録</h3>
        <c:forEach var="recordDetail" items="${recordDetails}">
            <p>
                <c:choose>
                    <c:when test="${recordDetail.mealTime == '1' }">
                                朝食
                            </c:when>
                    <c:when test="${recordDetail.mealTime == '2' }">
                                昼食
                            </c:when>
                    <c:when test="${recordDetail.mealTime == '3' }">
                                夕食
                            </c:when>
                    <c:when test="${recordDetail.mealTime == '4' }">
                               間食
                            </c:when>
                </c:choose>
                <c:out value="${recordDetail.food.name}" />
                <c:out value="${recordDetail.amount}" />
                <c:out value="${recordDetail.food.unit}" />
                <c:out
                    value="${recordDetail.food.caloriePerUnit * recordDetail.amount }" />
                kcal タンパク質
                <c:out value="${recordDetail.food.protein  * recordDetail.amount}" />
                ｇ 脂質
                <c:out value="${recordDetail.food.fat  * recordDetail.amount }" />
                ｇ 糖質
                <c:out value="${recordDetail.food.carbo  * recordDetail.amount}" />
                ｇ
            </p>
        </c:forEach>
    </c:param>
</c:import>