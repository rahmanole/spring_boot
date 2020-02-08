$(document).ready(function () {
    $('#academicField').hide();
    $('#alimClassField').hide();

    $('#className').focusout(function () {
        var course = $('#className').val();
        console.log(course);
        $.ajax({
            method: "GET",
            url: '/teacher/getTeacherByCourse/'+course,
            success: function (data) {
                console.log(data);
                if(data != ""){
                    $('#teacherSaveStatus').html('<span class="text-danger">Teacher already assigned</span>');
                }else{
                    $('#teacherSaveStatus').html("");
                }
            },
            error: function () {
                console.log('error to get teacher');
            }
        });
    });
    var course = "";

    $('#course').change(function () {
        course = $('#course option:selected').val();

        if(course == 'Academy'){
            $('#academicField').show();
            $('#alimClassField').hide();

            $('#academic').change(function () {
                course = $('#academic option:selected').val();
                console.log(course);
            });
        }

        if(course == 'Alim' || course == 'Alima'){
            $('#alimClassField').show();
            $('#academicField').hide();

            $('#alimClasses').change(function () {
                course = $('#alimClasses option:selected').val();
                console.log(course);
            });
        }
    });
});