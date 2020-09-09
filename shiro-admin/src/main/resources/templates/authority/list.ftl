<#assign model = "authoritys">
<#assign title = "权限配置">
<#include "/layout/header.ftl"/>
<script type="text/javascript" src='/assets/js/jquery-3.5.1.min.js'></script>
<script type="text/javascript" src='/assets/js/tooles.js?v=1.1.0'></script>
<script type="text/javascript" src='/assets/js/page-tools.js?v=1.2.0'></script>
<script type="text/javascript" src='/assets/js/wangEditor.js'></script>
<div class="iframe_box">
    <div class="iframe_left file_iframe_left">
        <div class="iframe_left_list">
            <div class="list_top">权限配置</div>
            <ul>
                <li class="list_folder"><a href="/authoritys?type=role">角色管理</a></li>
                <li class="list_folder"><a href="/authoritys?type=feat">功能配置</a></li>

            </ul>
        </div>
    </div>
   <#-- <div class="include table" style="display: none"  id="orgTable">
        <#include "/model/org.ftl"/>
    </div>-->
    <#if type?? && type!=''>
        <#include "/model/${type}.ftl"/>
    </#if>
</div>

<#include "/layout/booter.ftl"/>
<script>
    loadStyles("/assets/css/file.css");
</script>




