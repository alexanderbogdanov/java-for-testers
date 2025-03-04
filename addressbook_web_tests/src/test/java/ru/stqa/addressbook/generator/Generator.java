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

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withName(randomString(i * 10))
                    .withHeader(randomString(i * 10))
                    .withFooter(randomString(i * 10)));
        }
        return result;
    }

    private Object generateContacts() {
        var result = new ArrayList<ContactData>();
            for (int i = 0; i < count; i++) {
            result.add(new ContactData()
                    .withFirstName(randomString(i * 5))
                    .withLastName(randomString(i * 5))
                    .withAddress(randomString(i * 10))
                    .withHomePhone(randomPhone())
                    .withMobilePhone(randomPhone())
                    .withWorkPhone(randomPhone())
                    .withEmail(randomEmail())
                    .withEmail2(randomEmail())
                    .withEmail3(randomEmail())
                    .withPhoto(getRandomImagePath("src/test/resources/images/"))
            );
        }
        return result;
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


