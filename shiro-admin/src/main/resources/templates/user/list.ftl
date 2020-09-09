<#assign model = "users">
<#assign title = "用户管理">
<#include "/layout/header.ftl"/>
<script type="text/javascript" src='/assets/js/jquery-3.5.1.min.js'></script>
<script type="text/javascript" src='/assets/js/tooles.js?v=1.1.0'></script>
<script type="text/javascript" src='/assets/js/page-tools.js?v=1.2.0'></script>
<script type="text/javascript" src='/assets/js/wangEditor.js'></script>
<div class="iframe_box">
        <#include "/model/user.ftl"/>
</div>

<#include "/layout/booter.ftl"/>
<script>
    loadStyles("/assets/css/file.css");
</script>
