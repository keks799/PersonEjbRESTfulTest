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

        getExistedPersonList();

        $("#personForm").on().submit(function (e) {
            e.preventDefault();
            var formData = $("#personForm").serialize();

            $.ajax({
                type: "POST",
                url: 'rs/person/new',
                data: formData,
                dataType: 'json',
                success: function (data) {
                    displayPersonTable(data);
                },
                error: function (data) {
                    alert("An error has occurred!")
                }
            });
            return 0;
        });
    });

    function getExistedPersonList() {
        $.ajax({
            type: "GET",
            url: 'rs/person/all',
            success: function (data) {
                displayPersonTable(data);
            },
            error: function (data) {
                alert("An error has occurred!")
            }
        });
    }

    function displayPersonTable(data) {
        if (data.length != 0) {
            for (var i = 0; i < data.length; i++) {
                appendRowToList();
                var date = new Date(data[i].birthDate);
                var day = date.getUTCDate() + 1;
                var month = date.getUTCMonth() + 1;
                var year = date.getUTCFullYear();

                if (day < 10) {
                    day = "0" + day;
                }
                if (month < 10) {
                    month = "0" + month;
                }
                $(".firstName:last").text(data[i].firstName);
                $(".middleName:last").text(data[i].middleName);
                $(".lastName:last").text(data[i].lastName);
                $(".dateOfBirth:last").text(day + "/" + month + "/" + year);
            }
        }
    }

    function appendRowToList() {
        var rowHtml = '<tr><td class="appendedRow firstName"></td><td class="appendedRow middleName"></td>' +
                '<td class="appendedRow lastName"></td><td class="appendedRow dateOfBirth"></td><td class="edit"><input type="checkbox" class="editChB"></td>' +
                '<td><span class="del" style="cursor: pointer">X</span></td></tr>';

        var tableHtml = '<table id="personListedTable"><tr class="header"><th class="firstName th">Имя</th><th class="middleName th">Отчество</th>' +
                '<th class="lastName th">Фамилия</th><th class="dateOfBirth th">Дата рождения</th><th>Edit</th><th>Drop</th></tr></table>';

        if ($("#personListedTable").size() == 0) {
            $("#personForm").after(tableHtml);
            $("#personListedTable tr.header").after(rowHtml);
        } else {
            $("#personListedTable tr:last").after(rowHtml);
        }
    }
</script>

<form action="rs/person/new" method="POST" id="personForm">
    <input type="hidden" name="id"/>
    <table>
        <tr>
            <td>
                Name:
            </td>
            <td>
                <input type="text" name="firstName" id="firstName" class="formTextInput">
            </td>
        </tr>
        <tr>
            <td>
                Family Name:
            </td>
            <td>
                <input type="text" name="lastName" id="lastName" class="formTextInput">
            </td>
        </tr>
        <tr>
            <td>
                Middle Name:
            </td>
            <td>
                <input type="text" name="middleName" id="middleName" class="formTextInput">
            </td>
        </tr>
        <tr>
            <td>
                Birth Date:
            </td>
            <td>
                <input type="text" name="birthDate" id="birthdate" class="formTextInput dateInput">
            </td>
        </tr>
    </table>
    <input type="submit" value="Save">
</form>
<div id="personList">

</div>
</body>
</html>
