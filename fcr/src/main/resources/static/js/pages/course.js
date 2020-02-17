$(document).ready(function () {


    $('#addCourseForm').submit(function (event) {
        event.preventDefault();
        var course = $('#addCourseForm').serializeJSON();
        console.log(course);

        if(course.studentType == 'select'){
            $('#courseSaveStatus').html('');
            $('#courseSaveStatus').html('<span class="text-danger">Select student type</span>')
            return;
        }

        if(course.courseName == ''){
            $('#courseSaveStatus').html('');
            $('#courseSaveStatus').html('<span class="text-danger">Insert course name</span>')
            return;
        }

        $.ajax({
            method: 'post',
            url: '/course/save',
            data: course,
            cache: false,

            success: function () {
                $('#courseSaveStatus').html('<span class="text-success">Course Saved</span>')
            },
            error: function () {
                console.log('not success');
            }
        });

    });
});






















