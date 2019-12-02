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
    var total = 3900;

    $('#residentialFee').val(0.00);

    mealFee = $('#mealFee').val();

    mandatoryFee = totalMandatoryFee(stType, acFee, mealFee, bookFee);
    $('#totalMandatoryFee').html(mandatoryFee.toFixed(2));

    $('#total').html((totalMandatoryFee(stType, acFee, mealFee, bookFee) + tuitionFee).toFixed(2));


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

            total = totoalTuitionFee(mandatoryFee, tuitionFee, resFee)
            $('#total').html(total.toFixed(2));

            console.log(stType);
        }

    });


    $('#course').change(function () {
        acFee = parseInt($('#course option:selected').val());

        var course = $('#course option:selected').val();

        if (course == 'Alim' || course == 'Alima' || course == 'na') {
            $('#bookFeeDiv').show();

            acFee = (course == 'Alim') ? 755 : 240;
            if (course == 'na') {
                acFee = 0;
            }
        } else {
            $('#bookFeeDiv').hide();
        }
        if (course == 'Select a course') {
            acFee = 0;
        }
        $('#courseFee').val(acFee.toFixed(2));
        mandatoryFee = totalMandatoryFee(stType, acFee, mealFee, bookFee);
        $('#totalMandatoryFee').html(mandatoryFee.toFixed(2));

        total = totoalTuitionFee(mandatoryFee, tuitionFee, resFee)
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

        total = totoalTuitionFee(mandatoryFee, tuitionFee, resFee)
        $('#total').html(total.toFixed(2));
    });


    $('input[type=radio][name="boarding"]').change(function () {
        if ($(this).is(":checked")) { // check if the radio is checked
            resFee = parseInt($(this).val()); // retrieve the value
            tuitionFee = 3800;

            $('#residentialFee').val(resFee.toFixed(2));
            $('#tuitnFee').val(tuitionFee.toFixed(2));

            total = totoalTuitionFee(tuitionFee, resFee, mandatoryFee);
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
    var grandTotalFee = 0;

    $('#discount').click(function () {

        if ($('#sponsor').is(':checked')) {
            $('#sponsorDiv').slideDown();
        } else {
            $('#sponsorDiv').slideUp();
        }

        if ($('#dollarADay').is(':checked')) {
            $('#dollarADayDiv').slideDown();
        } else {
            $('#dollarADayDiv').slideUp();
        }

        if ($('#collection').is(':checked')) {
            $('#collectionDiv').slideDown();
        } else {
            $('#collectionDiv').slideUp();
        }

        if ($('#sibling').is(':checked')) {
            $('#siblingDiv').slideDown();
        } else {
            $('#siblingDiv').slideUp();
        }

        $('#staff').change(function () {
            var isChcked = $(this).is(':checked');
            if (isChcked) {
                $('#staffFeeRow').show();
                staff = $('#staffFeeAmt').val();
                console.log(otp);
                $('#staffFeeAmt').val(staff);
                grandTotalFee = total - staff;
                $('#gradnTotalFee').html(grandTotalFee.toFixed(2));
            }
            if (!isChcked) {
                var grandTotalFee = total;
                staff = 0;
                $('#gradnTotalFee').html(grandTotalFee.toFixed(2));
                $('#staffFeeRow').hide();
            }
        });


        $('#otp').change(function () {
            var isChcked = $(this).is(':checked');
            if (isChcked) {

                $('#otpRow').show();
                otp = $('#otpDisField').val();
                console.log(otp);
                $('#otpDisField').val(otp);

                grandTotalFee = grandTotal(total, sponsor, dollarADay, collection, sibling, staff, otp, zakat);
                $('#grandTotalFee').html(grandTotalFee.toFixed(2));
            }
            if (!isChcked) {
                var grandTotalFee = total;
                otp = 0;
                $('#grandTotalFee').html(grandTotalFee.toFixed(2));
                $('#otpRow').hide();

            }
        });

        if ($('#zakat').is(':checked')) {
            $('#zakatDiv').slideDown();
        } else {
            $('#zakatDiv').slideUp();
        }

        if ($('#selfFunded').is(':checked')) {
            $('#otp').attr('disabled', false);
            $('#sponsor').attr('disabled', true);
            $('#dollarADay').attr('disabled', true);
            $('#collection').attr('disabled', true);
            $('#sibling').attr('disabled', true);
            $('#staff').attr('disabled', true);
            $('#zakat').attr('disabled', true);

        } else {
            $('#otp').prop('checked', false);

            otp = 0;
            $('#grandTotalFee').html(total.toFixed(2));
            $('#otpDiv').slideUp();
            $('#otpRow').hide();

            $('#otp').attr('disabled', true);

            $('#sponsor').attr('disabled', false);
            $('#dollarADay').attr('disabled', false);
            $('#collection').attr('disabled', false);
            $('#sibling').attr('disabled', false);
            $('#staff').attr('disabled', false);
            $('#zakat').attr('disabled', false);
        }
    });


    hideDiscounts(['sponsorDiv', 'dollarADayDiv', 'collectionDiv', 'siblingDiv', 'staffDiv', 'otpDiv', 'zakatDiv']);

    //Load information for students
    //var st_id = document.getElementById('st_id').innerHTML;
    //getSponsorOfStudent(st_id);
    //=========end===========


    // $('#sponsorDiv').hide();
    // $('#sponsorDollarADay').hide();
    $('#otpRow').hide();
    $('#staffFeeRow').hide();
    $('#bookFeeDiv').hide();
    $('#sponsorRow').hide();
    $('#daddRow').hide();
    $('#collRow').hide();
    $('#sibRow').hide();
    $('#zakatFeeRow').hide();

    $('#stBtn').click(function () {
        getStudentById();
    });


    $.ajax({
        method: "GET",
        url: '/sponsor/list',
        success: function (data) {
            $("#spNameForAssign").autocomplete({
                source: data,
                select: function (event, ui) {
                    getSponsorByname(ui.item.value);
                }
            });
        },
        error: function () {
            console.log('Could not load sponsor list ');
        }
    });

    $.ajax({
        method: "GET",
        url: '/dadd/list',
        success: function (data) {
            $("#daddNames").autocomplete({
                source: data,
                select: function (event, ui) {
                    getDaddByname(ui.item.value);
                }
            });
        },
        error: function () {
            console.log('Could not load sponsor list ');
        }
    });


    //sponsor assigning

    $('#assignSp').click(function () {

        var sp_id = document.getElementById('sp_id').innerHTML;
        var st_id = document.getElementById('st_id').innerHTML;
        var fin_dtl_Id_of_sp = document.getElementById('fin_dtl_id').innerHTML;
        var dadd_id = 0;

        if (sp_id > 0 && fin_dtl_Id_of_sp > 0) {
            assignSp(fin_dtl_Id_of_sp, sp_id, st_id, dadd_id);
        }

        sponsor = applySponsorDiscount();
        console.log(sponsor);
        grandTotalFee = grandTotal(total, sponsor, dollarADay, collection, sibling, staff, otp, zakat);
        console.log(grandTotalFee);

        //$('#grandTotalFee').html(grandTotalFee.toFixed(2));

    });

    //dadd assigning

    $('#assignDadd').click(function () {

        var sp_id = 0;
        var st_id = document.getElementById('st_id').innerHTML;
        var fin_dtl_Id_of_sp = document.getElementById('fin_dtl_id').innerHTML;
        var dadd_id = document.getElementById('dadd_id').innerHTML;

        if (dadd_id > 0 && fin_dtl_Id_of_sp > 0) {
            assignDadd(fin_dtl_Id_of_sp, sp_id, st_id, dadd_id);
        }

        applyDaddDiscount();
    });

    //collection discount

    $('#addColl').click(function () {
        applyCollDiscount();
    });

    //Adding siblings

    $('#addSibling').click(function () {
        applySiblingDiscount();
    });

    //Adding zakar

    $('#addZakat').click(function () {
        applyZakatDiscount();
    });

    studentFeeReport(1);

});


function applySponsorDiscount() {

    var interval = document.getElementById('interval').innerText;
    var donationAmnt = document.getElementById('donationAmnt').innerText;
    var sponsor = 0;
    if (interval == 'Weekly') {
        sponsor = parseInt(donationAmnt) * 4 * 10;
    } else if (interval == 'Monthly') {
        sponsor = parseInt(donationAmnt) * 10;
    } else if (interval == 'Annually') {
        sponsor = parseInt(donationAmnt);
    }

    $('#spAmnt').val(sponsor);
    $('#sponsorRow').show();
    return sponsor;
}

function applyDaddDiscount() {

    var donationAmnt = document.getElementById('donationAmnt').innerText;
    dollarADay = parseInt(donationAmnt) * 360;

    $('#daddAmnt').val(dollarADay);
    $('#daddRow').show();

}

function applyCollDiscount() {

    var donationAmnt = $('#collectionAmt').val();
    collection = parseInt(donationAmnt);

    $('#collAmt').val(collection);
    $('#collRow').show();

}


function applySiblingDiscount() {

    var val = [];
    var i = 0;
    $("#siblingsIds option:selected").each(function () {
        val.push(this.text);
        i++;
    });

    var sibDiscount;

    if (i == 1) {
        sibDiscount = 600;
    } else if (i == 2) {
        sibDiscount = 800;
    } else if (i == 3) {
        sibDiscount = 1000;
    }

    $('#sibDisAmnt').val(sibDiscount);
    $('#sibRow').show();

}


function applyZakatDiscount() {

    zakatAmnt = parseInt($('#zakatAmount').val());
    console.log(zakatAmnt);
    $('#zakatAmnt').val(zakatAmnt);
    $('#zakatFeeRow').show();

}


function totalMandatoryFee(stType, acFee, mealFee, bookFee) {
    return parseInt(stType) +
        parseInt(acFee) +
        parseInt(mealFee) +
        parseInt(bookFee);
}

function totoalTuitionFee(tuitionFee, residentFee, mandFee) {
    return parseInt(tuitionFee) +
        parseInt(residentFee) +
        parseInt(mandFee);
}

function grandTotal(total, sponsor, dollarADay, collection, sibling, staff, otp, zakat) {
    if (parseInt(otp) > 0) {
        sponsor = 0;
        dollarADay = 0;
        collection = 0;
        sibling = 0;
        staff = 0;
        zakat = 0;
    }
    return (total - parseInt(sponsor) - parseInt(dollarADay) - parseInt(collection) - parseInt(sibling) - parseInt(staff) - parseInt(zakat) - parseInt(otp));
}

var ids = [];

function hideDiscounts(ids) {
    for (i = 0; i < ids.length; i++) {
        $('#' + ids[i]).hide();
    }
}

function getSponsorByname(name) {
    $.ajax({
        method: 'GET',
        url: '/sponsor/' + name,
        success: function (data) {
            $('#daddTBody').remove();

            $('#spnsrDtlsTbl').append(
                "<tbody id='daddTBody'><tr><td>" + "Sponsor ID" + "</td>" + "<td id='sp_id'>" + data.id + "</td></tr>"
                +
                "<tr><td>" + "Name" + "</td>" + "<td>" + data.name + "</td></tr>"
                +
                "<tr><td>" + "Email" + "</td>" + "<td>" + data.email + "</td></tr>"
                +
                "<tr><td>" + "Phone" + "</td>" + "<td>" + data.phone + "</td></tr>"
                +
                "<tr><td>" + "Address" + "</td>" + "<td>" + data.address + "</td></tr>"
                +
                "<tr><td>" + "Interval" + "</td>" + "<td id='interval'>" + data.donationInterval + "</td></tr>"
                +
                "<tr><td>" + "Ammount" + "</td>" + "<td id='donationAmnt'>" + data.donationAmount + "</td></tr>"
                +
                "<tr><td>" + "Remaining" + "</td>" + "<td>" + (data.st_id == 0 ? data.donationAmount : 0) + "</td></tr>"
                +
                "<tr><td>" + "Assigned Student" + "</td>" + "<td>" + (data.st_id == 0 ? 'Not assigned' : data.st_id) + "</td></tr></tbody>"
            );

            if (data.st_id > 0) {
                $('#spAssignStatus').html("");
                $('#spAssignStatus').html("This sponsor is assigned already");
                $('#assignSp').attr('disabled', true);

            } else {
                $('#spAssignStatus').html("");
                $('#assignSp').attr('disabled', false);
            }
        }
    })
}

function getDaddByname(name) {
    $.ajax({
        method: 'GET',
        url: '/dadd/' + name,
        success: function (data) {
            $('#daddTBody').remove();

            $('#ddaddDtlsTbl').append(
                "<tbody id='daddTBody'><tr><td>" + "Sponsor ID" + "</td>" + "<td id='dadd_id'>" + data.id + "</td></tr>"
                +
                "<tr><td>" + "Name" + "</td>" + "<td>" + data.name + "</td></tr>"
                +
                "<tr><td>" + "Email" + "</td>" + "<td>" + data.email + "</td></tr>"
                +
                "<tr><td>" + "Phone" + "</td>" + "<td>" + data.phone + "</td></tr>"
                +
                "<tr><td>" + "Address" + "</td>" + "<td>" + data.address + "</td></tr>"
                +
                "<tr><td>" + "Ammount" + "</td>" + "<td id='donationAmnt'>" + data.donationAmount + "</td></tr>"
                +
                "<tr><td>" + "Remaining" + "</td>" + "<td>" + (data.st_id == 0 ? data.donationAmount : 0) + "</td></tr>"
                +
                "<tr><td>" + "Assigned Student" + "</td>" + "<td>" + (data.st_id == 0 ? 'Not assigned' : data.st_id) + "</td></tr></tbody>"
            );

            if (data.st_id > 0) {
                $('#daddAssignStatus').html("");
                $('#daddAssignStatus').html("This sponsor is assigned already");
                $('#assignDadd').attr('disabled', true);

            } else {
                $('#daddAssignStatus').html("");
                $('#assignDadd').attr('disabled', false);
            }
        }
    })
}

function getStudentById() {
    $.ajax({
        method: 'GET',
        url: '/student/details/' + 1,
        success: function (data) {
            console.log(data);
        },
        error: function () {
            console.log('not success');
        }
    })
}

function assignSp(fin_dtl_id_of_st, sp_id, st_id, dadd_id) {

    $.ajax({
        method: 'POST',
        url: '/fee/spAssing/',
        data: "{fin_dtl_id:" + fin_dtl_id_of_st + ",sp_id:" + sp_id + ",st_id:" + st_id + ",dadd_id:" + dadd_id + "}",
        contentType: 'application/json',
        success: function () {
            console.log('success');
        },
        error: function () {
            console.log('not success');
        }
    })
}

function assignDadd(fin_dtl_id_of_st, sp_id, st_id, dadd_id) {

    $.ajax({
        method: 'POST',
        url: '/fee/daddAssign',
        data: "{fin_dtl_id:" + fin_dtl_id_of_st + ",sp_id:" + sp_id + ",st_id:" + st_id + ",dadd_id:" + dadd_id + "}",
        contentType: 'application/json',
        success: function () {
            console.log('success');
        },
        error: function () {
            console.log('not success');
        }
    })

}


function studentFeeReport(st_id) {
    $.ajax({
        method: 'GET',
        url: '/student/json/' + 1,
        success: function (data) {
            data = $.parseJSON(data);

            $('#studentDetails').append(
                "<tr style='display: none'><td>" + "Student ID" + "</td>" + "<td id='fin_dtl_id' >" + data.finDtlsOfStudent.id + "</td></tr>"
                +
                "<tr style='display: none'><td>" + "Student ID" + "</td>" + "<td id='st_id' >" + data.id + "</td></tr>"
                +
                "<tr><td>" + "Student ID" + "</td>" + "<td>" + data.studentId + "</td></tr>"
                +
                "<tr><td>" + "Student Name" + "</td>" + "<td>" + data.name + "</td></tr>"
                +
                "<tr><td>" + "Father  Name" + "</td>" + "<td>" + data.fatherName + "</td></tr>"
                +
                "<tr><td>" + "Mother Name" + "</td>" + "<td>" + data.motherName + "</td></tr>"
                +
                "<tr><td>" + "Date of Birth" + "</td>" + "<td>" + data.dob + "</td></tr>"
            );

            if (data.finDtlsOfStudent.sp_id > 0) {
                reportForSponsor(data.id,data.finDtlsOfStudent.id);
            }

            console.log(data);

        },
        error: function () {
            console.log('not success');
        }
    })
}

//This function adds sponsor's details of the student on finalcial details table
function reportForSponsor(st_id,fin_dtls_id) {
    $.ajax({
        method: 'GET',
        url: '/sponsor/student/' + st_id,
        success: function (data) {
            var sponsor = 0;
            var interval = data.donationInterval;
            console.log(data);
            if (interval == 'Weekly') {
                sponsor = parseInt(data.donationAmount) * 4 * 10;
            } else if (interval == 'Monthly') {
                sponsor = parseInt(data.donationAmount) * 10;
            } else if (interval == 'Annually') {
                sponsor = parseInt(data.donationAmount);
            }
            $('#finDtlsTbl').append(

                "<tr><td>" + "Sponsor Name" + "</td>" + "<td>" + data.name + "</td></tr>"
                +
                "<tr><td>" + "Sponsor\'s Amount" + "</td>" + "<td>" + sponsor + " /year</td></tr>"

            );
            $('#removeSpDiv').append(
                '<p> Sponsor Name: '+data.name+'</p>'
                +
                '<button id="removeSp" type="button" class="btn btn-primary"> Remove</button>'
            );

            $('#removeSp').click(function () {
                removingSponsor(fin_dtls_id,data.id);
                $('#spAssignStatus').html('Sponsor Removed')
            });
        },
        error: function () {
            console.log('not success');
        }
    })
}

//=====================end================

//This method for removing sponsor

function removingSponsor(fin_id, sp_id) {
    console.log(fin_id);
    console.log(sp_id);

    $.ajax({
        method: 'GET',
        url: '/sponsor/remover/' + parseInt(fin_id) + '/' + parseInt(sp_id),
        success: function () {
            console.log('success');
        },
        error: function () {
            console.log('not success');
        }
    })

}








