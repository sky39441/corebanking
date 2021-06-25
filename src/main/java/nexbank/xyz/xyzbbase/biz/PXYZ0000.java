package nexbank.xyz.xyzbbase.biz;

import nexcore.framework.channel.web.WebConstants;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.UserInfo;



/**
 * [PU]로그인.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin (admin)
 * @since 2016-08-09 17:23:16
 */
@BizUnit(value="[PU]로그인", type="PU")
public class PXYZ0000 extends nexbank.fwk.base.ProcessUnit  {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PXYZ0000(){
		super();
	}

	/**
	 * 로그인.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:23:16
	 */
	@BizMethod("로그인")
	public IDataSet pXYZ00001(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
	    // TODO HttpServletRequest의 header분석 설정이 되어 있는 경우 아래와 같이 헤더 정보를 조회할 수 있다.
//	    IDataSet requestHeaderDataSet = (IDataSet)requestData.get(WebConstants.REQUEST_HEADER_DATASET);
//	    if(requestHeaderDataSet != null){
//	    	
//	    }
	    
	    //TODO 사용자 정보 조회

	    //TODO 사용자가 존재하지 않는 경우나 비밀번호가 일치하지 않는 경우에는 예외 발생

	    
	    //TODO 조회된 사용자 정보로 사용자 객체 생성. 
	    //UserInfo는 Map 기반이므로 put()을 사용하여 관련 정보 모두 관리.
	    UserInfo userInfo = new UserInfo();
	    userInfo.setLoginId("guest");
	    
	    // userInfo.put("AAA", "AAAA");
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
//	    responseData.put(WebConstants.USER_INFO, userInfo); // FWK에서 세션에 관리하고 삭제함.
//	    responseData.setOkResultMessage("<메시지 코드>", null);
	    return responseData;
	}

	/**
	 * 로그아웃.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:23:16
	 */
	@BizMethod("로그아웃")
	public IDataSet pXYZ00002(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
	    //TODO 세션에서 삭제 처리는 FWK에서 수행하므로, 로그아웃 관련 이력 관리등 을 처리하면 됨.
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
//	    responseData.setOkResultMessage("<메시지 코드>", null);
	    return responseData;
	}
  
}
