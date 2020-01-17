$(document).ready(function () {

    // $('#admissionFeeForm').hide();
    $('#month').val(getMonthName());

    var studentID;
    var afToPay;
    var afPID;
    var afPaymentId;


    $('#studentIdsOnAdmisnFee').change(function () {
        studentID = $('#studentIdsOnAdmisnFee option:selected').val();

        if (studentID == '0') {
            $('#admisnFeeTblBody').html("<tr><td colspan='2' class='text-center'>" + "Admission Fee Statement" + "</td></tr>");
            $('#pdfGeneratorAdmisnFee').hide();
            $('#admissionFeeForm').hide();
            $('#admisnFeeStId').val('');
            $('#admisnFeeToPay').val('');
            $('#admsnFeePaid').val('');
            $('#admisnFeePaidFieldOnStmt').html('');
            $('#admsnFeeDue').val('');


            return;
        } else {

            //This codes for admission fee payment id
            afPaymentId = getAfPaymentId();
            $('#admissionFeePaymentID').val(afPaymentId);
            $('#admCashPID').val(afPaymentId);
            $('#admChequePID').val(afPaymentId);
            $('#admZellePID').val(afPaymentId);
            $('#admCCPID').val(afPaymentId);
            $('#amdMoneyOrderPID').val(afPaymentId);
            $('#admFromSalPID').val(afPaymentId);

            $('#admisnFeeStId').val(studentID);
            $('#pdfGeneratorAdmisnFee').show();
            $('#admissionFeeForm').show();

            afToPay = admissionFeeStmt(studentID, afPaymentId);

            console.log(afToPay);
        }
    });

    // $('#admsnFeeDue').keyup(function () {
    //     admisnFeeDue = $('#admsnFeeDue').val();
    //     if (isNaN(admisnFeeDue)) {
    //         return '';
    //     }
    //     $('#admisnFeeDue').html(admisnFeeDue);
    // });


    $('#admisnFee').click(function () {
        var admsnFeePaid = parseInt($('#admsnFeePaid').val());


        $('#afPayStatus').html('');
        if (studentID == undefined || studentID == 0) {
            $('#afPayStatus').append('<p class="text-danger">Select Student ID</p>');
            return "";
        }

        if (isNaN(admsnFeePaid)) {
            $('#afPayStatus').append('<p class="text-danger">Enter admission fee paid</p>');
            return "";
        }

        var fin_id = document.getElementById('fin_dtl_id').innerText;

        // insertingAdmissionFeeDue(admisnFeeDue, fin_id);

        var admissionFee = JSON.stringify($('#admFeeForm').serializeJSON());
        console.log(fin_id);
        console.log(admissionFee);
        $.ajax({
            method: 'post',
            url: '/admFee/save',
            data: admissionFee,
            contentType: "application/json",
            success: function () {
                var id = $('#st_id').html();
                admitStudent(id);
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });

    $('#admCashBtn').click(function () {
        var cash = JSON.stringify($('#cashForm').serializeJSON());
        $('#afCashSavingStatus').html('');

        if (JSON.parse(cash).paymentId == '') {
            $('#afCashSavingStatus').append('<span class="text-danger">Select a student</span>');
            return '';
        }

        if (JSON.parse(cash).amount <= 0 || JSON.parse(cash).amount == '') {
            $('#afCashSavingStatus').append('<span class="text-danger">Enter valid amount</span>');
            return '';
        }

        $.ajax({
            method: 'post',
            url: '/cash/save',
            data: cash,
            contentType: "application/json",
            success: function () {
                calculateAF(afPID, afToPay);
                $('#afCashSavingStatus').append('<span class="text-success">Success</span>');
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });


    $('#fromSalForm').submit(function (event) {
        event.preventDefault();
        var cheque = JSON.stringify($('#fromSalForm').serializeJSON());
        console.log(cheque);
        $('#fromSalChequeSavingStatus').html('');

        if (JSON.parse(cheque).paymentId == '') {
            $('#fromSalChequeSavingStatus').append('<span class="text-danger">Select a student</span>');
            return '';
        }

        if (JSON.parse(cheque).chequeNum == '' ||
            JSON.parse(cheque).amount == '' ||
            JSON.parse(cheque).payPeriod == '') {
            $('#fromSalChequeSavingStatus').append('<span class="text-danger">Fill out all the fields</span>');
            return '';
        }

        if ($('#fromSalChequeImg').val() == '') {
            $('#fromSalChequeSavingStatus').append('<span class="text-danger">Select image</span>');
            return '';
        }

        var frm = $('#fromSalForm')[0];
        $.ajax({
            method: 'post',
            url: '/fromSal/save',
            data: new FormData(frm),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function () {
                calculateAF(afPID, afToPay);
                $('#fromSalChequeSavingStatus').append('<span class="text-success">Success</span>');
                return false;
            },

            error: function () {
                console.log('not success');
            }
        })
    });

    $('#chequeForm').submit(function (event) {
        event.preventDefault();
        var cheque = JSON.stringify($('#chequeForm').serializeJSON());
        console.log(cheque);
        $('#mfChequeSavingStatus').html('');

        if (JSON.parse(cheque).paymentId == '') {
            $('#mfChequeSavingStatus').append('<span class="text-danger">Select a student</span>');
            return '';
        }

        if (JSON.parse(cheque).accountNum == '' ||
            JSON.parse(cheque).chequeNum == '' ||
            JSON.parse(cheque).chequeDate == '') {
            $('#mfChequeSavingStatus').append('<span class="text-danger">Fill out all the fields</span>');
            return '';
        }

        if ($('#chequeImg').val() == '') {
            $('#mfChequeSavingStatus').append('<span class="text-danger">Select image</span>');
            return '';
        }

        $.ajax({
            method: 'post',
            url: '/cheque/save',
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
                calculateAF(afPID, afToPay);
                $('#mfChequeSavingStatus').append('<span class="text-success">Success</span>');
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });


    $('#moneyOrderForm').submit(function (event) {
        event.preventDefault();
        var mo = JSON.stringify($('#moneyOrderForm').serializeJSON());
        console.log(mo);
        $('#afMOSavingStatus').html('');

        if (JSON.parse(mo).paymentId == '') {
            $('#afMOSavingStatus').append('<span class="text-danger">Select a student</span>');
            return '';
        }

        if (JSON.parse(mo).moneyOrderDate == '' ||
            JSON.parse(mo).moneyOrderNum == '' ||
            isNaN(JSON.parse(mo).amount)) {
            $('#afMOSavingStatus').append('<span class="text-danger">Enter valid data</span>');
            return '';
        }

        if ($('#moImage').val() == '') {
            $('#afMOSavingStatus').append('<span class="text-danger">Select image</span>');
            return '';
        }
        $.ajax({
            method: 'post',
            url: '/mo/save',
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function () {
                calculateAF(afPID, afToPay);
                $('#afMOSavingStatus').append('<span class="text-success">Success</span>');
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });


    $('#admZellBtn').click(function () {
        var zelle = JSON.stringify($('#zelleForm').serializeJSON());
        console.log(zelle);
        $('#afZelleSavingStatus').html('');

        if (JSON.parse(zelle).paymentId == '') {
            $('#afZelleSavingStatus').append('<span class="text-danger">Select a student</span>');
            return '';
        }

        if (JSON.parse(zelle).amount == '' || JSON.parse(zelle).amount <= 0) {
            $('#afZelleSavingStatus').append('<span class="text-danger">Enter amount</span>');
            return '';
        }

        if (JSON.parse(zelle).phoneNum == '' && JSON.parse(zelle).email == 0) {
            $('#afZelleSavingStatus').append('<span class="text-danger">Enter email or phone</span>');
            return '';
        }

        $.ajax({
            method: 'post',
            url: '/zelle/save',
            data: zelle,
            contentType: "application/json",
            success: function () {
                calculateAF(afPID, afToPay);
                $('#afZelleSavingStatus').append('<span class="text-success">Success</span>');
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });

    $('#admCCBtn').click(function () {

        var cc = JSON.stringify($('#ccForm').serializeJSON());
        $('#afCCSavingStatus').html('');

        if (JSON.parse(cc).paymentId == '') {
            $('#afCCSavingStatus').append('<span class="text-danger">Select a student</span>');
            return '';
        }

        if (JSON.parse(cc).amount == '' || JSON.parse(cc).amount <= 0) {
            $('#afCCSavingStatus').append('<span class="text-danger">Enter amount</span>');
            return '';
        }

        if (JSON.parse(cc).amount <= 0 ||
            isNaN(JSON.parse(cc).amount) ||
            JSON.parse(cc).tnxId == '' ||
            JSON.parse(cc).cardNum == '') {
            $('#afCCSavingStatus').append('<span class="text-danger">Enter valid data</span>');
            return '';
        }

        $.ajax({
            method: 'post',
            url: '/cc/save',
            data: cc,
            contentType: "application/json",
            success: function () {
                calculateAF(afPID, afToPay);
                $('#afCCSavingStatus').append('<span class="text-success">Success</span>');
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });

    $('body').on('click', '#mealPlan', function () {
        $('#mealPlanFeeRow').fadeOut(300);
        calculateAF(afPaymentId,(afToPay-100));
    });

    $('body').on('click', '#academicFee', function () {
        $('#academicFeeRow').fadeOut(300);
        console.log($('#academicFeeRow').is(':visible'));
    });

    $('body').on('click', '#bookFee', function () {
        $('#bookFeeRow').fadeOut(300);
        console.log($('#bookFeeRow').is(':visible'));
    });


    $('#pdfGeneratorAdmisnFee').click(function () {
        makePdf();
    });
});

function admissionFeeStmt(st_id, afPaymentId) {
    var afToPay;
    $.ajax({
        method: 'GET',
        url: '/student/json/' + parseInt(st_id),
        async: false,
        success: function (data) {
            data = $.parseJSON(data);
            afToPay = (getTotalCommonMandatoryFee(data[0].status, data[0].courseName) + getBookFee(data[0].year, data[0].gender));

            //$('#monthlyFeeTblBody').html('');

            //==========ends here===========

            $('#admisnFeeTblBody').html('');
            $('#admisnFeeTblBody').append(
                "<tr><td colspan='2' class='text-center'>" + "Admission Fee Statement" + "</td></tr>"
                +
                "<tr style='display: none'><td>" + "Fin Details ID" + "</td>" + "<td id='fin_dtl_id' >" + data[0].finDtlsOfStudent.id + "</td></tr>"
                +
                "<tr style='display: none'><td>" + "Student ID" + "</td>" + "<td id='st_id' >" + data[0].id + "</td></tr>"
                +
                "<tr style='display: none'><td>" + "Sponsor ID" + "</td>" + "<td id='sp_id_at_fin_report' >" + data[0].finDtlsOfStudent.sp_id + "</td></tr>"
                +
                "<tr><td>" + "Student ID" + "</td>" + "<td>" + data[0].studentId + "</td></tr>"
                +
                "<tr><td>" + "Student Name" + "</td>" + "<td>" + data[0].name + "</td></tr>"
                +
                "<tr><td>" + "Father  Name" + "</td>" + "<td>" + data[0].fatherName + "</td></tr>"
                +
                "<tr><td>" + "Month" + "</td>" + "<td id='motnhName'>" + getMonthName() + "</td></tr>"
                +
                "<tr><td>" + "Payment ID" + "</td>" + "<td id='paymentIDonStmt'>" + getAfPaymentId() + "</td></tr>"
            );

            $('#admisnFeeTblBody').append(
                "<tr ><td>" + "Admission Fee" + "</td>" + "<td >" + (data[0].status == 'admitted' ? 100 : 200) + "</td></tr>"
            );

            $('#admisnFeeTblBody').append(
                "<tr id='mealPlanFeeRow'><td>" + "Meal Plan" + "</td>" + "<td ><span>" + 100.0 + "</span><button id='mealPlan' class='btn btn-danger waves-effect' style='float:right;height: 18px;padding: 0px 5px'>X</button></td></tr>"
            );

            $('#admisnFeeTblBody').append(
                "<tr id='academicFeeRow'><td>" + "Academic Fee" + "</td>" + "<td ><span>" + getAcademicFee(data[0].courseName) + "</span><button id='academicFee' class='btn btn-danger waves-effect' style='float:right;height: 18px;padding: 0px 5px'>X</button></td></tr>"
            );

            if (data[0].year != null) {
                $('#admisnFeeTblBody').append(
                    "<tr id='bookFeeRow'><td>" + "Book Fee" + "</td>" + "<td ><span>" + getBookFee(data[0].year, data[0].gender.toLowerCase()) + "</span><button id='bookFee' class='btn btn-danger waves-effect' style='float:right;height: 18px;padding: 0px 5px'>X</button></td></tr>"
                );
            }

            $('#admisnFeeTblBody').append(
                "<tr><td>" + "Total Common Mandatory Fee" + "</td>" + "<td id='afToPayOnStmt'>"+afToPay+"</td></tr>"
                +
                "<tr><td>" + "Admission Fee Paid" + "</td>" + "<td id='admisnFeePaidFieldOnStmt'></td></tr>"
                +
                "<tr><td>" + "Admission Fee Due" + "</td>" + "<td id='admisnFeeDue'></td></tr>"
            );
            calculateAF(afPaymentId, afToPay);

        },
        error: function () {
            console.log('not success');
        }
    });

    return afToPay;
}

//=====================end================

function getAcademicFee(course) {

    if (course == 'Academy') {
        return 240;
    } else if (course == "Alim") {
        return 755;
    } else if (course == "Alima") {
        return 240;
    } else if (course == "Boy_Nazira") {
        return 755;
    } else if (course == "Banaat_Nazira") {
        return 240;
    } else if (course == "Boy_Hifz") {
        return 755;
    } else if (course == "Banaat_Hifz") {
        return 240;
    } else if (course == "Standardized_Test") {
        return 50;
    } else if (course == "na") {
        return 0.0;
    }
}

function getTotalCommonMandatoryFee(status, course) {
    return (status == 'admitted' ? 100 : 200) + getAcademicFee(course) + 100;
}

function getBookFee(year, gender) {
    var bookFee = 0;
    switch (year) {
        case 'Oola':
            bookFee = 80;
            console.log(bookFee);
            break;
        case 'Thania' :
            bookFee = (gender.toLowerCase() == 'male') ? 75.00 : 70.00;
            console.log(bookFee);
            break;
        case 'Thalitha' :
            bookFee = (gender.toLowerCase() == 'male') ? 80.00 : 70.00;
            console.log(bookFee);
            break;
        case 'Rabiya' :
            bookFee = (gender.toLowerCase() == 'male') ? 60.00 : 75.00;
            console.log(bookFee);
            break;
        case 'Khamisa' :
            bookFee = (gender.toLowerCase() == 'male') ? 80.00 : 90.00;
            console.log(bookFee);
            break
        case 'Saadisa' :
            bookFee = (gender.toLowerCase() == 'male') ? 100.00 : 95.00;
            console.log(bookFee);
            break
        case 'Darua' :
            bookFee = (gender.toLowerCase() == 'male') ? 200.00 : 150.00;
            console.log(bookFee);
            break;
    }

    return bookFee;
}

function getAfPaymentId() {
    var paymentID = null;
    $.ajax({
        method: 'GET',
        url: '/admissionFee/getPaymentId',
        async: false,
        success: function (data) {
            paymentID = data;
        },
        error: function () {
            console.log('not success');
        }
    });

    return paymentID;
}


function insertingAdmissionFeeDue(afDue, fin_id) {

    $.ajax({
        method: 'GET',
        url: '/findetails/mandFeesDue/' + parseInt(afDue) + '/' + parseInt(fin_id),
        success: function () {
            console.log('success');
        },
        error: function () {
            console.log('not success');
        }
    })

}

function getAFPaid(pid) {
    var amount = 0;
    $.ajax({
        method: 'GET',
        url: '/fee/' + pid,
        async: false,
        success: function (data) {
            amount = data;
        },
        error: function () {
            console.log('not success');
        }
    })
    return amount;
}

function calculateAF(afPaymentId,afToPay) {
    var feePaid = getAFPaid(afPaymentId);

    $('#admisnFeeToPay').val(afToPay);
    $('#admsnFeePaid').val(feePaid);
    $('#admsnFeeDue').val(afToPay - feePaid);
    $('#admisnFeeDue').html(afToPay - feePaid);
    $('#admisnFeePaidFieldOnStmt').html(feePaid);
}

function updateAF(afToPay) {
    //var feePaid = getAFPaid(afPaymentId);

    $('#admisnFeeToPay').val(afToPay);
    // $('#admsnFeePaid').val(feePaid);
    // $('#admsnFeeDue').val(afToPay - feePaid);
    // $('#admisnFeeDue').html(afToPay - feePaid);
    // $('#admisnFeePaidFieldOnStmt').html(feePaid);
}

function admitStudent(id) {
    $.ajax({
        method: 'GET',
        url: '/student/admit/' + parseInt(id),
        success: function () {
            $('#paymentPlanUpdateSts').html('<span class="text-success">"Successful"</span>');
        },
        error: function () {
            $('#paymentPlanUpdateSts').html('<span class="text-success">"Not Successful"</span>');
        }
    });
}


function getMonthName() {
    var d = new Date();
    var month = new Array();
    month[0] = "January";
    month[1] = "February";
    month[2] = "March";
    month[3] = "April";
    month[4] = "May";
    month[5] = "June";
    month[6] = "July";
    month[7] = "August";
    month[8] = "September";
    month[9] = "October";
    month[10] = "November";
    month[11] = "December";
    return month[d.getMonth()];
}

function makePdf() {
    $('#admisnFeeTbl').printThis({
        debug: false,               // show the iframe for debugging
        importCSS: true,            // import parent page css
        importStyle: false,         // import style tags
        printContainer: true,       // print outer container/$.selector
        loadCSS: "http://localhost:8082/css/w3.css",                // path to additional css file - use an array [] for multiple
        pageTitle: "",              // add title to print page
        removeInline: false,        // remove inline styles from print elements
        removeInlineSelector: "*",  // custom selectors to filter inline styles. removeInline must be true
        printDelay: 333,            // variable print delay
        header: null,               // prefix to html
        footer: null,               // postfix to html
        base: false,                // preserve the BASE tag or accept a string for the URL
        formValues: true,           // preserve input/form values
        canvas: false,              // copy canvas content
        doctypeString: '...',       // enter a different doctype for older markup
        removeScripts: false,       // remove script tags from print content
        copyTagClasses: false,      // copy classes from the html & body tag
        beforePrintEvent: null,     // function for printEvent in iframe
        beforePrint: null,          // function called before iframe is filled
        afterPrint: null            // function called before iframe is removed
    });

    // console.log('pdf');
    // var doc = new jsPDF();
    //
    // doc.fromHTML($('#finDtlsTbl'),15,15,{
    //     "width":170,
    //     "elementHandlers":specialElementHandlers
    // });
    // doc.output('dataurlnewwindow');
    // doc.save("fee-statement.pdf");

}






















