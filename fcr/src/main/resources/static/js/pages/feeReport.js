$(document).ready(function() {
    var stType=0;
    var acFee=0;
    var resFee = 0;
    var tuitionFee = 0;
    var due = 0;
    var total = 0;

    $('input[type=radio][name="admisnFee"]').change(function () {

        if( $(this).is(":checked") ){ // check if the radio is checked
            stType = $(this).val(); // retrieve the value
            if(stType == 200){
                $('#lastYearDue').hide();
                due =0;
            }else {

                $('#lastYearDue').show();
                due = parseInt($('#due').val());
            }
            $('#admsnFee').val(stType);
            total = total + parseInt(total);
            $('#total').val(totalFee(stType,acFee,resFee,tuitionFee,due));

            console.log(stType);
        }

    });


    $('#course').change(function () {
        acFee = $('#course option:selected').val();
        $('#courseFee').val(acFee);
        $('#total').val(totalFee(stType,acFee,resFee,tuitionFee,due));
    });


    $('input[type=radio][name="boarding"]').change(function () {

        if( $(this).is(":checked") ){ // check if the radio is checked
            resFee = $(this).val(); // retrieve the value
            $('#boarding').val(resFee);
            $('#residentialFee').val(resFee);
            $('#total').val(totalFee(stType,acFee,resFee,tuitionFee,due));

            console.log(resFee);
        }

    });

    $('#tuitionFee').keyup(function () {
        tuitionFee = $('#tuitionFee').val();
        $('#tuitnFee').val(tuitionFee);
        $('#total').val(totalFee(stType,acFee,resFee,tuitionFee,due));
        console.log(tuitionFee);
    });

    $('#lastYearDue').hide();
});

function totalFee(stType,acFee,resFee,tuitionFee,due) {
    var  tFee = isNaN(parseInt(tuitionFee))?0:parseInt(tuitionFee);
    return parseInt(stType)+
        parseInt(acFee)+
        parseInt(resFee)+
        tFee+
        parseInt(due);
}

