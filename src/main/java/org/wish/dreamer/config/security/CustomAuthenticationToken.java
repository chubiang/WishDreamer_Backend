package org.wish.dreamer.config.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    private String memId;
    private String nickNm;
    private String email;
    private String birtDt;
    private String authGrade;
    private String credentials;

    public CustomAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String memId, String email) {
        super(authorities);
        this.memId = memId;
        this.email = email;
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param credentials the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public CustomAuthenticationToken(String authGrade, String credentials) {
        super(null);
        this.authGrade = authGrade;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    public CustomAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String memId, String nickNm, String email, String birtDt, String authGrade, String credentials) {
        super(authorities);
        this.memId = memId;
        this.nickNm = nickNm;
        this.email = email;
        this.birtDt = birtDt;
        this.authGrade = authGrade;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    public CustomAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.authGrade;
    }
}
