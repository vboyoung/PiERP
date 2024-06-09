
0. 기본
- form은 웹페이지의 정보를 다른페이지로 전송하는 역할, 데이터를 주고받으며 움직이는
  웹 페이지일 경우 반드시 들어가는 태그요소
  action, method(get/post), data(name/value)

- 페이지 이동 시 searchForm data 들을 goPage(url, paramForm, appendText)를 통해 같이 전달

- getFormData
- postJson / postAjax 차이 
  postJson은 Url에 파라미터로 합쳐서 보낼때
  이때 파라미터를 $("#serachForm").serialize(): 쿼리스트링형태  serializeObject() : 객체형태

  postAjax는 객체 형태로 보낼 때
  이때 getFormData($('#sendForm'), 0); 을 이용해 객체형태로 변환시켜준 뒤 postAjax에 넣어준다.

- searchForm, paramForm, zForm, applyForm (목록 적용시 사용), mnuPqueForm (저장,삭제시 각 업무에 필요한 폼)

- SearchCmd : searchField, searchText, rangeStratDate, rangeEndDate
  ParamCmd : Qmap, Map, Keys, copyBasis - regEmpNo,regUsrId,redDt,lastChgEmpNo,lastChgUsrId,lastChgDt
 
  FormCmd : T form -  사용자 정의형 Form 파라미터 전달용이 필요한 경우 사용 (FormCmd<MnuPqueForm> mnuPqueParam = new FormCmd<>();)
  MnuPqueForm : 저장 및 삭제 시 사용하는 Form
  TbEipPqueDutyVO  -> EipPqueDutyVO 




<<<<<<<<<<<< MVC Pattern 

jsp -> controller -> service -> dao
- 조회 
    목록조회 : searchForm -> SearchCmd  (resDto.putAsGrid) -> SearchCmd -> SearchCmd
    한건조회 : zForm (searchForm ) -> SearchCmd  (resDto.put) -> SearchCmd -> SearchCmd

- 저장 
    목록저장(노출순서 저장) : applyForm (목록 menuId, lupOrd binding) -> listData, paramCmd -> mnuPqueParam, mnupqueForm
              
              1.목록형태 Form(listData)
              listData는 FormCmd<MnuPqueForm> mnuPqueParam = new FormCmd<>(); 
              setQmap을 이용해 목록형태의 폼 받기

              2.파라미터 paramCmd
              paramCmd는 new MnuPqueForm()을 이용해 
              해당업무입력시 사용한 setForm(mnuPqueForm) + copyBasis(pramCmd) 기본정보업데이트

              /* Controller 코드 확인 */
              FormCmd<MnuPqueForm> mnuPqueParam = new FormCmd<>();
              mnuPqueParam.setQmap(listData);			//목록형태의 폼 받기
              MnuPqueForm mnuPqueForm = new MnuPqueForm(); //해당업무 입력 시 사용한 form을 이용한다.
              mnuPqueForm.copyBasis(paramCmd);		//기본 정보 업데이트 시 필요
              mnuPqueParam.setForm(mnuPqueForm);		//기본 정보가 복사된 업무 form을 넘겨준다. 
              mnuo01Service.saveLupOrd(mnuPqueParam);
           

              /* Service 코드 확인 */
              MnuPqueForm mnuPqueForm = mnuPqueParam.getForm();
	            Map<String, ArrayList<String>> map = mnuPqueParam.getQmap();
              ArrayList<String> menuIdList = map.get("menuId"); 
	            ArrayList<String> lupOrdList = map.get("lupOrd");

              //목록 폼에서 받은 데이터 검증
              if(menuIdList.isEmpty() || lupOrdList.isEmpty()) {
                getLogger().error("노출순서 저장 오류::menuIdList OR menuIdList isEmpty ");
                getLogger().error("menuIdList :" + menuIdList.size());
                getLogger().error("lupOrdList :" + lupOrdList.size());
                throw processException("fail.common.runError",new String[]{"노출순서","저장오류 발생(1)"});			
              }

              //목록 폼에서 받은 데이터 검증(2)		
              if(menuIdList.size() != lupOrdList.size()) {
                getLogger().error("menuIdList :" + menuIdList.size());
                getLogger().error("lupOrdList :" + lupOrdList.size());			
                throw processException("fail.common.runError",new String[]{"노출순서","저장오류 발생(2)"});
              }
              
              //넘겨받은 목록 수 만큼 DB 처리.
              for(int i=0; i < menuIdList.size(); i++) {
                mnuPqueForm.setMenuId(menuIdList.get(i));
                mnuPqueForm.setLupOrd(lupOrdList.get(i));

                effectCnt += mnuo01Dao.updateLupOrd(mnuPqueForm);
              }		

              //업데이트 갯수 검증
              if (effectCnt != lupOrdList.size()) {
                getLogger().error("노출순서 저장 오류::업데이트된 건수와 다름");
                throw processException("fail.common.runError",new String[]{"노출순서","저장오류 발생(3)"});
              }	

                

    한건저장 :  mnuPqueForm (각 저장시 필요한 업무폼:Form) -> MnuPqueForm, uploader ->  MnuPqueForm, uploader  -> ParamCmd
                /* Controller 코드 확인 */
                mnuo01Service.savePqueDuty(mnuPqueForm,uploader);
              
                /* Service 코드 확인 */
                int nRet = 0;
                //폼 처리
                if(StringUtils.isEmpty(mnupqueForm.getMenuId())) {
                  nRet = mnuo01Dao.insertPqueDuty(mnupqueForm); //INSERT
                }else {
                  nRet = mnuo01Dao.updatePqueDuty(mnupqueForm); //UPDATE
                }
                
                uploader.setDocId(mnupqueForm.getMenuId());	//업무키 설정
                uploadService.updateFileInfo(uploader);
                
                //오류가 발생할 소지가 있는 부분에 대한 처리..
                if (nRet != 1) {
                  getLogger().error("현안업무 저장 오류");
                  throw processException("fail.common.runError",new String[]{"현안업무저장","저장시 오류가 발생 했습니다."});
                }



