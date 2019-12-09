$(document).ready(function () {

    $('#studentIdsOnAdmisnFee').change(function () {
        var studentID = $('#studentIdsOnAdmisnFee option:selected').val();
        $('#admisnFeeStId').val(studentID);

        if (studentID == '0') {
            $('#monthlyFeeTblBody').html("<tr><td colspan='2' class='text-center'>" + "Admission Fee Statement" + "</td></tr>");
            $('#pdfGenerator').hide();
            return;
        } else {
            $('#pdfGenerator').show();
            admissionFeeStmt(studentID);
        }
    });

    $('#pay').click(function () {
        console.log($('#cheque').val());
    });

    var admisnFeeDue= 0;

    $('#admsnFeePaid').keyup(function () {

        var val = parseInt($('#admsnFeePaid').val());
        var admisnFee = parseInt($('#admisnFeeToPay').val());
        admisnFeeDue = admisnFee-val;
        $('#admisnFeePaidFieldOnStmt').html(val);
        $('#admisnFeeDue').html(admisnFeeDue+'$');

    });



    $('#admsnFeeDue').keyup(function () {
        admisnFeeDue = $('#admsnFeeDue').val();
        $('#admisnFeeDue').html(admisnFeeDue);
    });

    $('#admisnFee').click(function () {
        console.log('admisnFeeDue');
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
                "<tr><td>" + "Month" + "</td>" + "<td>" + data[0].fatherName + "</td></tr>"
                +
                "<tr><td>" + "Admission Fee" + "</td>" + "<td >" + data[0].finDtlsOfStudent.mandatoryFees + "</td></tr>"
                +
                "<tr><td>" + "Admission Fee Paid" + "</td>" + "<td id='admisnFeePaidFieldOnStmt'></td></tr>"
                +
                "<tr><td>" + "Admission Fee Due" + "</td>" + "<td id='admisnFeeDue'></td></tr>"

            )
            $('#admisnFeeToPay').val(data[0].finDtlsOfStudent.mandatoryFees);
        },
        error: function () {
            console.log('not success');
        }
    })
}

//=====================end================























