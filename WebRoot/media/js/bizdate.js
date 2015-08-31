var Index = function () {

    return {

        initCalendar: function () {
            if (!jQuery().fullCalendar) {
                return;
            }

            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();

            var h = {};

            if ($('#calendar').width() <= 400) {
                $('#calendar').addClass("mobile");
                h = {
                    left: 'title, prev, next',
                    center: '',
                    right: 'today,month,agendaWeek,agendaDay'
                };
            } else {
                $('#calendar').removeClass("mobile");
                if (App.isRTL()) {
                    h = {
                        right: 'title',
                        center: '',
                        left: 'prev,next,today,month,agendaWeek,agendaDay'
                    };
                } else {
                    h = {
                        left: 'title',
                        center: '',
                        right: 'prev,next,today,month,agendaWeek,agendaDay'
                    };
                }               
            }

            $('#calendar').fullCalendar('destroy'); // destroy the calendar
            $('#calendar').fullCalendar({ //re-initialize the calendar
                disableDragging: false,
                header: h,
                editable: true,
                events: 
                [
                	{
                        title: '正常营业结束',                        
                        start: new Date(y, m, 1),
                        backgroundColor: App.getLayoutColorCode('green')
                    }, 
                    {
                        title: '正常营业结束',                        
                        start: new Date(y, m, 2),
                        backgroundColor: App.getLayoutColorCode('green')
                    }, 
                   	{
                        title: '正常营业结束',                        
                        start: new Date(y, m, 3),
                        backgroundColor: App.getLayoutColorCode('green')
                    }, 
                   	{
                        title: '正常营业结束',                        
                        start: new Date(y, m, 4),
                        backgroundColor: App.getLayoutColorCode('green')
                    },
                    {
                        title: '数据上传未完成',                        
                        start: new Date(y, m, 5),
                        backgroundColor: App.getLayoutColorCode('yellow')
                    }, 
                    {
                        title: '正常营业结束',                        
                        start: new Date(y, m, 6),
                        backgroundColor: App.getLayoutColorCode('green')
                    },
                    {
                        title: '营业结束未完成',                        
                        start: new Date(y, m, 7),
                        backgroundColor: App.getLayoutColorCode('red')
                    }
                ]
            });
        }
    }

}();