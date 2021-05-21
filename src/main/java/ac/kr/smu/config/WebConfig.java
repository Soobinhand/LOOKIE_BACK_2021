package ac.kr.smu.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

public class WebConfig implements WebApplicationInitializer {
    public final static String UPLOAD_PATH = "/Users/user/IdeaProjects/sklookie/upload";
    private final long MAX_FILE_SIZE = 20971520;
    private final long MAX_REQUEST_SIZE = 41943040;
    private final int FILE_SIZE_THRESHOLD = 20971520;


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	/*
        	빈을 등록하는 xml에서의 root-context.xml 설정
        */
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class, SecurityConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        /*
       	서블렛을 설정하는 xml설정에서의 servlet-context.xml 설정
       */
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ServletConfig.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
                new DispatcherServlet(applicationContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(UPLOAD_PATH, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        dispatcher.setMultipartConfig(multipartConfigElement);


        /*        	인코딩 설정, 해주지 않으면 한글이 깨진다
         */
        FilterRegistration.Dynamic filter = servletContext.addFilter("CHARACTER_ENCODING_FILTER",
                CharacterEncodingFilter.class);
        filter.setInitParameter("encoding", "UTF-8");
        filter.setInitParameter("forceEncoding", "true");
        filter.addMappingForUrlPatterns(null, false, "/*"); // /경로로 들어오는 모든 요청에 필터를 적용

	/*
		이 필터의 이름은 반드시 springSecurityFilterChain이어야 합니다.
	*/
        FilterRegistration.Dynamic securityFilter = servletContext.addFilter("springSecurityFilterChain",
                DelegatingFilterProxy.class);
        securityFilter.addMappingForUrlPatterns(null, false, "/*");

    }
}
