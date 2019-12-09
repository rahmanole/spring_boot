$(document).ready(function () {

    $('#studentIdsOnMnthFee').change(function () {
        var studentID = $('#studentIdsOnMnthFee option:selected').val();
        $('#pay_st_d_field').val(studentID);

        if (studentID == '0') {
            $('#monthlyFeeTblBody').html("<tr><td colspan='2' class='text-center'>" + "Montyly Fee Statement" + "</td></tr>");
            $('#pdfGenerator').hide();
            return;
        }

        $('#pdfGenerator').show();
        monthlyFeeStmt(studentID);
        console.log(studentID);
    });


    //These code for making payment
    $('#paymentType').change(function () {
        var val = $('#paymentType option:selected').val();
        if (val == "tFee") {
            $('#monthlyTuition').show();
            $('#monthlyFeePaid').show();
            $('#monthlyFeeDue').show();

            $('#collectionTarget').hide();
            $('#collectionCollected').hide();
            $('#collectionDue').hide();
            $('#monthlyFeeDuePay').hide();
            $('#collectionDuePay').hide();

        } else if (val == 'tFeeDue') {


            $('#monthlyFee').hide();
            $('#monthlyFeePaid').hide();
            $('#collectionDue').hide();
            $('#collectionDuePay').hide();
            $('#collectionTarget').hide();
            $('#collectionCollected').hide();


        } else if (val == 'collection') {

            $('#collectionTarget').show();
            $('#collectionCollected').show();
            $('#collectionDue').show();


            $('#monthlyFee').hide();
            $('#monthlyFeePaid').hide();
            $('#monthlyFeeDue').hide();
            $('#monthlyFeeDuePay').hide();
            $('#collectionDuePay').hide();


        } else {
            $('#collectionDue').show();
            $('#collectionDuePay').show();

            $('#monthlyFee').hide();
            $('#monthlyFeePaid').hide();
            $('#collectionTarget').hide();
            $('#collectionCollected').hide();
            $('#monthlyFeeDue').hide();
            $('#monthlyFeeDuePay').hide();
        }
    });
    $('#pay').click(function () {
        console.log($('#cheque').val());
    });


    //For generating pdf
    var specialElementHandlers = {
        "#editor": function (element, renderer) {
            return true;
        }
    };

    $('#pdfGenerator').click(function () {

        $('#finTbl').printThis({
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


function monthlyFeeStmt(st_id) {
    $.ajax({
        method: 'GET',
        url: '/student/json/' + parseInt(st_id),
        success: function (data) {
            data = $.parseJSON(data);

            //$('#monthlyFeeTblBody').html('');

            //==========ends here===========

            $('#monthlyFeeTblBody').html('');
            $('#monthlyFeeTblBody').append(
                "<tr><td colspan='2' class='text-center'>" + "Montyly Fee Statement" + "</td></tr>"
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
                "<tr><td>" + "Month" + "</td>" + "<td id='monthName'>" + data[0].fatherName + "</td></tr>"
                +
                "<tr><td>" + "Monthlt Tuition" + "</td>" + "<td id='monthName'>" + getMonthlyTuitionFee(data[0]) + "</td></tr>"
            )
        },

        error: function () {
            console.log('not success');
        }
    })
}

//=====================end================


function getMonthlyTuitionFee(student){
    var discount = 0;
    if (student.finDtlsOfStudent.sp_id > 0) {
        discount += getSponsorDonationAmnt(student.id);
    }

    if (student.finDtlsOfStudent.hasDadd) {
        discount += getDaddContribution(student.finDtlsOfStudent.id, student.id);
    }

    if (student.finDtlsOfStudent.collection > 0) {
        discount += parseInt(student.finDtlsOfStudent.collection);
    }
    if (student.finDtlsOfStudent.sibling_num > 0) {
        var i = student.finDtlsOfStudent.sibling_num;
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

    if (student.finDtlsOfStudent.isStaffChild) {
        discount += 3800.00;
    }

    if (student.finDtlsOfStudent.isSelfFunded) {
        return 3800/10;
    }

    return (3800-discount)/10;
}

function getSponsorDonationAmnt(st_id) {

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

function getDaddContribution(fin_id, st_id) {

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























