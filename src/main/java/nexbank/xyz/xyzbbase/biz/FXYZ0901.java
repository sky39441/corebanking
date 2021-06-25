package nexbank.xyz.xyzbbase.biz;

import nexbank.common.OutboundHeader;
import nexbank.fwk.outbound.OutboundTarget;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IOutboundResponse;
import nexcore.framework.core.data.TransformType;
import nexcore.framework.core.util.ByteArrayWrap;


/**
 * [FU]아웃바운드샘플.
 * <pre>
 * 
 * </pre>
 * 
 * @author 차지환 (jihwancha)
 * @since 2016-08-09 17:23:35
 */
@BizUnit(value="[FU]아웃바운드샘플", type="FU")
public class FXYZ0901 extends nexbank.fwk.base.FunctionUnit  {

	/**
	 * FEP비동기전문송신(XA).
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:27:47
	 */
	@BizMethod("FEP비동기전문송신(XA)")
	public IDataSet sendFepXA(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
	    // DataSet을 전문으로 변환
	    IDataSet outboundDataSetReq = new DataSet();
	    ByteArrayWrap requestByteArrayWrap = transformByteArrayForXio(TransformType.FLAT, "<전문 레이아웃 아이디>", outboundDataSetReq, onlineCtx);
	    
	    // 전문 송신을 위한 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    
	    // 요청전문으로 설정
	    oh.setRequest();
	    
	    // FEP 비동기 전문 XA 모드 : 현재 거래가 성공(에러가 발생하지 않음)해야 전문이 전송된다.
	    sendOutboundAfterCommit(OutboundTarget.FEP, oh, requestByteArrayWrap, onlineCtx);
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}

	/**
	 * FEP비동기전문송신(NON-XA).
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:27:47
	 */
	@BizMethod("FEP비동기전문송신(NON-XA)")
	public IDataSet sendFepNonXA(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
	    // DataSet을 전문으로 변환
	    IDataSet outboundDataSetReq = new DataSet();
	    ByteArrayWrap requestByteArrayWrap = transformByteArrayForXio(TransformType.FLAT, "<전문 레이아웃 아이디>", outboundDataSetReq, onlineCtx);

	    // 전문 송신을 위한 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    
	    // 요청전문으로 설정
	    oh.setRequest();
	    
	    // FEP 비동기 전문 NON-XA 모드 : 즉신 전송된다.
	    sendOutboundNow(OutboundTarget.FEP, oh, requestByteArrayWrap, onlineCtx);
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}

	/**
	 * FEP동기전문송신.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:27:47
	 */
	@BizMethod("FEP동기전문송신")
	public IDataSet callFep(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
		
	    // DataSet을 전문으로 변환
	    IDataSet outboundDataSetReq = new DataSet();
	    ByteArrayWrap requestByteArrayWrap = transformByteArrayForXio(TransformType.FLAT, "<전문 레이아웃 아이디>", outboundDataSetReq, onlineCtx);

	    // 전문 송신을 위한 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    
	    // 요청전문으로 설정
	    oh.setRequest();
	    
	    // 동기전문 
	    IOutboundResponse outboundResponse = callOutbound(OutboundTarget.FEP, oh, requestByteArrayWrap, onlineCtx);
	    
	    // 응답결과 분석
	    IDataSet outboundDataSetRes = transformDataSetForXio(TransformType.FLAT, "<전문 레이아웃 아이디>", (ByteArrayWrap)outboundResponse.getBody(), onlineCtx);
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}

