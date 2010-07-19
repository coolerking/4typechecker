<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.otakingex.type4.ViewConstants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="css/base.css">
<link type="text/css" rel="stylesheet" href="css/tables.css">
<link rel="shortcut icon" href="/favicon.ico">
<title>オタキングex 4タイプ簡易判定サイト</title>
</head>
<body>
<h1>4タイプ簡易判定サイト</h1>

<h2>ただいま、混み合っております</h2>

<p align="center">
しばらくお待ちいただいた後、再度実行してください。
</p>
<form action="/entry" method="POST">
	<!-- hidden -->
<%
	Map<String, String> hiddens = 
		(Map<String, String>)
			request.getAttribute(
					ViewConstants.REQ_ATTRKEY_HIDDENMAP);
	if(hiddens!=null && (!hiddens.isEmpty())){
		Iterator<String> iter = hiddens.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			if(key==null) continue;
			String value = hiddens.get(key);
			if(value==null) continue;
%>
	<input type="hidden" name="<%= key %>" value="<%=value %>" />
<%
		}
	}
%>
<center>
<input type="submit" value="判定サイトへ"></input>
</center>
</form>

</body>
</html>