<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">

        <div id="sub_title">
            <h2>日別記録一覧</h2>
        </div>
        <p>()目標差</p>
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
                    <tr>
                        <td><c:out value="${dailyTotal.date}" /></td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalCalorie}" />(<fmt:formatNumber
                                type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalCalorie - recommendCalorie}" />)</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalProtein}" />(<fmt:formatNumber
                                type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalProtein - recommendProtein }" />)</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalFat}" />(<fmt:formatNumber
                                type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalFat - recommendFat}" />)</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalCarbo}" />(<fmt:formatNumber
                                type="number" maxFractionDigits="2"
                                value="${dailyTotal.totalCarbo - recommendCarbo }" />)</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:param>
</c:import>