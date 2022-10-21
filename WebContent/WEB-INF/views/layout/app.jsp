<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>カロリー計算アプリ</title>
<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div id="sub_title">
                <h1>
                    <a href="<c:url value='?action=Top&command=index' />">
                        カロリー計算アプリ</a>
                </h1>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <c:if test="${sessionScope.login_user != null}">
                    <div class="header_menu">
                        <a href="<c:url value='?action=User&command=show' />">▶ プロフィール</a>
                    </div>
                    <div class="header_menu">
                        <a href="<c:url value='?action=Food&command=show' />">▶ 食品登録</a>
                    </div>
                    <div class="header_menu">
                        <a href="<c:url value='?action=Record&command=show' />">▶ 記録一覧</a>
                    </div>
                    <div class="header_menu">
                        <a href="<c:url value='?action=Auth&command=logout' />">▶ ログアウト</a>
                    </div>
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
        <div id="footer">by Shinji Nishioka</div>
    </div>
</body>
</html>