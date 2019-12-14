$(document).ready(function () {

    $('#admissionFeeForm').hide();
    $('#month').val(getMonthName());

    var paymentId = getPaymentId();
    $('#admissionFeePaymentID').val(paymentId);
    $('#admCashPID').val(paymentId);
    $('#admChequePID').val(paymentId);
    $('#admZellePID').val(paymentId);
    $('#admCCPID').val(paymentId);
    $('#amdMoneyOrderPID').val(paymentId);

    $('#studentIdsOnAdmisnFee').change(function () {
        var studentID = $('#studentIdsOnAdmisnFee option:selected').val();

        if (studentID == '0') {
            $('#admisnFeeTblBody').html("<tr><td colspan='2' class='text-center'>" + "Admission Fee Statement" + "</td></tr>");
            $('#pdfGeneratorAdmisnFee').hide();
            $('#admissionFeeForm').hide();

            return;
        } else {
            $('#admisnFeeStId').val(studentID);
            $('#pdfGeneratorAdmisnFee').show();
            $('#admissionFeeForm').show();
            admissionFeeStmt(studentID);
        }
    });



    var admisnFeeDue= 0;

    $('#admsnFeePaid').keyup(function () {
        var val = parseInt($('#admsnFeePaid').val());
        var admisnFee = parseInt($('#admisnFeeToPay').val());
        admisnFeeDue = admisnFee-val;
        $('#admisnFeePaidFieldOnStmt').html(val);
        $('#admisnFeeDue').html(admisnFeeDue+'$');
        $('#admsnFeeDue').val(admisnFeeDue);
    });



    $('#admsnFeeDue').keyup(function () {
        admisnFeeDue = $('#admsnFeeDue').val();
        $('#admisnFeeDue').html(admisnFeeDue);
    });

    $('#admisnFee').click(function () {
        var cash = JSON.stringify($('#admFeeForm').serializeJSON());
        console.log(cash);
        $.ajax({
            method:'post',
            url:'/admFee/save',
            data: cash,
            contentType: "application/json",
            success:function () {
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });

    $('#admCashBtn').click(function () {
        var cash = JSON.stringify($('#cashForm').serializeJSON());
        console.log(cash);
        $.ajax({
            method:'post',
            url:'/cash/save',
            data: cash,
            contentType: "application/json",
            success:function () {
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });




    $('#chequeForm').submit(function (event) {
        event.preventDefault();
        $.ajax({
            method:'post',
            url:'/cheque/save',
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache:false,
            success:function (data) {
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });

    $('#moneyOrderForm').submit(function (event) {
        event.preventDefault();
        $.ajax({
            method:'post',
            url:'/mo/save',
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache:false,
            success:function (data) {
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
        $.ajax({
            method:'post',
            url:'/zelle/save',
            data: zelle,
            contentType: "application/json",
            success:function () {
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });

    $('#admCCBtn').click(function () {
        var zelle = JSON.stringify($('#ccForm').serializeJSON());
        console.log(zelle);
        $.ajax({
            method:'post',
            url:'/cc/save',
            data: zelle,
            contentType: "application/json",
            success:function () {
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });


    //For generating pdf
    var specialElementHandlers ={
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
            $('#paymentIDonStmt').html(getPaymentId());
        },
        error: function () {
            console.log('not success');
        }
    })
}

//=====================end================

function getPaymentId() {
    var paymentID = null;
    $.ajax({
        method:'GET',
        url: '/admissionFee/getPaymentId',
        async:false,
        success:function (data) {
            paymentID = data;
        },
        error:function () {
            console.log('not success');
        }
    })

    return paymentID;
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






















