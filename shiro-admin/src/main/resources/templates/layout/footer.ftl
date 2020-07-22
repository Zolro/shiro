</div>
<script type="text/javascript" src='/assets/js/jquery-3.5.1.min.js'></script>
<script type="text/javascript">

    //$(document).ready(function(){
        getFileType();
        var num;
        var direId;
        var fileType;
        var type;
        var totalNum;
        var caseNum;
        function initmenu(){
            var afolder = document.getElementsByClassName('list_folder');
            var afile = document.getElementsByClassName('ul_file');
            var file = document.getElementsByClassName('list_file');
            for (var i = 0; i < afolder.length; i++) {
                afolder[i].index = i;
                afolder[i].onclick=function(){
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
            return direId;

        }
        function getDataByDire(param,pageNum) {
            getTilte(direId);
            show(direId,pageNum,num,param);
            initPage(totalNum);
        }
        $("#search").click(function(){
            if(!direId){
                alert("请选择二级目录！");
                return;
            }
            var param = $("input[name='search']").val();
            getDataByDire(param,1);

        });
        /* 初始化目录结构*/
        function getFileType(){
            $.ajax({
                type: "GET",
                url: "/fileType",
                dataType: "json",
                success: function (json) {
                    if (json.status === 200) {
                        let list = json.data;
                        content=``;
                        for(var i=0;i<list.length;i++){
                            var data = list[i];
                            var id = 'type_'+data["id"];
                            content+=`
                            <li class="list_folder" type="`+data["type"]+`" id=`+id+`>`+data["name"]+`</li>`;
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
</script>
<script type="text/javascript" src='/assets/js/page.js'></script>
<script type="text/javascript">
    function getTilte(direId){
        $.ajax({
            type: "GET",
            url: "/dire/title/"+direId,
            dataType: "json",
            async:false,
            success: function (json) {
                if (json.status === 200) {
                    let obj = json.data;
                    $(".iframe_right_list table").html("");
                    var content = `<tr><th>附件</th><th>序号</th>`;
                    num = obj.fileNum;
                    for(var i=0;i<obj.fileNum;i++){
                        var key = 'file'+i;
                        var value = obj[key];
                        if(!value){
                            value="";
                        }
                        content+=` <th>`+value+`</th>`;
                    }
                    if(type!=0){
                        content+=`<th>操作</th>`;
                    }
                    content+=`</tr>`;

                    $("#file_content table").html(content);
                    $("#case_content table").html(content);
                }
                return num;
            }

        });
    }

    function show(direId,pageNum,num,param){
        if(!param){
            param = "";
        }
        $.ajax({
            type: "GET",
            url: "/search/dire/condit/"+direId,
            data:{"pageNum":pageNum,"param":param},
            dataType: "json",
            async:false,
            success: function (json) {
                $("#file_content table").not(':first').html("");
                let list = json.rows;
                let total = json.total;
                totalNum = total;
                if(total>0){
                    var content = "";
                    for(var i=0;i<list.length;i++) {
                        let map = list[i];
                        content+=generTr(map,num);
                    }
                    $("#file_content table").append(content);
                }
                return totalNum;
            }
        });
    }

    function getCase(id,pageNum){
        var param = $("input[name='case_search']").val();
        initCase(id,pageNum,num,param);
        initCasePage(pageNum);


    }
    function initCase(id,pageNum,fileNum,param){
        $("#file_content table").not(':first').html("");
        $.ajax({
            type: "GET",
            url: "/search/case/condit/"+id,
            data:{"pageNum":pageNum,"param":param},
            dataType: "json",
            success: function (json) {
                console.log(json);
                let list = json.rows;
                let total = json.total;
                if(total==0){
                    alert("无数据！");
                    return false;
                }
                pageNum = total;
                if(total>0){
                    initPage(total);
                    var content = "";
                    for(var i=0;i<list.length;i++) {
                        let map = list[i];
                        content+=generCase(map,fileNum);
                    }
                    $("#case_content table").append(content);
                }
                return pageNum;
            }
        });
        $("#case_div").show();
    }
    function generTr(map,num){
        var content=`<tr><td class="file_list_img"></td><td>`+map['id']+`</td>`;
        for(var i=0;i<num;i++){
            var key = 'file'+i;
            var value = map[key];
            if(!value){
                value="";
            }
            content+=`<td>`+value+`</td>`;
        }
        if(type!=0){
            content+=`<td onclick="getCase(`+map['id']+`,0)">查看</td>`;
        }
        content+=`</tr>`;
        return content;

    }
    function generCase(map,num){
        var content=`<tr><td class="file_list_img"></td><td>`+map['id']+`</td>`;
        for(var i=0;i<num;i++){
            var key = 'file'+i;
            var value = map[key];
            if(!value){
                value="";
            }
            content+=`<td>`+value+`</td>`;
        }
        content+=`</tr>`;
        return content;

    }
    $('.index_top_right_list_li').click(function(){
        $('.index_top_right_list_li').removeClass("top_active");
        $(this).addClass("top_active");
    })
    function initPage(total){
            P.initMathod({
                params: {elemId: '#Page',total:total,pageSize:'10'},
                requestFunction: function () {
                }
            });
    }
    function initCasePage(total){
        V.initMathod({
            params: {elemId: '#case_page',total:total,pageSize:'10'},
            requestFunction: function () {
            }
        });
    }
</script>

</body>
</html>