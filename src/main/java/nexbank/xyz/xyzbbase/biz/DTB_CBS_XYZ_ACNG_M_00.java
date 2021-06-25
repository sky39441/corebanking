package nexbank.xyz.xyzbbase.biz;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;


/**
 * [DU]샘플부점별수신잔고.
 * <pre>
 * 
 * </pre>
 * 
 * @author nexbank (nexbank)
 * @since 2016-08-09 17:28:17
 */
@BizUnit(value="[DU]샘플부점별수신잔고", type="DU")
public class DTB_CBS_XYZ_ACNG_M_00 extends nexbank.fwk.base.DataUnit  {

	/**
	 * 부점별수신잔고조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:17
	 */
	@BizMethod("부점별수신잔고조회")
	public IDataSet s001(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    // 조회 쿼리 실행.
	    IRecordSet rs = dbSelect("S001", requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("LIST", rs);
	
	    return responseData;
	}
	
   /**
	 * 부점별수신잔고업데이트.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:17
	 */
	@BizMethod("부점별수신잔고업데이트")
	public IDataSet u001(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    int row = dbUpdate("U001", requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("EXPECTED_ROW", row);
	    return responseData;
	}

}
