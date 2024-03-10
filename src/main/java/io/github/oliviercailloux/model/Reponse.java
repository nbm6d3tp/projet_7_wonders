package io.github.oliviercailloux.model;

/**
 * Model of the response when we make a request HTTPS to the server
 */
class Response {
	String idToken;
	String email;
	String refreshToken;
	String expiresIn;
	String localId;
}
