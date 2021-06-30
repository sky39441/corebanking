package nexbank.xyz.corebanking.biz;

import nexbank.common.OutboundHeader;
import nexbank.fwk.outbound.OutboundTarget;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.OutboundTimeoutCallback;
import nexcore.framework.core.data.TransformType;
import nexcore.framework.core.util.ByteArrayWrap;

import org.apache.commons.logging.Log;


/**
 * [PU]대외처리.
 * <pre>
 * 
 * </pre>
 * 
 * @author 차지환 (jihwancha)
 * @since 2016-08-09 17:16:15
 */
@BizUnit(value="[PU]대외처리", type="PU")
public class PXYZ0902 extends nexbank.fwk.base.ProcessUnit  {

	/**
	 * 취급요청.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:16:46
	 */
	@BizMethod("취급요청")
	public IDataSet pXYZ09201(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
		    
	    // 전문 요청 데이타
	    IDataSet lXYZ01001Req = new DataSet();
	    lXYZ01001Req.put("message", "취급요청.");
	    
	    // 요청 전문으로 변환
	    ByteArrayWrap lXYZ01001ByteArrayWrap = transformByteArrayForXio(TransformType.FLAT, "IO_XYZB01001", lXYZ01001Req, onlineCtx);
	    
	    // 요청 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    oh.setRequest();
	    
	    // 대외 관련 정보 설정
	    oh.setXtisCd(""); //대외기관코드
	    oh.setBzwrSvrCd(""); //업무서버코드
	    oh.setOtsdMesgCd(""); //대외전문코드
	    oh.setOtsdMesgTrtmCd(""); //대외전문처리코드
	    oh.setOtsdTrnUnqNo(""); //대외거래고유번호
	    oh.setOtsdRespTxId("XYZ09202"); //대외응답거래코드
		
	    // 10초동안 취급응답이 오지 않으면 타임아웃 처리함.
//	    String delayAsyncKey = callDelayAsyncService("XYZ09204", 10, requestData, onlineCtx);
	    
	    // 전문 송신
	    OutboundTimeoutCallback timeoutCallback = new OutboundTimeoutCallback();
	    timeoutCallback.setSeconds(10);
	    timeoutCallback.setTxId("XYZ09204");
	    timeoutCallback.setRequestData(requestData);
 
	    sendOutboundAfterCommit(OutboundTarget.FEP, oh, lXYZ01001ByteArrayWrap, timeoutCallback, null, onlineCtx);
	    
	    if(true){
	    	throw new RuntimeException("강제 에러");
	    }
	    
	    // 더미 리턴 설정
	    setDummyReturn(onlineCtx, true);

	    // 처리 결과값을 responseData에 넣어서 리턴하십시요
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
	    return responseData;
	}
	
