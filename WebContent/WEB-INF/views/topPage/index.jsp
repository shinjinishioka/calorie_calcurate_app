<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h2>
            <c:out value="${login_user.name }" />
            さん、こんにちは
        </h2>
        <p>
            体重
            <c:out value="${login_user.weight }" />
            kg
        </p>
         <p>一日の総消費カロリー<c:out value="${metabolism}"/>kcal</p>
         <p>目標まで<c:out value="${targetWeight}"/>kg</p>
        <p>
            あと
            <c:out value="${deadLine}" />
            日
        </p>
    </c:param>
</c:import>