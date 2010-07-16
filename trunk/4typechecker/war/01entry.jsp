<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.otakingex.type4.ViewConstants" %>
<%@ page import="com.otakingex.type4.model.User" %>
<%
	User user = (User)request.getAttribute(ViewConstants.REQ_ATTRKEY_USER);
	if(user==null) user = new User();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="/css/base.css">
	<link type="text/css" rel="stylesheet" href="/css/tables.css">
	<link rel="shortcut icon" href="/images/favicon.ico">
	<title>オタキングex 4タイプ簡易判定サイト</title>
</head>
<body>
	<h1>4タイプ簡易判定サイト</h1>
	<h2><%= request.getAttribute(ViewConstants.REQ_ATTRKEY_SUBTITLE) %></h2>
	<p align="center">
	以下のフォームに記入し、開始ボタンを押してください。
	</p>

	<form method="POST" action="/test1">
		<table align="center">
		<tbody>

		<tr>
		<td style="font-weight:bold;">
		<div>名前：<input type="text" name="<%= ViewConstants.REQ_KEY_NAME %>" value="<%= user.getName() %>"></div>
		</td>
		</tr>

		<tr>
		<td style="font-weight:bold;">
		<div>年齢：<input type="text" name="<%= ViewConstants.REQ_KEY_AGE %>" value="<%= user.getAge()==0?"":new Integer(user.getAge()).toString() %>"></input></div>
		</td>
		</tr>

		<tr>
		<td style="font-weight:bold;">
		<div>性別：<input type="radio" name="<%= ViewConstants.REQ_KEY_SEX %>" value="<%= ViewConstants.REQ_VALUE_SEX_MALE %>"<%= (user.isMale())?" checked":"" %>>男 <input type=radio name="<%= ViewConstants.REQ_KEY_SEX %>" value="<%= ViewConstants.REQ_VALUE_SEX_FEMALE %>"<%= (user.isMale())?"":" checked" %>>女</div>
		</td>
		</tr>

		<tr>
		<th style="font-weight:bold;" class="nobg">
		<center><input type="submit" value="開始"><input type="reset" value="クリア"></center>
		</th>
		</tr>

		</tbody>
		</table>
	</form>

	<p align="center">
	<font size="-1">
	※未記入でも判定できます（未入力のまま、開始ボタンを押してください）。
	</font>
	</p>

</body>
</html>