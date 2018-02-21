package tag;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class JsonParser extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String var;
	private String json;

	public JsonParser() {
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setJson(String json) {
		this.json = json;
	}

        @Override
	public int doStartTag() throws JspException {
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		long tid = Thread.currentThread().getId();
		pageContext.setAttribute(String.valueOf(tid), engine);
		String expression = var + "=" + json;
		try {
			engine.eval(expression);
		} catch (Throwable t) {
			System.out.println(expression);
		}
		return 0;
	}
}