$(document).ready(function () {
    $.ajax({
        method: "GET",
        url: '/getAllSrchKeys',
        success: function (data) {
            $("#search").autocomplete({
                source: data
            });
        }
    });
});

