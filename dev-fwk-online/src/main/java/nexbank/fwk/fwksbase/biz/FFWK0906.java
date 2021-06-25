package nexbank.fwk.fwksbase.biz;

import nexcore.framework.biz.online.internal.BizServiceHelper;
import nexcore.framework.core.component.streotype.BizAttribute;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;


/**
 * [FU]DBIO제어.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin (admin)
 * @since 2016-08-09 17:02:56
 */
@BizUnit(value="[FU]DBIO제어", type="FU")
public class FFWK0906 extends nexbank.fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FFWK0906(){
		super();
	}

	/**
	 * DBIO제어.
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
	 * @since 2016-08-09 17:03:00
	 */
	@BizMethod("DBIO제어")
	@BizAttribute(isShared=true)
	public IDataSet dbioControl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    BizServiceHelper.initDbioConfig(onlineCtx);
		
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}
  
}
