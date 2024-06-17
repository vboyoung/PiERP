

const jsAA= {}


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



/*********** 사진관련  ***********/

jsMH.pic = {

    /**
     * jsMH.pic.viewByPicSrno( "#imgView", picSrno );
     */
    viewByPicSrno : function( selector, picSrno ){
        $( selector ).attr( 'src', '' );
        var url = '/erp/ajaxR/MHCommon/selectEmpPicByPicSrno.do?picSrno=' + picSrno+ '&timestamp=' + new Date().getTime();
        $( selector ).attr( 'src', url );
    },

    /**
     * jsMH.pic.viewByEmpUniqNo( "#imgView", empUniqNo );
     */
    viewByEmpUniqNo : function( selector, empUniqNo ){
        $( selector ).attr( 'src', '' );
        var url = '/erp/ajaxR/MHCommon/selectEmpPicByEmpUniqNo.do?empUniqNo=' + empUniqNo + '&timestamp=' + new Date().getTime();
        $( selector ).attr( 'src', url );
    },

    /**
     * jsMH.pic.viewByExmNo( "#imgView", exmNo );
     */
    viewByExmNo : function( selector, exmNo ){
        $( selector ).attr( 'src', '' );
        var url = '/erp/ajaxR/MHCommon/selectEmpPicByExmNo.do?exmNo=' + exmNo+ '&timestamp=' + new Date().getTime();
        $( selector ).attr( 'src', url );
    },
    viewByCertiSignAplNo : function( selector, aplNo, empUniqNo ){
        $( selector ).attr( 'src', '' );
        var url = '/erp/ajaxR/MHCommon/selectBizCertiSignAplNo.do?aplNo=' + aplNo + '&empUniqNo=' + empUniqNo + '&timestamp=' + new Date().getTime();
        $( selector ).attr( 'src', url );
    },

    viewByBzplCd: function (selector, bzplCd) {
        $(selector).attr('src', '');
        var url = '/erp/ajaxR/MHCommon/selectBzplImgByBzplCd.do?bzplCd=' + bzplCd+ '&timestamp=' + new Date().getTime();
        $(selector).attr('src', url);
    },

    empPicChk : function( params ) {
        var deferred = new $.Deferred();
        pierp.trx.request({
            url: contextPath + '/ajaxR/MHCommon/selectEmpPicChk.do',
            params : params,
            callback: function(status, resData) {
                if (status == 'success' && resData.status == 'success') {
                    var picYn = resData.objects.picYn;
                    if(picYn == 'N') {
                        pierp.ui.warn('해당 파일이 존재하지 않습니다.');
                        deferred.reject(false);
                    } else {
                        deferred.resolve(true);
                    }
                }
            }
        });
        return deferred.promise();
    }


    
        // function downloadImage(event){
            
        //     var params = {empUniqNo : '${commandMap.empNo}'};
        //     jsMH.pic.empPicChk(params).done(function(status) {
        //   		if(status) {
                        //동적생성 formID : pierp.glbalse/fn.js
        //   			cfn_SubmitInternal(
        // 					"_DownloadEmpPic"
        // 					,contextPath+'/ajaxR/MHCommon/downloadPicByEmpUniqNo.do'
        // 					,"post"
        // 					,params
        // 					,""
        // 			);
        //   		}
        // 	});
        // }

};




/*********** common function 함수정의  ***********/

