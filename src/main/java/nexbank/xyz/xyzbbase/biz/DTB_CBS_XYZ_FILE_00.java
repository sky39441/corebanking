package nexbank.xyz.xyzbbase.biz;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;


/**
 * [DU]파일관리.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin (admin)
 * @since 2016-08-09 17:28:10
 */
@BizUnit(value="[DU]파일관리", type="DU")
public class DTB_CBS_XYZ_FILE_00 extends nexbank.fwk.base.DataUnit  {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DTB_CBS_XYZ_FILE_00(){
		super();
	}

	/**
	 * 목록조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:10
	 */
	@BizMethod("목록조회")
	public IDataSet s001(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    IRecordSet LIST = dbSelect("S001", requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("LIST", LIST);
	    return responseData;
	}

	/**
	 * 등록.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : FILE_ID () [파일아이디]
	 *	- field : FILE_NAME () [파일명]
	 *	- field : STORAGE_TYPE () [보관유형]
	 *	- field : FILE_CONTENT () [파일내용]
	 *	- field : CRE_DTM () [생성일시]
	 *	- field : UPD_DTM () [수정일시]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:10
	 */
	@BizMethod("등록")
	public IDataSet i001(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    int row = dbInsert("I001", requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("EXPECTED_ROW", row);
	    return responseData;
	}

	/**
	 * 파일내용조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : FILE_ID () [파일아이디]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : FILE_CONTENT () [파일내용]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:10
	 */
	@BizMethod("파일내용조회")
	public IDataSet s002(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    Object FILE_CONTENT = dbSelectObject("S002", requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("FILE_CONTENT", FILE_CONTENT);
	    return responseData;
	}

	/**
	 * 삭제.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : FILE_ID () [파일아이디]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:10
	 */
	@BizMethod("삭제")
	public IDataSet d001(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
		
	    int row = dbDelete("D001", requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("EXPECTED_ROW", row);
	    return responseData;
	}
  
}
