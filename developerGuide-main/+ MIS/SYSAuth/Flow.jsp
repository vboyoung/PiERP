
@RequestMapping("/popup/TMPH0401.do")
public ModelAndView mainPopupTMPH0401(@RequestParam Map<String, Object> commandMap, @InjectSessionInfo ParamVO param) throws BaseException {

    UserInfo user = getUserInfo();
    ModelAndView mv = new ModelAndView("mis/bizTMP/tmph/TMPH0401");
    
    //Auth 조회
    String auth = hRMCommonService.getAuthYn("TMPH0401", getUserInfo().getUsrId(),"R_HRM,TM999");
    mv.addObject( "auth", auth );
    
    return mv;
}


//hRMCommonService getAuthYn
String getAuthYn(String mdulId, String usrId, String auths);


//hRMCommonService getAuthYn
@Override
public String getAuthYn(String mdulId, String usrId, String auths) {
    
    final String sysId = sysPropService.getString("system.sysId");
    String yn = "";
    
    List<String> list = new ArrayList<>();
    ParamVO param = new ParamVO(); 
    String[] spliStr = auths.split(",");
    for(int i=0 ; i<spliStr.length ; i++) {
        list.add(spliStr[i]);
    }
    
    param.put("mdulId", mdulId);
    param.put("sysId", sysId);
    param.put("usrId", usrId);
    param.put("auths", list);
    
    int cnt = hrmCommonDAO.getAuthYn(param);
    
    //cnt ? Y  : N
    if(cnt > 0 ) {
        yn = cnt ? Y : N; 
    }
    
    return yn;
}


//HRMCommonDAO 
public int getAuthYn(ParamVO params) {
    return selectOne("HRMCommon.getAuthYn", params.getMap());
}

//HRMCommon_SQL
<select id="getAuthYn" parameterClass="map" resultClass="integer"><![CDATA[

/* 인사담당자 여부 확인 */
SELECT COUNT(1) AS CNT
  FROM VI_SYS_AUHT_MDUL
 WHERE 1=1
   AND SYS_ID = #sysId#
   AND MDUL_ID = #mdulId#
   AND AUHT_ID IN (SELECT AUHT_ID FROM VI_SYS_USR_AUHT
                    WHERE SYS_ID = #sysId#
                      AND USR_ID = #usrId#

                    <isNotEmpty property="auths">
                      AND AUHT_ID IN  
                    <iterate property="auths" open="(" close=")" conjunction=",">
                        #auths[]#
                    </iterate>
                    </isNotEmpty>
                 )
 
]]></select>



//TMPH04_SQL 
/* auth 받아온 후 페이지 오픈 시  조회조건 걸기 */

<isEqual property="q_adminAuth" compareValue="N">
/* 학생인건비 과제목록 조회 권한 */
AND ( A.RESPER_EMP_NO = #sesEmpNo#  OR 
     A.BUDG_ITEM IN ( SELECT  A.BUDG_ITEM
                      FROM    TB_BDG_BUDG_PARTI A
                      WHERE   1 = 1   
                      AND     A.EMP_NO = #sesEmpNo#                                      
                    )
)
</isEqual>