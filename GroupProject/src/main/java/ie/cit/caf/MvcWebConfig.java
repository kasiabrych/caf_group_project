package ie.cit.caf;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan
public class MvcWebConfig extends WebMvcConfigurerAdapter {    
      
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
    }
     
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("language");
    return localeChangeInterceptor;
    }
     
    @Bean(name = "localeResolver")
    public CookieLocaleResolver localeResolver() {
    CookieLocaleResolver localeResolver = new CookieLocaleResolver();
    Locale defaultLocale = new Locale("en");
    localeResolver.setDefaultLocale(defaultLocale);
    return localeResolver;
    }
     
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:lang/messages");
    messageSource.setCacheSeconds(10); //reload messages every 10 seconds
    return messageSource;
    }

}