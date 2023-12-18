<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form action="${pageContext.request.contextPath}/login/changePass" method="post">
  <table>
    <tr>
      <td>Enter new password: </td>
      <td>
        <label>
          <input type="password" name="newPass" id="newPass" oninput="change();check()">
        </label>
        <p style="color: red; display: none" id="size1" >Between 5 and 60 characters</p>
      </td>
    </tr>
    <tr>
      <td>Enter new password again: </td>
      <td>
        <label>
          <input type="password" name="newPassAgain" id="newPassAgain" oninput="change();check()">
        </label>
        <p style="color: red; display: none" id="size2" >Between 5 and 60 characters</p>
      </td>
    </tr>
      <tr>
        <td colspan="2" style="align-items: center; color: red ; display: none " id="catch" >Not catch</td>
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
<script>
  function change() {
    document.getElementById("catch").style.display = "none";
    document.getElementById("size1").style.display = "none";
    document.getElementById("size2").style.display = "none";
  }

  function check1() {
    let pass = document.getElementById("newPass").value;
    if (pass.length > 0 && (pass.length < 5 || pass.length > 60)) {
      document.getElementById("size1").style.display = "block";
      return false;
    }
    return true;
  }

  function check2() {
    let pass = document.getElementById("newPassAgain").value;
    if (pass.length > 0 && (pass.length < 5 || pass.length > 60)) {
      document.getElementById("size2").style.display = "block";
      return false;
    } else {
      return true;
    }
  }

  function check3() {
    let pass = document.getElementById("newPassAgain").value;
    let passAgain = document.getElementById("newPass").value;
    if (check1() === true && check2() === true && pass !== passAgain) {
      document.getElementById("catch").style.display = "block";
    }
  }

  function check() {
    setTimeout(check1, 2000);
    setTimeout(check2, 2000);
    setTimeout(check3, 1000);
  }
</script>
</html>
