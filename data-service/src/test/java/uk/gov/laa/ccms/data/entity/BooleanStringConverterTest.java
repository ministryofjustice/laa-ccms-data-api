package uk.gov.laa.ccms.data.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BooleanStringConverterTest {

  @InjectMocks
  BooleanStringConverter booleanStringConverter;

  @Nested
  @DisplayName("Test ConvertToDb")
  class ConvertToDbTest {

    @Test
    @DisplayName("Should return true")
    void shouldReturnTrue(){
      assertTrue(booleanStringConverter.convertToEntityAttribute("True"));
    }

    @Test
    @DisplayName("Should return true lowercase")
    void shouldReturnTrueLowercase(){
      assertTrue(booleanStringConverter.convertToEntityAttribute("true"));
    }

    @Test
    @DisplayName("Should return true uppercase")
    void shouldReturnTrueUppercase(){
      assertTrue(booleanStringConverter.convertToEntityAttribute("TRUE"));
    }

    @Test
    @DisplayName("Should return false")
    void shouldReturnFalse(){
      assertFalse(booleanStringConverter.convertToEntityAttribute("False"));
    }

    @Test
    @DisplayName("Should return false lowercase")
    void shouldReturnFalseLowercase(){
      assertFalse(booleanStringConverter.convertToEntityAttribute("false"));
    }

    @Test
    @DisplayName("Should return false uppercase")
    void shouldReturnFalseUppercase(){
      assertFalse(booleanStringConverter.convertToEntityAttribute("FALSE"));
    }

  }

  @Nested
  @DisplayName("Test ConvertToDatabaseColumn")
  class ConvertToDatabaseColumnTest {

    @Test
    @DisplayName("Should return true")
    void shouldReturnTrue(){
      assertEquals("true", booleanStringConverter.convertToDatabaseColumn(true));
    }

    @Test
    @DisplayName("Should return false")
    void shouldReturnFalse(){
      assertEquals("false", booleanStringConverter.convertToDatabaseColumn(false));
    }
  }
}
