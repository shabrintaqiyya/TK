package apap.tugas.akhir.RumahSehat.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

public class JwtTokenFilter extends OncePerRequestFilter{
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
    
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getTokenFromRequest(request);
			String username = null;
			//System.out.println("Token-- " + token);
			if (token != null) {
			// if (token != null && jwtTokenUtil.validateJwtToken(token)) {
				// username = jwtTokenUtil.getUsernameFromToken(token);
				//System.out.println("User Name--JwtTokenFilter-- " + username);
				// UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				// //System.out.println("Authorities--JwtTokenFilter-- " + userDetails.getAuthorities());
				// UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				// 		userDetails, null, userDetails.getAuthorities());
				// authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				// SecurityContextHolder.getContext().setAuthentication(authentication);
				try {
					username = jwtTokenUtil.getUsernameFromToken(token);
				} catch (IllegalArgumentException e) {
					System.out.println("Unable to get JWT Token");
				} catch (ExpiredJwtException e) {
					System.out.println("JWT Token has expired");
				}
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	
				// if token is valid configure Spring Security to manually set authentication
				if (jwtTokenUtil.validateToken(token, userDetails)) {
	
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					// After setting the Authentication in the context, we specify
					// that the current user is authenticated. So it passes the Spring Security Configurations successfully.
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			filterChain.doFilter(request, response);

		} catch (Exception e) {
			//logger.error("Cannot set user authentication: {}", e);
			throw new RuntimeException("Cannot set user authentication" + e.getMessage());
		}
	}
	
	private String getTokenFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			// remove "Bearer "
			return token.substring(7, token.length());
		}
		return null;
	}
}