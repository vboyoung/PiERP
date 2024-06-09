/* codeNm 출력 처리 - v0.1 - 11/05/2020
 *  EIP JS용  프로퍼티 - 코드명
 *
 * http://itmate.co.kr/
 * Copyright (c) 2020 "itmate"
 *
 *
 * require :
 *
 *
 * call sample...
 * var arg = "A";
 * console.log(eip.prop.getVal("fdrmBtmlmtSe", arg));
 */

function __invokeEipPropGetVal(propNm, arg) {
  return eip.prop.getVal(propNm, arg);
}

var eip = {
  prop : {
    getVal: function(propNm, arg) {
      return this[propNm][arg];
      //return eval("this." + propNm + "." + arg); //eval은 안데~~
    },
    // ----------------------------- //

    //종일일정 구분 (샘플)
    alldaySchdYn : {
      Y : '종일'
      ,N: '아님'
    }

    //적용여부
    , aplyYn : {
        Y : '적용'
       ,N : '미적용'
    }

    //임시저장 상태
    , tmpSaveYn : {
        Y : '임시저장'
       ,N : '저장'
    }

    //게시판성경
    , brdClasCls : {
      A : '일반'
      ,B: '비공개'
      ,C: '익명'
    }

    // 회의실 위치
    , mtgIplaPos : {
      A : '대전'
     ,B : '광주'
     ,C : '울산'
     ,D : '제주'
    }

    // 등록 위치
    , rgtrPos : {
      A : '포털'
     ,B : 'MIS'
     ,C : 'PMS'
    }

    // 첨부파일 타입
    , ahflTyp : {
      A : '이미지'
     ,B : '동영상'
     ,C : '첨부파일'
    }

    // 옵션 구분
    , opinCls : {
       A : '오늘하루보지않기'
      ,B : '3일보지않기'
      ,C : '일주일보지않기'
    }

    // 회의장 사용여부(또는 사용여부)
    , useYn : {
         Y : '예'
        ,N : '아니오'
    }
    // 회의장 사용여부(또는 사용여부) 추가
    , mtgIplaUseYn : {
         Y : '사용'
        ,N : '미사용'
    }

  }//prop
};
