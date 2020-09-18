(function () {
  if (!document.getElementById('depts')) return false;
layui.use(['form', 'table', 'layer', 'jquery'], function () {
  var form = layui.form;
  var table = layui.table;
  var $ = layui.jquery;
  var layer = layui.layer;

  table.render({
    elem: '#test_two'
    , url: '/dept/list'
    , method: "POST"
    , title: '用户数据表'
    , id: "test_two"
    , toolbar: '#toolbarDemo2'
    , defaultToolbar: ['filter']
    , cols: [[
      { field: 'id', title: 'ID', width: 80, sort: true }
      , { field: 'code', title: '部门代码' }
      , { field: 'name', title: '部门名', }
      , { field: 'orgId_name', title: '所属机构名', }
      , { field: 'createTime', title: '创建时间', sort: true }
      , { field: 'updateTime', title: '更新时间', width: 100 }
      , { field: 'orgId', title: '所属机构ID', hide: true }
      , { fixed: 'right', title: '操作', toolbar: '#barDemo2', }
    ]]
    , parseData: function (res) { //res 即为原始返回的数据
      var result;
      var rows = Object.assign([], res.rows);
      for (let index = 0; index < rows.length; index++) {
        const el = rows[index];
        delete el.sysDept;
        el.createTime = utc2beijing(el.createTime);
        el.updateTime = utc2beijing(el.updateTime);
        el.orgId_name = el.parent.name;
        delete el.parent;
      }
      return {
        "code": 0, //解析接口状态
        "msg": "成功", //解析提示文本
        "count": res.total, //解析数据长度
        "data": rows
      };
    }
  });

  //头工具栏事件
  table.on('toolbar(test_two)', function (obj) {
    var checkStatus = table.checkStatus(obj.config.id);
    switch (obj.event) {
      case 'addNew_2':
        INIT_DATA = {};
        INIT_DATA = {
          id: null,
          isAddOrEdit: "add",
          name: '',
          code: '',
          orgId: ""
        }

        $.ajax({
          url: '/org/simple/list',
          type: 'POST',
          success: function (rep) {
            if (rep.status == 200) {
              // 拼接字符串
              let selectDom = $("#testForm_two [name='orgId']")[0];
              // console.log(selectDom);
              selectDom.innerHTML = "";
              selectDom.innerHTML = '<option value=""></option>';
              let data = rep.data;
              for (let index = 0; index < data.length; index++) {
                const el = data[index];
                selectDom.innerHTML += `<option value="${el.id}">${el.name}</option>`
              }

              INDEX_OPEN = layer.open({
                type: 1,
                title: "新增部门",
                content: $('#testForm_two'),
                area: ["400px", "350px"],
                success: function (layero, index) {
                  // console.log(layero, index);
                  form.val("testForm_form-two", INIT_DATA);
                }
              })

            } else {
              layer.msg("错误：" + rep.msg);
            }
          },
          error: function (error) {
            layer.msg("错误：" + error)
          }
        });

        break;
    };
  });

  //监听行工具事件
  table.on('tool(test_two)', function (obj) {
    var data = obj.data;
    //console.log(obj)
    switch (obj.event) {
      case 'edit_2':
        INIT_DATA = {};
        INIT_DATA = {
          isAddOrEdit: "edit",
          id: data.id,
          name: data.name,
          code: data.code,
          orgId: data.orgId
        }


        $.ajax({
          url: '/org/simple/list',
          type: 'POST',
          success: function (rep) {
            if (rep.status == 200) {
              // 拼接字符串
              let selectDom = $("#testForm_two [name='orgId']")[0];
              // console.log(selectDom);
              selectDom.innerHTML = "";
              selectDom.innerHTML = '<option value=""></option>';
              let data = rep.data;
              for (let index = 0; index < data.length; index++) {
                const el = data[index];
                selectDom.innerHTML += `<option value="${el.id}">${el.name}</option>`
              }


              INDEX_OPEN = layer.open({
                type: 1,
                title: "编辑机构",
                content: $('#testForm_two'),
                area: ["400px", "350px"],
                success: function (layero, index) {
                  // console.log(layero, index);
                  form.val("testForm_form-two", INIT_DATA);
                }
              })

            } else {
              layer.msg("错误：" + rep.msg);
            }
          },
          error: function (error) {
            layer.msg("错误：" + error)
          }
        });

        break;
      case 'del_2':

        layer.confirm("是否删除?", function (index) {

          $.ajax({
            url: '/dept/remove',
            type: 'POST',
            data: { 'ids': data.id },
            success: function (rep) {
              if (rep.status == 200) {
                //关闭
                layer.close(index);
                layer.msg('删除成功！', { icon: 1 });
                table.reload('test_two', {}, 'data');
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
  $(document).on('click', "#updateNode_2", function () {

    let data = form.val("testForm_form-two");

    if (!data.orgId) {
      layer.msg("请选择机构");
      return false;
    }

    if (!data.name.trim()) {
      layer.msg("请填写部门名称！");
      return false;
    }

    if (!data.code.trim()) {
      layer.msg("请填写部门代码！");
      return false;
    }


    if (data.isAddOrEdit == "add") {
      let add_data = Object.assign({}, data);
      delete add_data.isAddOrEdit;
      delete add_data.id;

      $.ajax({
        // url: "/dire",
        url: "/dept/add",
        type: "POST",
        data: add_data,
        success: function (rep) {
          if (rep.status == 200) {

            layer.close(INDEX_OPEN);
            layer.msg('新增成功！');
            table.reload('test_two', {}, 'data');
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
        // url: "/dire",
        url: "/dept/edit",
        type: "POST",
        data: edit_data,
        success: function (rep) {
          if (rep.status == 200) {

            layer.close(INDEX_OPEN);
            layer.msg('编辑成功！');
            table.reload('test_two', {}, 'data');
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
  $(document).on('click', '#resetNode_2', function () {
    layer.confirm("是否重置?", function (index) {
      form.val("testForm_form-two", INIT_DATA);
      layer.close(index)
    });
    return false;
  })

});
})()