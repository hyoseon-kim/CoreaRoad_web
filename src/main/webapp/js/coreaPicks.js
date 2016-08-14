/**
 * Created by preme on 2016-06-19.
 */
define(['handlebars'],function (Handlebars) {

    var aPicksData = [],
        map,
        bInit = false,
        bounds;


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


        // Override our map zoom level once our fitBounds function runs (Make sure it only runs once)
        var boundsListener = google.maps.event.addListener((map), 'bounds_changed', function(event) {
            this.setZoom(14);
            google.maps.event.removeListener(boundsListener);
        });

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

        var markers = [];
        // Multiple Markers
        /*var markers = [
         ['London Eye, London', 51.503454,-0.119562],
         ['Palace of Westminster, London', 51.499633,-0.124755]
         ];

         // Display multiple markers on a map

         }*/
        $('.picks_gallery').html('');
        $.each(parsedData, function (idx, data) {
            aPicksData[data.postId] = data;
            $('.picks_gallery').append(template(data));
            markers.push(data.title, data.map.split('/')[0], data.map.split('/')[1]);
        });

        var infoWindow = new google.maps.InfoWindow(), marker, i;

        // Loop through our array of markers & place each one on the map
        for (i = 0; i < markers.length; i++) {
            var position = new google.maps.LatLng(markers[i][1], markers[i][2]);
            bounds.extend(position);
            marker = new google.maps.Marker({
                position: position,
                map: map,
                title: markers[i][0]
            });

            // Automatically center the map fitting all markers on the screen
            map.fitBounds(bounds);
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

