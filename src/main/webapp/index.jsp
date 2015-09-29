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
    <script src="js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css">
    <link rel="stylesheet" href="js/jquery/jquery-ui-1.11.4.custom/jquery-ui.structure.min.css">
    <link rel="stylesheet" href="js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css">
    <title></title>
</head>
<body>
<script type="text/javascript">
    var mainFormAction = 'rs/person/new';
    function deletePerson(selectedTr) {
        var id = selectedTr.attr("id");
        $.ajax({
            type: "DELETE",
            url: 'rs/person/delete/' + id,
            dataType: 'json',
            success: function (data) {
                if(selectedTr.siblings("tr.appendedRow:visible").size() == 0) {
                    $("tr.header").fadeOut();
                }
                selectedTr.fadeOut();
                selectedTr.remove();
            },
            error: function (data) {
                alert("An error has occurred!")
            }
        });
    }

    function editPerson(selectedTr) {
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
        mainFormAction = 'rs/person/edit';
    }

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
        function dayMonthNormalize() {
            if (day < 10) {
                day = "0" + day;
            }
            if (month < 10) {
                month = "0" + month;
            }
        }

        if (data.length != 0) {
            if ( Object.prototype.toString.call( data ) === '[object Array]' ) {
                for (var i = 0; i < data.length; i++) {
                    var date = new Date(data[i].birthDate);
                    var day = date.getUTCDate() + 1;
                    var month = date.getUTCMonth() + 1;
                    var year = date.getUTCFullYear();

                    dayMonthNormalize();
                    $("tr.appendedRow:last").attr("id", data[i].id);
                    $(".firstName:last").text(data[i].firstName);
                    $(".middleName:last").text(data[i].middleName);
                    $(".lastName:last").text(data[i].lastName);
                    $(".birthDate:last").text(day + "." + month + "." + year);
                    if (!$("#personListedTable").is(":visible")) {
                        $("#personListedTable").fadeIn();
                    }
                    $("tr.appendedRow").fadeIn();

                    appendRowToList();
                }
            } else {
                var date = new Date(data.birthDate);
                var day = date.getUTCDate() + 1;
                var month = date.getUTCMonth() + 1;
                var year = date.getUTCFullYear();
                dayMonthNormalize();
                var editedTr = $("#personListedTable").find("tr#" + data.id);
                editedTr.children(".firstName").text(data.firstName);
                editedTr.children(".middleName").text(data.middleName);
                editedTr.children(".lastName").text(data.lastName);
                editedTr.children(".birthDate").text(day + "." + month + "." + year);
                $("#personForm input:not([type=submit])").val("");
                mainFormAction = 'rs/person/new';
                $("#submitButton").val("Save");
                editedTr.effect("highlight", {color: 'red'}, 1500);
            }
        }
    }

    function appendRowToList() {
        var rowHtml = '<tr style="display: none;" class="appendedRow"><td class="appendedRow firstName"></td><td class="appendedRow middleName"></td>' +
                '<td class="appendedRow lastName"></td><td class="appendedRow birthDate"></td><td class="edit"><span class="edit" style="cursor: pointer">[e]</span></td>' +
                '<td class="del"><span class="del" style="cursor: pointer">X</span></td></tr>';

        if(!$(".header").is(":visible")) {
            $(".header").fadeIn();
        }
        $("#personListedTable tr:last").after(rowHtml);
        $("#personForm input:not([type=submit])").val("");
    }

    function validate() {
        if($("#personForm input:not([type=submit]):not(#id)").filter(function() {
            return !this.value;
        }).length > 0) {
            $("#personFormTable").effect("shake", {distance: 5}, 1000);
            return false;
        }
        return true;
    }

    $(function () {
        console.log("loaded!");

        $(".dateInput").datepicker({
            dateFormat: "dd.mm.yy",
            changeMonth: true,
            changeYear: true,
            yearRange: '1900:+nn'
        });
        getExistedPersonList();

        $("#personForm").submit(function (e) {
            e.preventDefault();
            if (validate()) {
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
            }
            return 0;
        });

        $("#personListedTable").on("click", ".del", function () {
            deletePerson($(this).parents("tr"));
        });

        $("#personListedTable").on("click", ".edit", function () {
            editPerson($(this).parents("tr"));
        });
    });
</script>

<style>
    #personFormTable tr {
        height: 50px;
    }
    #personFormTable input {
        width: 50%;
        min-width: 500px;
    }
    #personListedTable {
        width: 50%;
        min-width: 500px;
        border-style: dotted;
    }
    .header {
        background-color: beige;
    }
    td.edit, td.del, td.birthDate {
        text-align: center;
    }
    td.firstName, td.middleName, td.lastName {
        text-align: right;
    }
</style>

<form method="POST" id="personForm">
    <input type="hidden" name="id" id="id"/>
    <table id="personFormTable">
        <tr>
            <td>Name*:<br />
                <input type="text" name="firstName" id="firstName" class="formTextInput">
            </td>
        </tr>
        <tr>
            <td>
                Middle Name*:<br />
                <input type="text" name="middleName" id="middleName" class="formTextInput">
            </td>
        </tr>
        <tr>
            <td>Family Name*:<br />
                <input type="text" name="lastName" id="lastName" class="formTextInput">
            </td>
        </tr>
        <tr>
            <td>Birth Date*:<br />
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
        <td class="edit">
            <span class="edit" style="cursor: pointer">[e]</span>
        </td>
        <td class="del">
            <span class="del" style="cursor: pointer">X</span>
        </td>
    </tr>
</table>

</div>
</body>
</html>
