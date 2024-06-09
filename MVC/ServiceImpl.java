

@Service
public class ServiceImpl extends BaseAbstractServiceImpl implements Service{
	
	@Autowired private MHLockTableService 	mhLockTableService;
	@Autowired private MHSpService			mhSpService;
	@Autowired private MHKeyGenService 		mhKeyGenService;
	@Autowired private MHK10DAO				mhk10DAO;
	@Autowired private TbMhFwpNewDAO 		tbMhFwpNewDAO;
	@Autowired private TableKeyGenService 		tableKeyGenService;
	
	@Override
	public List<QueryResultMap> selectChoiceFwpReqList(MHK1001FormVO vo) {
		return mhk10DAO.selectChoiceFwpReqList(vo);
	}




	@Override
	public QueryResultMap selectFwpInfo(String fwpAplNo) {
		return mhk10DAO.selectFwpInfo( fwpAplNo );
	}
	

}