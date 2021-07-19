<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>タスク詳細</h2>

        <p><c:out value="${task.toDo}"></c:out></p>
        <p>詳細：<c:out value="${task.detail}"></c:out></p>
        <p>締め切り：<fmt:formatDate value="${task.deadline}" pattern="yyyy-MM-dd HH:mm"/></p>
        <p>登録日時：<fmt:formatDate value="${task.create_at}" pattern="yyyy-MM-dd HH:mm"/></p>
        <p>編集日時：<fmt:formatDate value="${task.update_at}" pattern="yyyy-MM-dd HH:mm"/></p>

        <p><a href="${pageContext.request.contextPath}/edit?id=${task.id}">タスク内容の編集</a></p>
        <p><a href="${pageContext.request.contextPath}/index">タスク一覧に戻る</a></p>
    </c:param>
</c:import>
