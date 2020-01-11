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
    var boardingSts = '';

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
        var course = $('#course option:selected').val();

        if(course == 'Academy'){
            acFee = 240;
        }else if(course == "Boy's Nazira"){
            acFee = 755;
        }else if(course == "Banaat Nazira"){
            acFee = 240;
        }else if(course == "Boy's Hifz"){
            acFee = 755;
        }else if(course == "Banaat Hifz"){
            acFee = 240;
        }else if(course == "Standardized Test"){
            acFee = 50;
        }

        acFee = parseInt(acFee);

        var course = $('#course option:selected').val();

        if (course == 'Alim' || course == 'Alima' || course == 'na') {
            $('#bookFeeDiv').show();
            $('#bookFeeRow').show();

            acFee = (course == 'Alim') ? 755 : 240;
            if (course == 'na') {
                acFee = 0;
            }
        } else {
            $('#bookFeeDiv').hide();
            $('#bookFeeRow').hide();

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
            //resFee = parseInt($(this).val()); // retrieve the value
            boardingSts = $(this).val();
            if(boardingSts == 'yes'){
                resFee = 2700;
            }else{
                resFee = 0;
            }

            tuitionFee = 3800;

            $('#residentialFee').val(resFee.toFixed(2));
            $('#tuitnFee').val(tuitionFee.toFixed(2));

            total = totoalTuitionFee(tuitionFee, resFee, mandatoryFee);
            $('#total').html(total.toFixed(2));

            console.log(resFee);
        }
    });


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
                $('#staffDiv').slideDown();
            }
            if (!isChcked) {
                $('#staffDiv').slideUp();
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
                $('#monthlyTuition').html((grandTotalFee / 10).toFixed(2));
            }
            if (!isChcked) {
                otp = 0;
                grandTotalFee = grandTotal(total, sponsor, dollarADay, collection, sibling, staff, otp, zakat);
                $('#grandTotalFee').html(grandTotalFee.toFixed(2));
                $('#monthlyTuition').html((grandTotalFee / 10).toFixed(2));
                $('#otpRow').hide();

            }
        });

        if ($('#zakat').is(':checked')) {
            $('#zakatDiv').slideDown();
        } else {
            $('#zakatDiv').slideUp();
        }

        $('#selfFunded').change(function () {
            var isChecked = $(this).is(':checked');
            if (isChecked) {
                $('#otp').attr('disabled', false);
                $('#sponsor').attr('disabled', true);
                $('#dollarADay').attr('disabled', true);
                $('#collection').attr('disabled', true);
                $('#sibling').attr('disabled', true);
                $('#staff').attr('disabled', true);
                $('#zakat').attr('disabled', true);
                $('#selfFundedDiv').slideDown();
            }

            if (!isChecked) {
                $('#otp').prop('checked', false);
                otp = 0;
                grandTotalFee = grandTotal(total, sponsor, dollarADay, collection, sibling, staff, otp, zakat);
                $('#monthlyTuition').html((grandTotalFee / 10).toFixed(2));
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
                $('#selfFundedDiv').slideUp();
            }

        });
    });


    hideDiscounts(['sponsorDiv', 'dollarADayDiv', 'collectionDiv', 'siblingDiv', 'staffDiv', 'otpDiv', 'zakatDiv']);

    //Load information for students
    //var st_id = document.getElementById('st_id').innerHTML;
    //getSponsorOfStudent(st_id);
    //=========end===========


    // $('#sponsorDiv').hide();
    // $('#sponsorDollarADay').hide();
    $('#remainingTuitionDue').hide();
    $('#remainingCollectionDue').hide();
    $('#otpRow').hide();
    $('#staffFeeRow').hide();
    $('#bookFeeDiv').hide();
    $('#sponsorRow').hide();
    $('#daddRow').hide();
    $('#collRow').hide();
    $('#sibRow').hide();
    $('#zakatFeeRow').hide();
    $('#removeSp').hide();
    $('#selfFundedDiv').hide();
    $('#bookFeeRow').hide();
    $('#pdfGenerator').hide();
    $('#collectionTarget').hide();
    $('#collectionCollected').hide();
    $('#collectionDue').hide();
    $('#dueAlert').hide();



    $('#stBtn').click(function () {
        getStudentById();
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

        $('#assignSp').attr('disabled', true);
        $('#spAssignMsgs').html('');
        $('#spAssignStatus').html('<sapn class="text-success">Successfully assigned sponsor</sapn>');

        sponsor = applySponsorDiscount();
        $('#spAmnt').val(sponsor);
        $('#sponsorRow').show();
        grandTotalFee = grandTotal(total, sponsor, dollarADay, collection, sibling, staff, otp, zakat);
        $('#grandTotalFee').html(grandTotalFee.toFixed(2));

    });

