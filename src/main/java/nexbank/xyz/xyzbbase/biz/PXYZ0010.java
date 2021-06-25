package nexbank.xyz.xyzbbase.biz;

import org.apache.commons.logging.Log;

import nexbank.common.CommonArea;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.component.streotype.BizUnitBind;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.file.IExcelFileHandler;
import nexcore.framework.core.file.IFileResource;
import nexcore.framework.core.file.IUploadedFileInfo;
import nexcore.framework.core.log.ILog;
import nexcore.framework.core.util.DateUtils;

/**
 * [PU]업로드파일처리.
 * <pre>
 * 
 * </pre>
 * 
 * @author 마지막 (최초)
 * @since 마지막 (최초)
 */
@BizUnit(value="[PU]업로드파일처리", type="PU")
public class PXYZ0010 extends nexbank.fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */
	
	@BizUnitBind
	private DTB_CBS_XYZ_FILE_00 dTB_CBS_XYZ_FILE_00;

	/**
	 * Default Constructor
	 */
	public PXYZ0010(){
		super();
	}

	/**
	 * 파일저장.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : FILE_ID (string) [파일아이디]
	 *	- field : FILE_NAME (string) [파일명]
	 *	- field : STORAGE_TYPE (string) [보관유형]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : LIST_SIZE (int) [목록사이즈]
	 *	- recordSet : LIST [목록]
	 *		- field : FILE_ID (string) [파일아이디]
	 *		- field : FILE_NAME (string) [파일명]
	 *		- field : STORAGE_TYPE (string) [보관유형]
	 *		- field : CRE_DTM (string) [생성일시]
	 *		- field : UPD_DTM (string) [수정일시]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:22:52
	 */
	@BizMethod("파일저장")
	public IDataSet pXYZ00102(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    ILog log = getLog(onlineCtx);
	    
	    CommonArea ca = getCommonArea(onlineCtx);
	
	    String FILE_ID = requestData.getString("FILE_ID");
	    String FILE_NAME = requestData.getString("FILE_NAME");
	    String STORAGE_TYPE = requestData.getString("STORAGE_TYPE");
	    String DTM = DateUtils.getDateString("yyyyMMddHHmmssSSS");
	    requestData.put("CRE_DTM", DTM);
	    requestData.put("UPD_DTM", DTM);
	    
	    IFileResource fr = getFileResource(FILE_ID, onlineCtx);
	    if("DB".equals(STORAGE_TYPE)){
	    	requestData.put("FILE_CONTENT", fr);
	    }
	    
	    // 엑셀 파일 분석
	    if(FILE_NAME.toUpperCase().endsWith(".XLSX") || FILE_NAME.toUpperCase().endsWith(".XLS")){
    	    IExcelFileHandler excelHandler = getExcelFileHandler(fr, FILE_NAME.toUpperCase().endsWith(".XLSX"), onlineCtx);
    	    if(excelHandler != null){
    		    IRecordSet sheetRecordSet = excelHandler.getSheet(0);
    		    log = getLog(onlineCtx);
    		    if(log.isDebugEnabled()){
    		    	log.debug("엑셀파일 분석결과\n파일아이디:" + FILE_ID+ ", 파일명:" + FILE_NAME + "\n" + sheetRecordSet);
    		    }
    	    }
	    }
	    
	    // 파일 등록
//	    DTB_CBS_XYZ_FILE_00 dTB_CBS_XYZ_FILE_00 = (DTB_CBS_XYZ_FILE_00)lookupDataUnit(DTB_CBS_XYZ_FILE_00.class);
	    dTB_CBS_XYZ_FILE_00.i001(requestData, onlineCtx);
	    
	    if("DB".equals(STORAGE_TYPE) && fr != null){
	    	deleteFileResource(fr.getFileId(), onlineCtx);
	    }
	    
	    // 목록 조회
	    IDataSet dTB_CBS_XYZ_FILE_00_S001Res = dTB_CBS_XYZ_FILE_00.s001(requestData, onlineCtx);
	    IRecordSet LIST = dTB_CBS_XYZ_FILE_00_S001Res.getRecordSet("LIST");
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("LIST_SIZE", LIST == null ? 0 : LIST.getRecordCount());
	    responseData.put("LIST", LIST);
	    
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
	    return responseData;
	}

	/**
	 * 파일삭제.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : FILE_ID (string) [파일아이디]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : LIST_SIZE (int) [목록사이즈]
	 *	- recordSet : LIST [목록]
	 *		- field : FILE_ID (string) [파일아이디]
	 *		- field : FILE_NAME (string) [파일명]
	 *		- field : STORAGE_TYPE (string) [보관유형]
	 *		- field : CRE_DTM (string) [생성일시]
	 *		- field : UPD_DTM (string) [수정일시]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:22:52
	 */
	@BizMethod("파일삭제")
	public IDataSet pXYZ00103(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    String FILE_ID = requestData.getString("FILE_ID");
	    
	    // 물리파일 삭제
	    try {
	    	deleteFileResource(FILE_ID, onlineCtx);
	    }catch(Exception e){}
	    
//	    DTB_CBS_XYZ_FILE_00 dTB_CBS_XYZ_FILE_00 = (DTB_CBS_XYZ_FILE_00)lookupDataUnit(DTB_CBS_XYZ_FILE_00.class);
	    
	    // 삭제
	    dTB_CBS_XYZ_FILE_00.d001(requestData, onlineCtx);
	    
	    // 목록 조회
	    IDataSet dTB_CBS_XYZ_FILE_00_S001Res = dTB_CBS_XYZ_FILE_00.s001(requestData, onlineCtx);
	    IRecordSet LIST = dTB_CBS_XYZ_FILE_00_S001Res.getRecordSet("LIST");
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("LIST_SIZE", LIST == null ? 0 : LIST.getRecordCount());
	    responseData.put("LIST", LIST);
	    
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
	    return responseData;
	}

	/**
	 * 파일목록조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : LIST_SIZE (int) [목록사이즈]
	 *	- recordSet : LIST [목록]
	 *		- field : FILE_ID (string) [파일아이디]
	 *		- field : FILE_NAME (string) [파일명]
	 *		- field : STORAGE_TYPE (string) [보관유형]
	 *		- field : CRE_DTM (string) [생성일시]
	 *		- field : UPD_DTM (string) [수정일시]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:22:52
	 */
	@BizMethod("파일목록조회")
	public IDataSet pXYZ00101(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
//	    DTB_CBS_XYZ_FILE_00 dTB_CBS_XYZ_FILE_00 = (DTB_CBS_XYZ_FILE_00)lookupDataUnit(DTB_CBS_XYZ_FILE_00.class);
	    IDataSet dTB_CBS_XYZ_FILE_00_S001Res = dTB_CBS_XYZ_FILE_00.s001(requestData, onlineCtx);
	    IRecordSet LIST = dTB_CBS_XYZ_FILE_00_S001Res.getRecordSet("LIST");
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("LIST_SIZE", LIST == null ? 0 : LIST.getRecordCount());
	    responseData.put("LIST", LIST);
	    
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
	    return responseData;
	}

	/**
	 * 파일다운로드.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : FILE_ID (string) [파일아이디]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:22:52
	 */
	@BizMethod("파일다운로드")
	public IDataSet pXYZ00104(IDataSet requestData, IOnlineContext onlineCtx){
		IFileResource fr = null;
		try{
		    IDataSet responseData = new DataSet();
		    
//		    DTB_CBS_XYZ_FILE_00 dTB_CBS_XYZ_FILE_00 = (DTB_CBS_XYZ_FILE_00)lookupDataUnit(DTB_CBS_XYZ_FILE_00.class);
		    IDataSet dTB_CBS_XYZ_FILE_00_S002Res = dTB_CBS_XYZ_FILE_00.s002(requestData, onlineCtx);
		    fr = (IFileResource)dTB_CBS_XYZ_FILE_00_S002Res.get("FILE_CONTENT");
		    
		    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
		    setFileDownloadSettings(fr.getFileId(), null, true, responseData, onlineCtx);
		    
//		    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
		    return responseData;
		}catch(Exception e){
			if(fr != null){
				deleteFileResource(fr.getFileId(), onlineCtx);
			}
			throw new BizRuntimeException("XYZE0000", new String[]{"파일다운로드"}, e);
		}
	}

	/**
	 * 파일분석.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:22:53
	 */
	@BizMethod("파일분석")
	public IDataSet pXYZ00105(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    IUploadedFileInfo[] ufs = onlineCtx.getUploadedFileInfos();
	    if(ufs != null){
	    	for(IUploadedFileInfo uf : ufs){
	    		IFileResource fr = getFileResource(uf.getFileId(), onlineCtx);
	    		
	    	    // 엑셀 파일 분석
	    	    IExcelFileHandler excelHandler = getExcelFileHandler(fr, uf.getFieldName().toUpperCase().endsWith(".XLSX"), onlineCtx);
	    	    if(excelHandler != null){
	    		    IRecordSet sheetRecordSet = excelHandler.getSheet(0);
	    		    Log log = getLog(onlineCtx);
	    		    if(log.isDebugEnabled()){
	    		    	log.debug("엑셀파일 분석결과\n파일아이디:" + uf.getFileId()+ ", 파일명:" + uf.getFieldName() + "\n" + sheetRecordSet);
	    		    }
	    	    }
	    		
	    	}
	    }
	    
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
		   return responseData;
	}
  
}
