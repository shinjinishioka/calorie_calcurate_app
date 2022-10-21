<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">

        <div id="sub_title">
            <h2>日別記録一覧</h2>
        </div>
        <p>　　【】目標差</p>
        <table id="records_list">
            <tbody>
                <tr>
                    <th>日時</th>
                    <th>カロリー 合計</th>
                    <th>タンパク質 合計</th>
                    <th>脂質 合計</th>
                    <th>糖質 合計</th>

                </tr>
                <c:forEach var="dailyTotal" items="${dailyTotals}"
                    varStatus="status">
                    <fmt:parseDate value="${dailyTotal.date}" pattern="yyyy-MM-dd"
                        var="day" type="date" />
                    <tr>
                        <td><fmt:formatDate value='${day}' pattern='yyyy/MM/dd' /></td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalCalorie}" />kcal【<fmt:formatNumber
                                type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalCalorie - recommendCalorie}" />kcal】</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalProtein}" />g【<fmt:formatNumber
                                type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalProtein - recommendProtein }" />g】</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalFat}" />g【<fmt:formatNumber
                                type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalFat - recommendFat}" />g】</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalCarbo}" />【<fmt:formatNumber
                                type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalCarbo - recommendCarbo }" />g】</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
            （全 ${count} 件）<br />
            <c:forEach var="i" begin="1" end="${((count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a
                            href="<c:url value='?action=Record&command=showDailyRecord&page=${i}' />"><c:out
                                value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <br />
        <a href="<c:url value='?action=Record&command=show' />">戻る</a>
    </c:param>
</c:import>