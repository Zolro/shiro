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
                    <li class="index_top_right_list_li"><a href="/model/index" target="iframe_a">档案检索</a></li>
                    <li class="top_active index_top_right_list_li"><a href="/model/manger" target="iframe_a">档案管理</a></li>
                    <#--  <li class="index_top_right_list_li"><a href="login.html" target="iframe_a">综合利用</a></li>
                      <li class="index_top_right_list_li"><a href="login.html" target="iframe_a">统计日志</a></li>
                      <li class="index_top_right_list_li"><a href="login.html" target="iframe_a">档案配置</a></li>-->
                </ul>
            </div>
        </div>
    </div>
<div class="iframe_box">
    <#include "layout/meun.ftl"/>
    <div class="iframe_right file_iframe_right file1">
        <div class="iframe_right_top">
            <div class="right_top_left">
                <span class="folder_name" id="fileType"></span><span>-</span><span class="file_name" id="dire"></span>
                <input type="text" name="search" value="${param}"><div class="search_btn right_btn" id="search">检索</div>
            </div>
            <div class="right_top_right">
                <div class="right_btn">上传目录</div>
                <div class="right_btn">上传文件</div>
                <#--<div class="right_btn">设置</div><div class="right_btn">赋权申请</div><div class="right_btn">借阅申请</div><div class="right_btn">加入收藏</div><div class="right_btn">导出</div><div class="right_btn">原文下载</div>-->
            </div>
        </div>
        <div class="iframe_right_list" id="file_content">
            <table>
                <tr>
            
                </tr>


            </table>
        </div>
        <div class="iframe_right_page">
            <!-- 分页放在这里 -->
            <div class="page" id="Page"></div>
        </div>

        <div id="case_div" style="display:none;">
        <div class="iframe_right_top" id="case">
           <#-- <div class="right_top_left">
                <span>卷内目录</span><input type="text" name="case_search"><div class="search_btn right_btn">检索</div>
            </div>-->
        </div>

        <div class="iframe_right_list" id="case_content" >
            <table>
                <tr>

                </tr>


            </table>
        </div>
        <div class="iframe_right_page">
            <div class="pageCase" id="case_page"></div>
        </div>
        </div>


</div>

<#include "layout/footer.ftl"/>

