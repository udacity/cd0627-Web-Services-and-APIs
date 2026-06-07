package com.ecommerce.rma.controller;

import com.ecommerce.rma.event.ReturnApprovedEvent;
import com.ecommerce.rma.service.DashboardService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

/**
 * GraphQL controller that exposes the RMA dashboard query.
 *
 * <p>Spring for GraphQL uses {@code @Controller} (not {@code @RestController}) for
 * GraphQL resolvers. Each method annotated with {@code @QueryMapping} corresponds
 * to a field on the GraphQL {@code Query} type defined in
 * {@code src/main/resources/graphql/schema.graphqls}.
 *
 * <p>The GraphiQL playground is available at
 * <a href="http://localhost:8080/graphiql">http://localhost:8080/graphiql</a>
 * when the app is running.
 */
@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // =========================================================================
    // TODO (Step 6 – GraphQL Query Resolver):
    //
    // Add the @QueryMapping annotation to the method below.
    //
    // How @QueryMapping works:
    //  - Spring for GraphQL looks at the method name ("returnsDashboard") and
    //    matches it to the field with the same name on the GraphQL `type Query`
    //    defined in schema.graphqls.
    //  - The return type (Collection<ReturnApprovedEvent>) must be compatible
    //    with the GraphQL schema list type ([ReturnApprovedEvent]).
    //  - Spring automatically serialises each Java record field to a GraphQL field.
    //
    // After adding @QueryMapping, you can test the endpoint in GraphiQL with:
    //
    //   query {
    //     returnsDashboard {
    //       customerId
    //       itemType
    //       reason
    //     }
    //   }
    //
    // Note: The list will be empty until at least one approved return has been
    // submitted (POST /api/returns) and consumed by DashboardService.
    // =========================================================================

    @QueryMapping
    public Collection<ReturnApprovedEvent> returnsDashboard() {
        return dashboardService.getDashboard();
    }
}