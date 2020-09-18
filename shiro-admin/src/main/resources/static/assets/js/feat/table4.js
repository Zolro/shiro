(function () {
  if (!document.getElementById('roles')) return false;
layui.use(['form', 'table', 'layer', 'jquery'], function () {
  var form = layui.form;
  var table = layui.table;
  var $ = layui.jquery;
  var layer = layui.layer;

  table.render({
    elem: '#test_four'
    , url: '/roles/list'
    , method: "POST"
    , title: '角色数据表'
    , id: "test_four"
    , toolbar: '#toolbarDemo4'
    , defaultToolbar: ['filter']
    , cols: [[
      { field: 'id', title: 'ID', width: 80, sort: true }
      , { field: 'name', title: '角色名', }
      , { field: 'description', title: '角色描述' }
      , { field: 'available', title: '启用/禁止', }
      , { field: 'createTime', title: '创建时间', sort: true }
      , { field: 'updateTime', title: '更新时间', width: 100 }
      , { fixed: 'right', title: '操作', toolbar: '#barDemo4', }
    ]]
    , parseData: function (res) { //res 即为原始返回的数据
      var rows = Object.assign([], res.rows);
      for (let index = 0; index < rows.length; index++) {
        const el = rows[index];
        delete el.sysRole;
        el.createTime = utc2beijing(el.createTime);
        el.updateTime = utc2beijing(el.updateTime);
        el.available = "" + el.available;
      }
      return {
        "code": 0, //解析接口状态
        "msg": "成功", //解析提示文本
        "count": res.total, //解析数据长度
        "data": rows//解析数据列表
      };
    }
  });
  //头工具栏事件
  table.on('toolbar(test_four)', function (obj) {
    var checkStatus = table.checkStatus(obj.config.id);
    switch (obj.event) {
      case 'addNew_4':
        INIT_DATA = {};
        INIT_DATA = {
          id: null,
          isAddOrEdit: "add",
          name: '',
          description: '',
          available: false
        }
        INDEX_OPEN = layer.open({
          type: 1,
          title: "新增角色",
          content: $('#testForm_four'),
          area: ["400px", "400px"],
          success: function (layero, index) {
            // console.log(layero, index);
            form.val("testForm_form-four", INIT_DATA);
          }
        })
        break;
    };
  });
  //监听行工具事件
  table.on('tool(test_four)', function (obj) {
    var data = obj.data;
    //console.log(obj)
    switch (obj.event) {
      case 'edit_4':
        INIT_DATA = {};
        INIT_DATA = {
          isAddOrEdit: "edit",
          id: data.id,
          name: data.name,
          code: data.code
        }
        INDEX_OPEN = layer.open({
          type: 1,
          title: "编辑角色",
          content: $('#testForm_four'),
          area: ["400px", "300px"],
          success: function (layero, index) {
            // console.log(layero, index);
            form.val("testForm_form-four", INIT_DATA);
          }
        })
        break;
      case 'del_4':

        layer.confirm("是否删除?", function (index) {

          $.ajax({
            url: '/roles/remove/',
            type: 'POST',
            data: {
              'ids': data.id
            },
            success: function (rep) {
              if (rep.status == 200) {
                //关闭
                layer.close(index);
                layer.msg('删除成功！', { icon: 1 });
                table.reload('test_four', {
                  page: {
                    curr: 1 //重新从第 1 页开始
                  }
                  , where: {}
                }, 'data');
              } else {
                layer.msg(rep.message, { icon: 2 });
              }
            },
            error: function (error) {
              layer.msg("错误:" + error)
            }
          });

        });
        break;
    }
  });

  // 保存
  $(document).on('click', "#updateNode_4", function () {

    let data = form.val("testForm_form-four");

    if (!data.name.trim()) {
      layer.msg("请填写角色名！");
      return false;
    }

    if (data.available == '') {
      layer.msg("请选择是否启用！");
      return false;
    }


    if (data.isAddOrEdit == "add") {
      let add_data = Object.assign({}, data);
      delete add_data.isAddOrEdit;
      delete add_data.id;
      $.ajax({
        url: "/roles/add",
        type: "POST",
        data: add_data,
        success: function (rep) {
          if (rep.status == 200) {

            layer.close(INDEX_OPEN);
            layer.msg('新增成功！');
            table.reload('test_four', {}, 'data');
          } else {
            layer.msg(rep.message);
          }
        },
        error: function (error) {
          layer.msg("错误:" + error)
        }
      })
    } else if (data.isAddOrEdit == "edit") {
      let edit_data = Object.assign({}, data);
      delete edit_data.isAddOrEdit;
      $.ajax({
        url: "/roles/edit",
        type: "POST",
        data: edit_data,
        success: function (rep) {
          if (rep.status == 200) {

            layer.close(INDEX_OPEN);
            layer.msg('编辑成功！');
            table.reload('test_four', {}, 'data');
          } else {
            layer.msg(rep.message);
          }
        },
        error: function (error) {
          layer.msg("错误:" + error)
        }
      })
    }


    return false;
  })

  // 重置
  
  $(document).on('click', '#resetNode_4', function () {
    layer.confirm("是否重置?", function (index) {
      form.val("testForm_form-four", INIT_DATA);
      layer.close(index)
    });
    return false;
  })

});
})()