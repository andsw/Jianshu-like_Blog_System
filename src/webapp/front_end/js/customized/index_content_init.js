let pageUser;
$(document).ready(function () {
    pageUser = getQueryStrings('content');
    iniMainPage(pageUser);

    //异步获取个人简介
    const selfSummaryUrl = "http://localhost:8080/users/" + pageUser.userNo + "/self_summary";
    request_application_json(selfSummaryUrl, "GET", "",
        function (result) {
            const message = result.message;
            const data = result.data;
            if (result.code === 200) {
                $('#self_summary_div_from_textarea').text(data);
            } else {
                Materialize.toast(message, 2000);
                $('#self_summary_div_from_textarea').text(message);
            }
        },
        defaultErrorMethod(), true);

    //异步获取文集列表
    const corpusesUrl = "http://localhost:8080/users/" + pageUser.userNo + "/corpuses";
    request_application_json(corpusesUrl, "GET", "",
        function (result) {
            if (result.code === 200) {
                if (result.data.length === 0) {
                    $('#corpus_list_div').find('ul').append('<li class="corpus_item_li valign-wrapper">' +
                        '暂时没有文集哦！' +
                        '</li>');
                } else {
                    $(result.data).each(function (index, object) {
                        // TODO 这里的a要设置点击事件！ 别忘了
                        $('#corpus_list_div').find('ul').append('<li class="corpus_item_li valign-wrapper">' +
                            '<i class="material-icons prefix md-22 grey-text">book</i>' +
                            '<a href="">' +
                            object.corpusName +
                            '</a>' +
                            '<span class="grey-text right">' + object.blogNum +
                            '</span></li>');
                    });
                }
            } else {
                $('#corpus_list_div').text(result.message);
            }
        },
        defaultErrorMethod(), true);

    // 异步获取文章列表
    getArticleInfoByPage(0);
});

//记住后台需要根据所获取的用户id和本用户id比较决定是否会返回私有文章信息！
function getArticleInfoByPage(offset) {
    console.log('http://localhost:8080/users/' + pageUser.userNo + "/article_info?offset=" + offset);
    request_application_json('http://localhost:8080/users/' + pageUser.userNo + "/article_info?offset=" + offset, 'GET', '',
        function (result) {
            if (result.code === 200) {
                if (result.data.length !== 0) {
                    $(result.data).each(function (index, object) {
                        console.log(object);
                        $('#article_part_div').find('ul').append(
                            '<li class="grey-text col s12 row horizontal valign-wrapper">' +
                            '                        <div class="col s12 article_info">' +
                            '                            <a href="" class="black-text"><h5>' + object.articleTitle + '</h5></a>' +
                            '                            <p style="text-overflow: ellipsis;text-overflow: ellipsis;white-space: nowrap;">' +
                            object.articleSummary +
                            '                            </p>' +
                            '                            <div>' +
                            '                                <span class="number article_item_data_span"><i class="material-icons prefix md-10">visibility</i>' +
                            object.articleReadNum +
                            '                                </span>' +
                            '                                <span class="number article_item_data_span"><i class="material-icons prefix md-10">comment</i>' +
                            object.articleCommentNum +
                            '                                </span>' +
                            '                                <span class="number article_item_data_span"><i class="material-icons prefix md-10">thumb_up</i>' +
                            object.articleLikeNum +
                            '                                </span>' +
                            //这里记得用框架显示时间，即jquery_timeago.min.js-->
                            '                                <span class="article_item_data_span">' +
                            object.articleReleaseTime +
                            '                                </span>' +
                            '                            </div>' +
                            '                        </div>' +
                            isSummaryExists(object) +
                            '                    </li>' +
                            '<hr class="article_hr col s12">'
                        );
                    });
                } else {
                    //一篇文章都没有

                }

            } else {
                //获取失败
            }
        },
        function () {

        }, true);
}

function isSummaryExists(object) {
    if (object.articleSummaryImage === '') {
        return '';
    } else {
        return '<a class="" style="width: 170px;height: auto;" href="">' +
               '    <img src="' + object.articleSummaryImage + '" alt="" class="article_summary_image">' +
               '</a>';
    }
}

function iniMainPage(user) {
    user.gender = parseInt(user.gender);
    if (user.gender === 0) {
        //女
        $('#my_or_others_username_and_gender').html(user.username
            + "<img src='../front_end/img/gender/female.png' "
            + "alt='女'>");
    } else if (user.gender === 1) {
        //男
        $('#my_or_others_username_and_gender').html(user.username
            + "<img src='../front_end/img/gender/male.png' "
            + "alt='男'>");
    } else {
        // 保密
        $('#my_or_others_username_and_gender').html(user.username);
    }

    $('#small_avatar').attr('src', user.avatar);
    $('#medium_large_avatar').attr('src', user.avatar);

    //所有数据初始化
    $('#subscribe_num').text(user.followNum);
    $('#follower_num').text(user.followerNum);
    $('#article_num').text(parseInt(user.blogNumPrivate) + parseInt(user.blogNumPublic));
    $('#word_num').text(user.wordNum);
    $('#like_num').text(user.likeNum);
}