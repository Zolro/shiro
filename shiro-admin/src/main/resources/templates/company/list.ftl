<#assign model = "companys">
<#assign title = "组织管理">
<#include "/layout/header.ftl"/>
<script type="text/javascript" src='/assets/js/jquery-3.5.1.min.js'></script>
<script type="text/javascript" src='/assets/js/tooles.js?v=1.1.0'></script>
<script type="text/javascript" src='/assets/js/page-tools.js?v=1.2.0'></script>
<script type="text/javascript" src='/assets/js/wangEditor.js'></script>
<div class="iframe_box">
    <div class="iframe_left file_iframe_left">
        <div class="iframe_left_list">
            <div class="list_top">组织管理</div>
            <ul>
                <@shiro.hasPermission name="orgs">
                    <li class="list_folder"><a href="/companys?type=org">机构管理</a></li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="depts">
                    <li class="list_folder"><a href="/companys?type=dept">部门管理</a></li>
                </@shiro.hasPermission>
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




