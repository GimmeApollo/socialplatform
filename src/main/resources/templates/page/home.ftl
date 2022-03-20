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

    <p ${ (canSee!true) ? string('','hidden')}>关注数:<a href="https://www.runoob.com/">${user.followsNum}</a></p>
    <p ${ (canSee!true) ? string('','hidden')}>粉丝数:<a href="${followPath+"/follows?id="+user.id+"&page=1&size=2"}">${user.fansNum}</a></p>


</div>
</body>
</html>