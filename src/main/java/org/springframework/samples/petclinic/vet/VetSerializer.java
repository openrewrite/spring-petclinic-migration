/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.vet;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.springframework.boot.jackson.JsonComponent;

/**
 * Serializes a {@link Vet} for the JSON returned by {@link VetController}, listing each
 * specialty by name rather than as a nested object.
 *
 * @author Arjen Poutsma
 */
@JsonComponent
public class VetSerializer extends JsonSerializer<Vet> {

    @Override
    public void serialize(Vet vet, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("id", vet.getId());
        generator.writeStringField("firstName", vet.getFirstName());
        generator.writeStringField("lastName", vet.getLastName());
        generator.writeArrayFieldStart("specialties");
        for (Specialty specialty : vet.getSpecialties()) {
            generator.writeString(specialty.getName());
        }
        generator.writeEndArray();
        generator.writeEndObject();
    }

}
