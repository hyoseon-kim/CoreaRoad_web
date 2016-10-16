/**
 * Created by Naver on 2016-08-18.
 */
define(['handlebars',
    'rating'
],function (Handlebars ,rating) {
    var imageStack = [],
        imgIndex = 0,
        mainImageIndex = 0,
        tagList = [],
        title,
        selectedCategory,
        mapLatLng,
        address ;

    function _init(sTitle, sSelectedCategory, sMap, sAddr) {
        _initDatas();
        title = sTitle;
        selectedCategory = sSelectedCategory;
        mapLatLng = sMap;
        address = sAddr;
        $('._write_picks_title').val(sTitle);
        $('.category_select').val(sSelectedCategory);
        _attatchEvent();
    }

    function _initDatas() {
        imageStack = [];
        tagList = [];
    }

    function _attatchEvent() {
        $("#inputFileToLoad").on('change', function (e) {
            var filesSelected = document.getElementById("inputFileToLoad").files;
            if (filesSelected.length > 0) {
                var fileToLoad = filesSelected[0];

                var fileReader = new FileReader();

                fileReader.onload = function(fileLoadedEvent) {
                    var srcData = fileLoadedEvent.target.result; // <--- data: base64

                    var newImage = document.createElement('img');
                    newImage.src = srcData;
                    newImage.id = imgIndex++;
                    newImage.className = 'img_content_inner'
                    newImage.style.maxWidth = "300px";

                    //document.getElementById("imgTest").innerHTML = newImage.outerHTML;
                    $('._post_textarea').append('<br>' + newImage.outerHTML);
                    imageStack.push(srcData);

                    newImage.style.width = "30px";
                    newImage.style.height = "30px";
                    newImage.style.border = "1px solid #e3e3e3";
                    newImage.className = "img_thumbnail";
                    newImage.addEventListener("click", function (e) {
                        $('._main_img').attr('src', imageStack[newImage.id]);
                        $('._main_img').attr('data-index', newImage.id);
                        mainImageIndex = newImage.id;
                    })

                    $("#imgTest").append(newImage);
                }
                fileReader.readAsDataURL(fileToLoad);
            }
        });

        $('._plus_tag').on('click', function (e) {
            var tagText = $('._insert_tag_txt').val();
            $('._write_picks_tag_list').append('<span class="label label-danger _tag_item">#'+tagText+'</span>');
            tagList.push(tagText);
        });

        $('._plus_recommended_menu').on('click', function (e) {
            $('._inserted_recommended_menu').append('<span class="_menu">'+$('#recommeded_menu').val()+'</span> <span class="_price">' + $('#recommeded_price').val() + '/' + $('#recommeded_person').val() +' </span><br>');
            $('#recommeded_menu').val('');
            $('#recommeded_price').val('');
            $('#recommeded_person').val('');
        });

        $("._upload_picks").on('click', _upload);
    }
    
    function _getRatingCount() {
        return $('.glyphicon-star').length;
    }

    function _getTagList() {
        var tagList =  [] ;
        $('._tag_item').each(function () {
            tagList.push("'" + $(this).html().split("#")[1] + "'");
        });
        return tagList.join(',');
    }
    
    function _getContentHTML() {
        $('.img_content_inner').each(function (idx) {
            $(this).replaceWith('<img class="img_content_inner" src=""  data-index="'+idx+'>"');
        });

        return $('._post_textarea').html();
    }

    function _getMenus(priceArr) {
        //menu
        var menus = [];
        $('._menu').each(function (idx) {
            var item = {};
            item.menu = $(this).html();
            item.price = priceArr[idx];
            menus.push(item);
        });

        return menus;
    }

    function _upload() {
        $('.loading_icon').show();
        //price
        var priceSum = 0,
            startPrice = 0,
            endPrice = 0,
            priceArr = [];

        $('._price').each(function (idx) {
            var itemString = $(this).html(),
                price = parseInt(itemString.split('/')[0]),
                persons = parseInt(itemString.split('/')[1]);

            if(startPrice  == 0  || startPrice > (price/persons)){
                startPrice = price/persons;
            }

            if(endPrice == 0 || endPrice < (price/persons)) {
                endPrice = price/persons;
            }

            priceArr.push(itemString);
            priceSum += price/persons;
        });

        $.ajax({
            url: '/insertCoreaPicks',
            method: 'POST',
            data : {
                category : selectedCategory,
                tagList : _getTagList(),
                content : _getContentHTML(),
                map : mapLatLng,
                title: title,
                city: address.split(' ')[0] + address.split(' ')[1],
                startPrice : startPrice,
                endPrice : endPrice,
                avgPrice : priceSum/$('._price').length,
                mainPicture : $('._main_img').attr('data-index'),
                menus: _getMenus(priceArr),
                rating : _getRatingCount()
            }
        }).done(function (oData) {
            var oResult = $.parseJSON(oData);
            if(oData == 'fail') {
                return false;
            }
            var key = oResult.randomString,
                i,
                nLen;

            if(key) {
                for(i = 0  , nLen = imageStack.length; i < nLen ; i++) {
                    $.ajax({
                        url: '/insertCoreaPicksImg',
                        method: 'POST',
                        async: false,
                        data: {
                            key : key + '_' + i,
                            img: imageStack[i]
                        }
                    }).done(function (oData) {
                        if(i == (nLen-1)) {
                            alert('success');
                            _openCoreaPicks();
                            $('.loading_icon').hide();
                        }

                        if(oData !== "success") {
                            return false;
                        }


                    },this);
                }
            } else {
                alert('fail');
            }

        });
    }

    function _openCoreaPicks() {
        $('._corearoad_content').html(_welCoreaPicks);
        $('#menu_coreaPicks').click();
    }

    return {
        init : _init
    };
});