- 삭제
    목록삭제 : applyForm (목록 menuId, lupOrd binding) -> menuId, paramCmd -> mnuPqueParam, mnupqueForm
    한건삭제 : applyForm (목록 menuId, lupOrd binding) -> menuId, paramCmd -> mnuPqueParam, mnupqueForm

              /* Controller 코드 확인 */
              paramCmd.setSelectedIds(menuId);
    		      mnuo01Service.deletePqueDuty(paramCmd);
                
             /* setSelectedIds */
             public void setSelectedIds(String[] selectedIds) {
                this.selectedIds = new String[selectedIds.length];
                for(int i = 0; i < selectedIds.length ; i++) {
                  this.selectedIds[i] = selectedIds[i];
                }
              }
            
             /* Service 코드 확인 */
             int nRet = mnuo01Dao.deletePqueDuty(paramCmd);
		
             //오류 발생 처리(물리 파일 처리 보다 먼저 검사)
             if (nRet != paramCmd.getSelectedIds().length) {
               getLogger().error("현안업무 삭제 오류");
               throw processException("fail.common.runError",new String[]{"현안업무 삭제","요청건수와 삭제건수가 다릅니다."});
                 }		
             
             //실제 파일 삭제! (마지막에 할 것)
             TbSysAthflVO file = new TbSysAthflVO();
         
             String[] menuId = paramCmd.getSelectedIds();
             if(menuId !=  null) {
               for(int i = 0 ; i < menuId.length ; i++) {
                 file.setProgramId("EIP_MNU");
                 file.setDocId(menuId[i]);
                 uploadService.deleteFileInfo(file);
               }
             }


- 수정
  한건 수정 





<<<<<<<<<<<JSP

1. 조회
- pageLoad시 조회
  $(documnet).ready(function() {
    //pieip.trx.postJson 처리후 추가 로직이 있을경우.
    search().done(function() { console.log("logic add") });  
  })


- 목록 조회, pieip.trx.js : postJson (post) 
  var params = $("#searchForm").serialize();
  return pieip.trx.postJson(contextPath + "/ajaxR/MNUO0101/selectPqueDutyList.do?" + params, function(data) {
      if(data.status != "success") {
          pieip.page.printListMsg(10, data.serviceMessagae);
      }else {
        var resObject = data.objects;

        //no data
        if(resObject.paueDutyVoList.records == "0") {
            pieip.page.printListMsg(10, "데이터가 없습니다.")
        }

        /* success data */

        //건수조회
        pieip.page.renderingPaging(contextPath + "/ajaxR/MNUO0101/selectPqueDutyCnt.do");  
       
        //목록 조회
        $.each(resObject.pqueDutyVOList.rows, function(index, vo) {
            appendListRow(index, vo);  //tbody에 binding
        }); 

        //sortFiled, direction  
        pieip.page.sortStyle();

      }
  }).fail(function() {
    pieip.page.printListMsg(10, "데이터 조회 오류입니다.")
  });



