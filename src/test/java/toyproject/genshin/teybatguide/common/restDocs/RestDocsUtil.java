package toyproject.genshin.teybatguide.common.restDocs;

import java.util.List;

import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.QueryParametersSnippet;
import org.springframework.restdocs.snippet.Attributes.Attribute;

import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.restdocs.snippet.Attributes.key;

public class RestDocsUtil {

    public static QueryParametersSnippet generateRequestParams(List<Field> fields) {
        return queryParameters(Field.toQueryDescriptors(fields));
    }

    public static RequestFieldsSnippet generateRequestFields(List<Field> fields) {
        return requestFields(Field.toFieldDescriptors(fields));
    }

    public static ResponseFieldsSnippet generateResponseFields(List<Field> fields) {
        return responseFields(Field.toFieldDescriptors(fields));
    }

    public static Attribute constraints(String constraint) {
        return key("constraints").value(constraint);
    }
}
