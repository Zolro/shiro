<#assign model = "mangers">
<#assign title = "档案管理">
<#include "/layout/header.ftl"/>
<div class="iframe_box">
    <div class="hookup_bg" style="display: none;">
        <div class="hookup_box">
            <div class="hookup_top">
                <span>数据导入</span>
                <div class="hookup_close">X</div>
            </div>
            <div class="exl_name">
                <form id="uploadForm" enctype="multipart/form-data">
                    <input type="file" name="file" id="file" multiple="multiple" />
                </form>
            </div>

            <div class="file_table">
                <table>
                    <tr>
                        <th>文件名</th>
                        <th>状态</th>
                    </tr>
                    <tbody  id="info_box"></tbody>
                </table>
            </div>

            <div class="hookup_btm">
                <div class="hookup_close_btn">取消</div>
                <div class="hookup_new_btn" onclick="folder_upload()">确定导入</div>
            </div>
        </div>
    </div>
    <div class="iframe_left file_iframe_left">
        <div class="iframe_left_list">
            <div class="list_top">档案库列表</div>
            <ul id="title">

            </ul>
        </div>
    </div>

    <div class="iframe_right file_iframe_right file1">
        <div class="iframe_right_top">
            <div class="right_top_left">
                <span class="folder_name" id="fileType"></span><span>-</span><span class="file_name" id="dire"></span>
                <input type="text" name="search" value="${param}">
                <div class="search_btn right_btn" style="float: left;" id="search">检索</div>
            </div>
            <div class="right_top_right">
                <div class="right_btn" onclick="folder()">档案挂接</div>
                <div class="right_btn" onclick="upload()">目录上传</div>
            </div>
        </div>
        <div class="iframe_right_list" id="file_content">
            <table id="table">

            </table>
        </div>
        <div class="iframe_right_page">
            <!-- 分页放在这里 -->
            <div class="page" id="Page"></div>
        </div>


        <div class="upload_bg" style="display: none;">
            <div class="upload_box">
                <div class="upload_top">
                    <span>数据导入</span>
                    <div class="upload_close">X</div>
                </div>
                <div class="exl_name">
                    <input type="file" id="upload"  onchange="importf(this)">
                </div>
                <div class="exl_name">
                    <select id="uploadType">
                        <option value="0">文件</option>
                        <option value="1">案卷</option>
                    </select>
                </div>
                <div class="exl_content">
                    <table>
                        <tr>
                            <th>档案目录字段</th>
                            <th>导入文件字段</th>
                        </tr>
                        <tr>
                            <td>档号</td>
                            <td class="td td1"></td>
                        </tr>
                        <tr>
                            <td>分类名称</td>
                            <td class="td td2"></td>
                        </tr>
                        <tr>
                            <td>档案类别</td>
                            <td class="td td3"></td>
                        </tr>
                        <tr>
                            <td>全宗名称</td>
                            <td class="td td4"></td>
                        </tr>
                        <tr>
                            <td>保管期限代码</td>
                            <td class="td td5"></td>
                        </tr>
                        <tr>
                            <td>密级</td>
                            <td class="td td6"></td>
                        </tr>
                        <tr>
                            <td>文件标题</td>
                            <td class="td td7"></td>
                        </tr>
                        <tr>
                            <td>组织机构</td>
                            <td class="td td8"></td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td class="td td9"></td>
                        </tr>
                        <tr>
                            <td>存放位置</td>
                            <td class="td td10"></td>
                        </tr>
                        <tr>
                            <td>登记号</td>
                            <td class="td td11"></td>
                        </tr>
                        <tr>
                            <td>载体数量</td>
                            <td class="td td12"></td>
                        </tr>
                        <tr>
                            <td>全宗号</td>
                            <td class="td td13"></td>
                        </tr>
                        <tr>
                            <td>案卷号</td>
                            <td class="td td14"></td>
                        </tr>
                        <tr>
                            <td>盒号</td>
                            <td class="td td15"></td>
                        </tr>
                        <tr>
                            <td>责任者</td>
                            <td class="td td16"></td>
                        </tr>
                        <tr>
                            <td>附件</td>
                            <td class="td td17"></td>
                        </tr>
                        <tr>
                            <td>张页号</td>
                            <td class="td td18"></td>
                        </tr>
                        <tr>
                            <td>问题</td>
                            <td class="td td19"></td>
                        </tr>
                        <tr>
                            <td>保管期限</td>
                            <td class="td td20"></td>
                        </tr>
                        <tr>
                            <td>主题词</td>
                            <td class="td td21"></td>
                        </tr>
                        <tr>
                            <td>附注</td>
                            <td class="td td22"></td>
                        </tr>
                        <tr>
                            <td>年度</td>
                            <td class="td td23"></td>
                        </tr>
                        <tr>
                            <td>件号</td>
                            <td class="td td24"></td>
                        </tr>
                        <tr>
                            <td>卷内顺序号</td>
                            <td class="td td25"></td>
                        </tr>
                        <tr>
                            <td>分类号</td>
                            <td class="td td26"></td>
                        </tr>
                        <tr>
                            <td>成文日期</td>
                            <td class="td td27"></td>
                        </tr>
                        <tr>
                            <td>页数</td>
                            <td class="td td28"></td>
                        </tr>
                        <tr>
                            <td>目录号</td>
                            <td class="td td29"></td>
                        </tr>
                        <tr>
                            <td>文件级档号</td>
                            <td class="td td30"></td>
                        </tr>
                    </table>
                </div>
                <div class="upload_btm">
                    <div class="upload_close_btn">取消</div>
                    <div class="upload_new_btn" onclick="submit()">确定导入</div>
                </div>
            </div>
        </div>
        <div id="case_div" style="display:none;">
            <div class="iframe_right_list" id="case_content">
                <table>



                </table>
            </div>
            <div class="iframe_right_page">
                <!-- 分页放在这里 -->
                <div class="page" id="Page"></div>
            </div>
        </div>
    </div>

