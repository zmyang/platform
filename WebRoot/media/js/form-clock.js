var  FormClock = function() {
	var handleClockfaceTimePickers = function () {

        if (!jQuery().clockface) {
            return;
        }

        $('#totm').clockface({
            format: 'HH:mm',
            trigger: 'manual'
        });

        $('#totm_toggle').click(function (e) {
            e.stopPropagation();
            $('#totm').clockface('toggle');
        });

        $('#frmtm').clockface({
            format: 'HH:mm',
            trigger: 'manual'
        });

        $('#frmtm_toggle').click(function (e) {
            e.stopPropagation();
            $('#frmtm').clockface('toggle');
        });

    }
	
	return {
        //main function to initiate the module
        init: function () {
            handleClockfaceTimePickers();
        }

    };
}();