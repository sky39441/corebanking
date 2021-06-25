package nexbank.fwk.fwksbase.biz;

import nexcore.framework.core.component.streotype.BizAttribute;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.component.streotype.BizUnitBind;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.notification.INotificationConfig;
import nexcore.framework.core.notification.INotificationReceiver;
import nexcore.framework.core.prototype.IMessageCoded;
import nexcore.framework.core.service.txfilter.IServiceFilter;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.StringUtils;


/**
 * [FU]이벤트통지.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin (admin)
 * @since 2016-08-09 17:03:11
 */
@BizUnit(value="[FU]이벤트통지", type="FU")
public class FFWK0904 extends nexbank.fwk.base.FunctionUnit  {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FFWK0904(){
		super();
	}

	@BizUnitBind
	private DSMS_IF_00 dSMS_IF_00;

	/**
	 * 거래 처리전 통지.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:03:27
	 */
	@BizMethod("거래 처리전 통지")
	@BizAttribute(isShared=true)
	public IDataSet preNotification(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    // TODO 이벤트 사전 통지 처리 

//	    // 통지 설정 정보
//	    INotificationConfig notificationConfig = BaseUtils.getNotificationConfig(INotificationConfig.MODE.NORMAL, onlineCtx.getTransaction().getTxId());
		
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * 거래 완료 통지.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:03:27
	 */
	@BizMethod("거래 완료 통지")
	@BizAttribute(isShared=true)
	public IDataSet postNotification(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    // 정상시에만 존재함(성공시에도 NULL일수 있음)
		IDataSet requestDataSet  = requestData.getDataSet(IServiceFilter.REQUEST_DATASET_KEY);
		IDataSet responseDataSet = requestData.getDataSet(IServiceFilter.RESPONSE_DATASET_KEY);
	
	    // 통지 설정 정보
	    INotificationConfig notificationConfig = BaseUtils.getNotificationConfig(INotificationConfig.MODE.NORMAL, onlineCtx.getTxId());
	    
	    // 통지 설정이 존재하는 경우에만 처리
	    if(notificationConfig != null){
		    // 대상자 추출
		    INotificationReceiver[] receivers = notificationConfig.getAll();
		    
	    	// 송신 처리
	    	if(receivers != null){
		    	String msg = "거래["+onlineCtx.getTxId()+"]는 정상 처리되었습니다.";
	    		for(INotificationReceiver receiver : receivers){
	    			//TODO 시스템에 맞게 송신 처리 로직을 작성합니다.
	    			_notifyRecevier(receiver, msg, onlineCtx);
				}
	    	}
	    }
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}
  
	/**
	 * 거래 에러 통지.
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
	 * @since 2016-08-09 17:03:27
	 */
	@BizMethod("거래 에러 통지")
	@BizAttribute(isShared=true)
	public IDataSet errorPostNotification(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    // 예외 객체
		IDataSet requestDataSet  = requestData.getDataSet(IServiceFilter.REQUEST_DATASET_KEY);
		IDataSet responseDataSet = requestData.getDataSet(IServiceFilter.RESPONSE_DATASET_KEY);
	    Throwable throwable      = (Throwable)requestData.get(IServiceFilter.THROWABLE_KEY); 
	    
	    // 통지 설정 정보
	    INotificationConfig notificationConfig = BaseUtils.getNotificationConfig(INotificationConfig.MODE.ERROR, onlineCtx.getTxId());
	
		// 통지 설정이 존재하는 경우에만 처리
	    if(notificationConfig != null){
		    // 에러 메시지 아이디
		    String messageId = null;
	    	if(throwable instanceof IMessageCoded){
	    		messageId = ((IMessageCoded)throwable).getMessageId();
	    	}
	
	    	// 대상자 추출
		    INotificationReceiver[] receivers = null;
		    // 거래아이디별, 메시지아이디별로 대상자를 추출할 경우
		    if(!StringUtils.isEmpty(messageId)){
		    	receivers = notificationConfig.get(messageId);
		    }
		    // 거래아이디별로 대상자를 추출할 경우
		    else {
		    	receivers = notificationConfig.getAll();
		    }
		    
	    	// 송신 처리
	    	if(receivers != null){
		    	String msg = "거래["+onlineCtx.getTxId()+"]는 에러가 발생하였습니다.";
	    		for(INotificationReceiver receiver : receivers){
	    			_notifyRecevier(receiver, msg, onlineCtx);
				}
	    	}
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}

	private void _notifyRecevier(INotificationReceiver receiver, String msg, IOnlineContext onlineCtx){
		if(receiver.isMobile()){
//			DSMS_IF_00 dSMS_IF_00 = (DSMS_IF_00)lookupDataUnit(DSMS_IF_00.class);
			String[] mobileTokens = StringUtils.split(receiver.getMobileAddress(), '-');
			if(mobileTokens != null && mobileTokens.length == 3) {
				IDataSet dataSet = new DataSet();
				dataSet.put("MEMBER_NAME", receiver.getName());
				dataSet.put("MSG", msg);
				dataSet.put("DESTEL1", mobileTokens[0]);
				dataSet.put("DESTEL2", mobileTokens[1]);
				dataSet.put("DESTEL3", mobileTokens[2]);
				dSMS_IF_00.i001(dataSet, onlineCtx);
			}
		}
		if(receiver.isEmail()){
			
		}
	}

}
