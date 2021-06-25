package nexbank.xyz.xyzsdbio.biz;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;


/**
 * 샘플DBIO.
 * <pre>
 * [DBIO]DBIO_TB_CBS_XYZ_ACC_M
 * </pre>
 * 
 * @author admin (admin)
 * @since 2016-08-11 14:19:43
 */
@BizUnit(value="샘플DBIO", type="DB")
public class TB_CBS_XYZ_ACC_M extends nexcore.framework.biz.online.DBIO {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public TB_CBS_XYZ_ACC_M(){
		super("TB_CBS_XYZ_ACC_M");
	}

	/**
	 * TB_CBS_XYZ_ACC_M INSERT.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-11 14:20:59
	 */
	@BizMethod("TB_CBS_XYZ_ACC_M INSERT")
	public IDataSet insert(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbioInsert(requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시오
		responseData.put("EXPECTED_ROW", row);
	
	    return responseData;
	}

	/**
	 * TB_CBS_XYZ_ACC_M UPDATE.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-11 14:20:59
	 */
	@BizMethod("TB_CBS_XYZ_ACC_M UPDATE")
	public IDataSet update(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbioUpdate(requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시오
		responseData.put("EXPECTED_ROW", row);
	
	    return responseData;
	}

	/**
	 * TB_CBS_XYZ_ACC_M DELETE.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-11 14:20:59
	 */
	@BizMethod("TB_CBS_XYZ_ACC_M DELETE")
	public IDataSet delete(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbioDelete(requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시오
		responseData.put("EXPECTED_ROW", row);
	
	    return responseData;
	}
  
}
