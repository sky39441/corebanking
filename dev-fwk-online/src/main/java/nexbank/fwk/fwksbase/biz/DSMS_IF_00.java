package nexbank.fwk.fwksbase.biz;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.component.streotype.BizAttribute;


/**
 * [DU]SMS통지.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin (admin)
 * @since 2016-08-09 17:04:34
 */
@BizUnit(value="[DU]SMS통지", type="DU")
public class DSMS_IF_00 extends nexbank.fwk.base.DataUnit  {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSMS_IF_00(){
		super();
	}

	/**
	 * SMS통지등록.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:04:38
	 */
	@BizMethod("SMS통지등록")
	public IDataSet i001(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    dbInsert("I001", requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * 222.
	 * <pre>
	 * 222
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin (Administrator)
	 * @since 2021-03-03 22:06:15
	 */
	@BizMethod("222")
	public IDataSet s111(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    IRecord record = dbSelectSingle("STMT_ID", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    if (record != null){
	        responseData.putAll(record);
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}
  
}
