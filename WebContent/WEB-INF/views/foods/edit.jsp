<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:import url="../layout/app.jsp">

    <c:param name="content">

        <h2>食品の編集</h2>
        <!--　-->
        <form method="POST"
            action="<c:url value='?action=Food&command=update' />">
            <label>食品名<br /> <input type="text" name="name"
                value="${food.name }" /></label><br /> <label>単位<br /> <input
                type="text" name="unit" value="${food.unit }" /></label> <br /> <br /> <label>カロリー<br />
                <input type="text" name="caloriePerUnit"
                value="${food.caloriePerUnit }" /></label> <br /> <label>タンパク質<br />
                <input type="text" name="protein" value="${food.protein }" /></label> <br />
            <label>脂質<br /> <input type="text" name="fat"
                value="${food.fat }" /></label><br /> <label>糖質<br /> <input
                type="text" name="carbo" value="${food.carbo }" /></label><br /> <br /> <br />
            <input type="hidden" name="id" value="${food.id }">
            <button type="submit">変更</button>&nbsp;&nbsp;&nbsp;&nbsp;
            <a
                href="<c:url value='?action=Food&command=delete&id=${food.id }' />">削除する</a>
        </form>
        <br />
        <a href="<c:url value='?action=Food&command=show' />">戻る</a>
    </c:param>
</c:import>