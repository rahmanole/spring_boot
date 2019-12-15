$(document).ready(function () {

    //$('#admissionFeeForm').hide();
    $('#month').val(getMonthName());

    var paymentId = getPaymentId();
    $('#tFeePaymentID').val(paymentId);
    $('#tfCashPID').val(paymentId);
    $('#tfChequePID').val(paymentId);
    $('#tfZellePID').val(paymentId);
    $('#tfCCPID').val(paymentId);
    $('#tfMoneyOrderPID').val(paymentId);

    $('#studentIdsOnTuitionFee').change(function () {
        var studentID = $('#studentIdsOnTuitionFee option:selected').val();

        if (studentID == '0') {
            $('#tuitionFeeTblBody').html("<tr><td colspan='2' class='text-center'>" + "Tuition Fee Statement" + "</td></tr>");
            $('#pdfGeneratorAdmisnFee').hide();
            $('#tuitionFeeForm').hide();

            return;
        } else {
            $('#tFeeStId').val(studentID);
            $('#pdfGeneratorAdmisnFee').show();
            $('#tuitionFeeForm').show();
            tuitonFeeStmt(studentID);
        }
    });



    var tuitionFeeDue= 0;

    $('#tuitionFeePaid').keyup(function () {
        var val = parseInt($('#tuitionFeePaid').val());
        var tuitionFee = parseInt($('#tuitionFeeToPay').val());
        tuitionFeeDue = tuitionFee-val;
        $('#tuitionFeePaidFieldOnStmt').html(val);
        $('#tuitionFeeDueOnStmt').html(tuitionFeeDue+'$');
        $('#tuitionFeeDue').val(tuitionFeeDue);
    });



    $('#tuitionFeeDue').keyup(function () {
        tuitionFeeDue = $('#tuitionFeeDue').val();
        $('#tuitionFeeDue').html(tuitionFeeDue);
    });

    $('#tuitionFee').click(function () {
        var cash = JSON.stringify($('#tFeeForm').serializeJSON());
        console.log(cash);
        $.ajax({
            method:'post',
            url:'/tuitionFee/save',
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

    $('#tfCashBtn').click(function () {
        var cash = JSON.stringify($('#tfCashForm').serializeJSON());
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




    $('#tfChequeForm').submit(function (event) {
        event.preventDefault();
        $.ajax({
            method:'post',
            url:'/cheque/save',
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache:false,
            success:function () {
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });

    $('#tfMoneyOrderForm').submit(function (event) {
        event.preventDefault();
        $.ajax({
            method:'post',
            url:'/mo/save',
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache:false,
            success:function () {
                return false;
            },
            error: function () {
                console.log('not success');
            }
        })
    });

    $('#tfZellBtn').click(function () {
        var zelle = JSON.stringify($('#tfZelleForm').serializeJSON());
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

    $('#tfCCBtn').click(function () {
        var zelle = JSON.stringify($('#tfCCForm').serializeJSON());
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

    $('#pdfGeneratorTuitionFee').click(function () {

        $('#tuitionFeeTbl').printThis({
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

function tuitonFeeStmt(st_id) {
    $.ajax({
        method: 'GET',
        url: '/student/json/' + parseInt(st_id),
        success: function (data) {
            data = $.parseJSON(data);

            //$('#monthlyFeeTblBody').html('');

            //==========ends here===========

            $('#tuitionFeeTblBody').html('');
            $('#tuitionFeeTblBody').append(
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
                "<tr><td>" + "Admission Fee Paid" + "</td>" + "<td id='tuitionFeePaidFieldOnStmt'></td></tr>"
                +
                "<tr><td>" + "Admission Fee Due" + "</td>" + "<td id='tuitionFeeDueOnStmt'></td></tr>"

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
        url: '/tuitionFee/getPaymentId',
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






















