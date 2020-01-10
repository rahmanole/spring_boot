$(document).ready(function () {
    $('#bookFeeDivOnSIF').hide();

    $('#courseOnSIF').change(function () {
        var course = $('#courseOnSIF option:selected').val();
        console.log(course);
        if (course == 'Alim' || course == 'Alima' || course == 'na') {
            $('#bookFeeDivOnSIF').show();
        } else {
            $('#bookFeeDivOnSIF').hide();
        }
    });
})