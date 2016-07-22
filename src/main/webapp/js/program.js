/**
 * Created by Naver on 2016-07-23.
 */
define(['least'],function (least) {
    
    function _init(selectedIndex) {

        var _oData = {};

            $.ajax('/getMainActionPictureList.do').done(function (oData) {
                var json = $.parseJSON(oData);

                $.each(json, function( index, value ) {
                    _oData[value.no] = value;
                    var image = value.url;
                    var name = value.name;
                    var sHtml = [];
                    sHtml.push('<li>');
                    sHtml.push('<a href="img/' + image + '" title="'+name+'"data-subtitle="" data-index="'+ value.no +'" id="active_no_'+ value.no +'">');
                    sHtml.push(' <img src="img/' + image + '" alt="Alt Image Text" /></a>');
                    sHtml.push('</li>');
                    $('.least-gallery').append(sHtml.join(''));
                });
                $('.least-gallery').least();

                _setSelected(selectedIndex);
            });
    }

    function _setSelected(selectedIndex) {
        $("#active_no_" + selectedIndex).triggerHandler('click');
    }

    return {
        init : _init,
        setSelected: _setSelected
    }

});