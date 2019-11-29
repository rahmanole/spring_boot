$(document).ready(function () {
    loadDeptNameList();

})

function loadDeptNameList(){
    $.ajax({
        method: "get",
        url: '/dept/list',
        success: function (data) {
            $('#deptDropDiv').append('<select class="form-control show-tick" th:field="*{deptName}" id="deptNamesDrop" name="deptName"><option value="">-- Please select --</option></select>');
            for (var i=0;i<data.length;i++){
                $('#deptNamesDrop').append("<option value="+data[i]+">"+data[i]+"</option>");
            }
        },
        error:function () {
            alert('error')
        }
    });
}

function saveMemeberInfo(){
    var formData = JSON.stringify($('#regForm').serializeJSON());
    $.ajax({
        method:'POST',
        url: '/member/save',
        data:formData,
        enctype: 'multipart/form-data',
        contentType: false,
        processData:false,
        cache:false,
        success: function () {
            alert('saved');
            return formData;
        },
        error:function () {
            alert('not saved');
        }
    })
}
