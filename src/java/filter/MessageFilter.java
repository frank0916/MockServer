package filter;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class MessageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
            InputStream is = request.getInputStream();
            StringBuilder payload = new StringBuilder();
            int c;
            while ((c = is.read()) >= 0) {
                payload.append((char) c);
            }
            request.setAttribute("payload", payload.toString());
            CharResponseWrapper wrapper = new CharResponseWrapper(
                    (HttpServletResponse) response);

            chain.doFilter(request, wrapper);
            String output = wrapper.toString();
            response.getWriter().write(output.trim());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Message Filter";
    }
}
