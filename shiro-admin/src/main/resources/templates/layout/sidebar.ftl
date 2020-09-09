
    <div class="index_top">
        <div class="index_top_left" onclick="window.location.href= '/'">
        </div>
        <div class="index_top_right">
            <div class="index_top_right_list">
                <ul>
                   <#-- <@shiro.user>
                    <li class="index_top_right_list_li" id="index"><a href="/index">首页</a></li>
                    </@shiro.user>-->
                    <@zhydTag method="menus" userId="${user.id}">
                         <#if menus?? && menus?size gt 0>
                             <#list menus as item>
                                <li id="${item.permission}" class="index_top_right_list_li"><a href="${item.url?if_exists}"}>${item.name?if_exists}</a></li>
                             </#list>
                        </#if>
                    </@zhydTag>
                    <li class="log_out"><a href="/passport/logout" target="iframe_a">退出系统</a></li>
                </ul>

            </div>
        </div>
    </div>


<script>
    var status = '${model}';
    console.log(status);
    if(status){
        document.getElementById(status).className = 'top_active';
    }
</script>