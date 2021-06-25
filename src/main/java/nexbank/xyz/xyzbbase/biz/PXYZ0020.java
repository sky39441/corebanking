package nexbank.xyz.xyzbbase.biz;

import nexbank.common.OutboundHeader;
import nexbank.fwk.outbound.OutboundTarget;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;


/**
 * [PU]메시지 푸시.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin (admin)
 * @since 2016-08-09 17:22:08
 */
@BizUnit(value="[PU]메시지 푸시", type="PU")
public class PXYZ0020 extends nexbank.fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PXYZ0020(){
		super();
	}

	/**
	 * 단말 메시지 푸시 요청.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:22:08
	 */
	@BizMethod("단말 메시지 푸시 요청")
	public IDataSet pXYZ00201(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    callDelayAsyncService("XYZ00202", 5, requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
	    return responseData;
	}

	/**
	 * 단말 메시지 푸시 처리.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:22:08
	 */
	@BizMethod("단말 메시지 푸시 처리")
	public IDataSet pXYZ00202(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

    	// 단말에 타행이체 성공 결과를 전송한다.
	    OutboundHeader header = new OutboundHeader(onlineCtx);
	    header.setFrstTrnmChnlCd("UST");
	    header.setPush("M");
	    header.setIpAddr("127.0.0.1");
	    header.addMsg("NCOM0000");
	    sendOutboundAfterCommit(OutboundTarget.MCI, header, null, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
	    return responseData;
	}
  
}