- 상세조회 (목록 리스트 중 선택 후 상세페이지 이동)
  1. viewDetail(obj) ->  goPage(url, paramForm, appendText) 
 
  2. getSelectOne() 

  	var uploader;
    if("${cmd.selectedId}" != ""){
  		getSelectOne().done(function(){
  			uploader = createUploader();
  		});		
    }else {
        uploader = createUploader();
        pieip.ui.disableBtn( $("#btnDelete") );	//unbind event!
    }

    function getSelectOne() {
      var params = $("#searchForm").serialize();
      return pieip.trx.postJson(contextPath +"/ajaxR/MNUO0102/selectPqueDutyOne.do?"+params, function (data){
        if (data.status != "success") { //EgovBizException
          pierp.ui.alert(data.serviceMessage);
        }else{
          var resObject = data.objects;
          itemDispatch(resObject.pqueDutyVO);
        }
        }).fail(function(){
          pierp.ui.alert("데이터 조회 오류입니다.");
      });
    }
    
- 상세페이지에서 이전 목록조회 : (goList() -> goPage(url, paramForm, appendText))
  
    goPage()
    sitemesh - decorator_mgt의 zForm(post)을 이용해 특정페이지로 이동
    <form name="zForm" id="zForm" method="post">
        <input type="hidden" name="selMenuId" value="${param.selMenuId}" />
    </form>
                
    zForm : action(url), searchForm(paramForm), selectedId(appendText) -> submit();


2. 저장 

- 한건 저장, pierp.trx.request (파일업로드 때문에 erp로 호출)
  var mnuPqueForm = $("#mnuPqueForm").serializeObject(); //jquery.serialize-object
  pierp.trx.request({
    block : true,
      url : '<c:url value="/ajaxS/MNUO0102/savePqueDuty.do" />',
      params : {
        mnuPqueForm : mnuPqueForm
      },
      iDSs: [
        {type:'file', id:'uploader', uploader: uploader}      
        //파일업로드 처리 id값이 controller의 파라미터명과 동일해야함.
      ], 
      callback: function(status, objects) {
          console.log(objects);
          if(objects.status == "success") {
            //일반적으로 성공했을때 수행..
          }
      }, 
      eip: function(status, objects) {
        if(objects.status == "success") {
            goList(); //목록으로
        }
      }//eip
  });	


- 목록 저장 
  var formData = $("#applyForm").serialize();
	pierp.trx.request({
    block: true,
    url: '<c:url value="/ajaxS/MNUO0101/saveLupOrd.do" />',
    params: {
      listData : formData
    },
    callback: function(status, objects) {
      if(objects.status == "success") { 

      }
    },eip: function(status, objects){
    if(objects.status == "success") {
          search();
        }
      }
  }); 


3. 삭제
- 단건 삭제 remove() : menuId key 한건만 넘긴다.

  var menuId = $("#menuId").val();  //selectId
  if(menuId == "") return; 
     
  var message = "삭제하시겠습니까?";
  pierp.ui.confirm(message).then(function(btn) {
    if(btn === 'no') return;
    return pieip.trx.postJson(contextPath + "/ajaxD/MNUO0101/deletePqueDuty.do?menuId="+ menuId, function(data) {
        if(data.status != "success") {  
            pierp.ui.alert(data.serviceMessage);
        }else {
            goList();
        }
    }).fail(function() { pierp.ui.alert("서버오류입니다."); });

  });

- 목록 체크 삭제 checkedDelete(), remove() : menuId 여러개를 applyForm에 append해서 같이 넘긴다.
  applyForm에 menuId 체크 후 같이 넘기기
    var chkCnt = 0;
    
    $("#applyForm").html(""); //적용폼 초기화
    $("#DataList > tbody").find("input[type=checkbox]").each(function() {
        if($(this).is(":checked")){
          chkCnt++;
          $("#applyForm").append('<input type="hidden" name="menuId" value="' + $(this).val() + '">');
        }
    });
    
    if(chkCnt <= 0) {
      pierp.ui.alert("삭제 할 항목을 체크해 주세요.");
        return;
    }
    
    var message = "선택하신 현안업무명이 삭제 됩니다.\n삭제하시겠습니까?";
    pierp.ui.confirm(message).then(function(btn) {
      if (btn === 'no') {
          return;
        }
        removeList("delete");
    }); 

  removeList()
    var url = '<c:url value="/ajaxD/MNUO0101/deletePqueDuty.do" />';
    var formData = pieip.trx.getFormData($('#applyForm'), 0); //$form , form 인덱스 (동일 id 갯수가 여러개 일때)
    
    pieip.trx.postAjax(url, formData, function(){
      search();	
    });




