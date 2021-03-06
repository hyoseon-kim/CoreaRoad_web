/**
 * Created by Naver on 2016-08-14.
 */
define([
    'handlebars',
    'text!/writePicksContent.html',
    'writePicksContent'
],function (Handlebars, _welWritePicksContent, writePicksContent) {

    var map2,
        bounds,
        markers = [];

    
    function _init() {
        $('._write_picks_title').focus();
        _attachEvent();
    }

    function _attachEvent() {

        $('._search_shop_text').on('keyup', function (e) {
            if(e.keyCode == 13) {
                $("._search_shop").click();
            }
        });
        $("._search_shop").on('click', function (e) {

            var category = $('.category_select').val();
            var queryText = $('._search_shop_text').val();
            var ajaxCallUrl = "";

            if(queryText == '') {
                return ;
            }

            if(category == 'food') {
                ajaxCallUrl = '/getFoodShopListBySearch.do';
            } else {
                ajaxCallUrl = '';
            }

            $.ajax({
                url: ajaxCallUrl,
                method: 'post',
                data: {
                    lang: 'en',
                    name: queryText
                }
            }).done(function (oData) {
                var result = $.parseJSON(oData);

                if(oData.length > 1) {
                    $('._init_guide_msg').hide();
                    $("#_search_shop_map").show();
                    $("#_search_shop_result").show();
                    $('._search_shop_list').html('');
                    _setMap();
                }
                $.each(result, function (idx, data) {
                    $('._search_shop_list').append('<tr class="shop_item" data-index="'+data.shopNo+'"><td>'+data.shopName+'</td><td>'+data.shopAddrOld+'</td></tr>');


                    var position = new google.maps.LatLng(data.shopMap.split('/')[0],  data.shopMap.split('/')[1]);
                    bounds.extend(position);

                    var infoWindow = new google.maps.InfoWindow({map: map2, content: data.shopName});

                    var marker = new google.maps.Marker({
                        position: position,
                        title: data.shopName,
                        map: map2,
                        postId: data.shopNo
                    });

                    $('.shop_item').on('click', function (e) {
                        var shopNo = $(e.target).attr('data-shopNo');
                        var _welContentArea = $('._corearoad_content');
                        _welContentArea.html(_welWritePicksContent);
                        writePicksContent.init($('._write_picks_title').val(), $('.category_select').val(), data.shopMap, data.shopAddrOld);
                    });

                    infoWindow.open(map2, marker);
                    markers.push(marker);
                    map2.fitBounds(bounds);
                });
            })
        })
    }

    function _setMap() {
        bounds = new google.maps.LatLngBounds();
        var mapOptions = {
            mapTypeId: 'roadmap'
        };
        // Display a map on the page
        map2 = new google.maps.Map(document.getElementById("_search_shop_map"), mapOptions);

        console.log(map2);
    }
    return {
        init: _init
    };
});