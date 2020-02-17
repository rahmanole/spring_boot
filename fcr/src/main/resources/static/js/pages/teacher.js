$(document).ready(function () {
    $('#academicField').hide();
    $('#alimClassField').hide();

    $('#className').focusout(function () {
        var className = $('#className').val();
        if(className == ''){
            return;
        }
        console.log(course);
        isTeacherAssigned(className);

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

    $('#addTeacherForm').submit(function (event) {
        let teacher = $('#addTeacherForm').serializeJSON();

        if(teacher.teacherName == '' || teacher.className=='' || teacher.className == ''){
            $('#teacherSaveStatus').html('<span class="text-danger">Fill out all the fields</span>');
            event.preventDefault();
        }
    })



});

function isTeacherAssigned(className) {
    $.ajax({
        method: "GET",
        url: '/teacher/isAssigned/'+className,
        success: function (data) {
            console.log(data);
            if(data == true){
                $('#teacherSaveStatus').html('<span class="text-danger">Teacher already assigned</span>');
            }else{
                $('#teacherSaveStatus').html("");
            }
        },
        error: function () {
            $('#teacherSaveStatus').html('<span class="text-danger">Could not connect to server</span>');
            console.log('error to get teacher');
        }
    });
}