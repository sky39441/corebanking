package nexbank.fwk.bfafprcss.biz;

import nexbank.common.CommonArea;
import nexbank.common.CommonUtils;
import nexcore.framework.core.component.streotype.BizAttribute;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.ioc.BeanRegistry;
import nexcore.framework.core.log.LogManager;
import nexcore.framework.core.service.internal.OnlineContextHelper;
import nexcore.framework.core.service.txblocking.ITxBlockingManager;
import nexcore.framework.core.service.txfilter.IServiceFilter;
import nexcore.framework.core.service.txprofile.ITxProfile;
import nexcore.framework.core.service.txprofile.ITxProfileAttr;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.core.util.StringUtils;

import org.apache.commons.logging.Log;


/**
 * [FU]거래제어관리.
 * <pre>
 * 
 * </pre>
 * 
 * @author 차지환 (jihwancha)
 * @since 2016-08-09 17:03:54
 */
@BizUnit(value="[FU]거래제어관리", type="FU")
public class FFWK0901 extends nexbank.fwk.base.FunctionUnit  {

	/* 
    * 이 클래스는 Singleton 객체로 수행됩니다. 
    * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
    */

	/**
	 * Default Constructor
	 */
	public FFWK0901(){
		super();
	}
	
	/**
	 * 거래통제처리.
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
	 * @since 2016-08-09 17:04:04
	 */
	@BizMethod("거래통제처리")
	@BizAttribute(isShared=true)
	public IDataSet txBlocking(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
	    // 거래통제 실행
	    ITxBlockingManager txBlockingManager = (ITxBlockingManager)BeanRegistry.lookup(ITxBlockingManager.BEAN_ID);
	    txBlockingManager.checkTxBlocking(onlineCtx);
	    
		// 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}
	

	/**
	 * 거래제어처리.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:04:04
	 */
	@BizMethod("거래제어처리")
	@BizAttribute(isShared=true)
	public IDataSet txControl(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    Log log = getLog(onlineCtx);

		IDataSet requestDataSet  = (IDataSet)requestData.get(IServiceFilter.REQUEST_DATASET_KEY);
		IDataSet responseDataSet = (IDataSet)requestData.get(IServiceFilter.RESPONSE_DATASET_KEY);

		// FIXME 거래프로파일이 설정되지 않은 경우 거래 허용 여부
		boolean passingIfNotFound = false;
		
		// 거래프로파일 정보를 조회한다.
		ITxProfile txprofile = BaseUtils.getTxProfile(onlineCtx.getTxId());
		
		// 시스템 구분
		String runtimeMode = BaseUtils.getRuntimeMode();
		boolean isRealSystem  = "R".equals(runtimeMode) || "P".equals(runtimeMode); // 운영 시스템
		boolean isTestSystem  = "T".equals(runtimeMode) || "S".equals(runtimeMode); // 테스트/스테이징 시스템
		boolean isDevSystem   = "D".equals(runtimeMode);                            // 개발 시스템
		
//		if(onlineCtx.getReqeustProtocol() == IOnlineContext.PROTOCOL_SOAP){
//			return responseData;
//		}
		// FIXME 거래프로파일이 등록되지 않은 경우 처리 필요.
		if(txprofile == null){
			
			if(isRealSystem){ // 운영 시스템
				if(!passingIfNotFound){
					throw new BizRuntimeException("FWKE0001"); //거래 프로파일이 등록되지 않았습니다.
				}
			}
			else if(isTestSystem){ // 테스트/스테이징 시스템
				if(!passingIfNotFound){
					throw new BizRuntimeException("FWKE0001"); //거래 프로파일이 등록되지 않았습니다.
				}
			}
			else if(isDevSystem){ // 개발 시스템
				boolean isUtest = StringUtils.endsWith(onlineCtx.getRequestUri(), ".utest"); //단위테스트 여부
				
				if(!passingIfNotFound && !isUtest){
					throw new BizRuntimeException("FWKE0001"); //거래 프로파일이 등록되지 않았습니다.
				}
			}
			else { // 로컬 시스템
				// TODO 로컬은 거래프로파일 설정되지 않았더라도, 거래를 허용하도록 처리한다.
			}
		}
		else {
			// 거래제어를 허용한 경우에만 처리함.
			if(txprofile.isAttrYn()){
				if(log.isDebugEnabled()){
					log.debug("거래제어 처리 시작");
				}

				// 긴급 차단 여부 체크
				ITxProfileAttr attr = txprofile.getAttr("EMERGENCY_BLOCK_YN");
				if (attr != null && "Y".equals(attr.getValue())) {
					throw new BizRuntimeException("COME9002"); // 긴급 차단된 거래입니다
				};
				
				/**
				 * 01 : 회계발생여부
				 * 02 : 책임자승인발생여부
				 * 03 : 취소가능여부
				 * 04 : 정정가능여부
				 * 05 : 서비스가능 시작시각
				 * 06 : 서비스가능 종료시각
				 * 07 : 휴일거래가능여부
				 * 08 : 이전일자거래가능여부
				 * 09 : 서비스가능채널 
				 * 
				 * 14 : 허용권한그룹
				 */

				// Common Area
				CommonArea ca = getCommonArea(onlineCtx);
				
				// 취소가능여부, 정정가능여부
				_checkCanCrctDvcd(txprofile, ca);
				
				// 서비스가능 시작시각, 서비스가능 종료시각
				_checkServiceTime(txprofile, ca);
				
				// 휴일거래가능여부
				_checkHlddDvcd(txprofile, ca);
				
				// 이전일자거래가능여부
				_checkBfrDtLoinYn(txprofile, ca);
				
				// 서비스가능채널
				_checkTrnmChnlCd(txprofile, ca);
	
				// 허용권한그룹
//				_checkUserAuthorityGroup(txprofile, ca);
				
				if(log.isDebugEnabled()){
					log.debug("거래제어 처리 완료");
				}
			}
		}
		
		// 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}
	
	/**
	 * 취소정정구분코드를 기준으로 "취소거래", "정정거래"를 체크한다.
	 */
	private void _checkCanCrctDvcd(nexcore.framework.core.service.txprofile.ITxProfile txprofile, CommonArea ca){
//		//취소정정구분코드 (0:일반, 1:취소, 2:정정)
//		if("1".equals(ca.getCanCrctDvcd())){ //취소거래
//			nexcore.framework.core.service.txprofile.ITxProfileAttr attr = txprofile.getAttr("CAN_DVCD"); //취소가능여부
//			if(!"Y".equals(attr.getValue())){
//				throw new BizRuntimeException("FWKE0002", new String[]{"취소가능여부"});
//			}
//		}
//		else if("2".equals(ca.getCanCrctDvcd())){//정정거래
//			nexcore.framework.core.service.txprofile.ITxProfileAttr attr = txprofile.getAttr("CRCT_DVCD"); //정정가능여부
//			if(!"Y".equals(attr.getValue())){
//				throw new BizRuntimeException("FWKE0002", new String[]{"정정가능여부"});
//			}
//		}
	}
	
	/**
	 * 현재시각을 기준으로 서비스 가능 시각을 체크한다.
	 */
	private void _checkServiceTime(nexcore.framework.core.service.txprofile.ITxProfile txprofile, CommonArea ca){
		String hhmm = DateUtils.getDateString("HHmm"); //현재시각
		nexcore.framework.core.service.txprofile.ITxProfileAttr attr5 = txprofile.getAttr("SVC_STR_TM"); //서비스가능 시작시각
		nexcore.framework.core.service.txprofile.ITxProfileAttr attr6 = txprofile.getAttr("SVC_END_TM"); //서비스가능 종료시각
		if(hhmm.compareTo(attr5.getValue()) < 0 || hhmm.compareTo(attr6.getValue()) > 0){
			throw new BizRuntimeException("FWKE0002", new String[]{"서비스가능 시각:"+attr5.getValue() + "~" +attr6.getValue()+", 현재시각:" + hhmm});
		}
	}
	
	/**
	 * 휴일구분코드를 기준으로 휴일거래가능여부를 체크한다.
	 */
	private void _checkHlddDvcd(nexcore.framework.core.service.txprofile.ITxProfile txprofile, CommonArea ca){
//		//휴일구분코드
//		if("1".equals(ca.getHlddDvcd())){
//			nexcore.framework.core.service.txprofile.ITxProfileAttr attr = txprofile.getAttr("HLDD_DVCD"); //휴일거래가능여부
//			if(!"Y".equals(attr.getValue())){
//				throw new BizRuntimeException("FWKE0002", new String[]{"휴일거래가능여부"});
//			}
//		}
	}
	
	/**
	 * 이전일자로그인여부를 기준으로 "이전일자거래가능여부"를 체크한다.
	 */
	private void _checkBfrDtLoinYn(nexcore.framework.core.service.txprofile.ITxProfile txprofile, CommonArea ca){
//		//이전일자로그인여부 (N:당일거래, Y:이전일자거래)
//		if("Y".equals(ca.getBfrDtLoinYn())){
//			nexcore.framework.core.service.txprofile.ITxProfileAttr attr = txprofile.getAttr("BFR_DT_LOIN_YN"); //이전일자거래가능여부
//			if(!"Y".equals(attr.getValue())){
//				throw new BizRuntimeException("FWKE0002", new String[]{"이전일자거래가능여부"});
//			}
//		}
	}
	
	/**
	 * 전송채널을 기준으로 "서비스가능채널"을 체크한다.
	 */
	private void _checkTrnmChnlCd(nexcore.framework.core.service.txprofile.ITxProfile txprofile, CommonArea ca){
		nexcore.framework.core.service.txprofile.ITxProfileAttr attr = txprofile.getAttr("SVC_CHNL_CD"); //서비스가능채널
//		if(!attr.patternMatches(ca.getTrnmChnlCd())){
		if(!"*".equals(attr.getValue()) && !attr.listContains(ca.getTrnmChnlCd())){
			throw new BizRuntimeException("FWKE0002", new String[]{"서비스가능채널"});
		}
	}

	/**
	 * 허용권한그룹
	 */
	private void _checkUserAuthorityGroup(nexcore.framework.core.service.txprofile.ITxProfile txprofile, CommonArea ca){
		nexcore.framework.core.service.txprofile.ITxProfileAttr attr = txprofile.getAttr("USER_AUTH_GRP"); //허용권한그룹
		if(attr.getValue() != null && attr.getValue().length() > 0){
			if(!"*".equals(attr.getValue()) && !attr.listContains(ca.getUserAuthGrp())){
				throw new BizRuntimeException("FWKE0002", new String[]{"허용권한그룹"});
			}
		}
	}

	/**
	 * 거래강제롤백설정.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:04:04
	 */
	@BizMethod("거래강제롤백설정")
	@BizAttribute(isShared=true)
	public IDataSet txRollbackMark(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();

        CommonArea ca = CommonUtils.getCommonArea(onlineCtx);

        // 강제롤백여부 설정값이 존재하지 않는 경우에만 처리한다. (연동거래시에는 이미 설정되어 있음)
		if(!OnlineContextHelper.containsForceRollbackMarked(onlineCtx)){
			// 롤백 대상 여부를 확인한다.
	        
	        // 롤백대상채널 && 예비문자열내용(C:COMMIT, R:ROLLBACK)
	        boolean isRollbackMarked = "UTM".equals(ca.getFrstTrnmChnlCd()) && "R".equalsIgnoreCase(ca.getSprChrsCntn());
	        OnlineContextHelper.setForceRollbackMarked(onlineCtx, isRollbackMarked);
	        
	        if(isRollbackMarked){
	        	Log log = LogManager.getFwkLog();
	        	log.warn("** FORCE ROLLBACK MARKING: (REQUEST_ID:" + ca.getGlobId() + ", TX_ID:" + ca.getTxId() + ")");
	        }
		}

	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}
	
}
