<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>タスク一覧</h2>
        <ul>
            <c:forEach begin="0" end="${ArraySize-1}" step="1" varStatus="i">
                <li>
                    <c:out value="${toDos[i.index]}"/> &gt; <fmt:formatDate value="${deadlines[i.index]}" pattern="yyyy-MM-dd HH:mm"/><br>
                    <c:out value="${timeLags[i.index]}"/><br>
                    <a href="${pageContext.request.contextPath}/show?id=${ids[i.index]}">タスク詳細へ</a>

                </li>
            </c:forEach>
        </ul>

        <p><a href="${pageContext.request.contextPath}/new">タスクを追加する</a></p>

    </c:param>
</c:import>
