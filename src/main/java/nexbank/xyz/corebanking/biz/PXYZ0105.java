package nexbank.xyz.corebanking.biz;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.component.streotype.BizUnitBind;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;


/**
 * [PU]고객관리.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin(admin)
 * @since 2016-08-09 17:16:58
 */
@BizUnit(value="[PU]고객관리", type="PU")
public class PXYZ0105 extends nexbank.fwk.base.ProcessUnit  {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */
	
	@BizUnitBind
	private DTB_CBS_XYZ_CUST_M_00 dTB_CBS_XYZ_CUST_M_00;

	/**
	 * Default Constructor
	 */
	public PXYZ0105(){
		super();
	}

	/**
	 * 고객검색.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SEARCH_VALUE (string) [검색값]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : CUST_SIZE (int) [고객목록 건수]
	 *	- recordSet : CUST_LIST [고객목록]
	 *		- field : ID (string) [아이디]
	 *		- field : NAME (string) [한글명]
	 *		- field : ENG_NAME (string) [영문명]
	 *		- field : BIRTHDAY (string) [생년월일]
	 *		- field : CUSTOMER_TYPE (string) [고객타입]
	 *		- field : CELLPHONE (string) [휴대전화]
	 *		- field : EMAIL (string) [이메일]
	 *		- field : POSTAL_CODE (string) [우편번호]
	 *		- field : ADDRESS (string) [자택주소]
	 *		- field : ADDRESS_DETAIL (string) [상세주소]
	 *		- field : HOME_PHONE (string) [자택전화]
	 *		- field : COMPANY (string) [직장명]
	 *		- field : DEPARTMENT (string) [부서명]
	 *		- field : POSITION (string) [직위]
	 *		- field : COMPANY_PHONE (string) [직장전화]
	 *		- field : BLOOD_TYPE (string) [혈액형]
	 *		- field : RELIGION (string) [종교]
	 *		- field : HOBBY (string) [취미]
	 *		- field : WEDDING_ANNIVERSARY (string) [결혼기념일]
	 *		- field : INCOME_FOR_YEAR (string) [연수입]
	 *		- field : SAVING_FOR_MONTH (string) [월저축]
	 *		- field : CREATED_DATE (string) [등록일]
	 *		- field : MODIFIED_DATE (string) [수정일]
	 *		- field : NOTES (string) [비고]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:17:12
	 */
	@BizMethod("고객검색")
	public IDataSet pXYZ01501(IDataSet requestData, IOnlineContext onlineCtx){
		try {
		    IDataSet responseData = new DataSet();
		    
		    // 고객 검색
//		    DTB_CBS_XYZ_CUST_M_00 dTB_CBS_XYZ_CUST_M_00 = (DTB_CBS_XYZ_CUST_M_00)lookupDataUnit(DTB_CBS_XYZ_CUST_M_00.class);
		    IDataSet dTB_CBS_XYZ_CUST_M_00_S002Res = dTB_CBS_XYZ_CUST_M_00.s002(requestData, onlineCtx);
		    
		    // 검색된 고객 목록
		    IRecordSet CUST_LIST = dTB_CBS_XYZ_CUST_M_00_S002Res.getRecordSet("CUST_LIST");
		    
		    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
		    if(CUST_LIST != null){
		    	responseData.put("CUST_SIZE", CUST_LIST.getRecordCount());
		    	responseData.put("CUST_LIST", CUST_LIST);
		    }
		    else {
		    	responseData.put("CUST_SIZE", 0);
		    }
		    
//		    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
		    return responseData;
	    }
	    catch(BizRuntimeException e){
	    	throw e;
	    }
	    catch(Exception e){
	    	throw new BizRuntimeException("ECOM0000", new String[]{"고객조회"}, e);
	    }
	}

	/**
	 * 고객정보변경.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ID (string) [아이디]
	 *	- field : NAME (string) [한글명]
	 *	- field : ENG_NAME (string) [영문명]
	 *	- field : BIRTHDAY (string) [생년월일]
	 *	- field : CUSTOMER_TYPE (string) [고객타입]
	 *	- field : CELLPHONE (string) [휴대전화]
	 *	- field : EMAIL (string) [이메일]
	 *	- field : POSTAL_CODE (string) [우편번호]
	 *	- field : ADDRESS (string) [자택주소]
	 *	- field : ADDRESS_DETAIL (string) [상세주소]
	 *	- field : HOME_PHONE (string) [자택전화]
	 *	- field : COMPANY (string) [직장명]
	 *	- field : DEPARTMENT (string) [부서명]
	 *	- field : POSITION (string) [직위]
	 *	- field : COMPANY_PHONE (string) [직장전화]
	 *	- field : BLOOD_TYPE (string) [혈액형]
	 *	- field : RELIGION (string) [종교]
	 *	- field : HOBBY (string) [취미]
	 *	- field : WEDDING_ANNIVERSARY (string) [결혼기념일]
	 *	- field : INCOME_FOR_YEAR (string) [연수입]
	 *	- field : SAVING_FOR_MONTH (string) [월저축]
	 *	- field : NOTES (string) [비고]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW (int) [반영건수]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:17:12
	 */
	@BizMethod("고객정보변경")
	public IDataSet pXYZ01502(IDataSet requestData, IOnlineContext onlineCtx){
		try {
		    IDataSet responseData = new DataSet();
		    
		    // 고객 아이디 검증
		    if(StringUtils.isEmpty(requestData.getString("ID"))){
		    	throw new RuntimeException("고객 아이디가 입력되지 않았습니다.");
		    }
		    
		    // 고객 검색
//		    DTB_CBS_XYZ_CUST_M_00 dTB_CBS_XYZ_CUST_M_00 = (DTB_CBS_XYZ_CUST_M_00)lookupDataUnit(DTB_CBS_XYZ_CUST_M_00.class);
		    IDataSet dTB_CBS_XYZ_CUST_M_00_U001Res = dTB_CBS_XYZ_CUST_M_00.u001(requestData, onlineCtx);
		    
		    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
		    responseData.put("EXPECTED_ROW", dTB_CBS_XYZ_CUST_M_00_U001Res.get("EXPECTED_ROW"));
		    
//		    responseData.setOkResultMessage("XYZN0001", null); //정상처리 되었습니다.
		    return responseData;
	    }
	    catch(BizRuntimeException e){
	    	throw e;
	    }
	    catch(Exception e){
	    	throw new BizRuntimeException("ECOM0000", new String[]{"고객정보 변경"}, e);
	    }
	}
  
}
