package nexbank.xyz.xyzbbase.biz;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.integration.ibatis.XssfExcelWriteRowHandler;


/**
 * [DU]샘플계좌이력.
 * <pre>
 * 
 * </pre>
 * 
 * @author 차지환 (jihwancha)
 * @since 2016-08-09 17:28:24
 */
@BizUnit(value="[DU]샘플계좌이력", type="DU")
public class DTB_CBS_XYZ_ACC_H_00 extends nexbank.fwk.base.DataUnit  {

	/**
	 * 계좌이력조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [ACC_NO]
	 *	- field : PAGE_NO [페이지 번호]
	 *	- field : ROW_IN_PAGE [페이지당 건수]
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- recordSet : LIST [목록]
	 *		- field : ACC_NO [계좌번호] (string)
	 *		- field : CRE_DTM [생성일시] (string)
	 *		- field : KIND [처리구분] (string)
	 *		- field : AMOUNT [금액] (long)
	 *		- field : DESCRIPTION [설명] (string)
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:24
	 */
	@BizMethod("계좌이력조회")
	public IDataSet s001(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    // 페이지 번호
	    int pageNo = requestData.getInt("PAGE_NO");
	    
	    // 페이지당 표시건수
	    int rowInPage = requestData.getInt("ROW_IN_PAGE");
	    
	    // 조회 쿼리 실행.
	    IRecordSet rs = dbSelect("S001", 
	    						 requestData, 
	    						 pageNo, 
	    						 rowInPage, 
	    						 onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
    	responseData.put("LIST", rs);
	    return responseData;
	}
	
   /**
	 * 계좌이력등록.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [ACC_NO]
	 *	- field : KIND [KIND]
	 *	- field : AMOUNT [AMOUNT]
	 *	- field : DESCRIPTION [DESCRIPTION]
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [반영건수]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:24
	 */
	@BizMethod("계좌이력등록")
	public IDataSet i001(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
	    // 등록 쿼리 수행
	    String yyyyMMddHHmmssSSS = DateUtils.getDateString("yyyyMMddHHmmssSSS");
	    requestData.put("CRE_DTM", yyyyMMddHHmmssSSS);
	    requestData.put("UPD_DTM", yyyyMMddHHmmssSSS);
	    int row = dbInsert("I001", requestData, onlineCtx);
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("EXPECTED_ROW", row);
	
	    return responseData;
	}

	/**
	 * 계좌이력전체파일저장.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [계좌번호]
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:24
	 */
	@BizMethod("계좌이력전체파일저장")
	public IDataSet s002(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    ExcelFileOption excelFileOption = new ExcelFileOption();
	    
	    // 조회 쿼리 실행.
	    String fileId = dbSelectToFile("S001", requestData, excelFileOption, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("fileId", fileId);
	    return responseData;
	}

	/**
	 * 계좌이력대량등록.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:24
	 */
	@BizMethod("계좌이력대량등록")
	public IDataSet i002(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    final IDataSet dataSet = requestData;
	    String yyyyMMddHHmmssSSS = DateUtils.getDateString("yyyyMMddHHmmssSSS");
	    dataSet.put("CRE_DTM", yyyyMMddHHmmssSSS);
	    dataSet.put("UPD_DTM", yyyyMMddHHmmssSSS);
	    
	    final int max = 10000;
	    
	    final IRecordSet recordSet = requestData.getRecordSet("LIST");
	    
	    // SQL 배치 모드 실행
	    long totalCount = dbExecuteBatch(new DbBatchModeExecutor(1000) {
			
			@Override
			protected void execute() {
				
				for(int i=0; i<recordSet.getRecordCount(); i++){
					addBatch("I001", recordSet.getRecord(i));
				}
				
			}
	    	
	    }, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("totalCount", totalCount);
	    return responseData;
	}

	/**
	 * 계좌이력등록.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [ACC_NO]
	 *	- field : KIND [KIND]
	 *	- field : AMOUNT [AMOUNT]
	 *	- field : DESCRIPTION [DESCRIPTION]
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [반영건수]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:24
	 */
	@BizMethod("계좌이력등록 NOTX")
	public IDataSet i091(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
	    // 등록 쿼리 수행
	    String yyyyMMddHHmmssSSS = DateUtils.getDateString("yyyyMMddHHmmssSSS");
	    requestData.put("CRE_DTM", yyyyMMddHHmmssSSS);
	    requestData.put("UPD_DTM", yyyyMMddHHmmssSSS);
	    
	    int row = dbInsert("I001", requestData, "NOTX", onlineCtx);
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("EXPECTED_ROW", row);
	
	    return responseData;
	}

	/* 
    * 이 클래스는 Singleton 객체로 수행됩니다. 
    * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
    */

}
