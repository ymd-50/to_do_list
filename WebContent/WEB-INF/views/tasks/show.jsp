<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>タスク詳細</h2>

        <p><c:out value="${task.toDo}"></c:out></p>
        <p>詳細：<c:out value="${task.detail}"></c:out></p>
        <p><fmt:formatDate value="${task.deadline}" pattern="yyyy-MM-dd HH:mm"/></p>

        <p><a href="${pageContext.request.contextPath}/index">タスク一覧に戻る</a></p>
    </c:param>
</c:import>