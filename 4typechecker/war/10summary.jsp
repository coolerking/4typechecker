<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="jp.freeex.fourtypes.control.WorkoutContext" %>
<%@ page import="jp.freeex.fourtypes.control.Utils" %>
<%@ page import="jp.freeex.fourtypes.ViewConstants" %>
<%@ page import="jp.freeex.fourtypes.model.Answer" %>
<%@ page import="jp.freeex.fourtypes.model.Count" %>
<%
	String kingOrSolder = (String)request.getAttribute(ViewConstants.REQ_KEY_SCORE_KINGSOLD);
	String scholarOrCraftsman = (String)request.getAttribute(ViewConstants.REQ_KEY_SCORE_SCHLCRFT);
	String king = (String)request.getAttribute(ViewConstants.REQ_KEY_SCORE_KING);
	String solder = (String)request.getAttribute(ViewConstants.REQ_KEY_SCORE_SOLDER);
	String scholar = (String)request.getAttribute(ViewConstants.REQ_KEY_SCORE_SCHOLAR);
	String craftsman = (String)request.getAttribute(ViewConstants.REQ_KEY_SCORE_CRAFTSMAN);
	String result = (String)request.getAttribute(ViewConstants.REQ_ATTRKEY_RESULT);
	boolean isKingOrSolder = Integer.parseInt(kingOrSolder)>=Integer.parseInt(scholarOrCraftsman);
	Count c = (Count)request.getAttribute(ViewConstants.REQ_ATTRKEY_COUNT);
	int total = c.getTotal();
	int kingTotal = c.getKing();
	int soldTotal = c.getSolder();
	int schlTotal = c.getScholar();
	int crftTotal = c.getCraftsman();
	int kingPer = (int)(((float)kingTotal)*100.0F/((float)total));
	int soldPer = (int)(((float)soldTotal)*100.0F/((float)total));
	int schlPer = (int)(((float)schlTotal)*100.0F/((float)total));
	int crftPer = (int)(((float)crftTotal)*100.0F/((float)total));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="css/base.css">
<link type="text/css" rel="stylesheet" href="css/tables.css">
<link rel="shortcut icon" href="/favicon.ico">
<title>FREEex　4タイプ判定テスト</title>
<script type="text/javascript" src="/js/drawRader.js"></script>
</head>
<body onload="drawRader(<%= kingOrSolder %>, <%= scholarOrCraftsman %>, <%= isKingOrSolder?king:craftsman %>, <%= isKingOrSolder?solder:scholar %>,<%= kingPer %>, <%= soldPer %>, <%= schlPer %>, <%= crftPer %>);">
<p align="right"><font size="-2">累積判定者数：<%= total %>人(<%= c.getCreatedAt().toString() %>以降)</font></p>

<h1>4タイプ判定テスト</h1>

<h1>結果</h1>

<p align="center">
あなたは、<font size="+2"><%= result %></font>です。
</p>

<center>
<canvas id="rader" width="400" height="400">
<p>
※HTML5対応ブラウザを使用すると、グラフを表示できます
</p>
</canvas>
</center>

<br>

<form name="option" method="POST" action="/option">

<p align="center">
<table align="center" cellspacing="0" summary="4Type Result">
<tbody>
  <tr>
    <th scope="col" abbr="Configurations" class="nobg">集計結果</th>
    <th scope="col" abbr="Dual 1.2">注目・司令</th>
    <th scope="col" abbr="Dual 1.2">法則・理想</th>
	<th scope="col" abbr="Dual 0.8">注目型</th>
	<th scope="col" abbr="Dual 0.8">司令型</th>
	<th scope="col" abbr="Dual 0.8">法則型</th>
	<th scope="col" abbr="Dual 0.8">理想型</th>
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
    <th scope="row" abbr="Model" class="spec"><a href="#" onClick="document.option.submit();return false">注目型・司令型テスト</a></th>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