	/**
	 * MCI푸시전문-메시지(모달아님).
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:27:48
	 */
	@BizMethod("MCI푸시전문-메시지(모달아님)")
	public IDataSet sendMciPush01(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    // XA 여부
	    boolean isXA = false;

	    // 전문 송신을 위한 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    
	    // 푸시전문 유형
	    oh.setPush("M");
	    
	    // 수신자 설정
    	{
	    	// 해당 은행의 지정된 사용자에 송신하는 경우는 "부점코드", "사용자번호"를 설정
	    	{
	    		oh.setBrCd("<부점코드>");
	    		oh.setUsrNo("<사용자번호>");
	    	}
	    	
	    	// 해당 은행의 지정된 부점에 송신하는 경우는 "부점코드"를 설정
	    	{
	    		oh.setBrCd("<부점코드>");
	    		oh.setUsrNo(null);
	    	}
	    	
	    	// 해당 은행의 전체 단말에 송신하는 경우
	    	{
	    		oh.setBrCd(null);
	    		oh.setUsrNo(null);
	    	}
    	}
    	
    	// 전송할 메시지 다건 등록
    	oh.addMsg("<메시지코드>", null);

	    // XA 여부
	    if(isXA){
		    // 비동기 전문 XA 모드 : 현재 거래가 성공(에러가 발생하지 않음)해야 전문이 전송된다.
		    sendOutboundAfterCommit(OutboundTarget.MCI, oh, null, onlineCtx);
	    }
	    else {
		    // 비동기 전문 NON-XA 모드 : 즉신 전송된다.
	    	sendOutboundNow(OutboundTarget.MCI, oh, null, onlineCtx);
	    }

	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}

