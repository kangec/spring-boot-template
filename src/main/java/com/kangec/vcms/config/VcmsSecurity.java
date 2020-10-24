package com.kangec.vcms.config;

import com.kangec.vcms.service.impl.VcmsUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class VcmsSecurity extends WebSecurityConfigurerAdapter {

    private VcmsUserDetailsServiceImpl vcmsUserDetailsService;
    private PersistentTokenRepository persistentTokenRepository;

    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1.解决跨域问题。放行CORS预检
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

        // 2.禁止Security创建HttpSession(Security会通过HttpSession获取SecurityContext)
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().cacheControl();

        // 3.uri请求权限配置
        http.authorizeRequests()
                // 1) 放行注册请求
                .antMatchers(HttpMethod.POST, "/user/register").permitAll();

        // 4.拦截账号密码
    }
    */

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 开启表单登陆
        http.formLogin().and()
                .authorizeRequests().anyRequest().authenticated();

        // 开启记住密码
        http.rememberMe()
                .tokenRepository(persistentTokenRepository)
                .tokenValiditySeconds(60)
                .userDetailsService(vcmsUserDetailsService);

        // 开启注销
        http.logout().logoutSuccessUrl("/login");

        // 关闭csrf
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(vcmsUserDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return charSequence.equals(s);
            }
        });
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/js/**", "/css/**");
    }

    @Autowired
    public void setVcmsUserDetailsService(VcmsUserDetailsServiceImpl vcmsUserDetailsServiceImpl) {
        this.vcmsUserDetailsService = vcmsUserDetailsServiceImpl;
    }

    @Autowired
    public void setPersistentTokenRepository(PersistentTokenRepository persistentTokenRepository) {
        this.persistentTokenRepository = persistentTokenRepository;
    }
}
