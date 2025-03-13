package mytechshop.mytechshop.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static io.jsonwebtoken.Jwts.*;


@Service
public class JwtService {

    // Current time in milliseconds
    private final long currentTimeMillis = System.currentTimeMillis();

    // Expiration time: 1 hour from now
    private final long jwtExpiration = currentTimeMillis + 3600 * 1000; // 3600 seconds = 1 hour


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey() , SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey()) // Ensure this method returns the correct key
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            // Handle exceptions (e.g., invalid token, expired token, etc.)
            throw new RuntimeException("Failed to extract claims from token: " + e.getMessage(), e);
        }
    }



    private Key getSignInKey() {
        String secretKey = "2e5d430d0a21765cd0203c301a16b385f150a087b0ec07408adffc57f93d64ad60eb749268ee822a48b55022dd097d570b0b9fe7ab2fe63a3de924766c282a428becd2727ac27eb55270173b7eb47f08628cfe25d94e450ca72effde73bb732bd5d7fdfd1bb1da7cba681b515495a8ef5b2a302ff3fe01774ae2a08eba62149a236e9ad537f2be4aa6ba30c0871f51f2aaf652987c64f243cf6b1096035ca717424ce74f048fc5f2dd97c4ae3139be8afe2b996b79d7429ee47017f05e121c9540c5de68e3a933af94adccfdce3bec207ff0e9ede212c0b11a4957239aa8a2284c6b932c7975b0ea25b2bfb44bdaf1709ad45fcdf84136e2d2d4f6f780509363";
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}