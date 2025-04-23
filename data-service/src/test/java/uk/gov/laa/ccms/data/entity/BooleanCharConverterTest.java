package uk.gov.laa.ccms.data.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BooleanCharConverterTest {

  @InjectMocks
  BooleanCharConverter booleanCharConverter;

  @Test
  void convertDbToEntityY() {
    assertTrue(booleanCharConverter.convertToEntityAttribute('Y'));
  }

  @Test
  void convertDbToEntityN() {
    assertFalse(booleanCharConverter.convertToEntityAttribute('N'));
  }

  @Test
  void convertDbToEntityAnythingElse() {
    assertFalse(booleanCharConverter.convertToEntityAttribute('P'));
  }

  @Test
  void convertDbToEntityNULL() {
    assertNull(booleanCharConverter.convertToEntityAttribute(null));
  }

  @Test
  void entityToDbTrue() {
    assertEquals('Y', booleanCharConverter.convertToDatabaseColumn(Boolean.TRUE));
  }

  @Test
  void entityToDbFalse() {
    assertEquals('N', booleanCharConverter.convertToDatabaseColumn(Boolean.FALSE));
  }

  @Test
  void entityToDbNULL() {
    assertNull(booleanCharConverter.convertToDatabaseColumn(null));
  }

}
