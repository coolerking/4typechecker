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
	<link rel="shortcut icon" href="/favicon.ico">
	<title>FREEex 4タイプ判定テスト</title>
</head>
<body>
	<h1>4タイプ判定テスト</h1>
	<h2> </h2>
	<p align="center">
	開始ボタンを押してください。
	</p>

	<form method="POST" action="/workout">
<!--
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

		</tbody>
		</table>
<br>
-->
		<input type="hidden" name="<%= ViewConstants.REQ_KEY_NAME %>" value="<%= user.getName() %>"></input>
		<input type="hidden" name="<%= ViewConstants.REQ_KEY_AGE %>"  value="<%= user.getAge()==0?"":new Integer(user.getAge()).toString() %>"></input>
		<input type="hidden" name="<%= ViewConstants.REQ_KEY_SEX %>" value="<%= ViewConstants.REQ_VALUE_SEX_MALE %>"></input>
<center><input type="submit" value="開始"><!-- input type="reset" value="クリア" --></center>

	</form>
<!--
	<p align="center">
	<font size="-1">
	※未記入でも判定できます（未入力のまま、開始ボタンを押してください）。
	</font>
	</p>
-->
<p align="center">
各タイプの説明は、こちらをご参照ください。
</p>
<!--
<p align="center">
<a href="http://www.youtube.com/v/zFEYE9HYJUY&hl=ja_JP&feature=player_embedded&version=3">[Youtube]４タイプについての解説(王様=注目型、軍人=司令型、学者=法則型、職人=理想型)</a>
</p>
-->
<p align="center">
<!--
<a href="http://www.amazon.co.jp/exec/obidos/ASIN/4023308838/otakingex01-22/">[Amazon]人生の法則 「欲求の4タイプ」で分かるあなたと他人</a>
<br>
-->
[<a href="<%= ViewConstants.URL_KING %>">注目型</a>][<a href="<%= ViewConstants.URL_SOLD %>">司令型</a>][<a href="<%= ViewConstants.URL_SCHL %>">法則型</a>][<a href="<%= ViewConstants.URL_CRFT %>">理想型</a>]
<br>
<!--
<br>
<a href="http://www.youtube.com/watch?v=zFEYE9HYJUY">[Youtube]岡田斗司夫のひとり夜話(2010/11/6)#7</a>
<br>
<a href="http://on.fb.me/ebpNKn">[Facebook]人間関係の特効薬　人生のトリセツ</a>
-->
</p>
</body>
</html>