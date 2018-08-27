$(document).ready(function () {
    var username = $("#username").attr("username");
    var showName = username == "" ? "" : username;

    $("#welcome").text("当前用户：" + showName);
});