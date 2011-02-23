<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.otakingex.type4.control.Utils" %>
<%@ page import="com.otakingex.type4.ViewConstants" %>
<%@ page import="com.otakingex.type4.model.Question" %>
<%
	Boolean isLastTest = (Boolean)request.getAttribute(ViewConstants.REQ_ATTRKEY_SENDSUMMARY);
	String action = (isLastTest.booleanValue())?"/summary":"/workout";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="/css/base.css">
	<link type="text/css" rel="stylesheet" href="/css/tables.css">
	<link rel="shortcut icon" href="/favicon.ico">
	<title>オタキングex 4タイプ判定テスト</title>
</head>
<body>
	<h1>4タイプ判定テスト</h1>
	<h2><%= request.getAttribute(ViewConstants.REQ_ATTRKEY_SUBTITLE) %></h2>
	<p align="center">
	以下の質問に回答し、次へボタンを押してください。
	</p>

	<form method="POST" action="<%=action %>">
	
		<!-- 向性テスト -->
<%
	List<Question> tropism = 
		(List<Question>)request.getAttribute(
				ViewConstants.REQ_ATTRKEY_TROPISM_LIST);
	if(tropism!=null && tropism.size()>0){
%>
	<center>
	<table style="border-style:solid; border-width:1px; border-color:#919191; width:500px; ">
	<tbody>
<%
		Iterator<Question> it = tropism.iterator();
		while(it.hasNext()){
			Question q = it.next();
			if(q==null) continue;
%>
		<tr>
		<th style="font-weight:bold;" bgcolor="#ccffcc">
		<%= q.getLabel() %>：<%= q.getStatement() %>
		</th>
		</tr>

			<!-- answer -->
<%
			int value = 2;
			Iterator<String> ite = q.getAnswers().iterator();
			while(ite.hasNext()){
				String answer = ite.next();
%>
			<tr>
			<td>
			<input type="radio" name="<%=Utils.getRequestKey(ViewConstants.REQ_KEY_QUESTION_TEST1, q.getId(), q.getOrder(), q.isForward()) %>" value="<%= value %>" <%=(value==0)?"checked":"" %>><%= answer %>
			</td>
			</tr>
<%
				value--;
			}
		}
%>
	</tbody>
	</table>
	</center>
	<br></br>
	<br></br>
<%
	}
%>

		<!-- 注目型・司令型テスト -->
<%
	List<Question> kingSold = (List<Question>)request.getAttribute(
				ViewConstants.REQ_ATTRKEY_KINGSOLD_LIST);
	if(kingSold!=null && kingSold.size()>0){
%>
	<center>
	<table style="border-style:solid; border-width:1px; border-color:#919191; width:500px; ">
	<tbody>
<%
		Iterator<Question> it = kingSold.iterator();
		while(it.hasNext()){
			Question q = it.next();
			if(q==null) continue;
%>
		<tr>
		<th style="font-weight:bold;" bgcolor="#ccffcc">
		<%= q.getLabel() %>：<%= q.getStatement() %>
		</th>
		</tr>

			<!-- answer -->
<%
			int value = 2;
			Iterator<String> ite = q.getAnswers().iterator();
			while(ite.hasNext()){
				String answer = ite.next();
%>
			<tr>
			<td>
			<input type="radio" name="<%= Utils.getRequestKey(ViewConstants.REQ_KEY_QUESTION_TEST2, q.getId(), q.getOrder(), q.isForward()) %>" value="<%= value %>" <%=(value==0)?"checked":"" %>><%= answer %>
			</td>
			</tr>
<%
				value--;
			}
		}
%>
	</tbody>
	</table>
	</center>
	<br></br>
	<br></br>
<%
	}
%>

		<!-- 法則型・理想型テスト -->
<%
	List<Question> schlCrft = (List<Question>)request.getAttribute(ViewConstants.REQ_ATTRKEY_SCHLCRFT_LIST);
	if(schlCrft!=null && schlCrft.size()>0){
%>
	<center>
	<table style="border-style:solid; border-width:1px; border-color:#919191; width:500px; ">
	<tbody>
<%
		Iterator<Question> it = schlCrft.iterator();
		while(it.hasNext()){
			Question q = it.next();
			if(q==null) continue;
%>
		<tr>
		<th style="font-weight:bold;" bgcolor="#ccffcc">
		<%= q.getLabel() %>：<%= q.getStatement() %>
		</th>
		</tr>

			<!-- answer -->
<%
			int value = 2;
			Iterator<String> ite = q.getAnswers().iterator();
			while(ite.hasNext()){
				String answer = ite.next();
%>
			<tr>
			<td>
			<input type="radio" name="<%= Utils.getRequestKey(ViewConstants.REQ_KEY_QUESTION_TEST3, q.getId(), q.getOrder(), q.isForward()) %>" value="<%= value %>" <%=(value==0)?"checked":"" %>><%= answer %>
			</td>
			</tr>
<%
				value--;
			}
		}
%>
	</tbody>
	</table>
	</center>
	<br></br>
	<br></br>
<%
	}
%>
	
	<center>
	<input type="submit" value="次へ"/><input type="reset" value="クリア"/>
	</center>

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
	</form>
	
	<br>
	<p align="center">
	各タイプの説明は、こちらをご参照ください。
	</p>
	
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