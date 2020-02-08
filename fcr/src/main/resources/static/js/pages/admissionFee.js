$(document).ready(function () {

    var admissionYear = (new Date().getFullYear()).toString();
    $('#year').val(admissionYear);

    var studentID;
    var afToPay;
    var afPaymentId;
    var student;
    var paymentDBId = 0;

    $('#studentIdsOnAdmisnFee').change(function () {
        studentID = $('#studentIdsOnAdmisnFee option:selected').val();
        paymentDBId = 0;

        if (studentID == '0') {
            $('#admisnFeeTblBody').html("<tr><td colspan='2' class='text-center'>" + "Admission Fee Statement" + "</td></tr>");
            $('#pdfGeneratorAdmisnFee').hide();
            $('#admissionFeeForm').hide();
            $('#admisnFeeStId').val('');
            $('#admisnFeeToPay').val('');
            $('#admsnFeePaid').val('');
            $('#admisnFeePaidFieldOnStmt').html('');
            $('#admsnFeeDue').val('');
            $('#paymentIDonStmt').html('');
            $('#yearName').html('');
            $('#afPayStatus').append('');
            return;
        } else {
            //This codes for admission fee payment id
            afPaymentId = getAfPaymentId();
            student = admissionFeeStmt(studentID, afPaymentId);
            $('#yearName').html(admissionYear);
            console.log(admissionYear);
            var af = getAFPayment(studentID,admissionYear);

            afToPay = getTotalCommonMandatoryFee(student);
            console.log(af.year);
            console.log(af);

            if (af != undefined && af.year == admissionYear.toString()) {
                afPaymentId = af.afPaymentId;
                paymentDBId = af.id;
                console.log('hi');
            }

            calculateAF(studentID,afToPay,admissionYear);

            $('#admissionFeePaymentID').val(afPaymentId);
            $('#admCashPID').val(afPaymentId);
            $('#admChequePID').val(afPaymentId);
            $('#admZellePID').val(afPaymentId);
            $('#admCCPID').val(afPaymentId);
            $('#amdMoneyOrderPID').val(afPaymentId);
            $('#admFromSalPID').val(afPaymentId);

            $('#admCashStID').val(studentID);
            $('#admChequeStID').val(studentID);
            $('#admFromSalStID').val(studentID);
            $('#amdMOStID').val(studentID);
            $('#admCCStID').val(studentID);
            $('#admZelleStPID').val(studentID);

            $('#paymentIDonStmt').html(afPaymentId);
            $('#admisnFeeStId').val(studentID);
            $('#pdfGeneratorAdmisnFee').show();
            $('#admissionFeeForm').show();
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

        if(paymentDBId>0){
            var adm = admissionFee.replace("}","");
            var str = ',"id":"'+paymentDBId+'"}';
            var adm2 = adm.concat(str);
            admissionFee = adm2;
        }

        console.log(admissionFee);

        $.ajax({
            method: 'post',
            url: '/admFee/save',
            data: admissionFee,
            contentType: "application/json",
            success: function () {
                var id = $('#st_id').html();
                if (student.status == 'applied' && parseInt($('#admsnFeeDue').val()) == 0) {
                    admitStudent(id);
                }
                saveAdmissionFeeDetails(student);
                $('#afPayStatus').append('');
                $('#afPayStatus').append('<span class="text-success">Payment Saved</span>');
                paymentDBId = getAFPayment(studentID,admissionYear).id;
                return false;
            },
            error: function () {
                $('#afPayStatus').append('');
                $('#afPayStatus').append('<span class="text-danger">Payment Not Saved</span>');
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
      //  cash = addElement(cash,"year",admissionYear);
        console.log(cash);
        $.ajax({
            method: 'post',
            url: '/cash/save',
            data: cash,
            contentType: "application/json",
            success: function () {
                calculateAF(studentID, afToPay,admissionYear);
                $('#admsnFeePaid').val();
                $('#afCashSavingStatus').append('<span class="text-success">Cash Payment Entered</span>');
                return false;
            },
            error: function () {
                $('#afCashSavingStatus').append('<span class="text-success">Not Success</span>');
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
                calculateAF(studentID, afToPay,admissionYear);
                $('#fromSalChequeSavingStatus').append('<span class="text-success">Check Payment Entered</span>');
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

        // if ($('#chequeImg').val() == '') {
        //     $('#mfChequeSavingStatus').append('<span class="text-danger">Select image</span>');
        //     return '';
        // }

        $.ajax({
            method: 'post',
            url: '/cheque/save',
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
                calculateAF(studentID, afToPay,admissionYear);
                $('#mfChequeSavingStatus').append('<span class="text-success">Cash Payment Entered</span>');
                return false;
            },
            error: function () {
                $('#mfChequeSavingStatus').append('<span class="text-success">Not Success</span>');
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
                calculateAF(studentID, afToPay,admissionYear);
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
                calculateAF(studentID, afToPay,admissionYear);
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
                calculateAF(studentID, afToPay,admissionYear);
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
        afToPay = afToPay - 100;
        calculateAF(afPaymentId, afToPay,admissionYear);
    });

    $('body').on('click', '#academicFee', function () {
        $('#academicFeeRow').fadeOut(300);
        var af = getAcademicFee(student.courseName);
        afToPay = afToPay - af;
        calculateAF(afPaymentId, afToPay,admissionYear);

    });

    $('body').on('click', '#bookFee', function () {
        $('#bookFeeRow').fadeOut(300);
        var bookFee = getBookFee(student.year, student.gender);
        afToPay = afToPay - bookFee;
        calculateAF(afPaymentId, afToPay,admissionYear);
    });

    $('body').on('click', '#deleteBtnOnModal', function (e) {
        var id = $(this).attr("data-id");
        console.log('sucess');
    });

    $('#deleteBtnOnModal').click(function () {
        console.log('sucess');
    });


    $('#pdfGeneratorAdmisnFee').click(function () {
        makePdf();
    });
});

function admissionFeeStmt(st_id, afPaymentId) {
    var student;
    var afToPay;
    $.ajax({
        method: 'GET',
        url: '/student/json/' + parseInt(st_id),
        async: false,
        success: function (data) {

            data = $.parseJSON(data);
            student = data[0];
            afToPay = getTotalCommonMandatoryFee(data[0]);


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
                "<tr><td>" + "Year" + "</td>" + "<td id='yearName'></td></tr>"
                +
                "<tr><td>" + "Payment ID" + "</td>" + "<td id='paymentIDonStmt'></td></tr>"
            );

            $('#admisnFeeTblBody').append(
                "<tr ><td>" + "Admission Fee" + "</td>" + "<td >" + (data[0].status == 'admitted' ? 100 : 200) + "</td></tr>"
            );

            if (data[0].finDtlsOfStudent.mealFee != 'NA') {
                $('#admisnFeeTblBody').append(
                    "<tr id='mealPlanFeeRow'><td>" + "Meal Plan" + "</td>" + "<td ><span>" + 100.0 + "</span><button id='mealPlan' class='btn btn-danger waves-effect' style='float:right;height: 18px;padding: 0px 5px'>X</button></td></tr>"
                );
            }

            if (data[0].finDtlsOfStudent.academicFee != 'NA') {
                $('#admisnFeeTblBody').append(
                    "<tr id='academicFeeRow'><td>" + "Academic Fee" + "</td>" + "<td ><span>" + getAcademicFee(data[0].courseName) + "</span><button id='academicFee' class='btn btn-danger waves-effect' style='float:right;height: 18px;padding: 0px 5px'>X</button></td></tr>"
                );
            }


            if (data[0].year != null && data[0].finDtlsOfStudent.bookFee != 'NA') {
                $('#admisnFeeTblBody').append(
                    "<tr id='bookFeeRow'><td>" + "Book Fee" + "</td>" + "<td ><span>" + getBookFee(data[0].year, data[0].gender.toLowerCase()) + "</span><button id='bookFee' class='btn btn-danger waves-effect' style='float:right;height: 18px;padding: 0px 5px'>X</button></td></tr>"
                );
            }

            $('#admisnFeeTblBody').append(
                "<tr><td>" + "Total Common Mandatory Fee" + "</td>" + "<td id='afToPayOnStmt'>" + afToPay + "</td></tr>"
                +
                "<tr><td>" + "Admission Fee Paid" + "</td>" + "<td id='admisnFeePaidFieldOnStmt'></td></tr>"
                +
                "<tr><td>" + "Admission Fee Due" + "</td>" + "<td id='admisnFeeDue'></td></tr>"
            );

        },
        error: function () {
            console.log('not success');
        }
    });

    return student;
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

function getTotalCommonMandatoryFee(student) {
    var totalFee = (student.status == 'admitted' ? 100 : 200);

    if (student.finDtlsOfStudent.academicFee != 'NA') {
        totalFee += getAcademicFee(student.courseName);
    }

    if (student.finDtlsOfStudent.bookFee != 'NA') {
        totalFee += getBookFee(student.year, student.gender);
    }

    if (student.finDtlsOfStudent.mealFee != 'NA') {
        totalFee += 100;
    }

    return totalFee;
    ;
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


function calculateAF(studentID, afToPay,feeYear) {
    var feePaid = getAFPaid(studentID,feeYear);
    $('#admisnFeeToPay').val(afToPay);
    $('#afToPayOnStmt').html('');
    $('#afToPayOnStmt').html(afToPay);
    $('#admsnFeePaid').val(feePaid);
    $('#admsnFeeDue').val(afToPay - feePaid);
    $('#admisnFeeDue').html(afToPay - feePaid);
    $('#admisnFeePaidFieldOnStmt').html(feePaid);
}

function getAFPaid(stId,feeYear) {
    var amount = 0;
    $.ajax({
        method: 'GET',
        url: '/fee/' +stId+'/'+feeYear,
        async: false,
        success: function (data) {
            amount = data;
        },
        error: function () {
            console.log('not success');
        }
    });
    return amount;
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

function saveAdmissionFeeDetails(student) {
    console.log('success');
    if (!$('#mealPlanFeeRow').is(':visible')) {
        $.ajax({
            method: 'GET',
            url: '/af/mealFee/' + parseInt(student.finDtlsOfStudent.id),
            success: function () {
            },
            error: function () {
            }
        });
    }

    if (!$('#academicFeeRow').is(':visible')) {
        $.ajax({
            method: 'GET',
            url: '/af/acFee/' + parseInt(student.finDtlsOfStudent.id),
            success: function () {
            },
            error: function () {
            }
        });
    }

    if (!$('#bookFeeRow').is(':visible')) {
        $.ajax({
            method: 'GET',
            url: '/af/bookFee/' + parseInt(student.finDtlsOfStudent.id),
            success: function () {
            },
            error: function () {
            }
        });
    }
}

function getAFPayment(stId, year) {
    var af;
    $.ajax({
        method: 'GET',
        url: '/af/' + stId + '/' + year,
        async: false,
        success: function (data) {
            af = data;
        },
        error: function () {
        }
    });

    return af;
}

function deleteAFPayment(stId) {
    $.ajax({
        method: 'GET',
        url: '/af/delete/' + stId,
        async: false,
        success: function () {
        },
        error: function () {
        }
    });
}

function addElement(mainStr,name,value) {
    var adm = mainStr.replace("}","");
    var str = ',"'+name+'":"'+value+'"}';
    var adm2 = adm.concat(str);
    mainStr = adm2;

    return mainStr;
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






















