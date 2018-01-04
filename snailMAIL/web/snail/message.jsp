<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/30
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息</title>

</head>
<body valign="middle" align="center" bgcolor="#f0f8ff">
<a href="MessageServlet?action=readed&id=${id}">已读</a>
<a href="MessageServlet?action=sented&id=${id}">已发送</a>
<a href="MessageServlet?action=unsent&id=${id}">未发送</a>
<a href="editor.jsp?id=${id}">编辑信息</a>
<br/><br/><br/>
<center>
<table border="1">
    <tr><h2><c:if test="${action=='readed'}">已读</c:if>
        <c:if test="${action=='sented'}">已发送</c:if>
        <c:if test="${action=='unsent'}">未发送</c:if>
    </h2></tr>

    <c:if test="${action!='readed'}">
        <tr>

            <td>
                接收人
            </td>
            <td>
                内容
            </td>
            <td>
                发送时间
            </td>
            <td>
                操作
            </td>
        </tr>

        <c:forEach var="row" items="${messages}">

            <tr>

                <td>
                    <c:out value="${row.acceptID}"></c:out>
                </td>
                <td>
                    <c:out value="${row.mess}"></c:out>
                </td>
                <td>
                    <c:out value="${row.recordtime}"></c:out>
                </td>
                <td>
                    <script language="javascript">

                        function logout(){
                            if (confirm("你确定要删除这条信息吗？是－选择确定，否-选择取消")){
                                window.location.href="EditorServlet?messid=${row.id}&id=${id}"
                            }
                        }

                    </script>
                    <input type="button" onclick="logout()" value="删除"/>

                <%--<a href="EditorServlet?messid=${row.id}&id=${id}">删除</a>--%>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${action=='readed'}">
        <tr>

            <td>
                发送人
            </td>
            <td>
                内容
            </td>
            <td>
                发送时间
            </td>
            <td>
                操作
            </td>
        </tr>

        <c:forEach var="row" items="${messages}">

            <tr>

                <td>
                    <c:out value="${row.sentID}"></c:out>
                </td>
                <td>
                    <c:out value="${row.mess}"></c:out>
                </td>
                <td>
                    <c:out value="${row.recordtime}"></c:out>
                </td>
                <td>

                    <script language="javascript">

                        function logout(){
                            if (confirm("你确定要删除这条信息吗？是－选择确定，否-选择取消")){
                                window.location.href="EditorServlet?messid=${row.id}&id=${id}"
                            }
                        }

                    </script>
                        <input type="button" onclick="logout()" value="删除"/>
                    <%--<a href="EditorServlet?messid=${row.id}&id=${id}">删除</a>--%>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</center>
</body>
</html>
<%@include file="background.html"%>