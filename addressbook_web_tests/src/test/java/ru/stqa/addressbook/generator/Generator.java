package ru.stqa.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.stqa.addressbook.utils.CommonFunctions.*;

public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-c"})
    int count;

    private String getResourceDir() {
        try (var input = Generator.class.getClassLoader().getResourceAsStream("local.properties")) {
            var props = new java.util.Properties();
            props.load(input);
            return props.getProperty("resource.dir");
        } catch (IOException e) {
            throw new RuntimeException("Could not load 'resource.dir' from local.properties", e);
        }
    }

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    private List<?> generateData(Supplier<?> dataSupplier) {
        return Stream.generate(dataSupplier)
                .limit(count)
                .collect(Collectors.toList());

    }

    private Object generateGroups() {
        return generateData(() -> new GroupData()
                .withName(randomString(10))
                .withHeader(randomString(10))
                .withFooter(randomString(10)));
    }

    private Object generateContacts() {
        return generateData(() -> new ContactData()
                .withFirstName(randomString(5))
                .withLastName(randomString(5))
                .withAddress(randomString(10))
                .withHomePhone(randomHomePhone())
                .withMobilePhone(randomMobilePhone())
                .withWorkPhone(randomWorkPhone())
                .withEmail(randomEmail())
                .withEmail2(randomEmail())
                .withEmail3(randomEmail())
                .withPhoto(getRandomImagePath(getResourceDir())));
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            saveAsJson(data);
        } else if ("yaml".equals(format)) {
            saveAsYaml(data);
        } else if ("xml".equals(format)) {
            saveAsXml(data);

        } else {
            throw new IllegalArgumentException("Unknown format: " + format);
        }

    }

    private void saveAsJson(Object data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        var json = mapper.writeValueAsString(data);

        try (var writer = new FileWriter(output)) {
            writer.write(json);
        }

    }

    private void saveAsYaml(Object data) throws IOException {
        var mapper = new YAMLMapper();
        mapper.writeValue(new File(output), data);
    }

    private void saveAsXml(Object data) throws IOException {
        var mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(output), data);
    }


}