// removing a sponsor

    $('#removeSp').click(function () {

        var sp_id = document.getElementById('sp_id').innerHTML;
        var fin_dtl_Id = document.getElementById('fin_dtl_id').innerHTML;

        removingSponsor(fin_dtl_Id, sp_id);
        $('#spAssignStatus').html('Sponsor Removed')
        $('#assignSp').attr('disabled', false);
        $('#daddTBody').remove();
        sponsor = 0;
        $('#spAmnt').val('0.00');
        grandTotalFee = grandTotal(total, sponsor, dollarADay, collection, sibling, staff, otp, zakat);
        $('#grandTotalFee').html(grandTotalFee.toFixed(2));
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

        dollarADay += applyDaddDiscount();

        $('#daddAmnt').val(dollarADay);
        $('#daddRow').show();
        grandTotalFee = grandTotal(total, sponsor, dollarADay, collection, sibling, staff, otp, zakat);
        $('#grandTotalFee').html(grandTotalFee.toFixed(2));
    });

    //removing dadd

    $('#daddRmvBtn_0').on('click', function () {
        console.log('dadd rmv');
    });

    $(document).on('click', '#daddRmvBtn_0,#daddRmvBtn_1', function () {
        var st_id = document.getElementById('st_id').innerHTML;
        var fin_dtl_Id = document.getElementById('fin_dtl_id').innerHTML;

        console.log();
        if (this.id == 'daddRmvBtn_0') {
            var dadd_id = document.getElementById('daddId_0').innerText;
            removingDadd(fin_dtl_Id, dadd_id, st_id);
            $('#daddRow_0').fadeOut().delay(1000);
            console.log(this.id);
        } else if (this.id == 'daddRmvBtn_1') {
            var dadd_id = document.getElementById('daddId_1').innerText;
            removingDadd(fin_dtl_Id, dadd_id, st_id);
            $('#daddRow_1').fadeOut().delay(1000);
            console.log(this.id);
        }
    });


    //collection discount

    $('#addColl').click(function () {
        collection = applyCollDiscount();
        grandTotalFee = grandTotal(total, sponsor, dollarADay, collection, sibling, staff, otp, zakat);
        $('#grandTotalFee').html(grandTotalFee.toFixed(2));
        $('#monthlyTuition').html((grandTotalFee / 10).toFixed(2));
    });

    //adding staff child
    $('#confirmStaff').click(function () {
        var fin_dtl_Id = document.getElementById('fin_dtl_id').innerHTML;
        addingStaffChild(fin_dtl_Id);
        staff = 3800.00;
        $('#staffFeeAmt').val(staff);
        $('#staffFeeRow').show();
        grandTotalFee = grandTotal(total, sponsor, dollarADay, collection, sibling, staff, otp, zakat);
        $('#grandTotalFee').html(grandTotalFee.toFixed(2));
    });

    //removing staff child
    $('#removeStaff').click(function () {
        var fin_dtl_Id = document.getElementById('fin_dtl_id').innerHTML;
        removingStaffChild(fin_dtl_Id);
        staff = 0.00;
        $('#staffFeeAmt').val(staff);
        grandTotalFee = grandTotal(total, sponsor, dollarADay, collection, sibling, staff, otp, zakat);
        $('#grandTotalFee').html(grandTotalFee.toFixed(2));
    });

    //adding self funded
    $('#confirmSelfFunded').click(function () {
        var fin_dtl_Id = document.getElementById('fin_dtl_id').innerHTML;
        addingSelfFunded(fin_dtl_Id);
    });

    //removing self funded
    $('#removeSelfFunded').click(function () {
        var fin_dtl_Id = document.getElementById('fin_dtl_id').innerHTML;
        removingSelfFunded(fin_dtl_Id);
    });


    //Adding siblings

    $('#addSibling').click(function () {
        sibling = applySiblingDiscount();
        grandTotalFee = grandTotal(total, sponsor, dollarADay, collection, sibling, staff, otp, zakat);
        $('#grandTotalFee').html(grandTotalFee.toFixed(2));
        $('#monthlyTuition').html((grandTotalFee / 10).toFixed(2));

    });

    //Adding zakat

    $('#addZakat').click(function () {
        applyZakatDiscount();
    });

    //admiting student

    $('#admit').click(function () {
        var fin_dtl_Id = document.getElementById('fin_dtl_id').innerHTML;
        var mandFee = document.getElementById('totalMandatoryFee').innerHTML;

        var course = $('#course option:selected').val();
        var st_id = document.getElementById('st_id').innerHTML;

        insertingMandFees(mandFee, fin_dtl_Id);
        admitStudent(fin_dtl_Id);
        updateCourse(course,st_id);
        updateBoarding(boardingSts,st_id);

    });

    var flagToCallThisSpFunction = true;

    $('#studentIds').change(function () {
        var studentID = $('#studentIds option:selected').val();
        $('#pay_st_d_field').val(studentID);

        if (studentID == '0') {
            $('#basicInfoTblBody').html('');
            $('#finDtlsTbl').html('');
            $('#tblDaddList').html('');
            $('#daddTBody').html('');
            $('#spAsssignMsgs').html('');
            $('#assignSp').attr('disabled', false);
            $('#pdfGenerator').hide();
            return;
        }

        if(flagToCallThisSpFunction){
            flagToCallThisSpFunction = false;
            setSpNamesOnAutoCompleteField();
            setDADDNamesOnAutoCompleteField();
        }

        console.log(flagToCallThisSpFunction);

        $('#collection').prop('checked', false);
        $('#collectionAmt').val('');
        $('#collectionDiv').slideUp();


        $('#sponsor').prop('checked', false);
        $('#sponsorDiv').slideUp();

        $('#dollarADay').prop('checked', false);
        $('#dollarADayDiv').slideUp();

        $('#pdfGenerator').show();
        studentFeeReport(studentID);
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

function setSpNamesOnAutoCompleteField(){
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
}

function setDADDNamesOnAutoCompleteField(){
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
}


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
    } else {
        sponsor = parseInt(donationAmnt);
    }
    return sponsor;
}