jsAA.cfn = {
    // 정상적으로 데이터 처리가 되었는지 판별 , 호출 후 콜백함수 체크
    isAjaxTransactionResSuccess : function( data ){

        if( data.objects == null ||
            data.status == null ||
            data.status == 'error' ||
            data.status == 'fail' ||
            data.status == 'valid' ||
            data.status == 'invalid'
            )
            return false;

        return true;
    },
    
   // 그리드의 조회전 row로 selection을 복구
   // ex) jsMH.cfn.restoreGridSelection( '#list', { fwpAplNo : '201205001' }, true );
    restoreGridSelection : function( selector, args, evf ){

        const evt = ( evf != false )? true: evf;

        const grid = $(selector);
        const cnt = grid.getGridParam( 'records' );

        if( args == null && cnt > 0 ){
            grid.setSelection( 0, evt );
        } else if( args != null && cnt > 0 ){
            const rowid = grid.findRowidEx( args );
            grid.setSelection( rowid, evt );
        }
    },

    //사용자 정보를 리턴합니다.
    getUserInfo : function( empUniqNo ){

        const rtn = {};
        pierp.trx.request({
            url       : '/erp/ajaxR/MHCommon/getEmpInfo.do',
            params     : { empUniqNo : empUniqNo },
            showMsg   : false,
            async     : false,
            validator : false,
            cfrmMsgConvertor: function(gridSmmry, defMsg) {
                return null;
            },
            callback  : function( status, data ){
                if (jsAA.cfn.isAjaxTransactionResSuccess(data)) {
                    rtn = data.objects['info'];
                }
            }
        });
        return rtn;
    },


    //사용자 정보를 리턴합니다.(deptNo 로 조회)
    getUserInfo3 : function( deptCd ){

        const rtn = {};
        ajaxTransaction({
            iConsts     : { deptCd : deptCd },
            url       : '/erp/ajaxR/MHCommon/getEmpInfo3.do',
            showMsg   : false,
            async     : false,
            callback  : function( status, data ){
                if (jsAA.cfn.isAjaxTransactionResSuccess(data)) {
                    rtn = data.objects['info'];
                }
            }
        });

        return rtn;
    },


    /*******************************************************************************
     * function :  button enable/disable set!
     * argument : _objectID (buttonID)
     *            _boolAble (true/false)
     *            _cursor   (hand/non) style 변경용
     ex) setButtonAbleWithAuth('buttonID1', true, 'buttonID2', false ,.....)
     *******************************************************************************/
    setButtonAbleWithAuth : function(){
        const argCnt = jsAA.cfn.setButtonAbleWithAuth.arguments.length;
        const _objectID = '';
        const _boolAble = '';

        if(argCnt%2 == 1) {
            alert('button set error');
            return;
        }

        for(const i = 0; i<argCnt; i++){
            if(i%2 ==0){
                _objectID   = '#'+jsMH.cfn.setButtonAbleWithAuth.arguments[i];
                _boolAble   = (i < argCnt-1) ? jsMH.cfn.setButtonAbleWithAuth.arguments[i+1] : jsMH.cfn.setButtonAbleWithAuth.arguments[i];
             if($(_objectID)){
                    $(_objectID).disableObj( _boolAble );
                    i++;
                    continue;
                }else{
                    alert('button ID error');
                    break;
                }
           }
        }
    },
};


