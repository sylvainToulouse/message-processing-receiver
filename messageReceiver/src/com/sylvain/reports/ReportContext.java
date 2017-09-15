package com.sylvain.reports;

import com.sylvain.model.Sale;
import java.util.List;
import java.util.Map;


/**
 * Class to manage the report context (uses strategy pattern)
 * @author csy
 *
 */
public class ReportContext {
	private Report report;
	private Map<String,List<Sale>> map;
	
/**
 * Constructor
 * @param report
 * 		The instance of report to generate
 * @param map
 * 		The data to generate a report from
 */
	public ReportContext(final Report report, Map<String,List<Sale>> map){
		this.report = report;
		this.map = map;
	}
	
	/**
	 * Generates the report
	 */
	public void generateReport(){
		report.generateReport(map);
	}
}
