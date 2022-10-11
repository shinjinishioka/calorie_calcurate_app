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
            <h2>プロフィール情報</h2>
            <a href="<c:url value='?action=User&command=edit' />">編集する</a> <a
                href="<c:url value='?action=User&command=changePass' />">パスワード変更</a>

            <a href="<c:url value='?action=User&command=delete' />"onclick=" return deleteCheck();">退会する</a>
            <script>
                function deleteCheck() {
                    var checked = confirm("退会するとログインできなくなりますがよろしいですか？")
                        if (checked == true) {
                            return true;
                        } else {
                            return false;
                        }
                }
            </script>

        </div>


        <p>
            名前
            <c:out value="${login_user.name}" />
        </p>
        <p>
            年齢
            <c:out value="${login_user.age}" />
            歳
        </p>
        <p>
            性別
            <c:choose>
                <c:when test="${login_user.sex == '2' }">
                    女
                </c:when>
                <c:otherwise>
                    男
                </c:otherwise>
            </c:choose>
        </p>
        <p>
            身長
            <c:out value="${login_user.height}" />
            cm
        </p>
        <p>
            体重
            <c:out value="${login_user.weight}" />
            kg
        </p>
        <p>
            活動レベル
            <c:choose>
                <c:when test="${login_user.activityLevel == '1' }">
                    低
                </c:when>
                <c:when test="${login_user.activityLevel == '2' }">
                    中
                </c:when>
                <c:when test="${login_user.activityLevel == '3' }">
                    高
                </c:when>
            </c:choose>
        </p>
        <p>
            体脂肪
            <c:out value="${login_user.bodyFat}" />
            %
        </p>
        <p>
            目標体重
            <c:out value="${login_user.targetWeight}" />
            kg
        </p>
        <p>
            目標期日
            <c:out value="${login_user.period}" />
        </p>
        <p>
            目標PFCバランス P
            <c:out value="${login_user.targetProtein}" />
            % F
            <c:out value="${login_user.targetFat}" />
            % C
            <c:out value="${login_user.targetCarbo}" />
            %
        </p>

    </c:param>
</c:import>