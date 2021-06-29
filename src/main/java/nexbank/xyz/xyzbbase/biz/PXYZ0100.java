package nexbank.xyz.xyzbbase.biz;

import org.apache.commons.lang3.StringUtils;

import nexbank.common.CommonArea;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.component.streotype.BizUnitBind;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.file.IExcelFileBuilder;
import nexcore.framework.core.file.IFileResource;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.PropertyUtils;


/**
 * [PU]샘플계좌관리.
 * <pre>
 * 
 * </pre>
 * 
 * @author 차지환 (jihwancha)
 * @since 2016-08-09 17:21:11
 */
@BizUnit( value="[PU]샘플계좌관리", type="PU")
public class PXYZ0100 extends nexbank.fwk.base.ProcessUnit  {

	@BizUnitBind
	private DTB_CBS_XYZ_ACC_M_00 dTB_CBS_XYZ_ACC_M_00;
	@BizUnitBind
	private DTB_CBS_XYZ_ACC_H_00 dTB_CBS_XYZ_ACC_H_00;
	@BizUnitBind
	private FXYZ0100 fXYZ0100;
	@BizUnitBind
	private DTB_CBS_XYZ_ACNG_M_00 dTB_CBS_XYZ_ACNG_M_00;
	
	/**
	 * 계좌목록조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : BR_CD [부점코드] (string)
	 *	- field : PRD_CD [상품코드] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : BR_CD [부점코드] (string)
	 *	- field : PRD_CD [상품코드] (string)
	 *	- field : ACC_LIST_SIZE [계좌목록사이즈] (int)
	 *	- field : BR_LIST_SIZE [부점목록사이즈] (int)
	 *	- field : PRD_LIST_SIZE [상품목록사이즈] (int)
	 *	- recordSet : ACC_LIST [계좌목록]
	 *		- field : ACC_NO [ACC_NO] (string)
	 *		- field : ACC_NAME [ACC_NAME] (string)
	 *		- field : ACC_BALANCE [ACC_BALANCE] (long)
	 *		- field : CRE_DTM [CRE_DTM] (string)
	 *		- field : UPD_DTM [UPD_DTM] (string)
	 *	- recordSet : BR_LIST [부점목록]
	 *		- field : BR_CD [부점코드] (string)
	 *		- field : BR_NAME [부점명] (string)
	 *	- recordSet : PRD_LIST [상품목록]
	 *		- field : PRD_CD [상품코드] (string)
	 *		- field : PRD_NAME [상품명] (string)
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:21:12
	 */
	@BizMethod("계좌목록조회")
	public IDataSet pXYZ01001(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
	    String brCd = requestData.getString("BR_CD");
	    String prdCd = requestData.getString("PRD_CD");
	    
//	    
//	    String a = PropertyUtils.getProperty("XYZ", "myprop1.properties", "aaa", true);
//	    String b = PropertyUtils.getProperty("XYZ", "myprop2.properties", "bbb", true);
//	    String c = PropertyUtils.getProperty("XYZ", "myprop3.properties", "ccc", true);
//	    String d = PropertyUtils.getProperty("XYZ", "myprop4.properties", "ddd", true);
//
//	    
	    String accNoLike = null;
	    
	    // 파라미터 체크
	    if(brCd != null && brCd.length() > 0 && prdCd != null && prdCd.length() > 0){
	    	accNoLike = brCd + prdCd + "%";
	    }
	    else if(brCd != null && brCd.length() > 0){
	    	accNoLike = brCd + "%";
	    }
	    else if(prdCd != null && prdCd.length() > 0){
	    	accNoLike = prdCd + "%";
	    }
	    else{
//	    	throw new RuntimeException("입력 파라미터가 유효하지 않습니다.");
//	    	accNoLike = "%";
	    }
	    requestData.put("ACC_NO_LIKE", accNoLike);
	    
	    // 계좌목록 조회
	    IDataSet dTB_CBS_XYZ_ACC_M_00_S001Res = dTB_CBS_XYZ_ACC_M_00.s001(requestData, onlineCtx);
	    IRecordSet accListRs = null;
	    
	    // 계좌번호가 NUL이 아닌경우 처리
	    if(accNoLike != null){ // 라인주석
	    	accListRs = dTB_CBS_XYZ_ACC_M_00_S001Res.getRecordSet("LIST");
	    }
	    
	    // 부점 목록 조회
	    IDataSet dTB_CBS_XYZ_BRCD_M_00_S001Res = callMethodByDirect("DTB_CBS_XYZ_BRCD_M_00.s001", requestData, onlineCtx);
	    IRecordSet brList = dTB_CBS_XYZ_BRCD_M_00_S001Res.getRecordSet("LIST");
	    	    
	    // 상품 목록 조회
	    IDataSet dTB_CBS_XYZ_PRDCD_M_00_S001Res = callMethodByDirect("DTB_CBS_XYZ_PRDCD_M_00.s001", requestData, onlineCtx);
	    IRecordSet prdList = dTB_CBS_XYZ_PRDCD_M_00_S001Res.getRecordSet("LIST");
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요
	    responseData.put("BR_CD", brCd);
	    
	    responseData.put("PRD_CD", prdCd);

	    responseData.put("ACC_LIST_SIZE", accListRs == null ? 0 : accListRs.getRecordCount());
	    if(accListRs != null){
	    	responseData.put("ACC_LIST", accListRs);
	    }

	    responseData.put("BR_LIST_SIZE", brList == null ? 0 : brList.getRecordCount());
	    if(brList != null) {
	    	responseData.put("BR_LIST", brList);
	    }
	    
	    responseData.put("PRD_LIST_SIZE", prdList == null ? 0 : prdList.getRecordCount());
	    if(prdList!=null) {
	    	responseData.put("PRD_LIST", prdList);
	    }
	    
	    return responseData;
	}
	
