package com.softcaribbean.veterinariaXYZ.security.filter;

import jakarta.servlet.GenericFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/*@Component
public class AwsCognitoJwtAuthFilter extends GenericFilter {

    private static final Log logger = LogFactory.getLog(AwsCognitoJwtAuthFilter.class);
    private AwsCognitoIdTokenProcessor cognitoIdTokenProcessor;

    public AwsCognitoJwtAuthFilter(AwsCognitoIdTokenProcessor cognitoIdTokenProcessor) {
        this.cognitoIdTokenProcessor = cognitoIdTokenProcessor;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        Authentication authentication;
        try {
            authentication = this.cognitoIdTokenProcessor.authenticate((HttpServletRequest) request);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (ParseException | BadJOSEException | JOSEException e) {
            logger.error("Cognito ID Token processing error", e);
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
*/