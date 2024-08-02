package lschaan.springconcurrenthashmap.token;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/token")
public class TokenController {

    private static final long CACHE_DURATION = 30 * 1000; //30 seconds in millis
    private final ConcurrentHashMap<String, MemoryCache> cacheConcurrentHashMap;

    public TokenController() {
        this.cacheConcurrentHashMap = new ConcurrentHashMap<>();
    }

    //For every key, generates a token. A token lasts 30 seconds.
    @PostMapping("/{key}")
    public String getToken(@PathVariable String key) {
        MemoryCache tokenCache = cacheConcurrentHashMap.get(key);

        //generates new token & adds to cache
        if (tokenCache == null) {
            System.out.println("Cache for key " + key + " does not exist.");
            String token = createToken();
            cacheConcurrentHashMap.put(key, new MemoryCache(token, System.currentTimeMillis()));
            return token;
        }

        //cache expired, create & update
        if (System.currentTimeMillis() - tokenCache.createdAt > CACHE_DURATION) {
            System.out.println("Cache for key " + key + " is expired.");
            String token = createToken();
            cacheConcurrentHashMap.put(key, new MemoryCache(token, System.currentTimeMillis()));
            return token;
        }

        System.out.println("Cache for key " + key + " is still valid");
        return tokenCache.getToken();
    }

    private static String createToken() {
        Random random = new Random();
        String token = String.valueOf(1000000 + random.nextInt(9000000));
        System.out.println("Random 7-digit token: " + token);
        return token;
    }

    static class MemoryCache {
        private String token;
        private Long createdAt;

        public MemoryCache(String token, Long createdAt) {
            this.token = token;
            this.createdAt = createdAt;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Long createdAt) {
            this.createdAt = createdAt;
        }

    }
}
