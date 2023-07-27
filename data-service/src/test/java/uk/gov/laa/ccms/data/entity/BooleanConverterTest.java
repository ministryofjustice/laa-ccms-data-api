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
public class BooleanConverterTest {

  @InjectMocks
  BooleanConverter booleanConverter;

  @Test
  public void testConvertDbToEntity_Y() {
    assertTrue(booleanConverter.convertToEntityAttribute('Y'));
  }

  @Test
  public void testConvertDbToEntity_N() {
    assertFalse(booleanConverter.convertToEntityAttribute('N'));
  }

  @Test
  public void testConvertDbToEntity_AnythingElse() {
    assertFalse(booleanConverter.convertToEntityAttribute('P'));
  }

  @Test
  public void testConvertDbToEntity_NULL() {
    assertNull(booleanConverter.convertToEntityAttribute(null));
  }

  @Test
  public void testEntityToDb_true() {
    assertEquals('Y', booleanConverter.convertToDatabaseColumn(Boolean.TRUE));
  }

  @Test
  public void testEntityToDb_false() {
    assertEquals('N', booleanConverter.convertToDatabaseColumn(Boolean.FALSE));
  }

  @Test
  public void testEntityToDb_NULL() {
    assertNull(booleanConverter.convertToDatabaseColumn(null));
  }

}
