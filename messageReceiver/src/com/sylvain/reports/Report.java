package com.sylvain.reports;

import com.sylvain.model.Sale;
import java.util.List;
import java.util.Map;

/**
 * Sale reports interface
 * @author csy
 *
 */
public interface Report {
	public void generateReport(Map<String,List<Sale>> map);
}
