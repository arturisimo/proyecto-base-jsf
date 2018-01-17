package org.apz.xxx.web.view.util;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("requestView")
@Scope("request")
public class RequestView {

	private int nextTabindex;
	private HttpServletRequest request;
	private String contextPath;
	private String controlNavegador;
	private Boolean muestra2puntos;
	private Severity maximunSeverity;

	public String getCargaEstiloNavegador() {
		return String.format("%s/css/estilo%s.css", this.getContextPath(), this.getControlNavegador());
	}

	public String getContextPath() {
		if (this.contextPath == null) {
			this.contextPath = this.getRequest().getContextPath();
		}

		return this.contextPath;
	}

	public String getControlNavegador() {
		if (this.controlNavegador == null) {
			String ua = this.getRequest().getHeader("User-Agent");
			boolean isMSIE7 = (ua != null && ua.indexOf("MSIE 7.0") != -1);
			this.controlNavegador = (isMSIE7) ? "IE7" : "NoIE";
		}

		return this.controlNavegador;
	}

	public HttpServletRequest getRequest() {
		if (this.request == null) {
			final FacesContext facesContext = FacesContext.getCurrentInstance();
			this.request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		}

		return this.request;
	}

	public String getNextTabindex() {
		return String.format("%d", Integer.valueOf(++this.nextTabindex));
	}

	public String getDetailFormat() {
		if (this.muestra2puntos == null) {
			final List<FacesMessage> lsMsg = FacesContext.getCurrentInstance().getMessageList();
			final FacesMessage firstMsg = lsMsg.get(0);
			this.muestra2puntos = Boolean.valueOf(firstMsg.getDetail().equals(firstMsg.getSummary())
					|| (firstMsg.getSummary() == null || firstMsg.getSummary().equals("")));
		}

		if (this.maximunSeverity == null) {
			this.maximunSeverity = FacesContext.getCurrentInstance().getMaximumSeverity();
		}
		if (FacesMessage.SEVERITY_ERROR.equals(this.maximunSeverity) && this.muestra2puntos.booleanValue()) {
			return "{1}: {0}";
		}

		return "{0}";
	}
}