	/**
	 * MCI푸시전문-메시지(모달).
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:27:48
	 */
	@BizMethod("MCI푸시전문-메시지(모달)")
	public IDataSet sendMciPush02(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    // XA 여부
	    boolean isXA = false;

	    // 전문 송신을 위한 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    
	    // 푸시전문 유형
	    oh.setPush("O"); //모달메시지
	    
	    // 수신자 설정
    	{
	    	// 해당 은행의 지정된 사용자에 송신하는 경우는 "부점코드", "사용자번호"를 설정
	    	{
	    		oh.setBrCd("<부점코드>");
	    		oh.setUsrNo("<사용자번호>");
	    	}
	    	
	    	// 해당 은행의 지정된 부점에 송신하는 경우는 "부점코드"를 설정
	    	{
	    		oh.setBrCd("<부점코드>");
	    		oh.setUsrNo(null);
	    	}
	    	
	    	// 해당 은행의 전체 단말에 송신하는 경우
	    	{
	    		oh.setBrCd(null);
	    		oh.setUsrNo(null);
	    	}
    	}
    	
    	// 전송할 메시지 다건 등록
    	oh.addMsg("<메시지코드>", null);

	    // XA 여부
	    if(isXA){
		    // 비동기 전문 XA 모드 : 현재 거래가 성공(에러가 발생하지 않음)해야 전문이 전송된다.
	    	sendOutboundAfterCommit(OutboundTarget.MCI, oh, null, onlineCtx);
	    }
	    else {
		    // 비동기 전문 NON-XA 모드 : 즉신 전송된다.
		    sendOutboundNow(OutboundTarget.MCI, oh, null, onlineCtx);
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * MCI푸시전문-메시지(화면링크,모달아님).
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:27:48
	 */
	@BizMethod("MCI푸시전문-메시지(화면링크,모달아님)")
	public IDataSet sendMciPush03(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    // XA 여부
	    boolean isXA = false;

	    // 전문 송신을 위한 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    
	    // 푸시전문 유형
	    oh.setPush("L"); //화면링크
	    
	    // 화면번호설정
	    oh.setScrnNo("");
	    
	    // 수신자 설정
    	{
	    	// 해당 은행의 지정된 사용자에 송신하는 경우는 "부점코드", "사용자번호"를 설정
	    	{
	    		oh.setBrCd("<부점코드>");
	    		oh.setUsrNo("<사용자번호>");
	    	}
	    	
	    	// 해당 은행의 지정된 부점에 송신하는 경우는 "부점코드"를 설정
	    	{
	    		oh.setBrCd("<부점코드>");
	    		oh.setUsrNo(null);
	    	}
	    	
	    	// 해당 은행의 전체 단말에 송신하는 경우
	    	{
	    		oh.setBrCd(null);
	    		oh.setUsrNo(null);
	    	}
    	}
    	
    	// 전송할 메시지 단건 등록
    	oh.addMsg("<메시지코드>", null);

	    // XA 여부
	    if(isXA){
		    // 비동기 전문 XA 모드 : 현재 거래가 성공(에러가 발생하지 않음)해야 전문이 전송된다.
	    	sendOutboundAfterCommit(OutboundTarget.MCI, oh, null, onlineCtx);
	    }
	    else {
		    // 비동기 전문 NON-XA 모드 : 즉신 전송된다.
		    sendOutboundNow(OutboundTarget.MCI, oh, null, onlineCtx);
	    }
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * MCI푸시전문-화면지정.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:27:48
	 */
	@BizMethod("MCI푸시전문-화면지정")
	public IDataSet sendMciPush04(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    // XA 여부
	    boolean isXA = false;

	    // DataSet을 전문으로 변환
	    IDataSet outboundDataSetReq = new DataSet();
	    ByteArrayWrap requestByteArrayWrap = transformByteArrayForXio(TransformType.FLAT, "<전문 레이아웃 아이디>", outboundDataSetReq, onlineCtx);

	    // 전문 송신을 위한 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    
	    // 푸시전문 유형
	    oh.setPush("S");
	    
    	// 부점과 사용자를 변경해야 하는 경우는 다음과 같이 설정.
    	{
    		oh.setBrCd("<부점코드>");
    		oh.setUsrNo("<사용자번호>");
    	}

	    // 푸시전문에 데이타헤더 등록 
    	oh.addDataHeader("<출력거래코드>", "<출력여부(Y/N)>", "<출력화면번호>", "<배출구분코드>", "<유도메시지>");
	    
	    // XA 여부
	    if(isXA){
		    // 비동기 전문 XA 모드 : 현재 거래가 성공(에러가 발생하지 않음)해야 전문이 전송된다.
	    	sendOutboundAfterCommit(OutboundTarget.MCI, oh, requestByteArrayWrap, onlineCtx);
	    }
	    else {
		    // 비동기 전문 NON-XA 모드 : 즉신 전송된다.
		    sendOutboundNow(OutboundTarget.MCI, oh, requestByteArrayWrap, onlineCtx);
	    }
	    
	    return responseData;
	}

	/**
	 * MCI푸시전문-전일자거래(시작).
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:27:48
	 */
	@BizMethod("MCI푸시전문-전일자거래(시작)")
	public IDataSet sendMciPush05(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    // XA 여부
	    boolean isXA = false;

	    // 전문 송신을 위한 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    
	    // 푸시전문 유형
	    oh.setPush("X"); //전일자거래시작
	    
	    // 수신자 설정
    	{
	    	// 해당 은행의 지정된 사용자에 송신하는 경우는 "부점코드", "사용자번호"를 설정
	    	{
	    		oh.setBrCd("<부점코드>");
	    		oh.setUsrNo("<사용자번호>");
	    	}
	    	
	    	// 해당 은행의 지정된 부점에 송신하는 경우는 "부점코드"를 설정
	    	{
	    		oh.setBrCd("<부점코드>");
	    		oh.setUsrNo(null);
	    	}
	    	
	    	// 해당 은행의 전체 단말에 송신하는 경우
	    	{
	    		oh.setBrCd(null);
	    		oh.setUsrNo(null);
	    	}
    	}
    	
	    // XA 여부
	    if(isXA){
		    // 비동기 전문 XA 모드 : 현재 거래가 성공(에러가 발생하지 않음)해야 전문이 전송된다.
	    	sendOutboundAfterCommit(OutboundTarget.MCI, oh, null, onlineCtx);
	    }
	    else {
		    // 비동기 전문 NON-XA 모드 : 즉신 전송된다.
		    sendOutboundNow(OutboundTarget.MCI, oh, null, onlineCtx);
	    }
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * MCI푸시전문-전일자거래(종료).
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:27:48
	 */
	@BizMethod("MCI푸시전문-전일자거래(종료)")
	public IDataSet sendMciPush06(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    // XA 여부
	    boolean isXA = false;

	    // 전문 송신을 위한 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    
	    // 푸시전문 유형
	    oh.setPush("Y"); //전일자거래종료
	    
	    // 수신자 설정
    	{
	    	// 해당 은행의 지정된 사용자에 송신하는 경우는 "부점코드", "사용자번호"를 설정
	    	{
	    		oh.setBrCd("<부점코드>");
	    		oh.setUsrNo("<사용자번호>");
	    	}
	    	
	    	// 해당 은행의 지정된 부점에 송신하는 경우는 "부점코드"를 설정
	    	{
	    		oh.setBrCd("<부점코드>");
	    		oh.setUsrNo(null);
	    	}
	    	
	    	// 해당 은행의 전체 단말에 송신하는 경우
	    	{
	    		oh.setBrCd(null);
	    		oh.setUsrNo(null);
	    	}
    	}
    	
	    // XA 여부
	    if(isXA){
		    // 비동기 전문 XA 모드 : 현재 거래가 성공(에러가 발생하지 않음)해야 전문이 전송된다.
	    	sendOutboundAfterCommit(OutboundTarget.MCI, oh, null, onlineCtx);
	    }
	    else {
		    // 비동기 전문 NON-XA 모드 : 즉신 전송된다.
		    sendOutboundNow(OutboundTarget.MCI, oh, null, onlineCtx);
	    }
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * MCI푸시전문-CRM(표시).
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:27:48
	 */
	@BizMethod("MCI푸시전문-CRM(표시)")
	public IDataSet sendMciPush07(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    // XA 여부
	    boolean isXA = false;

	    // 전문 송신을 위한 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    
	    // 푸시전문 유형
	    oh.setPush("D"); //CRM표시
	    
	    // 수신자 설정
    	{
	    	// 해당 은행의 지정된 사용자에 송신하는 경우는 "부점코드", "사용자번호"를 설정
	    	{
	    		oh.setBrCd("<부점코드>");
	    		oh.setUsrNo("<사용자번호>");
	    	}
	    	
	    	// 해당 은행의 지정된 부점에 송신하는 경우는 "부점코드"를 설정
	    	{
	    		oh.setBrCd("<부점코드>");
	    		oh.setUsrNo(null);
	    	}
	    	
	    	// 해당 은행의 전체 단말에 송신하는 경우
	    	{
	    		oh.setBrCd(null);
	    		oh.setUsrNo(null);
	    	}
    	}
    	
	    // XA 여부
	    if(isXA){
		    // 비동기 전문 XA 모드 : 현재 거래가 성공(에러가 발생하지 않음)해야 전문이 전송된다.
	    	sendOutboundAfterCommit(OutboundTarget.MCI, oh, null, onlineCtx);
	    }
	    else {
		    // 비동기 전문 NON-XA 모드 : 즉신 전송된다.
		    sendOutboundNow(OutboundTarget.MCI, oh, null, onlineCtx);
	    }
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * MCI푸시전문-CRM(숨김).
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:27:48
	 */
	@BizMethod("MCI푸시전문-CRM(숨김)")
	public IDataSet sendMciPush08(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    // XA 여부
	    boolean isXA = false;

	    // 전문 송신을 위한 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    
	    // 푸시전문 유형
	    oh.setPush("H"); //CRM숨김
	    
	    // 수신자 설정
    	{
	    	// 해당 은행의 지정된 사용자에 송신하는 경우는 "부점코드", "사용자번호"를 설정
	    	{
	    		oh.setBrCd("<부점코드>");
	    		oh.setUsrNo("<사용자번호>");
	    	}
	    	
	    	// 해당 은행의 지정된 부점에 송신하는 경우는 "부점코드"를 설정
	    	{
	    		oh.setBrCd("<부점코드>");
	    		oh.setUsrNo(null);
	    	}
	    	
	    	// 해당 은행의 전체 단말에 송신하는 경우
	    	{
	    		oh.setBrCd(null);
	    		oh.setUsrNo(null);
	    	}
    	}
    	
	    // XA 여부
	    if(isXA){
		    // 비동기 전문 XA 모드 : 현재 거래가 성공(에러가 발생하지 않음)해야 전문이 전송된다.
	    	sendOutboundAfterCommit(OutboundTarget.MCI, oh, null, onlineCtx);
	    }
	    else {
		    // 비동기 전문 NON-XA 모드 : 즉신 전송된다.
		    sendOutboundNow(OutboundTarget.MCI, oh, null, onlineCtx);
	    }
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * MCI응답전문.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:27:48
	 */
	@BizMethod("MCI응답전문")
	public IDataSet sendMciResponse(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    // XA 여부
	    boolean isXA = false;

	    // DataSet을 전문으로 변환
	    IDataSet outboundDataSetReq = new DataSet();
	    ByteArrayWrap requestByteArrayWrap = transformByteArrayForXio(TransformType.FLAT, "<전문 레이아웃 아이디>", outboundDataSetReq, onlineCtx);

	    // 전문 송신을 위한 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    
	    // 응답전문 유형
	    oh.setResponse();
	    
    	// 부점과 사용자를 변경해야 하는 경우는 다음과 같이 설정.
    	{
    		oh.setBrCd("<부점코드>");
    		oh.setUsrNo("<사용자번호>");
    	}

	    // 푸시전문에 데이타헤더 등록 
    	oh.addDataHeader("<출력거래코드>", "<출력여부(Y/N)>", "<출력화면번호>", "<배출구분코드>", "<유도메시지>");
    	
    	// 응답 전문의 처리결과코드를 설정
    	oh.setOkResultMessage("<메시지코드>", null);
	    
    	
	    // XA 여부
	    if(isXA){
		    // 비동기 전문 XA 모드 : 현재 거래가 성공(에러가 발생하지 않음)해야 전문이 전송된다.
	    	sendOutboundAfterCommit(OutboundTarget.MCI, oh, requestByteArrayWrap, onlineCtx);
	    }
	    else {
		    // 비동기 전문 NON-XA 모드 : 즉신 전송된다.
		    sendOutboundNow(OutboundTarget.MCI, oh, requestByteArrayWrap, onlineCtx);
	    }
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * EAI비동기전문(XA).
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:27:48
	 */
	@BizMethod("EAI비동기전문(XA)")
	public IDataSet sendEaiAsyncXA(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
	    // DataSet을 전문으로 변환
	    IDataSet outboundDataSetReq = new DataSet();
	    ByteArrayWrap requestByteArrayWrap = transformByteArrayForXio(TransformType.FLAT, "<전문 레이아웃 아이디>", outboundDataSetReq, onlineCtx);

	    // 전문 송신을 위한 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    
	    // 요청전문으로 설정
	    oh.setRequest();
	    
	    // EAI는 XA만 지원한다.
	    // 비동기 전문 XA 모드 : 현재 거래가 성공(에러가 발생하지 않음)해야 전문이 전송된다.
	    sendOutboundAfterCommit(OutboundTarget.EAI, oh, requestByteArrayWrap, onlineCtx);
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}

	/**
	 * callEai.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:27:48
	 */
	@BizMethod("callEai")
	public IDataSet callEai(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
	    // DataSet을 전문으로 변환
	    IDataSet outboundDataSetReq = new DataSet();
	    ByteArrayWrap requestByteArrayWrap = transformByteArrayForXio(TransformType.FLAT, "<전문 레이아웃 아이디>", outboundDataSetReq, onlineCtx);

	    // 전문 송신을 위한 헤더 생성
	    OutboundHeader oh = new OutboundHeader(onlineCtx);
	    
	    // 요청전문으로 설정
	    oh.setRequest();
	    
	    // 비동기 전문 XA 모드 : 현재 거래가 성공(에러가 발생하지 않음)해야 전문이 전송된다.
	    IOutboundResponse outboundResponse = callOutbound(OutboundTarget.EAI, oh, requestByteArrayWrap, onlineCtx);
	    
	    // 응답결과 분석
	    IDataSet outboundDataSetRes = transformDataSetForXio(TransformType.FLAT, "<전문 레이아웃 아이디>", (ByteArrayWrap)outboundResponse.getBody(), onlineCtx);
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}

}