	/**
	 * 계좌상세조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [계좌번호] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [ACC_NO] (string)
	 *	- field : ACC_NAME [ACC_NAME] (string)
	 *	- field : ACC_PASSWD [ACC_PASSWD] (string)
	 *	- field : ACC_BALANCE [ACC_BALANCE] (long)
	 *	- field : CRE_DTM [CRE_DTM] (string)
	 *	- field : UPD_DTM [UPD_DTM] (string)
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:21:12
	 */
	@BizMethod("계좌상세조회")
	public IDataSet pXYZ01002(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
	    System.out.println(onlineCtx.getUserInfo().getClass().getName());
	
	    // 계좌원장 DU
//	    DTB_CBS_XYZ_ACC_M_00 dTB_CBS_XYZ_ACC_M_00 = (DTB_CBS_XYZ_ACC_M_00)lookupDataUnit(DTB_CBS_XYZ_ACC_M_00.class);
	    
	    // 계좌상세 조회
	    IDataSet dTB_CBS_XYZ_ACC_M_00_S001Res = dTB_CBS_XYZ_ACC_M_00.s002(requestData, onlineCtx);

	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.putAll(dTB_CBS_XYZ_ACC_M_00_S001Res);
	
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
	    return responseData;
	}
	
	/**
	 * 계좌등록.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [계좌번호] (string)
	 *	- field : ACC_NAME [계좌명] (string)
	 *	- field : ACC_PASSWD [계좌비밀번호] (string)
	 *	- field : ACC_BALANCE [잔액] (long)
	 *	- recordSet : 고객목록 [recordSet1]
	 *		- field : field1 [field1] (string)
	 *		- field : field2 [field2] (string)
	 *		- recordSet : 예금목록 [recordSet2]
	 *			- field : FA1 [field1] (string)
	 *			- field : FA2 [field2] (string)
	 *		- field : field3 [field3] (string)
	 *		- field : field4 [field4] (int)
	 *		- recordSet : 대출목록 [recordSet1]
	 *			- field : FB1 [field1] (string)
	 *			- field : FB2 [field2] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [계좌번호] (string)
	 *	- field : ACC_NAME [계좌명] (string)
	 *	- field : ACC_PASSWD [계좌비밀번호] (string)
	 *	- field : ACC_BALANCE [잔액] (long)
	 *	- field : CRE_DTM [생성일시] (string)
	 *	- field : UPD_DTM [수정일시] (string)
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:21:12
	 */
	@BizMethod("계좌등록")
	public IDataSet pXYZ01003(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    // 계좌 FU
//	    FXYZ0100 fXYZ0100 = (FXYZ0100)lookupFunctionUnit(FXYZ0100.class);
	    
	    // 계좌번호 존재여부 확인
	    IDataSet fXYZ01ExistAccRes = fXYZ0100.checkAccNo(requestData, onlineCtx);
	    
	    // 계좌가 존재하는 경우는 에러 처리.
	    if("Y".equals(fXYZ01ExistAccRes.getString("EXIST_ACC_YN"))){
	    	//EXYZ0001
	    	throw new BizRuntimeException("XYZE0001");
	    }
	    
	    // 계좌원장 DU
//	    DTB_CBS_XYZ_ACC_M_00 dTB_CBS_XYZ_ACC_M_00 = (DTB_CBS_XYZ_ACC_M_00)lookupDataUnit(DTB_CBS_XYZ_ACC_M_00.class);
	    
	    // 계좌등록
	    IDataSet dTB_CBS_XYZ_ACC_M_00_I001Res = dTB_CBS_XYZ_ACC_M_00.i001(requestData, onlineCtx);
//	    IDataSet dTB_CBS_XYZ_ACC_M_00_I001Res = dbioInsert("TB_CBS_XYZ_ACC_M", requestData, onlineCtx);
	    int EXPECTED_ROW = dTB_CBS_XYZ_ACC_M_00_I001Res.getInt("EXPECTED_ROW");
	    if(EXPECTED_ROW < 1){
	    	// 계좌등록을 실패하였습니다.
	    	throw new BizRuntimeException("XYZE0002");
	    }

	    // 계좌상세 조회
	    IDataSet dTB_CBS_XYZ_ACC_M_00_S001Res = dTB_CBS_XYZ_ACC_M_00.s002(requestData, onlineCtx);

	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.putAll(dTB_CBS_XYZ_ACC_M_00_S001Res);
	
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
	    return responseData;
	}