function applyDaddDiscount() {

    var donationAmnt = document.getElementById('donationAmnt').innerText;
    dollarADay = parseInt(donationAmnt) * 360;
    return dollarADay;


}

function applyCollDiscount() {

    var collectionAmnt = $('#collectionAmt').val();
    var fin_dtl_Id = document.getElementById('fin_dtl_id').innerHTML;

    var collection = parseInt(collectionAmnt);

    $('#collAmt').val(collection);
    $('#collRow').show();
    insertingCollection(collection, fin_dtl_Id);

    return collection;

}

function applyZakatDiscount() {

    var zakatAmnt = $('#zakatAmount').val();
    var fin_dtl_Id = document.getElementById('fin_dtl_id').innerHTML;

    var zakat = parseInt(zakatAmnt);

    $('#zakatAmnt').val(zakat);
    $('#zakatFeeRow').show();
    insertingZakat(zakat, fin_dtl_Id);
}


function applySiblingDiscount() {
    var fin_dtl_Id = document.getElementById('fin_dtl_id').innerHTML;

    var val = [];
    var i = 0;
    $("#siblingsIds option:selected").each(function () {
        val.push(this.text);
        i++;
    });
    console.log(val.toString());
    var sibDiscount = 0;
    console.log(i);
    if (i == 1) {
        sibDiscount = 600;
    } else if (i == 2) {
        sibDiscount = 800;
    } else if (i == 3) {
        sibDiscount = 1000;
    }else if(i == 0){
        val = null;
    }

    $('#sibDisAmnt').val(sibDiscount);
    $('#sibRow').show();
    insertingSibling(val, i, fin_dtl_Id);
    return sibDiscount;
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
                $('#isAssigned').html("");
                $('#isAssigned').append('<sapn id="spAsssignMsgs" class="text-danger">This sponsor already assigned</sapn>');
                $('#assignSp').attr('disabled', true);

            } else {
                $('#isAssigned').html("");
                var spId = document.getElementById('sp_id_at_fin_report').innerHTML;
                if (parseInt(spId) == 0) {
                    $('#assignSp').attr('disabled', false);
                }
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
        url: '/student/json/' + parseInt(st_id),
        success: function (data) {
            data = $.parseJSON(data);

            // $('#studentDetails').html('');
            //
            // $('#studentDetails').html(
            //
            //     "<tr><td>" + "Mother Name" + "</td>" + "<td>" + data[0].motherName + "</td></tr>"
            //     +
            //     "<tr><td>" + "Date of Birth" + "</td>" + "<td>" + data[0].dob.substr(0,13) +"</td></tr>"
            // );

            var total = 3800, discount = 0;

            total += parseInt(data[0].finDtlsOfStudent.mandatoryFees);
            //below codes clean every table and elements before loading another
            $('#basicInfoTblBody').html('');
            $('#finDtlsTbl').html('');
            $('#tblDaddList').html('');
            $('#daddTBody').html('');
            $('#spAsssignMsgs').html('');
            $('#assignSp').attr('disabled', false);
            //==========ends here===========


            $('#basicInfoTblBody').append(
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
                "<tr><td>" + "Father  Name" + "</td>" + "<td>" + data[0].motherName + "</td></tr>"
                +
                "<tr><td>" + "Date of Birth" + "</td>" + "<td>" + data[0].dob.substr(0,12) + "</td></tr>"
            );


            $('#finDtlsTbl').append(
                "<tr><td>" + "Common Mandatory Fee" + "</td>" + "<td>" + "$" + data[0].finDtlsOfStudent.mandatoryFees + " /year</td></tr>"
            );

            $('#finDtlsTbl').append(
                "<tr><td>" + "Regular Tuition Fee" + "</td>" + "<td>" + "$" + 3800.00 + " /year</td></tr>"
            );

            if (data[0].boarding == 'yes') {
                total += 2700;
                $('#finDtlsTbl').append(
                    "<tr><td>" + "Boarding Fee" + "</td>" + "<td>" + "$" + 2700.00 + " /year</td></tr>"
                );
            }


            //Below code for showing details on fin details section
            if (data[0].finDtlsOfStudent.sp_id > 0) {
                discount += reportForSponsor(data[0].id);
                $('#sponsor').prop('checked', true);
                $('#sponsorDiv').slideDown();
            }

            if (data[0].finDtlsOfStudent.hasDadd) {
                discount += getAllDaddsForASt(data[0].finDtlsOfStudent.id, data[0].id);
                $('#dollarADay').prop('checked', true);
                $('#dollarADayDiv').slideDown();
            }

            if (data[0].finDtlsOfStudent.collection > 0) {
                discount += parseInt(data[0].finDtlsOfStudent.collection);
                $('#finDtlsTbl').append(
                    "<tr><td>" + "Collection Target" + "</td>" + "<td>" + "- $" + data[0].finDtlsOfStudent.collection + " /year</td></tr>"
                );
                $('#collection').prop('checked', true);
                $('#collectionAmt').val(data[0].finDtlsOfStudent.collection);
                $('#collectionDiv').slideDown();
            }

            if (data[0].finDtlsOfStudent.sibling_num > 0) {
                var i = data[0].finDtlsOfStudent.sibling_num;
                var sibDiscount = 0;

                if (i == 1) {
                    sibDiscount = 600;
                } else if (i == 2) {
                    sibDiscount = 800;
                } else if (i == 3) {
                    sibDiscount = 1000;
                }
                discount += sibDiscount;
                $('#finDtlsTbl').append(
                    "<tr><td>" + "Sibling Discount" + "</td>" + "<td>" + "- $" + sibDiscount + " /year</td></tr>"
                );
            }

            if (data[0].finDtlsOfStudent.isStaffChild) {
                discount += 3800.00;
                $('#finDtlsTbl').append(
                    "<tr><td>" + "Staff Discount" + "</td>" + "<td>" + "- $" + 3800.00 + " /year</td></tr>"
                );
            }

            if (data[0].finDtlsOfStudent.isSelfFunded) {

            }



            $('#finDtlsTbl').append(
                "<tr class='bg-info'><td>" + "Total Payable At The Time Of Admission" + "</td>" + "<td>" + "$" + data[0].finDtlsOfStudent.mandatoryFees + " /year</td></tr>"
            );

            $('#finDtlsTbl').append(
                "<tr class='bg-info'><td>" + "Total Payable Tuition Fee" + "</td>" + "<td>" + "$" + (total - discount-data[0].finDtlsOfStudent.mandatoryFees) + " /year</td></tr>"
            );

            if(data[0].initialDue>0){
                $('#dueAlert').show();
                $('#dueMessage').html('');
                $('#dueMessage').html('<span class="text-danger padding-0">This student has account opening due</span>');
            }else {
                $('#dueMessage').html('');
                $('#dueAlert').hide();
            }

            currentConditions(data[0]);


        },

        error: function () {
            console.log('not success');
        }
    })
}

