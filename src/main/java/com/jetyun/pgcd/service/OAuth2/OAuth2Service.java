package com.jetyun.pgcd.service.OAuth2;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.LoggerFactory;


/**
 * OAuth权限验证服务功能
 * 
 * @author Lxh
 * 
 */
public class OAuth2Service {
	static String clientId = "f8ec8afac04b7297af247a710f9ada1a944a068167911580fe885743a8ae104e";
	static String clientSecret = "dcc1bd9a8ba824c5b48301b7b36ea41de4cbfa55ce1e8976c03fba05ac9cd342";
	static String tokenUrl = "http://octopus.bigbata.com/api/v1/oauth/token";

	/**
	 * 获取访问的token信息
	 * 
	 * @return
	 * @throws OAuthSystemException
	 * @throws OAuthProblemException
	 * @throws OAuth2Util.AccessTokenERROR
	 */
	public static String getAccessToken() throws OAuthSystemException,
			OAuthProblemException {
		OAuthClientRequest request = OAuthClientRequest.tokenLocation(tokenUrl)
				.setGrantType(GrantType.CLIENT_CREDENTIALS)
				.setClientId(clientId).setClientSecret(clientSecret)
				.buildQueryMessage();

		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthAccessTokenResponse oAuthResponse = oAuthClient
				.accessToken(request);

		return oAuthResponse.getAccessToken();
	}

	/**
	 * 获取最终用于访问的authorization信息
	 * 
	 * @return
	 */
	public static String getAuthorization() {
		String token;
		try {
			token = OAuth2Service.getAccessToken();
			token = "Bearer " + token;
		} catch (Exception e) {
			LoggerFactory.getLogger(OAuth2Service.class).error("获取accesstoken失败",
					e);
			throw new RuntimeException("系统错误，操作失败");
		}
		return token;
	}

	public static void main(String[] args) {
		System.out.println(getAuthorization());
	}
}
