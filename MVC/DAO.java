

@Repository
public class DAO extends BaseAbstractMapper {
	


	/** 
	 * QueryResultMap : EgovMap 사용하지 말고 QueryResultMap 사용할 것
	 * Map<String, Object>  : 
	 * List<QueryResultMap>  :
	 * 					
	 */

	public QueryResultMap selectFwpInfo(String fwpAplNo) {
		return selectOne( "MHK10.selectFwpInfo", fwpAplNo );
	}





}
