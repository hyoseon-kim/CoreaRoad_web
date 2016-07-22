/**
 * Created by Naver on 2016-07-23.
 */
define(['vendor/fullcalendar', 'vendor/scheduler'],function (fullCalendar, scheduler) {

    function _init() {
        $(document).ready(function() {

            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                defaultDate: '2016-01-12',
                editable: true,
                eventLimit: true, // allow "more" link when too many events
                events: [
                    {
                        title: 'ChiMaek in Han River',
                        start: '2016-01-01',
                        index : 1
                    },
                    {
                        title: 'Crazy hongdae Club',
                        start: '2016-01-07',
                        end: '2016-01-10',
                        index: 2
                    },
                    {
                        id: 999,
                        title: 'busking in hongdae',
                        start: '2016-01-09T16:00:00',
                        index: 3
                    }
                ],
                eventClick: function(calEvent, jsEvent, view) {
                   $("#menu_activities").triggerHandler('click', calEvent.index);
                }
            });

        });

    }

    return {
        init : _init
    }
});