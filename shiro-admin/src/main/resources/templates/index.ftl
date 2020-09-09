
<#assign title = "首页">
<#include "/layout/header.ftl"/>
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
        <div class="box_right_top">
            <div class="notice">操作记录</div>
            <div class="notice_content">
                <ul>

                </ul>
                <a href="/log" style="color: #d70c18">显示全部</a>
            </div>
        </div>
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
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<script type="text/javascript" src='/assets/js/jquery-3.5.1.min.js'></script>
<script type="text/javascript" src="/assets/js/clock.js"></script>
<script type="text/javascript" src='/assets/js/tooles.js'></script>
<script>
    window.onload=function(){start()};
</script>
<script type="text/javascript">
    $(".search_btn").click(function(){
        let param = $("input[name='search']").val();
        if(!param){
            alert("检索参数不能为空！");
        }
        $(location).attr('href','/mangers?param='+param);
    })
    loadStyles("/assets/css/search.css");
    count();
    log();
    function count(){
        $.ajax({
            type: "GET",
            url: "/file/count",
            dataType: "json",
            success: function (json) {
                console.log(json);
                if (json.status === 200) {
                    $("span[name='ele']").html(json.data.eleNumber);
                    $("span[name='file']").html(json.data.fileNumber);
                    $("span[name='case']").html(json.data.caseNumber);
                }
            }
        });
    }
    function log(){
        $.ajax({
            type: "GET",
            url: "/log/condit",
            data:{"pageNumber":1,"keywords":""},
            dataType: "json",
            async:false,
            success: function (json) {
                let rows = json.rows;
                length = json.total;
                let tbody = $(".notice_content ul");
                tbody.html("");
                let content = ``;
                let ser = 1;
                for(let row of rows){
                    if(ser>=4)
                        break;
                    content+=`<li><span>`+row.content+`</span> <span class="notice_time">`+renderTime(row.createTime)+`</span></li>`;
                    ser++;
                }
                tbody.html(content);
            }
        });
    }
</script>
<#include "/layout/booter.ftl"/>


