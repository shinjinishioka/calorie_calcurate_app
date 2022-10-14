<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title><c:out value="カロリー計算アプリ" /></title>
<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div id="header_menu">
                <h1>カロリー計算アプリ</h1>

                <c:if test="${sessionScope.login_user != null}">
                    <a href="<c:url value='?action=User&command=show' />">プロフィール画面</a>&nbsp;
                         <a
                        href="<c:url value='?action=Food&command=show' />">食品一覧</a>&nbsp;
                          <a
                        href="<c:url value='?action=Auth&command=logout' />">ログアウト</a>&nbsp;
                    </c:if>
            </div>
        </div>

        <!-- メッセージ表示 -->
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}" />
            </div>
        </c:if>

        <c:if test="${errors != null}">
            <div id="flush_error">
                入力内容にエラーがあります。<br />
                <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
                    <br />
                </c:forEach>
            </div>
        </c:if>
        <div id="content">${param.content}</div>
        <c:if test="${sessionScope.login_user != null}">
            <a href="<c:url value='?action=Top&command=index' />">TOPに戻る</a>
        </c:if>
        <div id="footer">by Shinji Nishioka</div>
    </div>
</body>
</html>