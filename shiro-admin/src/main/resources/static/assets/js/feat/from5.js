(function () {
  if (!document.getElementById('resources')) return false;

  layui.use(['form', 'layer', 'jquery'], function () {
    var form = layui.form;
    var $ = layui.jquery;
    var layer = layui.layer;
    //监听提交


    form.on('submit(updateNode_5)', function (data) {
      console.log(data);
      let formData = data.field;
      let role = formData.user;
      console.log(role);
      let str = "";
      for (let key in formData) {
        if (key == "user") {
          continue;
        }
        str += formData[key] + ",";
      }
      // console.log(str);
      str = str.slice(0, str.length - 1);

      $.ajax({
        // url: "/dire",
        url: "/roles/saveRoleResources",
        type: "POST",
        data: {
          resourcesId: str,
          roleId: role

        },
        success: function (rep) {
          console.log(rep)
          if (rep.status == 200) {

            layer.close(INDEX_OPEN);
            layer.msg('提交成功！');
          } else {
            layer.msg(rep.message);
          }
        },
        error: function (error) {
          layer.msg("错误:" + error)
        }
      })
      return false;
    });

    form.on('select(role_select)', function (data) {
      // console.log(data.elem); //得到select原始DOM对象
      // console.log(data.value); //得到被选中的值
      // console.log(data.othis); //得到美化后的DOM对象

      $.ajax({
        url: "/resources/resourcesWithSelected",
        type: "POST",
        data: {
          rid: data.value
        },
        success: function (rep) {
          if (rep.status == 200) {
            let data = rep.data;
            // console.log(data);
            let check_arr = [];
            for (let index = 0; index < data.length; index++) {
              const el = data[index];
              if (el.checked == "true") {
                check_arr.push(el.id);
              }
            }

            for (let index = 0; index < check_arr.length; index++) {
              const id = check_arr[index];
              var unitTypeCheckbox = $("input[type='checkbox']");
              for (let inner = 0; inner < unitTypeCheckbox.length; inner++) {
                let inner_el = unitTypeCheckbox[inner];
                if (inner_el.value == id) {
                  $(inner_el).next().click();
                }
              }
            }
            form.render();
            form_role_5 = form.val("role_testForm_form");
          } else {
            layer.msg("错误:" + rep.message)
          }
        },
        error: function (error) {
          layer.msg("错误:" + error);
        }
      })
    })

    $(document).on('click', '#resetNode_5', function () {
      layer.confirm("是否重置?", function (index) {
        console.log(form_role_5);

        form.val("role_testForm_form", { user: form_role_5.user })
        var unitTypeCheckbox = $("input[type='checkbox']");
        for (let inner = 0; inner < unitTypeCheckbox.length; inner++) {
          let inner_el = unitTypeCheckbox[inner];
          if ($(inner_el).next().hasClass("layui-form-checked")) {
            $(inner_el).next().click();
          }
        }

        for (let key in form_role_5) {
          if (key == "user") {
            continue;
          }

          $("input[name=" + key + "]").next().click();
        }

        layer.close(index);
      });
      return false;
    })

  });


})()


function reload_form(){
  layui.use(['form'],function(){
    var form = layui.form;
    form.render("select","role_testForm_form");
  })
}

function get_all_role() {
  $.ajax({
    url: '/roles/list',
    method: "POST",
    success: function (rep) {
      let selectDom = $("#role_testForm_form [name='user']")[0];
      // console.log(selectDom);
      selectDom.innerHTML = "";
      selectDom.innerHTML = '<option value=""></option>';
      let data = rep.rows;
      for (let index = 0; index < data.length; index++) {
        const el = data[index];
        selectDom.innerHTML += `<option value="${el.id}">${el.name}</option>`
      }

      reload_form();
    },
    error: function () {
      let selectDom = $("#role_testForm_form [name='user']")[0];
      // console.log(selectDom);
      selectDom.innerHTML = "";
      selectDom.innerHTML = '<option value="">无法获取角色列表</option>';

      reload_form();
    }
  })
}