/*********** 공통팝업 정의  ***********/
jsAA.popup = {

    	
	// 신청교육과정선택팝업(MHI0108P1)
    MHI0108P1Pop : function( param, callback) {
        cfn_PopUpModule({
            mdulId : 'MHI0108P1',
            params : param,
            width  : 800,
            height : 450
        });

        jsMH.popup.MHI0108P1Pop.cb_Set = function( data, rowid ){
            if( $.isFunction(callback) ){
                callback( data, rowid );
            }
        };
    },

    /// 정원등록 (MHE0121P1)
    MHD0121P1Pop : function( param, callback) {
        cfn_PopUpModule({
            mdulId : 'MHD0121P1',
            params : param,
            width  : 1000,
            height : 600
       });
        jsMH.popup.MHD0121P1Pop.cb_Set = function( data, rowid ){
           if( $.isFunction(callback) ){
               callback( data, rowid );
           }
       };
    },

    // 조직개편 부서 조회(MHA0202H1)
    orgzChgDept : function( param, callback) {
        cfn_PopUpModule({
            mdulId : 'MHC0505P1',
            params : param,
            width  : 580,
            height : 370
        });
        jsMH.popup.orgzChgDept.cb_Set = function( data, rowid ){
            if( $.isFunction(callback) ){
                callback( data, rowid );
            }
        };
    },

    // 지급계좌 조회(MHJ0105P1)
    actrAccn : function( param, callback) {
        cfn_PopUpModule({
            mdulId : 'MHJ0201P1',
            params : param,
            width  : 900,
            height : 450
        });
        jsMH.popup.actrAccn.cb_Set = function( data, rowid ){

            // 대표계좌 설정
            jsMH.cfn.setPublicActrAccn( data.actrCd, data.srno );

            if( $.isFunction(callback) ){
                callback( data, rowid );
            }
        };
    },
    actrAccn : function( param, callback) {
        cfn_PopUpModule({
            mdulId : 'MHJ0201P1',
            params : param,
            width  : 900,
            height : 450
       });
       jsMH.popup.actrAccn.cb_Set = function( data, rowid ){

           // 대표계좌 설정
           jsMH.cfn.setPublicActrAccn( data.actrCd, data.srno );

           if( $.isFunction(callback) ){
               callback( data, rowid );
           }
       };
    },


    // 최근 계좌정보로 설정함		
    setPublicActrAccn : function( actrCd, srno ){
        const rtn = {};
        pierp.trx.request({
            params    : { actrCd : actrCd, srno : srno },
            url       : '/erp/ajaxS/MHCommon/setPublicActrAccn.do',
            async     : false,
            callback  : function( status, data ){
                if (jsAA.cfn.isAjaxTransactionResSuccess(data)) {
                    rtn = data.objects['info'];
                }
            }
        });
        return rtn;
    },

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


/*********** fmt  ***********/

jsAA.fmt = {

     // 주민등록번호 체크, param value : 1111111111111(-없이)     
     isIhidnum : function ( value ) {
        if( value.length != 13 ) {
            return false;
        }
        const str_jumin1 = value.substring(0,6);
        const str_jumin2 = value.substring(6);

        if((str_jumin1.length > 7) || (str_jumin2.length > 8)) {
            return false;
        }
        const i3=0;
        for (const i=0;i<str_jumin1.length;i++) {
            if(Number(str_jumin1[i])<0 || Number(str_jumin1[i])>9) {
               i3=i3+1;
            }
        }
        if((str_jumin1 == '') || ( i3 != 0 )) {
            return false;
        }
        const i4=0;
        for(const i=0;i<str_jumin2.length;i++) {
            if (Number(str_jumin2[i])<0 || Number(str_jumin2[i])>9){
                i4=i4+1;
            }
        }
        if((str_jumin2 == '') || ( i4 != 0 )) {
            return false;
        }
        const month = str_jumin1.substring(2,4);
        if ( (month < "01") || (month > "12") ) {
            return false;
        }
        const day = str_jumin1.substring(4,2);
        if ( (day < "01")   || (day > "31")  ) {
            return false;
        }
        const sexAndMillennium =   str_jumin2.substring(0,1);

        if ( (sexAndMillennium >= "1" )  && (sexAndMillennium <= "4" ) ){
            const f1=str_jumin1.substring(0,1);
            const f2=str_jumin1.substring(1,2);
            const f3=str_jumin1.substring(2,3);
            const f4=str_jumin1.substring(3,4);
            const f5=str_jumin1.substring(4,5);
            const f6=str_jumin1.substring(5,6);
            const hap=f1*2+f2*3+f3*4+f4*5+f5*6+f6*7;
            const l1=str_jumin2.substring(0,1);
            const l2=str_jumin2.substring(1,2);
            const l3=str_jumin2.substring(2,3);
            const l4=str_jumin2.substring(3,4);
            const l5=str_jumin2.substring(4,5);
            const l6=str_jumin2.substring(5,6);
            const l7=str_jumin2.substring(6,7);
            hap=hap+l1*8+l2*9+l3*2+l4*3+l5*4+l6*5;
            hap=hap%11;
            hap=11-hap;
            hap=hap%10;

            if (hap != l7) {
                return false;
            }
        }else if((sexAndMillennium >= "5" ) && (sexAndMillennium <= "8" )){
            const sum=0;
            const odd=0;
            buf = new Array(13);
            for(i=0; i<13; i++) { buf[i]=parseInt(value.charAt(i)); }
            odd = buf[7]*10 + buf[8];
            if(odd%2 != 0) {
                return true;
            }
            if( (buf[11]!=7) && (buf[11]!=8) && (buf[11]!=9) ) {
                    return true;
            }
            multipliers = [2,3,4,5,6,7,8,9,2,3,4,5];
            for(i=0, sum=0; i<12; i++) {
                sum += (buf[i] *= multipliers[i]);
            }
            sum = 11 - (sum%11);
            if(sum >= 10) {
                sum -= 10;
            }
            sum += 2;
            if(sum >= 10) {
                sum -= 10;
            }
            if(sum != buf[12]) {
                return true;
            }	//20160812 외국인 주민등록번호 뒤 검증 로직 제외
            return true;
        }else{
            return false;
        }

        return true;
    },

    // 사업자등록번호 유효성 체크,  param value : 1111111111(-없이)
    isBzmnRegtNo : function ( value ) {
        if(value.length != 10) return false;
        const checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1);
        var chkSum = 0, c2, remander;
        for(const i = 0; i <= 7; i++) chkSum += checkID[i] * value.charAt(i);
        c2 = "0" + (checkID[8] * value.charAt(8));
        c2 = c2.substring(c2.length - 2, c2.length);
        chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1));
        remander = (10 - (chkSum % 10)) % 10 ;
        if (Math.floor(value.charAt(9)) == remander){
            return true;
        }else{
            return false;
        };
    },

    // 201204 -> 2012-04
    DateYm		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        const val = cellvalue;
        const rtnStr = val.substring(0,4) + '-' + val.substring(4,6);
        return rtnStr;
    },
   
    // 2012-04 -> 201204
    unDateYm	: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        return cellvalue.replace( '-', '' );
    },
   
    // 20120401 -> 2012-04-01
    Date		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        const val = cellvalue;
        const rtnStr = val.substring(0,4) + '-' + val.substring(4,6)+ '-' + val.substring(6,8);
        return rtnStr;
    },
    
    // 2012-04 -> 201204
    unDate	: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        return cellvalue.replace( '-', '' );
    },


    // 0900 -> 09:00
    Time		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        if( cellvalue.length != 4 ) return '';
        const val = cellvalue;
        const rtnStr = val.substring(0,2) + ':' + val.substring(2,4);
        return rtnStr;
    },
   
    // 09:00 -> 0900
    unTime		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        return cellvalue.replace( ':', '' );
    },

    // 095834 -> 09:58:34
    Time2		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        if( cellvalue.length != 6 ) return '';
        const val = cellvalue;
        const rtnStr = val.substring(0,2) + ':' + val.substring(2,4) + ':' + val.substring(4,6);
        return rtnStr;
    },

    // 09:58:34  -> 095834
    unTime2		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        return cellvalue.replaceAll( ':', '' );
    },

    // 20140527213424 -> 2014.05.27 21:34
    Time3		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        if( cellvalue.length != 14 ) return '';
        const val = cellvalue;
        const rtnStr = val.substring(0,4) + '.' + val.substring(4,6) + '.' + val.substring(6,8)+ ' ' + val.substring(8,10) + ':' + val.substring(10,12);
        return rtnStr;
    },
    
    // 2014.05.27 21:34:24   -> 201405272134
    unTime3		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        cellvalue.replaceAll( '-', '' );
        cellvalue.replaceAll( ' ', '' );
        cellvalue.replaceAll( '.', '' );
        return cellvalue.replaceAll( ':', '' );
    },

    // 20140527213424 -> 2014.05.27 21:34:24
     Time4		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        if( cellvalue.length != 14 ) return '';
        const val = cellvalue;
        const rtnStr = val.substring(0,4) + '.' + val.substring(4,6) + '.' + val.substring(6,8)+ ' ' + val.substring(8,10) + ':' + val.substring(10,12)+':' + val.substring(12,14);
        return rtnStr;
    },
    
    // 2014.05.27 21:34:24   -> 20140527213424
    unTime4		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        const cell = cellvalue.replaceAll( '-', '' );
        cell = cell.replaceAll( ' ', '' );
        cell = cell.replaceAll( '.', '' );
        cell = cell.replaceAll( ':', '' );
        return cell;
    },


    // 0900 -> 09:00 valid 24:00
    Time5		: function(cellvalue, options, rowObject) {
        if (!cellvalue) return '';
        if (cellvalue.length != 4) return '';
        if (cellvalue.substring(0, 2) > 24) cellvalue = '24' + cellvalue.substring(2, 4);
        if (cellvalue.substring(2, 4) > 1) cellvalue = cellvalue.substring(0, 2) + '30';


        const val = cellvalue;

        const rtnStr = val.substring(0,2) + ':' + val.substring(2,4);
        return rtnStr;
    },
    
    // 09:00 -> 0900
    unTime5		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        return cellvalue.replace( ':', '' );
    },


    // 123456 -> 123-456
    ZipNo		: function(cellvalue, options, rowObject) {
        //if( cellvalue.length != 6 ) return '';
        //const zipNo = cellvalue;
        //const rtnStr = zipNo.substring(0,3) + zipNo.substring(3,6);
        //const rtnStr = zipNo;
        return cellvalue;
    },
    /**
     * jsMH.fmt.unZipNo
     * 123-456 -> 123456
     */
    unZipNo		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        return cellvalue.replace( '', '' );
    },


    // 1111111111111 -> 111111-1111111
    RegNo		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        if( cellvalue.length != 13 ) return '';
        const regNo = cellvalue;
        const rtnStr = regNo.substring(0,6) + '-' + regNo.substring(6,13);
        return rtnStr;
    },
    AskNo		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        if( cellvalue.length != 15 ) return '';
        const regNo = cellvalue;
        const rtnStr = regNo.substring(0,10) + '-' + regNo.substring(10,13) + '-' + regNo.substring(13,15);
        return rtnStr;
    },
    
    // 111111-1111111 -> 1111111111111
    unRegNo		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        return cellvalue.replace( '-', '' );
    },
    unAskNo		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        return cellvalue.replace( '-', '' ).replace( '-', '' );
    },
    /**
     * jsMH.fmt.btnAttch
     * 첨부파일 화면 링크
     * cb_OpenAttachFile( gid, rowId )를 구현해야함!!!
     * 그리드상의 첨부파일이 여러개일경우 각자 구현한 cb_OpenAttachFile함수내에서 분기처리 함.
     */
    btnAttch   : function( cellvalue, options, rowObj ) {

        return '<img '
        + 'src="'+contextPath+'/resources/pierp/images/icon_disk.gif" '
        + 'style="cursor:pointer;" '
        + 'onclick="'
        + 'cb_OpenAttachFile('
                + '  \'' + options.gid +'\''
                + ', \'' + options.rowId + '\''
//					+ ', \'' + attchDoctId + '\''
        + ');" >';
    },
   
    // 1234567890123456-> 1234-5678-9012-3456
    CardNo		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        const val = cellvalue;
        const rtnStr = val.substring(0,4) + '-' + val.substring(4,8)+ '-' + val.substring(8,12)+ '-' + val.substring(12);
        return rtnStr;
    },
   
    // 1234-5678-9012-3456 -> 1234567890123456
    unCardNo	: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';

        return cellvalue.replace( /-/g, '' );
    },
   
    // 1309090065 -> 13-090-90065
    ExmNo		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        const val = cellvalue;
        const rtnStr = val.substring(0,2) + '-' + val.substring(2,5)+ '-' + val.substring(5);
        return rtnStr;
    },
   
    // 13-090-90065 -> 1309090065
    unExmNo	: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';

        return cellvalue.replace( /-/g, '' );
    },


    AplNo : function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        const val = cellvalue;
        const rtnStr = val.substring(0,4) + '-' + val.substring(4,6)+ '-' + val.substring(6,10);
        return rtnStr;
    },


    unAplNo	: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        return cellvalue.replace( /-/g, '' );
    },


    // 1111111111111 -> 111111-1XXXXXX
    RegNo2		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        if( cellvalue.length != 13 ) return '';
        const regNo = cellvalue;
        const rtnStr = regNo.substring(0,6) + '-' + regNo.substring(6,7) + 'xxxxxx';
        return rtnStr;
    },
   
    // 1111111111111 -> 111111-1111111
    RegNo3		: function(cellvalue, options, rowObject) {
        const cellValue = cellvalue.replaceAll( '-', '' );
        if( !cellValue ) return '';
        if( cellValue.length != 13 ) return '';
        const regNo = cellValue;
        const rtnStr = regNo.substring(0,6) + '-' + regNo.substring(6,13);
        return rtnStr;
    },
     
    // 자릿수 validation X 단 무조건 6자리이상이어야함

    RegNo4		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        const cellValue = cellvalue.replaceAll( '-', '' );
        const regNo = cellValue;
        const rtnStr = cellValue;
        if( cellvalue.length > 6 ) {
            rtnStr = regNo.substring(0,6) + '-' +  regNo.substring(6);
        }
        return rtnStr;
    },


    // 자릿수 validation X 단 무조건 6자리이상이어야함, ***처리 해주는거   
    RegNo5		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        const cellValue = cellvalue.replaceAll( '-', '' );
        const regNo = cellValue;
        const rtnStr = cellValue;
        if( cellvalue.length > 6 ) {
            rtnStr = regNo.substring(0,6) + '-' + regNo.substring(6,7) + '******';
        }
        return rtnStr;
    },


    // 201204011000 -> 2012-04-01 10:00
    DateTime		: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        const val = cellvalue;
        const rtnStr = val.substring(0,4) + '-' + val.substring(4,6)+ '-' + val.substring(6,8)+' '+ val.substring(8,10)+':'+val.substring(10,12);
        return rtnStr;
    },
   
    // 2012-04-01 10:00 -> 201204011000
    unDateTime	: function(cellvalue, options, rowObject) {
        if( !cellvalue ) return '';
        cellvalue.replaceAll( '-', '' );
        cellvalue.replaceAll( ' ', '' );
        return cellvalue.replaceAll( ':', '' );
    }

};