</div>
<iframe src="" id="pdf_iframe" style="height:100%;width:100%;position:absolute;z-index:999;bottom:0;display:none;"></iframe>
<div class="close_btn" id="close_btn"></div>

<#include "/layout/booter.ftl"/>
<script type="text/javascript" src='/assets/js/jquery-3.5.1.min.js'></script>
<script type="text/javascript" src='/assets/js/page.js?v=1.0.6'></script>
<script type="text/javascript" src='/assets/js/filePage.js?v=2'></script>
<script type="text/javascript" src='/assets/js/tooles.js?v=1.0.9'></script>
<script type="text/javascript" src='/assets/js/xlsx.full.min.js'></script>

<script>
    loadStyles("/assets/css/file.css?v=2.6");
    loadStyles("/assets/css/upload.css");
    loadStyles("/assets/css/hookup.css?v=1.0");
    function upload() {
        if(!direId){
            alert("请先选择目录！");
            return;
        }
        $(".upload_bg").show();

    }
</script>
<script>
    var wb;//读取完成的数据
    var rABS = false; //是否将文件读取为二进制字符串
    function importf(obj) {
        console.log('1');
        if(!obj.files) {
            return;
        }
        var f = obj.files[0];
        var reader = new FileReader();
        reader.onload = function(e) {
            if(rABS) {
                wb = XLSX.read(btoa(fixdata(e.target.result)), {
                    type: 'base64'
                });
            } else {
                wb = XLSX.read(e.target.result, {
                    type: 'binary'
                });
            }
            var data = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]);
            var keyAry = [];
            for(var key in data[1]){
                keyAry.push(key);
            }
            var select_title = '<option value="null">无</option>';
            var num = 0;
            $.each(keyAry,function(){
                select_title += "<option value="+num+">"+keyAry[num]+"</option>";
                num++;
            })
            console.log(select_title);
            $.each($('.td'),function(){
                $(this).html("<select>"+select_title +"</select>");
            })
        }
        if(rABS) {
            reader.readAsArrayBuffer(f);
        } else {
            reader.readAsBinaryString(f);
        }
    }

    function fixdata(data) { //文件流转BinaryStrings
        var o = "",
            l = 0,
            w = 10240;
        jsArry=[];
        for(; l < data.byteLength / w; ++l) jsArry.push(String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w, l * w + w))));
        return jsArry;
    }
    function submit(){
        let optionLen =$('option').length;
        if(optionLen==0){
            alert("请导入excel文件!");
            return;
        }
        let fields="";
        $.each($('table option:selected'),function(){
            if($(this).val()!="null"){
                let cls = $(this).parent().parent('.td').attr("class");
                let td = cls.substr(5,cls.length);
                fields+=td+","+$(this).html()+"|";
            }

        });
        console.log(fields);
        fields = fields.substr(0, fields.length - 1);
        let formData = new FormData();
        let file = document.getElementById("upload").files[0];
        formData.append("file", file);
        formData.append("direId", direId);
        formData.append("status",$("#uploadType").val());
        formData.append("fields", fields);
        $.ajax({
            type: "POST",
            url: "/file/import",
            data:formData,
            processData:false,
            contentType:false,
            async:false,
            success: function (json) {
                $(".upload_bg").hide();
            }
        });
    }
    function folder_upload() {
        var files = document.getElementById('file').files;
        let formData = new FormData();
        for (var i = 0, len = files.length; i < len; i++) {
            formData.append("files",files[i]);
        };
        $.ajax({
            type: 'POST',
            url: "/file/folder/import",
            data:formData,
            processData: false,
            contentType: false,
            cache: false,
            success: function (ret) {
                $(".hookup_bg").hide();
                alert(ret.message);
            }
        });
    }
    $(".hookup_close").click(function () {
        $(".hookup_bg").hide();
    })
    function folder() {
        $(".hookup_bg").show();
    }
</script>    
<script type="text/javascript">
    var fileDom = document.getElementById("file");
    var infoBoxDom = document.getElementById('info_box');

    fileDom.addEventListener("change", uploadEvent, false);
    function uploadEvent(){
        infoBoxDom.innerHTML = '';
        var list = this.files;
        for( var i = 0 ; i < list.length ; i++ ){
            infoBoxDom.innerHTML += "<tr><td>"+list[i].name+"</td><td>待上传</td></tr>";
        }
        //infoBoxDom.innerHTML += list.length+'个文件';
    }
</script>