	/**
	 * 계좌거래이력조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [계좌번호] (string)
	 *	- field : PAGE_NO [페이지번호] (int)
	 *	- field : ROW_IN_PAGE [페이지당표시건수] (int)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [계좌번호] (string)
	 *	- field : HAS_NEXT_PAGE [다음페이지존재] (string)
	 *	- field : LIST_SIZE [이력건수] (int)
	 *	- recordSet : LIST [이력목록]
	 *		- field : ACC_NO [ACC_NO] (string)
	 *		- field : CRE_DTM [CRE_DTM] (string)
	 *		- field : KIND [KIND] (string)
	 *		- field : AMOUNT [AMOUNT] (long)
	 *		- field : DESCRIPTION [DESCRIPTION] (string)
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:21:12
	 */
	@BizMethod("계좌거래이력조회")
	public IDataSet pXYZ01004(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
//	    System.out.println(onlineCtx.getUserInfo().getClass().getName() + ":" + onlineCtx.getUserInfo());

	    CommonArea ca = getCommonArea(onlineCtx);
	    
	    requestData.put("LOCALE_XD", ca.getUserLocale());
	
	    IDataSet dTB_CBS_XYZ_ACC_H_00Res = dTB_CBS_XYZ_ACC_H_00.s001(requestData, onlineCtx);
	    IRecordSet histList = dTB_CBS_XYZ_ACC_H_00Res.getRecordSet("LIST");
	    
//	    if(true) {
//	    	throw new RuntimeException("aaaaaaaaaaa");
//	    }
	    
	    for (IRecord record : histList) {
	    	String desc = record.getString("DESCRIPTION");
	    	
    		record.set("DESCRIPTION", desc+" --");
	    }
	    
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요
	    responseData.put("ACC_NO", requestData.getString("ACC_NO"));
	    responseData.put("HAS_NEXT_PAGE", histList.hasNextPage() ? "Y" : "N");
	    responseData.put("LIST_SIZE", histList == null ? 0 : histList.getRecordCount());
	    if(histList != null){
	    	responseData.put("LIST", histList);
	    }
	
	    return responseData;
	}

	/**
	 * 부점별수신잔고조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : LIST_SIZE [목록사이즈] (int)
	 *	- recordSet : LIST [목록]
	 *		- field : BR_CD [부점코드] (string)
	 *		- field : BR_NAME [부점명] (string)
	 *		- field : PRD_CD [상품코드] (string)
	 *		- field : PRD_NAME [상품명] (string)
	 *		- field : BALANCE [잔고] (long)
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:21:12
	 */
	@BizMethod("부점별수신잔고조회")
	public IDataSet pXYZ01005(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
//	    DTB_CBS_XYZ_ACNG_M_00 dTB_CBS_XYZ_ACNG_M_00 = (DTB_CBS_XYZ_ACNG_M_00)lookupDataUnit(DTB_CBS_XYZ_ACNG_M_00.class);
	    IDataSet dTB_CBS_XYZ_ACNG_M_00_S001Res = dTB_CBS_XYZ_ACNG_M_00.s001(requestData, onlineCtx);
	    IRecordSet list = dTB_CBS_XYZ_ACNG_M_00_S001Res.getRecordSet("LIST");
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("LIST_SIZE", list == null ? 0 : list.getRecordCount());
	    responseData.put("LIST", list);
	
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
	    return responseData;
	}

