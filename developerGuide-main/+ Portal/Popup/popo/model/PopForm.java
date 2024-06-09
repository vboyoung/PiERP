package pierp.app.mgt.bizPOP.popo.model;

import pierp.app.common.cmd.ParamCmd;

/**
 * @Class PopPupForm
 * @Description
 *  팝업 목록 - 생성/수정
 * @author 이정민
 * @since 2022. 05. 24.
 * @version
 *
 */

public class PopForm extends ParamCmd{

	private static final long serialVersionUID = 1L;//ParamCmd 를 상속 할 것!!

	private 	 String pupNo;			//팝업번호
	private 	 String rgtrId;			//등록자아이디
	private 	 String rgtrLoca;		//등록위치
	private 	 String ttl;			//제목
	private 	 String cnts;			//내용
	private 	 String cntsYn;			//내용 포함 여부
	private 	 String opinDiv;		//옵션구분
	private 	 String mndtAnucYn;		//필수공지여부
	private 	 String pstpBegnDtm;	//게시시작일시
	private 	 String pstpClseDtm;	//게시종료일시
	private 	 String ahflTyp;		//첨부파일타입
	private 	 String ahflNo;			//첨부파일번호
	private 	 String imgWdthSize;	//이미지가로사이즈
	private 	 String imgVertSize;	//이미지세로사이즈
	private 	 String tmpSaveYn;		//임시저장여부

	public String getPupNo() {
		return pupNo;
	}

	public void setPupNo(String pupNo) {
		this.pupNo = pupNo;
	}

	public String getRgtrId() {
		return rgtrId;
	}

	public void setRgtrId(String rgtrId) {
		this.rgtrId = rgtrId;
	}

	public String getRgtrLoca() {
		return rgtrLoca;
	}

	public void setRgtrLoca(String rgtrLoca) {
		this.rgtrLoca = rgtrLoca;
	}

	public String getTtl() {
		return ttl;
	}

	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

	public String getCnts() {
		return cnts;
	}

	public void setCnts(String cnts) {
		this.cnts = cnts;
	}

	public String getOpinDiv() {
		return opinDiv;
	}

	public void setOpinDiv(String opinDiv) {
		this.opinDiv = opinDiv;
	}

	public String getMndtAnucYn() {
		return mndtAnucYn;
	}

	public void setMndtAnucYn(String mndtAnucYn) {
		this.mndtAnucYn = mndtAnucYn;
	}
	public String getPstpBegnDtm() {
		return pstpBegnDtm;
	}

	public void setPstpBegnDtm(String pstpBegnDtm) {
		this.pstpBegnDtm = pstpBegnDtm;
	}

	public String getPstpClseDtm() {
		return pstpClseDtm;
	}

	public void setPstpClseDtm(String pstpClseDtm) {
		this.pstpClseDtm = pstpClseDtm;
	}

	public String getAhflTyp() {
		return ahflTyp;
	}

	public void setAhflTyp(String ahflTyp) {
		this.ahflTyp = ahflTyp;
	}

	public String getAhflNo() {
		return ahflNo;
	}

	public void setAhflNo(String ahflNo) {
		this.ahflNo = ahflNo;
	}

	public String getImgWdthSize() {
		return imgWdthSize;
	}

	public void setImgWdthSize(String imgWdthSize) {
		this.imgWdthSize = imgWdthSize;
	}

	public String getImgVertSize() {
		return imgVertSize;
	}

	public void setImgVertSize(String imgVertSize) {
		this.imgVertSize = imgVertSize;
	}

	public String getTmpSaveYn() {
		return tmpSaveYn;
	}

	public void setTmpSaveYn(String tmpSaveYn) {
		this.tmpSaveYn = tmpSaveYn;
	}

	public String getCntsYn() {
		return cntsYn;
	}

	public void setCntsYn(String cntsYn) {
		this.cntsYn = cntsYn;
	}



}
