<div class="iframe_right_top">
    <div class="right_top_right">
        <@shiro.hasPermission name="config:delete">
            <div class="right_btn" onclick="out()">删除</div>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="config:edit">
            <div class="right_btn" onclick="edit()">修改</div>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="config:add">
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
            <th>标识</th>
            <th>类型</th>
            <th>模式</th>
           <#-- <th>来源</th>-->
            <th>说明</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>

    <div class="pop_bg" style="display: none;">
        <form>
            <input name="id" type="hidden">
        <div class="pop_box">
            <div class="pop_top">
                <span>添加</span>
                <div class="pop_close" onclick="$('.pop_bg').hide();">X</div>
            </div>
            <div class="pop_title">添加档案类型</div>
            <div class="pop_mid">
                <div class="pop_name pop_content">
                    <div class="pop_name_left pop_left">名称★</div>
                    <div class="pop_name_right pop_right">
                        <input type="text" name="name" placeholder="文书档案">
                        <p>档案类型的名称，如 科技档案，基建档案 等，必须填写</p>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="pop_name pop_content">
                    <div class="pop_name_left pop_left">标识★</div>
                    <div class="pop_name_right pop_right">
                        <input type="text" name="code" placeholder="TQ2">
                        <p>档案类型的标识，如 WS，TQ2 等，必须填写 大小写不同，不可重复</p>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="pop_template pop_content">
                    <div class="pop_template_left pop_left">选择模板</div>
                    <div class="pop_template_right pop_right">
                        <p>
                            <label class="radio"><input type="radio" name="type" value="0"><span>一文一件</span></label>
                            <label class="radio"><input type="radio" name="type" value="1"><span>组卷</span></label>
                        </p>
                        <p>选择最接近的档案模板，创建后可根据实际情况调整结构、界面和规则。</p>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="pop_pattern pop_content">
                    <div class="pop_pattern_left pop_left">整理模式</div>
                    <div class="pop_pattern_right pop_right">
                        <p><label class="radio"><input type="radio" name="finisMod" value="1"><span>自动整理</span></label>&nbsp&nbsp 档号、件号等自动产生，检查一致性，适合新档案；</p>
                        <p><label class="radio"><input type="radio" name="finisMod" value="0"><span>手动整理</span></label>&nbsp&nbsp 档号、件号等手工输入，不检查合法性，适合兼容历史档案。</p>
                    </div>
                    <div class="clear"></div>
                </div>
           <#--     <div class="pop_source pop_content">
                    <div class="pop_source_left pop_left">档案来源</div>
                    <div class="pop_source_right pop_right">
                        <label class="radio"><input type="radio" checked="checked" name="source" value="手工录入"><span>手工录入</span></label>
                        <label class="radio"><input type="radio" name="source" value="离线客户端采集"><span>离线客户端采集</span></label>
                        <label class="radio"><input type="radio" name="source" value="OA"><span>OA</span></label>
                        <label class="radio"><input type="radio" name="source" value="ERP"><span>ERP</span></label>
                        <p>当档案接口进入时，需要选择接口的分类名称。</p>
                    </div>
                    <div class="clear"></div>
                </div>-->
                <div class="pop_mark pop_content">
                    <div class="pop_mark_left pop_left">备注说明</div>
                    <div class="pop_mark_right pop_right">
                        <textarea name="remarks"></textarea>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="pop_btm">
                <div class="pop_close_btn" onclick="$('.pop_bg').hide();">取消</div>
                <div class="pop_new_btn">保存</div>
            </div>
        </div>
        </form>
    </div>

</div>
<script>
    const emumType = {0:"一文一件", 1:"组卷"};
    const emumMode = {0:"自动整理", 1:"手动整理"};
    const list = function () {
        $.ajax({
            type: "GET",
            url: "/fileType",
            dataType: "json",
            async:false,
            success: function (json) {
                let rows = json.data;
                length = json.total;
                let tbody = $("table tbody");
                tbody.html("");
                let content = ``;
                let ser = 1;
                for(let object of rows){
                    content+=`<tr id="`+object.id+`">
                            <td><input name="id" type="checkbox" value="`+object.id+`"></td>
                            <td>`+ser+++`</td>
                            <td>`+object.name+`</td>
                            <td>`+object.code+`</td>
                             <td style="display: none;">`+object.type+`</td>
                            <td>`+emumType[object.type]+`</td>
                             <td>`+emumMode[object.finisMode]+`</td>
                             <td style="display: none;">`+object.finisMode+`</td>
                             <td>`+object.remarks+`</td>
                            </tr>`;
                }
                tbody.html(content);
            }
        });
    };

    const add = function () {
        $("form")[0].reset();
        $("form .pop_new_btn").on("click",function(){
            but();
        });
        $(".pop_bg").show();
    };

    const but = function () {
        $(".pop_bg").hide();
        let code = $("input[name='code']").val();
        let name = $("input[name='name']").val();
        if(!code||!name){
            alert("请输入必填项目！");
            return false;
        }
        let form =  $('form').serialize();
        $.ajax({
            type: "POST",
            url: "/fileType",
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
        let form =  $('form').serialize();
        $.ajax({
            type: "PUT",
            url: "/fileType",
            data:form,
            dataType: "json",
            async:false,
            success: function (json) {
                $(".pop_bg").hide();
                list()
                alert(json.message);
            }
        });
    }



    const out = function () {
        var chk_value =[];//定义一个数组
        $('input[name="id"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
            chk_value.push(parseInt($(this).val()));//将选中的值添加到数组chk_value中
        });
        if(chk_value.length==0){
            alert("请选择要操作的元素！");
            return false;
        }
        remove(chk_value[0]);

    };


    const checked = function (){
        let chk_value =[];//定义一个数组
        $('input[name="id"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
            chk_value.push(parseInt($(this).val()));//将选中的值添加到数组chk_value中
        });
        if(chk_value.length==0){
            alert("请选择要操作的元素！");
            return;
        }
        return chk_value;
    }

    const remove = function (id) {
        $.ajax({
            type: "DELETE",
            url: "/fileType/"+id,
            traditional: true,
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
        $("form input[name='id']").val(chk_value[0]);
        $("form input[name='name']").val(tds[2].innerHTML);
        $("form input[name='code']").val(tds[3].innerHTML);
        $("form textarea").val(tds[9].innerHTML);
        let type = tds[4].innerHTML;
        let finisMod = tds[7].innerHTML;
        let source = tds[8].innerHTML;
        let typeInput=$("input[name='type'][value='"+type+"']");
        let modeInput=$("input[name='finisMod'][value='"+finisMod+"']");
        let sourceIput = $("input[name='source'][value='"+source+"']");
        typeInput.prop("checked", "checked");
        modeInput.prop("checked", "checked");
        sourceIput.prop("checked", "checked");
        $("form .pop_new_btn").on("click",function(){
            update();
        });
        $(".pop_bg").show();
    };
    list();
</script>

