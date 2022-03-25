<!doctype html>
<html lang="en">
<#include "../common/head.ftl">
<title>动态发送</title>

<#if postVO??>
    <#assign ifReprint = postVO.ifReprint/>
    <#assign reprintId = postVO.reprintId/>
<#else>
    <#assign ifReprint = 0/>
    <#assign reprintId = 0/>
</#if>


<body>
<div id="app" style="margin: 20px 20%">
    <h1>发动态咯</h1>

    <form action="${postPath}/upload" method="post" enctype="multipart/form-data">
        <input type="text" name="content" value="写点想说的......"/><br><br>

        <input type="file" name="uploadFile1"/><br><br>
        <input type="file" name="uploadFile2"/><br><br>
        <input type="file" name="uploadFile3"/><br><br>
        <input type="checkbox" name="onlyISee" value="1"/>仅自己可见<br><br>
        <input type="hidden" name="ifReprint" value="${ifReprint}"/><br><br>
        <input type="hidden" name="reprintId" value="${reprintId}"/><br><br>

        <#--userid直接从请求里边拿-->


        <input type="submit" value="发布动态">
    </form>
</div>
</body>
</html>

<#--private int postId;         //动态id-->
<#--private String content;     //动态内容-->
<#--private String path;        //动态图片文件夹-->
<#--private int userId;         //作者id-->
<#--private int OnlyISee;       //是否自己可见,默认0=false-->
<#--private int ifReprint;      //是否为转载，默认0=false-->
<#--private int reprintId;      //转载id,默认0=false-->
<#--private int valid;          //是否存在（可能被删除）-->