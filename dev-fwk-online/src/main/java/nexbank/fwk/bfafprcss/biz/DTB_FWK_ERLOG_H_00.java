package nexbank.fwk.bfafprcss.biz;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;


/**
 * [DU]에러로그.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin(admin)
 * @since 2016-08-09 17:04:26
 */
@BizUnit(value="[DU]에러로그", type="DU")
public class DTB_FWK_ERLOG_H_00 extends nexbank.fwk.base.DataUnit  {

	/* 
    * 이 클래스는 Singleton 객체로 수행됩니다. 
    * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
    */

	/**
	 * Default Constructor
	 */
	public DTB_FWK_ERLOG_H_00(){
		super();
	}
	
	/**
	 * 에러로그등록.
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
	 * @since 2016-08-09 17:04:31
	 */
	@BizMethod("에러로그등록")
	public IDataSet i001(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
		
		int row = dbInsert("I001", requestData, onlineCtx);

	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
		responseData.put("isSuccess", row > 0);
		return responseData;
	}

}
