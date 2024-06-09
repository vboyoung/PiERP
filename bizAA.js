

var jsAA= {}




/*********** 그리드 공통 속성 ***********/
jsAA.gProp = {

    // 읽기전용속성
    getReadProp : function(){
        return {
            rowNum		  : 1000,
            height        : 400,
            sortable      : true,
            scroll        : true,
            rownumbers    : true,
            scrollrows	  : true,
            multiselect   : false,
            multiexcute   : false,
            editmode      : false,
            cellEdit      : false,
            datatype      : 'local',
            widthBase     : '#srchBox',
            searchUrl     : '',
            pagerButtons  : {refresh:false,col:false,add:false,del:false},
            propsEnd      : null
        };
    },

    // 입력전용속성
    getWriteProp :  function(){
        return {
            rowNum		  : 1000,
            height        : 400,
            sortable      : true,
            scroll        : true,
            rownumbers    : true,
            scrollrows	  : true,
            multiselect   : false,
            multiexcute   : true,
            editmode      : false,
            cellEdit      : true,
            datatype      : 'local',
            widthBase     : '#srchBox',
            searchUrl     : '',
            pagerButtons  : {refresh:false,col:false,add:false,del:false},
            propsEnd      : null
        };
    }
};


/*********** popup 호출 ***********/
jsAA.cfn = {




}





/*********** disable input, btn ***********/
jsAA.disable = {

    disableAll : function( flag ) {
		jsAA.mode.disableAllBtn( t_f );	
		jsAA.mode.disableAllForm( t_f );	
	},
	disableAllBtn : function( t_f ) {
		$('#content .btn_basic').disableObj( t_f );
	},
	disableAllForm : function( t_f ) {
		$('#content .detail_table :input').disableObj( t_f );
		$('#srchBox :disabled').disableObj( false );
	},
	// #mstBox > input
	disableForm : function( formId, t_f ) {
		$('#' + formId + ' :input').disableObj( t_f );
	},
	
    // #mstBox > input + btn 
	disableFormBtn : function( formId, t_f ) {
		$('#' + formId + ' :input').disableObj( t_f );
		$('#' + formId + ' .btn_basic:not(.btn_top)').disableObj( t_f );
	}

} 


jsAA.status = {

    isChecked : function(id) {
        return $('#' + id ).is(':checked');
    }


}