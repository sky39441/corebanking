package nexbank.xyz.xyzbbase.biz;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;


/**
 * [FU]업무선후처리.
 * <pre>
 * 
 * </pre>
 * 
 * @author nexbank (nexbank)
 * @since 2016-08-09 17:28:03
 */
@BizUnit(value="[FU]업무선후처리", type="FU")
public class FXYZ0101 extends nexbank.fwk.base.FunctionUnit  {

	/**
	 * 업무 선처리1.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:03
	 */
	@BizMethod("업무 선처리1")
	public IDataSet pre(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
//	    Log log = getLog(onlineCtx);
//	    
//	    if(log.isDebugEnabled()){
//	    	log.debug("업무 선처리 실행.");
//	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}
   /**
	 * 업무 후처리.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:03
	 */
	@BizMethod("업무 후처리")
	public IDataSet post(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
//	    Log log = getLog(onlineCtx);
//	    
//	    if(log.isDebugEnabled()){
//	    	log.debug("업무 후처리 실행.");
//	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}
/**
	 * 업무 선처리2.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:03
	 */
	@BizMethod("업무 선처리2")
	public IDataSet pre2(IDataSet requestData, IOnlineContext onlineCtx){
    IDataSet responseData = new DataSet();

    // 처리 결과값을 responseData에 넣어서 리턴하십시요 

    return responseData;
}
/**
	 * 업무 선처리3.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:03
	 */
	@BizMethod("업무 선처리3")
	public IDataSet pre3(IDataSet requestData, IOnlineContext onlineCtx){
    IDataSet responseData = new DataSet();

    // 처리 결과값을 responseData에 넣어서 리턴하십시요 

    return responseData;
}

}
