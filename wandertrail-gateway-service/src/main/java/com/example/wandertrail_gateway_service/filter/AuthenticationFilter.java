// package com.example.wandertrail_gateway_service.filter;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.cloud.gateway.filter.GatewayFilter;
// import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.server.reactive.ServerHttpRequest;

// import com.example.wandertrail_gateway_service.util.JwtUtil;

// import io.jsonwebtoken.Claims;

// // @Component
// public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
//     @Autowired
//     private JwtUtil jwtUtil;
//     private static final List<String> bypassPaths = List.of(
//             "/api/v1/auth/login",
//             "/api/v1/auth/register");

//     @Override
//     public GatewayFilter apply(Config config) {
//         return (exchange, chain) -> {
//             String path = exchange.getRequest().getURI().getPath();

//             // Bypass login/register
//             if (path != null && bypassPaths.contains(path)) {
//                 return chain.filter(exchange);
//             }

//             // Lấy header từ request của client kiểm tra có Authorization header không
//             String authHeader = exchange.getRequest().getHeaders().getFirst(jwtUtil.getHeader());
//             if (authHeader == null || !authHeader.startsWith(jwtUtil.getPrefix())) {
//                 // Set response 401 Unauthorized nếu không có hoặc sai định dạng
//                 exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                 // Trả về cho client và dừng chain filter
//                 return exchange.getResponse().setComplete();
//             }
//             // Lấy token từ header Authorization
//             // Cắt prefix "Bearer " để lấy token
//             String token = authHeader.substring(jwtUtil.getPrefix().length()).trim();

//             try {
//                 Claims claims = jwtUtil.validateToken(token);

//                 // Tạo request mới với header bổ sung thông tin user
//                 ServerHttpRequest newReq = exchange.getRequest().mutate()
//                         .header("X-User-Id", claims.getSubject())
//                         .header("X-User-Role", claims.get("role", String.class))
//                         .build();

//                 return chain.filter(exchange.mutate().request(newReq).build());
//             } catch (Exception e) {
//                 exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                 return exchange.getResponse().setComplete();
//             }
//         };
//     }

//     public static class Config {
//         // Put the configuration properties
//     }
// }
