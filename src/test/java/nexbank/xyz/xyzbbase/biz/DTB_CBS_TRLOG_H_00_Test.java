package nexbank.xyz.xyzbbase.biz;

import nexcore.framework.channel.web.testcase.AbstractJacksonBasedTestCase;
import org.junit.Test;

/**
 * [DU]단위테스트를 위한 샘플SQL.
 * <pre>
 * [DU]단위테스트를 위한 샘플SQL
 * </pre>
 * @author admin(admin)
 * @since 2016-07-06 15:53:22
 */
public class DTB_CBS_TRLOG_H_00_Test extends AbstractJacksonBasedTestCase {
	public DTB_CBS_TRLOG_H_00_Test() {
//		System.setProperty("NEXCORE_TEST_HTTP_URL", "http://localhost:9080/web/json.utest"); // liberty
//		System.setProperty("NEXCORE_TEST_HTTP_URL", "http://localhost:7001/web/json.utest"); // weblogic
		System.setProperty("NEXCORE_TEST_HTTP_URL", "http://localhost:8088/web/json.utest"); // jbosseap
//		System.setProperty("NEXCORE_TEST_HTTP_URL", "http://localhost:8808/web/json.utest"); // jeus
		System.setProperty("NEXCORE_TEST_DATA_DIR", "C:\\projects\\j2ee8_demo\\workspace\\dev-xyz-online\\src\\test\\java");
		System.setProperty("NEXCORE_TEST_VERBOSE", "true");
	}

	/**
	 * Test method for.
	 * @see nexbank.xyz.xyzbbase.biz.DTB_CBS_TRLOG_H_00#s001(nexcore.framework.core.data.IDataSet, nexcore.framework.core.data.IOnlineContext)
	 * @author admin(admin)
	 * @since 2016-07-06 17:22:29
	 */
	@Test
	public void s001_T20160706172229768() throws Exception {
		org.jdom2.Element element = doServiceCall();
	}
}
