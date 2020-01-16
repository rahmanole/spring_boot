$(document).ready(function () {
    getStudentsWithDue();
});

function getStudentsWithDue() {
    var tbl = $('#tuitionDueTable').DataTable();
    $.ajax({
        method:'GET',
        url: '/tuitionFee/tfDue',
        success:function (data) {
            for(var i = 0;i<data.length;i++){
                tbl.row.add([data[i].stID,data[i].name,data[i].dob,data[i].due]).draw();
            }
        },
        error: function () {
            console.log('not success');
        }
    });
}