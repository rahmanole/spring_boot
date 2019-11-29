$(document).ready(function () {
    var gender = 'male';
    var stType = 0;
    var acFee = 0;
    var resFee = 0;
    var tuitionFee = 3800;
    var due = 0;
    var mealFee = 100;
    var bookFee = 0;
    var mandatoryFee = 0;
    var total= 3900;

    $('#residentialFee').val(0.00);

    mealFee = $('#mealFee').val();

    mandatoryFee = totalMandatoryFee(stType, acFee, mealFee, bookFee);
    $('#totalMandatoryFee').html(mandatoryFee.toFixed(2));

    $('#total').html((totalMandatoryFee(stType, acFee, mealFee, bookFee)+tuitionFee).toFixed(2));


    $('input[type=radio][name="admisnFee"]').change(function () {

        if ($(this).is(":checked")) { // check if the radio is checked
            stType = parseInt($(this).val()); // retrieve the value
            if (stType == 200.00) {
                $('#tuitionDue').hide();
                $('#collectionDue').hide();
                due = 0;
            } else {

                $('#tuitionDue').show();
                $('#collectionDue').show();
                due = parseInt($('#due').val());
            }

            $('#admsnFee').val(stType.toFixed(2));

            mandatoryFee = totalMandatoryFee(stType, acFee, mealFee, bookFee);
            $('#totalMandatoryFee').html(mandatoryFee.toFixed(2));

            total = totoalTuitionFee(mandatoryFee,tuitionFee,resFee)
            $('#total').html(total.toFixed(2));

            console.log(stType);
        }

    });


    $('#course').change(function () {
        acFee = parseInt($('#course option:selected').val());

        var course = $('#course option:selected').val();

        if(course == 'Alim' || course == 'Alima'){
            $('#bookFeeDiv').show();
            acFee = (course == 'Alim')?755:240;
        }else{
            $('#bookFeeDiv').hide();
        }
        if(course == 'Select a course'){
            acFee =0;
        }
        $('#courseFee').val(acFee.toFixed(2));
        mandatoryFee = totalMandatoryFee(stType, acFee, mealFee, bookFee);
        $('#totalMandatoryFee').html(mandatoryFee.toFixed(2));

        total = totoalTuitionFee(mandatoryFee,tuitionFee,resFee)
        $('#total').html(total.toFixed(2));
    });

    $('#bookFee').change(function () {
        var bookFeeCat = $('#bookFee option:selected').val();

        switch (bookFeeCat) {
            case 'Oola':
                bookFee = 80;
                console.log(bookFee);
                break;
            case 'Thania' :
                bookFee = (gender == 'male') ? 75.00 : 70.00;
                console.log(bookFee);
                break;
            case 'Thalitha' :
                bookFee = (gender == 'male') ? 80.00 : 70.00;
                console.log(bookFee);
                break;
            case 'Rabiya' :
                bookFee = (gender == 'male') ? 60.00 : 75.00;
                console.log(bookFee);
                break;
            case 'Khamisa' :
                bookFee = (gender == 'male') ? 80.00 : 90.00;
                console.log(bookFee);
                break
            case 'Saadisa' :
                bookFee = (gender == 'male') ? 100.00 : 95.00;
                console.log(bookFee);
                break
            case 'Darua' :
                bookFee = (gender == 'male') ? 200.00 : 150.00;
                console.log(bookFee);
                break;
        }

        $('#bookFeeField').val(bookFee.toFixed(2));
        mandatoryFee = totalMandatoryFee(stType, acFee, mealFee, bookFee);
        $('#totalMandatoryFee').html(mandatoryFee.toFixed(2));

        total = totoalTuitionFee(mandatoryFee,tuitionFee,resFee)
        $('#total').html(total.toFixed(2));
    });


    $('input[type=radio][name="boarding"]').change(function () {
        if ($(this).is(":checked")) { // check if the radio is checked
            resFee = parseInt($(this).val()); // retrieve the value
            tuitionFee = 3800;

            $('#residentialFee').val(resFee.toFixed(2));
            $('#tuitnFee').val(tuitionFee.toFixed(2));

            total = totoalTuitionFee(tuitionFee, resFee,mandatoryFee);
            $('#total').html(total.toFixed(2));

            console.log(resFee);
        }
    });

    $('#tuitionDue').hide();
    $('#collectionDue').hide();

    var sponsor = 0;
    var dollarADay = 0;
    var collection = 0;
    var sibling = 0;
    var staff = 0;
    var otp = 0;
    var zakat = 0;
    var grandTotal=0;

    $('#discount').click(function () {

        if($('#sponsor').is(':checked')){
            $('#sponsorDiv').slideDown();
        }else {
            $('#sponsorDiv').slideUp();
        }

        if($('#dollarADay').is(':checked')){
            $('#dollarADayDiv').slideDown();
        }else {
            $('#dollarADayDiv').slideUp();
        }

        if($('#collection').is(':checked')){
            $('#collectionDiv').slideDown();
        }else {
            $('#collectionDiv').slideUp();
        }

        if($('#sibling').is(':checked')){
            $('#siblingDiv').slideDown();
        }else {
            $('#siblingDiv').slideUp();
        }

        if($('#staff').is(':checked')){
            $('#staffDiv').slideDown();
        }else {
            $('#staffDiv').slideUp();
        }

        $('#otp').change(function () {
            var isChcked = $(this).is(':checked');
            if(isChcked){
                $('#otpDiv').slideDown();
                $('#otpRow').show();
                otp = $('#otpDisField').val();
                console.log(otp);
                $('#otpDisField').val(otp);
                var grandTotalFee = total-otp;
                $('#gradnTotalFee').html(grandTotalFee.toFixed(2));
            }
            if(!isChcked){
                $('#otpDiv').slideUp();
                $('#otpRow').hide();
            }
        });

        if($('#zakat').is(':checked')){
            $('#zakatDiv').slideDown();
        }else {
            $('#zakatDiv').slideUp();
        }
    });




    hideDiscounts(['sponsorDiv','dollarADayDiv','collectionDiv','siblingDiv','staffDiv','otpDiv','zakatDiv']);
    var sponsor={};
    $.ajax({
        method: "GET",
        url: '/sponsor/list',
        success: function (data) {
            $("#spNameForAssign").autocomplete({
                source: data,
                select:function (event,ui) {
                    getSponsorByname(ui.item.value);
                }
            });
        },
        error:function () {
            console.log('Could not load sponsor list ');
        }
    });

    // $('#sponsorDiv').hide();
    // $('#sponsorDollarADay').hide();
    $('#otpRow').hide();
    $('#bookFeeDiv').hide();

    $('#stBtn').click(function () {
        getStudentById();
    });

});


