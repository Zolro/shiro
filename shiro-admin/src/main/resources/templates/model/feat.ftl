

   角色<select id="role">
       <option value="0" selected="selected">请选择角色</option>
    </select>
   <div style=" width: 100%;height: 1px;border-top: solid #ACC0D8 1px;"></div>
    <div>
        <h2>菜单栏配置</h2>
            <input type="checkbox" name="vehicle" value="22" />档案检索
            <input type="checkbox" name="vehicle" value="8" />档案配置
            <input type="checkbox" name="vehicle" value="23"/>档案管理
            <input type="checkbox" name="vehicle" value="1"/>用户管理
            <input type="checkbox" name="vehicle" value="26" />组织管理
            <input type="checkbox" name="vehicle" value="38"/>权限配置
    </div>
    <div>
        <h2>档案配置</h2>
        <p>1.档案类型</p>
        <input type="checkbox" name="vehicle" value="39" />增加
        <input type="checkbox" name="vehicle" value="40" />修改
        <input type="checkbox" name="vehicle" value="41"/>删除
    </div>
    <div>
        <h2>档案管理</h2>
        <input type="checkbox" name="vehicle" value="42" />目录挂挡
        <input type="checkbox" name="vehicle" value="43"/>文件上传
    </div>
    <div>
        <h2>用户管理</h2>
        <input type="checkbox" name="vehicle" value="1"/>增加
        <input type="checkbox" name="vehicle" value="6"/>删除
        <input type="checkbox" name="vehicle" value="5" />修改
        <input type="checkbox" name="vehicle" value="4"/>批量删除
        <input type="checkbox" name="vehicle" value="37"/>设置密码
        <input type="checkbox" name="vehicle" value="7" />分配角色
    </div>
    <div>
        <h2>组织管理</h2>
        <p>1.机构管理</p>
        <input type="checkbox" name="vehicle" value="29" />增加
        <input type="checkbox" name="vehicle" value="31"/>修改
        <input type="checkbox" name="vehicle" value="32"/>删除
        <input type="checkbox" name="vehicle" value="30"/>批量删除
        <p>2.部门管理</p>
        <input type="checkbox" name="vehicle" value="33" />增加
        <input type="checkbox" name="vehicle" value="35"/>修改
        <input type="checkbox" name="vehicle" value="36"/>删除
        <input type="checkbox" name="vehicle" value="34"/>批量删除
    </div>
    <div>
        <h2>权限配置</h2>
        <p>1.角色管理</p>
        <input type="checkbox" name="vehicle" value="15"/>增加
        <input type="checkbox" name="vehicle" value="17"/>修改
        <input type="checkbox" name="vehicle" value="18"/>删除
        <p>2.功能配置</p>
        <input type="checkbox" name="vehicle" value="38" />功能配置
    </div>
   <div style=" width: 100%;height: 1px;border-top: solid #ACC0D8 1px;"></div>
   <button>提交</button>
<script>
    findRole();
    function findRole(){
        $.ajax({
            type: "POST",
            url: "/roles/list",
            traditional: true,
            async:false,
            success: function (json) {
                let content = ``;
                let roles = json.rows;
                for(role of roles){
                    if(role.available){
                        content += `<option value="`+role.id+`">`+role.name+`</option>`;
                    }
                }
                $("#role").append(content);
            }
        });
    }
    function findResource(roleId){

        if(roleId==0){
            return;
        }
        let param = [1,6];
        for(let v of param){
            $('input[type="checkbox"][name="vehicle"][value='+v+']').attr("checked","true");
        }

    }
    $("#role").on("change",function() {
        let role = $("#role").val();
        console.log(role);
        if(role==0){

        }
        findResource(role);
    });
    $("button").click(function () {
        let param = new Array();
        $('input[type="checkbox"][name="vehicle"]:checked').each(function() {
                param.push($(this).val());
        });

    });
</script>


