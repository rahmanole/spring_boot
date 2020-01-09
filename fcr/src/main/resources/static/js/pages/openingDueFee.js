$(document).ready(function () {

    // $('#admissionFeeForm').hide();
    $('#month').val(getMonthName());

    var studentID;
    var odfToPay;
    var odfPID ;

    $('#studentIdsOnOpeningDueFee').change(function () {

        studentID = $('#studentIdsOnOpeningDueFee option:selected').val();

        if (studentID == '0') {

            $('#openingDueFeeTblBody').html("<tr><td colspan='2' class='text-center'>" + "Opening Due Fee Statement" + "</td></tr>");
            $('#pdfGeneratorOpeningDueFee').hide();
            $('#openingDueFeeForm').hide();
            $('#openingDueFeePaymentID').val('');
            $('#existingODF').val('');
            $('#odfFeeStId').val('');
            return;

        } else {
            console.log(studentID);

            //This codes for admission fee payment id
            var odfPaymentId = getODFPaymentId();
            console.log(odfPaymentId);

            $('#openingDueFeePaymentID').val(odfPaymentId);
            $('#odfCashPID').val(odfPaymentId);
            $('#odfChequePID').val(odfPaymentId);
            $('#odfZellePID').val(odfPaymentId);
            $('#odfCCPID').val(odfPaymentId);
            $('#odfMoneyOrderPID').val(odfPaymentId);
            $('#odfFromSalPID').val(odfPaymentId);
            $('#odfPaymentIDonStmt').html(odfPaymentId);

            $('#odfFeeStId').val(studentID);
            $('#pdfGeneratorOpeningDueFee').show();
            $('#openingDueFeeForm').show();
            openingDueFeeStmt(studentID);

            odfToPay = $('#existingODF').val();
            odfPID = $('#openingDueFeePaymentID').val();
            calculateODF(odfPID,odfToPay);
        }
    });


    $('#odfDue').keyup(function () {
       var admisnFeeDue = $('#odfDue').val();
        if(isNaN(admisnFeeDue)){
            return '';
        }
        $('#odfDueOnStmt').html(admisnFeeDue);
    });


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
        insertingAdmissionFeeDue(admisnFeeDue, fin_id);

        var admissionFee = JSON.stringify($('#admFeeForm').serializeJSON());
        console.log(fin_id);
        console.log(admissionFee);
        $.ajax({
            method: 'post',
            url: '/admFee/save',
            data: admissionFee,
            contentType: "application/json",
            success: function () {
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });


    $('#odfCashBtn').click(function () {
        var cash = JSON.stringify($('#odfCashForm').serializeJSON());

        $('#odfCashSavingStatus').html('');

        if(JSON.parse(cash).paymentId == ''){
            $('#odfCashSavingStatus').append('<span class="text-danger">Select a student</span>');
            return '';
        }

        if (JSON.parse(cash).amount <= 0 || JSON.parse(cash).amount == '') {
            $('#odfCashSavingStatus').append('<span class="text-danger">Enter valid amount</span>');
            return '';
        }

        $.ajax({
            method: 'post',
            url: '/cash/save',
            data: cash,
            contentType: "application/json",
            success: function () {
                calculateODF(odfPID,odfToPay);
                $('#odfCashSavingStatus').append('<span class="text-success">Success</span>');
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });

    $('#odfFromSalForm').submit(function (event) {
        event.preventDefault();
        var cheque = JSON.stringify($('#odfFromSalForm').serializeJSON());
        console.log(cheque);
        $('#odfFromSalChequeSavingStatus').html('');

        if(JSON.parse(cheque).paymentId == ''){
            $('#odfFromSalChequeSavingStatus').append('<span class="text-danger">Select a student</span>');
            return '';
        }

        if(JSON.parse(cheque).chequeNum == '' ||
            JSON.parse(cheque).amount == '' ||
            JSON.parse(cheque).payPeriod == ''){
            $('#odfFromSalChequeSavingStatus').append('<span class="text-danger">Fill out all the fields</span>');
            return '';
        }

        if ($('#fromSalChequeImg').val() == '') {
            $('#odfFromSalChequeSavingStatuss').append('<span class="text-danger">Select image</span>');
            return '';
        }

        $.ajax({
            method: 'post',
            url: '/fromSal/save',
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function () {
                calculateODF(odfPID,odfToPay);
                $('#odfFromSalChequeSavingStatuss').append('<span class="text-success">Success</span>');
                return false;
            },

            error: function () {
                console.log('not success');
            }
        })
    });

    $('#odfChequeForm').submit(function (event) {
        event.preventDefault();
        var cheque = JSON.stringify($('#odfChequeForm').serializeJSON());
        console.log(cheque);
        $('#odfChequeSavingStatus').html('');

        if(JSON.parse(cheque).paymentId == ''){
            $('#odfChequeSavingStatus').append('<span class="text-danger">Select a student</span>');
            return '';
        }

        if(JSON.parse(cheque).accountNum == '' ||
            JSON.parse(cheque).chequeNum == '' ||
            JSON.parse(cheque).chequeDate == ''){
            $('#odfChequeSavingStatus').append('<span class="text-danger">Fill out all the fields</span>');
            return '';
        }

        if ($('#chequeImg').val() == '') {
            $('#odfChequeSavingStatus').append('<span class="text-danger">Select image</span>');
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
            success: function () {
                calculateODF(odfPID,odfToPay);
                $('#odfChequeSavingStatus').append('<span class="text-success">Success</span>');
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });

    $('#odfMoneyOrderForm').submit(function (event) {
        event.preventDefault();
        var mo = JSON.stringify($('#odfMoneyOrderForm').serializeJSON());
        console.log(mo);
        $('#odfMOSavingStatus').html('');

        if(JSON.parse(mo).paymentId == ''){
            $('#odfMOSavingStatus').append('<span class="text-danger">Select a student</span>');
            return '';
        }

        if(JSON.parse(mo).moneyOrderDate == '' ||
            JSON.parse(mo).moneyOrderNum == '' ||
            isNaN(JSON.parse(mo).amount)){
            $('#odfMOSavingStatus').append('<span class="text-danger">Enter valid data</span>');
            return '';
        }

        if ($('#moImage').val() == '') {
            $('#odfMOSavingStatus').append('<span class="text-danger">Select image</span>');
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
                calculateODF(odfPID,odfToPay);
                $('#odfMOSavingStatus').append('<span class="text-success">Success</span>');
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });

    $('#odfZellBtn').click(function () {
        var zelle = JSON.stringify($('#odfZelleForm').serializeJSON());
        console.log(zelle);
        $('#odfZelleSavingStatus').html('');

        if(JSON.parse(zelle).paymentId == ''){
            $('#odfZelleSavingStatus').append('<span class="text-danger">Select a student</span>');
            return '';
        }

        if(JSON.parse(zelle).amount == '' || JSON.parse(zelle).amount <= 0){
            $('#odfZelleSavingStatus').append('<span class="text-danger">Enter amount</span>');
            return '';
        }

        if(JSON.parse(zelle).phoneNum == '' && JSON.parse(zelle).email == 0){
            $('#odfZelleSavingStatus').append('<span class="text-danger">Enter email or phone</span>');
            return '';
        }

        $.ajax({
            method: 'post',
            url: '/zelle/save',
            data: zelle,
            contentType: "application/json",
            success: function () {
                calculateODF(odfPID,odfToPay);
                $('#odfZelleSavingStatus').append('<span class="text-success">Success</span>');
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });

    $('#odfCCBtn').click(function () {

        var cc = JSON.stringify($('#odfCCForm').serializeJSON());
        $('#odfCCSavingStatus').html('');

        if(JSON.parse(cc).paymentId == ''){
            $('#odfCCSavingStatus').append('<span class="text-danger">Select a student</span>');
            return '';
        }

        if (JSON.parse(cc).amount <= 0 ||
            isNaN(JSON.parse(cc).amount) ||
            JSON.parse(cc).tnxId == '' ||
            JSON.parse(cc).cardNum == '') {
            $('#odfCCSavingStatus').append('<span class="text-danger">Enter valid data</span>');
            return '';
        }

        $.ajax({
            method: 'post',
            url: '/cc/save',
            data: cc,
            contentType: "application/json",
            success: function () {
                calculateODF(odfPID,odfToPay);
                $('#odfCCSavingStatus').append('<span class="text-success">Success</span>');
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });





    //For generating pdf
    var specialElementHandlers = {
        "#editor": function (element, renderer) {
            return true;
        }
    };

    $('#pdfGeneratorOpeningDueFee').click(function () {

        $('#odfFeeTbl').printThis({
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
    });
});

function openingDueFeeStmt(st_id) {
    $.ajax({
        method: 'GET',
        url: '/student/json/' + parseInt(st_id),
        async:false,
        success: function (data) {
            data = $.parseJSON(data);

            //$('#monthlyFeeTblBody').html('');

            //==========ends here===========

            $('#openingDueFeeTblBody').html('');
            $('#openingDueFeeTblBody').append(
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
                "<tr><td>" + "Month" + "</td>" + "<td id='motnhName'></td></tr>"
                +
                "<tr><td>" + "Payment ID" + "</td>" + "<td id='odfPaymentIDonStmt'></td></tr>"
                +
                "<tr><td>" + "Existing Opening Due" + "</td>" + "<td >" + data[0].initialDue + "</td></tr>"
                +
                "<tr><td>" + "Opening Due Paid" + "</td>" + "<td id='odfFeePaidFieldOnStmt'></td></tr>"
                +
                "<tr><td>" + "Remaining Opening Due" + "</td>" + "<td id='odfDueOnStmt'></td></tr>"
            );

            $('#existingODF').val(data[0].initialDue);
            $('#motnhName').html(getMonthName());
            $('#odfPaymentIDonStmt').html(getODFPaymentId());
        },
        error: function () {
            console.log('not success');
        }
    });
}

function calculateDuesOnOpeningDue(odfFeePaid,amount) {

    odfFeePaid += parseInt(amount);
    $('#odfFeePaid').val(odfFeePaid);
    var val = parseInt($('#existingODF').val());

    $('#odfFeePaidFieldOnStmt').html(odfFeePaid);
    $('#odfDueOnStmt').html(val - odfFeePaid);
    $('#odfDue').val(val - odfFeePaid);

}

//=====================end================

function getODFPaymentId() {
    var paymentID = null;
    $.ajax({
        method: 'GET',
        url: '/openingDueFee/getPaymentId',
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


function calculateODF(odfPaymentId,odfToPay) {
    var feePaid = getAFPaid(odfPaymentId);
    $('#odfFeePaid').val(feePaid);
    $('#odfDue').val(odfToPay - feePaid);
    $('#odfFeePaidFieldOnStmt').html(feePaid);
    $('#odfDueOnStmt').html(odfToPay-feePaid);
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






















