

/* 리스크항목관리(핵심위험지표 ID) :  공통코드 */
    SELECT COM_CD_CNTS 
    FROM VW_SY_COM_CD_DTL
    WHERE COM_CLSS_CD = 'AD028'
    AND COM_CD= '12'


/* SYS : 권한 설정-> 권한ID 조회 */
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


    -- auth 받아온 후 페이지 오픈 시  조회조건 걸기
        <isEqual property="q_adminAuth" compareValue="N">
            -- 학생인건비 과제목록 조회 권한
            AND ( A.RESPER_EMP_NO = #sesEmpNo#  OR 
                A.BUDG_ITEM IN ( SELECT  A.BUDG_ITEM
                                    FROM    TB_BDG_BUDG_PARTI A
                                    WHERE   1 = 1   
                                    AND     A.EMP_NO = #sesEmpNo#                                      
                                )
            )
        </isEqual>       

