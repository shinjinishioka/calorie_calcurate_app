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
            <fmt:formatNumber type="number" maxFractionDigits="2"
                value="${login_user.weight }" />
            kg
        </p>
        <p>
            基礎代謝
            <fmt:formatNumber type="number" maxFractionDigits="2"
                value="${metabolism}" />
            kcal
        </p>
        <p>
            目標摂取カロリー
            <fmt:formatNumber type="number" maxFractionDigits="2"
                value="${recommendCalorie}" />
            kcal
        </p>
        <p>
            目標まで
            <fmt:formatNumber type="number" maxFractionDigits="2"
                value="${targetWeight}" />
            kg
        </p>
        <p>
            あと
            <c:out value="${deadLine}" />
            日
        </p>

        <h3>今日の合計摂取 【目標差】</h3>
        <p>
            <fmt:formatNumber type="number" maxFractionDigits="2"
                value="${dailyTotal.totalCalorie }" />
            kcal 【
            <fmt:formatNumber type="number" maxFractionDigits="2"
                value="${dailyTotal.totalCalorie - recommendCalorie}" />
            kcal 】
        </p>
        <p>
            タンパク質
            <fmt:formatNumber type="number" maxFractionDigits="2"
                value="${dailyTotal.totalProtein }" />
            g 【
            <fmt:formatNumber type="number" maxFractionDigits="2"
                value="${dailyTotal.totalProtein - recommendProtein }" />
            g 】
        </p>
        <p>
            脂質
            <fmt:formatNumber type="number" maxFractionDigits="2"
                value="${dailyTotal.totalFat }" />
            g 【
            <fmt:formatNumber type="number" maxFractionDigits="2"
                value="${dailyTotal.totalFat - recommendFat}" />
            g 】
        </p>
        <p>
            糖質
            <fmt:formatNumber type="number" maxFractionDigits="2"
                value="${dailyTotal.totalCarbo }" />
            g 【
            <fmt:formatNumber type="number" maxFractionDigits="2"
                value="${dailyTotal.totalCarbo - recommendCarbo }" />
            g 】
        </p>
        <c:choose>
            <c:when test="${foods == null }">
                               食品が登録されておりません。
                            </c:when>
            <c:otherwise>
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
                    </select> <label>数量<br /> <input type="text" name="amount"
                        value="0" /></label>
                    <button type="submit">記録追加</button>
                </form>
            </c:otherwise>
        </c:choose>

        <a href="<c:url value='?action=Record&command=show' />">食事記録一覧を見る</a>
        <h3>直近の記録</h3>
        <c:forEach var="recordDetail" items="${recordDetails}">
            <p>
                <c:out value="${recordDetail.dailyRecord.date}" />
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
                <fmt:formatNumber type="number" maxFractionDigits="2"
                    value="${recordDetail.food.caloriePerUnit * recordDetail.amount }" />
                kcal タンパク質
                <fmt:formatNumber type="number" maxFractionDigits="2"
                    value="${recordDetail.food.protein  * recordDetail.amount}" />
                ｇ 脂質
                <fmt:formatNumber type="number" maxFractionDigits="2"
                    value="${recordDetail.food.fat  * recordDetail.amount }" />
                ｇ 糖質
                <fmt:formatNumber type="number" maxFractionDigits="2"
                    value="${recordDetail.food.carbo  * recordDetail.amount}" />
                ｇ
            </p>
        </c:forEach>
    </c:param>
</c:import>