package com.jetyun.pgcd.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.jetyun.pgcd.system.alert.AlertMessage;
import com.jetyun.pgcd.system.web.SessionState;

/**
 * 提醒框标签
 * 
 * @author Administrator
 */
public class AlertMsgTag extends SimpleTagSupport {
	private AlertMessage msg = null;
	private String name = null;
	
	public void setName(String name){
		this.name = name;
	}

	public void doTag() throws JspException, IOException {
		SessionState st = new SessionState();
		msg = st.get(name);
		st.clear(name);
		
		if(this.msg ==null || this.msg.getType()==null) return ;
		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"alert ");
		switch (msg.getType()) {
		case SUCCESS:
			sb.append("alert-success");
			break;
		case INFO:
			sb.append("alert-info");
			break;
		case WARNING:
			sb.append("alert-warning");
			break;
		case DANGER:
			sb.append("alert-danger");
			break;
		}
		sb.append(" fade in\">");
		sb.append("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>");
		sb.append(msg.getContent());
		sb.append("</div>");
		
		JspWriter out = getJspContext().getOut();
		out.println(sb.toString());
	}

}
