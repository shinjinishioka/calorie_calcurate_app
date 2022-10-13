<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">

        <div id="sub_title">
            <h2>食事記録一覧</h2>
            <a
                            href="<c:url value='?action=Record&command=showDailyRecord' />">日別記録一覧</a>
        </div>
        <table id="records_list">
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
                    <tr>
                        <td><c:out value="${recordDetail.dailyRecord.date}" /></td>
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
                        <td><c:out value="${recordDetail.food.caloriePerUnit * recordDetail.amount }" /></td>
                        <td><c:out value="${recordDetail.food.protein  * recordDetail.amount}" /></td>
                        <td><c:out value="${recordDetail.food.fat  * recordDetail.amount }" /></td>
                        <td><c:out value="${recordDetail.food.carbo  * recordDetail.amount}" /></td>
                        <td><a
                            href="<c:url value='?action=Record&command=edit&id=${recordDetail.id }' />">編集</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:param>
</c:import>