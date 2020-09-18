
(function () {
  if (!document.getElementById('orgs')) return false;
  layui.use(['form', 'table', 'layer', 'jquery'], function () {
    var form = layui.form;
    var table = layui.table;
    var $ = layui.jquery;
    var layer = layui.layer;

    table.render({
      elem: '#test_one'
      , url: '/org/list'
      , method: "POST"
      , title: '用户数据表'
      , id: "test_one"
      , toolbar: '#toolbarDemo'
      , defaultToolbar: ['filter']
      , cols: [[
        { field: 'id', title: 'ID', width: 80, sort: true }
        , { field: 'code', title: '机构代码' }
        , { field: 'name', title: '机构名', }
        , { field: 'createTime', title: '创建时间', sort: true }
        , { field: 'updateTime', title: '更新时间', width: 100 }
        , { fixed: 'right', title: '操作', toolbar: '#barDemo', }
      ]]
      , parseData: function (res) { //res 即为原始返回的数据
        var rows = Object.assign([], res.rows);
        for (let index = 0; index < rows.length; index++) {
          let el = rows[index];
          delete el.sysOrg;
          el.createTime = utc2beijing(el.createTime);
          el.updateTime = utc2beijing(el.updateTime);
        }
        // console.log(rows)
        return {
          "code": 0, //解析接口状态
          "msg": "成功", //解析提示文本
          "count": res.total, //解析数据长度
          "data": rows
        };
      }
    });

    //头工具栏事件
    table.on('toolbar(test_one)', function (obj) {
      switch (obj.event) {
        case 'addNew_1':
          INIT_DATA = {};
          INIT_DATA = {
            id: null,
            isAddOrEdit: "add",
            name: '',
            code: ''
          }
          INDEX_OPEN = layer.open({
            type: 1,
            title: "新增机构",
            content: $('#testForm_one'),
            area: ["400px", "300px"],
            success: function (layero, index) {
              // console.log(layero, index);
              form.val("testForm_form-one", INIT_DATA);
            }
          })
          break;
      };
    });

    //监听行工具事件
    table.on('tool(test_one)', function (obj) {
      var data = obj.data;
      //console.log(obj)
      switch (obj.event) {
        case 'edit_1':
          INIT_DATA = {};
          INIT_DATA = {
            isAddOrEdit: "edit",
            id: data.id,
            name: data.name,
            code: data.code
          }
          INDEX_OPEN = layer.open({
            type: 1,
            title: "编辑机构",
            content: $('#testForm_one'),
            area: ["400px", "300px"],
            success: function (layero, index) {
              // console.log(layero, index);
              form.val("testForm_form-one", INIT_DATA);
            }
          })
          break;
        case 'del_1':
          // console.log(data.id)
          console.log({ 'ids': [data.id] });
          layer.confirm("是否删除?", function (index) {

            $.ajax({
              // url: '/dire/' + data.id,
              url: "/org/remove",
              type: 'POST',
              data: { 'ids': data.id },
              success: function (rep) {
                if (rep.status == 200) {
                  //关闭
                  layer.close(index);
                  layer.msg('删除成功！', { icon: 1 });
                  table.reload('test_one', {}, 'data');
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
    $(document).on('click', "#updateNode_1", function () {
      let data = form.val("testForm_form-one");

      if (!data.code.trim()) {
        layer.msg("请填写部门代码！");
        return false;
      }
      if (!data.name.trim()) {
        layer.msg("请填写部门名称！");
        return false;
      }


      if (data.isAddOrEdit == "add") {
        let add_data = Object.assign({}, data);

        delete add_data.isAddOrEdit;
        delete add_data.id;

        $.ajax({
          // url: "/dire",
          url: "/org/add",
          type: "POST",
          data: add_data,
          success: function (rep) {
            if (rep.status == 200) {

              layer.close(INDEX_OPEN);
              layer.msg('新增成功！');
              table.reload('test_one', {}, 'data');
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
          url: "/org/edit",
          type: "POST",
          data: edit_data,

          success: function (rep) {
            if (rep.status == 200) {

              layer.close(INDEX_OPEN);
              layer.msg('编辑成功！');
              table.reload('test_one', {}, 'data');
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
    $(document).on('click', '#resetNode_1', function () {
      layer.confirm("是否重置?", function (index) {
        form.val("testForm_form-one", INIT_DATA);
        layer.close(index)
      });
      return false;
    })

  });
})()
