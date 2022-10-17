<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h2>プロフィール編集画面</h2>
        <!--　-->
        <form method="POST"
            action="<c:url value='?action=User&command=update' />">
            <label>氏名<br /> <input type="text" name="name"
                value="${login_user.name}" /></label><br /> <label>年齢<br /> <input
                type="text" name="age" value="${login_user.age}" /></label> <br /> <label>性別<br />
                <select name="sex" size="1">
                    <option value="1" label="男"
                        <c:if test="${login_user.sex == '1'}">
selected
</c:if>>男</option>
                    <option value="2" label="女"
                        <c:if test="${login_user.sex == '2'}">
selected
</c:if>>女</option>
            </select></label> <br /> <label>身長<br /> <input type="text" name="height"
                value="${login_user.height}" /></label> <br /> <label>体重<br /> <input
                type="text" name="weight" value="${login_user.weight}" /></label> <br /> <label>体脂肪<br />
                <input type="text" name="bodyFat" value="${login_user.bodyFat}" /></label><br />
            <label>目標体重<br /> <input type="text" name="targetWeight"
                value="${login_user.targetWeight}" /></label><br /> <label>活動レベル<br />
                <select name="activityLevel" size="1">
                    <option value="1" label="低 : 一日中座っていることが多い"
                        <c:if test="${login_user.activityLevel == '1'}">
selected
</c:if>></option>
                    <option value="2" label="中 : 家事など日常的に歩くことが多い"
                        <c:if test="${login_user.activityLevel == '2'}">
selected
</c:if>></option>
                    <option value="3" label="高 : 肉体労働や日常的にスポーツをしている"
                        <c:if test="${login_user.activityLevel == '3'}">
selected
</c:if>></option>
            </select></label> <br /> <label>目標期日<br /> <input type="date" name="period"
                value="${login_user.period}"></label> <br /> <label>目標PFC割合<br />
                P: <input type="text" name="targetProtein"
                value="${login_user.targetProtein}" />F: <input type="text"
                name="targetFat" value="${login_user.targetFat}" />C: <input type="text"
                name="targetCarbo" value="${login_user.targetCarbo}" /></label> <br /> <input
                type="hidden" name="id" value="${login_user.id}" /> <input type="hidden"
                name="password" value="${login_user.password}" />
            <button type="submit">登録</button>
        </form>
    </c:param>
</c:import>