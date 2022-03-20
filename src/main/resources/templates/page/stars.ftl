<!doctype html>
<html lang="en">
<#include "../common/head.ftl">
<body>
<div id="app">
    ${user.name}的关注列表！
</div>
<script>

</script>

<div id="stars">

    <#list pager.list as star>
        <div>
            <p>
                <a href="http://localhost:8081/platform/user/home/${star.id}">
                    <img src="${basepath+"/logo/"+star.avatar}" alt="logo">
                </a>
            </p>

            <p>昵称：${star.name}  简介：${star.profile}</p>
            <button id="btn${star.id}" disabled="disabled">
                ${(star.valid==1) ? string('互相关注','已关注')}
            </button>
        </div>

    </#list>


</div>

<div id="pageNum">
    <#-- 总记录数 -->
    <#assign total = pager.total/>
    <#-- 当前页码 -->
    <#assign page = pager.page/>
    <#-- 每页显示数量 -->
    <#assign rows = pager.size/>

    <#-- 计算出总页数 -->
    <#if total==0>
        <#assign totalPage = 1 />
    <#elseif ((total % rows) == 0)>
        <#assign totalPage = (total / rows)?int />
    <#else>
        <#assign totalPage = ((total / rows) + 1)?int />
    </#if>

    <#list 1..totalPage as val>
        <#if (val == page)>
        <#-- 当前页，不渲染连接 -->
            <a> ${val} </a>
        <#else>
        <#-- 非当前页@RequestParam("id") int id, @RequestParam("page") int page,@RequestParam("size") int size-->
            <a href="${followPath+"/stars?id="+user.id+"&page="+val+"&size="+rows}"> ${val} </a>
        </#if>
    </#list>


</div>
</body>
</html>