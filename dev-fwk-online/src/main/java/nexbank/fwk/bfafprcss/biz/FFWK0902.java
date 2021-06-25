package nexbank.fwk.bfafprcss.biz;

import nexbank.common.CommonArea;
import nexbank.common.internal.CommonAreaHelper;
import nexcore.framework.biz.online.internal.BizServiceHelper;
import nexcore.framework.core.component.streotype.BizAttribute;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.util.DateUtils;


/**
 * [FU]공통선후처리.
 * <pre>
 * 
 * </pre>
 * 
 * @author nexbank (nexbank)
 * @since 2016-08-09 17:03:46
 */
@BizUnit(value="[FU]공통선후처리", type="FU")
public class FFWK0902 extends nexbank.fwk.base.FunctionUnit  {

	/* 
    * 이 클래스는 Singleton 객체로 수행됩니다. 
    * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
    */

	/**
	 * Default Constructor
	 */
	public FFWK0902(){
		super();
	}

	/**
	 * CommonArea 생성/초기화.
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
	 * @since 2016-08-09 17:03:50
	 */
	@BizMethod("CommonArea 생성/초기화")
	@BizAttribute(isShared=true)
	public IDataSet initCommonArea(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
    	// CommonArea의 초기값을 설정한다.
    	// CommonArea가 존재하지 않는 경우에는 생성한후 초기값을 설정한다.
    	CommonAreaHelper.createInit(onlineCtx);
    	
    	CommonArea ca = getCommonArea(onlineCtx);
    	if(ca.getLogDate() == null) {
			String yyyyMMdd = DateUtils.getDateString("yyyyMMdd");
			ca.setLogDate(yyyyMMdd);
    	}
    	
//    	BizServiceHelper.initDbioConfig(onlineCtx);
    	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}

}