	/**
	 * 계좌목록조회(WAITING).
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : BR_CD [부점코드] (string)
	 *	- field : PRD_CD [상품코드] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : BR_CD [부점코드] (string)
	 *	- field : PRD_CD [상품코드] (string)
	 *	- field : ACC_LIST_SIZE [계좌목록사이즈] (int)
	 *	- field : BR_LIST_SIZE [부점목록사이즈] (int)
	 *	- field : PRD_LIST_SIZE [상품목록사이즈] (int)
	 *	- recordSet : ACC_LIST [계좌목록]
	 *		- field : ACC_NO [ACC_NO] (string)
	 *		- field : ACC_NAME [ACC_NAME] (string)
	 *		- field : ACC_BALANCE [ACC_BALANCE] (long)
	 *		- field : CRE_DTM [CRE_DTM] (string)
	 *		- field : UPD_DTM [UPD_DTM] (string)
	 *	- recordSet : BR_LIST [부점목록]
	 *		- field : BR_CD [부점코드] (string)
	 *		- field : BR_NAME [부점명] (string)
	 *		- recordSet : MEM_LIST [recordSet1]
	 *			- field : NAME [부원 이름] (string)
	 *			- field : LEVEL [직위] (string)
	 *	- recordSet : PRD_LIST [상품목록]
	 *		- field : PRD_CD [상품코드] (string)
	 *		- field : PRD_NAME [상품명] (string)
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:21:12
	 */
	@BizMethod("계좌목록조회(WAITING)")
	public IDataSet pXYZ01006(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
//	    CommonArea ca = getCommonArea(onlineCtx);
	    
//	    Log log = getLog(onlineCtx);
//	    log.info(ca.getUserLocale() +"=" + BaseUtils.getMessage("NCOM0000", BaseUtils.asLocale(ca.getUserLocale())));
//	    log.info("default=" + BaseUtils.getMessage("NCOM0000"));
	    
	    String brCd = requestData.getString("BR_CD");
	    String prdCd = requestData.getString("PRD_CD");
	    
	    if(brCd == null || brCd.length() < 1){
	    	brCd = "100";
	    }
	    
	    String accNoLike = null;
	    if(brCd != null && brCd.length() > 0 && prdCd != null && prdCd.length() > 0){
	    	accNoLike = brCd + prdCd + "%";
	    }
	    else if(brCd != null && brCd.length() > 0){
	    	accNoLike = brCd + "%";
	    }
	    else if(prdCd != null && prdCd.length() > 0){
	    	accNoLike = prdCd + "%";
	    }
	    else{
	    	accNoLike = "%";
	    }
	    requestData.put("ACC_NO_LIKE", accNoLike);
	    
	    
	    // 계좌원장 DU
//	    DTB_CBS_XYZ_ACC_M_00 dTB_CBS_XYZ_ACC_M_00 = (DTB_CBS_XYZ_ACC_M_00)lookupDataUnit(DTB_CBS_XYZ_ACC_M_00.class);
	    
	    // 계좌목록 조회
	    IDataSet dTB_CBS_XYZ_ACC_M_00_S001Res = dTB_CBS_XYZ_ACC_M_00.s001(requestData, onlineCtx);
	    IRecordSet accListRs = null;
	    if(accNoLike != null){
	    	accListRs = dTB_CBS_XYZ_ACC_M_00_S001Res.getRecordSet("LIST");
	    }
	    
	    // 부점 목록 조회
	    IDataSet dTB_CBS_XYZ_BRCD_M_00_S001Res = callMethodByDirect("DTB_CBS_XYZ_BRCD_M_00.s001", requestData, onlineCtx);
	    IRecordSet brList = dTB_CBS_XYZ_BRCD_M_00_S001Res.getRecordSet("LIST");
	    
	    // 강제로 대기하도록 함.
	    for(int i=0; i<100; i++){
	    	callMethodByDirect("FXYZ0901.xyzPre", requestData, onlineCtx);
		    try{
		    	Thread.sleep(1000);
		    }catch(Exception e){}
	    }
	    
	    // 상품 목록 조회
	    IDataSet dTB_CBS_XYZ_PRDCD_M_00_S001Res = callMethodByDirect("DTB_CBS_XYZ_PRDCD_M_00.s001", requestData, onlineCtx);
	    IRecordSet prdList = dTB_CBS_XYZ_PRDCD_M_00_S001Res.getRecordSet("LIST");
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요
	    responseData.put("BR_CD", brCd);
	    responseData.put("PRD_CD", prdCd);

	    responseData.put("ACC_LIST_SIZE", accListRs == null ? 0 : accListRs.getRecordCount());
	    if(accListRs != null){
	    	responseData.put("ACC_LIST", accListRs);
	    }

	    responseData.put("BR_LIST_SIZE", brList.getRecordCount());
	    responseData.put("BR_LIST", brList);

	    responseData.put("PRD_LIST_SIZE", prdList.getRecordCount());
	    responseData.put("PRD_LIST", prdList);
	    
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
	    return responseData;
	}

