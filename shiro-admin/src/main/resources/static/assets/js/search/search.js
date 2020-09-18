(function () {
    var $li = $(".iframe_left_list li");
    var $right_iframe = $(".right-iframe");
    for (let index = 0; index < $li.length; index++) {
        $right_iframe.eq(index).css("display", "none")
    }

    for (let index = 0; index < $li.length; index++) {
        const el = $li[index];
        el.onclick = function () {
            for (let index = 0; index < $li.length; index++) {
                let li_alone = $li.eq(index);
                li_alone.removeClass("hover-li");
            }
            $(el).addClass("hover-li");
            for (let index = 0; index < $li.length; index++) {
                $right_iframe.eq(index).css("display", "none")
            }

            $right_iframe.eq(index).css("display", "inline-block")
        }
    }
    $li.eq(0).click();
})();



layui.use(['form', 'table', "layer", 'jquery'], function () {
    var form = layui.form;
    var layer = layui.layer;
    var $ = layui.jquery;
    var table =layui.table;

    let keys_cell = $("#keys [name='keys']").next().children().eq(1);
    // console.log(keys.find(".layui-this").attr("lay-value"));
    let isInclude_cell = $("#isInclude [name='isInclude']").next().children().eq(1);
    let keyValue_cell = $("#keyValue [name='keyValue']").eq(0);
    let textarea_main = $("#textarea_main");
    let where = "";
    let show_where = "";
    var last_where_value = [];
    var last_show_where_value = [];

    $("#direct_search").on("click", function () {
        let keys = keys_cell.find(".layui-this").attr("lay-value");
        let isInclude = isInclude_cell.find(".layui-this").attr("lay-value");
        let keyValue = keyValue_cell.val();
        if (!keyValue) {
            layer.msg("请输入关键词");
            return false;
        }
        let str = "";
        switch (isInclude) {
            case "0":
                str = keys + " LIKE " + "'%" + keyValue + "%'";
                break;
            case "1":
                str = keys + " LIKE " + "'" + keyValue + "'";
                break;
            case "2":
                str = keys + "NOT LIKE " + "'%" + keyValue + "%'";
                break;
            default:
                layer.msg("不正确的关系！")
                return false;
        }
        console.log({
            param: str
        });
        sessionStorage.setItem("where",str);
        $.ajax({
            url: "/file/fullSearch",
            type: "POST",
            data: {
                param: str
            },
            success: function (rep) {
                if (rep.total) {

                    $(".iframe_left_list li").eq(1).click();
                    table.reload("top_table", {

                        url: "/file/fullSearch",
                        method:"POST",
                        where:{
                            param:sessionStorage.getItem("where")
                        }

                    })

                } else {
                    layer.msg("未找到无数据");
                }
            },
            error: function (error) {
                layer.msg("错误:" + error);
            }
        })
        return false;
    })


    $("#add_condition").on("click", function () {
        let keys = keys_cell.find(".layui-this").attr("lay-value");
        let keys_name = keys_cell.find(".layui-this").text();
        let isInclude = isInclude_cell.find(".layui-this").attr("lay-value");
        let isInclude_name = isInclude_cell.find(".layui-this").text();
        let keyValue = keyValue_cell.val();
        if (!keyValue) {
            layer.msg("请输入关键词");
            return false;
        }
        let str = "";
        let show_str = "";
        switch (isInclude) {
            case "0":
                str = keys + " LIKE " + "'%" + keyValue + "%'";
                break;
            case "1":
                str = keys + " LIKE " + "'" + keyValue + "'";
                break;
            case "2":
                str = keys + " NOT LIKE " + "'%" + keyValue + "%'";
                break;
            default:
                layer.msg("不正确的关系！");
                return false;
        }

        show_str = keys_name + isInclude_name + keyValue;

        last_where_value.push(" " + str + " ");
        last_show_where_value.push(" " + show_str);

        textarea_main.val(show_where + " " + show_str);
        show_where = textarea_main.val();
        where = where + " " + str + " ";


        return false;
    })

    $("#button__and").on("click", function () {
        textarea_main.val(show_where + " 并且 ");
        show_where = textarea_main.val();
        where = where + " AND ";

        last_where_value.push(" AND ");
        last_show_where_value.push(" 并且 ");

    })
    $("#button__or").on("click", function () {
        textarea_main.val(show_where + " 或者 ");
        show_where = textarea_main.val();
        where = where + " OR ";

        last_where_value.push(" OR ");
        last_show_where_value.push(" 或者 ");
    })
    $("#button__not").on("click", function () {
        textarea_main.val(show_where + " 非 ");
        show_where = textarea_main.val();
        where = where + " NOT ";

        last_where_value.push(" NOT ");
        last_show_where_value.push(" 非 ");
    })
    $("#button__left").on("click", function () {
        textarea_main.val(show_where + " ( ");
        show_where = textarea_main.val();
        where = where + " ( ";

        last_where_value.push(" ( ");
        last_show_where_value.push(" ( ");
    })
    $("#button__right").on("click", function () {
        textarea_main.val(show_where + " ) ");
        show_where = textarea_main.val();
        where = where + " ) ";

        last_where_value.push(" ) ");
        last_show_where_value.push(" ) ");
    })

    $("#empty").on("click", function () {
        // console.log(where);
        textarea_main.val('');
        where = "";
        show_where = "";
        return false;
    })

    $("#delete_last").on("click", function () {

        if (!last_show_where_value.length || !last_where_value.length) {
            return false;
        }
        let show_where_len = show_where.length;
        let where_len = where.length;
        let last_where_value_len = last_where_value[last_where_value.length - 1].length;
        let last_show_where_value_len = last_show_where_value[last_show_where_value.length - 1].length;


        show_where = show_where.slice(0, show_where_len - last_show_where_value_len);
        where = where.slice(0, where_len - last_where_value_len );


        textarea_main.val(show_where);

        return false;
    })

    $("#multi_search").on("click", function () {



        sessionStorage.setItem("where",where);
        $.ajax({
            url: "/file/fullSearch",
            type: "POST",
            data: {
                param: where
            },
            success: function (rep) {
                if (rep.total) {

                    $(".iframe_left_list li").eq(1).click();
                    table.reload("top_table", {
                        url: "/file/fullSearch",
                        method:"POST",
                        where:{
                            param:sessionStorage.getItem("where")
                        }
                    })

                } else {
                    layer.msg("错误:" + "未找到无数据")
                }
            },
            error: function (error) {
                layer.msg("错误:" + error)
            }
        })

        return false;
    })

});