   /**
	 * 취급응답.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : inputByteArray (binary) [입력전문]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : outputByteArray (binary) [응답전문]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:16:46
	 */
	@BizMethod("취급응답")
	public IDataSet pXYZ09202(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
		// 입력전문
		byte[] inputByteArray = requestData.getByteArray("inputByteArray");
		
		// 입력전문을 DataSet으로 변환
		IDataSet dataSet = transformDataSetForXio(TransformType.FLAT, "IO_XYZB01001", new ByteArrayWrap(inputByteArray), onlineCtx);
	    
	    // 처리 로직
	    
	    // 지연비동기키값 조회
	    String delayAsyncKey = null;
	    
	    // 지연비동기가 등록된 경우에 삭제 요청
	    boolean executedTimeoutModule = false;
	    if(delayAsyncKey != null){
	    	boolean removedDelayAsync = removeDelayAsyncService(delayAsyncKey, onlineCtx);
	    	// 삭제 성공
	    	if(removedDelayAsync){
	    		executedTimeoutModule = false;
	    	}
	    	// 존재하지 않거나, 이미 실행된 경우
	    	else {
	    		executedTimeoutModule = true;
	    	}
	    }
	    
	    // 타임아웃 처리가 실행되지 않은 경우에 처리함.
	    if(!executedTimeoutModule){
		    // 단말에 송신할 응답전문 생성
		    IDataSet lXYZ01002Req = new DataSet(); 
		    lXYZ01002Req.put("message", "취급응답이 성공적으로 수행되었습니다.");
		    ByteArrayWrap lXYZ01002ByteArrayWrap = transformByteArrayForXio(TransformType.FLAT, "IO_XYZB01002", lXYZ01002Req, onlineCtx);
	
		    // 단말에 송신할 응답헤더 생성
		    OutboundHeader oh = new OutboundHeader(onlineCtx);
		    oh.setResponse();
		    
		    oh.setOkResultMessage("NCOM0000", null);
		    
		    // 단말에 응답전문 송신
		    sendOutboundAfterCommit(OutboundTarget.MCI, oh, lXYZ01002ByteArrayWrap, onlineCtx);
	    }
	    else {
	    	Log log = getLog(onlineCtx);
	    	if(log.isWarnEnabled()){
	    		log.warn("타임아웃 처리가 실행된것 같음.");
	    	}
	    }
	    
	    // 더미리턴 처리함.
	    setDummyReturn(onlineCtx, true);

	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
	    return responseData;
	}

	/**
	 * 개설요청.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : inputByteArray (binary) [입력전문]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : outputByteArray (binary) [응답전문]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:16:46
	 */
	@BizMethod("개설요청")
	public IDataSet pXYZ09203(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    // 입력전문
	    byte[] inputByteArray = requestData.getByteArray("inputByteArray");
	    
	    // 입력전문을 DataSet으로 변환
	    IDataSet dataSet = transformDataSetForXio(TransformType.FLAT, "IO_XYZB01003", new ByteArrayWrap(inputByteArray), onlineCtx);
	    
	    // 로직처리
	    
	    // 송신할 응답전문 생성
	    IDataSet lXYZ01004Req = new DataSet(); 
	    lXYZ01004Req.put("message", "개설요청이 성공적으로 수행되었습니다.");
	    ByteArrayWrap lXYZ01004ByteArrayWrap = transformByteArrayForXio(TransformType.FLAT, "IO_XYZB01004", lXYZ01004Req, onlineCtx);

	    // 송신할 응답헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    oh.setResponse();
	    
	    oh.setOkResultMessage("NCOM0000", null);
	    
	    // 응답전문 송신
	    sendOutboundAfterCommit(OutboundTarget.FEP, oh, lXYZ01004ByteArrayWrap, onlineCtx);
	    
	    // 더미리턴 처리함.
	    setDummyReturn(onlineCtx, true);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
	    return responseData;
	}
	
	/**
	 * 취급요청-타임아웃.
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
	 * @since 2016-08-09 17:16:46
	 */
	@BizMethod("취급요청-타임아웃")
	public IDataSet pXYZ09204(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    Log log = getLog(onlineCtx);
	    
	    // 타임아웃 처리
	    if(log.isWarnEnabled()){
    		log.warn("타임아웃 처리를 실행함.");
    	}
	    
	    // 단말에 송신할 응답전문 생성
	    IDataSet lXYZ01002Req = new DataSet(); 
	    lXYZ01002Req.put("message", "취급응답이 타임아웃이 발생하였습니다.");
	    ByteArrayWrap lXYZ01002ByteArrayWrap = transformByteArrayForXio(TransformType.FLAT, "IO_XYZB01002", lXYZ01002Req, onlineCtx);

	    // 단말에 송신할 응답헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    oh.setResponse();
	    
	    oh.setOkResultMessage("NCOM0000", null);
	    
	    // 단말에 응답전문 송신
	    sendOutboundAfterCommit(OutboundTarget.MCI, oh, lXYZ01002ByteArrayWrap, onlineCtx);
	    
	    // 더미리턴 처리함.
	    setDummyReturn(onlineCtx, true);

	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

}
