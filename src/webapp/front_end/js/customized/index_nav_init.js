// 只负责预定义顶部和侧边导航栏
$(document).ready(function () {
    const userNo = $.cookie('userNo');

    let personalInfo = undefined;
    //第一步获取个人信息
    getUserInfo(userNo, function (result) {

        personalInfo = result.data;
        //初始化头像
        $('.avatar').attr('src', result.data.avatar);
        //初始化用户名和性别显示
        if (result.data.gender === 0) {
            //女
            $('#my_username_and_gender').html(result.data.username
                + "<img src='../front_end/img/gender/female.png' "
                + "alt='' class='gender_img_style'>")
        } else if (result.data.gender === 1) {
            //男
            $('#my_username_and_gender').html(result.data.username
                + "<img src='../front_end/img/gender/male.png' "
                + "alt='' class='gender_img_style'>")
        } else {
            // 保密
            $('#my_username_and_gender').text(result.data.username)
        }
    });

    //默认加载个人主页,一开始就是不用userNo的传值后面如果还需要其他用户主页的话就直接传入userNo然后iframe自己获取！
    // 用encodeURI默认UTF-8编码！
    $('#content').attr('src', encodeURI('../front_end/index_content.html?' + 'gender=' + personalInfo.gender
        + '&avatar=' + personalInfo.avatar
        + '&followNum=' + personalInfo.followNum
        + '&followerNum=' + personalInfo.followerNum
        + '&blogNumPrivate=' + personalInfo.blogNumPrivate
        + '&blogNumPublic=' + personalInfo.blogNumPublic
        + '&wordNum=' + personalInfo.wordNum
        + '&likeNum=' + personalInfo.likeNum
        + '&username=' + personalInfo.username
    ));


    // 部件初始化，不包括数据
    $('.dropdown-button').dropdown({
        inDuration: 150,
        outDuration: 100,
        constrain_width: false, // Does not change width of dropdown to that of the activator
        hover: true, // Activate on hover
        gutter: 0, // Spacing from edge
        belowOrigin: false, // Displays dropdown below the button
        alignment: 'left' // Displays dropdown with edge aligned to the left of button
    });
    $('.collapsible').collapsible();
});


//导航栏的登出点击事件的响应方法
function logout(){
    //这步删除session成功了，才能有下一步的删除刷新操作！
    request_plain_text("http://localhost:8080/auth/logout", "get", "", null, null, false);
    $.removeCookie("rememberMe", {path:"/"});
    $.removeCookie("userNo", {path:"/"});
    location.reload();

    //方便性的提示下，这里是用了登录时发生错误提示所用的cookie
    $.cookie('loginStatus', "登出成功", {path:'/front_end/loginAndRegister.html'});
    return false;
}