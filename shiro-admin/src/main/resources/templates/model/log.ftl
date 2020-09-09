<div class="iframe_right_top">
    <div class="right_top_left">
        <input type="text" name="search" value=""><div class="search_btn right_btn" onclick="search()">检索</div>
    </div>
</div>

<div class="iframe_right_list">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>序号</th>
            <th>内容</th>
            <th>时间</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>

</div>
<div class="iframe_right_page">
    <!-- 分页放在这里 -->
    <div class="page" id="Page"></div>
</div>
<script>
    var length = 0;
    const list = function (pageNum,param) {
        if(!param){
            param = "";
        }
        if(!pageNum){
            pageNum = 1;
        }else{
            pageNum = parseInt(pageNum);
        }
        $.ajax({
            type: "GET",
            url: "/log/condit",
            data:{"pageNum":pageNum,"param":param},
            dataType: "json",
            async:false,
            success: function (json) {
                console.log(json);
                let rows = json.rows;
                length = json.total;
                let tbody = $("table tbody");
                tbody.html("");
                let content = ``;
                let ser = 1;
                for(let row of rows){
                    content+=`<tr>
                            <td>`+ser+++`</td>
                            <td>`+row.content+`</td>
                            <td>`+renderTime(row.createTime)+`</td>
                            </tr>`;
                }
                tbody.html(content);
                tbody.html(content);
            }
        });
    };
    list();
    initPage(length,"Page");


    const search = function(){
        let keys = $("input[name='search']").val();
        list(1,keys);
        initPage(length,"Page");
    };

</script>

