package tag;

import javax.script.ScriptEngine;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class JsonEval
        extends TagSupport {

    private String var;
    private String selection;

    public JsonEval() {
    }

    public void setVar(String var) {
        this.var = var;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String tid = String.valueOf(Thread.currentThread().getId());
            ScriptEngine engine = (ScriptEngine) pageContext.getAttribute(tid);
            Object o = engine.eval(selection);
            if (o != null) {
                pageContext.setAttribute(var, o.toString());
            }
        } catch (Throwable t) {
        }
        return 0;
    }
}
