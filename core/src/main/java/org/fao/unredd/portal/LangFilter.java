package org.fao.unredd.portal;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LangFilter implements Filter {
	private Config config;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config = (Config) filterConfig.getServletContext().getAttribute(
				"config");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String lang = request.getParameter("lang");
		if (lang == null) {
			lang = config.getDefaultLang();
		}

		Locale locale;
		if (lang != null && lang.trim().length() > 0) {
			locale = new Locale(lang);
		} else {
			locale = request.getLocale();
		}
		request.setAttribute("locale", locale);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
