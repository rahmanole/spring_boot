$(document).ready(function () {

    $('.boyCourses').hide();
    $('.girlCourses').hide();

    $('input[type=radio][name=gender]').change(function () {


        if ($(this).is(":checked")) {
            let course = getCourseList($(this).val());

            if ($(this).val() == 'Girl') {
                $('.boyCourses').hide();
                $('.girlCourses').show();
                $('#boardingInfo').hide();

                // for (let i = 0; i < course.length; i++) {
                //     $("#courses").html("<option value=\"" + course[i] + "\">" + course[i] + "</option>");
                // }
            }else {
                $('.girlCourses').hide();
                $('.boyCourses').show();
                $('#boardingInfo').show();

            }
        }
    });

    $('.phone').inputmask('+999-999-999', { placeholder: '___-___-___' });
    $('#dob').inputmask('m/d/y', { placeholder: 'mm/dd/yyyy' });
    $('#email').inputmask({ alias: "email" });


    var name,fName,mName;

    $('#sif').submit(function (event) {
        name = $('#name').val();
        fName = $('#fName').val();
        mName = $('#mName').val();
        var student = $('#sif').serializeJSON();
        console.log(student);
        console.log(student.gender);

        if(isStudentExists(name,fName,mName) == true){
            $('#studentSavingStatus').html('<span class="text-danger">This student already exists</span>');
            event.preventDefault();
            return;
        }else if(student.gender == undefined){
            $('#studentSavingStatus').html('<span class="text-danger">Select your gender</span>');
            event.preventDefault();
            return;
        }else if(student.courseName == 'Select_a_course'){
            $('#studentSavingStatus').html('<span class="text-danger">Select your course</span>');
            event.preventDefault();
            return;
        }else if(student.gender == 'Boy'){
            if(student.boarding == undefined){
                $('#studentSavingStatus').html('<span class="text-danger">Select your boarding</span>');
                event.preventDefault();
                return;
            }
        }else if(student.d == ''){
            $('#studentSavingStatus').html('<span class="text-danger">Enter your valid date of birth</span>');
            event.preventDefault();
            return;
        }else if(student.parentEmail == ''){
            $('#studentSavingStatus').html('<span class="text-danger">Enter parent email</span>');
            event.preventDefault();
            return;
        }else if( isStudentExists(name,fName,mName) == 'serverErr'){
            $('#studentSavingStatus').html('<span class="text-danger">Could not connect with server</span>');
            event.preventDefault();
            return;
        }

        event.preventDefault();
        console.log(isStudentExists(name,fName,mName));
    });

    $('#courses').change(function () {
        if($(this).val() == 'Academy'){
            $('#boardingInfo').hide();
        }else{
            $('#boardingInfo').show();
        }
    });

    var video = document.getElementById("videoFrame"),
        vendorUrl = window.URL;

    navigator.getUserMedia = navigator.getUserMedia ;

    // navigator.getUserMedia({
    //     video:true,
    //     audio:false
    // },function (stream) {
    //     video.src = vendorUrl.createObjectURL(stream);
    //     video.play();
    // },function () {
    //
    // });








});

function isStudentExists(name,fName,mName) {
    var isStudentExists=false;
    $.ajax({
        method:'GET',
        url:'/student/isExists/'+name+'/'+fName+'/'+mName,
        async:false,
        success:function (data) {
            isStudentExists = data;
        },
        error:function () {
            console.log('could not connect');
            isStudentExists = 'serverErr';
        }

    });

    return isStudentExists;
}

function getCourseList(studentType) {
    var courses;

    $.ajax({
        method:'GET',
        url:'/course/'+studentType,
        async:false,
        success:function (data) {
            courses = data;
        },
        error:function () {
            console.log('could not connect');
            courses = 'serverErr';
        }

    });

    return courses;
}


