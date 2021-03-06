<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="jp.freeex.fourtypes.ViewConstants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="css/base.css">
<link type="text/css" rel="stylesheet" href="css/tables.css">
<link rel="shortcut icon" href="/favicon.ico">
<title>FREEex 4タイプ判定テスト</title>
</head>
<body>
<h1>4タイプ判定テスト</h1>

<h2>ただいま、混み合っております</h2>

<p align="center">
しばらくお待ちいただいた後、再度実行してください。<br>
<br>
本サイトは無課金のGoogle App Engine環境にて動作しております。<br>
テスト回答後結果を表示する画面の直後に本画面に到達した場合は、<br>
無課金版制約上限に達した可能性があります。<br>
制約は、世界標準時間の午前0時（日本では午後3時）にリセットされます。<br>
ご注意下さい。<br>
<br>
お待たせして申し訳ありません。<br>
もしよろしければ、以下のサイトも見ていただけるとうれしいです。<br>
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