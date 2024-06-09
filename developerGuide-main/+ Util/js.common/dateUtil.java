/**
 * File Name  : dateUtil.js
 * Description  : 날짜 관련 함수모음 - 공통으로 사용 할 만한 유틸
 * author   : 정성희
 * since    : 2020.05.16
 *
 *
 *
 * require :
 *    * moment.js
 *
 */

function getToday(format) {
  //var moment = require('moment');
  if(format != '') {
    return moment().format(format);
  }else{
    return moment().format();
  }
}

function getTimestamp() {
  var timestamp = new Date().getTime();
  return timestamp;
}

function getTimestampSecond() {
  var timestampSecond = Math.floor(+ new Date() / 1000);
  return timestampSecond;
}

//IE의 캐시방지용 파라미터를 만듦
function getNoCacheParam(arg) {
  if(arg == "f") {        //처음 오는 파라미터
    return "?ignore_cache=" + getTimestamp();
  }else if (arg == "s") { //한개만 필요할때
    return "ignore_cache=" + getTimestamp();
  }else {                 //보통의 경우 뒤에 붙을꺼니깐
    return "&ignore_cache=" + getTimestamp();
  }
}

//요일 구하기
function getInputDayLabel(dateStr) {
    var week = new Array('일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일');

    var today = new Date(dateStr).getDay();
    var todayLabel = week[today];

    return todayLabel;
}

//달의 마지막 날
function monthOfLastDay(yearStr, monthStr) {
    var lastDay = ( new Date( yearStr, monthStr, 0) ).getDate();
    return lastDay;
}


function formatDate(date) {
  var d = new Date(date),
      month = '' + (d.getMonth() + 1),
      day = '' + d.getDate(),
      year = d.getFullYear();

  if (month.length < 2) month = '0' + month;
  if (day.length < 2) day = '0' + day;

  return [year, month, day].join('-');
}

/*
 * format : 변경하고자 하는 포맷
 *  년도 : YYYY
 *  달 : MM
 *  일 : DD
 *  시 : HH
 *  분 : mm
 *  초 : ss
 *  orgFormat : 원본 데이터의 포맷 (옵셔널)
 */
function strToDateFormat(src, format, orgFormat) {
    var ret = "-"; //기본값

    if(src == null || format == null)
        return ret;

    src = jQuery.trim(src);
    format = jQuery.trim(format);

    if(orgFormat == null || typeof orgFormat === 'undefined' || orgFormat == ''){
      ret = moment(src).format(format);
    }else{
      ret = moment(src, orgFormat).format(format);
    }

    if(ret == "Invalid date") {
      console.log(ret + ",[" + src + "]" + ",[" + format + "]");
      return ret;
    }
    return ret;
}

/*
 *  ※ startDate, endDate, format 입력받아 과거일자인지 체크
 *  ※format 형식 예)YYYY-MM-DD, YYYY-MM-DD HH:mm
 *  ※return value
 *  -1 이면 startDate >  endDate
 *   0 이면 startDate == endDate
 *   1 이면 startDate <  endDate
 * */
function validatorForDate(startDate, endDate, format){
  var flg = 0;
  var startDate = moment(startDate,format);
  var endDate   = moment(endDate,format);

  var difMin = moment.duration(endDate.diff(startDate)).asMinutes();

  if(difMin < 0){
    flg = -1;
  }else if(difMin > 0){
    flg = 1;
  }else{
    flg = 0;
  }
return flg;
}


/*
 * 오늘이 시작~종료 사이에 있는지
 */

function todayIsBetween(startDate, endDate) {
  var format = "YYYY-MM-DD HH:mm";
  var startDate = moment(startDate,format);
  var endDate   = moment(endDate,format);

  return moment().isBetween(startDate, endDate);
}

function todayIsBefore(startDate) {
  var format = "YYYY-MM-DD HH:mm";
  var startDate = moment(startDate,format);

  return moment().isBefore(startDate);
}


/*
 * format
 *  년도 : YYYY
 *  달 : MM
 *  일 : DD
 *  시 : HH
 *  분 : mm
 *  초 : ss
 */
function dateFormatChk(src, format) {
  if(src == null || format == null)
      return false;

  src = jQuery.trim(src);
  format = jQuery.trim(format);

  var ret = moment(src).format(format);
  console.log(ret + ",[" + src + "]" + ",[" + format + "]");
  if(ret == "Invalid date") {
    return false;
  }
  return true;
}

/*
 * input 을 YYYY-MM-DD 로 만듦
 */
function dateFormat(obj){
  obj.value = obj.value.replace(/(\d\d\d\d)(\d\d)(\d\d)/g, '$1-$2-$3');
}


/*
 * 분을 입력 받아 x일 xx시간 xx분 으로 돌려줌
 */
function minToDateHour(min) {
  var days = Math.floor(min / 60 / 24)
  var hours = Math.floor((min - (days * 60 * 24 )) / 60);
  var mins = min - (days * 60 * 24) - (hours * 60);

  var daysStr = days;
  var hourStr = (hours > 9) ? hours : '0' + hours;
  var minStr = (mins > 9) ? mins : '0' + mins;

  var retStr = '';
  if(days > 0) {
    retStr += daysStr + '일';
  }

  if(hours > 0) {
    retStr += hourStr + '시간';
  }

  retStr += minStr + '분';
  return retStr;
}