<%
	}else{
%>
    <th scope="row" abbr="Model" class="spec">注目型・司令型テスト</th>
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
    <th scope="row" abbr="Model" class="spec"><a href="#" onClick="document.option.submit();return false">法則型・理想型テスト</a></th>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
<%
	}else{
%>
    <th scope="row" abbr="Model" class="spec">法則型・理想型テスト</th>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td bgcolor="#919191"> </td>
    <td align="right"><%= scholar %></td>
    <td align="right"><%= craftsman %></td>
<%
	}
	Map<String, String> hiddens = 
		(Map<String, String>)
			request.getAttribute(
					ViewConstants.REQ_ATTRKEY_HIDDENMAP);
	if(hiddens!=null && (!hiddens.isEmpty())){
		Iterator<String> iter2 = hiddens.keySet().iterator();
		while(iter2.hasNext()){
			String key2 = iter2.next();
			if(key2==null) continue;
			if(key2.equals(ViewConstants.REQ_ATTRKEY_RESULT)) continue;
			String value2 = hiddens.get(key2);
			if(value2==null) continue;
%>
	<input type="hidden" name="<%= key2 %>" value="<%=value2 %>" />
<%
		}
	}
%>
  </tr>
</tbody>
</table>
</p>

<br>

<p align="center">
お疲れ様でした（＾ ＾）<br>
結果はお分かりになりましたか？<br>
<br>
各タイプの説明は、こちらをご参照ください。
</p>

<p align="center">
<!--
<a href="http://www.amazon.co.jp/exec/obidos/ASIN/4023308838/otakingex01-22/">[Amazon]人生の法則 「欲求の4タイプ」で分かるあなたと他人</a>
<br>
-->
[<a href="<%= ViewConstants.URL_KING %>">注目型</a>][<a href="<%= ViewConstants.URL_SOLD %>">司令型</a>][<a href="<%= ViewConstants.URL_SCHL %>">法則型</a>][<a href="<%= ViewConstants.URL_CRFT %>">理想型</a>]
<br>
[<a href="<%= ViewConstants.URL_UNKN %>">判定不能の方</a>]
<br>
<!--
<br>
<a href="http://www.youtube.com/watch?v=zFEYE9HYJUY">[Youtube]岡田斗司夫のひとり夜話(2010/11/6)#7</a>
<br>
<a href="http://on.fb.me/ebpNKn">[Facebook]人間関係の特効薬　人生のトリセツ</a>
-->
</p>

<br>
<p align="center">
<a href="/21enquete.html" target="_blank">4タイプ判定テストのご意見をお聞かせください</a>
</P>
<%
	if(hiddens!=null && (!hiddens.isEmpty())){
		Iterator<String> iter = hiddens.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			if(key==null) continue;
			if(key.equals(ViewConstants.REQ_ATTRKEY_RESULT)) continue;
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
		<th scope="col" abbr="Dual 1.2">注目型・司令型</th>
    	<th scope="col" abbr="Dual 1.2">法則型・理想型</th>
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



<!-- 注目型・司令型テスト結果 -->
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
		<th scope="col" abbr="Configurations" class="nobg"><a name="#kingOrSolder">注目型・司令型テスト</a></th>
		<th scope="col" abbr="Dual 1.2">注目型</th>
    	<th scope="col" abbr="Dual 1.2">司令型</th>
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

<!-- 法則型・理想型テスト結果 -->
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
		<th scope="col" abbr="Configurations" class="nobg"><a name="#kingOrSolder">法則型・理想型テスト</a></th>
		<th scope="col" abbr="Dual 1.2">法則型</th>
    	<th scope="col" abbr="Dual 1.2">理想型</th>
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
	<center><input type="button" onClick="javascript:Submit('reentry')">再実行</input></center>
	</th>
  </tr>
</tbody>
</table>
</p>

<!-- facebook plugin start -->
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/ja_JP/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<center><div class="fb-like" data-href="http://4typechecker.appspot.com/" data-send="true" data-width="450" data-show-faces="true"></div></center>
<!-- facebook plugin end -->

</form>
</body>
</html>