$(document).ready(function () {

    $('#interval').change(function () {
        var interval = $('#interval option:selected').val();
        console.log(interval);
    });

    $('#addSponsor').click(function () {
        var frm  = $("form").serializeArray();
        var name = $('#sponsorName').val();
        var sponsor = {
            'name':name,
        };
        console.log(frm);
    });
});






