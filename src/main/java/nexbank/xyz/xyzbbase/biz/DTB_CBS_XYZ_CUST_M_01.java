package nexbank.xyz.xyzbbase.biz;

import java.util.Map;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;

/**
 * 고객정보.
 * <pre>
 * 고객정보
 * </pre>
 * 
 * @author admin (Administrator)
 * @since 2018-09-09 23:32:53
 */
@BizUnit(value="고객정보", type="DU")
public class DTB_CBS_XYZ_CUST_M_01 extends nexbank.fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DTB_CBS_XYZ_CUST_M_01(){
		super();
	}

	/**
	 * 고객정보 SELECT.
	 * <pre>
	 * 고객정보 SELECT
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ID [아이디] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : ID [아이디] (string)
	 *	- field : NAME [한글명] (string)
	 *	- field : ENG_NAME [영문명] (string)
	 *	- field : BIRTHDAY [생년월일] (string)
	 *	- field : CUSTOMER_TYPE [고객타입] (string)
	 *	- field : CELLPHONE [휴대전화] (string)
	 *	- field : EMAIL [이메일] (string)
	 *	- field : POSTAL_CODE [우편번호] (string)
	 *	- field : ADDRESS [자택주소] (string)
	 *	- field : ADDRESS_DETAIL [상세주소] (string)
	 *	- field : HOME_PHONE [자택전화] (string)
	 *	- field : COMPANY [직장명] (string)
	 *	- field : DEPARTMENT [부서명] (string)
	 *	- field : POSITION [직위] (string)
	 *	- field : COMPANY_PHONE [직장전화] (string)
	 *	- field : BLOOD_TYPE [혈액형] (string)
	 *	- field : RELIGION [종교] (string)
	 *	- field : HOBBY [취미] (string)
	 *	- field : WEDDING_ANNIVERSARY [결혼기념일] (string)
	 *	- field : INCOME_FOR_YEAR [연수입] (bigDecimal)
	 *	- field : SAVING_FOR_MONTH [월저축] (bigDecimal)
	 *	- field : CREATED_DATE [등록일] (string)
	 *	- field : MODIFIED_DATE [수정일] (string)
	 *	- field : NOTES [비고] (string)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2018-09-09 23:32:55
	 */
	@BizMethod("고객정보 SELECT")
	public IDataSet s001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    IRecord record = dbSelectSingle("s001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    if (record != null){
	        responseData.putAll(record);
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * 고객정보 SELECT ALL.
	 * <pre>
	 * 고객정보 SELECT ALL
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- recordSet : LIST [고객정보 LIST ALL]
	 *		- field : ID [아이디] (string)
	 *		- field : NAME [한글명] (string)
	 *		- field : ENG_NAME [영문명] (string)
	 *		- field : BIRTHDAY [생년월일] (string)
	 *		- field : CUSTOMER_TYPE [고객타입] (string)
	 *		- field : CELLPHONE [휴대전화] (string)
	 *		- field : EMAIL [이메일] (string)
	 *		- field : POSTAL_CODE [우편번호] (string)
	 *		- field : ADDRESS [자택주소] (string)
	 *		- field : ADDRESS_DETAIL [상세주소] (string)
	 *		- field : HOME_PHONE [자택전화] (string)
	 *		- field : COMPANY [직장명] (string)
	 *		- field : DEPARTMENT [부서명] (string)
	 *		- field : POSITION [직위] (string)
	 *		- field : COMPANY_PHONE [직장전화] (string)
	 *		- field : BLOOD_TYPE [혈액형] (string)
	 *		- field : RELIGION [종교] (string)
	 *		- field : HOBBY [취미] (string)
	 *		- field : WEDDING_ANNIVERSARY [결혼기념일] (string)
	 *		- field : INCOME_FOR_YEAR [연수입] (bigDecimal)
	 *		- field : SAVING_FOR_MONTH [월저축] (bigDecimal)
	 *		- field : CREATED_DATE [등록일] (string)
	 *		- field : MODIFIED_DATE [수정일] (string)
	 *		- field : NOTES [비고] (string)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2018-09-09 23:32:55
	 */
	@BizMethod("고객정보 SELECT ALL")
	public IDataSet s002(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    IRecordSet recordset = dbSelect("s002", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    if (recordset.getRecordCount() > 0){
	        responseData.put("LIST", recordset);
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * 고객정보 SELECT FOR UPDATE.
	 * <pre>
	 * 고객정보 SELECT FOR UPDATE
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ID [아이디] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : ID [아이디] (string)
	 *	- field : NAME [한글명] (string)
	 *	- field : ENG_NAME [영문명] (string)
	 *	- field : BIRTHDAY [생년월일] (string)
	 *	- field : CUSTOMER_TYPE [고객타입] (string)
	 *	- field : CELLPHONE [휴대전화] (string)
	 *	- field : EMAIL [이메일] (string)
	 *	- field : POSTAL_CODE [우편번호] (string)
	 *	- field : ADDRESS [자택주소] (string)
	 *	- field : ADDRESS_DETAIL [상세주소] (string)
	 *	- field : HOME_PHONE [자택전화] (string)
	 *	- field : COMPANY [직장명] (string)
	 *	- field : DEPARTMENT [부서명] (string)
	 *	- field : POSITION [직위] (string)
	 *	- field : COMPANY_PHONE [직장전화] (string)
	 *	- field : BLOOD_TYPE [혈액형] (string)
	 *	- field : RELIGION [종교] (string)
	 *	- field : HOBBY [취미] (string)
	 *	- field : WEDDING_ANNIVERSARY [결혼기념일] (string)
	 *	- field : INCOME_FOR_YEAR [연수입] (bigDecimal)
	 *	- field : SAVING_FOR_MONTH [월저축] (bigDecimal)
	 *	- field : CREATED_DATE [등록일] (string)
	 *	- field : MODIFIED_DATE [수정일] (string)
	 *	- field : NOTES [비고] (string)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2018-09-09 23:32:56
	 */
	@BizMethod("고객정보 SELECT FOR UPDATE")
	public IDataSet s003(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    IRecord record = dbSelectSingle("s003", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    if (record != null){
	        responseData.putAll(record);
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * 고객정보 INSERT.
	 * <pre>
	 * 고객정보 INSERT
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ID [아이디] (string)
	 *	- field : NAME [한글명] (string)
	 *	- field : ENG_NAME [영문명] (string)
	 *	- field : BIRTHDAY [생년월일] (string)
	 *	- field : CUSTOMER_TYPE [고객타입] (string)
	 *	- field : CELLPHONE [휴대전화] (string)
	 *	- field : EMAIL [이메일] (string)
	 *	- field : POSTAL_CODE [우편번호] (string)
	 *	- field : ADDRESS [자택주소] (string)
	 *	- field : ADDRESS_DETAIL [상세주소] (string)
	 *	- field : HOME_PHONE [자택전화] (string)
	 *	- field : COMPANY [직장명] (string)
	 *	- field : DEPARTMENT [부서명] (string)
	 *	- field : POSITION [직위] (string)
	 *	- field : COMPANY_PHONE [직장전화] (string)
	 *	- field : BLOOD_TYPE [혈액형] (string)
	 *	- field : RELIGION [종교] (string)
	 *	- field : HOBBY [취미] (string)
	 *	- field : WEDDING_ANNIVERSARY [결혼기념일] (string)
	 *	- field : INCOME_FOR_YEAR [연수입] (bigDecimal)
	 *	- field : SAVING_FOR_MONTH [월저축] (bigDecimal)
	 *	- field : CREATED_DATE [등록일] (string)
	 *	- field : MODIFIED_DATE [수정일] (string)
	 *	- field : NOTES [비고] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2018-09-09 23:32:56
	 */
	@BizMethod("고객정보 INSERT")
	public IDataSet i001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbInsert("i001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    responseData.put("EXPECTED_ROW", row);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * 고객정보 UPDATE.
	 * <pre>
	 * 고객정보 UPDATE
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ID [아이디] (string)
	 *	- field : NAME [한글명] (string)
	 *	- field : ENG_NAME [영문명] (string)
	 *	- field : BIRTHDAY [생년월일] (string)
	 *	- field : CUSTOMER_TYPE [고객타입] (string)
	 *	- field : CELLPHONE [휴대전화] (string)
	 *	- field : EMAIL [이메일] (string)
	 *	- field : POSTAL_CODE [우편번호] (string)
	 *	- field : ADDRESS [자택주소] (string)
	 *	- field : ADDRESS_DETAIL [상세주소] (string)
	 *	- field : HOME_PHONE [자택전화] (string)
	 *	- field : COMPANY [직장명] (string)
	 *	- field : DEPARTMENT [부서명] (string)
	 *	- field : POSITION [직위] (string)
	 *	- field : COMPANY_PHONE [직장전화] (string)
	 *	- field : BLOOD_TYPE [혈액형] (string)
	 *	- field : RELIGION [종교] (string)
	 *	- field : HOBBY [취미] (string)
	 *	- field : WEDDING_ANNIVERSARY [결혼기념일] (string)
	 *	- field : INCOME_FOR_YEAR [연수입] (bigDecimal)
	 *	- field : SAVING_FOR_MONTH [월저축] (bigDecimal)
	 *	- field : CREATED_DATE [등록일] (string)
	 *	- field : MODIFIED_DATE [수정일] (string)
	 *	- field : NOTES [비고] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2018-09-09 23:32:56
	 */
	@BizMethod("고객정보 UPDATE")
	public IDataSet u001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbUpdate("u001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    responseData.put("EXPECTED_ROW", row);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * 고객정보 DELETE.
	 * <pre>
	 * 고객정보 DELETE
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ID [아이디] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2018-09-09 23:32:56
	 */
	@BizMethod("고객정보 DELETE")
	public IDataSet d001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbDelete("d001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    responseData.put("EXPECTED_ROW", row);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}
  
}