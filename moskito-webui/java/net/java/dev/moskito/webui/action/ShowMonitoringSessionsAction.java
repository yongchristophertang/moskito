package net.java.dev.moskito.webui.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.util.NumberUtils;
import net.java.dev.moskito.core.usecase.session.IMonitoringSessionManager;
import net.java.dev.moskito.core.usecase.session.MonitoringSession;
import net.java.dev.moskito.core.usecase.session.MonitoringSessionManagerFactory;
import net.java.dev.moskito.webui.bean.MonitoringSessionListItemBean;

public class ShowMonitoringSessionsAction extends BaseMoskitoUIAction{
	
	private IMonitoringSessionManager sessionManager;
	
	public ShowMonitoringSessionsAction(){
		sessionManager = MonitoringSessionManagerFactory.getMonitoringSessionManager();
	}

	@Override
	protected String getLinkToCurrentPage(HttpServletRequest req) {
		return "";
	}

	@Override
	public ActionForward execute(ActionMapping mapping, HttpServletRequest req, HttpServletResponse res) throws Exception {

		
		List<MonitoringSession> sessions = sessionManager.getSessions();
		List<MonitoringSessionListItemBean> beans = new ArrayList<MonitoringSessionListItemBean>(sessions.size());
		
		for (MonitoringSession s : sessions){
			MonitoringSessionListItemBean bean = new MonitoringSessionListItemBean();
			
			bean.setName(s.getName());
			bean.setActive(s.isActive());
			bean.setCreated(NumberUtils.makeISO8601TimestampString(s.getCreatedTimestamp()));
			bean.setLastActivity(NumberUtils.makeISO8601TimestampString(s.getLastActivityTimestamp()));
			bean.setNumberOfCalls(s.getUseCases().size());
			beans.add(bean);
		}

		req.setAttribute("monitoringSessions", beans);
		if (beans.size()>0)
			req.setAttribute("monitoringSessionsPresent", Boolean.TRUE);
		return mapping.findForward("success");
	}
	
	
}