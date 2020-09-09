function getDire(){
    $.ajax({
        type: "POST",
        url: "/passport/signin",
        data: $("#login-form").serialize(),
        dataType: "json",
        success: function (json) {
            if (json.status === 200) {
                window.location.href = "/";
            }
        }
    });
}
