package com.bta.axondemo.infra.sql.config;

import com.bta.axondemo.domain.plr.events.upcasters.SituationUpdatedEventUpcaster;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    public SingleEventUpcaster myUpcaster() {
        return new SituationUpdatedEventUpcaster();
    }

    //    @Bean
//    public JpaEventStorageEngine eventStorageEngine(Serializer eventSerializer,
//                                                    Serializer snapshotSerializer,
//                                                    DataSource dataSource,
//                                                    SingleEventUpcaster myUpcaster,
//                                                    EntityManagerProvider entityManagerProvider,
//                                                    TransactionManager transactionManager) throws SQLException {
//        return JpaEventStorageEngine.builder()
//                .eventSerializer(eventSerializer)
//                .snapshotSerializer(snapshotSerializer)
//                .dataSource(dataSource)
//                .entityManagerProvider(entityManagerProvider)
//                .transactionManager(transactionManager)
//                .upcasterChain(myUpcaster)
//                .build();
//    }
//    @Bean
//    public EventStorageEngine eventStorageEngine(Serializer defaultSerializer,
//                                                 PersistenceExceptionResolver persistenceExceptionResolver,
//                                                 @Qualifier("eventSerializer") Serializer eventSerializer,
//                                                 AxonConfiguration configuration,
//                                                 EntityManagerProvider entityManagerProvider,
//                                                 TransactionManager transactionManager) {
//        return JpaEventStorageEngine.builder()
//                .snapshotSerializer(defaultSerializer)
//                .upcasterChain(configuration.upcasterChain())
//                .persistenceExceptionResolver(persistenceExceptionResolver)
//                .eventSerializer(eventSerializer)
//                .entityManagerProvider(entityManagerProvider)
//                .transactionManager(transactionManager)
//                .build();
//    }
}
