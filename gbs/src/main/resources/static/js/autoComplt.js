
$(document).ready(function () {
    $.ajax({
        method: "GET",
        url: '/getAllSrchKeys',
        success: function (data) {
            $("#second").append().html("");
            for (var i = 0; i < data.length; i++) {
                $("#second").append("<option value=\"" + data[i].value + "\">" + data[i] + "</option>");
            }

            auto(data);
        }
    });
});
 function auto(tags){
    $( "#search" ).autocomplete({
        source: tags
    });
} ;