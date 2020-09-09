<!DOCTYPE html>
<html lang="en">
<head>

    <title>次元数字档案管理平台-登录界面</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/login.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/css.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/reset.css">
</head>

<body>
<div class="box">
    <form action="/passport/signin" method="POST" id="login-form">
        <div class="login_right">
            <div class="title"></div>
            <div class="login_box">
                <div class="user_box">
                    <input type="text" placeholder="请输入用户名" name="username">
                </div>
                <div class="password_box">
                    <input type="password" placeholder="请输入密码" name="password" >
                </div>
            </div>
            <div class="login_btn"></div>
        </div>
    </form>
</div>
</body>

<script src="https://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" type="text/javascript"></script>
<script>
    $(".login_btn").click(function () {
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
    });
</script>
</html>
