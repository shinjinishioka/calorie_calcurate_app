<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:import url="../layout/app.jsp">

    <c:param name="content">
        <c:if test="${errors != null}">
            <div id="flush_error">
                入力内容にエラーがあります。<br />
                <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
                    <br />
                </c:forEach>

            </div>
        </c:if>
        <h2>食品登録</h2>
        <!--　-->
        <form method="POST"
            action="<c:url value='?action=Food&command=create' />">
            <label>食品名<br /> <input type="text" name="name" /></label><br /> <label>単位<br />
                <input type="text" name="unit" /></label> <br /> <br /> <label>カロリー<br />
                <input type="text" name="caloriePerUnit" value="0" /></label> <br /> <label>タンパク質<br />
                <input type="text" name="protein" value="0" /></label> <br /> <label>脂質<br />
                <input type="text" name="fat" value="0" /></label><br /> <label>糖質<br />
                <input type="text" name="carbo" value="0" /></label><br /> <br /> <br />
            <button type="submit">登録</button>
        </form>
    </c:param>
</c:import>