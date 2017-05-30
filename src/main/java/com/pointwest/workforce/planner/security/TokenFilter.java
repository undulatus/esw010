package com.pointwest.workforce.planner.security;

import org.springframework.stereotype.Component;

@Component
public class TokenFilter {
	/*extends OncePerRequestFilter {
}

	@Autowired
	JWTDecoderService jwtDecoderService;
	
	@Value("${token.request.header}")
	private String TOKENHEADER;
	
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = request.getHeader("Authorization");
        
        // validate the value in xAuth
        if(jwtDecoderService.isValidToken(token) == false){
            throw new SecurityException();
        }                            
        
        // The token is 'valid' so magically get a user id from it
        TokenUser tokenUser = jwtDecoderService.getTokenUser(token);
        
        // Create our Authentication and let Spring know about it
        Authentication auth = new UsernamePasswordAuthenticationToken(tokenUser.getUsername(), "password");
        SecurityContextHolder.getContext().setAuthentication(auth);      
        
        filterChain.doFilter(request, response);
    }
*/
}