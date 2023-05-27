package stefanmonko.sk.accountsweb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("features")
public class FeatureProperties {

    @Getter @Setter private Boolean canaryflag;
    @Getter @Setter private Boolean datastream;
    @Getter @Setter private Boolean dbdata;
    @Getter @Setter private Boolean Messaging;

}
