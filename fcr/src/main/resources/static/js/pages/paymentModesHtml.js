$(document).ready(function () {

    $('#beforePaymentModes').after(
        '<tr>' +
            '<td colspan="2">' +
                '<ul class="nav nav-tabs">' +
                    '<li class="active"><a data-toggle="tab" href="#menu1">Cheque</a></li>' +
                    '<li><a data-toggle="tab" href="#menu1">Cash</a></li>' +
                    '<li><a data-toggle="tab" href="#cc">Credit Card</a></li>' +
                    '<li><a data-toggle="tab" href="#menu1">Money Order</a></li>' +
                    '<li><a data-toggle="tab" href="#menu1">Zelle</a></li>' +
                '</ul>' +

                '<div class="tab-content">' +
                    '<div id="menu1" class="tab-pane fade">' +
                        '<form method="POST">' +
                            '<div class="form-group form-float" style="margin-bottom: 10px">' +
                                '<div class="form-line">' +
                                    '<input disabled type="text" id="sponsorName" class="form-control" placeholder="Payment ID">' +
                                '</div>' +
                            '</div>' +
                            '<div class="form-group form-float">' +
                                '<div class="form-line">' +
                                    '<input type="text" class="form-control" placeholder="Account Number">' +
                                '</div>' +
                            '</div>' +
                            '<button type="button" class="btn btn-primary waves-effect">SUBMIT</button>\n' +
                        '</form>' +
                    '</div>' +
                    '<div id="cc" class="tab-pane fade">' +
                        '<form method="POST">' +
                            '<div class="form-group form-float" style="margin-bottom: 10px">' +
                                '<div class="form-line">' +
                                    '<input disabled type="text" id="sponsorName" class="form-control" placeholder="Payment ID">' +
                                '</div>' +
                            '</div>' +
                            '<div class="form-group form-float" style="margin-bottom: 10px">' +
                                '<div class="form-line">' +
                                    '<input type="text" class="form-control" placeholder="Account Number">' +
                                '</div>' +
                            '</div>' +
                            '<div class="form-group form-float" style="margin-bottom: 10px">' +
                                '<div class="form-line">' +
                                    '<input type="text" class="form-control" placeholder="Cheque Number">' +
                                '</div>' +
                            '</div>' +
                            '<div class="form-group form-float" style="margin-bottom: 10px">' +
                                '<div class="form-line">' +
                                    '<input type="text" class="form-control" placeholder="Transaction ID">' +
                                '</div>' +
                            '</div>' +
                            '<div class="form-group form-float" style="margin-bottom: 10px">' +
                                '<div class="form-line">' +
                                    '<input type="text" class="form-control" placeholder="Amount">' +
                                '</div>' +
                            '</div>' +
                            '<button type="button" class="btn btn-primary waves-effect">SUBMIT</button>\n' +
                        '</form>' +
                    '</div>' +
                '</div>' +
            '</td>' +
        '</tr>'
    );

});
