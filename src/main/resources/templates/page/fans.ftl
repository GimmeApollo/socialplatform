<!doctype html>
<html lang="en">
<#include "../common/head.ftl">
<body>
<div id="app">
    ${user.name}的粉丝列表！
</div>
<script>
    function follow(fansId) {
        var s = fansId.toString();
        var btn = document.getElementById("btn"+s);
        ${'$'}.get("${followPath+"/follow/"}" + s, function () {
            console.log("关注成功");
            btn.innerText = "互相关注";
            btn.disabled ="true";
        });

    }
</script>

<div id="fans">

    <#list pager.list as fans>
        <div>
            <p>
                <a href="http://localhost:8081/platform/user/home/${fans.id}">
                    <img src="${basepath+"/logo/"+fans.avatar}" alt="logo">
                </a>
            </p>
            <p>昵称：${fans.name}  简介：${fans.profile}</p>
            <button id="btn${fans.id}" ${(fans.valid==1) ? string('hidden','')} onclick="follow(${fans.id})">
                关注
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
            <a href="${followPath+"/follows?id="+user.id+"&page="+val+"&size="+rows}"> ${val} </a>
        </#if>
    </#list>


</div>
</body>
</html>