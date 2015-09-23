<%--
  Created by IntelliJ IDEA.
  User: Business_Book
  Date: 11.09.2015
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="js/jquery/jquery-2.1.4.min.js" type="text/javascript" charset="UTF-8"></script>
    <title></title>
</head>
<body>
<script type="text/javascript">
    $(function () {
        console.log("loaded!");
    })
</script>
<form action="ws/person/new" method="POST">
    <table>
        <tr>
            <td>
                Name:
            </td>
            <td>
                <input type="text" name="firstname" id="firstname" class="formTextInput">
            </td>
        </tr>
        <tr>
            <td>
                Family Name:
            </td>
            <td>
                <input type="text" name="lastname" id="lastname" class="formTextInput">
            </td>
        </tr>
        <tr>
            <td>
                Middle Name:
            </td>
            <td>
                <input type="text" name="middlename" id="middlename" class="formTextInput">
            </td>
        </tr>
        <tr>
            <td>
                Birth Date:
            </td>
            <td>
                <input type="text" name="birthdate" id="birthdate" class="formTextInput dateInput">
            </td>
        </tr>
    </table>
    <input type="submit" value="Save">
</form>
</body>
</html>
