// $(document).ready(function () {
//
//     $('#beforePaymentModes').after(
//         '<tr>' +
//             '<td colspan="2">' +
//                 '<ul class="nav nav-tabs">' +
//                     '<li class="active"><a data-toggle="tab" href="#cash">Cash</a></li>' +
//                     '<li><a data-toggle="tab" href="#cheque">Cheque</a></li>' +
//                     '<li><a data-toggle="tab" href="#cc">Credit Card</a></li>' +
//                     '<li><a data-toggle="tab" href="#moneyOrder">Money Order</a></li>' +
//                     '<li><a data-toggle="tab" href="#zelle">Zelle</a></li>' +
//                 '</ul>' +
//
//                 '<div class="tab-content">' +
//                     '<div id="zelle" class="tab-pane fade">' +
//                         '<form method="POST" id="zelleForm">' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input disabled type="text" name="admZellePID" id="admZeePID" class="form-control" placeholder="Payment ID">' +
//                                 '</div>' +
//                             '</div>' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input type="text" name="amount" class="form-control" placeholder="Amount">' +
//                                 '</div>' +
//                             '</div>' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input type="text" name="phoneNum" class="form-control" placeholder="Phone">' +
//                                 '</div>' +
//                             '</div>' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input type="text" name="email" class="form-control" placeholder="Email">' +
//                                 '</div>' +
//                             '</div>' +
//                             '<button type="button" id="admZelleBtn" class="btn btn-primary waves-effect">SUBMIT</button>\n' +
//                         '</form>' +
//                     '</div>' +
//                     '<div id="cash" class="tab-pane active in">' +
//                         '<form method="POST" id="cashForm">' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input disabled type="text" name="admCashPID" class="form-control" placeholder="Payment ID">' +
//                                 '</div>' +
//                             '</div>' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input type="text" name="amount" class="form-control" placeholder="Amount">' +
//                                 '</div>' +
//                             '</div>' +
//                             '<button type="button" id="admCashBtn" class="btn btn-primary waves-effect">SUBMIT</button>\n' +
//                         '</form>' +
//                     '</div>' +
//
//                     '<div id="cheque" class="tab-pane fade">' +
//                         '<form method="POST" id="chequeForm">' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input disabled type="text" id="admChequePID" class="form-control" placeholder="Payment ID">' +
//                                 '</div>' +
//                             '</div>' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input type="text" name="accountNum" class="form-control" placeholder="Account Number">' +
//                                 '</div>' +
//                             '</div>' +
//
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input type="text" name="chequeNum" class="form-control" placeholder="Cheque Number">' +
//                                 '</div>' +
//                             '</div>' +
//
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input type="text" name="amount" class="form-control" placeholder="Amount">' +
//                                 '</div>' +
//                             '</div>' +
//
//                             '<div class="form-group form-float">' +
//                                 '<div class="form-line">' +
//                                     '<input type="file" style="margin-right:30px;float: left">' +
//                                     '<input type="button" id="chequeTakePhoto" class="btn" value="Take Photo" style="margin-right:30px;">' +
//                                 '</div>' +
//                             '</div>' +
//
//                             '<button type="button" id="admChequeBtn" class="btn btn-primary waves-effect">SUBMIT</button>\n' +
//                         '</form>' +
//                     '</div>' +
//
//                     '<div id="moneyOrder" class="tab-pane fade">' +
//                         '<form method="POST" id="moneyOrderForm">' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input disabled type="text" name="" id="amdMoneyOrderPID" class="form-control" placeholder="Payment ID">' +
//                                 '</div>' +
//                             '</div>' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input type="date" name="moneyOrderNum" class="form-control" placeholder="Money Order Date">' +
//                                 '</div>' +
//                             '</div>' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input type="text" name="moneyOrderNum" class="form-control" placeholder="Money Order Number">' +
//                                 '</div>' +
//                             '</div>' +
//
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input type="text" name="amount" class="form-control" placeholder="Amount">' +
//                                 '</div>' +
//                             '</div>' +
//
//                             '<div class="form-group form-float">' +
//                                 '<div class="form-line">' +
//                                     '<input type="file" style="margin-right:30px;float: left">' +
//                                     '<input type="button" id="takePhotoMO" class="btn" value="Take Photo" style="margin-right:30px;">' +
//                                 '</div>' +
//                             '</div>' +
//
//                             '<button type="button" id="admMoneyOrderBtn" class="btn btn-primary waves-effect">SUBMIT</button>\n' +
//                         '</form>' +
//                     '</div>' +
//                     '<div id="cc" class="tab-pane fade">' +
//                         '<form method="POST" id="ccForm">' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input disabled type="text" name="paymentId" id="admCCPID" class="form-control" placeholder="Payment ID">' +
//                                 '</div>' +
//                             '</div>' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input type="text" name="cardNum" class="form-control" placeholder="Card Number">' +
//                                 '</div>' +
//                             '</div>' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input type="text" name="tnxId" class="form-control" placeholder="Transaction ID">' +
//                                 '</div>' +
//                             '</div>' +
//                             '<div class="form-group form-float" style="margin-bottom: 10px">' +
//                                 '<div class="form-line">' +
//                                     '<input type="text" name="amount" class="form-control" placeholder="Amount">' +
//                                 '</div>' +
//                             '</div>' +
//                             '<button type="button" id="admCCBtn" class="btn btn-primary waves-effect">SUBMIT</button>\n' +
//                         '</form>' +
//                     '</div>' +
//                 '</div>' +
//             '</td>' +
//         '</tr>'
//     );
//
// });
