package nexbank.xyz.xyzbbase.biz;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;

import nexcore.framework.biz.online.resolver.ServiceResolverFactory;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.ioc.BeanRegistry;
import nexcore.framework.core.jmx.INexcoreMBeanInvoker;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.integration.ibatis.IRecordResolver;

/**
 * [DU]단위테스트를 위한 샘플SQL.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin(admin)
 * @since 2016-06-22 15:46:47
 */
@BizUnit(value="[DU]단위테스트를 위한 샘플SQL", type="DU")
public class DTB_CBS_TRLOG_H_00 extends nexbank.fwk.base.DataUnit {
	
	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DTB_CBS_TRLOG_H_00(){
		super();
	}

	/**
	 * Batch Array샘플.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:28
	 */
	@BizMethod("Batch Array샘플")
	public IDataSet i001(IDataSet requestData, final IOnlineContext onlineCtx) {
		Log log = getLog(onlineCtx);
	    IDataSet responseData = new DataSet();
	    final IRecordSet rs = dbSelect("S001", requestData, onlineCtx);
	    long totalCnt = dbExecuteBatch(new DbBatchModeExecutor(1000) {
			@Override
			protected void execute() {
				for(int i=0; i<rs.getRecordCount(); i++) {
					addBatch("I001", rs.getRecord(i));
				}
				
			}
		}, onlineCtx);
	    if(log.isInfoEnabled()) {
	    	log.info("총 처리건수 : ["+totalCnt+"]");
	    }
	    return responseData;
	}

	/**
	 * rowResolver처리.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:28
	 */
	@BizMethod("rowResolver처리")
	public IDataSet s001(IDataSet requestData, IOnlineContext onlineCtx) {
//		INexcoreMBeanInvoker invoker = (INexcoreMBeanInvoker)BeanRegistry.lookup(INexcoreMBeanInvoker.BEAN_ID);
//		System.out.println(invoker.refreshCache("code", null));
		
		Map param = new HashMap();
		param.put("ARG1", "ARG1");
		param.put("ARG2", "ARG2");
		param.put("ARG3", "ARG3");
		
		// String jobExeId = ServiceResolverFactory.getInstance().getBatchResolver().callJobNow("BPOC1900", param, onlineCtx);
		
		
	    IDataSet responseData = new DataSet();
	    Log log = getLog(onlineCtx);
	    IRecordResolver resolver = new IRecordResolver() {
			@Override
			public void resolve(IRecord record) {
				String svcStrnDttm = (String)record.get("SVC_STRN_DTTM");
				try {
					record.set("SVC_STRN_DTTM", DateUtils.parseDate(svcStrnDttm, "yyyyMMddHHmmssSSS"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		};
		
		IRecordSet rs = dbSelect("S001", requestData, resolver, onlineCtx);
		if(log.isInfoEnabled()) {
	    	log.info(rs);
	    }
		responseData.put("TRLOG_LIST", rs);
//		responseData.put("jobExeId", jobExeId);
	    return responseData;
	}

	/**
	 * optionString을 통한 타 DB조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:28
	 */
	@BizMethod("optionString을 통한 타 DB조회")
	public IDataSet s002(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    IRecordSet rs= dbSelect("S002", requestData, "_ETC", onlineCtx); 
	
	    return responseData;
	}

	/**
	 * 쿼리타임아웃테스트.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:28
	 */
	@BizMethod("쿼리타임아웃테스트")
	public IDataSet s003(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    IRecordSet rs = dbSelect("S003", requestData, onlineCtx); 
	    responseData.put("TIMEOUT_RS", rs);
	    return responseData;
	}

	/**
	 * 페이지처리Select.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:28
	 */
	@BizMethod("페이지처리Select")
	public IDataSet s004(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    IRecordSet recordset = dbSelect("S001", requestData, 1, 999, onlineCtx);
	    
	    // Auto-generated make response block
	    if (recordset.getRecordCount() > 0){
	        responseData.put("LIST", recordset);
	    }
	    return responseData;
	}
  
}