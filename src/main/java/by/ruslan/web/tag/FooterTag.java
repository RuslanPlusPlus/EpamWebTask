package by.ruslan.web.tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class FooterTag extends TagSupport {

    static Logger logger = LogManager.getLogger();
    private static final String TEXT_CONTENT = "\"Sport bet\" by Ruslan Nedvedski 2021 ";
    private static final String COPYRIGHT_MARK = "&copy;";
    private static final String FOOTER_TAG_START = "<footer>";
    private static final String P_TAG_START = "<p>";
    private static final String FOOTER_TAG_END = "</footer>";
    private static final String P_TAG_END = "</p>";

    @Override
    public int doStartTag() throws JspTagException{
        try {
            JspWriter out = pageContext.getOut();
            out.write(FOOTER_TAG_START);
            out.write(P_TAG_START);
            out.write(COPYRIGHT_MARK + " " + TEXT_CONTENT);
            out.write(P_TAG_END);

        } catch (IOException e) {
            logger.error(e);
            throw new JspTagException(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
            out.write(FOOTER_TAG_END);
        } catch (IOException e) {
            logger.error(e);
            throw new JspTagException(e);
        }
        return EVAL_PAGE;
    }
}
