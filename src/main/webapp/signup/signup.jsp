<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 14/12/2023
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Netflix</title>
</head>
<body>
<h1>Step 2 of 3</h1>
<h1>Choose your plan</h1>
<ul>
    <li>No commitments, cancels anytime.</li>
    <li>Everything on Netflix for one low price.</li>
    <li>No ads and no extra fees. Ever.</li>
</ul>
<form action="/signup" method="post">
  <input type="submit" value="Next">
</form>

<script>
    localStorage.setItem("lastSignUpStep", "Step2");
    let dataToSend = localStorage.getItem("lastSignUpStep");
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/main', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
        }
    };
    xhr.send(JSON.stringify({key: dataToSend}))
</script>
</body>
</html>
