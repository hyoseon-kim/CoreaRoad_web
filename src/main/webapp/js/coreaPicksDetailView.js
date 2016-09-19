/**
 * Created by Naver on 2016-09-11.
 */
define([
    'handlebars',
    'text!/coreaPicksDetailView.html',
    'rating'
],function (Handlebars, _welCoreaPicksDetailView, rating) {
    var html = $(_welCoreaPicksDetailView),
        oPicksData = null,
        oTitleArea = html.find("._write_picks_title"),
        oWriterArea = html.find(".detail_view_writer"),
        oContentArea = html.find(".post_content"),
        oMainPicture = html.find('._main_picture_carousel .main_picture'),
        oRatingArea = html.find('._rating_area'),
        oTagListArea = html.find('._tag_list_area');

    function _init(data) {
        oPicksData = data;
        $('._corearoad_content').html(html);
        console.log(data);
        _setData();
    }
    
    function _setData() {
        oTitleArea.html(oPicksData.title);
        oWriterArea.html(oPicksData.writerEmail);
        oContentArea.html(oPicksData.content);
        oMainPicture.attr('src',oPicksData.mainPicture);
        oRatingArea.html(_getRating(oPicksData.rating));
        oTagListArea.html(_getTagList(oPicksData.processedTagList));
    }
    
    function _getRating(rating) {
        var sHtml = [];
        for(var i =0 ;i < parseInt(rating); i++) {
            sHtml.push('<img src="img/star_icon.png" width="30px"/>');
        }
        return sHtml.join('');
    }

    function _getTagList(tagList) {
        console.log(tagList);
        var sHtml = [];
        for(var i=0, nLen = tagList.length; i < nLen ; i ++) {
            sHtml.push('<span class="label label-danger">#'+tagList[i]+'</span>');
        }
        return sHtml.join('');
    }

    return {
        init: _init
    }
});