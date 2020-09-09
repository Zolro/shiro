<#assign model = "configs">
<#assign title = "系统配置">
<#include "/layout/header.ftl"/>
<script type="text/javascript" src='/assets/js/jquery-3.5.1.min.js'></script>
<script type="text/javascript" src='/assets/js/tooles.js?v=1.1.0'></script>
<script type="text/javascript" src='/assets/js/page-tools.js?v=1.2.0'></script>
<div class="iframe_box">
    <div class="iframe_left file_iframe_left">
        <div class="iframe_left_list">
            <div class="list_top">配置列表</div>
            <ul>
                <li class="list_folder"><a href="/configs">档案类型</a></li>


            </ul>
        </div>
    </div>
   <#-- <div class="include table" style="display: none"  id="orgTable">
        <#include "/model/org.ftl"/>
    </div>-->

        <#include "/model/fileType.ftl"/>

</div>

<#include "/layout/booter.ftl"/>
<script>
    loadStyles("/assets/css/file.css");
</script>