	/**
	 * 계좌거래이력 엑셀파일다운로드.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [계좌번호] (string)
	 *	- field : EXCEL_TYPE [엑셀버전유형] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:21:12
	 */
	@BizMethod("계좌거래이력 엑셀파일다운로드")
	public IDataSet pXYZ01007(IDataSet requestData, IOnlineContext onlineCtx){
		IFileResource fr = null;
		IExcelFileBuilder builder = null;
		try{
		    IDataSet responseData = new DataSet();
		    String EXCEL_TYPE = requestData.getString("EXCEL_TYPE");
		    
		    // 업무그룹을 입력하여 다운로드 가능한 임시파일을 생성한다.
		    fr = createTempFile("XYZ", onlineCtx);
		    
		    // 엑셀파일 기록기 선택 (XLSX or XLS)
		    builder = getExcelFileBuilder(fr, "xlsx".equalsIgnoreCase(EXCEL_TYPE), onlineCtx);
	
		    // 페이징을 이용한 데이터 조회
//		    DTB_CBS_XYZ_ACC_H_00 dTB_CBS_XYZ_ACC_H_00 = (DTB_CBS_XYZ_ACC_H_00)lookupDataUnit(DTB_CBS_XYZ_ACC_H_00.class);
		    requestData.put("ROW_IN_PAGE", 999);
		    requestData.put("PAGE_NO", 1);
		    
		    // 페이징 조회 처리
		    while(true){
			    IDataSet dTB_CBS_XYZ_ACC_H_00Res = dTB_CBS_XYZ_ACC_H_00.s001(requestData, onlineCtx);
			    IRecordSet histList = dTB_CBS_XYZ_ACC_H_00Res.getRecordSet("LIST");
			    requestData.put("PAGE_NO", histList.getPageNo() + 1);
			    
			    // 조회된 레코드셋을 시트에 기록
			    builder.createSheet("이력목록", histList);
			    
			    if(!histList.hasNextPage()){
			    	break;
			    }
		    }
		    
		    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
		    setFileDownloadSettings(fr.getFileId(), null, true, responseData, onlineCtx);
		    
//		    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
		    return responseData;
		}catch(Exception e){
			if(fr != null){
				deleteFileResource(fr.getFileId(), onlineCtx);
			}
			throw new BizRuntimeException("XYZE0000", new String[]{"거래이력 엑셀다운로드"}, e);
		}
		finally{
			if(builder != null){
				builder.close();
			}
		}
	}

	/**
	 * 계좌거래이력 CSV파일다운로드.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [계좌번호] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:21:12
	 */
	@BizMethod("계좌거래이력 CSV파일다운로드")
	public IDataSet pXYZ01008(IDataSet requestData, IOnlineContext onlineCtx){
		try{
		    IDataSet responseData = new DataSet();

//		    DTB_CBS_XYZ_ACC_H_00 dTB_CBS_XYZ_ACC_H_00 = (DTB_CBS_XYZ_ACC_H_00)lookupDataUnit(DTB_CBS_XYZ_ACC_H_00.class);
		    IDataSet dS002Res = dTB_CBS_XYZ_ACC_H_00.s002(requestData, onlineCtx);
		    String fileId = dS002Res.getString("fileId");
	
		    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
		    setFileDownloadSettings(fileId, null, true, responseData, onlineCtx);
		    
//		    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
		    return responseData;
		}catch(Exception e){
			throw new BizRuntimeException("XYZE0000", new String[]{"거래이력 엑셀다운로드"}, e);
		}
	}

}
