
(function () {
  if (!document.getElementById('users')) return false;
layui.use(['form', 'table', 'layer', 'jquery'], function () {
  var form = layui.form;
  var table = layui.table;
  var $ = layui.jquery;
  var layer = layui.layer;

  table.render({
    elem: '#test_three'
    , url: '/user/list'
    , method: "POST"
    , title: '用户数据表'
    , id: "test_three"
    , toolbar: '#toolbarDemo3'
    , defaultToolbar: ['filter']
    , cols: [[
      { type: 'radio' }
      , { field: 'id', title: 'ID', width: 80, sort: true }
      , { field: 'username', title: '用户名' }
      , { field: 'password', title: '用户密码', }
      , { field: 'userType', title: '角色类型' }
      , { field: 'mobile', title: '手机号' }
      , { field: 'dept', title: '所属部门' }
      , { field: 'org', title: '所属机构' }
      , { field: 'createTime', title: '创建时间', sort: true }
      , { field: 'updateTime', title: '更新时间', width: 100 }
      , { fixed: 'right', title: '操作', toolbar: '#barDemo3', }
    ]]
    , parseData: function (res) { //res 即为原始返回的数据
      // console.log(res)
      var rows = Object.assign([], res.rows);
      for (let index = 0; index < rows.length; index++) {
        const el = rows[index];
        delete el.sysUser;
        el.createTime = utc2beijing(el.createTime);
        el.updateTime = utc2beijing(el.updateTime);
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
  table.on('toolbar(test_three)', function (obj) {
    var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
    switch (obj.event) {
      case 'addNew_3':
        INIT_DATA = {};
        INIT_DATA = {
          id: null,
          isAddOrEdit: "add",
          username: '',
          password: '',
          userType: '',
          mobile: '',
          dept: '',
          org: ''
        }

        $.ajax({
          url: '/org/simple/list',
          type: 'POST',
          success: function (rep) {
            if (rep.status == 200) {
              // 拼接字符串
              let selectDom = $("#testForm-three__org")[0];
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
                title: "新增用户",
                content: $('#testForm_three'),
                area: ["500px", "500px"],
                success: function (layero, index) {
                  // console.log(layero, index);
                  form.val("testForm_form-three", INIT_DATA);
                }
              })

            } else {
              layer.msg("错误：" + rep.msg);
            }
          },
          error: function (error) {
            layer.msg("错误：" + error);
          }
        });

        break;
      case "selectRole_3":
        var data = checkStatus.data;  //获取选中行数据
        if (!data.length) {
          layer.msg("请选择用户");
          return false;
        }
        INIT_DATA = {};
        INIT_DATA = {
          userId: data[0].id,
          roleIds: ""
        };

        $.ajax({
          url: '/roles/list',
          type: 'POST',
          success: function (rep) {
            if (rep.total) {
              // 拼接字符串
              let selectDom = $("#three-select__role")[0];
              // console.log(selectDom);
              selectDom.innerHTML = "";
              selectDom.innerHTML = '<option value=""></option>';
              let roleData = rep.rows;
              for (let index = 0; index < roleData.length; index++) {
                const el = roleData[index];
                selectDom.innerHTML += `<option value="${el.id}">${el.name}</option>`
              }
              INDEX_OPEN = layer.open({
                type: 1,
                title: "修改用户角色",
                content: $('#testForm_three_select'),
                area: ["300px", "200px"],
                success: function (layero, index) {
                  // console.log(layero, index);
                  form.val("testForm_form-three_select", INIT_DATA);
                }
              })

            } else {
              layer.msg("错误：" + rep.msg);
            }
          },
          error: function () {
            layer.msg("错误：" + error)
          }
        })

        break;
    };
  });

  //监听行工具事件
  table.on('tool(test_three)', function (obj) {
    var data = obj.data;
    //console.log(obj)
    switch (obj.event) {
      case 'edit_3':
        INIT_DATA = {};
        INIT_DATA = {
          isAddOrEdit: "edit",
          id: data.id,
          username: data.username,
          password: data.password,
          userType: data.userType,
          mobile: data.mobile,
          dept: data.dept,
          org: data.org
        }

        let orgId_tmp =  data.org;

        $.ajax({
          url: '/org/simple/list ',
          type: 'POST',
          success: function (rep) {
            if (rep.status == 200) {
              // 拼接字符串
              let selectDom = $("#testForm-three__org")[0];
              // console.log(selectDom);
              selectDom.innerHTML = "";
              selectDom.innerHTML = '<option value=""></option>';
              let data = rep.data;
              for (let index = 0; index < data.length; index++) {
                const el = data[index];
                selectDom.innerHTML += `<option value="${el.id}">${el.name}</option>`
              }

              $.ajax({
                url: "/dept/org/" + orgId_tmp,
                type: "POST",
                success: function (rep) {
                  if (rep.status == 200) {
                    let selectDom = $("#testForm_three [name='dept']")[0];
                    // console.log(selectDom);
                    selectDom.innerHTML = "";
                    selectDom.innerHTML = '<option value="">无</option>';
                    let data = rep.data;
                    for (let index = 0; index < data.length; index++) {
                      const el = data[index];
                      selectDom.innerHTML += `<option value="${el.id}">${el.name}</option>`
                    }
                    form.render("select");
                    INDEX_OPEN = layer.open({
                      type: 1,
                      title: "编辑用户",
                      content: $('#testForm_three'),
                      area: ["500px", "600px"],
                      success: function (layero, index) {
                        // console.log(layero, index);
                        form.val("testForm_form-three", INIT_DATA);
                      }
                    })
                  } else {
                    layer.msg(rep.message);
                  }
                },
                error: function (error) {
                  layer.msg("错误:" + error)
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
      case 'del_3':

        layer.confirm("是否删除?", function (index) {

          $.ajax({
            url: '/user/remove',
            type: 'POST',
            data: { 'ids': data.id },
            success: function (rep) {
              if (rep.status == 200) {
                //关闭
                layer.close(index);
                layer.msg('删除成功！', { icon: 1 });
                table.reload('test_three', {}, 'data');
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
  $(document).on('click', "#updateNode_3", function () {
    let data = form.val("testForm_form-three");
    if (!/^[a-zA-Z][a-zA-Z0-9_]{4,15}$/.test(data.username)) {
      layer.msg("请填写用户名,5——16位");
      return false;
    }

    if (!data.password) {
      layer.msg("密码不能为空");
      return false;
    }
    if (!data.userType) {
      layer.msg("请选择角色类型");
      return false;
    }
    if (!data.org) {
      layer.msg("请选择机构！");
      return false;
    }

    if (!data.dept) {
      layer.msg("请选择部门");
      return false;
    }

    if (data.isAddOrEdit == "add") {
      let add_data = Object.assign({}, data);
      delete add_data.isAddOrEdit;
      delete add_data.id;
      $.ajax({
        url: "/user/add",
        type: "POST",
        data: add_data,
        success: function (rep) {
          if (rep.status == 200) {

            layer.close(INDEX_OPEN);
            layer.msg('新增成功！');
            table.reload('test_three', {}, 'data');
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
        url: "/user/edit",
        type: "POST",
        data: edit_data,
        success: function (rep) {
          if (rep.status == 200) {

            layer.close(INDEX_OPEN);
            layer.msg('编辑成功！');
            table.reload('test_three', {}, 'data');
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

  // 保存
  $(document).on('click', "#three-save__role", function () {
    let data = form.val("testForm_form-three_select");
    if (data.userId != INIT_DATA.userId) {
      layer.msg("错误的用户id");
      return false;
    }
    if (!data.roleIds) {
      layer.msg("请选择角色类型");
      return false;
    }

    $.ajax({
      url: "/user/saveUserRoles",
      type: "POST",
      data: data,
      success: function (rep) {
        if (rep.status == 200) {

          layer.close(INDEX_OPEN);
          layer.msg('修改用户角色成功！');

        } else {
          layer.msg(rep.message);
        }
      },
      error: function (error) {
        layer.msg("错误:" + error)
      }
    })

    return false;
  })


  // 级联下拉框
  $(document).on('blur', "#testForm-three__org_cell", function () {
    let orgId = $("#testForm-three__org_cell .layui-this").attr("lay-value");

    if (!orgId) {
      return;
    }
    $.ajax({
      url: "/dept/org/" + orgId,
      type: "POST",
      success: function (rep) {
        if (rep.status == 200) {
          let selectDom = $("#testForm_three [name='dept']")[0];
          // console.log(selectDom);
          selectDom.innerHTML = "";
          selectDom.innerHTML = '<option value="">无</option>';
          let data = rep.data;
          for (let index = 0; index < data.length; index++) {
            const el = data[index];
            selectDom.innerHTML += `<option value="${el.id}">${el.name}</option>`
          }
          form.render("select");
        } else {
          layer.msg(rep.message);
        }
      },
      error: function (error) {
        layer.msg("错误:" + error)
      }
    })
  })

  // 重置
  $(document).on('click', '#resetNode_3', function () {
    layer.confirm("是否重置?", function (index) {
      form.val("testForm_form-three", INIT_DATA);
      layer.close(index)
    })
  })

});
})()