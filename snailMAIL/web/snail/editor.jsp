<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/31
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息编辑</title>
    <script type="text/javascript">
        function checkN() {
            acceptID = document.getElementById("acceptID").value;
            acceptIDwarn = document.getElementById("acceptIDwarn");
            if (acceptID == "") {
                acceptIDwarn.innerHtml = "收信人不能为空";
                return false;
            } else {
                acceptIDwarn.innerHtml = "";
                return true;
            }
        }
        function checkText() {
            sub = document.getElementById("sub").value;
            subwarn = document.getElementById("subwarn");
            if (sub == "") {
                subwarn.innerHTML = "信息内容不能为空";
                return false;
            } else {
                subwarn.innerHTML = "";
                return true;
            }
        }
        function check() {
            if(!checkN()){
                alert("收信人不能为空");
                return false;
            }else if(!checkText()){
                alert("内容不能为空");
                return false;
            }else{
                return true;
            }

        }


    </script>
</head>
<body bgcolor="#f0f8ff">
<%

    request.setAttribute("id", request.getParameter("id"));
%>
<form action="EditorServlet?id=${id}" method="post" onsubmit="return check();">

    <table align="center" width="35%">
        <center>
            <span style="color: #d6090e;"> <c:if test="${unexitMess!=null}">${unexitMess}</c:if></span>
            <tr>
                <td align="right">收信人ID：</td>
                <td><input type="text" id="acceptID" name="acceptID" onblur="checkN()"/>
                    <font size="2"><span id="acceptIDwarn" style="color: #d6090e;"></span></font>
                </td>
            </tr>
            <tr>
                <td align="right">
                    内容：
                </td>
                <td>
                    <textarea id="sub" name="sub" rows="3" cols="20" onblur="checkText()"></textarea>
                    <font size="2"><span id="subwarn" style="color: #d6090e;"></span></font>
                </td>

            </tr>
            <tr>
                <td align="right">
                    发送时间：
                </td>
                <%
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    request.setAttribute("date", df.format(new Date()));
                %>
                <td>
                    <input type="datetime" name="datetime" value="${date}" placeholder="默认系统时间">
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <input type="submit" value="发送">
                    <input type="reset" name="reset" id="reset" value="重置">
                </td>
            </tr>
        </center>
    </table>

</form>
</body>
</html>
<%@include file="background.html"%>