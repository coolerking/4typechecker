<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8"%>
<%@ page import="com.otakingex.type4.ViewConstants" %>
<%@ page import="com.otakingex.type4.control.Utils" %>
<%@ page import="com.otakingex.type4.model.Count" %>
<%
       Count c = (Count)request.getAttribute(ViewConstants.REQ_KEY_COUNT);
       int total = c.getTotal();
       String date = Utils.getDate(c.getCreatedAt());
       int king = c.getKing();
       int solder = c.getSolder();
       int scholar = c.getScholar();
       int craftsman = c.getCraftsman();
       int kingPer = (int)(100.0F*(float)king/(float)total);
       int solderPer = (int)(100.0F*(float)solder/(float)total);
       int scholarPer = (int)(100.0F*(float)scholar/(float)total);
       int craftsmanPer = (int)(100.0F*(float)craftsman/(float)total);
       int other = total - king - solder - scholar - craftsman;
       int otherPer = 100 - kingPer - solderPer - scholarPer - craftsmanPer;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="css/base.css">
<link type="text/css" rel="stylesheet" href="css/tables.css">
<link rel="shortcut icon" href="/favicon.ico">
<title>FrEEex 4タイプ判定テスト</title>
</head>
<body>
<h1>4タイプ判定テスト</h1>

<h1>統計情報</h1>

<p align="center">
<%=date %>から計測開始しました。<br>
母集団<%=total %>人の統計情報です。
</p>
<br>
<p align="center">
<img src="http://chart.apis.google.com/chart?cht=p3
&amp;chco=0000FF
&amp;chd=t:<%= kingPer %>,<%= solderPer %>,<%= scholarPer %>,<%= craftsmanPer %>,<%= otherPer %>
&amp;chs=500x250
&amp;chf=bg,s,E6EAE9
&amp;chl=注目型|司令型|法則型|理想型|その他" />
</P>
<br>
<br>
<p align="center">
<table align="center" cellspacing="0" summary="4Type Result">
<tbody>
 <tr>
       <th scope="col" abbr="Dual 1.2">注目型</th>
       <th scope="col" abbr="Dual 1.2">司令型</th>
       <th scope="col" abbr="Dual 1.2">法則型</th>
       <th scope="col" abbr="Dual 1.2">理想型</th>
       <th scope="col" abbr="Dual 1.2">その他</th>
 </tr>
   <td align="right"><%= king %>人(<%=kingPer %>%)</td>
   <td align="right"><%= solder %>人(<%=solderPer %>%)</td>
   <td align="right"><%= scholar %>人(<%=scholarPer %>%)</td>
   <td align="right"><%= craftsman %>人(<%=craftsmanPer %>%)</td>
   <td align="right"><%= other %>人(<%=otherPer %>%)</td>
</body>
</html>