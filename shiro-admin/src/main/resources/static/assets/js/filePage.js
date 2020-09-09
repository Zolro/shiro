var num;
var direId;
var fileType;
var type;

getFileType();
/* 初始化目录结构*/
function getFileType(){
    $.ajax({
        type: "GET",
        url: "/fileType/simple",
        dataType: "json",
        success: function (json) {
            if (json.status === 200) {
                let list = json.data;
                content=``;
                for(var i=0;i<list.length;i++){
                    var data = list[i];
                    var id = 'type_'+data["id"];
                    content+=`<li class="list_folder" type="`+data["type"]+`" id=`+id+`>`+data["name"]+`</li>`;
                    if(data["list"].length>0){
                        let arr = data["list"];
                        content+=`<ul class="ul_file">`;
                        for(var j=0;j<arr.length;j++){
                            let obj = arr[j];
                            var id = 'dire_'+obj["id"];
                            content+=`
                                <li class="list_file" id="`+id+`">`+obj["name"]+`</li>`;
                        }
                        content+=`</ul>`;
                    }
                }
                $("#title").html(content);
                initmenu();
            }
        }
    });
}

function initmenu(){
    var afolder = document.getElementsByClassName('list_folder');
    var afile = document.getElementsByClassName('ul_file');
    var file = document.getElementsByClassName('list_file');
    for (var i = 0; i < afolder.length; i++) {
        afolder[i].index = i;
        afolder[i].onclick=function(){
            $("#file_content table").html("");
            $("#Page").hide();
            $("input[name='search']").val("");
            initType(afolder[this.index].getAttribute("id"),afolder[this.index].innerHTML,afolder[this.index].getAttribute("type"));
            if(afile[this.index]){
                for (var j = 0; j < afile.length; j++) {
                    afile[j].style.display = 'none';
                    afile[this.index].style.display = 'block';
                }
                for (var k = 0; k < afolder.length; k++) {
                    afolder[k].style.backgroundImage = 'url(/assets/images/arror_right.png)';
                    afolder[this.index].style.backgroundImage = 'url(/assets/images/arror_down.png)';
                }
            }
        }
    }
    for (var i = 0; i < file.length; i++) {
        file[i].index = i;
        file[i].onclick=function(){
            initDire(file[this.index].getAttribute("id"),file[this.index].innerHTML);
        }
    }
}

function initType(idStr,name,typeId){
    type = typeId;
    fileType = idStr.split('_')[1];
    $("#fileType").text(name);
    $("#dire").text("");
    return type;
}

function initDire(idStr,name){
    direId = idStr.split('_')[1];
    $("#dire").text(name);
    var param = $("input[name='search']").val();
    getDataByDire(param,1);
    initPage(getRows(direId,param),"Page");
    return direId;
}

function getDataByDire(param,pageNum) {
    show(direId,pageNum,param);
}
function show(direId,pageNum,param){
    if(!param){
        param = "";
    }
    $.ajax({
        type: "GET",
        url: "/file/dire/"+direId,
        data:{"pageNumber":pageNum},
        dataType: "json",
        async:false,
        success: function (json) {
            $("#file_content table").html("");
            let list = json.rows;
            let total = json.total;
            if(total>0){
                let content = `<thead><tr>`;
                if(type==0){
                    content += `<td>附件</td>`;
                }
                 content += `              
                <td>档号</td>
                <td>分类名称</td>
                <td>档案类别</td>
                <td>全宗名称</td>              
                <td>保管期限代码</td>
                <td>密级</td>
                <td>文件标题</td>
                <td>组织机构</td>
                <td>备注</td>
                <td>存放位置</td>
                <td>登记号</td>
                <td>载体数量</td>
                <td>全宗号</td>
                <td>案卷号</td>
                <td>盒号</td>
                <td>责任者</td>
                <td>附件</td>
                <td>张页号</td>
                <td>问题</td>
                <td>保管期限</td>
                <td>主题词</td>
                <td>附注</td>
                <td>年度</td>
                <td>件号</td>
                <td>卷内顺序号</td>
                <td>分类号</td>
                <td>成文日期</td>
                <td>页数</td>
                <td>目录号</td>              
                `;
                if(type==1){
                    content+= `<td>操作</td>`;
                }
                content+=`</tr></thead><tbody>`;
                for(var i=0;i<list.length;i++) {
                    let map = list[i];
                    content+=` <tr>`;
                    if(type==0){
                        content+= `<td class="file_list_img" onclick="show_pdf('`+map.filePath+`')"></td>`;
                    }
                    content+=  `                                                     
                            <td>`+map.fileNumber+`</td>
                            <td>`+map.categoryName+`</td>
                            <td>`+map.fileCategory+`</td>
                            <td>`+map.fondName+`</td>
                            <td>`+map.storagePeriodCode+`</td>
                            <td>`+map.secretLevel+`</td>
                            <td>`+map.fileTitle+`</td>
                            <td>`+map.organization+`</td>
                            <td>`+map.remarks+`</td>
                            <td>`+map.storageLocation+`</td>
                            <td>`+map.licenseNumber+`</td>
                            <td>`+map.carriersNumber+`</td>
                            <td>`+map.fondNumber+`</td>
                            <td>`+map.filesNumber+`</td>
                            <td>`+map.boxNumber+`</td>
                            <td>`+map.person+`</td>
                            <td>`+map.annex+`</td>
                            <td>`+map.pageNumber+`</td>
                            <td>`+map.problem+`</td>
                            <td>`+map.storageTime+`</td>
                            <td>`+map.subjectHeading+`</td>
                            <td>`+map.note+`</td>
                            <td>`+map.year+`</td>
                            <td>`+map.partNumber+`</td>
                            <td>`+map.volumeNumber+`</td>
                            <td>`+map.categoryNumber+`</td>
                            <td>`+map.writingDate+`</td>
                            <td>`+map.pagesNumber+`</td>
                             <td>`+map.catalogNumber+`</td>                           
                            `;
                    if(type==1){
                        content+= `<td onclick="showCase('`+map.fileNumber+`')">查看</td>`;
                    }
                }
                content+=`</tr></tbody>`;
                $("#table").append(content);
            }
        }
    });
}



