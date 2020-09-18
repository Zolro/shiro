layui.use(['table', 'jquery', 'layer', 'form',], function () {
    var table = layui.table;
    var form = layui.form;
    var layer = layui.layer;
    var $ = layui.jquery;

    var renderTable_top = table.render({
        elem: '#test'
        , height: "full"
        , toolbar: '#toolbarDemo'
        , defaultToolbar: ['filter', 'print']
        // , where : {
        //     param: sessionStorage.getItem("where")
        // }
        , id: "top_table"
        , data: []
        , parseData: function (res) { //res 即为原始返回的数据
            return {
                "code": res.status ? res.status : 0, //解析接口状态
                "msg": "成功", //解析提示文本
                "count": res.total, //解析数据长度
                "data": res.rows //解析数据列表
            };
        }
        , cols: [[
            { field: 'id', width: 80, title: 'ID', sort: true, hide: true }
            , { field: 'fileNumber', width: 180, title: '档号', sort: true }
            , { field: 'fondName', title: '全宗名称' }
            , { field: 'fileTitle', title: '文件标题' }
            , { field: 'categoryName', title: '分类名称', hide: true }
            , { field: 'fileCategory', title: '档案类别', hide: true }
            , { field: 'storagePeriodCode', title: '保管期限代码', hide: true }
            , { field: 'secretLevel', title: '密级', hide: true }
            , { field: 'organization', title: '组织机构', hide: true }
            , { field: 'remarks', title: '备注', hide: true }
            , { field: 'storageLocation', title: '存放位置', hide: true }
            , { field: 'licenseNumber', title: '登记号', hide: true }
            , { field: 'carriersNumber', title: '载体数量', hide: true }
            , { field: 'fondNumber', title: '全宗号', hide: true }
            , { field: 'filesNumber', title: '案卷号', hide: true }
            , { field: 'boxNumber', title: '盒号', hide: true }
            , { field: 'annex', title: '附件', hide: true }
            , { field: 'problem', title: '问题', hide: true }
            , { field: 'storageTime', title: '保管期限', hide: true, sort: true }
            , { field: 'categoryNumber', title: '分类号', hide: true }
        ]]
        , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            limit: 10 //一页显示多少条
            , limits: [5, 10, 15, 20, 25, 30]//每页条数的选择项
            , last: "尾页" //不显示尾页
        }
    });

    //头工具栏事件
    table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'search_Test':

                let tr_arr = $("[lay-id='top_table'] .layui-table-body.layui-table-main tr");
                // console.log(tr_arr);
                let search_value = $("#test_Reload__input").val();
                search_value = search_value.trim();
                // console.log(tr_arr,search_value)

                for (let index = 0; index < tr_arr.length; index++) {

                    let text_arr = [];

                    let tr_index = tr_arr.eq(index);

                    for (let inner = 0; inner < tr_index.children().length; inner++) {
                        let td_index = tr_index.children().eq(inner).text();
                        td_index = td_index.trim();
                        text_arr.push(td_index);
                    }

                    // console.log(text_arr);
                    let have = false;
                    for (let index = 0; index < text_arr.length; index++) {
                        const text = text_arr[index];


                        if (text.indexOf(search_value) > -1) {
                            have = true;
                        }

                        if (index + 1 == text_arr.length && !have) {
                            tr_index.css("display", "none")
                        } else {
                            tr_index.css("display", "")
                        }
                    }
                }

                break;
            case 'showAll':
                layer.confirm("是否显示全部？", { icon: 3, title: '提示' },
                    function (index) {
                        let tr_arr = $("[lay-id='top_table'] .layui-table-body.layui-table-main tr");
                        for (let index = 0; index < tr_arr.length; index++) {
                            const tr_index = tr_arr.eq(index);
                            tr_index.css("display", "")
                        }
                        layer.close(index);
                    }
                );
                break;

        };
    });


});