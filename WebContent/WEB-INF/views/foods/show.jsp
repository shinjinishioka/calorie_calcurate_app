<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">

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
                    <tr>
                        <td><c:out value="${food.name}" /></td>
                        <td><c:out value="${food.unit}" /></td>
                        <td><c:out value="${food.caloriePerUnit}" /></td>
                        <td><c:out value="${food.protein}" /></td>
                        <td><c:out value="${food.fat}" /></td>
                        <td><c:out value="${food.carbo}" /></td>
                        <td><a
                            href="<c:url value='?action=Food&command=edit&id=${food.id }' />">編集</a>
                        </td>
                    </tr>
              </c:forEach>
            </tbody>
        </table>
    </c:param>
</c:import>