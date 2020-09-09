<div class="iframe_right_top">
    <div class="right_top_left">
        <input type="text" name="search" value=""><div class="search_btn right_btn" onclick="search()">检索</div>
    </div>
    <div class="right_top_right">
        <@shiro.hasPermission name="dept:batchDelete">
            <div class="right_btn" onclick="batchDelete()">批量删除</div>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="dept:delete">
            <div class="right_btn" onclick="out()">删除</div>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="dept:edit">
            <div class="right_btn" onclick="edit()">修改</div>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="dept:add">
            <div class="right_btn" onclick="add()">增加</div>
        </@shiro.hasPermission>
    </div>
</div>

<div class="iframe_right_list">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th></th>
            <th>序号</th>
            <th>名称</th>
            <th>部门号</th>
            <th>所属机构</th>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>


    <form  class="bootstrap-frm" style="display: none;">
        <input type="hidden" name="id">
        <h1>
            <span onclick="$('form.bootstrap-frm').hide()">关闭</span>
        </h1>
        <label>
            <span>名称:</span>
            <input  type="text" name="name" placeholder="请输入部门的名称" />
        </label>
        <label>
            <span>部门号 :</span>
            <input  type="text" name="code" placeholder="请输入您的部门号" />
        </label>

        <label>
            <span>机构 :</span>
            <select name="orgId">

            </select>
        </label>

        <label>
            <span>&nbsp;</span>
            <input type="button" class="button"  value="提交" />
        </label>
    </form>


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
            type: "POST",
            url: "/dept/list",
            data:{"pageNumber":pageNum,"keywords":param},
            dataType: "json",
            async:false,
            success: function (json) {
                let rows = json.rows;
                length = json.total;
                let tbody = $("table tbody");
                tbody.html("");
                let content = ``;
                let ser = 1;
                for(let row of rows){
                    let object = row.sysDept;
                    content+=`<tr id="`+object.id+`">
                            <td><input name="dept" type="checkbox" value="`+object.id+`"></td>
                            <td>`+ser+++`</td>
                            <td>`+object.name+`</td>
                            <td>`+object.code+`</td>
                             <td>`+object.parent.name+`</td>
                            <td>`+renderTime(object.createTime)+`</td>
                            </tr>`;
                }
                tbody.html(content);
            }
        });
    };
    const orgSelect = function () {
        $.ajax({
            type: "POST",
            url: "/org/simple/list",
            dataType: "json",
            async:false,
            success: function (json) {
                let list = json.data;
                let content = ``;
                for(l of list){
                    content+=`<option value=`+l.id+`>`+l.name+`</option>`
                }
               $("form select").html(content);
            }
        });
    }
    const but = function () {
        $("form.bootstrap-frm").hide();
        let form =  $('form').serialize();
        $.ajax({
            type: "POST",
            url: "/dept/add",
            data:form,
            dataType: "json",
            async:false,
            success: function (json) {
                alert(json.message);
                list()
            }
        });
    }

    const update = function () {
        $("form.bootstrap-frm").hide();
        let form =  $('form').serialize();
        $.ajax({
            type: "POST",
            url: "/dept/edit",
            data:form,
            dataType: "json",
            async:false,
            success: function (json) {
                alert(json.message);
                list()
            }
        });
    }

    const add = function () {
        $("form")[0].reset();
        $("form.bootstrap-frm .button").on("click",function(){
            but();
        });
        $("form.bootstrap-frm").show();
    };

    const out = function () {
        var chk_value =[];//定义一个数组
        $('input[name="dept"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
            chk_value.push(parseInt($(this).val()));//将选中的值添加到数组chk_value中
        });
        if(chk_value.length==0){
            alert("请选择要操作的元素！");
            return false;
        }
        remove(chk_value[0]);

    };

    const batchDelete = function () {
        let chk_value = checked();
        remove(chk_value);
    };

    const checked = function (){
        let chk_value =[];//定义一个数组
        $('input[name="dept"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
            chk_value.push(parseInt($(this).val()));//将选中的值添加到数组chk_value中
        });
        if(chk_value.length==0){
            alert("请选择要操作的元素！");
            return;
        }
        return chk_value;
    }

    const remove = function (ids) {
        $.ajax({
            type: "POST",
            url: "/dept/remove",
            traditional: true,
            data:{"ids":ids},
            async:false,
            success: function (json) {
                alert(json.message);
                list()
            }
        });
    }

    const edit = function () {
        let chk_value = checked();
        if(!chk_value){
            return;
        }
        if(chk_value.length>1){
            alert("请选择一条元素！");
            return;
        }
        let tds = $("#"+chk_value[0]+" td");
        $("form.bootstrap-frm input[name='id']").val(chk_value[0]);
        $("form.bootstrap-frm input[name='name']").val(tds[2].innerHTML);
        $("form.bootstrap-frm input[name='code']").val(tds[3].innerHTML);
        $("form.bootstrap-frm .button").on("click",function(){
            update();
        });
        $("form.bootstrap-frm").show();
    };

    list();
    orgSelect();
    initPage(length,"Page");
    const search = function(){
        let keys = $("input[name='search']").val();
        list(1,keys);
        initPage(length,"Page");
    };



</script>