//This function adds sponsor's details of the student on finalcial details table
function reportForSponsor(st_id) {
    // return $.ajax({
    //     url: '/sponsor/student/' + st_id
    // }).responseText;

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

            $('#finDtlsTbl').append(
                "<tr><td>" + "Sponsor\'s Contribution" + "</td>" + "<td id='sponsorDonation'>" + '-$' + sponsor + ' /year' + "</td></tr>"
            );
            getSponsorByname(data.name);


            //if sponsor is available then assign button willbe disabled and remove buttom
            //will be appeared
            $('#removeSp').show();
            $('#assignSp').attr('disabled', true);
            //ends here

        },
        error: function () {
            console.log('not success');
        }
    })

    return sponsor;
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

//This method removes a dadd
function removingDadd(fin_id, dadd_id, st_id) {

    console.log(fin_id);
    console.log(st_id);

    $.ajax({
        method: 'GET',
        url: '/dadd/remove/' + parseInt(fin_id) + '/' + parseInt(dadd_id) + '/' + parseInt(st_id),
        success: function () {
            console.log('success');
        },
        error: function () {
            console.log('not success');
        }
    })

}

//Get all dadds of a student
var totalDadd = 0;

function getAllDaddsForASt(fin_id, st_id) {

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

                $('#tblDaddList').append(
                    "<tr id='daddRow_" + i + "'><td>" + "Name" + "</td>" + "<td>" + data[i].name + "</td>" + "<td id='daddId_" + i + "'>" + data[i].id + "</td>" + "<td><button type='button' class='btn' id='daddRmvBtn_" + i + "'>Remove</button></td></tr>"
                )
            }

            $('#finDtlsTbl').append(
                "<tr><td>" + "Amount from DADDs" + "</td>" + "<td id='sp_name'>" + '-$' + daddsContribution + '/year' + "</td></tr>"
            );
        },
        error: function () {
            console.log('not success');
        }
    })
    return daddsContribution;
}


