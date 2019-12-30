$(document).ready(function () {

    // $('#admissionFeeForm').hide();
    $('#month').val(getMonthName());

    var studentID;

    $('#studentIdsOnAdmisnFee').change(function () {
        studentID = $('#studentIdsOnAdmisnFee option:selected').val();

        if (studentID == '0') {
            $('#admisnFeeTblBody').html("<tr><td colspan='2' class='text-center'>" + "Admission Fee Statement" + "</td></tr>");
            $('#pdfGeneratorAdmisnFee').hide();
            $('#admissionFeeForm').hide();
            $('#admisnFeeStId').val('');
            $('#admisnFeeToPay').val('');
            return;
        } else {

            //This codes for admission fee payment id
            var afPaymentId = getAfPaymentId();
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
            admissionFeeStmt(studentID);
        }
    });


    var admisnFeeDue = 0;

    $('#admsnFeePaid').keyup(function () {
        var val = parseInt($('#admsnFeePaid').val());
        if(isNaN(val)){
            $('#admisnFeePaidFieldOnStmt').html('');
            $('#admisnFeeDue').html('');
            $('#admsnFeeDue').val('');
            return '';
        }

        var admisnFee = parseInt($('#admisnFeeToPay').val());
        admisnFeeDue = admisnFee - val;
        $('#admisnFeePaidFieldOnStmt').html(val);
        $('#admisnFeeDue').html(admisnFeeDue + '$');
        $('#admsnFeeDue').val(admisnFeeDue);
    });


    $('#admsnFeeDue').keyup(function () {
        admisnFeeDue = $('#admsnFeeDue').val();
        if(isNaN(admisnFeeDue)){
            return '';
        }
        $('#admisnFeeDue').html(admisnFeeDue);
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

    $('#admCashBtn').click(function () {
        var cash = JSON.stringify($('#cashForm').serializeJSON());
        $('#afCashSavingStatus').html('');
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
                $('#afCashSavingStatus').append('<span class="text-success">Success</span>');
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });


    $('#fromSal').submit(function (event) {
        event.preventDefault();
        var cheque = JSON.stringify($('#fromSalForm').serializeJSON());
        console.log(cheque);
        $('#afFromSalChequeSavingStatus').html('');

        if(JSON.parse(cheque).chequeNum == '' ||
            JSON.parse(cheque).amount == '' ||
            JSON.parse(cheque).payPeriod == ''){
            $('#afFromSalChequeSavingStatus').append('<span class="text-danger">Fill out all the fields</span>');
            return '';
        }

        if ($('#fromSalChequeImg').val() == '') {
            $('#afFromSalChequeSavingStatus').append('<span class="text-danger">Select image</span>');
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

        if(JSON.parse(cheque).accountNum == '' ||
            JSON.parse(cheque).chequeNum == '' ||
            JSON.parse(cheque).chequeDate == ''){
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

        if(JSON.parse(mo).moneyOrderDate == '' ||
            JSON.parse(mo).moneyOrderNum == '' ||
            isNaN(JSON.parse(mo).amount)){
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
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });

    // $('#admFromSalBtn').submit(function (e) {
    //     e.stopPropagation();
    //     e();
    //     var chequeFromSal = JSON.stringify($('#fromSalForm').serializeJSON());
    //     console.log(chequeFromSal);
    //     $('#afFromSalChequeSavingStatus').html('');
    //
    //     if(JSON.parse(chequeFromSal).accountNum == '' ||
    //         JSON.parse(chequeFromSal).chequeNum == '' ||
    //         JSON.parse(chequeFromSal).chequeDate == ''){
    //         $('#afFromSalChequeSavingStatus').append('<span class="text-danger">Fill out all the fields</span>');
    //         return '';
    //     }
    //
    //     if ($('#fromSalChequeImg').val() == '') {
    //         $('#afFromSalChequeSavingStatus').append('<span class="text-danger">Select image</span>');
    //         return '';
    //     }
    //
    //     $.ajax({
    //         method: 'post',
    //         url: '/fromSal/save',
    //         data: new FormData(this),
    //         enctype: 'multipart/form-data',
    //         processData: false,
    //         contentType: false,
    //         cache: false,
    //         success: function (data) {
    //             return false;
    //         },
    //         error: function () {
    //             console.log('not success');
    //             return false;
    //         }
    //     })
    // });

    $('#admZellBtn').click(function () {
        var zelle = JSON.stringify($('#zelleForm').serializeJSON());
        console.log(zelle);
        $('#afZelleSavingStatus').html('');

        if(JSON.parse(zelle).amount == '' || JSON.parse(zelle).amount <= 0){
            $('#afZelleSavingStatus').append('<span class="text-danger">Enter amount</span>');
            return '';
        }

        if(JSON.parse(zelle).phoneNum == '' && JSON.parse(zelle).email == 0){
            $('#afZelleSavingStatus').append('<span class="text-danger">Enter email or phone</span>');
            return '';
        }

        $.ajax({
            method: 'post',
            url: '/zelle/save',
            data: zelle,
            contentType: "application/json",
            success: function () {
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

    $('#pdfGeneratorAdmisnFee').click(function () {

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
    });
});

function admissionFeeStmt(st_id) {
    $.ajax({
        method: 'GET',
        url: '/student/json/' + parseInt(st_id),
        success: function (data) {
            data = $.parseJSON(data);

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
                "<tr><td>" + "Month" + "</td>" + "<td id='motnhName'></td></tr>"
                +
                "<tr><td>" + "Payment ID" + "</td>" + "<td id='paymentIDonStmt'></td></tr>"
                +
                "<tr><td>" + "Admission Fee" + "</td>" + "<td >" + data[0].finDtlsOfStudent.mandatoryFees + "</td></tr>"
                +
                "<tr><td>" + "Admission Fee Paid" + "</td>" + "<td id='admisnFeePaidFieldOnStmt'></td></tr>"
                +
                "<tr><td>" + "Admission Fee Due" + "</td>" + "<td id='admisnFeeDue'></td></tr>"
            )
            $('#admisnFeeToPay').val(data[0].finDtlsOfStudent.mandatoryFees);
            $('#motnhName').html(getMonthName());
            $('#paymentIDonStmt').html(getAfPaymentId());
        },
        error: function () {
            console.log('not success');
        }
    });
}

//=====================end================

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






















