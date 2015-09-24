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
        //test
        $(".formTextInput:not(.dateInput)").val("test");
        $(".dateInput").val("12.12.1212");
        //test

        $("#personForm").on().submit(function(e){
            e.preventDefault();
            var formData = $("#personForm").serialize();

            $.ajax({
                type: "POST",
                url: 'rs/person/new',
                data: formData,
                dataType: 'json',
                success: function(data) {
                    appendRowToList();
                }
            });
            return 0;
        })
    });

    function appendRowToList(){
        var rowHtml = '<tr><td clas="appendedRow firstName"></td><td clas="appendedRow middleName"></td>' +
                '<td clas="appendedRow lastName"></td><td clas="appendedRow dateOfBirth"></td></tr>';

        var tableHtml = '<table id="personListedTable"><th class="firstNameTh">Имя</th><th class="middleNameTh">Отчество</th>' +
                '<th class="lastNameTh">Фамилия</th><th class="dateOfBirthTh">Дата рождения</th></table>';

        if ($("#personListedTable").size() == 0) {
            $("#personForm").after(tableHtml);
            $("#personListedTable th:last").after(rowHtml);
        } else {
            $("#personListedTable tr:last").after(rowHtml);
        }
    }
</script>

<form action="rs/person/new" method="POST" id="personForm">
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
<div id="personList">

</div>
</body>
</html>
