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
public class BooleanCharConverterTest {

  @InjectMocks
  BooleanCharConverter booleanCharConverter;

  @Test
  public void testConvertDbToEntity_Y() {
    assertTrue(booleanCharConverter.convertToEntityAttribute('Y'));
  }

  @Test
  public void testConvertDbToEntity_N() {
    assertFalse(booleanCharConverter.convertToEntityAttribute('N'));
  }

  @Test
  public void testConvertDbToEntity_AnythingElse() {
    assertFalse(booleanCharConverter.convertToEntityAttribute('P'));
  }

  @Test
  public void testConvertDbToEntity_NULL() {
    assertNull(booleanCharConverter.convertToEntityAttribute(null));
  }

  @Test
  public void testEntityToDb_true() {
    assertEquals('Y', booleanCharConverter.convertToDatabaseColumn(Boolean.TRUE));
  }

  @Test
  public void testEntityToDb_false() {
    assertEquals('N', booleanCharConverter.convertToDatabaseColumn(Boolean.FALSE));
  }

  @Test
  public void testEntityToDb_NULL() {
    assertNull(booleanCharConverter.convertToDatabaseColumn(null));
  }

}
