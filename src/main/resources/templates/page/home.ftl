<!doctype html>
<html lang="en">
<#include "../common/head.ftl">
<script>
    function change() {
        var btn = document.getElementById("btn");
        var mark = btn.innerText;
        if (mark == '关注') {
            ${'$'}.
            get("${followPath+"/follow/"+user.id}", function (data) {
                console.log("关注成功");
                btn.innerText = "${(userFollowGuest!false) ? string('互相关注','已关注')}";
            });
        } else {
            ${'$'}.
            get("${followPath+"/unfollow/"+user.id}", function (data) {
                console.log("取关成功");
                btn.innerText = "关注";
            });
        }
    }

</script>

<body>
<div id="app">
    ${user.name}的个人页！
</div>
<div id="user">

    <p>
        <img src="${basepath+"/logo/"+user.avatar}" alt="logo"></p>

    <p>昵称：${user.name}<br>简介：${user.profile}</p>

    <button id="btn" ${(canSee??) ? string('','hidden')} onclick="change()">
        ${(guestFollowUser!false) ? string( (userFollowGuest!false) ? string('互相关注','已关注') ,'关注')}
    </button>

    <p ${ (canSee!true) ? string('','hidden')}>关注数:<a
                href="${followPath+"/stars?id="+user.id+"&page=1&size=2"}">${user.followsNum}</a></p>
    <p ${ (canSee!true) ? string('','hidden')}>粉丝数:<a
                href="${followPath+"/follows?id="+user.id+"&page=1&size=2"}">${user.fansNum}</a></p>
</div>
<div id="posting">
    <a href="${postPath}/posting" ${(canSee??) ? string('hidden','')}>
        发动态
    </a>
</div>
<div id="posts">
    <#if postVOs?? && (postVOs?size >0)>
        <#list postVOs as postVO>
            <div>
                ******************************
                <p>
                    <img src="${basepath+"/logo/"+user.avatar}" alt="logo" height="20" width="20">${user.name}
                    于${postVO.postTime?string('yyyy-MM-dd HH:mm:ss')}发表
                </p>
                <h3>
                    ${postVO.content}
                </h3>

                <p>
                    <#if postVO.fileNames?? && (postVO.fileNames?size >0)>
                        <#list postVO.fileNames as fileName>
                            <img src="${picpath+"/"+postVO.path+fileName}" alt="picture">
                        </#list>
                    </#if>
                <p>
                <p>
                    <button id="like">点赞</button>
                    <button id="comment">评论</button>
                    <button id="detail">详情</button>
                    <button id="reprint">转载</button>
                    <button id="onlyIsee">仅自己可见</button>
                </p>

            </div>
        </#list>
    </#if>
</div>


</div>
</body>
</html>