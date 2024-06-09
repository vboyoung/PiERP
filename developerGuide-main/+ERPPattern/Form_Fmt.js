

    //초기화
    $("#btnInit").click(function(){
        
        //formdata 세팅
		$("#search").resetFormData({
			srcYear : '${srcYear}',
			srcAstsKindCd : '${srcAstsKinCd}'
		});
		$("#mngeList").clearGridData();
		$("#list").clearGridData();
		fn_SelectValsSuvyNos('${srcYear}');

		// Radio 전체선택
        $('input[name=reftStatus][value=""]').attr('checked', true);

	})


    /* select시 재물조사번호 조회 */
    <pierp:select id="srcValsSuvyNo" name="srcValsSuvyNo" mode="layer" required="true" first="선택해주세요" width="600px" title="재물조사일자/명"/>
    
    function fn_SelectValsSuvyNos(srcYear) {

        pierp.ui.component.ajaxLoadSelect($('#search [name="srcValsSuvyNo"]'), {
            sid : 'bizMA.sb_selectValsSuvyNo',
            params: {
                scode: $('#srcYear').val()
            },
            first : "선택"
            }).done(function(data){
            //완료 후 동작
            $("#srcValsSuvyNo option:selected").removeAttr("selected");
            $('#srcValsSuvyNo .label-inner').text("선택");

            $('#search').setFormData({
                srcValsSuvyNo :  data.codeNm
            });

            /* $('#btnSearch').trigger("click"); */
            });
    };


    <pierp:date name="srcYear" id="srcYear" edittype="date_y" required="true" value="${srcYear}"  title="조사연도" />

    



    <pierp:text id="astsGvbRson" name="astsGvbRson" title="반려사유"/>

    
    /* 라디오 */
    
    <pierp:radio name="reftStatus" value="" checked="checked" label="전체"/>&nbsp;&nbsp;
    <pierp:radio name="reftStatus" value="1" label="반영대상"/>&nbsp;&nbsp;
    <pierp:radio name="reftStatus" value="2" label="반영"/>
    $('input[name=reftStatus]').change(function(){ 
    
    });


    <pierp:radiogroup  name="useYn" cid="SY000" first="전체" title="사용여부"/>

    <textarea title="근거법령내용" name="grndSttuCnts" rows="2" style="width:95%" readonly="false" />${vo.grndSttuCnts}</textarea>

    /* 버튼 */
    <pierp:button bttn="${bttnMap['btnGvb']}" id="btnGvb" label="반려"  type="2" />
    $('#btnGvb').click(function() {

    });



     /* 직원검색  */
    
    <pierp:search type="search2" name="srcUsrEmpNm" name2="srcUsrEmpNo"  style="width:80px" style2="width:50px" show2="true" value2="" maxlength="10" title="사용자 조회"/>
	
    $('#search [name="srcUsrEmpNm"]').setSearchHandler({
		srchFunc : function(params, callback){
			cfn_SearchEmpPop(params, callback);
		},
		getParams : function(autoSelect){
			return  {
					empNm: $('#search [name="srcUsrEmpNm"]').val()
					,autoSelect : false
			};
		},
		callback : function(data,rowId){
			$("#search").setFormData({
				srcUsrEmpNm : data.empNm,
				srcUsrEmpNo : data.empNo
			})
		},
		clearSync : function(){
		    $('#search [name="srcUsrEmpNm"]').val('');
		    $('#search [name="srcUsrEmpNo"]').val('');
		}
	});




   
    {/* 부서검색  */}

    <pierp:search type="search2" name="srcUseDeptNm" name2="srcUseDeptCd"  style="width:80px" style2="width:50px" show2="true" value2="" title="사용 부서조회"/>

        $('#search [name="srcUseDeptNm"]').setSearchHandler({
            srchFunc : function(params, callback){
                cfn_SearchDeptPop(params, callback);
            },
            getParams : function(autoSelect){
                return  {
                        deptNm: $('#search [name="srcUseDeptNm"]').val()
                        ,autoSelect : false
                        };
            },
            callback : function(data,rowId){
    /* 		    $('#search [name="srcuseDeptNm"]').val(data[0].deptNm);
                $('#search [name="srcuseDeptCd"]').val(data[0].deptCd); */

                $("#search").setFormData({
                    srcUseDeptNm : data[0].deptNm,
                    srcUseDeptCd : data[0].deptCd
                })
            },
            clearSync : function(){
                $('#search [name="srcUseDeptNm"]').val('');
                $('#search [name="srcUseDeptCd"]').val('');
            }
        });




        $('#btnInit').trigger('click');



