<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">

        <div id="sub_title">
            <h2>食事記録一覧</h2>
        </div>
        <form method="POST"
            action="<c:url value='?action=Record&command=update' />">
            <label>日付<br /> <input type="date" name="date"
                value="${recordDetail.dailyRecord.date}" /></label>
            <p>食事時間</p>
            <select name="mealTime">
                <option value="1"
                    <c:if test="${recordDetail.mealTime == '1'}">
selected
</c:if>>朝食</option>
                <option value="2"
                    <c:if test="${recordDetail.mealTime == '2'}">
selected
</c:if>>昼食</option>
                <option value="3"
                    <c:if test="${recordDetail.mealTime == '3'}">
selected
</c:if>>夕食</option>
                <option value="4"
                    <c:if test="${recordDetail.mealTime == '4'}">
selected
</c:if>>間食</option>
            </select>
            <p>食品</p>
            <select name="food">
                <c:forEach var="food" items="${foods}">
                    <option value="${food.id}"
                        <c:if test="${recordDetail.food.id == food.id}">
selected
</c:if>><c:out
                            value="${food.name}" /> （
                        <c:out value="${food.unit}" />）
                    </option>
                </c:forEach>
            </select> <label>数量<br /> <input type="text" name="amount"
                value="${recordDetail.amount}" /></label>
            <p>
                カロリー
                <c:out
                    value="${recordDetail.food.caloriePerUnit * recordDetail.amount }" />
            </p>
            <p>
                タンパク質
                <c:out value="${recordDetail.food.protein  * recordDetail.amount}" />
            </p>
            <p>
                脂質
                <c:out value="${recordDetail.food.fat  * recordDetail.amount }" />
            </p>
            <p>
                糖質
                <c:out value="${recordDetail.food.carbo  * recordDetail.amount}" />
            </p>
            <input type="hidden" name="id" value="${recordDetail.id }">
            <button type="submit">変更</button>
        </form>
        <a
            href="<c:url value='?action=Record&command=delete&id=${recordDetail.id }' />">削除する</a>
    </c:param>
</c:import>