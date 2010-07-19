<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.otakingex.type4.control.WorkoutContext" %>
<%@ page import="com.otakingex.type4.control.Utils" %>
<%@ page import="com.otakingex.type4.ViewConstants" %>
<%@ page import="com.otakingex.type4.model.Answer" %>
<%
	String kingOrSolder = (String)request.getAttribute(ViewConstants.REQ_KEY_SCORE_KINGSOLD);
	String scholarOrCraftsman = (String)request.getAttribute(ViewConstants.REQ_KEY_SCORE_SCHLCRFT);
	String king = (String)request.getAttribute(ViewConstants.REQ_KEY_SCORE_KING);
	String solder = (String)request.getAttribute(ViewConstants.REQ_KEY_SCORE_SOLDER);
	String scholar = (String)request.getAttribute(ViewConstants.REQ_KEY_SCORE_SCHOLAR);
	String craftsman = (String)request.getAttribute(ViewConstants.REQ_KEY_SCORE_CRAFTSMAN);
	String result = (String)request.getAttribute(ViewConstants.REQ_ATTRKEY_RESULT);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="css/base.css">
<link type="text/css" rel="stylesheet" href="css/tables.css">
<link rel="shortcut icon" href="/favicon.ico">
<title>オタキングex 4タイプ簡易判定サイト</title>
<script type="text/javascript" language=JavaScript>
<!--
	function Submit(formName){
  		document.forms[formName].submit();
	}
// -->
</script>
</head>
<body>
<form name="option" method="POST" action="/option">

<h1>4タイプ簡易判定サイト</h1>

<h1>結果</h1>

<p align="center">
あなたは、<font size="+2"><%= result %></font>です。
</p>

<p align="center">
<table align="center" cellspacing="0" summary="4Type Result">
<tbody>
  <tr>
    <th scope="col" abbr="Configurations" class="nobg">集計結果</th>
    <th scope="col" abbr="Dual 1.2">王様・軍人</th>
    <th scope="col" abbr="Dual 1.2">学者・職人</th>
	<th scope="col" abbr="Dual 0.8">王様</th>
	<th scope="col" abbr="Dual 0.8">軍人</th>
	<th scope="col" abbr="Dual 0.8">学者</th>
	<th scope="col" abbr="Dual 0.8">職人</th>
  </tr>
  <tr>
    <th scope="row" abbr="Model" class="spec">向性テスト</th>
    <td align="right"><%= kingOrSolder %></td>
    <td align="right"><%= scholarOrCraftsman %></td>
    <td bgcolor="#919191"></td>
    <td bgcolor="#919191"></td>
    <td bgcolor="#919191"></td>
    <td bgcolor="#919191"></td>
  </tr>
  <tr>
<%
	if(" ".equals(king) || " ".equals(solder)){
%>
    <th scope="row" abbr="Model" class="spec"><a href="javascript:Submit('option')">"王様・軍人テスト</a></th>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
<%
	}else{
%>
    <th scope="row" abbr="Model" class="spec">王様・軍人テスト</th>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td align="right"><%= king %></td>
    <td align="right"><%= solder %></td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
<%
	}
%>
  </tr>
   <tr>
<%
	if(" ".equals(scholar) || " ".equals(craftsman)){
%>
    <th scope="row" abbr="Model" class="spec"><a href="javascript:Submit('option')">学者・職人テスト</a></th>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
<%
	}else{
%>
    <th scope="row" abbr="Model" class="spec">学者・職人テスト</th>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td align="right"><%= scholar %></td>
    <td align="right"><%= craftsman %></td>
<%
	}
%>
  </tr>
</tbody>
</table>
</p>

<br>

<p align="center">


</p>
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

<!-- 向性テスト結果 -->
<%
	List<Answer> tList = (List<Answer>)request.getAttribute(ViewConstants.REQ_KEY_ANSWERS_TROPISM);
	if(!tList.isEmpty()){
%>
	<br></br>
	<br></br>
	<p align="center">
	<table align="center" cellspacing="0" summary="4Type Result">
	<tbody>
		<tr>
		<th scope="col" abbr="Configurations" class="nobg"><a name="#tropism">向性テスト</a></th>
		<th scope="col" abbr="Dual 1.2">王様・軍人</th>
    	<th scope="col" abbr="Dual 1.2">学者・職人</th>
  		</tr>
<%
		Iterator<Answer> it = tList.iterator();
		while(it.hasNext()){
			Answer answer = it.next();
%>
		<tr>
		<th scope="row" abbr="Model" class="spec"><%=answer.getLabel() %>：<%=answer.getStatement() %></th>
		<td align="right"><%=answer.getFwdScore() %></td>
		<td align="right"><%=answer.getRewScore() %></td>
		</tr>
<%
		}
%>

	</tbody>
	</table>
	</p>
<%
	}
%>



<!-- 王様・軍人テスト結果 -->
<%
	List<Answer> ksList = (List<Answer>)request.getAttribute(ViewConstants.REQ_KEY_ANSWERS_KINGSOLD);
	if(!ksList.isEmpty()){
%>
	<br></br>
	<br></br>
	<p align="center">
	<table align="center" cellspacing="0" summary="4Type Result">
	<tbody>
		<tr>
		<th scope="col" abbr="Configurations" class="nobg"><a name="#kingOrSolder">王様・軍人テスト</a></th>
		<th scope="col" abbr="Dual 1.2">王様</th>
    	<th scope="col" abbr="Dual 1.2">軍人</th>
  		</tr>
<%
		Iterator<Answer> it = ksList.iterator();
		while(it.hasNext()){
			Answer answer = it.next();
%>
		<tr>
		<th scope="row" abbr="Model" class="spec"><%=answer.getLabel() %>：<%=answer.getStatement() %></th>
		<td align="right"><%=answer.getFwdScore() %></td>
		<td align="right"><%=answer.getRewScore() %></td>
		</tr>
<%
		}
%>

	</tbody>
	</table>
	</p>
<%
	}
%>

<!-- 学者・職人テスト結果 -->
<%
	List<Answer> scList = (List<Answer>)request.getAttribute(ViewConstants.REQ_KEY_ANSWERS_SCHLCRFT);
	if(!scList.isEmpty()){
%>
	<br></br>
	<br></br>
	<p align="center">
	<table align="center" cellspacing="0" summary="4Type Result">
	<tbody>
		<tr>
		<th scope="col" abbr="Configurations" class="nobg"><a name="#kingOrSolder">学者・職人テスト</a></th>
		<th scope="col" abbr="Dual 1.2">学者</th>
    	<th scope="col" abbr="Dual 1.2">職人</th>
  		</tr>
<%
		Iterator<Answer> it = scList.iterator();
		while(it.hasNext()){
			Answer answer = it.next();
%>
		<tr>
		<th scope="row" abbr="Model" class="spec"><%=answer.getLabel() %>：<%=answer.getStatement() %></th>
		<td align="right"><%=answer.getFwdScore() %></td>
		<td align="right"><%=answer.getRewScore() %></td>
		</tr>
<%
		}
%>
	</tbody>
	</table>
	</p>
<%
	}
%>
</form>
<form name="reentry" method="POST" action="/entry">
<p align="center">
<table>
<tbody>
  <tr>
  	<th　colspan="7" class="nobg">
	<center><button onClick="javascript:Submit('reentry')">再実行</button></center>
	</th>
  </tr>
</tbody>
</table>
</p>



</form>
</body>
</html>