//method for adding collection

function insertingCollection(collAmnt, fin_id) {

    $.ajax({
        method: 'GET',
        url: '/findetails/' + parseInt(collAmnt) + '/' + parseInt(fin_id),
        success: function () {
            console.log('success');
            $('#collAddSts').html('');
            $('#collAddSts').append('<span class="text-success">"Successfull"</span>');
        },
        error: function () {
            console.log('not success');
        }
    })

}

//inserting true for staff child
function addingStaffChild(fin_id) {
    $.ajax({
        method: 'GET',
        url: '/findetails/addStaff/' + parseInt(fin_id),
        success: function () {
            console.log('success');
            $('#staffAddSts').html('');
            $('#staffAddSts').html('<span class="text-success">"Successful"</span>');
        },
        error: function () {
            console.log('not success');
        }
    })
}

//removing staff child
function removingStaffChild(fin_id) {
    $.ajax({
        method: 'GET',
        url: '/findetails/removeStaff/' + parseInt(fin_id),
        success: function () {
            console.log('success');
            $('#staffAddSts').html('');
            $('#staffAddSts').html('<span class="text-success">"Removed"</span>');
        },
        error: function () {
            console.log('not success');
        }
    })
}

//inserting true self funded
function addingSelfFunded(fin_id) {
    $.ajax({
        method: 'GET',
        url: '/findetails/selfFund/' + parseInt(fin_id),
        success: function () {
            console.log('success');
            $('#staffAddSts').append('');
            $('#staffAddSts').append('<span class="text-success">"Successfull"</span>');
        },
        error: function () {
            console.log('not success');
            $('#staffAddSts').append('');
            $('#staffAddSts').append('<span class="text-success">"Not Successfull"</span>');
        }
    })
}

