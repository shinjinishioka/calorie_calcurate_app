<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:import url="../layout/app.jsp">

    <c:param name="content">

        <h2>食品登録</h2><br/>
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
        <br />
         <a href="<c:url value='?action=Food&command=show' />">戻る</a>
    </c:param>
</c:import>