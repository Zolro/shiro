function loadStyles(url) {
    var link = document.createElement("link");
    link.rel = "stylesheet";
    link.type = "text/css";
    link.href = url;
    var head = document.getElementsByTagName("head")[0];
    head.appendChild(link);
}
/** 初始化分页 **/
function initPage(total,dom){
    let element = document.getElementById(dom);
    if(total==0){
        element.style.display = "none";
        return;
    }else {
        element.style.display = "";
        P.initMathod({
            params: {elemId: '#'+dom,total:total,pageSize:'10',pageIndex:1},
            requestFunction: function () {

            }
        });
    }
}

//时间格式化函数
function renderTime(date){
    var dateee = new Date(date).toJSON();
    return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
}

function show_pdf(text){
    var oiframe = document.getElementById('pdf_iframe');
    var close_btn = document.getElementById('close_btn');
    oiframe.style.display="block";
    close_btn.style.display = "block";
    oiframe.src=text;
    close_btn.onclick=function(){
        oiframe.style.display = 'none';
        close_btn.style.display = "none";
    }

}