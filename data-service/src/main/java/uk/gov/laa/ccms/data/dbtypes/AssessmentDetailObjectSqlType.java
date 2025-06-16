package uk.gov.laa.ccms.data.dbtypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import uk.gov.laa.ccms.data.model.CaseAssessmentDetail;

public class AssessmentDetailObjectSqlType extends CaseAssessmentDetail implements
    SQLData {

  @Override
  @JsonIgnore
  public String getSQLTypeName() throws SQLException {
    return "XXCCMS.XXCCMS_ASSESSMENT_OBJ";
  }

  @Override
  public void readSQL(SQLInput stream, String typeName) throws SQLException {
    setEntityName(stream.readString());
    setInstanceLabel(stream.readString());
    setAttributeName(stream.readString());
    setAttributeValue(stream.readString());
    setAttributeType(stream.readString());
    setAttributeUserDefinedIndicator(stream.readBoolean());
  }

  @Override
  public void writeSQL(SQLOutput stream) throws SQLException {
    // Not required
  }

}
