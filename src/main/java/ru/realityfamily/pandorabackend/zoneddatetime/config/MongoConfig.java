package ru.realityfamily.pandorabackend.zoneddatetime.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.realityfamily.pandorabackend.zoneddatetime.converter.ZonedDateTimeReadConverter;
import ru.realityfamily.pandorabackend.zoneddatetime.converter.ZonedDateTimeWriteConverter;

import java.util.ArrayList;
import java.util.List;

@EnableMongoRepositories(basePackages = { "ru.realityfamily" })
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();

    @Override
    protected String getDatabaseName() {
        return "test";
    }

    @Override
    public MongoCustomConversions customConversions() {
        converters.add(new ZonedDateTimeReadConverter());
        converters.add(new ZonedDateTimeWriteConverter());
        return new MongoCustomConversions(converters);
    }

}