//removing self funded
function removingSelfFunded(fin_id) {
    $.ajax({
        method: 'GET',
        url: '/findetails/removeSelfFund/' + parseInt(fin_id),
        success: function () {
            console.log('success');
            $('#staffAddSts').append('<span class="text-success">"Successfull"</span>');
        },
        error: function () {
            console.log('not success');
        }
    })
}


//method for adding zakat

function insertingZakat(zakat, fin_id) {

    $.ajax({
        method: 'GET',
        url: '/findetails/zakat/' + parseInt(zakat) + '/' + parseInt(fin_id),
        success: function () {
            console.log('success');
            $('#collAddSts').append('<span class="text-success">"Successfull"</span>');
        },
        error: function () {
            console.log('not success');
        }
    })

}

function insertingSibling(val, i, fin_id) {

    $.ajax({
        method: 'GET',
        url: '/findetails/insertSibling/' + val + '/' + i + '/' + parseInt(fin_id),
        success: function () {
            console.log('success');
            $('#siblingAddSts').append('<span class="text-success">"Successfull"</span>');
        },
        error: function () {
            console.log('not success');
        }
    })

}

function insertingMandFees(mandFees, fin_id) {

    $.ajax({
        method: 'GET',
        url: '/findetails/mandFees/' + parseInt(mandFees) + '/' + parseInt(fin_id),
        success: function () {
            console.log('success');
            // $('#siblingAddSts').append('<span class="text-success">"Successfull"</span>');
        },
        error: function () {
            console.log('not success');
        }
    })

}

function admitStudent(fin_id) {

    $.ajax({
        method: 'GET',
        url: '/student/admit/' + parseInt(fin_id),
        success: function () {
            $('#paymentPlanUpdateSts').html('<span class="text-success">"Successful"</span>');
        },
        error: function () {
            $('#paymentPlanUpdateSts').html('<span class="text-success">"Not Successful"</span>');
        }
    })

}

function updateCourse(course,st_id) {

    $.ajax({
        method: 'GET',
        url: '/student/updateCourse/'+course+'/' + parseInt(st_id),
        success: function () {
            console.log('success');
        },
        error: function () {
            console.log('not success');
        }
    })

}


function updateBoarding(boarding,st_id) {

    $.ajax({
        method: 'GET',
        url: '/student/updateBoarding/'+boarding+'/' + parseInt(st_id),
        success: function () {
            console.log('success');
        },
        error: function () {
            console.log('not success');
        }
    })

}

function currentConditions(student){

    if(student.status == 'admitted'){
        $('#returningSt').attr('checked',true).change();
    }else {
        $('#newSt').attr('checked',true).change();
    }

    $('#course').val(student.courseName).change();

    if(student.boarding.toLowerCase() == 'yes'){
        $('#yes').attr('checked',true).change();
        $('#resFeeOnStmt').show();
    }else{
        $('#no').attr('checked',true).change();
        $('#resFeeOnStmt').hide();
    }

    console.log(student.year);
    $('#bookFee').val(student.year).change();

}












