<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<label for="toDo">タスク</label><br>
<input type="text" name="toDo" value="${task.toDo}"/>
<br><br>

<label for="detail">タスク詳細</label><br>
<input type="text" name="detail" value="${task.detail}"/>
<br><br>

<label for="deadline">締め切り</label><br>
<input type="datetime-local" name="deadline" value="${task.deadline}"/>
<br><br>

<input type="hidden" name="_token" value="${_token}"/>

<button type="submit">投稿</button>