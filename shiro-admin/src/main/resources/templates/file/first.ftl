<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>次元数字档案管理平台-首页</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/index.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/css.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/file.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/css.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/page.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/search.css?v=1">
    <script type="text/javascript" src="/assets/js/clock.js"></script>
</head>
<body onload="start();">
<div class="box">
    <div class="index_top">
        <div class="index_top_left">
            <a href="/model/index" class="index_logo"></a>
        </div>
        <div class="index_top_right">
            <div class="index_top_right_list">
                <ul>
                    <li class="index_top_right_list_li"><a href="/index">首页</a></li>
                    <li class="top_active  index_top_right_list_li"><a href="/model/index" target="iframe_a">档案检索</a></li>
                    <li class="index_top_right_list_li"><a href="/model/manger" target="iframe_a">档案管理</a></li>
                    <#--  <li class="index_top_right_list_li"><a href="login.html" target="iframe_a">综合利用</a></li>
                      <li class="index_top_right_list_li"><a href="login.html" target="iframe_a">统计日志</a></li>
                      <li class="index_top_right_list_li"><a href="login.html" target="iframe_a">档案配置</a></li>-->
                </ul>
            </div>
        </div>
    </div>
<div class="first_box">
    <div class="first_box_left">
        <div class="box_left_top">
            <div class="first_left_btn first_left_btn1"></div>
            <div class="first_left_btn first_left_btn2"></div>
            <div class="first_left_btn first_left_btn3"></div>
            <div class="first_left_btn first_left_btn4"></div>
            <div class="first_left_btn first_left_btn5"></div>
            <div class="first_left_btn first_left_btn6"></div>
            <div class="first_left_btn first_left_btn7"></div>
        </div>
        <div class="box_left_btm">
            <div class="first_logo"></div>
            <div class="first_search">
                <input type="text" placeholder="输入关键词搜索档案，多个关键字用空格分开" name="search"><div class="search_btn"></div>
            </div>
        </div>
    </div>
    <div class="first_box_right">
        <#--<div class="box_right_top">
            <div class="notice">公告&通知</div>
            <div class="notice_content">
                <ul>
                    <li><a href=""><span>系统停机升级公告</span> <span class="notice_time">02-22</span></a></li>
                    <li><a href=""><span>系统停机升级公告</span> <span class="notice_time">02-22</span></a></li>
                    <li><a href=""><span>系统停机升级公告</span> <span class="notice_time">02-22</span></a></li>
                    <li><a href=""><span>系统停机升级公告</span> <span class="notice_time">02-22</span></a></li>
                    <li><a href=""><span>系统停机升级公告</span> <span class="notice_time">02-22</span></a></li>
                    <li><a href=""><span>系统停机升级公告</span> <span class="notice_time">02-22</span></a></li>
                </ul>
            </div>
        </div>-->
        <div class="box_right_mid">
            <div id="time_box"></div>
            <div id="date_box"></div>
        </div>
        <div class="box_right_btm">
            <div class="statistics_box">
                <div class="statistics_num">
                    <div class="statistics_img statistics_img1"></div>
                    <p><span class="color_red" name="file"></span><span>件</span> </p>
                </div>
                <div class="statistics_num">
                    <div class="statistics_img statistics_img2"></div>
                    <p><span class="color_red" name="ele"></span><span>个</span> </p>
                </div>
                <div class="statistics_num">
                    <div class="statistics_img statistics_img3"></div>
                    <p><span class="color_red" name="case"></span><span>卷</span> </p>
                </div>
                <#-- <div class="statistics_num">
                     <div class="statistics_img statistics_img4"></div>
                     <p><span class="color_red">11</span><span>份</span> </p>
                 </div>
                 <div class="statistics_num">
                     <div class="statistics_img statistics_img5"></div>
                     <p><span class="color_red">11</span><span>份</span> </p>
                 </div>-->
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<script type="text/javascript" src='/assets/js/jquery-3.5.1.min.js'></script>
<script type="text/javascript">
    $(".search_btn").click(function(){
        var param = $("input[name='search']").val();
        if(!param){
            alert("检索参数不能为空！");
        }
        $(location).attr('href','/model/manger?param='+param);
    })
    count();
    function count(){
        $.ajax({
            type: "GET",
            url: "/data/count",
            dataType: "json",
            success: function (json) {
                if (json.status === 200) {
                    var map = json.data;
                    console.log(map);
                    $("span[name='ele']").html(map["ele"]);
                    $("span[name='file']").html(map["file"]);
                    $("span[name='case']").html(map["case"]);
                }
            }
        });
    }

</script>
<#include "layout/footer.ftl"/>