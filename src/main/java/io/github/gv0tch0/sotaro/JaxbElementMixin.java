package io.github.gv0tch0.sotaro;

import javax.xml.bind.JAXBElement;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Ensures, when the Jackson {@link ObjectMapper} is configured with it, that
 * {@link JAXBElement}-wrapped response objects when serialized as JSON documents
 * do not feature the JAXBElement properties; but instead the JSON-document that
 * results in marshalling the member returned by the {@link JAXBElement#getValue()}
 * call.
 * <p>
 * More on the usage and configuration options is available
 * <a href="http://wiki.fasterxml.com/JacksonMixInAnnotations">here</a>.
 */
public interface JaxbElementMixin {
  @JsonValue
  Object getValue();
}

