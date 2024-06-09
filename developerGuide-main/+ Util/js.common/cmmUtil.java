/**
 * File Name  : cmmUtil.js
 * Description  : 함수모음 - 공통으로 사용 할 만한 유틸
 * author   : 정성희
 * since    : 2019.01.06
 */



/*
//문자열 전체 replace
function replaceAll(str, find, replace) {
    return str.replace( new RegExp( find, 'g' ), replace );
}
*/

// 패턴 사용하지 않는 replaceAll
function replaceAll(str, find, replace) {
    return str.split(find).join(replace);
}

// 문자열 인덱스 위치에 치환 ex) "hello".replaceAt(3, 'p');
String.prototype.replaceAt=function replaceAt(index, character) {
  return this.substr(0, index) + character + this.substr(index+character.length);
}

//IE 용 startsWith
String.prototype.startsWith = function(str) {
  if (this.length < str.length) { return false; }
  return this.indexOf(str) == 0;
}

//IE 용 endsWith
String.prototype.endsWith = function(str) {
  if (this.length < str.length) { return false; }
  return this.lastIndexOf(str) + str.length == this.length;
}



function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }
}

//null값 공백으로 처리
function nullToEmpty(str){
  if(str == null)
    return "";
  else
    return str;
}

//문자열 자르기
function cutString(str, len) {
    if(str == null || str == "")
        return "";

    if(str.length <= len)
        return str;

    return str.substr(0, len);
}

// 쿠키 생성
function setCookie(cName, cValue, cDay){
    var expire = new Date();
    expire.setDate(expire.getDate() + cDay);
    var cookies = cName + '=' + escape(cValue) + '; path=/ '; // 한글 깨짐 방지
    if(typeof cDay != 'undefined') cookies += ';expires=' + expire.toGMTString() + ';';
    document.cookie = cookies;
}

// 쿠키 가져오기
function getCookie(cName) {
    cName = cName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cName);
    var cValue = '';
    if(start != -1){
        start += cName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cValue = cookieData.substring(start, end);
    }
    return unescape(cValue);
}

function htmlEncode(value){
  //create a in-memory div, set it's inner text(which jQuery automatically encodes)
  //then grab the encoded contents back out.  The div never exists on the page.
  return $('<div/>').text(value).html();
}

function htmlDecode(value){
  return $('<div/>').html(value).text();
}



///////////// --- OLD --- /////////////

//원단위 절삭
function wonTrim(dOrg) {
    if(dOrg == null || dOrg == "") {
        return 0;
    }

    dOrg = dOrg * 1;    //숫자
    if ( isNaN(dOrg) ) {
        return 0;
    }

    var d= Math.floor(dOrg);  //소수점 이하 버림
    var rest = d % 10;           //1 자리의 값을 구하고

    return d - rest; //1 자리 값으르 빼버림
}

