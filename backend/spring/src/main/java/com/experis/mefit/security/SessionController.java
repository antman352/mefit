package com.experis.mefit.security;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import com.experis.mefit.security.models.Credentials.CredentialType;
import com.experis.mefit.security.models.SecurityProperties;
import com.experis.mefit.security.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.SessionCookieOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/session")
public class SessionController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private CookieService cookieUtils;

	@Autowired
	private SecurityProperties secProps;

	@PostMapping("login")
	public void sessionLogin(HttpServletRequest request) {
		String idToken = securityService.getBearerToken(request);
		User user = securityService.getUser();
		//TODO: Remove println later
		System.out.println("USER ID: " + user.getUid());
		int sessionExpiryDays = secProps.getFirebaseProps().getSessionExpiryInDays();
		long expiresIn = TimeUnit.DAYS.toMillis(sessionExpiryDays);
		SessionCookieOptions options = SessionCookieOptions.builder().setExpiresIn(expiresIn).build();
		try {
			String sessionCookieValue = FirebaseAuth.getInstance().createSessionCookie(idToken, options);
			cookieUtils.setSecureCookie("session", sessionCookieValue, sessionExpiryDays);
			cookieUtils.setCookie("authenticated", Boolean.toString(true), sessionExpiryDays);
			// cookieUtils.setCookie("displayName", user.getDisplayName().replaceAll("\\s+", "_").toLowerCase(), sessionExpiryDays);
		} catch (FirebaseAuthException e) {
			e.printStackTrace();
		}
	}

	@PostMapping("logout")
	public void sessionLogout() {
		if (securityService.getCredentials().getType() == CredentialType.SESSION
				&& secProps.getFirebaseProps().isEnableLogoutEverywhere()) {
			try {
				FirebaseAuth.getInstance().revokeRefreshTokens(securityService.getUser().getUid());
			} catch (FirebaseAuthException e) {
				e.printStackTrace();
			}
		}
		cookieUtils.deleteSecureCookie("session");
		cookieUtils.deleteCookie("authenticated");
		cookieUtils.deleteCookie("displayName");
	}

	@PostMapping("me")
	public User getUser() {
		return securityService.getUser();
	}

	@GetMapping("create/token")
	public String getCustomToken() throws FirebaseAuthException {
		return FirebaseAuth.getInstance().createCustomToken(String.valueOf(securityService.getUser().getUid()));
	}
}
