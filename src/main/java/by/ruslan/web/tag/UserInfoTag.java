package by.ruslan.web.tag;

import by.ruslan.web.command.SessionAttribute;
import by.ruslan.web.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * The {@code UserInfoTag} class represents custom tag
 *
 * @author Ruslan Nedvedskiy
 * @see TagSupport
 */
public class UserInfoTag extends TagSupport {

    /*<h3>${user.username}</h3>
      <p class="label">${user.email}</p>
      <p class="label">${user.role}</p>
     */

    static Logger logger = LogManager.getLogger();
    private static final String USER_INFO_TAG_START = "<user-info>";
    private static final String USER_INFO_TAG_END = "</user-info>";
    private static final String CLASS_STYLE_LABEL = "\"label\"";
    private static final String H3_TAG_START = "<h3>";
    private static final String H3_TAG_END = "</h3>";

    @Override
    public int doStartTag() throws JspTagException {

        try {
            User user = (User) pageContext.getSession().getAttribute(SessionAttribute.USER);
            JspWriter out = pageContext.getOut();
            out.write(USER_INFO_TAG_START);
            out.write(H3_TAG_START + user.getUsername() + H3_TAG_END);
            out.write("<p class=" + CLASS_STYLE_LABEL + ">" + user.getEmail() + "</p>");
            out.write("<p class=" + CLASS_STYLE_LABEL + ">" + user.getRole() + "</p>");

        } catch (IOException e) {
            logger.error(e);
            throw new JspTagException(e);
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
            out.write(USER_INFO_TAG_END);
        } catch (IOException e) {
            logger.error(e);
            throw new JspTagException(e);
        }
        return EVAL_PAGE;
    }
}
