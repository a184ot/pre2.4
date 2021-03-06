<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Read</title>
</head>
<body>
<div align="center">
    <table width="80%">
        <caption><h2>My data list</h2></caption>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Login</th>
            <th>Password</th>
            <th>E-mail</th>
            <th>Age</th>
            <th>Role</th>
            <th>Action1</th>
            <th>Action2</th>
        </tr>
        <c:forEach var="user" items="${userList}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.viewName}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.age}"/></td>
<%--            <td><c:out value="${user.role}"/></td>--%>
            <td><c:forEach var="role" items="${user.role}">
                <option value="<c:out value="${role}"/>"><c:out value="${role}"/></option>
            </c:forEach>
            </td>
            <td>
        </tr>
        </c:forEach>
    </table>
</div>
<hr>
<div align="center">
    <table>
        <caption><h2>Actions</h2></caption>
        <tr>
            <c:if test="${role.contains('ADMIN')}">
            <th align="left">
                <form action="admin" method="post">
                    <button name="" value="">List All Users</button>
                </form>
            </th>
            <th align="left">
                <form action="create" method="post">
                    <button name="" value="">Add New User</button>
                </form>
            </th>
            </c:if>
            <th align="left">
                <form action="logout" method="post">
                    <button name="" value="">Logout</button>
                </form>
            </th>
            <th align="left">
                <form action="admin" method="get">
                    <button name="" value="">Admin</button>
                </form>
            </th>
        </tr>
    </table>
</div>
</body>
</html>
