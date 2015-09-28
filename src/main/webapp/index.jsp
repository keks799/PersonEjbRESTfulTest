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
        var mainFormAction = 'rs/person/new';
        console.log("loaded!");
        //test
        $(".formTextInput:not(.dateInput)").val("test");
        $(".dateInput").val("12.12.1971");
        //test

        getExistedPersonList();

        $("#personForm").submit(function (e) {
            e.preventDefault();
            var formData = $("#personForm").serialize();

            $.ajax({
                type: "POST",
                url: mainFormAction,
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

        $("#personListedTable").on("click", ".del", function(){
            var selectedTr = $(this).parents("tr");
            var id = selectedTr.attr("id");
            $.ajax({
                type: "POST",
                url: 'rs/person/delete/' + id,
                dataType: 'json',
                success: function (data) {
                    selectedTr.fadeOut();
                    selectedTr.remove();
                },
                error: function (data) {
                    alert("An error has occurred!")
                }
            });
        });

        $("#personListedTable").on("click", ".edit", function(){
            var selectedTr = $(this).parents("tr");
            var id = selectedTr.attr("id");
            var firstName = selectedTr.find(".firstName").text();
            var middleName = selectedTr.find(".middleName").text();
            var lastName = selectedTr.find(".lastName").text();
            var birthDate = selectedTr.find(".birthDate").text();

            $("#id").val(id);
            $("#firstName").val(firstName);
            $("#middleName").val(middleName);
            $("#lastName").val(lastName);
            $("#birthDate").val(birthDate);
            $("#submitButton").val("Edit");
            mainFormAction = 'rs/person/edit'
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
                $("tr:last").attr("id", data[i].id);
                $(".firstName:last").text(data[i].firstName);
                $(".middleName:last").text(data[i].middleName);
                $(".lastName:last").text(data[i].lastName);
                $(".birthDate:last").text(day + "." + month + "." + year);
                if(!$("#personListedTable").is(":visible")) {
                    $("#personListedTable").fadeIn();
                }
                $("tr.appendedRow").fadeIn();

                appendRowToList();
            }
        }
    }

    function appendRowToList() {
        var rowHtml = '<tr style="display: none;" class="appendedRow"><td class="appendedRow firstName"></td><td class="appendedRow middleName"></td>' +
                '<td class="appendedRow lastName"></td><td class="appendedRow birthDate"></td><td class="edit"><span class="edit" style="cursor: pointer">[e]</span></td>' +
                '<td><span class="del" style="cursor: pointer">X</span></td></tr>';

        $("#personListedTable tr:last").after(rowHtml);
    }
</script>

<form method="POST" id="personForm">
    <input type="hidden" name="id" id="id"/>
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
                Middle Name:
            </td>
            <td>
                <input type="text" name="middleName" id="middleName" class="formTextInput">
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
                Birth Date:
            </td>
            <td>
                <input type="text" name="birthDate" id="birthDate" class="formTextInput dateInput">
            </td>
        </tr>
    </table>
    <input type="submit" value="Save" id="submitButton">
</form>
<table id="personListedTable" style="display: none;">
    <tr class="header">
        <th class="firstName th">Имя</th>
        <th class="middleName th">Отчество</th>
        <th class="lastName th">Фамилия</th>
        <th class="birthDate th">Дата рождения</th>
        <th>Edit</th>
        <th>Drop</th>
    </tr>
    <tr style="display: none;" class="appendedRow">
        <td class="appendedRow firstName"></td>
        <td class="appendedRow middleName"></td>
        <td class="appendedRow lastName"></td>
        <td class="appendedRow birthDate"></td>
        <td>
            <span class="edit" style="cursor: pointer">[e]</span>
        </td>
        <td>
            <span class="del" style="cursor: pointer">X</span>
        </td>
    </tr>
</table>

</div>
</body>
</html>
