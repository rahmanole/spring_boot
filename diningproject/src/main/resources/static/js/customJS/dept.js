$(document).ready(function () {
    showDeptist();

    $('#addDept').click(function () {
        var formData = JSON.stringify($("form").serializeJSON());
        $.ajax({
            method: "POST",
            url: '/dept/save',
            data: formData,
            contentType: "application/json",
            success: function () {
                showDeptist()
                console.log('success');
            },
            error:function () {
                alert('not success');
            }

        });

        console.log(formData);
        return false;
    });
});

function showDeptist(){
    $.ajax({
        metthod: "get",
        url: '/dept/list',
        success: (function (data) {
            console.log(data);
            $('#deptNames').remove();
            $('#deptListDiv').append("<ul id='deptNames'></ul>");

            for (var i=0;i<data.length;i++){
                $('#deptNames').append("<li>"+data[i]+"</li>");
            }

        })
    });
}
