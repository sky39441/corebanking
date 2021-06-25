package nexbank.xyz.xyzsbase.biz;

import nexbank.common.CommonArea;
import nexcore.framework.core.component.streotype.BizAttribute;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;

import org.apache.commons.logging.Log;


/**
 * [FU]전체업무선후처리.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin (admin)
 * @since 2016-08-09 17:13:43
 */
@BizUnit(value="[FU]전체업무선후처리", type="FU")
public class FXYZ0900 extends nexbank.fwk.base.FunctionUnit  {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FXYZ0900(){
		super();
	}

	/**
	 * 전체업무 선처리.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:14:18
	 */
	@BizMethod("전체업무 선처리")
	@BizAttribute(isShared=true)
	public IDataSet trPre(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    Log log = getLog(onlineCtx);
	    if(log.isDebugEnabled()){
	    	log.debug("전체 업무 선처리.");
	    }
	    
	    String yyyyMMdd = DateUtils.getDateString();

	    CommonArea ca = getCommonArea(onlineCtx);
	    if(ca == null){
	    	throw new BizRuntimeException("CommonArea가 생성되지 않았습니다.");
	    }
	    ca.setLogDate(yyyyMMdd); //로그 영업일
	    ca.setBzopDt(yyyyMMdd); //영업일
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * 전체업무 후처리.
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
	 * @since 2016-08-09 17:14:18
	 */
	@BizMethod("전체업무 후처리")
	@BizAttribute(isShared=true)
	public IDataSet trPost(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    Log log = getLog(onlineCtx);
	    if(log.isDebugEnabled()){
	    	log.debug("전체 업무 후처리.");
	    }

	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}
  
}
