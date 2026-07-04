package com.synapse.gatewayservice.filters;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public @NonNull Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        String method = exchange.getRequest().getMethod().toString();
        long start = System.currentTimeMillis();

        logger.info("REQUEST: {} {}", method, path);

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            String status = String.valueOf(exchange.getResponse().getStatusCode());
            long duration = System.currentTimeMillis() - start;
            logger.info("RESPONSE: {} {} | status={} | duration={}ms", method, path, status, duration);
        }));
    }
}
