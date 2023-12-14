<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 13/12/2023
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change password</title>
</head>
<body>
<h1>Change password</h1>
<form action="${pageContext.request.contextPath}/change_pass" method="post">
  <table>
    <tr>
      <td>Enter new password: </td>
      <td>
        <label>
          <input type="password" name="newPass">
        </label>
      </td>
    </tr>
    <tr>
      <td>Enter new password again: </td>
      <td>
        <label>
          <input type="password" name="newPassAgain">
        </label>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <label>
          <input type="submit" value="Change password">
        </label>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
