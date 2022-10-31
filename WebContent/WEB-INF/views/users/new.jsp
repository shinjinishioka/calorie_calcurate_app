<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:import url="../layout/app.jsp">

    <c:param name="content">
        <h2>新規登録</h2>
        <!--　-->
        <form method="POST"
            action="<c:url value='?action=User&command=create' />">
            <label>ユーザー名<br /> <input type="text" name="name"
                value="${name}" /></label><br /> <label>年齢<br /> <input
                type="text" name="age" value="${age}"
                size="3" /></label>
            <br /> <label>性別<br /> <select name="sex" size="1">
                    <option value="1" label="男"
                        <c:if test="${sex == '1'}">
selected
</c:if>>男</option>
                    <option value="2" label="女"
                        <c:if test="${sex == '2'}">
selected
</c:if>>女</option>
            </select></label> <br /> <label>身長<br /> <input type="text" name="height"
                value="${height}" size="5" /></label> <br /> <label>体重<br /> <input
                type="text" name="weight" value="${weight}" size="5" /></label> <br /> <label>体脂肪<br />
                <input type="text" name="bodyFat" value="${bodyFat}" size="5" /></label><br /> <label>目標体重<br />
                <input type="text" name="targetWeight" value="${targetWeight}" size="5" /></label><br />
            <label>活動レベル<br /> <select name="activityLevel" size="1">
                    <option value="1" label="低 : 一日中座っていることが多い"></option>
                    <option value="2" label="中 : 家事など日常的に歩くことが多い" selected></option>
                    <option value="3" label="高 : 肉体労働や日常的にスポーツをしている"></option>
            </select></label> <br /> <label>目標期日<br /> <input type="date" name="period"></label>
            <br /> <label>目標栄養素摂取割合(割合の合計が100になるように入れてください)<br /> タンパク質: <input type="text"
                name="targetProtein" value="${targetProtein}" size="5" />&nbsp;脂質: <input
                type="text" name="targetFat" value="${targetFat}" size="5" />&nbsp;糖質: <input
                type="text" name="targetCarbo" value="${targetCarbo}" size="5" /></label> <br /> <label>パスワード<br />
                <input type="password" name="password" /></label> <br /> <label>パスワード確認<br />
                <input type="password" name="passwordCheck" /></label> <br /> <br />
            <button type="submit">登録</button>
        </form>
    </c:param>
</c:import>