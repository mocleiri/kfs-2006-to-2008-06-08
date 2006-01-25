/*
 * Created on Apr 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.kuali.workflow.postprocessor;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;

import org.apache.log4j.Logger;
import org.kuali.Constants;
import org.kuali.KualiSpringServiceLocator;
import org.kuali.core.UserSession;
import org.kuali.core.bo.DocumentStatusChange;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.SpringServiceLocator;

import edu.iu.uis.eden.EdenConstants;
import edu.iu.uis.eden.clientapp.PostProcessorRemote;
import edu.iu.uis.eden.clientapp.vo.ActionTakenEventVO;
import edu.iu.uis.eden.clientapp.vo.DeleteEventVO;
import edu.iu.uis.eden.clientapp.vo.DocumentRouteLevelChangeVO;
import edu.iu.uis.eden.clientapp.vo.DocumentRouteStatusChangeVO;

/**
 * @author bmcgough
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class KualiPostProcessor implements PostProcessorRemote {

    private static Logger LOG = Logger.getLogger(KualiPostProcessor.class);
    

    public boolean doRouteStatusChange(DocumentRouteStatusChangeVO statusChangeEvent) throws RemoteException {
        LOG.debug("entering post processor");
        try {
            if (EdenConstants.ROUTE_HEADER_PROCESSED_CD.equals(statusChangeEvent.getNewRouteStatus()) || EdenConstants.ROUTE_HEADER_CANCEL_CD.equals(statusChangeEvent.getNewRouteStatus()) || EdenConstants.ROUTE_HEADER_DISAPPROVED_CD.equals(statusChangeEvent.getNewRouteStatus()) || EdenConstants.ROUTE_HEADER_ENROUTE_CD.equals(statusChangeEvent.getNewRouteStatus())) {
                LOG.debug("passing document " + statusChangeEvent.getRouteHeaderId() + " to DocumentService");
                DocumentStatusChange docStatChange = new DocumentStatusChange();
                docStatChange.setFinancialDocumentNumber(statusChangeEvent.getRouteHeaderId().toString());
                docStatChange.setStatusChangeEvent(statusChangeEvent.getNewRouteStatus());
                GlobalVariables.setUserSession(new UserSession(Constants.SCHEDULED_TASK_USER_ID));
                SpringServiceLocator.getDocumentService().handleDocumentRouteStatusChangeEvent(docStatChange);
                // writeOutDocumentStatusChange(statusChangeEvent);org.kuali.workflow.postprocessor.KualiPostProcessor
            }
        } catch (Exception e) {
            LOG.error("Caught Exception handing StatusChangeEvent", e);
            throw new RemoteException(e.getMessage());
        }

        return true;
    }

//    private void writeOutDocumentStatusChange(DocumentRouteStatusChangeVO statusChangeEvent) {
//        // write a record to a table with the docHeaderId and the EdenConstants.ROUTE_HEADER_PROCESSED_CD
//        // it will be picked up on the other side -- then dealt with -- and finally deleted
//        
//        Context ctx = null;
//        Connection conn = null;
//        try {
//            conn = KualiSpringServiceLocator.getDataSource().getConnection();
//            PreparedStatement insertDocumentChangeEvent = conn.prepareStatement("INSERT INTO FP_DOC_STATUS_CHANGE_QUEUE_T VALUES (?,?,?,?,?)");
//            insertDocumentChangeEvent.setLong(1, statusChangeEvent.getRouteHeaderId().longValue());
//            insertDocumentChangeEvent.setLong(2, 1);
//            insertDocumentChangeEvent.setString(3, statusChangeEvent.getNewRouteStatus());
//            insertDocumentChangeEvent.setString(4, "N");
//            insertDocumentChangeEvent.setString(5, "N");
//            insertDocumentChangeEvent.executeUpdate();
//        } catch (Exception e) {
//            LOG.error("Error getting connection",e);
//            throw new RuntimeException("An Error occurred during routing of this document",e);
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e1) {
//                    // Ignore
//                }
//                conn = null;
//            }
//        }
//    }
    
    public boolean doActionTaken(ActionTakenEventVO event) throws RemoteException {
        return true;
    }
    public boolean doDeleteRouteHeader(DeleteEventVO event) throws RemoteException {
        return true;
    }
    public boolean doRouteLevelChange(DocumentRouteLevelChangeVO levelChangeEvent) throws RemoteException {
        return true;
    }
}
