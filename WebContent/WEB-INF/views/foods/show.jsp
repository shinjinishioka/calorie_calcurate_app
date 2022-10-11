<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <div id="sub_title">
            <h2>登録済み食品一覧</h2><a
                            href="<c:url value='?action=Food&command=entryNew' />">食品の登録</a>

        </div>
        <table id="foods_list">
            <tbody>
                <tr>
                    <th>食品名</th>
                    <th>単位</th>
                    <th>カロリー</th>
                    <th>タンパク質</th>
                    <th>脂質</th>
                    <th>糖質</th>
                    <th>編集</th>
                </tr>
                <c:forEach var="food" items="${foods}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${food.name}" /></td>
                        <td><c:out value="${food.unit}" /></td>
                        <td><c:out value="${food.caloriePerUnit}" /></td>
                        <td><c:out value="${food.protein}" /></td>
                        <td><c:out value="${food.fat}" /></td>
                        <td><c:out value="${food.carbo}" /></td>
                        <td><a
                            href="<c:url value='?action=Food&command=edit' />">編集</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!--
        <div id="pagination">
            （全 ${employees_count} 件）<br />
            <c:forEach var="i" begin="1"
                end="${((employees_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a
                            href="<c:url value='?action=${actEmp}&command=${commIdx}&page=${i}' />"><c:out
                                value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p>
            <a href="<c:url value='?action=${actEmp}&command=${commNew}' />">新規従業員の登録</a>
        </p>
-->
    </c:param>
</c:import>