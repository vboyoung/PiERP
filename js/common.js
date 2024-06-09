
/**************************** GRID ***************************/



function fn_init(){

}






//grid prop 변경
function fn_gridEdit(t_f){
    $('#list').setColProp('bznsNm',           {editable:t_f});  // 소속기관
    $('#list').setColProp('bznsPsitNm',       {editable:t_f});  // 직위
}







/*************************** Control **************************/


/**
 * input, button control
 * pierp.ui : disableObj, readonlyObj, blockBtn, getBlock
 */





// 결재상태값에 따른 input, btn 처리
function fn_DisableObjMstBox(t_f){
    pierp.ui.disableObj([
      '[name="wrpDeptCd"]','[name="wrpDeptNm"]', '[name="deptUniqNo"]',
      '#btn_wrpDeptNm', '#btn_wrpEmpNM'
     ], t_f); 
}


switch(snctnStatDivCd) {
   
    case "00" : {
      //00: 기안중(저장)
      pierp.ui.disableObj(['#btnSave', '#btnDelete', '#btnAppReq'
                          ,'#btnAddTarget', '#btnDelTarget'
                          ,'#btnAddDiff', '#btnDelDiff'], false);
      fn_DisableObjMstBox(false);
      fn_gridEdit(true);
      break;
    }

    case "01" : {
      //01: 요청
      pierp.ui.disableObj(['#btnSave', '#btnDelete', '#btnAppReq'
                          ,'#btnAddTarget', '#btnDelTarget'
                          ,'#btnAddDiff', '#btnDelDiff'], true);
      fn_DisableObjMstBox(true);
      fn_gridEdit(!true);
      break;
    }

    case "02" : {
      //02:결재진행 
      pierp.ui.disableObj(['#btnSave', '#btnDelete', '#btnAppReq'
                          ,'#btnAddTarget', '#btnDelTarget'
                          ,'#btnAddDiff', '#btnDelDiff'], true);
      fn_DisableObjMstBox(true);
      fn_gridEdit(!true);
      break;
    }

    case "03" : {
     //03:반려됨
      pierp.ui.disableObj(['#btnSave', '#btnDelete'
                          ,'#btnAddTarget', '#btnDelTarget'
                          ,'#btnAddDiff', '#btnDelDiff'], false);
      pierp.ui.disableObj('#btnAppReq', true);
      fn_DisableObjMstBox(false);

      $('#snctnOpnn').show();

      break;
    }

    case "05" : {
      //05:결재완료
      pierp.ui.disableObj(['#btnSave', '#btnDelete', '#btnAppReq'
                          ,'#btnAddTarget', '#btnDelTarget'
                          ,'#btnAddDiff', '#btnDelDiff'], true);
      fn_DisableObjMstBox(true);
      fn_gridEdit(!true);
      break;
    }
  }












/**************************** Mst BOX ***************************/


