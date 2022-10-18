<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:import url="../layout/app.jsp">
    <c:param name="content">

        <div id="sub_title">
            <h2 id="sub">食事記録一覧</h2>
            <div class="sub_menu">
            <a href="<c:url value='?action=Record&command=showDailyRecord' />">日別記録一覧</a>
            </div>
        </div>
        <table>
            <tbody>
                <tr>
                    <th>日時</th>
                    <th>食事時間</th>
                    <th>食品</th>
                    <th>数量</th>
                    <th>単位</th>
                    <th>カロリー</th>
                    <th>タンパク質</th>
                    <th>脂質</th>
                    <th>糖質</th>
                    <th>編集</th>
                </tr>
                <c:forEach var="recordDetail" items="${recordDetails}"
                    varStatus="status">
                    <fmt:parseDate value="${recordDetail.dailyRecord.date}"
                        pattern="yyyy-MM-dd" var="day" type="date" />
                    <tr>
                        <td><fmt:formatDate value='${day}' pattern='MM/dd' /></td>
                        <c:choose>
                            <c:when test="${recordDetail.mealTime == '1' }">
                                <td>朝食</td>
                            </c:when>
                            <c:when test="${recordDetail.mealTime == '2' }">
                                <td>昼食</td>
                            </c:when>
                            <c:when test="${recordDetail.mealTime == '3' }">
                                <td>夕食</td>
                            </c:when>
                            <c:when test="${recordDetail.mealTime == '4' }">
                                <td>間食</td>
                            </c:when>
                        </c:choose>
                        <td><c:out value="${recordDetail.food.name}" /></td>
                        <td><c:out value="${recordDetail.amount}" /></td>
                        <td><c:out value="${recordDetail.food.unit}" /></td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2"
                                value="${recordDetail.food.caloriePerUnit * recordDetail.amount }" />kcal</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2"
                                value="${recordDetail.food.protein  * recordDetail.amount}" />g</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2"
                                value="${recordDetail.food.fat  * recordDetail.amount }" />g</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2"
                                value="${recordDetail.food.carbo  * recordDetail.amount}" />g</td>
                        <td><a
                            href="<c:url value='?action=Record&command=edit&id=${recordDetail.id }' />">編集</a>
                        </td>
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
                        <a href="<c:url value='?action=Record&command=show&page=${i}' />"><c:out
                                value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
                <br/>
 <a href="<c:url value='?action=Top&command=index' />">TOPに戻る</a>
    </c:param>
</c:import>