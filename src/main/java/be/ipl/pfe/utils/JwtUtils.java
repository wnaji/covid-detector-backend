package be.ipl.pfe.utils;

import be.ipl.pfe.exceptions.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.Properties;

public class JwtUtils {

    private static Properties props = new Properties();

    static {
        String profile = System.getenv("PROFILE");
        System.out.println(profile);
        if (profile == null) profile = "local";

        try (FileInputStream file = new FileInputStream("src/main/resources/application-" + profile + ".properties")) {
            props.load(file);
        } catch (IOException ioException) {
            System.out.println("Error while reading profile properties file.");
            System.exit(1);
        }
    }

    public static String createJWT(String id) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date(System.currentTimeMillis());

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(props.getProperty("JWT_SECRET"));
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    public static String decodeJWT(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(props.getProperty("JWT_SECRET")))
                    .parseClaimsJws(token)
                    .getBody()
                    .getId();
        } catch (Exception ex) {
            throw new InvalidTokenException();
        }
    }

    public static String createQRCodeContentToken(String doctorId, String qrCodeId) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date(System.currentTimeMillis());

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(props.getProperty("JWT_SECRET"));
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setId(doctorId)
                .setSubject(qrCodeId)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    public static Claims decodeQRCodeContentToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(props.getProperty("JWT_SECRET")))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            throw new InvalidTokenException();
        }
    }

}