$("#search").click(function(){
    if(!direId){
        alert("请先选择目录！");
        return;
    }
    var param = $("input[name='search']").val();
    initPage(getRows(direId,param),"Page");
    getDataByDire(param,1);

});

function getRows(direId,param){
    var rows = 0;
    if(!param){
        param = "";
    }
    $.ajax({
        type: "GET",
        url: "/file/dire/count/"+direId,
        data:{"param":param},
        dataType: "json",
        async:false,
        success: function (json) {
            if(json.status==200){
                rows = json.data;
            }
        }
    });
    return rows;
}



function showCase(fileNumber){
    $.ajax({
        type: "GET",
        url: "/file/dire/fileNumber/"+fileNumber,
        dataType: "json",
        async:false,
        success: function (json) {
            let list = json;
            $("#case_content table").html("");
            if(list.length>0){
                let content = `<thead><tr>`;

                content += `<td>附件</td>`;

                content += `              
                <td>档号</td>
                <td>分类名称</td>
                <td>档案类别</td>
                <td>全宗名称</td>              
                <td>保管期限代码</td>
                <td>密级</td>
                <td>文件标题</td>
                <td>组织机构</td>
                <td>备注</td>
                <td>存放位置</td>
                <td>登记号</td>
                <td>载体数量</td>
                <td>全宗号</td>
                <td>案卷号</td>
                <td>盒号</td>
                <td>责任者</td>
                <td>附件</td>
                <td>张页号</td>
                <td>问题</td>
                <td>保管期限</td>
                <td>主题词</td>
                <td>附注</td>
                <td>年度</td>
                <td>件号</td>
                <td>卷内顺序号</td>
                <td>分类号</td>
                <td>成文日期</td>
                <td>页数</td>
                <td>目录号</td> 
                 <td>文件级档号</td>                 
                `;
                content+=`</tr></thead><tbody>`;
                for(var i=0;i<list.length;i++) {
                    let map = list[i];
                    content+=` <tr>`;

                    content+= `<td class="file_list_img" onclick="show_pdf('`+map.filePath+`')"></td>`;

                    content+=  `                                                     
                            <td>`+map.fileNumber+`</td>
                            <td>`+map.categoryName+`</td>
                            <td>`+map.fileCategory+`</td>
                            <td>`+map.fondName+`</td>
                            <td>`+map.storagePeriodCode+`</td>
                            <td>`+map.secretLevel+`</td>
                            <td>`+map.fileTitle+`</td>
                            <td>`+map.organization+`</td>
                            <td>`+map.remarks+`</td>
                            <td>`+map.storageLocation+`</td>
                            <td>`+map.licenseNumber+`</td>
                            <td>`+map.carriersNumber+`</td>
                            <td>`+map.fondNumber+`</td>
                            <td>`+map.filesNumber+`</td>
                            <td>`+map.boxNumber+`</td>
                            <td>`+map.person+`</td>
                            <td>`+map.annex+`</td>
                            <td>`+map.pageNumber+`</td>
                            <td>`+map.problem+`</td>
                            <td>`+map.storageTime+`</td>
                            <td>`+map.subjectHeading+`</td>
                            <td>`+map.note+`</td>
                            <td>`+map.year+`</td>
                            <td>`+map.partNumber+`</td>
                            <td>`+map.volumeNumber+`</td>
                            <td>`+map.categoryNumber+`</td>
                            <td>`+map.writingDate+`</td>
                            <td>`+map.pagesNumber+`</td>
                             <td>`+map.catalogNumber+`</td> 
                             <td>`+map.levelNumber+`</td>                            
                            `;
                }
                content+=`</tr></tbody>`;
                $("#case_content table").append(content);
                $("#case_div").show();
            }


        }
    });
}