function totalMandatoryFee(stType, acFee, mealFee, bookFee) {
    return parseInt(stType) +
        parseInt(acFee) +
        parseInt(mealFee) +
        parseInt(bookFee);
}

function totoalTuitionFee(tuitionFee, residentFee,mandFee) {
    return parseInt(tuitionFee) +
        parseInt(residentFee)+
        parseInt(mandFee);
}
function grandTotal(total,otp) {
    return total-
        parseInt(otp);
}

var ids = [];
function hideDiscounts( ids) {
    for (i = 0;i<ids.length;i++){
        $('#'+ids[i]).hide();
    }
}

function getSponsorByname(name) {
    $.ajax({
        method:'GET',
        url:'/sponsor/'+name,
        success:function (data) {
            $('#tBody').remove();

            $('#spnsrDtlsTbl').append(
                "<tbody id='tBody'><tr></tr><td>" + "Name" + "</td>" + "<td>"+ data.name+"</td></tr>"
                +
                "<tr><td>" + "Email" + "</td>" + "<td>"+ data.email+"</td></tr>"
                +
                "<tr><td>" + "Phone" + "</td>" + "<td>"+ data.phone+"</td></tr>"
                +
                "<tr><td>" + "Address" + "</td>" + "<td>"+ data.address+"</td></tr>"
                +
                "<tr><td>" + "Interval" + "</td>" + "<td>"+ data.donationInterval+"</td></tr>"
                +
                "<tr><td>" + "Ammount" + "</td>" + "<td>"+ data.donationAmount+"</td></tr>"
                +
                "<tr><td>" + "Remaining" + "</td>"+ "<td>"+(data.st_id==null?data.donationAmount:0)+"</td></tr></tbody>"
                +
                "<tr><td>" + "Assigned Student" + "</td>"+ "<td>"+ (data.st_id==null?'Not assigned':data.st_id)+"</td></tr></tbody>"
            );

            console.log(data);
        }
    })
}

function getStudentById() {
    $.ajax({
        method:'GET',
        url:'/student/details/'+1,
        success:function (data) {
            console.log(data);
        },
        error:function () {
            console.log('not success');
        }
    })
}



