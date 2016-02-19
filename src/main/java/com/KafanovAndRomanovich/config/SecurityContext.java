package com.KafanovAndRomanovich.config;

import com.KafanovAndRomanovich.security.service.RepositoryUserDetailsService;
import com.KafanovAndRomanovich.security.service.SimpleSocialUserDetailsService;
import com.KafanovAndRomanovich.user.controller.ProfileController;
import com.KafanovAndRomanovich.user.repository.*;
import com.KafanovAndRomanovich.user.search.PostSearchImpl;
import com.KafanovAndRomanovich.user.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;


@Configuration
@ComponentScan(basePackages = {"com.KafanovAndRomanovich.user.controller"})
@EnableWebSecurity
public class SecurityContext extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;
    private static PasswordEncoder encoder;



    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private LikesRepository likesRepository;

    @Bean
    public RepositoryLikesService repositoryLikesService(){
        return new RepositoryLikesService(likesRepository);
    }
    @Bean
    public RepositoryRatingService repositoryRatingService(){return new RepositoryRatingService(ratingRepository);}
    @Bean
    public PostSearchImpl postSearchImpl(){return new PostSearchImpl();}
    @Bean
    public RepositoryPostService repositoryPostService() {
        return new RepositoryPostService(postRepository);
    }
    @Bean
    public TagService repositoryTagService() {
        return new RepositoryTagService(postRepository,tagRepository);
    }
    @Bean CommentService repositoryCommentService(){return new RepositoryCommentService(postRepository,commentRepository);}
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                //Spring Security ignores request to static resources such as CSS or JS files.
                .ignoring()
                    .antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //Configures form login
                .csrf().disable()

                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login/authenticate")
                    .failureUrl("/login?error=bad_credentials")
                //Configures the logout function
                .and()
                    .logout()
                        .deleteCookies("JSESSIONID")
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                //Configures url based authorization
                .and()
                    .authorizeRequests()
                        //Anyone can access the urls
                        .antMatchers(
                                "/auth/**",
                                "/login",
                                "/signup/**",
                                "/user/register/**",
                                "/user/signIn/**",
                                "/user/profilePage/**",
                                "/user/singlePost/**",
                                "user/templates/**",
                                "/getprofile",
                                "/saveprofile",
                                "/savepost",
                                "/addpost",
                                "/getposts",
                                "/deletepost",
                                "/get-tags-list/**",
                                "/get-tags-/**",
                                "/saveavatar",
                                "/saveimage",
                                "/getSinglePost",
                                "/getUserName",
                                "/saveComment",
                                "/getComments",
                                "/getAllPosts",
                                "/getCategoryPosts",
                                "/search",
                                "/getRating",
                                "/getLikes",
                                "/changeLikes",
                                "/getPopularPosts",
                                "/getCloudTags",
                                "/getPersonalRating"



                        ).permitAll()
                        //The rest of the our application is protected.

                        .antMatchers("/**").hasRole("USER")
                //Adds the SocialAuthenticationFilter to Spring Security's filter chain.
                .and()
                    .apply(new SpringSocialConfigurer());
    }

    /**
     * Configures the authentication manager bean which processes authentication
     * requests.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    /**
     * This is used to hash the password of the user.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        if(encoder == null) {
            encoder = new BCryptPasswordEncoder(10);
        }
        return encoder;
    }

    /**
     * This bean is used to load the user specific data when social sign in
     * is used.
     */
    @Bean
    public SocialUserDetailsService socialUserDetailsService() {
        return new SimpleSocialUserDetailsService(userDetailsService());
    }

    /**
     * This bean is load the user specific data when form login is used.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new RepositoryUserDetailsService(userRepository);
    }



}
