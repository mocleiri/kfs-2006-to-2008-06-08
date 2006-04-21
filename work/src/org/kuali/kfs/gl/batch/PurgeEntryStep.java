/*
 * Created on Apr 7, 2006
 *
 */
package org.kuali.module.gl.batch;

import java.util.Iterator;
import java.util.List;

import org.kuali.Constants;
import org.kuali.core.batch.Step;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.module.chart.service.ChartService;
import org.kuali.module.gl.service.EntryService;

public class PurgeEntryStep implements Step {
  private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PurgeEntryStep.class);

  private ChartService chartService;
  private EntryService entryService;
  private KualiConfigurationService kualiConfigurationService;

  /**
   * This step will purge data from the gl_entry_t table older than a specified year.  It purges the
   * data one chart at a time each within their own transaction so database transaction logs don't get completely
   * filled up when doing this.  This step class should NOT be transactional.
   * 
   */
  public boolean performStep() {
    LOG.debug("performStep() started");

    String yearStr = kualiConfigurationService.getApplicationParameterValue(Constants.ParameterGroups.SYSTEM,
        Constants.SystemGroupParameterNames.PURGE_GL_ENTRY_T_BEFORE_YEAR);

    int year = Integer.parseInt(yearStr);

    List charts = chartService.getAllChartCodes();
    for (Iterator iter = charts.iterator(); iter.hasNext();) {
      String chart = (String)iter.next();
      entryService.purgeYearByChart(chart,year);
    }

    return true;
  }

  public String getName() {
    return "Purge gl_entry_t";
  }

  public void setEntryService(EntryService entryService) {
    this.entryService = entryService;
  }

  public void setChartService(ChartService chartService) {
    this.chartService = chartService;
  }

  public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
    this.kualiConfigurationService = kualiConfigurationService;
  }
}
