package nexbank.fwk.bfafprcss.biz;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.logging.Log;

import nexbank.common.TrtmRsltMsg;
import nexbank.common.internal.CommonAreaHelper;
import nexbank.common.internal.ImplCommonArea;
import nexbank.fwk.flat.FlatHeaderHelper;
import nexcore.framework.core.component.streotype.BizAttribute;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.component.streotype.BizUnitBind;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.service.txfilter.IServiceFilter;
import nexcore.framework.core.service.txprofile.ITxProfile;
import nexcore.framework.core.service.txprofile.ITxProfileAttr;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.core.util.StringUtils;


/**
 * [FU]거래로그관리.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin(admin)
 * @since 2016-08-09 17:04:10
 */
@BizUnit(value="[FU]거래로그관리", type="FU")
public class FFWK0900 extends nexbank.fwk.base.FunctionUnit  {

	/* 
    * 이 클래스는 Singleton 객체로 수행됩니다. 
    * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
    */
	
	@BizUnitBind
	private DTB_FWK_TRLOG_H_00 dTB_FWK_TRLOG_H_00;

	@BizUnitBind
	private DTB_FWK_ERLOG_H_00 dTB_FWK_ERLOG_H_00;
	
	/**
	 * Default Constructor
	 */
	public FFWK0900(){
		super();
	}

	/**
	 * 거래로그기록.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:04:16
	 */
	@BizMethod("거래로그기록")
	@BizAttribute(isShared=true)
	public IDataSet writeTrLog(IDataSet requestData, IOnlineContext onlineCtx) {
		try {
		    IDataSet responseData = new DataSet();
		    
		    Log log = getLog(onlineCtx);
	
//		    // 디퍼드는 로그를 기록하지 않음.
//		    if(onlineCtx.isDeferredTx()){
//		    	if(log.isInfoEnabled()){
//			    	log.info("디퍼드 서비스는 거래로그를 기록하지 않습니다.");
//			    }
//		    	return responseData;
//		    }
		    
		    // 거래로그를 기록하는 거래아이디에 부합하지 않으면 처리하지 않는다.
		    if(onlineCtx.getTxId().length() > 8){
		    	return responseData;
		    }

		    // 거래프로파일의 거래로깅여부를 체크한다.
		    ITxProfile txprofile = BaseUtils.getTxProfile(onlineCtx.getTxId());
		    ITxProfileAttr attr = txprofile.getAttr("TRLOG_YN");
		    if( attr !=null && "N".equals(attr.getValue())){
		    	return responseData;
		    }
		    
		    if(log.isDebugEnabled()){
		    	log.debug("거래로그 처리 시작");
		    }
	
		    IDataSet logDataSet = _toLogDataSet(onlineCtx, true);
	
		    //TODO 임시
		    logDataSet.put("LOG_DATE", DateUtils.getDateString());
	
	//	    DTB_CBS_FWK_TRLOG_H_00 dTB_CBS_FWK_TRLOG_H_00 = (DTB_CBS_FWK_TRLOG_H_00)lookupDataUnit(DTB_CBS_FWK_TRLOG_H_00.class);
		    dTB_FWK_TRLOG_H_00.i001(logDataSet, onlineCtx);
		    
		    if(log.isDebugEnabled()){
		    	log.debug("거래로그 처리 완료");
		    }
		    
		    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
		    return responseData;
		}catch(Exception e){
			throw new BizRuntimeException("거래로그 기록을 실패하였습니다.", e);
		}
	}
	
   /**
	 * 에러로그기록.
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
	 * @since 2016-08-09 17:04:16
	 */
	@BizMethod("에러로그기록")
	@BizAttribute(isShared=true)
	public IDataSet writeErLog(IDataSet requestData, IOnlineContext onlineCtx) {
		try {
		    IDataSet responseData = new DataSet();
	
		    // 거래로그를 기록하는 거래아이디에 부합하지 않으면 처리하지 않는다.
		    if(onlineCtx.getTxId().length() > 8){
		    	return responseData;
		    }
		    
		    Log log = getLog(onlineCtx);
		    if(log.isDebugEnabled()){
		    	log.debug("에러로그 처리 시작");
		    }
	
		    // 예외 정보 분석
		    Throwable throwable = (Throwable)requestData.get(IServiceFilter.THROWABLE_KEY);
		    
	    	ImplCommonArea ca = CommonAreaHelper.getImpl(onlineCtx);
	    	if(ca != null){
		    	if(ca.getMsgList() == null || ca.getMsgCcnt() < 1){
		    		ca.setMsgList(new ArrayList<TrtmRsltMsg>(10));
			    	FlatHeaderHelper.addMessage(ca.getMsgList(), null, throwable);
		    	}
	    	}
	
		    IDataSet logDataSet = _toLogDataSet(onlineCtx, false);
		    logDataSet.put("EROR_STACK", BaseUtils.getExceptionStackTrace(throwable)); //에러스택 추
	
		    dTB_FWK_ERLOG_H_00.i001(logDataSet, onlineCtx);
	
		    if(log.isDebugEnabled()){
		    	log.debug("에러로그 처리 완료");
		    }
		    
		    return responseData;
		}catch(Exception e){
			throw new BizRuntimeException("에러로그 기록을 실패하였습니다.", e);
		}
	}
	
	/**
	 * CommonArea 정보를 Map으로 변환한다.
	 * @throws ParseException 
	 */
	private IDataSet _toLogDataSet(IOnlineContext onlineCtx, boolean trlog) throws ParseException {
		IDataSet dataSet = new DataSet();
		
		String yyyyMMddHHmmssSSS = DateUtils.getDateString("yyyyMMddHHmmssSSS");
		String yyyyMMdd = yyyyMMddHHmmssSSS.substring(0, 8);
//		String partitionKey = null;
		
		// CommonArea ==> DataSet
	   	ImplCommonArea ca = CommonAreaHelper.getImpl(onlineCtx);
		if(ca != null){
			ca.setSvcEndDttm(yyyyMMddHHmmssSSS);
			Map map = nexbank.common.internal.CommonAreaHelper.toMap(ca);
			dataSet.putAll(map);

			long svcStartTime = DateUtils.parseDate(ca.getSvcStrnDttm(), "yyyyMMddHHmmssSSS").getTime();
			long svcEndTime = DateUtils.parseDate(ca.getSvcEndDttm(), "yyyyMMddHHmmssSSS").getTime();
			dataSet.put("SVC_DUR_TM", (svcEndTime-svcStartTime));
//			dataSet.put("INPT_MESG", ca.getInptMesg());

//			if(ca.getGlobId() != null && ca.getGlobId().length() > 0){
//				partitionKey = ca.getGlobId().substring(ca.getGlobId().length()-1);
//			}
		}
//		if(partitionKey == null || !StringUtils.isNumeric(partitionKey)){
//			partitionKey = "0";
//		}

		if(StringUtils.isEmpty(dataSet.getString("LOG_DATE"))){
			dataSet.put("LOG_DATE", yyyyMMdd);
		}
		dataSet.put("RUNTIME_NODE_ID", nexcore.framework.core.util.BaseUtils.getRuntimeId());
//		dataSet.put("CRE_DATE", yyyyMMdd);
//		dataSet.put("_PARTITION_KEY", partitionKey);

		return dataSet;
	}
	
}
