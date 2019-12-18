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

    var studentID;
    $('#studentIdsOnTuitionFee').change(function () {
        studentID = $('#studentIdsOnTuitionFee option:selected').val();

        if (studentID == '0') {
            $('#tuitionFeeTblBody').html("<tr><td colspan='2' class='text-center'>" + "Tuition Fee Statement" + "</td></tr>");
            $('#pdfGeneratorAdmisnFee').hide();
            $('#tuitionFeeForm').hide();
            $('#tFeeStId').val('');
            $('#tFeeToPay').val('');

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
        if(isNaN(val)){
            $('#tuitionFeePaidFieldOnStmt').html('');
            $('#tuitionFeeDueOnStmt').html('');
            $('#tuitionFeeDue').val('');
            return '';
        }
        var tuitionFee = parseInt($('#tuitionFeeToPay').val());
        tuitionFeeDue = tuitionFee-val;
        $('#tuitionFeePaidFieldOnStmt').html(val);
        $('#tuitionFeeDueOnStmt').html(tuitionFeeDue+'$');
        $('#tuitionFeeDue').val(tuitionFeeDue);
    });



    $('#tuitionFeeDue').keyup(function () {
        tuitionFeeDue = $('#tuitionFeeDue').val();
        if(isNaN(tuitionFeeDue)){
            return '';
        }
        $('#tuitionFeeDue').html(tuitionFeeDue);
    });

    $('#tuitionFee').click(function () {
        var tFee = JSON.stringify($('#tFeeForm').serializeJSON());
        var tFeePaid = parseInt($('#tuitionFeePaid').val());

        console.log(tFee);
        $('#tfPayStatus').html('');

        if (studentID == undefined || studentID == 0) {
            $('#tfPayStatus').append('<p class="text-danger">Select Student ID</p>');
            return "";
        }

        if (isNaN(tFeePaid)) {
            $('#tfPayStatus').append('<p class="text-danger">Enter admission fee paid</p>');
            return "";
        }
        $.ajax({
            method:'post',
            url:'/tuitionFee/save',
            data: tFee,
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
        $('#tfCashSavingStatus').html('');
        if (JSON.parse(cash).amount <= 0 || JSON.parse(cash).amount == '') {
            $('#tfCashSavingStatus').append('<span class="text-danger">Enter valid amount</span>');
            return '';
        }
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
        var cheque = JSON.stringify($('#tfChequeForm').serializeJSON());
        console.log(cheque);
        $('#tfChequeSavingStatus').html('');

        if(JSON.parse(cheque).accountNum == '' ||
            JSON.parse(cheque).chequeNum == '' ||
            JSON.parse(cheque).chequeDate == ''){
            $('#tfChequeSavingStatus').append('<span class="text-danger">Fill out all the fields</span>');
            return '';
        }

        if ($('#tfChequeImg').val() == '') {
            $('#tfChequeSavingStatus').append('<span class="text-danger">Select image</span>');
            return '';
        }
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
        var mo = JSON.stringify($('#tfMoneyOrderForm').serializeJSON());

        $('#tfMOSavingStatus').html('');

        if(JSON.parse(mo).moneyOrderDate == '' ||
            JSON.parse(mo).moneyOrderNum == '' ||
            isNaN(JSON.parse(mo).amount)){
            $('#tfMOSavingStatus').append('<span class="text-danger">Enter valid data</span>');
            return '';
        }

        if ($('#tfMOImg').val() == '') {
            $('#tfMOSavingStatus').append('<span class="text-danger">Select image</span>');
            return '';
        }
        console.log(mo);
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
        $('#tfZelleSavingStatus').html('');

        if(JSON.parse(zelle).amount == '' || JSON.parse(zelle).amount <= 0){
            $('#tfZelleSavingStatus').append('<span class="text-danger">Enter amount</span>');
            return '';
        }

        if(JSON.parse(zelle).phoneNum == '' && JSON.parse(zelle).email == 0){
            $('#tfZelleSavingStatus').append('<span class="text-danger">Enter email or phone</span>');
            return '';
        }

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
        var cc = JSON.stringify($('#tfCCForm').serializeJSON());
        console.log(cc);
        $('#tfCCSavingStatus').html('');
        if (JSON.parse(cc).amount <= 0 ||
            isNaN(JSON.parse(cc).amount) ||
            JSON.parse(cc).tnxId == '' ||
            JSON.parse(cc).cardNum == '') {
            $('#tfCCSavingStatus').append('<span class="text-danger">Enter valid data</span>');
            return '';
        }
        console.log(cc);
        $.ajax({
            method:'post',
            url:'/cc/save',
            data: cc,
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
            var manFeesDue = parseInt(data[0].finDtlsOfStudent.mandatoryFeesDue);
            var tuitionFee = (3800+manFeesDue-parseInt(getDiscount(data[0])))/10;
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
                "<tr><td>" + "Tuition Fee" + "</td>" + "<td >" + tuitionFee  + "</td></tr>"
                +
                "<tr><td>" + "Tuition Fee Paid" + "</td>" + "<td id='tuitionFeePaidFieldOnStmt'></td></tr>"
                +
                "<tr><td>" + "Tuition Fee Due" + "</td>" + "<td id='tuitionFeeDueOnStmt'></td></tr>"

            )
            $('#tuitionFeeToPay').val(tuitionFee);
            $('#motnhName').html(getMonthName());
            $('#paymentIDonStmt').html(getPaymentId());



        },
        error: function () {
            console.log('not success');
        }
    })
}

//=====================end================

function getDiscount(data){
    var discount = 0;

    if (data.finDtlsOfStudent.sp_id > 0) {
        discount += getSponsorCont(data.id);
    }

    if (data.finDtlsOfStudent.hasDadd) {
        discount += getDaddCont(data.finDtlsOfStudent.id,data.id);
    }

    if (data.finDtlsOfStudent.collection > 0) {
        discount += parseInt(data.finDtlsOfStudent.collection);
    }

    if (data.finDtlsOfStudent.sibling_num > 0) {
        var i = data.finDtlsOfStudent.sibling_num;
        var sibDiscount = 0;

        if (i == 1) {
            sibDiscount = 600;
        } else if (i == 2) {
            sibDiscount = 800;
        } else if (i == 3) {
            sibDiscount = 1000;
        }
        discount += sibDiscount;
    }

    if (data.finDtlsOfStudent.isStaffChild) {
        discount += 3800.00;
    }

    return discount;
}

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

function getSponsorCont(st_id) {
    var sponsor = 0;
    $.ajax({
        method: 'GET',
        url: '/sponsor/student/' + st_id,
        async: false,
        success: function (data) {

            var interval = data.donationInterval;
            if (interval == 'Weekly') {
                sponsor = parseInt(data.donationAmount) * 4 * 10;
            } else if (interval == 'Monthly') {
                sponsor = parseInt(data.donationAmount) * 10;
            } else if (interval == 'Annually') {
                sponsor = parseInt(data.donationAmount);
            } else {
                sponsor = parseInt(data.donationAmount);
            }

        },
        error: function () {
            console.log('not success');
        }
    })

    return sponsor;
}

function getDaddCont(fin_id, st_id) {

    var daddsContribution = 0;
    $.ajax({
        method: 'GET',
        url: '/dadd/all/' + parseInt(st_id),
        async: false,
        success: function (data) {
            totalDadd = data.length;
            $('#tblDaddList').html('');
            for (var i = 0; i < data.length; i++) {
                daddsContribution += (parseInt(data[i].donationAmount)) * 360;
            }
        },
        error: function () {
            console.log('not success');
        }
    })
    return daddsContribution;
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






















