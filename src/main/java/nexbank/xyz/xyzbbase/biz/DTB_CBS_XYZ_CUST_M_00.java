package nexbank.xyz.xyzbbase.biz;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * [DU]고객정보.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin (admin)
 * @since 2016-08-09 17:28:13
 */
@BizUnit(value="[DU]고객정보", type="DU")
public class DTB_CBS_XYZ_CUST_M_00 extends nexbank.fwk.base.DataUnit  {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DTB_CBS_XYZ_CUST_M_00(){
		super();
	}

	/**
	 * 고객정보 단건조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ID () [아이디]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : ID () [아이디]
	 *	- field : NAME () [한글명]
	 *	- field : ENG_NAME () [영문명]
	 *	- field : BIRTHDAY () [생년월일]
	 *	- field : CUSTOMER_TYPE () [고객타입]
	 *	- field : CELLPHONE () [휴대전화]
	 *	- field : EMAIL () [이메일]
	 *	- field : POSTAL_CODE () [우편번호]
	 *	- field : ADDRESS () [자택주소]
	 *	- field : ADDRESS_DETAIL () [상세주소]
	 *	- field : HOME_PHONE () [자택전화]
	 *	- field : COMPANY () [직장명]
	 *	- field : DEPARTMENT () [부서명]
	 *	- field : POSITION () [직위]
	 *	- field : COMPANY_PHONE () [직장전화]
	 *	- field : BLOOD_TYPE () [혈액형]
	 *	- field : RELIGION () [종교]
	 *	- field : HOBBY () [취미]
	 *	- field : WEDDING_ANNIVERSARY () [결혼기념일]
	 *	- field : INCOME_FOR_YEAR () [연수입]
	 *	- field : SAVING_FOR_MONTH () [월저축]
	 *	- field : CREATED_DATE () [등록일]
	 *	- field : MODIFIED_DATE () [수정일]
	 *	- field : NOTES () [비고]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:13
	 */
	@BizMethod("고객정보 단건조회")
	public IDataSet s001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    IRecord record = dbSelectSingle("S001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    if(record != null){
	      responseData.putAll(record);
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * 고객정보 등록.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ID () [아이디]
	 *	- field : NAME () [한글명]
	 *	- field : ENG_NAME () [영문명]
	 *	- field : BIRTHDAY () [생년월일]
	 *	- field : CUSTOMER_TYPE () [고객타입]
	 *	- field : CELLPHONE () [휴대전화]
	 *	- field : EMAIL () [이메일]
	 *	- field : POSTAL_CODE () [우편번호]
	 *	- field : ADDRESS () [자택주소]
	 *	- field : ADDRESS_DETAIL () [상세주소]
	 *	- field : HOME_PHONE () [자택전화]
	 *	- field : COMPANY () [직장명]
	 *	- field : DEPARTMENT () [부서명]
	 *	- field : POSITION () [직위]
	 *	- field : COMPANY_PHONE () [직장전화]
	 *	- field : BLOOD_TYPE () [혈액형]
	 *	- field : RELIGION () [종교]
	 *	- field : HOBBY () [취미]
	 *	- field : WEDDING_ANNIVERSARY () [결혼기념일]
	 *	- field : INCOME_FOR_YEAR () [연수입]
	 *	- field : SAVING_FOR_MONTH () [월저축]
	 *	- field : CREATED_DATE () [등록일]
	 *	- field : MODIFIED_DATE () [수정일]
	 *	- field : NOTES () [비고]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW () [반영건수]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:13
	 */
	@BizMethod("고객정보 등록")
	public IDataSet i001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbInsert("I001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    responseData.put("row", row);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * 고객정보 수정.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ID () [아이디]
	 *	- field : NAME () [한글명]
	 *	- field : ENG_NAME () [영문명]
	 *	- field : BIRTHDAY () [생년월일]
	 *	- field : CUSTOMER_TYPE () [고객타입]
	 *	- field : CELLPHONE () [휴대전화]
	 *	- field : EMAIL () [이메일]
	 *	- field : POSTAL_CODE () [우편번호]
	 *	- field : ADDRESS () [자택주소]
	 *	- field : ADDRESS_DETAIL () [상세주소]
	 *	- field : HOME_PHONE () [자택전화]
	 *	- field : COMPANY () [직장명]
	 *	- field : DEPARTMENT () [부서명]
	 *	- field : POSITION () [직위]
	 *	- field : COMPANY_PHONE () [직장전화]
	 *	- field : BLOOD_TYPE () [혈액형]
	 *	- field : RELIGION () [종교]
	 *	- field : HOBBY () [취미]
	 *	- field : WEDDING_ANNIVERSARY () [결혼기념일]
	 *	- field : INCOME_FOR_YEAR () [연수입]
	 *	- field : SAVING_FOR_MONTH () [월저축]
	 *	- field : CREATED_DATE () [등록일]
	 *	- field : MODIFIED_DATE () [수정일]
	 *	- field : NOTES () [비고]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW () [반영건수]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:13
	 */
	@BizMethod("고객정보 수정")
	public IDataSet u001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbUpdate("U001", requestData, onlineCtx);
	    
	    if (row < 1) {
	    	throw new BizRuntimeException("고객 ID 가 존재하지 않습니다.");
	    }
	    // Auto-generated make response block
	    responseData.put("EXPECTED_ROW", row);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * 고객정보 삭제.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ID () [아이디]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW () [반영건수]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:13
	 */
	@BizMethod("고객정보 삭제")
	public IDataSet d001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbDelete("D001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    responseData.put("row", row);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * 검색조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SEARCH_VALUE () [검색값]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:13
	 */
	@BizMethod("검색조회")
	public IDataSet s002(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    IRecordSet recordset = dbSelect("S002", requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("CUST_LIST", recordset);
	
	    return responseData;
	}
  
}
