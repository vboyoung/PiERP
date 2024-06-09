package pierp.app.eip.bizGRP.grpa.model;

/**
 * @Class GwRetCntVO
 * @Description
 *  그룹웨어 카드 영역의 건수 출력 용 VO
 * @author 정성희
 * @since 2022. 06. 22.
 * @version
 *
 */

public class GwRetCntVO {

	private String apprwait;	//결재대기
	private String examproc;	//발송처리 (권한필요)
	private String recvwait;	//접수대기 (권한필요)
	private String appring;		//결재진행
	private String pubwait;		//공람대기
	private String recving;		//수신반송
	private String recvuser;	//개인접수

	private String bbsNewHom;	//최근게시물 건수

	public String getApprwait() {
		return apprwait;
	}
	public void setApprwait(String apprwait) {
		this.apprwait = apprwait;
	}
	public String getExamproc() {
		return examproc;
	}
	public void setExamproc(String examproc) {
		this.examproc = examproc;
	}
	public String getRecvwait() {
		return recvwait;
	}
	public void setRecvwait(String recvwait) {
		this.recvwait = recvwait;
	}
	public String getAppring() {
		return appring;
	}
	public void setAppring(String appring) {
		this.appring = appring;
	}
	public String getPubwait() {
		return pubwait;
	}
	public void setPubwait(String pubwait) {
		this.pubwait = pubwait;
	}
	public String getRecving() {
		return recving;
	}
	public void setRecving(String recving) {
		this.recving = recving;
	}
	public String getRecvuser() {
		return recvuser;
	}
	public void setRecvuser(String recvuser) {
		this.recvuser = recvuser;
	}
	public String getBbsNewHom() {
		return bbsNewHom;
	}
	public void setBbsNewHom(String bbsNewHom) {
		this.bbsNewHom = bbsNewHom;
	}

	@Override
	public String toString() {
		return "GwRetCntVO [apprwait=" + apprwait + ", examproc=" + examproc + ", recvwait="
				+ recvwait + ", appring=" + appring + ", pubwait=" + pubwait + ", recving="
				+ recving + ", recvuser=" + recvuser + ", bbsNewHom=" + bbsNewHom + "]";
	}
}


