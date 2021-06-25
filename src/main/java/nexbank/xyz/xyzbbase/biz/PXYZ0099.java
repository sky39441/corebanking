package nexbank.xyz.xyzbbase.biz;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.component.streotype.BizUnitBind;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.util.BaseUtils;

import org.apache.commons.logging.Log;

/**
 * [PU]단위테스트를 위한 테스트PU.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin(admin)
 * @since 2016-06-22 17:23:13
 */
@BizUnit(value="[PU]단위테스트를 위한 테스트PU", type="PU")
public class PXYZ0099 extends nexbank.fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */
	@BizUnitBind
	private DTB_CBS_TRLOG_H_00 dTB_CBS_TRLOG_H_00;

	/**
	 * Default Constructor
	 */
	public PXYZ0099(){
		super();
	}

	/**
	 * 타임아웃 잔여시간 적용테스트.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:21:50
	 */
	@BizMethod("타임아웃 잔여시간 적용테스트")
	public IDataSet pXYZ00991(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    Log log = getLog(onlineCtx);
	    if(log.isInfoEnabled())log.info("타임아웃 잔여시간 적용테스트 시작");
	    
	    dTB_CBS_TRLOG_H_00.i001(requestData, onlineCtx);
	    
	    dTB_CBS_TRLOG_H_00.s001(requestData, onlineCtx);
	    
	    dTB_CBS_TRLOG_H_00.s003(requestData, onlineCtx);
	     
	    if(log.isInfoEnabled())log.info("타임아웃 잔여시간 적용테스트 끝");
	    return responseData;
	}

	/**
	 * 프로퍼티조회 Util호출테스트.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:21:50
	 */
	@BizMethod("프로퍼티조회 Util호출테스트")
	public IDataSet pXYZ00992(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    Log log = getLog(onlineCtx);
	    String allowedExtList = BaseUtils.getBootupConfiguration("file.upload.allowedExtListStr");
	    String runtimeProList = BaseUtils.getRuntimeConfiguration("Test03");
	    String labelName = BaseUtils.getLabelName("sample.boardcontents");
	    String noLabelName = BaseUtils.getLabelName("test.boardcontents");
	    if(log.isInfoEnabled()) log.info("레이블명 : ["+labelName+"]");
	    if(log.isInfoEnabled()) log.info("존재하지 않는 레이블명 : ["+noLabelName+"]");
	    if(log.isInfoEnabled()) log.info("업로드 허용확장자리스트 : ["+allowedExtList+"]");
	    if(log.isInfoEnabled()) log.info("Runtime파라미터리스트 : ["+runtimeProList+"]");
	    return responseData;
	}

	/**
	 * MBeanInvoker를 사용한 Cache처리테스트.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:21:50
	 */
	@BizMethod("MBeanInvoker를 사용한 Cache처리테스트")
	public IDataSet pXYZ00993(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    Log log = getLog(onlineCtx);
//	    ICache cache = BaseUtils.getCache("logLevel");
	    
	    log.debug(">>>>>>출력메시지 : "+BaseUtils.getMessage("FWKE0001"));
//	    cache.removeAll();
//	    INexcoreMBeanInvoker mbeanInvoker = (INexcoreMBeanInvoker)BeanRegistry.lookup("nc.jmx.MBeanInvoker");
	    
	    return responseData;
	}

	/**
	 * 로그레벨별로깅테스트.
	 * <pre>
	 * 로그레벨별로깅테스트
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:21:50
	 */
	@BizMethod("로그레벨별로깅테스트")
	public IDataSet pXYZ00994(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    Log log = getLog(onlineCtx);
	    String logginTxt = "가나다라마바사ABCDEFG";
	    
	    if(log.isTraceEnabled())log.trace("[로그레벨TRACE] : "+logginTxt);
	    if(log.isDebugEnabled())log.debug("[로그레벨DEBUG] : "+logginTxt);
	    if(log.isInfoEnabled())log.info("[로그레벨INFO] : "+logginTxt);
	    if(log.isWarnEnabled())log.warn("[로그레벨WARN] : "+logginTxt);
	    if(log.isErrorEnabled())log.error("[로그레벨ERROR] : "+logginTxt);
	    if(log.isFatalEnabled())log.fatal("[로그레벨FATAL] : "+logginTxt);
	    
	    
	    return responseData;
	}
  
}