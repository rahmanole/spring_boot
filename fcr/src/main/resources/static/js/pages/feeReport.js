$(document).ready(function() {
    var stType=0;
    var acFee=0;
    var resFee = 0;
    var total = 0;

    $('input[type=radio][name="admisnFee"]').change(function () {

        if( $(this).is(":checked") ){ // check if the radio is checked
            stType = $(this).val(); // retrieve the value
            $('#admsnFee').val(stType);
            total = total + parseInt(total);
            $('#total').val(totalFee(stType,acFee,resFee));

            console.log(stType);
        }

    });


    $('#course').change(function () {
        acFee = $('#course option:selected').val();
        $('#courseFee').val(acFee);
        $('#total').val(totalFee(stType,acFee,resFee));
    });


    $('input[type=radio][name="boarding"]').change(function () {

        if( $(this).is(":checked") ){ // check if the radio is checked
            resFee = $(this).val(); // retrieve the value
            $('#boarding').val(resFee);
            $('#residentialFee').val(resFee);
            $('#total').val(totalFee(stType,acFee,resFee));

            console.log(resFee);
        }

    });


});

function totalFee(stType,acFee,resFee) {
    return parseInt(stType)+parseInt(acFee)+parseInt(resFee);
}

