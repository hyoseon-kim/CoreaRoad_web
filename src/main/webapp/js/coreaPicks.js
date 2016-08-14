/**
 * Created by preme on 2016-06-19.
 */
define(['handlebars'],function (Handlebars) {

    var aPicksData = [],
        map,
        bInit = false,
        bounds,
        markers = [];


    function _init() {
        _attachEvent();

        /*card 공유 버튼*/
        $('.card__share > a').on('click', function(e){
            e.preventDefault() // prevent default action - hash doesn't appear in url
            $(this).parent().find( 'div' ).toggleClass( 'card__social--active' );
            $(this).toggleClass('share-expanded');
        });

        if(!bInit) {
            window['initialize'] = initialize;
            // Asynchronously Load the map API
            var script = document.createElement('script');
            script.src = "//maps.googleapis.com/maps/api/js?sensor=false&callback=initialize";
            document.body.appendChild(script);
            bInit = true;
        } else {
            _renderCoreaPicksMain();
        }
    }

    function initialize() {
       bounds = new google.maps.LatLngBounds();
        var mapOptions = {
            mapTypeId: 'roadmap'
        };

        // Display a map on the page
        map = new google.maps.Map(document.getElementById("picks_map"), mapOptions);
        map.setTilt(45);

        _renderCoreaPicksMain();
    }

    function _renderCoreaPicksMain() {
        $.ajax('/getAllCoreaPicksList.do').done(function (data) {
            _renderWithData($.parseJSON(data));
        });
    }

    function _renderWithData(data) {
        var parsedData = data;
        var template = Handlebars.compile($("#picks_card_template").html());

        $('.picks_gallery').html('');

        setMapOnAll();

        $.each(parsedData, function (idx, data) {
            aPicksData[data.postId] = data;
            $('.picks_gallery').append(template(data));

            var position = new google.maps.LatLng(data.map.split('/')[0],  data.map.split('/')[1]);
            bounds.extend(position);

            var infoWindow = new google.maps.InfoWindow({map: map, content: data.title});

            var marker = new google.maps.Marker({
                position: position,
                title: data.title,
                map: map,
                postId: data.postId
            });

            infoWindow.open(map, marker);
            markers.push(marker);
            map.fitBounds(bounds);

            marker.addListener('click', function(e) {
                var postId = data.postId;
                $('html, body').animate({
                    scrollTop: $("#picks_"+postId).offset().top
                }, 2000);

            });
        });


    }

    // Sets the map on all markers in the array.
    function setMapOnAll() {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
    }
    
    function _attachEvent() {
        $('.coreaPicks_search_button').on('click', function (oEvent) {
        picksButtonClickEvent(oEvent);
        });

        $("#search_Picks").keyup(function (e) {
            var code = e.keyCode || e.which;
            var value = $(e.target).val();

            if(code == 13) {
               $(".text_tag").prepend('<span class="label label-success">#<span class="tag_list">'+value+'</span><span class="badge">X</span></span>')
                $(e.target).val('');
            }
        });

        $("#picks-search-btn").on('click', _search);
        
    }

    function _search() {
        var startPrice = $('._start_price').val(),
            endPrice = $('._end_price').val(),
            city = $("#city_picks").val(),
            categoryList = [],
            tagList = [];


        //push category list
        if($('.food_picks').hasClass('on')) {
            categoryList.push('food');
        } 
        if($('.accommo_picks').hasClass('on')) {
            categoryList.push('accommodation');
        }
        if($('.activity_picks').hasClass('on')) {
            categoryList.push('activity');
        }

        //push tagList
        $.each($('.tag_list'), function (index, tag) {
            tagList.push(tag.innerText);
        });

        $.ajax({
            url:'/searchCoreaPicks.do',
            method: 'get',
            data : {
                category: categoryList.join(','),
                tagList: tagList.join(','),
                city: city,
                startPrice: startPrice,
                endPrice: endPrice
            }
        }).done(function (data) {
            _renderWithData($.parseJSON(data));
        })

    }
    /*searchbox on/off*/
    function picksButtonClickEvent(we){
        if($(we.target).hasClass('on')){
            $(we.target).removeClass('on');
            $(we.target).addClass('off');

            //이미 off일때는 class > on
        } else {
            $(we.target).removeClass('off');
            $(we.target).addClass('on');
        }
    }
    /*back to top*/
    function backToTop() {
        if ($('#back-to-top').length) {
            var scrollTrigger = 100, // px
                backToTop = function () {
                    var scrollTop = $(window).scrollTop();
                    if (scrollTop > scrollTrigger) {
                        $('#back-to-top').addClass('show');
                    } else {
                        $('#back-to-top').removeClass('show');
                    }
                };
            backToTop();
            $(window).on('scroll', function () {
                backToTop();
            });
            $('#back-to-top').on('click', function (e) {
                e.preventDefault();
                $('html,body').animate({
                    scrollTop: 0
                }, 700);
            });
        }
    }

    return {
        init: _init
    }



});

