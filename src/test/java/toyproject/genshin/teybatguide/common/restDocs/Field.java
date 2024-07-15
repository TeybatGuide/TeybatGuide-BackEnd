package toyproject.genshin.teybatguide.common.restDocs;

import lombok.Getter;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.snippet.Attributes;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static toyproject.genshin.teybatguide.common.restDocs.RestDocsUtil.constraints;

@Getter
public class Field {
    private String path;
    private Object type;
    private String description;
    private String constraints;
    private boolean optional;

    public Field(String path, Object type, String description) {
        this.path = path;
        this.type = type;
        this.description = description;
        this.constraints = null;
        this.optional = false;
    }

    public Field(String path, Object type, String description, String constraints, boolean optional) {
        this.path = path;
        this.type = type;
        this.description = description;
        this.constraints = constraints;
        this.optional = optional;
    }

    public static List<FieldDescriptor> toFieldDescriptors(List<Field> fields) {
        return fields.stream()
                .map(field -> {
                    FieldDescriptor descriptor = fieldWithPath(field.getPath())
                            .type(field.getType())
                            .description(field.getDescription());
                    if (field.isOptional()) {
                        descriptor.optional();
                    }
                    if (field.getConstraints() != null && !field.getConstraints().isEmpty()) {
                        descriptor.attributes(constraints(field.getConstraints()));
                    }
                    return descriptor;
                })
                .collect(Collectors.toList());
    }

    public static List<ParameterDescriptor> toQueryDescriptors(List<Field> fields) {
        return fields.stream()
                .map(field -> {
                    ParameterDescriptor parameterDescriptor = parameterWithName(field.getPath())
                            .description(field.getDescription())
                            .attributes(
                                    Attributes.key("type").value(field.getType())
                            );
                    if (field.isOptional()) {
                        parameterDescriptor.optional();
                    }
                    if (field.getConstraints() != null) {
                        parameterDescriptor.attributes(constraints(field.getConstraints()));
                    }
                    return parameterDescriptor;
                })
                .collect(Collectors.toList());
    }
}
